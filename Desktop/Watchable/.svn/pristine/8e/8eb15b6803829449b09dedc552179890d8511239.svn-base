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
 * Class Name: VeriyLogInPageContent Description: This test case verifies the
 * content of login form Author: Manoj
 * **/

public class VeriyLogInPageContent extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVeriyLogInPageContent() throws Exception {

		try {

			log.info("Script: VeriyLogInPageContent");
			log.info("*****************************");

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

			// Verify title present in login form
			assertTrue(
					"Login form does not contain title",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.loginFormTitle_XPATH)));
			log.info("The title '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.loginFormTitle_XPATH))
							.getText() + "' Present in login form");

			// Verify Close icon present in login form
			assertTrue("Close icon is not present in login form",
					CommonFun.isElementPresent(driver, By
							.xpath(XpathObjectRepo.loginFormCloseButton_XPATH)));
			log.info("Close icon is present in login form");

			// Verify email text box present in login form
			assertTrue(
					"Email text box is not present in login form",
					CommonFun.isElementPresent(driver,
							By.id(XpathObjectRepo.loginFormEmailText_ID)));
			log.info("Email text box is present in login form");

			// Verify password text box present in login form
			assertTrue(
					"Password text box is not present in login form",
					CommonFun.isElementPresent(driver,
							By.id(XpathObjectRepo.loginFormPasswordText_ID)));
			log.info("Password text box is present in login form");

			// Verify Remember me check box present in login form
			assertTrue(
					"Remember me check box is not present in login form",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.loginFormRememberMeCheckBox_XPATH)));
			log.info("Remember me check box is present in login form");

			// Verify Remember me label present in login form
			assertTrue(
					"Remember me label is not present in login form",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.loginFormRememberMeLabel_XPATH)));
			log.info("Remember me label is present in login form");

			// Verify FORGOT YOUR PASSWORD link present in login form
			assertTrue(
					"FORGOT YOUR PASSWORD link is not present in login form",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.loginFormRememberMeLabel_XPATH)));
			log.info("FORGOT YOUR PASSWORD link is present in login form");

			// Verify Login button present in login form
			assertTrue(
					"Login button is not present in login form",
					CommonFun.isElementPresent(driver,
							By.id(XpathObjectRepo.loginFormLoginButton_ID)));
			log.info("Login button is present in login form");

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