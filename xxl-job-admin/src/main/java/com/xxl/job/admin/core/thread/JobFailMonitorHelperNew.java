package com.xxl.job.admin.core.thread;

import com.xxl.job.admin.core.conf.XxlJobAdminConfig;
import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.core.model.XxlJobLog;
import com.xxl.job.admin.core.trigger.TriggerTypeEnum;
import com.xxl.job.admin.core.util.I18nUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * job monitor instance
 *
 * @author xuxueli 2015-9-1 18:05:56
 */
@SuppressWarnings("DuplicatedCode")
public class JobFailMonitorHelperNew {
    public static final long PRE_READ_MS = 5000;    // pre read
    private static final Logger logger = LoggerFactory.getLogger(JobFailMonitorHelperNew.class);

    private static final JobFailMonitorHelperNew instance = new JobFailMonitorHelperNew();
    private Thread monitorThread;

    private static final Map<Integer, List<Long>> ringData = new ConcurrentHashMap<>();
    private Thread ringThread;

    // ---------------------- monitor ----------------------
    private volatile boolean toStop = false;
    private volatile boolean ringThreadToStop = false;

    public static JobFailMonitorHelperNew getInstance() {
        return instance;
    }

    public void start() {
        monitorThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(5000 - System.currentTimeMillis() % 1000);
                } catch (InterruptedException e) {
                    if (!toStop) {
                        logger.error(e.getMessage(), e);
                    }
                }

                logger.info(">>>>>>>>> init xxl-job admin fail retry scheduler success.");

                // monitor
                while (!toStop) {
                    long start = System.currentTimeMillis();

                    Connection conn = null;
                    Boolean connAutoCommit = null;
                    PreparedStatement preparedStatement = null;

                    boolean preReadSuc = true;
                    try {
                        conn = XxlJobAdminConfig.getAdminConfig().getDataSource().getConnection();
                        connAutoCommit = conn.getAutoCommit();
                        conn.setAutoCommit(false);

                        preparedStatement = conn.prepareStatement("select * from xxl_fail_retry_lock where lock_name = 'fail_retry_lock' for update");
                        preparedStatement.execute();

                        long nowTime = System.currentTimeMillis();

                        List<Long> failLogIds = XxlJobAdminConfig.getAdminConfig().getXxlJobLogDao().findFailJobLogIdsNew(1000, nowTime + PRE_READ_MS);
                        if (failLogIds != null && !failLogIds.isEmpty()) {
                            for (long failLogId : failLogIds) {

                                // lock log
                                int lockRet = XxlJobAdminConfig.getAdminConfig().getXxlJobLogDao().updateAlarmStatus(failLogId, 0, -1);
                                if (lockRet < 1) {
                                    continue;
                                }
                                //每次trigger一次执行，都会生成一条log,所以log是鲜活的。不会出现TriggerMsg叠加问题
                                XxlJobLog log = XxlJobAdminConfig.getAdminConfig().getXxlJobLogDao().load(failLogId);
                                XxlJobInfo info = XxlJobAdminConfig.getAdminConfig().getXxlJobInfoDao().loadById(log.getJobId());

                                boolean needRetryButHaveNot = false;

                                // 1、fail retry monitor
                                if (log.getExecutorFailRetryCount() > 0) {//需要重试

                                    if (nowTime >= log.getTriggerNextTime()) {//立即执行
                                        //--------------------------------------------------------------------此处会将重试次数减1
                                        JobTriggerPoolHelper.trigger(log.getJobId(), TriggerTypeEnum.RETRY, (log.getExecutorFailRetryCount() - 1), log.getExecutorShardingParam(), log.getExecutorParam(), null);
                                        String retryMsg = "<br><br><span style=\"color:#F39C12;\" > >>>>>>>>>>>" + I18nUtil.getString("jobconf_trigger_type_retry") + "<<<<<<<<<<< </span><br>";
                                        log.setTriggerMsg(log.getTriggerMsg() + retryMsg);
                                        XxlJobAdminConfig.getAdminConfig().getXxlJobLogDao().updateTriggerInfo(log);
                                    } else {
                                        needRetryButHaveNot = true;
                                        // 1、make ring second
                                        int ringSecond = (int) ((log.getTriggerNextTime() / 1000) % 60);

                                        // 2、push time ring
                                        pushTimeRing(ringSecond, log.getId());
                                    }
                                }
                                if (needRetryButHaveNot) {//恢复原样 todo 有可能永远成为锁定状态
                                    XxlJobAdminConfig.getAdminConfig().getXxlJobLogDao().updateAlarmStatus(failLogId, -1, 0);
                                } else {
                                    // 2、fail alarm monitor
                                    int newAlarmStatus = 0;        // 告警状态：0-默认、-1=锁定状态、1-无需告警、2-告警成功、3-告警失败
                                    if (info != null && info.getAlarmEmail() != null && info.getAlarmEmail().trim().length() > 0) {
                                        boolean alarmResult = XxlJobAdminConfig.getAdminConfig().getJobAlarmer().alarm(info, log);
                                        newAlarmStatus = alarmResult ? 2 : 3;
                                    } else {
                                        newAlarmStatus = 1;
                                    }

                                    XxlJobAdminConfig.getAdminConfig().getXxlJobLogDao().updateAlarmStatus(failLogId, -1, newAlarmStatus);
                                }
                            }
                        } else {
                            preReadSuc = false;
                        }

                    } catch (Exception e) {
                        if (!toStop) {
                            logger.error(">>>>>>>>>>> xxl-job, job fail monitor thread error:{}", e);
                        }
                    } finally {

                        // commit
                        if (conn != null) {
                            try {
                                conn.commit();
                            } catch (SQLException e) {
                                if (!toStop) {
                                    logger.error(e.getMessage(), e);
                                }
                            }
                            try {
                                conn.setAutoCommit(connAutoCommit);
                            } catch (SQLException e) {
                                if (!toStop) {
                                    logger.error(e.getMessage(), e);
                                }
                            }
                            try {
                                conn.close();
                            } catch (SQLException e) {
                                if (!toStop) {
                                    logger.error(e.getMessage(), e);
                                }
                            }
                        }

                        // close PreparedStatement
                        if (null != preparedStatement) {
                            try {
                                preparedStatement.close();
                            } catch (SQLException e) {
                                if (!toStop) {
                                    logger.error(e.getMessage(), e);
                                }
                            }
                        }

                        long cost = System.currentTimeMillis() - start;

                        logger.debug("---------zhe--------fail retry cost= " + cost + "preReadSuc= " + preReadSuc);
                        // Wait seconds, align second
                        if (cost < 1000) {  // scan-overtime, not wait
                            try {
                                // pre-read period: success > scan each second; fail > skip this period;
                                TimeUnit.MILLISECONDS.sleep((preReadSuc ? 1000 : PRE_READ_MS) - System.currentTimeMillis() % 1000);
                            } catch (InterruptedException e) {
                                if (!toStop) {
                                    logger.error(e.getMessage(), e);
                                }
                            }
                        }

                    }

                }

