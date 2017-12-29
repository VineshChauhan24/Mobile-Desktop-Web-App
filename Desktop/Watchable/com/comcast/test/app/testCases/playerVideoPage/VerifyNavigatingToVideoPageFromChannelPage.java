package comcast.test.app.testCases.playerVideoPage;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.channelPage.channelPageFunctions.ChannelPageFun;
import comcast.test.app.testCases.playerVideoPage.playerPageFunctions.PlayerPageFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyNavigatingToVideoPageFromChannelPage Description: This test
 * script verifies navigating to video page by selecting any video from videos
 * section of show detail page. Author: Manoj
 * **/

public class VerifyNavigatingToVideoPageFromChannelPage extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyNavigatingToVideoPageFromChannelPage()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyNavigatingToVideoPageFromChannelPage");
			log.info("**************************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method assertsWatchable shows of the week title.
			AssertionRepoFunctions.assertFeaturedChannelsTitle();

			// This method is to scroll UI to Watchable shows of the week
			// Section.
			HomePageCommonFunctions.scrollToPopularChannelsSection();
			Thread.sleep(sleepTime);

			// Verify Channel present in Watchable shows of the week row

			int channelCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
					.size();
			if (channelCount > 0) {

				// Click on first show name from Watchable shows of the week
				// section

				ChannelPageFun.clickOnFirstFeaturedChannelLink();

				// Verify successfully navigate to show details page
				AssertionRepoFunctions.assertChannelPageTitle();

				int videoCount = driver.findElements(
						By.xpath(XpathObjectRepo.featuredVideosIcon_XPATH))
						.size();
				if (videoCount > 0) {

					// Click on first video from video section
					PlayerPageFun.clickOnFeaturedVideoTitle();

					// Verify User is navigated to video page
					AssertionRepoFunctions.assertVideoPageTitle();

					// Mouse over video player
					CommonFun.mouseOverElement(driver, driver.findElement(By
							.id(XpathObjectRepo.videoPlayer_ID)));
					Thread.sleep(LessSleepTime);

					// Verify video player is displayed in video page
					AssertionRepoFunctions.assertVideoPlayer();

					log.info("");
				} else {
					log.error("Chaneel page video section does not contain videos");
					log.info("");
				}

			} else {
				log.error("Featured channel row does not contain channels");
				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
