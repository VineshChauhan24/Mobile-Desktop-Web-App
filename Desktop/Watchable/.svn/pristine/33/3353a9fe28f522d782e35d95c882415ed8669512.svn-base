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
 * Class Name: VerifyResetPasswordFormCloseButton Description: This test script
 * verifies clicking on close button from reset password pop up window closing
 * the reset password window. Author: Manoj
 * **/

public class VerifyResetPasswordFormCloseButton extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyResetPasswordFormCloseButton() throws Exception {

		try {

			log.info("Script: VerifyResetPasswordFormCloseButton");
			log.info("******************************************");

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
				
				if(driver.findElement(
								By.xpath(XpathObjectRepo.resetPasswordForm_XPATH))
								.isDisplayed() == true){

				// Verify Reset Password form is opened successfully
				assertTrue(
						"Reset Password form is not opened",
						CommonFun.isElementPresent(driver, By
								.xpath(XpathObjectRepo.resetPasswordForm_XPATH)));
				log.info("Reset Passwordn form opened successfully");

				// Verify Close icon present in Reset password form
				assertTrue(
						"Close icon is not present in Reset password form",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.resetPasswordFormCloseButton_XPATH)));
				log.info("Close icon is present in Reset password form");

				// Close the reset password form
				LoginFun.clickOnResetPasswordFormCloseIcon();

				// Verify Reset password form is closed successfully

				assertFalse(
						"Reset password Form is not closed",
						driver.findElement(
								By.xpath(XpathObjectRepo.resetPasswordForm_XPATH))
								.isDisplayed());

				log.info("Reset password Form is closed");
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