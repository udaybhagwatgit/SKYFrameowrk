package com.autothon.entity;

import java.util.Map;

public class TestData {

    private String testClass;
    private String testMethod;
    private Map<String, Object> additionalData;

    public String getTestClass() {
        return testClass;
    }
    public void setTestClass(String testClass) {
        this.testClass = testClass;
    }
    public String getTestMethod() {
        return testMethod;
    }
    public void setTestMethod(String testMethod) {
        this.testMethod = testMethod;
    }
    public Map<String, Object> getAdditionalData() {
        return additionalData;
    }
    public void setAdditonalData(Map<String, Object> additonalData) {
        this.additionalData = additonalData;
    }
}