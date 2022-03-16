package com.qa.ebay.EbayAutomation;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.qa.ebay.helper.BaseDriver;
import com.qa.ebay.pageobjectmodel.EbayLandingPage;

public class EbayPageTest extends BaseDriver {

	@Test(groups = { "real" })
	//@Parameters({ "site" })
	public void testVerifyPageTitleUsingTestngParameter() throws IOException {
		//System.out.println("testng value----------" + "de");
		String pageName="homepage";
		WebDriver driver = getDriver("de",pageName);
		String expectedTitle = prop.getProperty("pagetitle");
		String actualTitle = new EbayLandingPage(driver).getPageTitle();
		System.out.println(actualTitle);
		AssertJUnit.assertEquals(actualTitle, expectedTitle);
	}


}
