package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By search = By.name("search");
	private By header = By.cssSelector("div#logo a");
	private By accountsList = By.cssSelector("div#content h2");
	private By searchBtn = By.cssSelector("div#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccountsPageTitle() {
		return eleUtil.waitForTitleIs(Constants.DEFAULT_TIMEOUT, Constants.ACCOUNTS_PAGE_TITLE);
	}

	public boolean isAccountsPageHeaderExist() {
		return eleUtil.doIsDisplayed(header);
	}

	public boolean isSearchExist() {
		return eleUtil.doIsDisplayed(search);
	}

	public SearchResultsPage doSearch(String productName) {
		if (isSearchExist()) {
			eleUtil.doSendKeys(search, productName);
			eleUtil.doClick(searchBtn);
			return new SearchResultsPage(driver);
		}
		return null;
	}

	public List<String> getAccountsPageSectionsList() {
		List<WebElement> sectionList = eleUtil.getElements(accountsList);
		List<String> accSecValList = new ArrayList<String>();

		for (WebElement e : sectionList) {
			String text = e.getText();
			accSecValList.add(text);
		}
		return accSecValList;
	}

}
