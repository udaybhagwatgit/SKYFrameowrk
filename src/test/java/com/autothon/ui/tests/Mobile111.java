package com.autothon.ui.tests;


import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

//import com.autothon.pages.MobileLoginPage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Mobile111 {


       @Test
         public void mobileTest() {
              // Create object of  DesiredCapabilities class and specify android platform
              DesiredCapabilities capabilities=DesiredCapabilities.android();
              
              
              // set the capability to execute test in chrome browser
              capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
              
              // set the capability to execute our test in Android Platform
              capabilities.setCapability(MobileCapabilityType.PLATFORM,Platform.ANDROID);
              
              // we need to define platform name
              capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
              
              // Set the device name as well (you can give any name)
              capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"my phone");
              
              // set the android version as well 
              capabilities.setCapability(MobileCapabilityType.VERSION,"7.1.1");
              URL url= null;
              // Create object of URL class and specify the appium server address
                                     try {
                                                    url = new URL("http://0.0.0.0:4723/wd/hub");
                                     } catch (MalformedURLException e) {
                                                    e.printStackTrace();
                                     }
              
              // Create object of  AndroidDriver class and pass the url and capability that we created
              WebDriver driver = new AndroidDriver(url, capabilities);
              
              driver.get("https://18.220.173.145/admin");
              
//            mob.enterUserName("tm2@testmile.net");
//            mob.enterPassword("TestMile123");
//            
//            mob.clickSignIn();
              
//              return driver;
          }

}



