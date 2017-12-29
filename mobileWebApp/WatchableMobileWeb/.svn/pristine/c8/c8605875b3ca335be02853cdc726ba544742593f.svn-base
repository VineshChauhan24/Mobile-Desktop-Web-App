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
 * @author Manoj.Paragen
 * @description: This test script verifies the behaviour and functionality of
 *               next and previous playlist navigation buttons
 * @Created on 28-April-2016 Last updated on 28-April-2016
 * 
 */

public class VerifyPlaylistNavigationButtons extends BaseTest {

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
	 * Verify Next button is displaying in playlist section.
	 */
	@Test(description = "Step 2: Verify Next button is displaying in playlist section.", priority = 2)
	public void Step02_VerifyNextButton() throws Exception {

		// Verify Next button is displaying in playlist section.
		Assert.assertTrue(CustomFun.isElementPresent(
				By.xpath(ObjRepoProp.getProperty("nextPlaylistButton_XPATH")),
				driver),
				"Next button is not displaying on first page of playlist section. ");

		log.info("Next button is displaying on first page of playlist section \n");
		Reporter.log("<p>Next button is displaying on first page of playlist section");

	}
	
	
	/**
	 * Verify Next button is enabled on first page of playlist.
	 */
	@Test(description = "Step 3: Verify Next button is enabled on first page of playlist.", priority = 3)
	public void Step03_VerifyNextButtonEnabledOnFirstPage() throws Exception {

		// Verify Next button is enabled on first page of playlist.
		Assert.assertTrue(
				driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("nextPlaylistButton_XPATH")))
						.isEnabled(),
				"Next button is not enabled on first page of playlist.");

		log.info("Next button is enabled on first page of playlist.\n");
		Reporter.log("<p>Next button is enabled on first page of playlist.");

	}
	
	
	/**
	 * Verify Previous button is displaying in playlist section.
	 */
	@Test(description = "Step 4: Verify Previous button is displaying in playlist section.", priority = 4)
	public void Step04_VerifyPreviousButton() throws Exception {

		// Verify Previous button is displaying in playlist section.
		Assert.assertTrue(
				driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("previousPlaylistButton_XPATH")))
						.isEnabled(),
				"Previous button is not displaying on first page of playlist section");

		log.info("Previous button is displaying on first page of playlist section.\n");
		Reporter.log("<p>Previous button is displaying on first page of playlist section");

	}

	
	
	/**
	 * Verify previous button is enabled on first page of playlist.
	 */
	@Test(description = "Step 5: Verify previous button is disabled on first page of playlist.", priority = 5)
	public void Step05_VerifyPreviousButtonEnabledOnFirstPage() throws Exception {

		// Verify previous button is enabled on first page of playlist.
				
		Assert.assertTrue(
				driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("previousPlaylistButton_XPATH")))
						.isEnabled(),
				"Previous button is not enabled on first page of playlist.");

		log.info("Previous button is enabled on first page of playlist.\n");
		Reporter.log("<p>Previous button is enabled on first page of playlist.");

	}
	
	
	/**
	 * Navigate to last page and verify Next button is enabled in last page.
	 */
	@Test(description = "Step 6: Navigate to last page and verify Next button is enabled in last page.", priority = 6)
	public void Step06_VerifyNextButtonDisnabledOnLastPage() throws Exception {

		//Navigate to last page
		HomeFun.clickOnNextButton();
		
		Thread.sleep(5000);
		
		// Verify next button is disabled on last page of playlist.
		
		Assert.assertTrue(
				driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("nextPlaylistButton_XPATH")))
						.isEnabled(),
				"Next button is not enabled in last page");

		log.info("Next button is enabled in last page\n");
		Reporter.log("<p>Next button is enabled in last page");

	}
	
	
	
	/**
	 * Verify Previous button is enabled in last page.
	 */
	@Test(description = "Step 7:Verify Previous button is enabled in last page.", priority = 7)
	public void Step07_VerifyPreviousButtonDisabledOnFirstPage() throws Exception {

		// Verify Previous button is enabled in last page
		Assert.assertTrue(
				driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("previousPlaylistButton_XPATH")))
						.isEnabled(),
				"Previous button is not enabled in last page.");

		log.info("Previous button is enabled in last page.\n");
		Reporter.log("<p>Previous button is enabled in last page.");

	}
}
