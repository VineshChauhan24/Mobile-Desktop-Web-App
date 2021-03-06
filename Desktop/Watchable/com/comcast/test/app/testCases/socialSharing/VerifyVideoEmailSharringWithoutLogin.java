package comcast.test.app.testCases.socialSharing;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.playerVideoPage.playerPageFunctions.PlayerPageFun;
import comcast.test.app.testCases.socialSharing.socialSharingFunctions.SocialSharingFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyVideoEmailSharringWithoutLogin Description: This test
 * script verifies the login pop up displayed if user try to share video through
 * email when user is not logged into application. Author: Manoj
 * **/

public class VerifyVideoEmailSharringWithoutLogin extends BaseTest {
	@Test
	public void testVerifyVideoEmailSharringWithoutLogin() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyVideoEmailSharringWithoutLogin");
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

				int nowPlaying = driver.findElements(
						By.xpath(XpathObjectRepo.nowPlayingTitle_XPATH)).size();

				if (nowPlaying > 0) {

					// Checking whether selected video is partially played
					WebElement playFromStartPresent = driver.findElement(By
							.id(XpathObjectRepo.playFromStartButton_ID));

					if (driver.findElement(
							By.xpath(XpathObjectRepo.nowPlayingTitle_XPATH))
							.isDisplayed()) {

						boolean btnPresent = playFromStartPresent.isDisplayed();

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

				// Verify login pop up opened

				assertTrue(
						"Login pop up is not opened when user clicked on video email sharing option if user is not logged into application",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.loginForm_XPATH)));

				assertTrue(
						"Login pop up is not opened when user clicked on video email sharing option if user is not logged into application",
						driver.findElement(
								By.xpath(XpathObjectRepo.loginForm_XPATH))
								.isDisplayed());
				log.info("Login pop up is opened when user clicked on video email sharing option if user is not logged into application");

				log.info("");

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
