package comcast.test.app.testCases.homePage;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyHeaderlinksWithoutLogin Description: This test case
 * verifies all header item without login Author: Manoj
 * **/

// Manoj Added

public class VerifyHeaderlinksWithoutLogin extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyHeaderlinksWithoutLogin() throws Exception {

		try {

			log.info("Script: VerifyHeaderlinksWithoutLogin");
			log.info("*************************************");
			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Verifying Watchable Logo is present in header
			Thread.sleep(LessSleepTime);
			assertTrue(
					"Watchable Logo is not present in header",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.watchableTopLogo_XPATH)));
			log.info("Watchable Logo is present in header");

			// Verifying Playlists menu is present in header
			Thread.sleep(LessSleepTime);
			assertTrue(
					"Playlists menu is not present in header",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.playLists_XPATH)));
			log.info("Playlists menu is present in header");
			
			// Verifying  Browse Shows menu is present in header
			Thread.sleep(LessSleepTime);
			assertTrue(
					" Browse Shows menu is not present in header",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.allChannelsMenu_XPATH)));
			log.info(" Browse Shows menu is present in header");

			// Verifying My Shows menu is present in header
			Thread.sleep(LessSleepTime);
			assertTrue(
					"My Shows menu is not present in header",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.myWatchlistMenu_XPATH)));
			log.info("My Shows menu is present in header");

			// Verifying Search icon(button) is present in header
			Thread.sleep(LessSleepTime);
			assertTrue(
					"Search icon(button) is not present in header",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.searchButton_XPATH)));
			log.info("Search icon(button) is present in header");
			
			// Click on Search icon to make search text box visible
			HomeFun.clickOnSearchButton();

			// Verifying Search text box is present in header
			Thread.sleep(LessSleepTime);
			assertTrue(
					"Search text box is not present in header",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.searchText_XPATH)));
			log.info("Search text box is present in header");

			// Verifying Log In menu is present in header
			Thread.sleep(LessSleepTime);
			assertTrue(
					"Log In menu is not present in header",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.loginMenu_XPATH)));
			log.info("Log In menu is present in header");

			// Verifying Sign Up menu is present in header
			Thread.sleep(LessSleepTime);
			assertTrue(
					"Sign Up menu is not present in header",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.signUpMenu_XPATH)));
			log.info("Sign Up menu is present in header");
			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
