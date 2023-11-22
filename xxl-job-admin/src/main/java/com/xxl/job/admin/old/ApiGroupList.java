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
public class ApiGroupList {

    private int groupID;
    private String groupName;
    private int projectID;
    private int parentGroupID;
    private int isChild;
    private List<ApiList> apiList;
    private List<String> apiGroupChildList;
    public void setGroupID(int groupID) {
         this.groupID = groupID;
     }
     public int getGroupID() {
         return groupID;
     }

    public void setGroupName(String groupName) {
         this.groupName = groupName;
     }
     public String getGroupName() {
         return groupName;
     }

    public void setProjectID(int projectID) {
         this.projectID = projectID;
     }
     public int getProjectID() {
         return projectID;
     }

    public void setParentGroupID(int parentGroupID) {
         this.parentGroupID = parentGroupID;
     }
     public int getParentGroupID() {
         return parentGroupID;
     }

    public void setIsChild(int isChild) {
         this.isChild = isChild;
     }
     public int getIsChild() {
         return isChild;
     }

    public void setApiList(List<ApiList> apiList) {
         this.apiList = apiList;
     }
     public List<ApiList> getApiList() {
         return apiList;
     }

    public void setApiGroupChildList(List<String> apiGroupChildList) {
         this.apiGroupChildList = apiGroupChildList;
     }
     public List<String> getApiGroupChildList() {
         return apiGroupChildList;
     }

}
