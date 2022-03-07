package com.qa.ebay.EbayAutomation;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Unit test for simple App.
 */
public class EbayTest 
{
    /**
     * Rigorous Test :-)
     */
	WebDriver driver=null;
    @BeforeMethod
    public void setUp()
    {
    	String driverpath = System.getProperty("user.dir") + "/chromedriver.exe";
    	System.out.println(driverpath);
		System.setProperty("webdriver.chrome.driver", driverpath);
       
    }
    
    @Test
    public void verifyEbayCategory() {
    	driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ebay.com/");
		driver.findElement(By.id("gh-shop-a")).click();
		List<WebElement> myList = driver.findElements(By.cssSelector("#gh-sbc h3 a"));
		List<String> myProductList=myList.stream().map(s->(s.getText())).collect(Collectors.toList());
	
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(myProductList.contains("Motors"));
		softAssert.assertTrue(myProductList.contains("Clothing & Accessories"));
		softAssert.assertTrue(myProductList.contains("Sporting goods"));
		softAssert.assertTrue(myProductList.contains("Electronics"));
		softAssert.assertAll();
    }
    
    
    @Test
    public void verifyEbaySearch() {
    	driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ebay.com/");
		driver.findElement(By.id("gh-ac")).sendKeys("iPhone");
		driver.findElement(By.id("gh-btn")).click();
	    String productName =
	    		driver
	    		.findElement(By.xpath("//div[@id='srp-river-results']/ul/child::li[4]/div/div[2]/a"))
	    		.getText();
	    System.out.println(productName);
	    Assert.assertTrue(productName.contains("iPhone"));
    }
    
    @Test
    public void verifySubCategoryUnderMainCategory() {
    	driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ebay.com/");
		
		driver.findElement(By.id("gh-shop-a")).click();
		WebElement rootTag = driver.findElement(By.xpath("//table[@id='gh-sbc'] //td[1] "));
		String categoryName = rootTag.findElement(By.xpath("//h3/a")).getText();
		System.out.println(categoryName);
		int size = rootTag.findElements(By.xpath("//ul[1]/li")).size();
		System.out.println(size);
    }
    
    
    @Test
    public void verifyAdvertisement() {
    	driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ebay.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		List<WebElement> myList = driver.findElements(By.cssSelector(".hl-full-bleed-banner__wrapper div.ebayui-ellipsis-3 span"));
		System.out.println(myList.size());
		myList.stream().forEach(s->System.out.println(s.getText()));
		Assert.assertTrue(myList.size()>0);
    }
    
    @AfterMethod
    public void tearDown() {
    	if(driver!=null) {
    	driver.quit();}
    }
}
