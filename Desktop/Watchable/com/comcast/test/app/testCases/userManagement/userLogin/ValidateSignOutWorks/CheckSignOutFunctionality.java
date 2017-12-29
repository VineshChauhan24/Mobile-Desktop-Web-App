package comcast.test.app.testCases.userManagement.userLogin.ValidateSignOutWorks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.dataServices.userRegistration.UserRegistrationUsingComcast;

/**
 * Class Name: CheckSignOutFunctionality Description: This test case validates
 * if 'SignOut' feature works properly for registered user in Gazeebo
 * application.
 **/

public class CheckSignOutFunctionality extends BaseTest {

	UserRegistrationUsingComcast userRegDS = new UserRegistrationUsingComcast();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testCheckSignOutFunctionality() throws Exception,
			AssertionError {

		// This method is used to register new user into Watchable Application
		// userRegDS.testUserRegistrationUsingComcast(driver);
		driver.get(proUtil.getProperty("APPURL"));

		try {
			// This method is used to enter user name and password credentials
			userLogin.UserLoginCredentials(driver);

			driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
					.click();
			Thread.sleep(sleepTime);
			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

				// This method is to ensure Home page is Active page.
				assertionFunction.assertHomeActiveLink();

				// This method is used to logout from application.
				userLogin.LogOut(driver);
			}

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
