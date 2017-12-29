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
 * Class Name: ValidateCurrentPasswordWhenChangingPassword Description: This
 * test case validates reset password functionality by entering wrong value in
 * old password field. Author: Manoj
 * **/

public class ValidateCurrentPasswordWhenChangingPassword extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testValidateChangePassByEnteringCurrentPassword()
			throws Exception {

		try {

			log.info("Script: ValidateCurrentPasswordWhenChangingPassword");
			log.info("***************************************************");

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

					// Enter invalid old password value
					LoginFun.enterOldNewConfirmPasswordDetails(driver,
							UILablesRepo.INVALID_PASSWORD,
							UILablesRepo.NEW_PASSWORD,
							UILablesRepo.NEW_PASSWORD);

					// Click on Update button by keeping all fields blank
					LoginFun.clickOnResetPasswordFormUpdateButton();

					// Verify Invalid old Password error message
					assertTrue(
							"Invalid old Password Error Message is not displayed",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.resetPasswordFormInvalidOldPasswordError_XPATH)));
					log.info("Invalid old Error Message '"
							+ driver.findElement(
									By.xpath(XpathObjectRepo.resetPasswordFormInvalidOldPasswordError_XPATH))
									.getText() + "' is displayed");

					// Logout from Watchable Application.
					// LoginFun.logOut(driver);

					log.info("");
				}

				else {
					log.error("Failed to open  Reset Passwordn form...!");
				}
			}
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}