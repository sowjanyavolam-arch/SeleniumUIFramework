package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void regPageSetup() {
		registrationPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmail() {
		Random random = new Random();
		return "Newusertest"+random.nextInt(1000)+"@gmail.com";
	}
	
	@DataProvider
	public Object[][] getRegistrationData() {
		return new Object[][] {
			{"Lisa", "Oliver", "8478234567", "test@123", "yes"},
			{"Gaurav", "Mishra", "84345234567", "test@1234", "no"}
		};
	}
	
//	@DataProvider
//	public Object[][] getRegistrationData() {
//		Object regData[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
//		return regData;
//	}
	
	@Test(dataProvider = "getRegistrationData")
	public void userRegistrationTest(String firstName,String lastName,String telephone,String password,String subscribe) {
		Assert.assertTrue(registrationPage.accountRegistration(firstName,lastName,getRandomEmail(),telephone,password,subscribe));
	}

}
