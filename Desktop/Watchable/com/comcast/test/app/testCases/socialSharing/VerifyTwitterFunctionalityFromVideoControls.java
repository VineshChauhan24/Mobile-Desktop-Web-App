package comcast.test.app.testCases.socialSharing;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.playerVideoPage.playerPageFunctions.PlayerPageFun;
import comcast.test.app.testCases.socialSharing.socialSharingFunctions.SocialSharingFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyTwitterFunctionalityFromVideoControls Description: This
 * test script verifies the opening of twitter sharing window after clicking
 * onTwitter button from video play controls. Author: Manoj
 * **/

public class VerifyTwitterFunctionalityFromVideoControls extends BaseTest {
	@Test
	public void testVerifyTwitterFunctionalityFromVideoControls()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyTwitterFunctionalityFromVideoControls");
			log.info("****************************************************");

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

				// Click on first video from What we're watching video section
				PlayerPageFun.clickOnFeaturedVideoTitle();

				// Verify User is navigated to video page
				AssertionRepoFunctions.assertVideoPageTitle();

				// Mouse over video player
				PlayerPageFun.mouseOverVideoPlayer();

				// Verify video player is displayed in page
				AssertionRepoFunctions.assertVideoPlayer();

				// Verify Twitter button in video control
				assertTrue(
						"Twitter button is not present in video control",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.videoControltwitterButton_XPATH)));

				log.info("Twitter button is present in video control");
				Thread.sleep(LessSleepTime);

				String videoUrl = driver.getCurrentUrl();
				videoUrl = videoUrl.substring(28);
				log.info("Currently playing video: " + videoUrl);

				// Click on Twitter button from video player controls
				SocialSharingFun.clickonTwitterButtonFromPlayer();

				String parentWindow = driver.getWindowHandle();
				Set<String> handles = driver.getWindowHandles();

				for (String windowHandle : handles) {
					if (!windowHandle.equals(parentWindow)) {
						driver.switchTo().window(windowHandle);

						Thread.sleep(sleepTime);
						log.info("Share a link on Twitter popup window opened after clicking on Tweet button");
						String twitterVideoUrl = driver
								.findElement(
										By.xpath(XpathObjectRepo.tweetVideoUrlText_XPATH))
								.getText();
						if (twitterVideoUrl.trim().length() > 0) {
							log.info("Text present in share text box in Twitter pop up window");
							log.info("Share text box content : "
									+ twitterVideoUrl);

							assertTrue(
									"Text present in share text box in Twitter pop up window NOT contains currently playing video URL",
									twitterVideoUrl.contains(videoUrl));
							log.info("Text present in share text box in Twitter pop up window contains currently playing video URL");

						} else {
							log.error("No text present in share text box in Twitter pop up window");
						}

						driver.close(); // closing child window
						driver.switchTo().window(parentWindow); // Control
																// to
																// parent
																// window

					}

				}

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
