package comcast.test.app.testCases.follow;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.channelPage.channelPageFunctions.ChannelPageFun;
import comcast.test.app.testCases.follow.followFunctions.FollowFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyFollowFromChannelPageWithoutLogin Description: This test
 * case verifies login popup is opened if user try to follow a channel from
 * channel page without login. Author: Manoj
 * **/

public class VerifyFollowFromChannelPageWithoutLogin extends BaseTest {

	@Test
	public void testVerifyFollowFromChannelPageWithoutLogin() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyFollowFromChannelPageWithoutLogin");
			log.info("***********************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method asserts Featured Channels title.
			AssertionRepoFunctions.assertFeaturedChannelsTitle();

			// This method is to scroll UI to Popular Channels Section.
			HomePageCommonFunctions.scrollToPopularChannelsSection();
			Thread.sleep(sleepTime);

			// Verify Channel present in FEATURED CHANNELS row

			int channelCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
					.size();
			if (channelCount > 0) {

				log.info(channelCount
						+ " Channels are present in featured channels section in home page");

				// Click on first channel from featured Shows section

				ChannelPageFun.clickOnFirstFeaturedChannelLink();

				// Verify successfully navigate to Shows details page
				AssertionRepoFunctions.assertChannelPageTitle();

				// Verify follow button is present in Shows page

				assertTrue(
						"Follow button is not present in Shows page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.followButton_XPATH)));
				log.info("Follow button is present in Shows page");

				// Click on follow button
				FollowFun.clickOnFollowButton();

				// Verify login pop up is opened if user is not logged in to
				// application
				assertTrue(
						"Login pop up is not opened on clicking Follow button if user is not logged in to application",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.loginForm_XPATH)));
				log.info("Login pop up is opened on clicking Follow button if user is not logged in to application");
				log.info("");

			} else {
				log.error("Shows are not present in featured Shows section in home page");
				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
