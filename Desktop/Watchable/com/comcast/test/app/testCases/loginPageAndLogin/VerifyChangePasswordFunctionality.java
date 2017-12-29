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
 * Class Name: VerifyChangePasswordFunctionality Description: This test case
 * validates whether the reset password functionality works fine. Author: Manoj
 * **/

public class VerifyChangePasswordFunctionality extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyChangePasswordFunctionality() throws Exception {

		try {

			log.info("Script: VerifyChangePasswordFunctionality");
			log.info("*****************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Login to Watchable application
			LoginFun.loginToWatchableApplication(driver,
					UILablesRepo.RESET_PASS_EMAIL, UILablesRepo.PASSWORD);

			WebElement loginError = driver.findElement(By
					.id(XpathObjectRepo.loginError_ID));

			if (loginError.isDisplayed() == false) {

				// Select Settings Menu

				LoginFun.selectSettingsMenu();
				if (driver.findElement(
						By.xpath(XpathObjectRepo.resetPasswordForm_XPATH))
						.isDisplayed() == true) {

					// Verify Reset Password form is opened successfully

					assertTrue(
							"Reset Password form is not opened",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.resetPasswordForm_XPATH)));
					log.info("Reset Passwordn form opened successfully");

					// Enter different correct old password and same valid
					// values in
					// new and confirm passwords
					LoginFun.enterOldNewConfirmPasswordDetails(driver,
							UILablesRepo.PASSWORD, UILablesRepo.NEW_PASSWORD,
							UILablesRepo.NEW_PASSWORD);

					// Click on Update button
					LoginFun.clickOnResetPasswordFormUpdateButton();

					// Verify Password change confirmation message
					assertTrue(
							"Password updated confirm Message is not displayed",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.resetPasswordFormConfirmMsg_XPATH)));
					log.info("Password updated confirm Message '"
							+ driver.findElement(
									By.xpath(XpathObjectRepo.resetPasswordFormConfirmMsg_XPATH))
									.getText() + "' is displayed");

					// Logout from Watchable Application.
					//LoginFun.logOut(driver);

					log.info("");
				}
				else
				{
					log.error("Failed to open  Reset Passwordn form...!");
				}

				}
			

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}