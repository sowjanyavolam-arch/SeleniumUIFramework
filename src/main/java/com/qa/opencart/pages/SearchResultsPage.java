package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private ElementUtil eleUtil;
	private WebDriver driver;
	private By searchHeader = By.cssSelector("div#content h1");
	private By products = By.cssSelector("div.caption a");

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getResultsPageHeaderValue() {
		if (eleUtil.doIsDisplayed(searchHeader)) {
			return eleUtil.doElementGetText(searchHeader);
		}
		return null;
	}
	
	public List<String> getProductResultsList() {
		List<WebElement> productList = eleUtil.waitForElementsToBeVisible(products,Constants.DEFAULT_TIMEOUT);
		List<String> productValList = new ArrayList<String>();
		for(WebElement e:productList) {
			String text = e.getText();
			productValList.add(text);
		}
		return productValList;
	}
	
	public int getproductSearchCount() {
		return eleUtil.waitForElementsToBeVisible(products,Constants.DEFAULT_TIMEOUT).size();
	}
	
	public ProductInfoPage selectProduct(String mainProductName) {
		List<WebElement> productList = eleUtil.waitForElementsToBeVisible(products,Constants.DEFAULT_TIMEOUT);
		for(WebElement e:productList) {
			String text = e.getText();
			if(text.equals(mainProductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}

}
