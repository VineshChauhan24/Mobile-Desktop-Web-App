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
 * Class Name: VerifyLoginPageMandatoryFields Description: This test case try to
 * login to Watchable application by keeping user name and passowrd as blank
 * Author: Manoj
 * **/

public class VerifyLoginPageMandatoryFields extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyLoginPageMandatoryFields() throws Exception {

		try {

			log.info("Script: VerifyLoginPageMandatoryFields");
			log.info("********************************************");

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

			// Click on login button by keeping email and password field blank
			LoginFun.clickOnLoginButtonFormLoginForm();

			// Verify Email error message
			assertTrue(
					"Email Error Message is not displayed",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.loginEmailError_XPATH)));
			log.info("Email Error Message '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.loginEmailError_XPATH))
							.getText() + "' is displayed");

			// Verify Password error message
			assertTrue(
					"Password Error Message is not displayed",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.loginPasswordError_XPATH)));
			log.info("Password Error Message '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.loginPasswordError_XPATH))
							.getText() + "' is displayed");

			log.info("Login to watchable application failed with blank user name and password");

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