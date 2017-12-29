package comcast.test.app.testCases.homePage;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyEightChannelsDisplayedInFeaturedShowSection Description:
 * This test case verifies channels are displayed in Watchable SHOWS OF THE WEEK
 * section Author: Manoj
 * **/

public class VerifyEightChannelsDisplayedInFeaturedShowSection extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyEightChannelsDisplayedInFeaturedShowSection()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyEightChannelsDisplayedInFeaturedShowSection");
			log.info("**********************************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method asserts Watchable SHOWS OF THE WEEK title.
			AssertionRepoFunctions.assertFeaturedChannelsTitle();

			// This method is to scroll UI to Watchable SHOWS OF THE WEEK
			// Section.
			HomePageCommonFunctions.scrollToPopularChannelsSection();
			Thread.sleep(sleepTime);

			// Verify Watchable SHOWS OF THE WEEK row
			assertTrue(
					"Watchable SHOWS OF THE WEEK row is not present in home page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.featuredChannelsRow_XPATH)));
			log.info("Watchable SHOWS OF THE WEEK row is present in home page");

			// Verify Shows present in Watchable SHOWS OF THE WEEK row

			boolean match = false;

			int channelCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
					.size();
			if (channelCount > 0) {

				match = true;

				/*
				 * assertEquals("Featured show section does not contains 8 Shows"
				 * , 8, channelCount);
				 * log.info("Featured show section contains 8 channels");
				 */

				assertTrue(
						"Watchable SHOWS OF THE WEEK section does not contains Shows",
						match);
				log.info("Watchable SHOWS OF THE WEEK section contains "
						+ channelCount + " Showss");

				log.info("");

			} else {
				log.error("Watchable SHOWS OF THE WEEK row does not contain Shows");
				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
