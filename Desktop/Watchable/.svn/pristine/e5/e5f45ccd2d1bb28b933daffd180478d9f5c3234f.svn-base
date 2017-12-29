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
 * Class Name: VerifyNavigatingToChannelPageFromMyWatchlist Description:This
 * test case verifies navigating to show page by clicking on the popular shows
 * section, where there are no videos in My Shows page. Author: Manoj
 * **/

public class VerifyNavigatingToChannelPageFromMyWatchlist extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyNavigatingToChannelPageFromMyWatchlist()
			throws Exception {

		try {

			log.info("Script: VerifyNavigatingToChannelPageFromMyWatchlist");
			log.info("****************************************************");

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
					// my Shows
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

					// Verify check Popular shows displaying when no item in
					// my Shows
					assertTrue(
							"Check popular shows message not present when no items present in My Shows",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.myWatchlistPageErrorSubTitle_XPATH)));

					log.info("The Message '"
							+ driver.findElement(
									By.xpath(XpathObjectRepo.myWatchlistPageErrorSubTitle_XPATH))
									.getText()
							+ "' is present in My Shows Page when no items present in My Shows");

					// Verify Popular shows title displaying when no item in
					// my Shows
					assertTrue(
							"Popular  Shows title not present when no items present in My Shows",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.featuredChannelsTitle_XPATH)));

					log.info("Popular Shows title '"
							+ driver.findElement(
									By.xpath(XpathObjectRepo.featuredChannelsTitle_XPATH))
									.getText()
							+ "' is present in My Shows Page when no items present in My Shows");

					// Verifying channel present in featured channel section
					int channelCount = driver
							.findElements(
									By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
							.size();
					if (channelCount > 0) {

						// Navigating to channel page
						for (int i = 1; i <= channelCount; i++) {
							driver.findElement(
									By.xpath(XpathObjectRepo.featuredChannelTitle_XPATH
											+ i + "]/a")).click();

							if (driver
									.findElements(
											By.xpath(XpathObjectRepo.featuredChannelTitle_XPATH
													+ i + "]/a")).size() > 0) {
								log.info("Clicked on the channel "
										+ driver.findElement(
												By.xpath(XpathObjectRepo.featuredChannelTitle_XPATH
														+ i + "]/a")).getText());
							}
							Thread.sleep(sleepTime);

							// Verify success fully navigated channel page
							AssertionRepoFunctions.assertChannelPageTitle();

							// Navigate back to My Watchlist page
							CommonFun.navigateBack(driver);
							i = i + 1;

						}
						log.info("");

					} else {
						log.error("Popular Shows row does not contain shows");
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
