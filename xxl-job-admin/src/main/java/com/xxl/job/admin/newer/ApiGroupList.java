/**
  * Copyright 2020 bejson.com
  */
package com.xxl.job.admin.newer;

import java.util.List;

/**
 * Auto-generated: 2020-09-11 23:8:48
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ApiGroupList {

    private long groupID;
    private String groupName;
    private int parentGroupID;
    private int groupDepth;
    private String groupOrder;
    private List<ApiList> apiList;
    public void setGroupID(long groupID) {
         this.groupID = groupID;
     }
     public long getGroupID() {
         return groupID;
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

    public void setGroupDepth(int groupDepth) {
         this.groupDepth = groupDepth;
     }
     public int getGroupDepth() {
         return groupDepth;
     }

    public void setGroupOrder(String groupOrder) {
         this.groupOrder = groupOrder;
     }
     public String getGroupOrder() {
         return groupOrder;
     }

    public void setApiList(List<ApiList> apiList) {
         this.apiList = apiList;
     }
     public List<ApiList> getApiList() {
         return apiList;
     }

}
