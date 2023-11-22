/**
  * Copyright 2020 bejson.com
  */
package com.xxl.job.admin.old;
import java.util.Date;

/**
 * Auto-generated: 2020-09-11 17:37:17
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ProjectInfo {

    private String projectName;
    private int projectType;
    private Date projectUpdateTime;
    private String projectVersion;
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

}
