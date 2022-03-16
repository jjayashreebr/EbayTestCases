package com.qa.ebay.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.ebay.locators.EbayLandingPageLocators;

public class EbayLandingPage extends EbayLandingPageLocators {
	public WebDriver driver;

	public EbayLandingPage(WebDriver driver) {
		System.out.println(" inside EbayLandingPage");
		this.driver = driver;
	}
	
	public WebElement searchBox() {
		return driver.findElement(SEARCH_BOX);
	}
	

	public WebElement searchButton() {
		return driver.findElement(SEARCH_BUTTON);
	}

	public String getPageTitle() {
		// TODO Auto-generated method stub
		return driver.getTitle();
	}
	
	
}
