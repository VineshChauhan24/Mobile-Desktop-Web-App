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
 * Class Name: VerifyEmailSharringPopuoWindowContents Description: This test
 * script verifies the controls (Objects) present in email sharing pop up
 * window. 
 * Author: Manoj
 * **/

public class VerifyEmailSharringPopuoWindowContents extends BaseTest {
	@Test
	public void testVerifyEmailSharringPopuoWindowContents() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyEmailSharringPopuoWindowContents");
			log.info("************************************************");

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

				// This method is to scroll UI to What we're watching Video from
				// featured shows
				// section.
				HomePageCommonFunctions
						.scrollToVideoSectionFromChannelSection();

				// Verify What we're watching VIDEOS row
				assertTrue(
						"Featured video row is not present in home page",
						CommonFun.isElementPresent(driver, By
								.xpath(XpathObjectRepo.featuredVideosRow_XPATH)));
				log.info("Featured video row is present in home page");

				// Verify Video present in What we're watching VIDEOS row

				int videoCount = driver.findElements(
						By.xpath(XpathObjectRepo.featuredVideosIcon_XPATH))
						.size();
				if (videoCount > 0) {

					// Click on first video from featured video section
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

					// Verify Email button in sharing section
					assertTrue(
							"Email button is not present in sharing section",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.videoControlemailButton_XPATH)));

					log.info("Email button is present in sharing section");

					// Click on Email button from video player controls
					SocialSharingFun.clickonEmailButtonFromPlayer();

					// Verify Email sharing pop up window is displayed
					assertTrue(
							"Email sharing pop up window is not displayed after clicking on Email button",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.emailSharingForm_XPATH)));

					log.info("Email sharing pop up window is displayed after clicking on Email button");

					// Verify Email pop up title

					assertTrue(
							"Titel is not displaying Email sharing pop up window",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.emailPopupTitle_XPATH)));
					log.info("The Title "
							+ driver.findElement(
									By.xpath(XpathObjectRepo.emailPopupTitle_XPATH))
									.getText()
							+ " is displaying email pop up window");

					Thread.sleep(sleepTime);

					// Verify close button

					assertTrue(
							"Close button is not displaying email pop up window",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.emailPopupClosoButton_XPATH)));
					log.info("Close button is displaying email pop up window");

					// Verify From email address test box

					assertTrue(
							"From email address text box is not displaying email pop up window",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.emailPopupFromEmailText_XPATH)));
					log.info("From email address text box is displaying email pop up window");

					// Verify To email address test box

					assertTrue(
							"To email address text box is not displaying email pop up window",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.emailPopupToEmailText_XPATH)));
					log.info("To email address text box is displaying email pop up window");

					// Verify Message test box

					assertTrue(
							"Message text box is not displaying email pop up window",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.emailPopupMessageText_XPATH)));
					log.info("Message text box is displaying email pop up window");

					// Verify Send button

					assertTrue(
							"Send button is not displaying email pop up window",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.emailPopupSendButton_XPATH)));
					log.info("Send button is displaying email pop up window");

					// Verify Message is displaying in message text box in
					// email pop up window
					if (driver
							.findElement(
									By.xpath(XpathObjectRepo.emailPopupMessageText_XPATH))
							.getText().length() > 0) {
						log.info("The Message "
								+ driver.findElement(
										By.xpath(XpathObjectRepo.emailPopupMessageText_XPATH))
										.getText()
								+ " is displaying in message area of email pop up window");
					} else {
						log.error("Message  is not displaying in message area of email pop up window");
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
