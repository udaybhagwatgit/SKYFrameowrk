package com.autothon.util;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

@SuppressWarnings("unused")
public class Utilities {

	private static final int MAXRETRY = 15;
	private static final int RETRYRESTTIME = 750;

	/**
	 * Returns true if - the 'text' is present in the Page
	 * @param driver
	 * @param text - The Text we want to check for existence
	 * @param waittime - The time we want to wait
	 * @param wait
	 * @return
	 */
	public static boolean checkElementWithPageSource(WebDriver driver, String text, int waittime, WebDriverWait wait) {
		Boolean bool = false;

		wait.until(documentReady());
		for (int second = 0;; second++) {
			try {
				bool = driver.getPageSource().contains(text);
				if (bool) {
					System.out.println("Text is present-->" + text);
					break;
				} else {
					if (second > waittime) {
						Assert.fail("time out.....");
						break;
					} else {
						Thread.sleep(1000);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return bool;
	}

	/**
	 * Waits for the Frame to load
	 * @param driver
	 * @param framename - Name of the Frame
	 * @param wait
	 */
	public static void waitForFrame(WebDriver driver, String framename, WebDriverWait wait) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(framename)));

	}

	/**
	 * 
	 * @param driver
	 * @param button
	 * @param wait
	 * @throws InterruptedException
	 */
	public static void clickButton(WebDriver driver, WebElement button, WebDriverWait wait) throws InterruptedException {
		wait.until(elementToBeClickable(button));
		moveToElement(driver, button);
		button.click();
	}

	/**
	 * Sets the file path in the upload path field. DOES NOT UPLOAD THE FILE THOUGH!
	 * @param element
	 * @param fileName
	 * @param relativePath
	 */
	public static void setFileNameForUploadFile(WebElement element, String fileName, String relativePath) {
		String ImageFolderpath = String.format("%s%s", System.getProperty("user.dir"), relativePath);
		String filepath = ImageFolderpath + fileName;
		element.sendKeys(filepath);
	}

	/**
	 * Javascript implementation of Scroll Down
	 * @param driver
	 * @param element
	 * @throws InterruptedException
	 */
	public static void scrollToElementDown(WebDriver driver, WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(1000);
	}

	/**
	 * Javascript implementation of Scroll Up
	 * @param driver
	 * @param element
	 * @throws InterruptedException
	 */
	public static void scrollToElementUp(WebDriver driver, WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
		Thread.sleep(1000);
	}

	/**
	 * Force clicks on an element, when the element is unclickable by Selenium
	 * @param driver
	 * @param element
	 * @param value
	 */
	public static void forceClickOnElement(WebDriver driver, WebElement element, String value) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
		element.sendKeys(value);
	}

	/**
	 * Switches the control to the Child/Sub window
	 * @param driver
	 * @return - The windowhandle for the parent window
	 */
	public static String switchToChildWindow(WebDriver driver) {
		String parentWindowHandler = driver.getWindowHandle(); // Store your
																// parent window
		for (String subWindowHandler : driver.getWindowHandles()) {
			driver.switchTo().window(subWindowHandler); // switch to pop up
														// window
		}
		return parentWindowHandler;
	}

	/**
	 * Launches an application is a new Tab
	 * @param url
	 * @param driver
	 */
	public static void getUrlonBrowserTab(String url, WebDriver driver) {

		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	/**
	 * Returns the URL of the current Website
	 * @param driver
	 * @return
	 */
	public static String getURL(WebDriver driver) {
        URL u = null;
        try {
            u = new URL(driver.getCurrentUrl());
        } catch (MalformedURLException e) {

        }
        String port = u.getPort() == -1 ? "" : ":" + String.valueOf(u.getPort());
        return u.getProtocol() + "://" + u.getHost() + port;
    }

	/**
	 * Returns a String representation of today's Date 
	 * @param dateFormat - DateFormat we need
	 * @param timeZone - The Timezone whose Date we need
	 * @return
	 */
	public static String getTodaysDate(String dateFormat, String timeZone) {

		Date today = new Date();
		DateFormat df = new SimpleDateFormat(dateFormat);
		df.setTimeZone(TimeZone.getTimeZone(timeZone));
		String PST = df.format(today);
		return PST;
	}

	/**
	 * Returns a String representation of a Previous Date
	 * 
	 * @param days
	 * @param strDate
	 * @param dateFormat
	 * @return
	 * @throws ParseException
	 */
	public static String getPreviousDate(int days, String strDate, String dateFormat) throws ParseException {

		DateFormat formatterObj = new SimpleDateFormat(dateFormat);
		Date dateChg = formatterObj.parse(strDate);
		Calendar calObj = Calendar.getInstance();
		calObj.setTime(dateChg);
		calObj.add(Calendar.DATE, -days);
		Date dateformat = calObj.getTime();
		String sDate = formatterObj.format(dateformat);

		return sDate;

	}

	/**
	 * Returns a String representation of a Future Month Date
	 * 
	 * @param Days
	 * @param strDate
	 * @param dateFormat
	 * @return
	 * @throws ParseException
	 */
	public static String getFutureMonthDate(int days, String strDate, String dateFormat) throws ParseException {

		DateFormat formatterObj = new SimpleDateFormat(dateFormat);
		Date dateChg = formatterObj.parse(strDate);
		Calendar calObj = Calendar.getInstance();
		calObj.setTime(dateChg);
		calObj.add(Calendar.MONTH, +days);
		Date dateformat = calObj.getTime();
		String sDate = formatterObj.format(dateformat);

		return sDate;

	}

	public static String getFutureDate(int days, String strDate, String dateFormat) throws ParseException {

		DateFormat formatterObj = new SimpleDateFormat(dateFormat);
		Date dateChg = formatterObj.parse(strDate);
		Calendar calObj = Calendar.getInstance();
		calObj.setTime(dateChg);
		calObj.add(Calendar.DATE, +days);
		Date dateformat = calObj.getTime();
		String sDate = formatterObj.format(dateformat);

		return sDate;

	}

	public static String getFutureDate(int days, int months) {
		TimeZone.setDefault(TimeZone.getTimeZone("PST"));
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("PST"));
		calendar.add(Calendar.DATE, days);
		calendar.add(Calendar.MONTH, months);

		return format.format(calendar.getTime());

	}

