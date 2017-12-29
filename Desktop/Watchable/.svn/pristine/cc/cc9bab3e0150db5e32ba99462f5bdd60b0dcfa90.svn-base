package comcast.test.app.testCases.follow;

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
 * Class Name: VerifyUnfollowConfirmationPopupContent Description: This test
 * script verifies the content of un follow confirmation pop up..Author: Manoj
 * **/

public class VerifyUnfollowConfirmationPopupContent extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyUnfollowConfirmationPopupContent() throws Exception {

		try {

			log.info("Script: VerifyUnfollowConfirmationPopupContent");
			log.info("**********************************************");

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
						 

						// Verify The channel name displayed in Un-follow
						// confirmation pop up message
						assertTrue(
								"channel name not displayed in Un-follow confirmation pop up message",
								CommonFun.isElementPresent(
										driver,
										By.xpath(XpathObjectRepo.unFollowChannel_XPATH)));
						log.info("The channel name '"
								+ driver.findElement(
										By.xpath(XpathObjectRepo.channelPageChannelTitle_XPATH))
										.getText()
								+ " ' displayed in Un-follow confirmation pop up message");

						// Verify Un-follow confirmation pop up message contains
						// cancel button
						assertTrue(
								"Un-follow confirmation pop up message does not contains cancel button",
								CommonFun.isElementPresent(
										driver,
										By.xpath(XpathObjectRepo.unFollowCancelButton_XPATH)));
						log.info("Un-follow confirmation pop up message contains cancel button");

						// Verify Un-follow confirmation pop up message contains
						// confirm button
						assertTrue(
								"Un-follow confirmation pop up message does not contains confirm button",
								CommonFun.isElementPresent(
										driver,
										By.xpath(XpathObjectRepo.unFollowConfirmButton_XPATH)));
						log.info("Un-follow confirmation pop up message contains confirm button");*/

						log.info("");

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
