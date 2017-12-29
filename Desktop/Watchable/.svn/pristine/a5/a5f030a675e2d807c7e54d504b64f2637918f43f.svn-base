package comcast.test.app.testCases.myWatchlist;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.channelPage.channelPageFunctions.ChannelPageFun;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.app.testCases.myWatchlist.myWatchlistFunctions.MyWatchlistFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyNavigatingToShowsPageFromFeaturedShowsSectionOfMyShows
 * Description:This test case verifies navigating to shows page from Watchable
 * SHOWS OF THE WEEK section when video are not present in MYSHOWS page. Author:
 * Manoj
 * **/

public class VerifyNavigatingToShowsPageFromFeaturedShowsSectionOfMyShows
		extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyNavigatingToShowsPageFromFeaturedShowsSectionOfMyShows()
			throws Exception {

		try {

			log.info("Script: VerifyNavigatingToShowsPageFromFeaturedShowsSectionOfMyShows");
			log.info("********************************************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			Thread.sleep(sleepTime);

			// Login to Watchable application
			LoginFun.loginToWatchableApplication(driver,
					UILablesRepo.NO_WATCHLIST_USER, UILablesRepo.PASSWORD);
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

				// Verify my Shows list page title
				assertTrue(
						"My Shows menu is not present",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.myWatchlistPageTitle_XPATH)));

				log.info("The title '"
						+ driver.findElement(
								By.xpath(XpathObjectRepo.myWatchlistPageTitle_XPATH))
								.getText() + "' is present in My Shows Page");
				int videoCount = driver.findElements(
						By.xpath(XpathObjectRepo.myWatchlistVideoIcon_XPATH))
						.size();

				if (videoCount == 0) {

					// Verify Not following message displaying when no item in
					// my watchlist
					assertTrue(
							"Not following any Shows message not present when no items present in My Shows",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.myWatchlistPageErrorTitle_XPATH)));

					log.info("The Message '"
							+ driver.findElement(
									By.xpath(XpathObjectRepo.myWatchlistPageErrorTitle_XPATH))
									.getText()
							+ "' is present in My Shows Page when no items present in My Shows");

					// Verify check featured Shows displaying when no item in
					// my watchlist
					assertTrue(
							"Check featured Shows message not present when no items present in My Shows",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.myWatchlistPageErrorSubTitle_XPATH)));

					log.info("The Message '"
							+ driver.findElement(
									By.xpath(XpathObjectRepo.myWatchlistPageErrorSubTitle_XPATH))
									.getText()
							+ "' is present in My Shows Page when no items present in My Shows");

					// Verify Watchable SHOWS OF THE WEEK title displaying when
					// no item in
					// my watchlist
					assertTrue(
							"Watchable SHOWS OF THE WEEK title not present when no items present in My Shows",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.featuredChannelsTitle_XPATH)));

					log.info("Watchable SHOWS OF THE WEEK title '"
							+ driver.findElement(
									By.xpath(XpathObjectRepo.featuredChannelsTitle_XPATH))
									.getText()
							+ "' is present in My Shows Page when no items present in My Shows");

					// Verifying Shows present in Watchable SHOWS OF THE WEEK
					// section
					int channelCount = driver
							.findElements(
									By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
							.size();
					if (channelCount > 0) {

						log.info(channelCount
								+ " Shows present in Watchable SHOWS OF THE WEEK section");

						// Navigate to show page on clicking first shows title
						// from featured shows section
						// Click on first channel from featured Channel section

						ChannelPageFun.clickOnFirstFeaturedChannelLink();

						// Verify successfully navigate to channel details page
						AssertionRepoFunctions.assertChannelPageTitle();

						log.info("");

					} else {
						log.error("Watchable SHOWS OF THE WEEK row does not contain Shows");
						log.info("");
					}
				}
			}
		}

		catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}

	}
}
