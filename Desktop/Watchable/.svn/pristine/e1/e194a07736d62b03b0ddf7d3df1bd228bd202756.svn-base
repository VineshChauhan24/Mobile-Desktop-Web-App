package comcast.test.app.testCases.loginPageAndLogin;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyForgotPasswordFunctionalityWithValidEmail Description: This
 * test case is to verify Forgot Password functionality by providing valid
 * Email. Author: Manoj
 * **/

public class VerifyForgotPasswordFunctionalityWithValidEmail extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyForgotPasswordFunctionalityWithValidEmail()
			throws Exception {

		try {

			log.info("Script: VerifyForgotPasswordFunctionalityWithValidEmail");
			log.info("*******************************************************");

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

			// Enter invalid formated email id

			driver.findElement(
					By.id(XpathObjectRepo.forgotPasswordFormEmailText_ID))
					.clear();
			driver.findElement(
					By.id(XpathObjectRepo.forgotPasswordFormEmailText_ID))
					.sendKeys(UILablesRepo.FORGOT_PASS_EMAIL);

			log.info("Entered the valid Email ID: "
					+ UILablesRepo.FORGOT_PASS_EMAIL);

			// Click on Continue button after entering invalid email field blank
			LoginFun.clickOnForgotPasswordFormContinueButton();

			// Verify Forgot password confirmation message
			assertTrue(
					"Forgot password confirmation Message is not displayed",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.forgotPasswordConfirmMsg)));
			log.info("Forgot password confirmation Message '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.forgotPasswordConfirmMsg))
							.getText() + "' is displayed");

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