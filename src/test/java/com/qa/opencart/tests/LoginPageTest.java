package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

public class LoginPageTest extends BaseTest{
	
	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println(actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE,Errors.LOGIN_PAGE_TITLE_MISMATCH);
	}
	
	@Test
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();
		System.out.println(actUrl);
		Assert.assertTrue(actUrl.contains(Constants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accountsPage.isAccountsPageHeaderExist());
	}
	
	@Test
	public void isRegisterLinkTest() {
		Assert.assertTrue(loginPage.isRegisterLinkLinkExist());
	}
	
}
