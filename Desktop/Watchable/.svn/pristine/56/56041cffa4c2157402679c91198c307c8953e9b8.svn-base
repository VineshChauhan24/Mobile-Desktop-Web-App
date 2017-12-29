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
 * Class Name: VerifyVideoEmailSharringFromEmailContentLoginWithUserName
 * Description: This test script verifies the from email text field im email
 * sharing of video windows is pre-populated with logged in user email address
 * after login with user name. 
 * Author: Manoj
 * **/

public class VerifyVideoEmailSharringFromEmailContentLoginWithUserName extends
		BaseTest {
	@Test
	public void testVerifyVideoEmailSharringFromEmailContentLoginWithUserName()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyVideoEmailSharringFromEmailContentLoginWithUserName");
			log.info("*****************************************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Login to Watchable application using valid user name and password
			LoginFun.loginToWatchableApplication(driver, UILablesRepo.USERNAME,
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

					// Verify From email address test box

					assertTrue(
							"From email address text box is not displaying email pop up window",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.emailPopupFromEmailText_XPATH)));
					log.info("From email address text box is displaying email pop up window");

					// Verify the from email text field is Pre-populated with
					// logged in user email id

					assertTrue(
							"From email address text box is not pre-populated with logged in user's email id after login with user name",
							driver.findElement(
									By.xpath(XpathObjectRepo.emailPopupFromEmailText_XPATH))
									.getAttribute("value")
									.matches(UILablesRepo.EMAIL));
					log.info("From email address text box is pre-populated with logged in user's email id after login with user name");

					log.info("From email address pre-populated is : "
							+ driver.findElement(
									By.xpath(XpathObjectRepo.emailPopupFromEmailText_XPATH))
									.getAttribute("value"));

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
