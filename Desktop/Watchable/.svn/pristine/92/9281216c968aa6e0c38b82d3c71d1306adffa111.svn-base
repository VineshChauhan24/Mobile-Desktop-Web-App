package comcast.test.app.testCases.loginPageAndLogin;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyLogInFormCloseButton Description: This test script verifies
 * clicking on close button from login pop up window closing the login window.
 * Author: Manoj
 * **/

public class VerifyLogInFormCloseButton extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyLogInFormCloseButton() throws Exception {

		try {

			log.info("Script: VerifyLogInFormCloseButton");
			log.info("**********************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Click on Login button from header menu
			LoginFun.clickOnLoginButton();

			// Verify login pop up opened successfully
			assertTrue(
					"Login form is not opened",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.loginForm_XPATH)));
			log.info("Login form opened successfully");

			// Verify Close icon present in login form
			assertTrue("Close icon is not present in login form",
					CommonFun.isElementPresent(driver, By
							.xpath(XpathObjectRepo.loginFormCloseButton_XPATH)));
			log.info("Close icon is present in login form");

			// Close the login form
			LoginFun.clickOnLoginFormCloseIcon();

			// Verify login form is closed successfully
			assertFalse(
					"Login Form is not closed",
					driver.findElement(
							By.xpath(XpathObjectRepo.loginForm_XPATH))
							.isDisplayed());
			log.info("Login Form is closed");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}