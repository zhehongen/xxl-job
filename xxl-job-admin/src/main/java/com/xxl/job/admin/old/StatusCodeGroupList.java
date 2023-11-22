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
public class StatusCodeGroupList {

    private int groupID;
    private int projectID;
    private String groupName;
    private int parentGroupID;
    private int isChild;
    private List<String> statusCodeList;
    private List<String> statusCodeGroupChildList;
    public void setGroupID(int groupID) {
         this.groupID = groupID;
     }
     public int getGroupID() {
         return groupID;
     }

    public void setProjectID(int projectID) {
         this.projectID = projectID;
     }
     public int getProjectID() {
         return projectID;
     }

    public void setGroupName(String groupName) {
         this.groupName = groupName;
     }
     public String getGroupName() {
         return groupName;
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

    public void setStatusCodeList(List<String> statusCodeList) {
         this.statusCodeList = statusCodeList;
     }
     public List<String> getStatusCodeList() {
         return statusCodeList;
     }

    public void setStatusCodeGroupChildList(List<String> statusCodeGroupChildList) {
         this.statusCodeGroupChildList = statusCodeGroupChildList;
     }
     public List<String> getStatusCodeGroupChildList() {
         return statusCodeGroupChildList;
     }

}
