package com.autothon.ui.tests;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;

import com.autothon.config.WebDriverFactory;
import com.autothon.dao.mongorepo.LoginPropertiesRepository;
import com.autothon.dao.mongorepo.TestDataRepsitory;
import com.autothon.entity.LoginProperties;
import com.autothon.pages.LoginPage;
import com.autothon.spring.test.TestApplicationContext;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { TestApplicationContext.class })
public class TestBase extends AbstractTestNGSpringContextTests {

    @Autowired protected WebDriverFactory webDriverFactory;
    @Autowired protected TestDataRepsitory testDataRepsitory;
    @Autowired protected LoginPropertiesRepository loginPropertiesRepository;

    protected WebDriver driver = null;

    //Add this in the Test
    @DataProvider(name = "hardCodedBrowsers", parallel = true)
    public static Object[][] sauceBrowserDataProvider(Method testMethod) {
        return new Object[][]{
                new Object[]{"MicrosoftEdge", "14.14393", "Windows 10"},
                new Object[]{"firefox", "49.0", "Windows 10"}
                /*new Object[]{"internet explorer", "11.0", "Windows 7"},
                new Object[]{"safari", "10.0", "OS X 10.11"},
                new Object[]{"chrome", "54.0", "OS X 10.10"},*/
                /*new Object[]{"firefox", "latest-1", "Windows 7"},*/
        };
    }

    /**
     * @return the {@link WebDriver} for the current thread
     */
    public WebDriver getWebDriver(String browser, String version, String os, String methodName) throws MalformedURLException, UnexpectedException  {

        driver = webDriverFactory.createDriver(browser, version, os, methodName);
        return driver;
    }

    public void maximizeBrowser() {
        driver.manage().window().maximize();
    }

    public LoginPage launchApplication(String url) {
        driver.get(url);
        return new LoginPage(driver);
    }
    

    public String getUrlFromLoginProperty()
    {
        LoginProperties loginProperty=loginPropertiesRepository.getLoginProperties();
        return loginProperty.getUrl();
    }

    /**
     * Method that gets invoked after test.
     * Dumps browser log and
     * Closes the browser
     */
  /*  @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        driver.quit();
    }*/

}

