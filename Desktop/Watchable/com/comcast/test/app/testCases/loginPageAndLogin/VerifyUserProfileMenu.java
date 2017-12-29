package comcast.test.app.testCases.loginPageAndLogin;

import static org.junit.Assert.*;

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
 * Class Name: VerifyUserProfileMenu Description: This test case verifies the
 * user profile drop down menu contains the options 'SETTINGS' and "LOGOUT'.
 * Author: Manoj
 * **/

public class VerifyUserProfileMenu extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyUserProfileMenu() throws Exception {

		try {

			log.info("Script: VerifyUserProfileMenu");
			log.info("*******************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Login to Watchable application using valid password
			LoginFun.loginToWatchableApplication(driver, UILablesRepo.EMAIL,
					UILablesRepo.PASSWORD);
			WebElement loginError = driver.findElement(By
					.id(XpathObjectRepo.loginError_ID));

			if (loginError.isDisplayed() == false) {

				// Mouse over on user profile icon

				CommonFun.mouseOverElement(driver, driver.findElement(By
						.xpath(XpathObjectRepo.profileIcon_XPATH)));
				Thread.sleep(sleepTime);

				// Verify user profile drop down menu contains 'Settings' option
				assertTrue(
						"User profile drop down menu does not contains 'Settings' option",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.settingsButton_XPATH)));
				log.info("User profile drop down menu contains 'Settings' option");

				// Verify user profile drop down menu contains 'Log Out' option
				assertTrue(
						"User profile drop down menu does not contains 'Log Out' option",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.logoutButton_XPATH)));
				log.info("User profile drop down menu contains 'Log Out' option");

				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}