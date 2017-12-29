package comcast.test.app.testCases.follow;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.playerVideoPage.playerPageFunctions.PlayerPageFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyFollowFromVideoControlWithoutLogin Description: This test
 * case verifies login popup is opened if user try to follow a channel from
 * video page by clicking on follow icon from video controls without login.
 * Author: Manoj
 * **/

public class VerifyFollowFromVideoControlWithoutLogin extends BaseTest {

	@Test
	public void testVerifyFollowFromVideoControlWithoutLogin() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyFollowFromVideoControlWithoutLogin");
			log.info("************************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method is to scroll UI to What we're watching Video from
			// featured shows
			// section.
			HomePageCommonFunctions.scrollToVideoSectionFromChannelSection();
			Thread.sleep(sleepTime);

			// Verify What we're watching VIDEOS row
			assertTrue(
					"Featured video row is not present in home page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.featuredVideosRow_XPATH)));
			log.info("Featured video row is present in home page");

			// Verify Video present in What we're watching VIDEOS row

			int videoCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredVideosIcon_XPATH)).size();
			if (videoCount > 0) {

				// Click on first video from What we're watching video section
				PlayerPageFun.clickOnFeaturedVideoTitle();

				// Verify User is navigated to video page
				AssertionRepoFunctions.assertVideoPageTitle();

				assertTrue(
						"Video player is not present in video page",
						CommonFun.isElementPresent(driver,
								By.id(XpathObjectRepo.videoPlayer_ID)));

				// Verify video player is displayed in page
				AssertionRepoFunctions.assertVideoPlayer();

				// Verify follow icon is present in video player controls
				// Mouse over video player
				PlayerPageFun.mouseOverVideoPlayer();
				// Verify Follow Button in player
				assertTrue(
						"Follow Button is not present in video player",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.playerFollowButton_XPATH)));
				log.info("Follow Button is present in video player");

				// Click on follow icon
				PlayerPageFun.clickOnFollowButton();

				// Verify login pop up is opened if user is not logged in to
				// application
				assertTrue(
						"Login pop up is not opened on clicking Follow icon if user is not logged in to application",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.loginForm_XPATH)));
				log.info("Login pop up is opened on clicking Follow icon if user is not logged in to application");

			} else {
				log.error("Featured video section does not contain videos");
				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