	public static String getDateTime(int days, int months) {
		TimeZone.setDefault(TimeZone.getTimeZone("PST"));
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("PST"));
		calendar.add(Calendar.DATE, days);
		calendar.add(Calendar.MONTH, months);
		return format.format(calendar.getTime());

	}

	public static String getYYYYMMDDpattern(int days, int months) {
		TimeZone.setDefault(TimeZone.getTimeZone("PST"));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("PST"));
		calendar.add(Calendar.DATE, days);
		calendar.add(Calendar.MONTH, months);

		return format.format(calendar.getTime());

	}

	public static String getTime() {
		TimeZone.setDefault(TimeZone.getTimeZone("PST"));
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("PST"));

		return format.format(calendar.getTime());
	}

	/**
	 * Drags and drops the element to a destination from source
	 * @param driver
	 * @param sourceElement
	 * @param destinationElement
	 */
	public static void dragAndDropElement(WebDriver driver, WebElement sourceElement, WebElement destinationElement) {
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceElement, destinationElement).build().perform();
	}

	/**
	 * Double click on the Element
	 * @param driver
	 * @param element
	 */
	public static void doubleClickElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver).doubleClick(element);
		action.perform();
	}
	
	/**
	 * Moves to the element by scrolling to the element
	 * @param driver
	 * @param element
	 */
	public static void moveToElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver).moveToElement(element);
		action.perform();
	}

	/**
	 * Clicks the element by Mouse
	 * @param driver
	 * @param element
	 */
	public static void clickElementByMouse(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver).moveToElement(element);
		action.click().perform();
	}

	/**
	 * Validate and return invalid links in a Page - VERIFY THIS
	 * @param driver
	 * @return
	 */
	public static List<String> validateAndReturnInvalidLinks(WebDriver driver) {

		List<String> invalidLinksList = new ArrayList<>();
		try {
			List<WebElement> anchorTagsList = driver.findElements(By.tagName("a"));
			for (WebElement anchorTagElement : anchorTagsList) {
				if (anchorTagElement != null) {
					String url = anchorTagElement.getAttribute("href");
					if (url != null && !url.contains("javascript")) {
						verifyURLStatus(url);
					} else {
						invalidLinksList.add(url);
					}
				}
			}			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return invalidLinksList;
	}

	public static void verifyURLStatus(String URL) {

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(URL);
		try {
			HttpResponse response = client.execute(request);
			int invalidLinksCount = 0;
			if (response.getStatusLine().getStatusCode() != 200)
				invalidLinksCount++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes all the Cookies of the browser
	 * 
	 * @param driver
	 */
	public static void deleteAllCookies(WebDriver driver) {
		driver.manage().deleteAllCookies();
	}

	/**
	 * Deletes a cookie by Name
	 * @param driver
	 * @param cookieName
	 */
	public static void deleteCookieByName(WebDriver driver, String cookieName) {
		driver.manage().deleteCookieNamed("cookieName");
	}

	/**
	 * Navigates forward
	 * @param driver
	 */
	public static void navigateForward(WebDriver driver) {
		driver.navigate().forward();
	}

	/**
	 * Navigates back
	 * @param driver
	 */
	public static void navigateBack(WebDriver driver) {
		driver.navigate().back();
	}

	/**
	 * Refreshes the Page
	 * @param driver
	 */
	public static void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	/**
	 * Checks for 2 Map<String, String> to be equal
	 * @param m1
	 * @param m2
	 * @return
	 */
	public static boolean checkForEqualityOfStringMaps(Map<String, String> m1, Map<String, String> m2) {
		if (m1.size() != m2.size())
			return false;
		for (String key : m1.keySet())
			if (!m1.get(key).equals(m2.get(key)))
				return false;
		return true;
	}

	public static ExpectedCondition<Boolean> documentReady() {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				String script = "return document.readyState === 'complete'";
				return (Boolean) js.executeScript(script);
			}

			@Override
			public String toString() {
				return String.format("%s()", "documentReady");
			}
		};
	}
}