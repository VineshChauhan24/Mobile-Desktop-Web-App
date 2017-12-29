package comcast.test.app.testCases.userManagement.profileManagement.RegisteredUserTestCasesForEditProfileDetails.ValidateZipField;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;

/**
 * Class Name: ValidateZipFieldByEnteringCharactersValue Description: This test
 * case validates Zip field by entering characters value by logging registered
 * user into Gazeebo application.
 **/

public class ValidateZipFieldByEnteringCharactersValue extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testValidateZipFieldByEnteringCharactersValue()
			throws Exception {

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

				assertTrue(driver
						.findElement(
								By.xpath(XpathObjectRepo.TOP_MENU_HOME_BUTTON_XPATH))
						.getText().equalsIgnoreCase(UILablesRepo.TOPMENU_HOME));

				// This method is to navigate Profile Management Account details
				// page.
				Thread.sleep(sleepTime);
				userLogin.navigateToAccountDetailsPage(driver);

				// This method asserts Gazeebo Logo.
				assertionFunction.assertWatchableLogo();

				Thread.sleep(sleepTime);

				// This method is to assert Join Gazeebo Header in Sign Up page.
				// assertionFunction.assertJoinGazeeboBanner();

				driver.findElement(
						By.xpath(XpathObjectRepo.ACCOUNT_ZIP_TEXTFLD_XPATH))
						.clear();
				driver.findElement(
						By.xpath(XpathObjectRepo.ACCOUNT_ZIP_TEXTFLD_XPATH))
						.sendKeys(DataServiceProperties._INVALID_ZIP);

				// Click on Save Profile
				driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
						.click();

				assertTrue(driver
						.findElement(
								By.xpath(XpathObjectRepo.ACCOUNT_ZIP_EMPTYFLD_MSG_XPATH))
						.getText()
						.equalsIgnoreCase(
								UILablesRepo.ACCOUNT_ZIP_FLDVALIDATION_MSG));

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
