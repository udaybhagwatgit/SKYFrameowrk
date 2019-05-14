package com.autothon.ui.tests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.autothon.entity.TestData;

public class RESTAPIResponse extends TestBase {
	
	// 1.	REST api response should be Successful. 200 OK

    @DataProvider(name = "DataProvider")
    public Iterator<Object[]> DataProvider(Method method) {
        List<Object[]> dp = new ArrayList<Object[]>();

        TestData testData = testDataRepsitory.getTestData(this.getClass().getCanonicalName(), method.getName());
        Map<String, Object> additionalData = (testData.getAdditionalData());
        List<Map<String, Object>> browserInfos = (List<Map<String, Object>>) additionalData.get("browserInfo");
        for (Map<String, Object> browserInfo : browserInfos) {
            dp.add(new Object[] { additionalData, (String) browserInfo.get("browserName"), (String) browserInfo.get("version"), (String) browserInfo.get("OS"), method.getName() });
        }

        return dp.iterator();
    }

    @Test(dataProvider = "DataProvider")
    public void restAPIResponse200OK_test(Map<String, Object> testData, String browserName, String version, String os, String methodName) throws Exception {

      

    }
}
