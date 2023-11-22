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
public class MockInfo {

    private List<MockRule> mockRule;
    private String mockResult;
    private MockConfig mockConfig;
    public void setMockRule(List<MockRule> mockRule) {
         this.mockRule = mockRule;
     }
     public List<MockRule> getMockRule() {
         return mockRule;
     }

    public void setMockResult(String mockResult) {
         this.mockResult = mockResult;
     }
     public String getMockResult() {
         return mockResult;
     }

    public void setMockConfig(MockConfig mockConfig) {
         this.mockConfig = mockConfig;
     }
     public MockConfig getMockConfig() {
         return mockConfig;
     }

}
