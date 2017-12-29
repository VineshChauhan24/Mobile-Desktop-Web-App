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
 * Class Name: VerifyLoginPageDetailsWithEmptyFields Description: This test case
 * is used to validate empty Email and Password text boxes on Login form.
 * Author: Manoj
 * **/

public class VerifyLoginPageDetailsWithEmptyFields extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyLoginPageDetailsWithEmptyFields() throws Exception {

		try {

			log.info("Script: VerifyLoginPageDetailsWithEmptyFields");
			log.info("*********************************************");

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

			// Ensure Email text box is displayed with blank value

			assertEquals(
					"Email text box is not blank",
					"",
					driver.findElement(
							By.id(XpathObjectRepo.loginFormEmailText_ID))
							.getAttribute("value"));

			log.info("Email text box is blank");

			// Ensure Password text box is displayed with blank value

			assertEquals(
					"Password text box is not blank",
					"",
					driver.findElement(
							By.id(XpathObjectRepo.loginFormPasswordText_ID))
							.getAttribute("value"));

			log.info("Password text box is blank");

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