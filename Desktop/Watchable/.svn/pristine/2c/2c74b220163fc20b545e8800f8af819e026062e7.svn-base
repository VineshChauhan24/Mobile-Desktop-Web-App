package comcast.test.app.testCases.userManagement.profileManagement.RegisteredUserTestCasesForChangePassword;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.profileManagement.common.ProfileManagementScenarioBasedFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;

/**
 * Class Name: ValidateChangePassByEnteringCurrentPassword Description: This
 * test case validates changing password from Profile Management by entering
 * same value in current password, new pass and confirm password fields by
 * registered user into Watchable application.
 * **/

public class ValidateChangePassByEnteringCurrentPassword extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	ProfileManagementScenarioBasedFunctions profMangScenarioFun = new ProfileManagementScenarioBasedFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testValidateChangePassByEnteringCurrentPassword()
			throws Exception {

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);
			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

				// This method asserts Home link ensure Home is Active page when
				// Login into Application.
				assertionFunction.assertHomeActiveLink();

				// This method is to navigate Profile Management Account details
				// page.
				Thread.sleep(sleepTime);
				userLogin.navigateToAccountDetailsPage(driver);

				driver.findElement(
						By.xpath(XpathObjectRepo.CHANGEPASSWORD_XPATH)).click();
				// This method asserts Watchable Logo.
				assertionFunction.assertWatchableLogo();

				// This method is to assert Join Watchable Header in Sign Up
				// page.

				// assertionFunction.assertJoinGazeeboBanner();
				// This method is to assert Join Watchable Header in Sign Up
				// page.
				assertionFunction.assertJoinWatchableBanner();

				profMangScenarioFun.ValidatePassword(driver,
						proUtil.getProperty("PASSWORD"),
						proUtil.getProperty("PASSWORD"),
						proUtil.getProperty("PASSWORD"));

				driver.findElement(
						By.xpath(XpathObjectRepo.RESET_PASSWORD_SUBMIT_XPATH))
						.click();

				Thread.sleep(sleepTime);

				assertTrue(driver
						.findElement(
								By.xpath(XpathObjectRepo.RESET_PASSWORD_ERROR_XPATH))
						.getText().matches(UILablesRepo.RESET_PASSWORD_ERROR));

				// This method is used to logout from application.
				userLogin.LogOut(driver);
			}
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
