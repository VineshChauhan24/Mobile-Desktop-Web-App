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
 * Class Name: VerifyChannelNameDisplayInMyWatchlistPageWhenVideosPresent
 * Description: This test case verifies Shows names are displaying in video
 * section when video are present in My Shows page. Author: Manoj
 * **/

public class VerifyChannelNameDisplayInMyWatchlistPageWhenVideosPresent extends
		BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyChannelNameDisplayInMyWatchlistPageWhenVideosPresent()
			throws Exception {

		try {

			log.info("Script: VerifyChannelNameDisplayInMyWatchlistPageWhenVideosPresent");
			log.info("******************************************************************");

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
						"My Shows menu is not present",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.myWatchlistMenu_XPATH)));
				log.info("My Shows menu is present");

				// Click on My Shows menu
				MyWatchlistFun.clickOnMyWatchlistMenu();

				// Verify user is navigated to My Shows list page
				AssertionRepoFunctions.assertMyWatchlistPageTitle();

				// Verify Video present in my Shows list

				int videoCount = driver.findElements(
						By.xpath(XpathObjectRepo.myWatchlistVideoIcon_XPATH))
						.size();
				if (videoCount > 0) {
					
					int j = 0;

					int channelCount = driver
							.findElements(
									By.xpath(XpathObjectRepo.myWatchlistChannelCount_XPATH))
							.size();
					if (channelCount > 0) {
						log.info("Shows names are displaying in My Shows page");

						log.info(videoCount + " Vedeos present under "
								+ channelCount
								+ " Shows in My Watchlist page");

						log.info("Videos are present from following Shows in My Shows page");
						log.info("----------------------------------------------------------------");

						//Getting Shows names
						for (int i = 1; i <= channelCount; i++) {
							// Getting Shows title from My Shows page
							String myWatchlistChannelTitle = driver
									.findElement(
											By.xpath(XpathObjectRepo.myWatchlistChannelTitlePartOne_XPATH
													+ j
													+ XpathObjectRepo.myWatchlistChannelTitlePartTwo_XPATH))
									.getText();

							log.info(i + ". " + myWatchlistChannelTitle);
							j = j + 1;

						}
						log.info("");
					}

					else {
						log.error("My Shows section does not contain videos");
						log.info("");

					}

				} else {
					log.error("My Shows section does not contain videos");
					log.info("");
				}
			}
		}

		catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}

	}
}
