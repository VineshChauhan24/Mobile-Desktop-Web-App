package comcast.test.app.testCases.userManagement.profileManagement.RegisteredUserTestCasesForChangePassword;

import static org.junit.Assert.assertEquals;

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
 * Class Name: VerifyChangePasswordMandatoryFields Description: This test case
 * validates changing password mandatory fields from Profile Management by
 * registered user into Gazeebo application.
 * **/

public class VerifyChangePasswordMandatoryFields extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	ProfileManagementScenarioBasedFunctions profMangScenarioFun = new ProfileManagementScenarioBasedFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testVerifyChangePasswordMandatoryFields() throws Exception {

		try {
			/*
			 * This Method is to register new user using Gazeebo application and
			 * to change a password.
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
				assertionFunction.assertWatchableLogo();

				// This method is to assert Join Gazeebo Header in Sign Up page.

				// assertionFunction.assertJoinGazeeboBanner();
				// This method is to assert Join Gazeebo Header in Sign Up page.
				assertionFunction.assertJoinWatchableBanner();

				driver.findElement(
						By.xpath(XpathObjectRepo.FORGOT_PASSWORD_SUBMITBTN_XPATH))
						.click();

				Thread.sleep(sleepTime);
				/*
				 * assertEquals("This field is required.",driver.findElement(By.
				 * xpath
				 * (".//*[@id='step-0']/fieldset[1]/div[2]/label/label")).getText
				 * ());
				 * assertEquals("This field is required.",driver.findElement
				 * (By.xpath
				 * (".//*[@id='step-0']/fieldset[2]/div[2]/label/label")
				 * ).getText());
				 * assertEquals("This field is required.",driver.findElement
				 * (By.xpath
				 * (".//*[@id='step-0']/fieldset[3]/div[2]/label/label")
				 * ).getText());
				 */

				assertEquals(
						UILablesRepo.FORGOT_PASSWORD_EMPTYFIELD_WARNINGMSG,
						driver.findElement(
								By.xpath(XpathObjectRepo.FORGOT_PASSWORD_EMPTYFIELD_WARNINGMSG_XPATH))
								.getText());

				// This method is used to logout from application.
				userLogin.LogOut(driver);
			}
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
