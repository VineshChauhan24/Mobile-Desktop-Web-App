package comcast.test.homepage;

import org.testng.Reporter;
import org.testng.annotations.Test;
import comcast.common.asserts.Assertions;
import comcast.config.BaseTest;
import comcast.test.homepage.homePageFunctions.HomeFun;

/**
 * 
 * @author Karthik.Ashoka 
 * @description: This test script verify The contents of UpSellBannerSection 
 * watchable mobile app home page
 * @Created on 21-April-2016 Last updated on 6-June-2016
 * 
 */

public class VerifyUpsellBannerSectionContents extends BaseTest {

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
	 *  Verify UpSell Banner displaying in home page and verify the contents of UpSell Banner
	*/
	@Test(description = "Step 3 : Verify the contents of Upsell banner section.", priority = 3)
	public void Step03_UpsellBannerSectionContents() throws Exception
	{
		
		//Verify the contents of Upsell banner section
		Assertions.mobileWebUpSellContent();
		
		log.info("UpSell Banner section contents is present in home page");
		Reporter.log("<p>UpSell Banner section contents is present in home page </p>");
	}

}
