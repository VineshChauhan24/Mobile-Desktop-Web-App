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
import comcast.test.app.testCases.channelPage.channelPageFunctions.ChannelPageFun;
import comcast.test.app.testCases.follow.followFunctions.FollowFun;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.app.testCases.myWatchlist.myWatchlistFunctions.MyWatchlistFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyFollowFromChannelPageAfterLogin Description: This test
 * script verifies follow a channel from channel page after login and verified
 * the followed channel is present in My Watch List. Author: Manoj
 * **/

public class VerifyFollowFromChannelPageAfterLogin extends BaseTest {

	@Test
	public void testVerifyFollowFromChannelPageAfterLogin() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyFollowFromChannelPageAfterLogin");
			log.info("***********************************************");

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

				// This method asserts Featured Channels title.
				AssertionRepoFunctions.assertFeaturedChannelsTitle();

				// This method is to scroll UI to Popular Channels Section.
				HomePageCommonFunctions.scrollToPopularChannelsSection();
				Thread.sleep(sleepTime);

				// Verify Channel present in FEATURED CHANNELS row

				int channelCount = driver.findElements(
						By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
						.size();
				if (channelCount > 0) {

					log.info(channelCount
							+ " Channels are present in featured channels section in home page");

					String selectedChannel = driver
							.findElement(
									By.xpath(XpathObjectRepo.featuredFirstChannelTitle_XPATH))
							.getText();
					log.info("Selected channel: " + selectedChannel);
					// Click on first channel from featured Channel section

					ChannelPageFun.clickOnFirstFeaturedChannelLink();

					// Verify successfully navigate to channel details page
					AssertionRepoFunctions.assertChannelPageTitle();

					// Verify selected channel is already followed or not

					boolean channelPresent = false;
					
					// Verify follow button is present in Shows page

					assertTrue(
							"Follow button is not present in Shows page",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.followButton_XPATH)));
					log.info("Follow button is present in Shows page");

					String follow = driver
							.findElement(
									By.xpath(XpathObjectRepo.followButton_XPATH))
							.getAttribute("class").trim();

					log.info(follow);

					if (!follow.contains(UILablesRepo.FOLLOW)) {

						// Click on follow button
						FollowFun.clickOnFollowButton();

						// Verify successful follow message is displayed
					/*	assertTrue(
								"Successful follow message is not displayed",
								CommonFun.isElementPresent(
										driver,
										By.xpath(XpathObjectRepo.followSuccessMsg_XPATH)));
						log.info("Successful follow message is displayed");*/

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

						channelCount = driver
								.findElements(
										By.xpath(XpathObjectRepo.myWatchlistChannelCount_XPATH))
								.size();
						if (channelCount > 0) {

							// Getting Shows names
							for (int i = 1; i <= channelCount; i++) {
								// Getting channel title from My Watch list
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
								log.info("The Followed Shows '"
										+ selectedChannel
										+ "' is not present in My Shows list");

								log.info("");
							}

						} else {
							log.error("My Shows section does not contain videos");
							log.info("");
						}

					} else {

						log.info("Your are already following this Shows...!");
					}

				} else {
					log.error("Shows are not present in featured Shows section in home page");
					log.info("");
				}
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
