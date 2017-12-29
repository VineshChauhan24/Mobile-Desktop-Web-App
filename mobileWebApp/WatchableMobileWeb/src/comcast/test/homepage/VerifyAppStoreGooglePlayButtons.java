package comcast.test.homepage;
import static comcast.util.PropertyFileReader.ObjRepoProp;

import java.util.Set;

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
 * @author karthik.ashoka
 * @description: This test script verifies the functionality of App Store and Google Play buttons at the bottom of the home page
 * @Created on 17-may-2016 Last updated on 18-may-2016
 * 
 */

	public class VerifyAppStoreGooglePlayButtons extends BaseTest {

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
	 * Verify App Store button is present at the bottom of home page.
	 */
	@Test(description = "Step 2: Verify App Store button is present at the bottom of home page.", priority = 2)
	public void Step02_VerifyPresenceAppStoreButton() throws Exception {

		// Verify App Store button is present at the bottom of home page
		HomeFun.scrollToFooterSection();
		Assertions.AppStoreButton();
		log.info("App Store button is present at bottom of show row.");
		Reporter.log("<p>App Store button is present at bottom of show row.</p> ");

	}
	
	/**
	 * Verify Click on App Store button and verify it is navigate to App Store Page.
	 */
	@Test(description = "Step 3: Verify Click on App Store button.", priority = 3)
	public void Step03_VerifyAppStoreButtonClickable() throws Exception {

		
		//Click on App Store Button and navigating to App Store Page
		HomeFun.clickOnUpAppStoreButton();
		
		//Assert.assertTrue(CustomFun.switchToNewWindow(driver, driver.getTitle()));
		
		String parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();

		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);

				Thread.sleep(5000);
				
				Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("appstorepage_XPATH")),
		driver), "watchable logo present in app store");
				log.info("watchable logo present in app store");

				driver.close(); // closing child window
				driver.switchTo().window(parentWindow); // Control to parent window
														

			}

		}
		log.info("User successfully navigate to Apple  Store.");
		Reporter.log("<p>User successfully navigate to Apple  Store.</p> ");

	}
		
	/**
	 * Verify App Store button is present at the bottom of home page.
	 */
	@Test(description = "Step 4: Verify Google Play  button is present at the bottom of home page.", priority = 4)
	public void Step04_VerifyPresenceGooglePlayButton() throws Exception {
		
		HomeFun.scrollToFooterSection();
		Assertions.GooglePlayButton();
		log.info("Google Play button is present at bottom of show row.");
		Reporter.log("<p>Google Play button is present at bottom of show row.</p> ");

	}
	
	/**
	 * Verify Click on Google Play button and verify it is navigate to Google Play.
	 */
	@Test(description = "Step 5: Verify Click on Google Play button.", priority = 5)
	public void Step05_VerifyPresenceGooglePlayButton() throws Exception {

		
		//Click on App Store Button and navigating to App Store Page
		HomeFun.clickOnGooglePlayButton();
		
		String parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();

		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);

				Thread.sleep(5000);
				
				Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("googleplaypage_XPATH")),
		driver), "watchable logo present in Google Play Store");
				log.info("watchable logo present in Google Play Store");

				driver.close(); // closing child window
				driver.switchTo().window(parentWindow); // Control to parent window
														

			}

		}
		log.info("User successfully navigate to Google Play  Store.");
		Reporter.log("<p>User successfully navigate to Google Play  Store.</p> ");

	}

	
}
