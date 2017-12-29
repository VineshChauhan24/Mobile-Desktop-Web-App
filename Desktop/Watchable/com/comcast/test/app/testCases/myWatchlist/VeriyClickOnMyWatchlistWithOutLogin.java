package comcast.test.app.testCases.myWatchlist;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.myWatchlist.myWatchlistFunctions.MyWatchlistFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VeriyClickOnMyWatchlistWithOutLogin Description: This test scrip
 * verifies login popup is displaying on clicking My Shows menu, If user is
 * not logged into the application. Author: Manoj
 * **/

public class VeriyClickOnMyWatchlistWithOutLogin extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVeriyClickOnMyWatchlistWithOutLogin() throws Exception {

		try {

			log.info("Script: VeriyClickOnMyWatchlistWithOutLogin");
			log.info("*******************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			Thread.sleep(sleepTime);

			// Verify My Shows menu is present
			assertTrue(
					"My Shows menu is not present",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.myWatchlistMenu_XPATH)));
			log.info("My Shows menu is present");

			// Click on My Shows menu
			MyWatchlistFun.clickOnMyWatchlistMenu();

			// Verify login pop up is opened if user is not logged in to
			// application
			assertTrue(
					"Login pop up is not opened on clicking My Shows menu if user is not logged in to application",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.loginForm_XPATH)));
			log.info("Login pop up is opened on clicking My Shows menu if user is not logged in to application");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
