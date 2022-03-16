package com.qa.ebay.EbayAutomation;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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
		List<Object> myProductList=myList.stream().map(s->(s.getText())).collect(Collectors.toList());

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
    public void getSubCategory() {
    	driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ebay.com/");

		driver.findElement(By.id("gh-shop-a")).click();

        List<WebElement> tdElements = driver.findElements(By.cssSelector("#gh-sbc td"));
      outer:
        for (int i = 1; i <= tdElements.size(); i++) {
         String xpathEx="//td["+i+"]";
         List<WebElement> h3Elements = tdElements.get(i).findElements(By.cssSelector("h3"));

            System.out.println("h3 count"+h3Elements.size());
            for(int j = 1; j <= h3Elements.size(); j++) {
            	xpathEx=xpathEx+"/h3["+j+"]";
            	WebElement h3Element = h3Elements.get(j);
            	List<WebElement> aElements =h3Element.findElements(By.cssSelector("a"));
            	//System.out.println(aElements.size());
            	//xpathEx=xpathEx+"/a/parent::h3/following-sibling::ul[1]/li";
            	System.out.println(xpathEx+"/a/parent::h3/following-sibling::ul[1]/li");
            	List<WebElement> subElements
            	=driver.findElements(By.xpath(xpathEx+"/a/parent::h3/following-sibling::ul[1]/li"));
            	System.out.println("********"+subElements.size());
            	//td[1]/h3[3]/a/parent::h3/following-sibling::ul[1]/li

                }

        }
       // return map;
    }

    @Test
    public void HomePageScroll() {
    	driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ebay.com/");
	    JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        int size=driver
        .findElements(By.xpath("//*[@id=\"s0-0-32-4-0-0[2]-4-match-media-0-ebay-carousel-list\"]/li"))
        .size();
      Assert.assertTrue(size>=0);
    }

    @Test
    public void DailyDealsLink() {
    	driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ebay.com/");
		driver.findElement(By.cssSelector("#gh-p-1 a")).click();
		boolean flag=driver.findElement(By.cssSelector(".ebayui-dne-item-featured-card")).isDisplayed();
        Assert.assertTrue(flag);

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
