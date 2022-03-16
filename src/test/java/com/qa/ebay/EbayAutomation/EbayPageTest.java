package com.qa.ebay.EbayAutomation;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.ebay.helper.BaseDriver;
import com.qa.ebay.pageobjectmodel.EbayLandingPage;

public class EbayPageTest extends BaseDriver {

	@Test(groups = { "localization" })
	@Parameters({ "site" })
	public void testVerifyPageTitleUsingTestngParameter(String site) throws IOException {
		System.out.println("testng value----------" + site);
		String pageName="homepage";
		WebDriver driver = getDriver(site,pageName);
		String expectedTitle = prop.getProperty("pagetitle");
		String actualTitle = new EbayLandingPage(driver).getPageTitle();
		System.out.println(actualTitle);
		AssertJUnit.assertEquals(actualTitle, expectedTitle);
	}


}
