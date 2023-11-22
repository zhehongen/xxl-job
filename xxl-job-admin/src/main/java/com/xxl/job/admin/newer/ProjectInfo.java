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
public class ProjectInfo {

    private String projectName;
    private int projectType;
    private Date projectUpdateTime;
    private String projectVersion;
    private String projectDesc;
    private String mockApiSetting;
    private String duplicateCheckSetting;
    private String synchronizeSetting;
    public void setProjectName(String projectName) {
         this.projectName = projectName;
     }
     public String getProjectName() {
         return projectName;
     }

    public void setProjectType(int projectType) {
         this.projectType = projectType;
     }
     public int getProjectType() {
         return projectType;
     }

    public void setProjectUpdateTime(Date projectUpdateTime) {
         this.projectUpdateTime = projectUpdateTime;
     }
     public Date getProjectUpdateTime() {
         return projectUpdateTime;
     }

    public void setProjectVersion(String projectVersion) {
         this.projectVersion = projectVersion;
     }
     public String getProjectVersion() {
         return projectVersion;
     }

    public void setProjectDesc(String projectDesc) {
         this.projectDesc = projectDesc;
     }
     public String getProjectDesc() {
         return projectDesc;
     }

    public void setMockApiSetting(String mockApiSetting) {
         this.mockApiSetting = mockApiSetting;
     }
     public String getMockApiSetting() {
         return mockApiSetting;
     }

    public void setDuplicateCheckSetting(String duplicateCheckSetting) {
         this.duplicateCheckSetting = duplicateCheckSetting;
     }
     public String getDuplicateCheckSetting() {
         return duplicateCheckSetting;
     }

    public void setSynchronizeSetting(String synchronizeSetting) {
         this.synchronizeSetting = synchronizeSetting;
     }
     public String getSynchronizeSetting() {
         return synchronizeSetting;
     }

}
