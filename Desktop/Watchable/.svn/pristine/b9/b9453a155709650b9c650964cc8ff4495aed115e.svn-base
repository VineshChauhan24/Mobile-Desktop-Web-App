package comcast.test.app.testCases.playerVideoPage;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.app.testCases.playerVideoPage.playerPageFunctions.PlayerPageFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyPartiallyPlayedVideo Description: This test script verify
 * whether the selected video is partially played or not. Author: Manoj
 * **/

public class VerifyPartiallyPlayedVideo extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyPartiallyPlayedVideo() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyPartiallyPlayedVideo");
			log.info("**********************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Login to Watchable application
			LoginFun.loginToWatchableApplication(driver, UILablesRepo.EMAIL,
					UILablesRepo.PASSWORD);

			WebElement loginError = driver.findElement(By
					.id(XpathObjectRepo.loginError_ID));

			if (loginError.isDisplayed() == false) {

				// This method is to scroll UI to What we're watching section.
				HomePageCommonFunctions
						.scrollToVideoSectionFromChannelSection();

				// This method asserts What we're watching title.
				AssertionRepoFunctions.assertFeaturedTitle();

				// Verify What we're watching VIDEOS row
				assertTrue(
						"What we're watching video row is not present in home page",
						CommonFun.isElementPresent(driver, By
								.xpath(XpathObjectRepo.featuredVideosRow_XPATH)));
				log.info("What we're watching video row is present in home page");

				// Verify Video present in What we're watching VIDEOS row

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
						if (driver
								.findElement(
										By.xpath(XpathObjectRepo.nowPlayingTitle_XPATH))
								.isDisplayed()) {
							log.info("The selected video is partially played");

							assertTrue(
									"Featured video row is not present in home page",
									CommonFun.isElementPresent(
											driver,
											By.xpath(XpathObjectRepo.nowPlayingTitle_XPATH)));
							log.info("The title '"
									+ driver.findElement(
											By.xpath(XpathObjectRepo.nowPlayingTitle_XPATH))
											.getText() + "' is displayed");

							// Verify PLAY FROM START button is displayed

							assertTrue(
									"Play from start button is not displayed",
									CommonFun.isElementPresent(
											driver,
											By.id(XpathObjectRepo.playFromStartButton_ID)));
							log.info("Play from start button is not displayed");

							// Verify PLAY FROM RESUME poin button is displayed

							assertTrue(
									"Play from resume point button is not displayed",
									CommonFun.isElementPresent(
											driver,
											By.id(XpathObjectRepo.playFromResumeButton_ID)));
							log.info("Play from resume point button is not displayed");

						}
					}

					else {
						log.info("The selected video is not partially played");
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
