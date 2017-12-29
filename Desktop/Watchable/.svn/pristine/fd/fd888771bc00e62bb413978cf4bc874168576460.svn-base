package comcast.test.app.testCases.homePage;

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
 * Class Name: VerifyFeaturedShowSectionContainsMoreShowsButton Description:
 * This test case verifies More shows button is present at the end of Watchable
 * SHOWS OF THE WEEK section. 
 * Author: Manoj
 * **/

public class VerifyFeaturedShowSectionContainsMoreShowsButton extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyFeaturedShowSectionContainsMoreShowsButton()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyFeaturedShowSectionContainsMoreShowsButton");
			log.info("********************************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method is to scroll UI to Watchable SHOWS OF THE WEEK
			// Section.
			HomePageCommonFunctions.scrollToPopularChannelsSection();
			Thread.sleep(sleepTime);

			// This method asserts Watchable SHOWS OF THE WEEK section title.
			AssertionRepoFunctions.assertFeaturedChannelsTitle();

			// Verify Watchable SHOWS OF THE WEEK row
			assertTrue(
					"Watchable SHOWS OF THE WEEK row is not present in home page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.featuredChannelsRow_XPATH)));
			log.info("Watchable SHOWS OF THE WEEK row is present in home page");

			// Verify shows present in Watchable SHOWS OF THE WEEK row

			int channelCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
					.size();

			channelCount = channelCount - 1; // More shows button placed in
												// place of 8th show icon
			if (channelCount > 0) {

				log.info("Watchable SHOWS OF THE WEEK row contains channels");

				// Verify More shows button
				assertTrue(
						"More shows button is not present at the end of Watchable SHOWS OF THE WEEK section.",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.featuredShowsMoreShowsButton_XPATH)));
				log.info("More shows button is present at the end of Watchable SHOWS OF THE WEEK section.");
				log.info("");

			} else {
				log.error("Watchable SHOWS OF THE WEEK row does not contain shows");
				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
