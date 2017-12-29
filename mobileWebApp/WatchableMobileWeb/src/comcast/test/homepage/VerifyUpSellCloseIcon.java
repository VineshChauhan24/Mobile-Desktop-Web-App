package comcast.test.homepage;

import static comcast.util.PropertyFileReader.ObjRepoProp;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import comcast.common.asserts.Assertions;
import comcast.config.BaseTest;
import comcast.custom.CustomFun;
import comcast.test.homepage.homePageFunctions.HomeFun;


/**
 * 
 * @author Karthik.Ashoka 
 * @description: This test script the functionality of close icon in up sell section
 * @Created on 25-April-2016 Last updated on 29-April-2016
 * 
 */

public class VerifyUpSellCloseIcon extends BaseTest {

	/**
	 * Open the browser, Enter the Watchable URL
	 */
	@Test(description = "Step 1: Open the browser, Enter the Watchable URL", priority = 1)
	public void Step01_NavigeteToURL() throws Exception {

		// Navigates to Watchable mobile web URL
		HomeFun.navigateToWatchable_URL();

		// Verify successfully navigate to home page
		Assertions.assertWatchableTitle();

		log.info("Successfully navigated to Watchable Mobile web Home page  \n");
		Reporter.log("<p>Successfully navigated to Watchable Mobile web Home page");

	}
	
	/**
	 *  Verify UpSell Banner displaying in home page
	*/
	@Test(description = "Step 2 : Verify Upsell banner is present in home page", priority = 2)
	public void Step02_UpsellBannerSection() throws Exception
	{
		
		//Verify UpSell Banner section displaying in Homepage
		Assertions.mobileWebUpSellSection();
		log.info("Upsell banner section is present in home page. ");
		Reporter.log("<p>Upsell banner section is present in home page. </p>");
					
	}
	
	
	/**
	 *  Verify close icon is present in up cell section
	*/
	@Test(description = "Step 3 : Verify close icon is present in up cell section.", priority = 3)
	public void Step03_VerifyPresenceOfCloseButton() throws Exception
	{
		//Verify the contents of UpSell banner section
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("homePageUpSellBarCloseButton_XPATH")),
				driver), "UpSell Banner Close icon not present");
		log.info("Close icon is present in up cell section");
		Reporter.log("<p>Close icon is present in up cell section </p>");
	}
	
	
	/**
	 * Verify after click on close icon from UpSell section whether it is closing or not
	 */
	@Test(description = "Step 4: This test script the functionality of close icon in up sell section", priority = 4)
	public void Step04_closingUpSell() throws Exception
	{
			 
		  //Verify able to close UpSell banner 
		  HomeFun.clickOnUpSellCloseButton();
		
		  // Verify UpSell banner is displaying even after close.
		  Assert.assertFalse(driver.findElement(By.xpath(ObjRepoProp.getProperty("homePageUpSellBarCloseButton_XPATH"))).isDisplayed(), 
					"UpSell close icon should not display");
		  
		  log.info("UpSell section is closed successfully");
		  
		  Reporter.log("<p>UpSell section is closed successfully</p>");
				
	}
	
	/**
	 * Verify after refreshing page UpSell banner displaying in home page
	 */
	@Test(description = "Step 5: This test script displaying upsell banner after refresing page", priority = 5)
	public void Step05_refreshBrowser() throws Exception
	{
		//verify page is refreshing 
		HomeFun.refreshHomePage();
		
		
		// Verify Up Sell banner is displaying after refreshing page
		  Assert.assertTrue(driver.findElement(By.xpath(ObjRepoProp.getProperty("homePageUpSellBar_XPATH"))).isDisplayed(), 
					"UpSell section is not displayed after refreshing  the page");
		  
		  log.info("UpSell section is displayed again after refreshing  the page");
		   Reporter.log("<p>UpSell section is displayed again after refreshing  the page</p>");
				
	}
}
