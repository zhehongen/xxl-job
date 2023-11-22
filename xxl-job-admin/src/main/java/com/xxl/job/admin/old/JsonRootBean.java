/**
  * Copyright 2020 bejson.com
  */
package com.xxl.job.admin.old;
import java.util.List;

/**
 * Auto-generated: 2020-09-11 17:37:17
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private ProjectInfo projectInfo;
    private List<ApiGroupList> apiGroupList;
    private List<StatusCodeGroupList> statusCodeGroupList;
    private List<String> env;
    private List<String> pageGroupList;
    private List<String> caseGroupList;
    public void setProjectInfo(ProjectInfo projectInfo) {
         this.projectInfo = projectInfo;
     }
     public ProjectInfo getProjectInfo() {
         return projectInfo;
     }

    public void setApiGroupList(List<ApiGroupList> apiGroupList) {
         this.apiGroupList = apiGroupList;
     }
     public List<ApiGroupList> getApiGroupList() {
         return apiGroupList;
     }

    public void setStatusCodeGroupList(List<StatusCodeGroupList> statusCodeGroupList) {
         this.statusCodeGroupList = statusCodeGroupList;
     }
     public List<StatusCodeGroupList> getStatusCodeGroupList() {
         return statusCodeGroupList;
     }

    public void setEnv(List<String> env) {
         this.env = env;
     }
     public List<String> getEnv() {
         return env;
     }

    public void setPageGroupList(List<String> pageGroupList) {
         this.pageGroupList = pageGroupList;
     }
     public List<String> getPageGroupList() {
         return pageGroupList;
     }

    public void setCaseGroupList(List<String> caseGroupList) {
         this.caseGroupList = caseGroupList;
     }
     public List<String> getCaseGroupList() {
         return caseGroupList;
     }

}
