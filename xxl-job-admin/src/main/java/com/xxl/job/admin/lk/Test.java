package com.xxl.job.admin.lk;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hongen.zhang
 * time: 2022/3/30 10:38
 * email: hongen.zhang@things-matrix.com
 */
public class Test {
    public List<Integer> busiestServersBak(int k, int[] arrival, int[] load) {
        //arrival数组表示任务的到达时间，严格递增
        //load数组表示任务的需要的处理时间
        //最繁忙表示的是这个服务器处理的请求数最多
        //第i个请求优先由第i%k个服务器处理。不需要记录上次轮询到的服务器索引

        //当前轮询到的server索引
        int serverIndex = 0;
        //相应server将在哪个时间点空闲
        int[] serverIdlePoint = new int[k];
        //每个服务器执行任务的数量
        List<Integer> execNum = new ArrayList<>(k);
        for (int i = 0; i < serverIdlePoint.length; i++) {
            execNum.add(0);
        }
        //对任务进行轮询
        for (int i = 0; i < arrival.length; i++) {
            //第i个任务的到达时间
            int arrivalTime = arrival[i];
            //第i个任务需要消耗的时间
            int consumeTime = load[i];
            //对于任务i从serverIndex开始轮询到的当前轮询server索引
            int serverCurrentIndex = serverIndex;
            boolean first = true;
            boolean success = false;
            //对server进行轮询
            while (first || serverCurrentIndex != serverIndex) {
                first = false;
                //当前server空闲时间点
                int serverCurrentIdlePoint = serverIdlePoint[serverCurrentIndex];
                //如果当前server的空闲时间点小于任务到达时间则将此任务分配给此服务器，并更新空闲时间点后，并记录任务执行数量后，结束循环
                if (serverCurrentIdlePoint <= arrivalTime) {
                    System.out.println("第" + (i + 1) + "个任务分给了第" + serverCurrentIndex + "个服务器");

                    serverIdlePoint[serverCurrentIndex] = consumeTime + arrivalTime;
                    Integer num = execNum.get(serverCurrentIndex);
                    execNum.set(serverCurrentIndex, num == null ? 1 : ++num);
                    success = true;
                }
                //索引加1
                serverCurrentIndex++;
                //求模
                serverCurrentIndex %= k;
                if (success) {
                    serverIndex = serverCurrentIndex;
                    break;
                }
            }
            if (!success) {
                System.out.println("第" + (i + 1) + "个任务丢弃了");
            }
        }
        return execNum;
    }

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        //arrival数组表示任务的到达时间，严格递增
        //load数组表示任务的需要的处理时间
        //最繁忙表示的是这个服务器处理的请求数最多
        //第i个请求优先由第i%k个服务器处理。不需要记录上次轮询到的服务器索引


        //相应server将在哪个时间点空闲
        int[] serverIdlePoint = new int[k];
        //每个服务器执行任务的数量
        int[] execNum = new int[k];
        //对任务进行轮询
        for (int i = 0; i < arrival.length; i++) {
            //第i个任务的到达时间
            int arrivalTime = arrival[i];
            //第i个任务需要消耗的时间
            int consumeTime = load[i];
            //当前轮询到的server索引
            int serverIndex = i % k;
            //当前轮询到的server索引
            int serverCurrentIndex = serverIndex;
            boolean first = true;
            boolean success = false;
            //对server进行轮询
            while (first || serverCurrentIndex != serverIndex) {
                first = false;
                //当前server空闲时间点
                int serverCurrentIdlePoint = serverIdlePoint[serverCurrentIndex];
                //如果当前server的空闲时间点小于任务到达时间则将此任务分配给此服务器，并更新空闲时间点后，并记录任务执行数量后，结束循环
                if (serverCurrentIdlePoint <= arrivalTime) {
                    System.out.println("第" + i + "个任务分给了第" + serverCurrentIndex + "个服务器");

                    serverIdlePoint[serverCurrentIndex] = consumeTime + arrivalTime;
                    execNum[serverCurrentIndex]++;
                    success = true;
                    break;
                }
                //索引加1
                serverCurrentIndex++;
                //求模
                serverCurrentIndex %= k;
            }
            if (!success) {
                System.out.println("第" + i + "个任务丢弃了");
            }
        }
        return sort(execNum);
    }

    public static void main(String[] args) {
//        int k = 3;
//        int[] arrival = new int[]{1, 2, 3, 4, 5};
//        int[] load = new int[]{5, 2, 3, 3, 3};

//        int k = 3;
//        int[] arrival = new int[]{1, 2, 3, 4};
//        int[] load = new int[]{1, 2, 1, 2};

//        int k = 3;
//        int[] arrival = new int[]{1, 2, 3};
//        int[] load = new int[]{10, 12, 11};

        int k = 3;
        int[] arrival = new int[]{1, 2, 3, 4, 8, 9, 10};
        int[] load = new int[]{5, 2, 10, 3, 1, 2, 2};


        List<Integer> busiestServers = new Test().busiestServers(k, arrival, load);
        System.out.println(busiestServers);
    }

    //获取元素最大的位置
    public static List<Integer> sort(int[] arr) {
        //最大的元素
        int max = 0;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                result.clear();
                result.add(i);
            } else if (arr[i] == max) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main1(String[] args) {
        int[] arr = new int[]{5, 2, 10, 3, 1, 2, 2};
        List<Integer> result = sort(arr);
        System.out.println(result);
    }
}
