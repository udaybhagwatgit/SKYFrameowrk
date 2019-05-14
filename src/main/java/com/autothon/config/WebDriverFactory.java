package com.autothon.config;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

@Component
public class WebDriverFactory {

	public String username = "uday123";

	public String accesskey = "2b0dddc6-47ae-4462-a441-89cf5de9af98";

	private ThreadLocal<String> sessionId = new ThreadLocal<String>();
	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private ThreadLocal<AndroidDriver> androidDriver = new ThreadLocal<AndroidDriver>();

	public String getSessionId() {
		return sessionId.get();
	}

	/**
	 *
	 * @return the Sauce Job id for the current thread
	 */

	public WebDriver createDriver(String browser, String version, String os, String methodName)
			throws MalformedURLException, UnexpectedException {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		// set desired capabilities to launch appropriate browser on Sauce
		capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
		capabilities.setCapability(CapabilityType.VERSION, version);
		capabilities.setCapability(CapabilityType.PLATFORM, os);
		capabilities.setCapability("name", methodName);

		// Launch remote browser and set it as the current thread
		driver.set(new RemoteWebDriver(
				new URL("https://" + username + ":" + accesskey + "@ondemand.saucelabs.com:443/wd/hub"), capabilities));

		// set current sessionId
		String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
		sessionId.set(id);

		return driver.get();
	}

	public WebDriver createAndroidDriver() throws MalformedURLException, UnexpectedException {
		// Create object of DesiredCapabilities class and specify android
		// platform

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4");
		capabilities.setCapability("deviceName", "Samsung Galaxy S4 Emulator");
		capabilities.setCapability("deviceOrientation", "portrait");
		capabilities.setCapability("appiumVersion", "1.5.3");

		String app = "";

		capabilities.setCapability("app", app);

		// Launch remote browser and set it as the current thread
		androidDriver.set(new AndroidDriver(
				new URL("https://" + username + ":" + accesskey + "@ondemand.saucelabs.com:443" + "/wd/hub"),
				capabilities));

		String id = ((RemoteWebDriver) getAndroidDriver()).getSessionId().toString();

		return androidDriver.get();

	}

	public AndroidDriver getAndroidDriver() {
		return androidDriver.get();
	}

	/**
	 * @return the {@link WebDriver} for the current thread
	 */
	public WebDriver getWebDriver() {
		return driver.get();
	}

	protected void annotate(String text) {
		((JavascriptExecutor) driver.get()).executeScript("sauce:context=" + text);
	}

}
