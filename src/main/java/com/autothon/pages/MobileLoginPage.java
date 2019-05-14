package com.autothon.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MobileLoginPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;



	@FindBy(xpath=".//input[@id='ember465']")
	MobileElement userNameText;

	@FindBy(xpath=".//input[@id='ember467']")
	MobileElement passwordText;

	@FindBy(xpath=".//button[@id='ember477']")
	MobileElement signInButton;

	@FindBy(xpath=".//p[contains(.,'Please fill out the form to sign in.')]")
	MobileElement errorMessage;


	public MobileLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), this);
	}

	public MobileLoginPage enterUserName(String userNameVal){
		userNameText.sendKeys(userNameVal);
		return this;
	}

	public MobileLoginPage enterPassword(String passwordVal){
		passwordText.sendKeys(passwordVal);
		return this;
	}

	public String getErrorMesage(){
		String error=errorMessage.getText();
		return error;
	}

	public void clickSignIn(){
		signInButton.click();       
	}

}
