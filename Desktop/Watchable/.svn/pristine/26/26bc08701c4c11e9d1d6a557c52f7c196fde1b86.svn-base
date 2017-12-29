package comcast.test.app.testCases.myWatchlist;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.app.testCases.myWatchlist.myWatchlistFunctions.MyWatchlistFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyClickOnWatchableLogoFromMyWatchlistPageNavigatesHomePage
 * Description: This test script verifies clicking on watchable logo from MY
 * Shows page user is taking back to home page. Author: Manoj
 * **/

public class VerifyClickOnWatchableLogoFromMyWatchlistPageNavigatesHomePage
		extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickOnWatchableLogoFromMyWatchlistPageNavigatesHomePage()
			throws Exception {

		try {

			log.info("Script: VerifyClickOnWatchableLogoFromMyWatchlistPageNavigatesHomePage");
			log.info("**********************************************************************");

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

				// Click on Top Watchable logo from My Watch list page to
				// navigate
				// back
				// to home page

				HomeFun.clickOnTopWatchableLogo();

				// Verify user navigate back to home page after clicking on
				// Watchable log from My Shows list page
				AssertionRepoFunctions.assertWatchableTitle();
				log.info("Successfully Navigated to Home page after clicking on Watchable logo from My Shows list Page");

				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
