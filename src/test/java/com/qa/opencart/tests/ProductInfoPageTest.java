package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productinfoSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"MacBook","MacBook Pro",4},
			{"MacBook","MacBook Air",4},
			{"Apple","Apple Cinema 30\"",8},
		};
	}

	@Test(dataProvider="getProductData")
	public void productInfoHeaderTest(String searchProductName, String mainProductName,int productImgCount) {
		searchResultsPage = accountsPage.doSearch(searchProductName);
		productInfoPage = searchResultsPage.selectProduct(mainProductName);
		String actProductHeader = productInfoPage.getProductHeaderText();
		Assert.assertEquals(actProductHeader, mainProductName);
	}

	@Test(dataProvider="getProductData")
	public void productImagesTest(String searchProductName, String mainProductName,int productImgCount) {
		searchResultsPage = accountsPage.doSearch(searchProductName);
		productInfoPage = searchResultsPage.selectProduct(mainProductName);
		int actProductImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actProductImagesCount, productImgCount);
	}

	@Test()
	public void productInfoTest() {
		searchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));
		softAssert.assertEquals(actProductInfoMap.get("ProductName"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Price"), "$2,000.00");
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfoMap.get("Reward Points"), "800");
		softAssert.assertAll();
	}

}
