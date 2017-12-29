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
 * Class Name: VerifyForgotPasswordPageDetailsWithEmptyFields Description: This
 * test case is used to validate empty Email text box on forgot password form
 * Author: Manoj
 * **/

public class VerifyForgotPasswordPageDetailsWithEmptyFields extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyForgotPasswordPageDetailsWithEmptyFields()
			throws Exception {

		try {

			log.info("Script: VerifyForgotPasswordPageDetailsWithEmptyFields");
			log.info("******************************************************");

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

			// Verify FORGOT YOUR PASSWORD link present in login form
			assertTrue(
					"FORGOT YOUR PASSWORD link is not present in login form",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.loginFormForgotPassLink_XPATH)));
			log.info("FORGOT YOUR PASSWORD link is present in login form");

			// Click on FORGOT YOUR PASSWORD link

			LoginFun.clickOnForgetPasswordLinkFormLoginForm();

			// Verify forgot password pop up opened successfully
			assertTrue(
					"Forgot password form is not opened",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.forgotPasswordForm_XPATH)));
			log.info("Forgot password form opened successfully");

			// Ensure Email text box is displayed with blank value

			assertEquals(
					"Email text box is not blank",
					"",
					driver.findElement(
							By.id(XpathObjectRepo.forgotPasswordFormEmailText_ID))
							.getAttribute("value"));

			log.info("Email text box is blank");

			// Close the forgot password form
			LoginFun.clickOnForgotPasswordFormCloseIcon();

			// Verify forgot password form is closed successfully
			int forgotPassForm = driver.findElements(
					By.xpath(XpathObjectRepo.forgotPasswordForm_XPATH)).size();
			if (forgotPassForm > 0) {
				assertFalse(
						"Forgot password Form is not closed",
						driver.findElement(
								By.xpath(XpathObjectRepo.forgotPasswordForm_XPATH))
								.isDisplayed());
				log.info("Forgot password Form is closed");
			} else {
				log.info("Forgot password Form is closed");
			}

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}