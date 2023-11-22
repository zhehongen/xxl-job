/**
  * Copyright 2020 bejson.com
  */
package com.xxl.job.admin.newer;
import com.xxl.job.admin.newer.DataStructureList;

import java.util.List;

/**
 * Auto-generated: 2020-09-11 23:8:48
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */

public class JsonRootBean {

    private ProjectInfo projectInfo;
    private List<ApiGroupList> apiGroupList;
    private List<String> tagNameList;
    private List<String> statusCodeGroupList;
    private List<String> pageGroupList;
    private boolean env;
    private List<String> commonFunctionList;
    private List<DataStructureList> dataStructureList;
    private List<String> apiTemplateList;
    private String apiGroupOrder;
    private String statusCodeGroupOrder;
    private String pageGroupOrder;
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

    public void setTagNameList(List<String> tagNameList) {
         this.tagNameList = tagNameList;
     }
     public List<String> getTagNameList() {
         return tagNameList;
     }

    public void setStatusCodeGroupList(List<String> statusCodeGroupList) {
         this.statusCodeGroupList = statusCodeGroupList;
     }
     public List<String> getStatusCodeGroupList() {
         return statusCodeGroupList;
     }

    public void setPageGroupList(List<String> pageGroupList) {
         this.pageGroupList = pageGroupList;
     }
     public List<String> getPageGroupList() {
         return pageGroupList;
     }

    public void setEnv(boolean env) {
         this.env = env;
     }
     public boolean getEnv() {
         return env;
     }

    public void setCommonFunctionList(List<String> commonFunctionList) {
         this.commonFunctionList = commonFunctionList;
     }
     public List<String> getCommonFunctionList() {
         return commonFunctionList;
     }

    public void setDataStructureList(List<DataStructureList> dataStructureList) {
         this.dataStructureList = dataStructureList;
     }
     public List<DataStructureList> getDataStructureList() {
         return dataStructureList;
     }

    public void setApiTemplateList(List<String> apiTemplateList) {
         this.apiTemplateList = apiTemplateList;
     }
     public List<String> getApiTemplateList() {
         return apiTemplateList;
     }

    public void setApiGroupOrder(String apiGroupOrder) {
         this.apiGroupOrder = apiGroupOrder;
     }
     public String getApiGroupOrder() {
         return apiGroupOrder;
     }

    public void setStatusCodeGroupOrder(String statusCodeGroupOrder) {
         this.statusCodeGroupOrder = statusCodeGroupOrder;
     }
     public String getStatusCodeGroupOrder() {
         return statusCodeGroupOrder;
     }

    public void setPageGroupOrder(String pageGroupOrder) {
         this.pageGroupOrder = pageGroupOrder;
     }
     public String getPageGroupOrder() {
         return pageGroupOrder;
     }

}
