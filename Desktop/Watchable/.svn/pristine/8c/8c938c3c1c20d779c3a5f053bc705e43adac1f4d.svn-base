package comcast.test.app.testCases.follow;

//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.follow.followFunctions.FollowFun;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.app.testCases.myWatchlist.myWatchlistFunctions.MyWatchlistFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyUnfollowConfirmationConfirmFunctionality Description: This
 * test script verifies the functionality of confirm button from unfollow
 * confirmation popup.Author: Manoj
 * **/

public class VerifyUnfollowConfirmationConfirmFunctionality extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyUnfollowConfirmationConfirmFunctionality()
			throws Exception {

		try {

			log.info("Script: VerifyUnfollowConfirmationConfirmFunctionality");
			log.info("*******************************************************");

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

				// Verify My Watchlist menu is present
				assertTrue(
						"My Watchlist menu is not present",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.myWatchlistMenu_XPATH)));
				log.info("My Watchlist menu is present");

				boolean channelPresent = true;

				// Click on My Watchlist menu
				MyWatchlistFun.clickOnMyWatchlistMenu();

				// Verify user is navigated to My Watch list page
				AssertionRepoFunctions.assertMyWatchlistPageTitle();

				// Verify GO TO CHANNEL option
				int goToMyChannelPresent = driver
						.findElements(
								By.xpath(XpathObjectRepo.myWatchlistGoToChannelLink_XPATH))
						.size();
				if (goToMyChannelPresent > 0) {

					log.info("GO TO CHANNEL option is present in My Watch list page when it contains Videos ");

					int channelCount = driver
							.findElements(
									By.xpath(XpathObjectRepo.myWatchlistChannelCount_XPATH))
							.size();
					if (channelCount > 0) {

						// Navigating to channel Page by clicking on GO TO
						// CHANNEL option
						// Getting channel title from My Watchlist page
						String myWatchlistChannelTitle = driver
								.findElement(
										By.xpath(XpathObjectRepo.myWatchlistChannelTitlePartOne_XPATH
												+ 1
												+ XpathObjectRepo.myWatchlistChannelTitlePartTwo_XPATH))
								.getText();

						// Click on GO TO CHANNEL option

						driver.findElement(
								By.xpath(XpathObjectRepo.myWatchlistGoToChannelLinkPartOne_XPATH
										+ 1
										+ XpathObjectRepo.myWatchlistGoToChannelLinkPartTwo_XPATH))
								.click();
						Thread.sleep(sleepTime);
						log.info("Successfully clicked on GO TO CHANNEL option of video section of the channel '"
								+ myWatchlistChannelTitle + "'");

						// Verify success fully navigated channel page
						AssertionRepoFunctions.assertChannelPageTitle();

						String channelPageChannelTitle = driver
								.findElement(
										By.xpath(XpathObjectRepo.channelPageChannelTitle_XPATH))
								.getText();

						assertTrue(
								"Failed to Navigate to Chaanel details page of the channel '"
										+ myWatchlistChannelTitle + "'",
								channelPageChannelTitle
										.contains(myWatchlistChannelTitle));

						// Click on un-follow button
						FollowFun.clickOnUnFollowButton();

						// Verify Un-follow confirmation pop up message is
						// displayed
						/*
						 * assertTrue(
						 * "Un-follow confirmation pop up message is not displayed"
						 * , CommonFun.isElementPresent( driver,
						 * By.xpath(XpathObjectRepo.unFollowSuccessMsg_XPATH)));
						 * log
						 * .info("Un-follow confirmation message is displayed");
						 */

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
						FollowFun.clickOnConfirmButton();

						// Verify Un-follow confirmation pop up is closed

						/*
						 * assertFalse(
						 * "Un-follow confirmation pop up is not closed after clicking on confirm button"
						 * , driver.findElement(
						 * By.xpath(XpathObjectRepo.unFollowSuccessMsg_XPATH))
						 * .isDisplayed()); log.info(
						 * "Un-follow confirmation pop up is closed after clicking on confirm button"
						 * );
						 */

						// **** Verify the un-followed channel is not present in
						// My
						// Watch list ****

						// Verify My Watchlist menu is present
						assertTrue(
								"My Watchlist menu is not present",
								CommonFun.isElementPresent(
										driver,
										By.xpath(XpathObjectRepo.myWatchlistMenu_XPATH)));
						log.info("My Watchlist menu is present");

						// Click on My Watch list menu
						MyWatchlistFun.clickOnMyWatchlistMenu();

						// Verify user is navigated to My Watch list page
						AssertionRepoFunctions.assertMyWatchlistPageTitle();

						// Verify Video present in my watch list

						channelCount = driver
								.findElements(
										By.xpath(XpathObjectRepo.myWatchlistChannelCount_XPATH))
								.size();
						if (channelCount > 0) {

							// Getting Channel names
							for (int i = 1; i <= channelCount; i++) {

								myWatchlistChannelTitle = driver
										.findElement(
												By.xpath(XpathObjectRepo.myWatchlistChannelTitlePartOne_XPATH
														+ i
														+ XpathObjectRepo.myWatchlistChannelTitlePartTwo_XPATH))
										.getText();

								if (!myWatchlistChannelTitle.toUpperCase()
										.contains(
												channelPageChannelTitle
														.toUpperCase())) {

									log.info("The UN Followed channel '"
											+ channelPageChannelTitle
											+ "' is not present in My Watch list");

									channelPresent = false;

									log.info("");
									break;

								}

							}

							if (channelPresent != false) {
								log.info("The UN Followed channel '"
										+ channelPageChannelTitle
										+ "' is present in My Watch list");

								log.info("");
							}

							log.info("");
						} else {
							log.info("The UN Followed channel '"
									+ channelPageChannelTitle
									+ "' is not present in My Watch list");

							log.info("");
						}

					}

				} else {
					log.error("GO TO CHANNEL option is NOT present in My Watch list page when it contains Videos ");
					log.info("");
				}

			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
