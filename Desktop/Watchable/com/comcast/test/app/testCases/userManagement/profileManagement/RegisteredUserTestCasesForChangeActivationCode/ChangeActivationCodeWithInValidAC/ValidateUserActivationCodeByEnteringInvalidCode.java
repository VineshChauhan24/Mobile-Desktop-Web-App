package comcast.test.app.testCases.userManagement.profileManagement.RegisteredUserTestCasesForChangeActivationCode.ChangeActivationCodeWithInValidAC;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;

/**
 * Class Name: ValidateUserActivationCodeByEnteringInvalidCode Description: This
 * test case validates Activation code by entering invalid code by logging
 * registered user into Gazeebo application.
 * **/

public class ValidateUserActivationCodeByEnteringInvalidCode extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testValidateUserActivationCodeByEnteringInvalidCode()
			throws Exception {

		try {
			/*
			 * This Method is to register new user using Gazeebo application and
			 * to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method asserts Home link ensure Home is Active page when
			// Login into Application.
			assertionFunction.assertHomeActiveLink();

			// This method is to navigate Profile Management Account details
			// page.
			Thread.sleep(sleepTime);
			userLogin.navigateToAccountDetailsPage(driver);

			driver.findElement(By.linkText("Change activation code")).click();

			// This method asserts Gazeebo Logo.
			assertionFunction.assertWatchableLogo();

			// This method is to assert Join Gazeebo Header in Sign Up page.

			// assertionFunction.assertJoinGazeeboBanner();
			// This method is to assert Join Gazeebo Header in Sign Up page.
			assertionFunction.assertJoinWatchableBanner();

			driver.findElement(By.name("user[activation_code]")).clear();
			driver.findElement(By.name("user[activation_code]")).sendKeys(
					proUtil.getProperty("INVALID_ACTIVATION_CODE"));

			// Click on Activate Button.
			driver.findElement(By.name("commit")).click();

			Thread.sleep(sleepTime);
			assertEquals(
					"Activation code "
							+ proUtil.getProperty("INVALID_ACTIVATION_CODE")
							+ " is invalid",
					driver.findElement(By.cssSelector("label.error")).getText());

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Copy Right Links.
			assertionFunction.assertFooterCopyRight();

			// This method is used to logout from application.
			userLogin.LogOut(driver);

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
