package comcast.test.app.testCases.playerVideoPage;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.playerVideoPage.playerPageFunctions.PlayerPageFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyVideoContentsWhenPlayPaused Description: This test case
 * verifies the Contents of video when play is paused. Author: Manoj
 * **/

public class VerifyVideoContentsWhenPlayPaused extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyVideoContentsWhenPlayPaused() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyVideoContentsWhenPlayPaused");
			log.info("***********************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method is to scroll UI to What we're watching section.
			HomePageCommonFunctions.scrollToVideoSectionFromChannelSection();

			// This method asserts What we're watching title.
			AssertionRepoFunctions.assertFeaturedTitle();

			// Verify What we're watching VIDEOS row
			assertTrue(
					"What we're watching video row is not present in home page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.featuredVideosRow_XPATH)));
			log.info("What we're watching video row is present in home page");

			// Verify Video present in What we're watching VIDEOS row

			int videoCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredVideosIcon_XPATH)).size();
			if (videoCount > 0) {

				// Click on first video from What we're watching video section
				PlayerPageFun.clickOnFeaturedVideoTitle();

				// Verify User is navigated to video page
				AssertionRepoFunctions.assertVideoPageTitle();

				// pause play
				PlayerPageFun.clickonVideoPlayer();

				// Verify video title in video

				assertTrue(
						"Selected video title is not displaying in video when play is paused",
						CommonFun.isElementPresent(driver, By
								.xpath(XpathObjectRepo.playingVideoTitle_XPATH)));

				log.info("The video title displayed is '"
						+ driver.findElement(
								By.xpath(XpathObjectRepo.playingVideoTitle_XPATH))
								.getText() + "'");

				// Verify show title in video

				assertTrue(
						"Selected video's show title is not displaying in video when play is paused",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.playingVideoChannelTitle_XPATH)));

				log.info("The show title displayed is '"
						+ driver.findElement(
								By.xpath(XpathObjectRepo.playingVideoChannelTitle_XPATH))
								.getText() + "'");

				// Verify show description in video

				assertTrue(
						"Selected video's show description is not displaying in video when play is paused",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.playingVideoChannelDesc_XPATH)));

				log.info("The show description displayed is '"
						+ driver.findElement(
								By.xpath(XpathObjectRepo.playingVideoChannelDesc_XPATH))
								.getText() + "'");

				// Verify show logo in video

				assertTrue(
						"Selected video's show logo is not displaying in video when play is paused",
						CommonFun.isElementPresent(driver, By
								.xpath(XpathObjectRepo.videoPageShowLogo_XPATH)));

				log.info("Selected video's show logo is displaying in video when play is paused");

				// Verify show follow button in video

				assertTrue(
						"follow button is not displaying in video when play is paused",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.followVideoPageButton_XPATH)));

				log.info("follow button is displaying in video when play is paused");

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
