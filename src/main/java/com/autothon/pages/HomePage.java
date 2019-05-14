package com.autothon.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.autothon.util.Utilities;

public class HomePage {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;

	@FindBy(xpath = "//h2[@class = 'gh-canvas-title']")
	WebElement pageTitleElement;
	@FindBy(xpath = "//button[@type = 'button']")
	WebElement writeStoryButton;
	@FindBy(css = "a.ember948")
	WebElement newStoryLink;
	@FindBy(css = "#ember861")
	WebElement newStoryFromFrame;
	@FindBy(css = "a.gh-nav-main-content.active.active.ember-view")
	WebElement listStoriesFromFrame;
	@FindBy(css = "textarea[placeholder = 'Post Title']")
	WebElement titleElement;
	@FindBy(css = ".CodeMirror-sizer .CodeMirror-lines>div[role = 'presentation']")
	WebElement contentTextArea;
	@FindBy(css = "section.view-actions div#ember1460 div.gh-btn.gh-btn-outline.gh-publishmenu-trigger.ember-basic-dropdown-trigger.ember-view span")
	WebElement publishDropDown;
	@FindBy(css = "button.gh-btn.gh-btn-blue.gh-publishmenu-button.gh-btn-icon.ember-view")
	WebElement publishButton;
	@FindBy(css = "section.content-list li.gh-posts-list-item.ember-view")
	List<WebElement> allPosts;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		js = ((JavascriptExecutor) driver);
		wait = new WebDriverWait(driver, 180, 1000);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	}

	public String returnHomePageTitle() {
		String pageText = pageTitleElement.getText();
		return pageText;
	}

	public HomePage clickOnWriteStory() {
		writeStoryButton.click();
		return this;
	}

	public HomePage enterStoryTitle(String title) {
		Utilities.moveToElement(driver, titleElement);
		Utilities.clickElementByMouse(driver, titleElement);
		titleElement.sendKeys(title);
		return this;
	}

	public HomePage enterContent(String content) {
		Utilities.moveToElement(driver, contentTextArea);
		Utilities.clickElementByMouse(driver, contentTextArea);
		titleElement.sendKeys(content);
		return this;
	}

	public HomePage clickOnStories() {
		Utilities.moveToElement(driver, listStoriesFromFrame);
		Utilities.clickElementByMouse(driver, listStoriesFromFrame);
		Utilities.clickElementByMouse(driver, listStoriesFromFrame);
		return this;
	}

	public boolean areStoriesSectionPopulated() {
		boolean ispopulated = true;
		List<WebElement> allPostsList = driver
				.findElements(By.cssSelector("section.content-list li.gh-posts-list-item.ember-view"));
		ispopulated = allPostsList.size() > 0 ? true : false;
		return ispopulated;
	}

	public boolean isStoryPresentWithName(String storyTitle) {
		boolean isPresent = true;
		String xpathForStoryTitle = String.format(
				"//section[@class = 'content-list']//li[@class = 'gh-posts-list-item ember-view']//a[text() = '%s']",
				storyTitle);
		try {
			WebElement storyTitleElement = driver.findElement(By.xpath(xpathForStoryTitle));
		} catch (NoSuchElementException e) {
			isPresent = false;
		}
		return isPresent;
	}
	
	public String getStatusOfPost(String postName) {
		String xpathForStoryTitle = String.format(
				"//section[@class = 'content-list']//li[@class = 'gh-posts-list-item ember-view']//a[text() = '%s']/parent::*[1]/following-sibling::*[2]/span[1]",
				postName);
		WebElement storyTitleElement = driver.findElement(By.xpath(xpathForStoryTitle));
		String status = storyTitleElement.getText();
		return status;
	}
	
	public String getAuthorOfPost(String postName) {
		String xpathForStoryTitle = String.format(
				"//section[@class = 'content-list']//li[@class = 'gh-posts-list-item ember-view']//a[text() = '%s']/parent::*[1]/following-sibling::*[2]/span[2]",
				postName);
		WebElement storyTitleElement = driver.findElement(By.xpath(xpathForStoryTitle));
		String author = storyTitleElement.getText();
		return author;
	}
}
