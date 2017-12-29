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
 * Class Name: VerifyUnfollowConfirmationPopupDisplay Description: This test
 * script verifies clicking on un-follow button displaying confirmation popup
 * message.Author: Manoj
 * **/

public class VerifyUnfollowConfirmationPopupDisplay extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyUnfollowConfirmationPopupDisplay() throws Exception {

		try {

			log.info("Script: VerifyUnfollowConfirmationPopupDisplay");
			log.info("**********************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			Thread.sleep(sleepTime);

			// Login to Watchable application
			LoginFun.loginToWatchableApplication(driver, UILablesRepo.EMAIL,
					UILablesRepo.PASSWORD);
			WebElement loginError = driver.findElement(By
					.id(XpathObjectRepo.loginError_ID));

			if (loginError.isDisplayed() == false) {

				// Verify My Shows menu is present
				assertTrue(
						"My Watchlist menu is not present",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.myWatchlistMenu_XPATH)));
				log.info("My Watchlist menu is present");

				// Click on My Shows menu
				MyWatchlistFun.clickOnMyWatchlistMenu();

				// Verify user is navigated to My Shows list page
				AssertionRepoFunctions.assertMyWatchlistPageTitle();

				int channelCount = driver
						.findElements(
								By.xpath(XpathObjectRepo.myWatchlistChannelCount_XPATH))
						.size();
				if (channelCount > 0) {

					// Getting channel title from My Watchlist page
					String myWatchlistChannelTitle = driver
							.findElement(
									By.xpath(XpathObjectRepo.myWatchlistChannelTitlePartOne_XPATH
											+ 0
											+ XpathObjectRepo.myWatchlistChannelTitlePartTwo_XPATH))
							.getText();

					// Click on First show title

					MyWatchlistFun.clickOnShowsTitle();

					// Verify success fully navigated shows page
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
					
					// Verify follow button is present in Shows page

					assertTrue(
							"Follow button is not present in Shows page",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.followButton_XPATH)));
					log.info("Follow button is present in Shows page");

					// Click on un-follow button
					FollowFun.clickOnUnFollowButton();

					// Verify Un-follow confirmation pop up message is
					// displayed
					/*
					 * assertTrue(
					 * "Un-follow confirmation pop up message is not displayed",
					 * CommonFun.isElementPresent( driver,
					 * By.xpath(XpathObjectRepo.unFollowSuccessMsg_XPATH)));
					 * log.info("Un-follow confirmation message is displayed");
					 * 
					 * log.info(
					 * "The message displayed: YOU ARE NO LONGER FOLLOWING " +
					 * driver.findElement(
					 * By.xpath(XpathObjectRepo.channelPageChannelTitle_XPATH))
					 * .getText());
					 */

					log.info("");

				}

				else {
					log.info("You are not following any shows.");
				}

			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
