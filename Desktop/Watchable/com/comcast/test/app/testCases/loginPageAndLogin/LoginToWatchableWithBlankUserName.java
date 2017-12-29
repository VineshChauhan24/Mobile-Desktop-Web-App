package comcast.test.app.testCases.loginPageAndLogin;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: LoginToWatchableWithBlankUserName Description: This test case
 * try to login to Watchable application with blank UserName and Password.
 * Author: Manoj
 * **/

public class LoginToWatchableWithBlankUserName extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testLoginToWatchableWithBlankUserName() throws Exception {

		try {

			log.info("Script: LoginToWatchableWithBlankUserName");
			log.info("******************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Login to Watchable application using blank user name and
			// valid password
			LoginFun.loginToWatchableApplicationInvalidLengthUser(driver, "",
					UILablesRepo.PASSWORD);

			WebElement loginError = driver.findElement(By
					.id(XpathObjectRepo.loginError_ID));

			if (loginError.isDisplayed() == false) {

				// Verify login form is closed after successful login to
				// application
				assertFalse(
						"Login Form is not closed",
						driver.findElement(
								By.xpath(XpathObjectRepo.loginForm_XPATH))
								.isDisplayed());
				log.info("Login Form is closed after successful login to application");

				// Logout from Watchable Application.
				//LoginFun.logOut(driver);

				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}