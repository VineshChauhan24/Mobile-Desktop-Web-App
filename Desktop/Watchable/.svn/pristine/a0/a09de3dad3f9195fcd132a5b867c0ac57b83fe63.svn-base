package comcast.test.app.testCases.myWatchlist;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.app.testCases.myWatchlist.myWatchlistFunctions.MyWatchlistFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyNavigatingToMyWatchlistAfterLoginFromPopup Description:
 * This test case verifies navigation to My Shows page after login to login
 * pop up when user clicked on My Shows menu, If user is not logged in to
 * the application. Author: Manoj
 * **/

public class VerifyNavigatingToMyWatchlistAfterLoginFromPopup extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyNavigatingToMyWatchlistAfterLoginFromPopup()
			throws Exception {

		try {

			log.info("Script: VerifyNavigatingToMyWatchlistAfterLoginFromPopup");
			log.info("********************************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			Thread.sleep(sleepTime);

			// Verify My Shows menu is present
			assertTrue(
					"My Show menu is not present",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.myWatchlistMenu_XPATH)));
			log.info("My Show menu is present");

			// Click on My Shows menu
			MyWatchlistFun.clickOnMyWatchlistMenu();

			// Verify login pop up is opened if user is not logged in to
			// application
			assertTrue(
					"Login pop up is not opened on clicking My Watchlist menu if user is not logged in to application",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.loginForm_XPATH)));
			log.info("Login pop up is opened on clicking My Watchlist menu if user is not logged in to application");

			// Login to Shows application using invalid password
			LoginFun.loginToWatchable(driver, UILablesRepo.EMAIL,
					UILablesRepo.PASSWORD);

			// Verify user is navigated to My Shows list page
			AssertionRepoFunctions.assertMyWatchlistPageTitle();

			// Verify My Shows list page title

			assertTrue("My Shows menu is not present",
					CommonFun.isElementPresent(driver, By
							.xpath(XpathObjectRepo.myWatchlistPageTitle_XPATH)));

			log.info("The title '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.myWatchlistPageTitle_XPATH))
							.getText() + "' is present in My Shows Page");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