                logger.info(">>>>>>>>>>> xxl-job, job fail monitor thread stop");

            }
        });
        monitorThread.setDaemon(true);
        monitorThread.setName("xxl-job, admin JobFailMonitorHelper");
        monitorThread.start();


        // ring thread
        ringThread = new Thread(new Runnable() {
            @Override
            public void run() {

                // align second
                try {
                    TimeUnit.MILLISECONDS.sleep(1000 - System.currentTimeMillis() % 1000);
                } catch (InterruptedException e) {
                    if (!ringThreadToStop) {
                        logger.error(e.getMessage(), e);
                    }
                }

                while (!ringThreadToStop) {

                    try {
                        // second data
                        List<Long> ringItemData = new ArrayList<>();
                        int nowSecond = Calendar.getInstance().get(Calendar.SECOND);   // 避免处理耗时太长，跨过刻度，向前校验一个刻度；
                        for (int i = 0; i < 2; i++) {
                            List<Long> tmpData = ringData.remove((nowSecond + 60 - i) % 60);
                            if (tmpData != null) {
                                ringItemData.addAll(tmpData);
                            }
                        }

                        // ring trigger
                        logger.debug(">>>>>>>>>>> xxl-job, time-ring beat : " + nowSecond + " = " + Arrays.asList(ringItemData));
                        if (ringItemData.size() > 0) {
                            // do trigger
                            for (long logId : ringItemData) {
                                XxlJobLog log = XxlJobAdminConfig.getAdminConfig().getXxlJobLogDao().load(logId);
                                //--------------------------------------------------------------------此处会将重试次数减1
                                JobTriggerPoolHelper.trigger(log.getJobId(), TriggerTypeEnum.RETRY, (log.getExecutorFailRetryCount() - 1), log.getExecutorShardingParam(), log.getExecutorParam(), null);
                                String retryMsg = "<br><br><span style=\"color:#F39C12;\" > >>>>>>>>>>>" + I18nUtil.getString("jobconf_trigger_type_retry") + "<<<<<<<<<<< </span><br>";
                                log.setTriggerMsg(log.getTriggerMsg() + retryMsg);
                                XxlJobAdminConfig.getAdminConfig().getXxlJobLogDao().updateTriggerInfo(log);
                                // todo 报警没做
                            }
                            // clear
                            ringItemData.clear();
                        }
                    } catch (Exception e) {
                        if (!ringThreadToStop) {
                            logger.error(">>>>>>>>>>> xxl-job, JobFailMonitorHelper#ringThread error:{}", e);
                        }
                    }

                    // next second, align second
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000 - System.currentTimeMillis() % 1000);
                    } catch (InterruptedException e) {
                        if (!ringThreadToStop) {
                            logger.error(e.getMessage(), e);
                        }
                    }
                }
                logger.info(">>>>>>>>>>> xxl-job, JobFailMonitorHelper#ringThread stop");
            }
        });
        ringThread.setDaemon(true);
        ringThread.setName("xxl-job, admin JobFailMonitorHelper#ringThread");
        ringThread.start();
    }

    public void toStop() {
        toStop = true;
        // interrupt and wait
        monitorThread.interrupt();
        try {
            monitorThread.join();
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void pushTimeRing(int ringSecond, Long logId) {
        // push async ring
        List<Long> ringItemData = ringData.get(ringSecond);
        if (ringItemData == null) {
            ringItemData = new ArrayList<Long>();
            ringData.put(ringSecond, ringItemData);
        }
        ringItemData.add(logId);

        logger.debug(">>>>>>>>>>> xxl-job, fail retry push time-ring : " + ringSecond + " = " + Arrays.asList(ringItemData));
    }
}
