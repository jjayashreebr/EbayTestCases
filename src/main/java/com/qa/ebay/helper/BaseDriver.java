package com.qa.ebay.helper;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;


public class BaseDriver {
	public static ThreadLocal<WebDriver> webdrivers = new ThreadLocal<>();
	public static Properties prop;
	public static String url;
	static {
		String driverpath = System.getProperty("user.dir") + "/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverpath);
	}



	public static WebDriver getDriver(String site, String pageName) throws IOException {

		WebDriver driver = null;
		prop = new Properties();
		prop.load(ContentReader.getPropertyFile(site,pageName));

		switch (site) {
		case "us":
			url = "https://www.ebay.com";
			break;
		case "uk":
			url = "https://www.ebay.co.uk";
			break;

		case "au":
			url = "https://www.ebay.com.au";
			break;
		case "de":
			url = "https://www.ebay.de";
			break;

		default:
			url = "https://www.ebay.com";

		}

		String browser = System.getProperty("testbrowser");
		System.out.println("Pom value-----------------" + browser);

		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
			driver.get(url);
		}
		webdrivers.set(driver);
		return webdrivers.get();

	}

	@AfterMethod
	public void tearDown() {
		if (webdrivers.get() != null) {
			webdrivers.get().close();
		}
	}
}
