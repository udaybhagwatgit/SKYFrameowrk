package com.autothon.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    
    @FindBy(css="input[name = 'identification']")
    WebElement userNameText;
    
    @FindBy(css="input[name = 'password']")
    WebElement passwordText;
    
    @FindBy(css="button[type='submit']")
    WebElement signInButton;
    
    @FindBy(xpath=".//p[contains(.,'Please fill out the form to sign in.')]")
    WebElement errorMessage;
    
    public LoginPage(WebDriver driver) {
    	this.driver = driver;
        js = ((JavascriptExecutor) driver);
        wait = new WebDriverWait(driver, 180, 1000);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }
    
    public LoginPage enterUserName(String userNameVal){
		userNameText.sendKeys(userNameVal);
		return this;
	}
	
	public LoginPage enterPassword(String passwordVal){
		passwordText.sendKeys(passwordVal);
		return this;
	}
	
	public String getErrorMesage(){
		String error=errorMessage.getText();
		return error;
	}
	
	public HomePage clickSignIn(){
		signInButton.click();
		return new HomePage(driver);
	}
    
}
