package comcast.test.app.testCases.myWatchlist;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.app.testCases.myWatchlist.myWatchlistFunctions.MyWatchlistFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyGoToChannelOption Description: This test case verifies the
 * presence of 'GO TO CHANNEL' option if video are present in My Watchlist
 * page. Author: Manoj
 * **/

public class VerifyGoToChannelOption extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyGoToChannelOption() throws Exception {

		try {

			log.info("Script: VerifyGoToChannelOption");
			log.info("*******************************");

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

				// Click on My Shows menu
				MyWatchlistFun.clickOnMyWatchlistMenu();

				// Verify user is navigated to My Watch list page
				AssertionRepoFunctions.assertMyWatchlistPageTitle();

				// Verify Video present in my watch list

				int videoCount = driver.findElements(
						By.xpath(XpathObjectRepo.myWatchlistVideoIcon_XPATH))
						.size();
				if (videoCount > 0) {

					int channelCount = driver
							.findElements(
									By.xpath(XpathObjectRepo.myWatchlistChannelCount_XPATH))
							.size();

					log.info(videoCount + " Vedeos present under "
							+ channelCount + " channel(s) in My Watchlist page");

					// Verify GO TO CHANNEL option
					int goToMyChannelPresent = driver
							.findElements(
									By.xpath(XpathObjectRepo.myWatchlistGoToChannelLink_XPATH))
							.size();
					if (goToMyChannelPresent > 0) {

						log.info("GO TO CHANNEL option is present in My Watch list page when it contains Videos ");

					} else {
						log.error("GO TO CHANNEL option is NOT present in My Watch list page when it contains Videos ");
					}

					log.info("");
				}

				else {
					log.error("My Watchlist section does not contain videos");
					log.info("");
				}
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
