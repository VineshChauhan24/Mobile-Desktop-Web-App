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
 * Class Name: VerifyMoreShowsButtonInShowsSectionWhenNoFollowedShows Description:This
 * test case verifies More shows button is displaying in Watchable SHOWS OF THE WEEK section when no followed shows in My Shows page.
 * Author: Manoj
 * **/

public class VerifyMoreShowsButtonInShowsSectionWhenNoFollowedShows extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyMoreShowsButtonInShowsSectionWhenNoFollowedShows()
			throws Exception {

		try {

			log.info("Script: VerifyMoreShowsButtonInShowsSectionWhenNoFollowedShows");
			log.info("****************************************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			Thread.sleep(sleepTime);

			// Login to Watchable application 
			LoginFun.loginToWatchableApplication(driver, UILablesRepo.NO_WATCHLIST_USER,
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
				
				driver.navigate().refresh();
				Thread.sleep(sleepTime);

				// Verify user is navigated to My Shows list page
				AssertionRepoFunctions.assertMyWatchlistPageTitle();

				// Verify my Shows list page title
				assertTrue(
						"My Shows menu is not present",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.myWatchlistPageTitle_XPATH)));

				log.info("The title '"
						+ driver.findElement(
								By.xpath(XpathObjectRepo.myWatchlistPageTitle_XPATH))
								.getText()
						+ "' is present in My Shows Page");
				int videoCount = driver.findElements(
						By.xpath(XpathObjectRepo.myWatchlistVideoIcon_XPATH))
						.size();

				if (videoCount == 0) {

					
					
					// Verify Featured shows title displaying when no item in
					// my watchlist
					assertTrue(
							"Featured Shows title not present when no items present in My Shows",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.featuredChannelsTitle_XPATH)));

					log.info("Featured Shows title '"
							+ driver.findElement(
									By.xpath(XpathObjectRepo.featuredChannelsTitle_XPATH))
									.getText()
							+ "' is present in My Shows Page when no items present in My Shows");

					// Verifying Shows present in featured Shows section
					int channelCount = driver
							.findElements(
									By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
							.size();
					if (channelCount > 0) {

						//Verify More shows button present in Watchable SHOWS OF THE WEEK section
						assertTrue(
								"More shows button int not present in Watchable SHOWS OF THE WEEK section",
								CommonFun.isElementPresent(
										driver,
										By.xpath(XpathObjectRepo.featuredShowsMoreShowsButton_XPATH)));
						log.info("More shows button is present in Watchable SHOWS OF THE WEEK section");

						}
						log.info("");

					} else {
						log.error("Featured Shows row does not contain Shows");
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
