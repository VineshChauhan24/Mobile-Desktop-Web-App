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
 * Class Name: ValidateNewPasswordAndConfirmPasswordMatch Description: This test
 * case validates whether New and Confirm password matches for registered user
 * into Gazeebo application.
 * **/

public class ValidateNewPasswordAndConfirmPasswordMatch extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	ProfileManagementScenarioBasedFunctions profMangScenarioFun = new ProfileManagementScenarioBasedFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testValidateNewPasswordAndConfirmPasswordMatch()
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

				// This method asserts Gazeebo Logo.
				// assertionFunction.assertGazeeboLogo();

				// This method is to assert Join Gazeebo Header in Sign Up page.
				assertionFunction.assertJoinWatchableBanner();

				// This method is used to validate whether new and confirm
				// password matches
				profMangScenarioFun.VerifyNewPassAndConfirmPassMatch(driver,
						proUtil.getProperty("PASSWORD"),
						proUtil.getProperty("PASSWORD"),
						proUtil.getProperty("INVALID_PASSWORD_LENGTH"));

				Thread.sleep(sleepTime);
				assertTrue(driver
						.findElement(
								By.xpath(XpathObjectRepo.RESETPASSMISMATCHWARNING_XPATH))
						.getText()
						.equalsIgnoreCase(UILablesRepo.RESETPASSMISMATCHWARNING));

				// This method asserts Home and My Channels inactive link when
				// user clicks on Bundle/Channel/Show.
				assertionFunction.assertAllInActiveLink();

				// This method asserts Gazeebo Logo.
				assertionFunction.assertWatchableLogo();

				// This method is used to logout from application.
				userLogin.LogOut(driver);
			}

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			// assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
