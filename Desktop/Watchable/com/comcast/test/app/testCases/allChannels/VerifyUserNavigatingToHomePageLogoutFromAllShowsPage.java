package comcast.test.app.testCases.allChannels;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyUserNavigatingToHomePageLogoutFromAllShowsPage
 * Description:This test case verifies user is navigating back to home page if
 * user logout from all shows page. 
 * Author: Manoj
 * **/

public class VerifyUserNavigatingToHomePageLogoutFromAllShowsPage extends
		BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	boolean channelPresent = false;

	@Test
	public void testVerifyUserNavigatingToHomePageLogoutFromAllShowsPage()
			throws Exception {

		try {

			log.info("Script: VerifyUserNavigatingToHomePageLogoutFromAllShowsPage");
			log.info("************************************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Login to Watchable application
			LoginFun.loginToWatchableApplication(driver, UILablesRepo.EMAIL,
					UILablesRepo.PASSWORD);
			WebElement loginError = driver.findElement(By
					.id(XpathObjectRepo.loginError_ID));

			if (loginError.isDisplayed() == false) {

				// Verify Browse Shows menu is present
				assertTrue(
						"Browse Shows menu is not present",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.allChannelsMenu_XPATH)));

				log.info("Browse Shows menu '"
						+ driver.findElement(
								By.xpath(XpathObjectRepo.allChannelsMenu_XPATH))
								.getText() + "' is present");

				// Click on Browse Shows Menu
				driver.findElement(
						By.xpath(XpathObjectRepo.allChannelsMenu_XPATH))
						.click();
				log.info("Successfully clicked on Browse Shows Menu");
				Thread.sleep(sleepTime);

				// Verify User Successfully Navigated to Shows by genre page

				assertTrue(
						"User is not Navigated to all Shows page after clicking ALL Shows menu",
						driver.getTitle().contains(
								UILablesRepo.ALL_CHANNEL_TITLE));
				log.info("The All Shows page title '" + driver.getTitle()
						+ "' is displayed");

				// Click on Top Watchable logo from all shows page to navigate
				// back
				// to home page

				// Logout from Watchable Application.
				LoginFun.logOut(driver);

				// Verify user is navigate back to Home Page after logout
				// from
				// My Shows Page
				AssertionRepoFunctions.assertWatchableTitle();
				log.info("Successfully navigate back to Home Page after logout from All Shows Page");

				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
