package comcast.test.app.testCases.follow;

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
import comcast.test.app.testCases.myWatchlist.myWatchlistFunctions.MyWatchlistFun;
import comcast.test.app.testCases.playerVideoPage.playerPageFunctions.PlayerPageFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyFollowFromVideoControlAfterLogin Description: This test
 * script verifies follow a channel from video controls after login and verified
 * the followed channel is present in My Watch List. Author: Manoj
 * **/

public class VerifyFollowFromVideoControlAfterLogin extends BaseTest {

	@Test
	public void testVerifyFollowFromVideoControlAfterLogin() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyFollowFromVideoControlAfterLogin");
			log.info("************************************************");

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

				// This method is to scroll UI to What we're watching Video from
				// featured
				// shows
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

					// Click on first video from What we're watching video
					// section
					PlayerPageFun.clickOnFeaturedVideoTitle();

					int nowPlaying = driver.findElements(
							By.xpath(XpathObjectRepo.nowPlayingTitle_XPATH))
							.size();

					if (nowPlaying > 0) {

						if (driver
								.findElement(
										By.xpath(XpathObjectRepo.nowPlayingTitle_XPATH))
								.isDisplayed()) {

							// Checking whether selected video is partially
							// played
							WebElement playFromStartPresent = driver
									.findElement(By
											.id(XpathObjectRepo.playFromStartButton_ID));

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

					// Verify selected channel is already followed or not
					boolean channelPresent = false;

					String follow = driver
							.findElement(
									By.xpath(XpathObjectRepo.followVideoPageButton_XPATH))
							.getAttribute("class").trim();

					log.info(follow);

					if (!follow.contains(UILablesRepo.FOLLOW)) {

						String selectedChannel = driver
								.findElement(
										By.xpath(XpathObjectRepo.playingVideoChannelTitle_XPATH))
								.getText();

						// Click on follow icon
						PlayerPageFun.clickOnFollowButton();

						// **** Verify the followed channel is present in My
						// Watch list ****

						// Verify My Shows menu is present
						assertTrue(
								"My Shows menu is not present",
								CommonFun.isElementPresent(
										driver,
										By.xpath(XpathObjectRepo.myWatchlistMenu_XPATH)));
						log.info("My Shows menu is present");

						// Click on My Shows list menu
						MyWatchlistFun.clickOnMyWatchlistMenu();

						// Verify user is navigated to My Shows list page
						AssertionRepoFunctions.assertMyWatchlistPageTitle();

						// Verify Video present in my watch list
						
						int j = 0;

						int channelCount = driver
								.findElements(
										By.xpath(XpathObjectRepo.myWatchlistChannelCount_XPATH))
								.size();
						if (channelCount > 0) {

							// Getting Shows names
							for (int i = 1; i <= channelCount; i++) {
								// Getting Shows title from My Shows list
								// page
								String myWatchlistChannelTitle = driver
										.findElement(
												By.xpath(XpathObjectRepo.myWatchlistChannelTitlePartOne_XPATH
														+ j
														+ XpathObjectRepo.myWatchlistChannelTitlePartTwo_XPATH))
										.getText();

								// log.info(i + ". " + myWatchlistChannelTitle);

								if (myWatchlistChannelTitle
										.toUpperCase()
										.contains(selectedChannel.toUpperCase())) {

									log.info("The Followed Shows '"
											+ selectedChannel
											+ "' is present in My Shows list");
									channelPresent = true;

									log.info("");
									break;

								}
								
								j = j + 1;

							}

							if (channelPresent != true) {
								log.info("The Followe channel '"
										+ selectedChannel
										+ "' is not present in My Watch list");

								log.info("");
							}

						} else {
							log.error("My Watchlist section does not contain videos");
							log.info("");
						}

					} else {

						log.info("Your are already following this channel...!");
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
