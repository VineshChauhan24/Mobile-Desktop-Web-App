package comcast.test.app.testCases.userManagement.userLogin.ValidateUserLoginUsingValidCredentials;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.testng.Reporter;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.LoginFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

import org.apache.log4j.Logger;

/**
 * Class Name: LoginToGazeeboWithValidCredentials Description: This test case is
 * used to login to Watchable application with valid UserName and Password for
 * registered user.
 * **/

public class LoginToWatchableWithValidCredentials1 extends BaseTest {

	// Manoj: Code refactoring done

	LoginFunctions loginFuntion = new LoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testLoginToWatchableWithValidCredentials() throws Exception {

		try {

			driver.get(DataServiceProperties.HOMEAPPURL);

			// This method is to navigate Log In page details.
			loginFuntion.navigateToLoginPage();

			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.LOGIN_PAGE_EMAILHEADER_XPATH))
					.getText().equalsIgnoreCase(UILablesRepo.LOGIN_PAGE_EMAIL));

			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.LOGIN_PAGE_PASSWDHEADER_XPATH))
					.getText().equalsIgnoreCase(UILablesRepo.LOGIN_PAGE_PASSWD));

			// This method is used to enter UserName and Password
			loginFuntion.LoginToXidioApplication(driver, UILablesRepo.USERNAME,
					UILablesRepo.PASSWORD);

			driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
					.click();
			Thread.sleep(sleepTime);

			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();
			Thread.sleep(sleepTime);

			if (loginError == 0) {

				Reporter.log("Successfully Login to application");
				log.info("Successfully Login to application");
				// This method is to ensure Home is Active page..
				assertionFunction.assertHomeActiveLink();

				// This method asserts Sign Out link.
				assertionFunction.assertSignOutLink();

				// This method asserts footer links.
				assertionFunction.assertFooterCopyRight();

				// This method is used to logout from application.
				Thread.sleep(sleepTime);
				userLogin.LogOut(driver);
				log.info("Successfully Logout from the application");

				// This method is to ensure Login page is displayed when user
				// Sign
				// Out from Application.
				assertionFunction.assertLoginPageDetails();
			} else {
				Reporter.log("Login to application failed");
				log.error("Login to application failed");
			}

		} catch (Throwable e) {
			captureScreenshot();
			collector.addError(e);
		}
		log.info("___________________________________________");
	}

}
