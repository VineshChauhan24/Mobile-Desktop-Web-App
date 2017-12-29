package comcast.test.app.testCases.socialSharing;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.app.testCases.playerVideoPage.playerPageFunctions.PlayerPageFun;
import comcast.test.app.testCases.socialSharing.socialSharingFunctions.SocialSharingFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyEmailFunctionalityFromVideoControls Description: This test
 * script verifies the opening of Email sharing popup window after clicking on
 * Email button from video play controls. 
 * Author: Manoj
 * **/

public class VerifyEmailFunctionalityFromVideoControls extends BaseTest {
	@Test
	public void testVerifyEmailFunctionalityFromVideoControls()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyEmailFunctionalityFromVideoControls");
			log.info("****************************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Login to Watchable application using valid password
			LoginFun.loginToWatchableApplication(driver, UILablesRepo.EMAIL,
					UILablesRepo.PASSWORD);
			WebElement loginError = driver.findElement(By
					.id(XpathObjectRepo.loginError_ID));

			if (loginError.isDisplayed() == false) {

				// This method is to scroll UI to Featured Video from featured
				// shows
				// section.
				HomePageCommonFunctions
						.scrollToVideoSectionFromChannelSection();

				// Verify What we're watching row
				assertTrue(
						"Featured video row is not present in home page",
						CommonFun.isElementPresent(driver, By
								.xpath(XpathObjectRepo.featuredVideosRow_XPATH)));
				log.info("Featured video row is present in home page");

				// Verify Video present in What we're watching row

				int videoCount = driver.findElements(
						By.xpath(XpathObjectRepo.featuredVideosIcon_XPATH))
						.size();
				if (videoCount > 0) {

					// Click on first video from What we're watching video
					// section
					PlayerPageFun.clickOnFeaturedVideoTitle();

					int nowPlaying = driver.findElements(
							By.xpath(XpathObjectRepo.nowPlayingTitle_XPATH))
							.size();

					if (nowPlaying > 0) {

						// Checking whether selected video is partially played
						WebElement playFromStartPresent = driver.findElement(By
								.id(XpathObjectRepo.playFromStartButton_ID));

						if (driver
								.findElement(
										By.xpath(XpathObjectRepo.nowPlayingTitle_XPATH))
								.isDisplayed()) {

							boolean btnPresent = playFromStartPresent
									.isDisplayed();

							log.info(btnPresent);
							if (btnPresent == true) {

								log.info("play from start present");

								driver.findElement(
										By.id(XpathObjectRepo.playFromStartButton_ID))
										.click();
								Thread.sleep(sleepTime);
								Thread.sleep(sleepTime);
							}
						}
					}

					// Verify User is navigated to video page
					AssertionRepoFunctions.assertVideoPageTitle();

					// Mouse over video player
					PlayerPageFun.mouseOverVideoPlayer();

					// Verify video player is displayed in page
					AssertionRepoFunctions.assertVideoPlayer();

					// Mouse over video player
					PlayerPageFun.mouseOverVideoPlayer();

					// Verify Email button in video control
					assertTrue(
							"Email button is not present in video control",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.videoControlemailButton_XPATH)));

					log.info("Email button is present in video control");
					Thread.sleep(LessSleepTime);

					// Mouse over share icon
					// PlayerPageFun.mouseOverShareIcon();

					// Click on Email button from video player controls
					SocialSharingFun.clickonEmailButtonFromPlayer();

					// Verify Email sharing pop up window is displayed
					assertTrue(
							"Email sharing pop up window is not displayed after clicking on Email button",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.emailSharingForm_XPATH)));

					assertTrue(
							"Email sharing pop up window is not displayed after clicking on Email button",
							driver.findElement(
									By.xpath(XpathObjectRepo.emailSharingForm_XPATH))
									.isDisplayed());

					log.info("Email sharing pop up window is displayed after clicking on Email button");

					// Verify The Video URL is displaying in message text box in
					// email pop up window
					if (driver
							.findElement(
									By.xpath(XpathObjectRepo.emailPopupMessageText_XPATH))
							.getText().length() > 0) {
						log.info("The message "
								+ driver.findElement(
										By.xpath(XpathObjectRepo.emailPopupMessageText_XPATH))
										.getText()
								+ " is displaying in message area of email pop up window");
					} else {
						log.error("The message is not displaying in message area of email pop up window");
					}

				} else {
					log.error("Featured video section does not contain videos");
					log.info("");
				}
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
