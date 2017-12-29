package comcast.test.app.testCases.socialSharing;

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
 * Class Name: VerifyPresenceOfSocialSharingFunctionalityInVideoControls
 * Description: This test case verifies The presnce of social sharing
 * functionality in in video page. Author: Manoj
 * **/

public class VerifyPresenceOfSocialSharingFunctionalityInVideoControls extends
		BaseTest {
	@Test
	public void testVerifyPresenceOfSocialSharingFunctionalityInVideoControls()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyPresenceOfSocialSharingFunctionalityInVideoControls");
			log.info("*****************************************************************");

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

				// Click on first video from featured video section
				PlayerPageFun.clickOnFeaturedVideoTitle();

				// Verify User is navigated to video page
				AssertionRepoFunctions.assertVideoPageTitle();

				// Mouse over video player
				PlayerPageFun.mouseOverVideoPlayer();

				// Verify video player is displayed in page
				AssertionRepoFunctions.assertVideoPlayer();

				// Mouse over share icon

				// PlayerPageFun.mouseOverShareIcon();

				// Verify Face book button in video control
				assertTrue(
						"Face book button is not present in video control",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.videoControlFacebookButton_XPATH)));

				log.info("Face book button is present in video control");
				// Thread.sleep(LessSleepTime);

				// Mouse over share icon

				// PlayerPageFun.mouseOverShareIcon();

				// Verify Twitter button in video control
				assertTrue(
						"Twitter button is not present in video control",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.videoControltwitterButton_XPATH)));

				log.info("Twitter button is present in video control");
				// Thread.sleep(LessSleepTime);

				// Mouse over share icon

				// PlayerPageFun.mouseOverShareIcon();

				// Verify Copy URL button in video control
			/*	assertTrue(
						"Copy URL button is not present in video control",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.copyUrlButton_XPATH)));

				log.info("Copy URL button is present in video control");*/
				Thread.sleep(LessSleepTime);

				// Mouse over share icon

				// PlayerPageFun.mouseOverShareIcon();

				// Verify Email button in sharing section
				assertTrue(
						"Email button is not present in video control",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.videoControlemailButton_XPATH)));

				log.info("Email button is present in video control");
				Thread.sleep(LessSleepTime);

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
