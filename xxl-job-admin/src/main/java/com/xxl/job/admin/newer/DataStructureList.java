/**
  * Copyright 2020 bejson.com
  */
package com.xxl.job.admin.newer;

import java.util.Date;

/**
 * Auto-generated: 2020-09-11 23:8:48
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DataStructureList {

    private long structureID;
    private long projectID;
    private String structureName;
    private String structureDesc;
    private Date updateTime;
    private long createUserID;
    private long updateUserID;
    private String structureData;
    private String structureType;
    private int removed;
    public void setStructureID(long structureID) {
         this.structureID = structureID;
     }
     public long getStructureID() {
         return structureID;
     }

    public void setProjectID(long projectID) {
         this.projectID = projectID;
     }
     public long getProjectID() {
         return projectID;
     }

    public void setStructureName(String structureName) {
         this.structureName = structureName;
     }
     public String getStructureName() {
         return structureName;
     }

    public void setStructureDesc(String structureDesc) {
         this.structureDesc = structureDesc;
     }
     public String getStructureDesc() {
         return structureDesc;
     }

    public void setUpdateTime(Date updateTime) {
         this.updateTime = updateTime;
     }
     public Date getUpdateTime() {
         return updateTime;
     }

    public void setCreateUserID(long createUserID) {
         this.createUserID = createUserID;
     }
     public long getCreateUserID() {
         return createUserID;
     }

    public void setUpdateUserID(long updateUserID) {
         this.updateUserID = updateUserID;
     }
     public long getUpdateUserID() {
         return updateUserID;
     }

    public void setStructureData(String structureData) {
         this.structureData = structureData;
     }
     public String getStructureData() {
         return structureData;
     }

    public void setStructureType(String structureType) {
         this.structureType = structureType;
     }
     public String getStructureType() {
         return structureType;
     }

    public void setRemoved(int removed) {
         this.removed = removed;
     }
     public int getRemoved() {
         return removed;
     }

}
