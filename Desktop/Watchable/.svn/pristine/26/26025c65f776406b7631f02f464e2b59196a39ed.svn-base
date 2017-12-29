package comcast.test.app.testCases.loginPageAndLogin;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: LoginToWatchableUsingSQLStatement Description: This test case try
 * to login to Watchable application by entering user name/email as SQL statement and
 * valid password Author: Manoj
 * **/

public class LoginToWatchableUsingSQLStatement extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testLoginToWatchableUsingSQLStatement() throws Exception {

		try {

			log.info("Script: LoginToWatchableUsingSQLStatement");
			log.info("********************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Login to Watchable application using invalid password
			LoginFun.loginToWatchableApplication(driver,
					proUtil.getProperty("SQLSTMT_USERNAME"),
					UILablesRepo.PASSWORD);

			log.info("Login to watchable application failed user name as SQL statement and valid password");
			log.info("Error Message: "
					+ driver.findElement(By.id(XpathObjectRepo.loginError_ID))
							.getText());

			// Close the login form
			LoginFun.clickOnLoginFormCloseIcon();

			// Verify login form is closed successfully
			assertFalse(
					"Login Form is not closed",
					driver.findElement(
							By.xpath(XpathObjectRepo.loginForm_XPATH))
							.isDisplayed());
			log.info("Login Form is closed");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}