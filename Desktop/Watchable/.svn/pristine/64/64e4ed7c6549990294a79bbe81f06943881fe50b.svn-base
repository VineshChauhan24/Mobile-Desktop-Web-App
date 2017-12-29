package comcast.test.app.testCases.providerPage;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.channelPage.channelPageFunctions.ChannelPageFun;
import comcast.test.app.testCases.providerPage.providerPageFunctions.providerPageFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyNavigationToProviderPageFromFeaturedShows Description: This
 * test cases verifies clicking on any channel from What We're WATCHING section,
 * user is navigating to channel detail page Then to Provider Page. Author:
 * Manoj
 * **/

public class VerifyNavigationToProviderPageFromFeaturedShows extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyNavigationToProviderPageFromFeaturedShows()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyNavigationToProviderPageFromFeaturedShows");
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
			AssertionRepoFunctions.assertFeaturedChannelsTitle();

			/*
			 * // This method is to scroll UI to Popular Channels Section.
			 * HomePageCommonFunctions.scrollToPopularChannelsSection(); // This
			 * method is to scroll UI to Featured Video from featured shows //
			 * section.
			 * HomePageCommonFunctions.scrollToVideoSectionFromChannelSection();
			 * Thread.sleep(sleepTime);
			 */

			// Verify videos present inWhat We're WATCHING row

			int videoCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredVideosIcon_XPATH)).size();
			if (videoCount > 0) {

				log.info(videoCount
						+ " Videos are present in What We're WATCHING section in home page");

				// Click on first show fromWhat We're WATCHING section

				ChannelPageFun.clickOnFirstChannelLinkFromFeaturedVideos();

				// Verify successfully navigate to channel details page
				AssertionRepoFunctions.assertChannelPageTitle();

				// Click on Provider title from Channel detail page
				providerPageFun.clickOnProviderLinkFromChannelPage();

				// Verify successfully navigate to Provider details page
				assertionFunction.assertProviderPageTitle();

				log.info("");

			} else {
				log.error("Shows are not present in Watchable SHOWS OF THE WEEK section in home page");
				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
