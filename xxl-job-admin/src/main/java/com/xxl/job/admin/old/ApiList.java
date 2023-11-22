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
public class ApiList {

    private BaseInfo baseInfo;
    private List<String> headerInfo;
    private MockInfo mockInfo;
    private List<RequestInfo> requestInfo;
    private List<ResultInfo> resultInfo;
    public void setBaseInfo(BaseInfo baseInfo) {
         this.baseInfo = baseInfo;
     }
     public BaseInfo getBaseInfo() {
         return baseInfo;
     }

    public void setHeaderInfo(List<String> headerInfo) {
         this.headerInfo = headerInfo;
     }
     public List<String> getHeaderInfo() {
         return headerInfo;
     }

    public void setMockInfo(MockInfo mockInfo) {
         this.mockInfo = mockInfo;
     }
     public MockInfo getMockInfo() {
         return mockInfo;
     }

    public void setRequestInfo(List<RequestInfo> requestInfo) {
         this.requestInfo = requestInfo;
     }
     public List<RequestInfo> getRequestInfo() {
         return requestInfo;
     }

    public void setResultInfo(List<ResultInfo> resultInfo) {
         this.resultInfo = resultInfo;
     }
     public List<ResultInfo> getResultInfo() {
         return resultInfo;
     }

}
