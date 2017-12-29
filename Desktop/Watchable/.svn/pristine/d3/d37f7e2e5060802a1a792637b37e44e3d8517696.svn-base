package comcast.test.app.testCases.playLists;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.app.testCases.playLists.playListsFunctions.PlayListsFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyPlayListEmailSharringFromEmailContentLoginWithUserName
 * Description: This test script verifies the from email address in play list
 * email sharing pop up window is pre-populated with user email address after
 * login with user name. 
 * Author: Manoj
 * **/

public class VerifyPlayListEmailSharringFromEmailContentLoginWithUserName
		extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyPlayListEmailSharringFromEmailContentLoginWithUserName()
			throws Exception {

		try {

			log.info("Script: VerifyPlayListEmailSharringFromEmailContentLoginWithUserName");
			log.info("********************************************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			Thread.sleep(sleepTime);

			// Login to Watchable application using valid user name and password
			LoginFun.loginToWatchableApplication(driver, UILablesRepo.USERNAME,
					UILablesRepo.PASSWORD);

			WebElement loginError = driver.findElement(By
					.id(XpathObjectRepo.loginError_ID));

			if (loginError.isDisplayed() == false) {

				// Verify Play List menu is present
				assertTrue(
						"Play Lists menu is not present",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.playLists_XPATH)));
				log.info("Play Lists menu is present");

				// Click on 'PLAYLISTS' menu
				PlayListsFun.clickOnPlaylistsMenu();

				// Verify All Play Lists page title
				AssertionRepoFunctions.assertAllPlayListsPageTitle();
				int playListCount = driver.findElements(
						By.xpath(XpathObjectRepo.allPlaylistsIcon_XPATH))
						.size();
				if (playListCount > 0) {

					// Verify Play Icon in Play List
					assertTrue(
							"Play Icon not present in play list",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.playlistPlayIcon_XPATH)));
					log.info("Play Icon present in play list");

					// Click on Play Icon
					PlayListsFun.clickOnPlayIconFromPlayList();

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

					// Verify Video page title
					AssertionRepoFunctions.assertVideoPageTitleFromPlayList();
					log.info("The Video page title displayed after navigating from play list is '"
							+ driver.getTitle() + "'");

					// Verify Play List share button
					assertTrue(
							"Play List share button not present in play list",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.playlistShareButton_XPATH)));
					log.info("Play List share button present in play list");

					// Mouse over Play List share button
					CommonFun.mouseOverElement(driver, driver.findElement(By
							.xpath(XpathObjectRepo.playlistShareButton_XPATH)));

					// Verify Play list Email Share button share option
					assertTrue(
							"Email button not present in play list share option",
							CommonFun.isElementPresent(driver, By
									.id(XpathObjectRepo.playlistShareEmail_ID)));
					log.info("Email button present in play list share option");

					Thread.sleep(LessSleepTime);

					// Click on Play list Email Share button
					PlayListsFun.clickOnPlayListEmailShareButton();

					// Verify Play List Email sharing pop up window is displayed
					assertTrue(
							"Play List Email sharing pop up window is not displayed after clicking on Email button",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.playlistemailShareForm_XPATH)));

					log.info("Play List Email sharing pop up window is displayed after clicking on Email button");

					// Verify From email address test box

					assertTrue(
							"From email address text box is not displaying email pop up window",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.playlistPopupFromEmailText_XPATH)));
					log.info("From email address text box is displaying email pop up window");

					// Verify the from email text field is Pre-populated with
					// logged in user email id

					assertTrue(
							"From email address text box is not pre-populated with logged in user's email id",
							driver.findElement(
									By.xpath(XpathObjectRepo.playlistPopupFromEmailText_XPATH))
									.getAttribute("value")
									.matches(UILablesRepo.EMAIL));
					log.info("From email address text box is pre-populated with logged in user's email id after login with user name");

					log.info("From email address pre-populated is : "
							+ driver.findElement(
									By.xpath(XpathObjectRepo.playlistPopupFromEmailText_XPATH))
									.getAttribute("value"));

					log.info("");
				} else {
					log.info("Play Lists does NOT contains Play list");
				}
			}
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
