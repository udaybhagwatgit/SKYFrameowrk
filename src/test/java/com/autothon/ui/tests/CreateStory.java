package com.autothon.ui.tests;

import java.lang.reflect.Method;
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

public class CreateStory extends TestBase {
	HomePage homePage; 
	
    @Test
    public void createNewStory() throws Exception {

    	driver = getWebDriver("firefox","49.0","Windows 10","testValidLogin");
        String url = "http://18.220.173.145/admin";
        driver.get(url);
        maximizeBrowser();
        LoginPage loginPage = new LoginPage(driver);

//              String userName = (String) testData.get("userName");
//              String password = (String) testData.get("password");
          
          String userName = "tm1@testmile.net";
		  String password = "TestMile123";
            
           // change the method names accordingly
              loginPage.enterUserName(userName);
              loginPage.enterPassword(password);
              homePage=loginPage.clickSignIn();
              
              
              String homePageTitle = homePage.returnHomePageTitle();
              Assert.assertTrue(homePageTitle.equals("Your stories"));


  		// verify
  		String title=homePage.returnHomePageTitle();
  		homePage.clickOnWriteStory();
  		homePage.enterStoryTitle("Story title");
  		homePage.enterContent("Publish");


              
              
    }

}
