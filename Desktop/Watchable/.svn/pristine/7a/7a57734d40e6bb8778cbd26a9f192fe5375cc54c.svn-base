package comcast.test.app.testCases.myWatchlist;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VeriyMyWatchlistTitle Description: This test case verifies My
 * Shows menu.  Author: Manoj
 * **/

public class VeriyMyWatchlistTitle extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVeriyMyWatchlistTitle() throws Exception {

		try {

			log.info("Script: VeriyMyWatchlistTitle");
			log.info("*****************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");
			
			Thread.sleep(sleepTime);

			// Verify My Shows menu is present
			assertTrue(
					"My Shows menu is not present",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.myWatchlistMenu_XPATH)));
			log.info("My Shows menu is present");

			// Verify My Watchlist menu Title
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.myWatchlistMenu_XPATH))
					.getText().matches(UILablesRepo.TOPMENU_MYWATCHLIST));
			log.info("The menu title '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.myWatchlistMenu_XPATH))
							.getText() + "' is present");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}

