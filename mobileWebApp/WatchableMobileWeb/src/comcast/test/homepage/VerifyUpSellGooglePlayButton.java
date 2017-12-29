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
 * @description: This test script to Verify the Google Play button in Upsell banner section is clickable 
 * watchable mobile app home page
 * @Created on 27-April-2016 Last updated on 6-June-2016
 * 
 */

public class VerifyUpSellGooglePlayButton extends BaseTest {

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
		
		//Verify Upsell Banner section displaying in homepage
		Assertions.mobileWebUpSellSection();
		log.info("Upsell banner section is present in home page. ");
		Reporter.log("<p>Upsell banner section is present in home page. </p>");
					
	}
	
	
	/**
	 *  Verify Google play button is present in UpSell section
	*/
	@Test(description = "Step 3 : Verify the presence of google play button.", priority = 3)
	public void Step03_VerifyPresenceOfGooglePlayButton() throws Exception
	{
		
		//verify UpSell Banner Google Play button is displaying or not
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("homePageUpSellBarGooglePlayButton_XPATH")),
		driver), "UpSell Banner Google Play button not present");
		log.info("Google play button is present in UpSell section");
		Reporter.log("<p>Google play button is present in UpSell section</p>");
	}
	
	/**
	 * Verify Google play button is click-able
	 */
	@Test(description = "Step 4 : Verify Google play button is clickable.", priority = 4)
	public void Step04_googlePlayButtonClickable() throws Exception
	{
		Assert.assertTrue(driver.findElement(By.xpath(ObjRepoProp.getProperty("homePageUpSellBarGooglePlayButton_XPATH")))
			      .isEnabled(),"Google Play button is not able to click");
		log.info("Google play button is clickable in UpSell section");
		Reporter.log("<p>Google play button is clickable in UpSell section</p>");
	}
}
