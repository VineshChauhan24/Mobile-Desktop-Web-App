package comcast.test.app.testCases.userManagement.profileManagement.RegisteredUserTestCasesForChangePassword;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.LoginFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.TestDataGenerator;
import comcast.test.config.dataServices.userRegistration.UserRegistrationUsingComcast;

/**
 * Class Name: VerifyChangePasswordFunctionality Description: This test case
 * validates whether the reset password functionality works fine from Profile
 * Management by registered user into Watchable application.
 * **/

public class VerifyChangePasswordFunctionality extends BaseTest {

	UserLoginFunctions userLogFun = new UserLoginFunctions();
	UserRegistrationUsingComcast userReg = new UserRegistrationUsingComcast();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	LoginFunctions loginFuntion = new LoginFunctions();
	int loginError;

	@Test
	public void testVerifyChangePasswordFunctionality() throws Exception {

		try {
			TestDataGenerator proUtil = new TestDataGenerator();
			proUtil.load(new FileInputStream(new File("com/data.properties")));

			// This method is used to register new user into Watchable
			// Application
			// userReg.testUserRegistrationUsingComcast(driver);

			driver.get(proUtil.getProperty("HOMEAPPURL"));

			// This method is to ensure Home is Active page before Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method is to navigate Log In page details.
			loginFuntion.navigateToLoginPage();

			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.LOGIN_PAGE_EMAILHEADER_XPATH))
					.getText().equalsIgnoreCase(UILablesRepo.LOGIN_PAGE_EMAIL));

			Thread.sleep(sleepTime);
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.LOGIN_PAGE_PASSWDHEADER_XPATH))
					.getText().equalsIgnoreCase(UILablesRepo.LOGIN_PAGE_PASSWD));

			// This method is used to enter user name and password credentials
			userLogFun.UserLoginCredentials(driver);

			driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
					.click();
			Thread.sleep(sleepTime);

			loginError = driver
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
				userLogFun.navigateToAccountDetailsPage(driver);

				driver.findElement(
						By.xpath(XpathObjectRepo.CHANGEPASSWORD_XPATH)).click();

				// This method is used to Change Password
				userLogin.ChangePassword(driver,
						DataServiceProperties._PASSWORD,
						DataServiceProperties._RESET_PASSWORD,
						DataServiceProperties._CONFIRM_PASSWORD);

				driver.findElement(
						By.xpath(XpathObjectRepo.FORGOT_PASSWORD_SUBMITBTN_XPATH))
						.click();

				TestDataGenerator.updateResetPasswordDataPropertiesFields();

				// This method is used to logout from application.
				userLogin.LogOut(driver);
			}

			// This method is to navigate Log In page details.
			if (!driver
					.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
					.getAttribute("class").contains("active")) {
				loginFuntion.navigateToLoginPage();
			}

			// This method is used to enter UserName with new valid Password
			userLogFun.UserLoginCredentials(driver);
			driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
					.click();
			Thread.sleep(sleepTime);
			loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

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
