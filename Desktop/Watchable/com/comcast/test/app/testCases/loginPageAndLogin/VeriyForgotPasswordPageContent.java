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
 * Class Name: VeriyForgotPasswordPageContent Description: This test case
 * verifies the content of forgot password form Author: Manoj
 * **/

public class VeriyForgotPasswordPageContent extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVeriyForgotPasswordPageContent() throws Exception {

		try {

			log.info("Script: VeriyForgotPasswordPageContent");
			log.info("**************************************");

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

			// Verify title present in forgot password form
			assertTrue(
					"Forgot password form does not contain title",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.forgotPasswordFormTitle_XPATH)));
			log.info("The title '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.forgotPasswordFormTitle_XPATH))
							.getText() + "' Present in forgot password form");

			// Verify Close icon present in forgot password form
			assertTrue(
					"Close icon is not present in forgot password form",
					CommonFun.isElementPresent(
							driver,
							By.id(XpathObjectRepo.forgotPasswordFormCloseButton_ID)));
			log.info("Close icon is present in forgot password form");

			// Verify email text box present in login form
			assertTrue(
					"Email text box is not present in forgot password form",
					CommonFun.isElementPresent(driver, By
							.id(XpathObjectRepo.forgotPasswordFormEmailText_ID)));
			log.info("Email text box is present in forgot password form");

			// Verify continue button present in forgot password form
			assertTrue(
					"Continue button is not present in forgot password form",
					CommonFun.isElementPresent(
							driver,
							By.id(XpathObjectRepo.forgotPasswordFormContinueButton_ID)));
			log.info("Continue button is present in forgot password form");

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