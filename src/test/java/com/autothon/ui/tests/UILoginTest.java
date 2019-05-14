package com.autothon.ui.tests;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.autothon.entity.TestData;
import com.autothon.pages.HomePage;
import com.autothon.pages.LoginPage;

/*
4.	Launch application and check the title
5.	Login and verify the fields
8.	Login using wrong password
 */

public class UILoginTest extends TestBase {
	
	HomePage homePage; 

   

    @Test
    public void testValidLogin() throws MalformedURLException, UnexpectedException {

    	
        driver = getWebDriver("firefox","49.0","Windows 10","testValidLogin");
        String url = "http://18.220.173.145/admin";
        driver.get(url);
        maximizeBrowser();
        LoginPage loginPage = new LoginPage(driver);
//      String userName = (String) testData.get("userName");
//      String password = (String) testData.get("password");
  
  String userName = "tm1@testmile.net";
		  String password = "TestMile123";
          
         // change the method names accordingly
            loginPage.enterUserName(userName);
            loginPage.enterPassword(password);
            homePage=loginPage.clickSignIn();
            
                       
            String homePageTitle = homePage.returnHomePageTitle();
            Assert.assertTrue(homePageTitle.equals("Your stories"));


    }
        
         
        @Test(dataProvider = "LoginCredentialsDataProvider")
        public void testBlankLogin(Map<String, Object> testData, String browserName, String version, String os, String methodName) throws MalformedURLException, UnexpectedException {

            driver = getWebDriver(browserName,version,os,methodName);
            String url = getUrlFromLoginProperty();
            LoginPage loginPage = launchApplication(url);
            maximizeBrowser();

                String userName = (String) testData.get("userName");
                String password = (String) testData.get("password");
                
             // change the method names accordingly
                loginPage.clickSignIn();
                homePage=loginPage.clickSignIn();
                
             
                String error = loginPage.getErrorMesage();
              Assert.assertTrue(error.equals("Please fill out the form to sign in."));


            
    }
        
//      @Test(dataProvider = "LoginCredentialsDataProvider")
//      public void testInValidLogin(Map<String, Object> testData, String browserName, String version, String os, String methodName) throws MalformedURLException, UnexpectedException {
//
//          driver = getWebDriver(browserName,version,os,methodName);
//          String url = getUrlFromLoginProperty();
//          LoginPage loginPage = launchApplication(url);
//          maximizeBrowser();
//
//              String userName = (String) testData.get("userName");
//              String password = (String) testData.get("password");
//              
//           // change the method names accordingly
//              loginPage.clickSignIn();
//              loginPage.enterUserName(userName);
//              loginPage.enterPassword(password);
//              homePage=loginPage.clickSignIn();
//              
//              String error = loginPage.getErrorMesage();
//              Assert.assertTrue(error.equals("Please fill out the form to sign in."));
//
//          
//  }
}
