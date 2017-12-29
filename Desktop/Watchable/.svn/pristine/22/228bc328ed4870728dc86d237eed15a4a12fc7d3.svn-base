package comcast.test.app.testCases.follow;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
//import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.follow.followFunctions.FollowFun;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.app.testCases.myWatchlist.myWatchlistFunctions.MyWatchlistFun;
import comcast.test.app.testCases.playerVideoPage.playerPageFunctions.PlayerPageFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyUnfollowFromVideoPage Description: This test script
 * verifies unfollow a channel from video page and verified the un followed
 * channel is removed from My Watch List.Author: Manoj
 * **/

public class VerifyUnfollowFromVideoPage extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyUnfollowFromVideoPage() throws Exception {

		try {

			log.info("Script: VerifyUnfollowFromVideoPage");
			log.info("*************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			Thread.sleep(sleepTime);

			// Login to Watchable application using invalid password
			LoginFun.loginToWatchableApplication(driver, UILablesRepo.EMAIL,
					UILablesRepo.PASSWORD);
			WebElement loginError = driver.findElement(By
					.id(XpathObjectRepo.loginError_ID));

			if (loginError.isDisplayed() == false) {

				// Verify My Shows menu is present
				assertTrue(
						"My Shows menu is not present",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.myWatchlistMenu_XPATH)));
				log.info("My Shows menu is present");

				boolean channelPresent = true;

				// Click on My Shows menu
				MyWatchlistFun.clickOnMyWatchlistMenu();

				// Verify user is navigated to My Watch list page
				AssertionRepoFunctions.assertMyWatchlistPageTitle();

				// Verify Video present in my watch list

				int videoCount = driver.findElements(
						By.xpath(XpathObjectRepo.myWatchlistVideoIcon_XPATH))
						.size();
				if (videoCount > 0) {

					// Click on first video from my Shows list section
					PlayerPageFun.clickOnMyWatchlistVideoTitle();

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

					// Verify video player is displayed in video page
					AssertionRepoFunctions.assertVideoPlayer();

					// Scroll to channel section
					//HomePageCommonFunctions.scrollToVideoResultSection();
					
					PlayerPageFun.mouseOverVideoPlayer();
					// Click on Pause button
					PlayerPageFun.clickOnPauseButton();

					String videoPageChannelTitle = driver
							.findElement(
									By.xpath(XpathObjectRepo.playingVideoChannelTitle_XPATH))
							.getText();

					// Click on un-follow button
					FollowFun.clickOnVideoUnFollowButton();

					// Verify Un-follow confirmation pop up message is
					// displayed
					/*	assertTrue(
							"Un-follow confirmation pop up message is not displayed",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.unFollowSuccessMsg_XPATH)));
					log.info("Un-follow confirmation message is displayed");

					// Verify Un-follow confirmation pop up message contains
					// confirm button
					assertTrue(
							"Un-follow confirmation pop up message does not contains confirm button",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.unFollowConfirmButton_XPATH)));
					log.info("Un-follow confirmation pop up message contains confirm button");

					// Click on confirm button from Un-follow confirmation
					// pop up message
					FollowFun.clickOnConfirmButton();*/

					// **** Verify the un-followed channel is not present in
					// My
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

					// Verify user is navigated to My Watch list page
					AssertionRepoFunctions.assertMyWatchlistPageTitle();

					// Verify Video present in my Shows list

					int channelCount = driver
							.findElements(
									By.xpath(XpathObjectRepo.myWatchlistChannelCount_XPATH))
							.size();
					int j = 0;
					if (channelCount > 0) {

						// Getting Channel names
						for (int i = 1; i <= channelCount; i++) {

							String myWatchlistChannelTitle = driver
									.findElement(
											By.xpath(XpathObjectRepo.myWatchlistChannelTitlePartOne_XPATH
													+ j
													+ XpathObjectRepo.myWatchlistChannelTitlePartTwo_XPATH))
									.getText();

							if (!myWatchlistChannelTitle
									.toUpperCase()
									.contains(
											videoPageChannelTitle.toUpperCase())) {

								log.info("The UN Followed Shows '"
										+ videoPageChannelTitle
										+ "' is not present in My Shows list");

								channelPresent = false;

								log.info("");
								break;

							}
							
							j = j + 1;

						}

						if (channelPresent != false) {
							log.info("The UN Followed Shows '"
									+ videoPageChannelTitle
									+ "' is present in My Shows list");

							log.info("");
						}

						log.info("");
					} else {
						log.info("The UN Followed Shows '"
								+ videoPageChannelTitle
								+ "' is not present in My Shows list");

						log.info("");
					}

				}

				else {
					log.error("My Shows section does not contain Shows");
					log.info("");
				}
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
