package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.Errors;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// private By Locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//*[@value='Login' and @type='submit']");
	private By forgotPwd = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By loginErrorMessage = By.cssSelector("div.alert.alert-danger.alert-dismissible");

	// public page class constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// public page actions
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIs(Constants.DEFAULT_TIMEOUT, Constants.LOGIN_PAGE_TITLE);
	}

	public String getLoginPageUrl() {
		return eleUtil.waitForUrl(Constants.DEFAULT_TIMEOUT, Constants.LOGIN_PAGE_FRACTION_URL);
	}

	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwd);
	}

	public AccountsPage doLogin(String username, String pwd) {
		eleUtil.waitForElementToBeVisible(emailId, Constants.DEFAULT_TIMEOUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public boolean doInvalidLogin(String username, String pwd) {
		eleUtil.waitForElementToBeVisible(emailId, Constants.DEFAULT_TIMEOUT);
		eleUtil.doSendKeys(emailId, username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		String actErrorMsg = eleUtil.waitForElementPresent(loginErrorMessage,5000).getText();
		if(actErrorMsg.contains(Errors.LOGIN_PAGE_ERROR_MSG)) {
			return true;
		}
		return false;
	}
	
	public boolean isRegisterLinkLinkExist() {
		return eleUtil.waitForElementToBeVisible(registerLink,Constants.DEFAULT_TIMEOUT).isDisplayed();
	}
	
	public RegistrationPage navigateToRegisterPage() {
		if(isRegisterLinkLinkExist()) {
			eleUtil.doClick(registerLink);
			return new RegistrationPage(driver);
		}
		return null;
	}

}
