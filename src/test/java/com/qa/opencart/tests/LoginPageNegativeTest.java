package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Errors;

public class LoginPageNegativeTest extends BaseTest{
	
	@DataProvider
	public Object[][] getLoginNegativeData() {
		return new Object[][] {
			{"sv@gmail.com","@$%T"},
			{"  ","test@123"},
			{"sv@gmail.@.com","test@123"},
			{"   ","  "},
		};
	}
	
	@Test(dataProvider = "getLoginNegativeData")
	public void invalidLoginTest(String username, String pwd) {
		Assert.assertTrue(loginPage.doInvalidLogin(username, pwd),Errors.LOGIN_PAGE_ERROR_MSG);
	}
}
