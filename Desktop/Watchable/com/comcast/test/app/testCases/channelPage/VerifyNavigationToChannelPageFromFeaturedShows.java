package comcast.test.app.testCases.channelPage;

import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.channelPage.channelPageFunctions.ChannelPageFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyNavigationToChannelPageFromFeaturedShows Description: This
 * test cases verifies clicking on any shows from What We're WATCHING section,
 * user is navigating to shows detail page. Author: Manoj
 * **/

public class VerifyNavigationToChannelPageFromFeaturedShows extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyNavigationToChannelPageFromFeaturedVideos()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyNavigationToChannelPageFromFeaturedVideos");
			log.info("******************************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method is to scroll UI to What We're WATCHING Section.
			HomePageCommonFunctions.scrollToSection();

			Thread.sleep(sleepTime);

			// This method asserts What We're WATCHING title.
			AssertionRepoFunctions.assertFeaturedTitle();

			// Verify Videos present inWatchable SWhat We're WATCHINGK row

			int videoCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredVideosIcon_XPATH)).size();
			if (videoCount > 0) {

				log.info(videoCount
						+ " Videos are present in What We're WATCHING section in home page");

				// Click on first channel from featured video section

				ChannelPageFun.clickOnFirstChannelLinkFromFeaturedVideos();

				// Verify successfully navigate to channel details page
				AssertionRepoFunctions.assertChannelPageTitle();
				log.info("");

			} else {
				log.error("Videos are not present in What We're WATCHING section in home page");
				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
