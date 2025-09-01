package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accountsPageTitleTest() {
		String actAccountsPageTitle = accountsPage.getAccountsPageTitle();
		Assert.assertEquals(actAccountsPageTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	public void accPageHeaderTest() {
		Assert.assertTrue(accountsPage.isAccountsPageHeaderExist());
	}

	@Test
	public void isSearchExist() {
		Assert.assertTrue(accountsPage.isSearchExist());
	}

	@Test
	public void accSectionsTest() {
		List<String> actSecList = accountsPage.getAccountsPageSectionsList();
		Assert.assertEquals(actSecList, Constants.ACCOUNTS_SECTIONS_LIST);
	}

	@Test
	public void searchHeaderTest() {
		searchResultsPage = accountsPage.doSearch("Macbook");
		String actSearchHeader = searchResultsPage.getResultsPageHeaderValue();
		Assert.assertTrue(actSearchHeader.contains("Macbook"));
	}

	@Test
	public void searchProductCountTest() {
		searchResultsPage = accountsPage.doSearch("Macbook");
		int actProductCount = searchResultsPage.getproductSearchCount();
		Assert.assertEquals(actProductCount, Constants.MACBOOK_PRODUCT_COUNT);
	}
	
	@Test
	public void getSearchProductListTest() {
		searchResultsPage = accountsPage.doSearch("Macbook");
		List<String> actProductList = searchResultsPage.getProductResultsList();
		System.out.println(actProductList);
	}

}
