package comcast.test.app.testCases.loginPageAndLogin;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: ValidateRememberMeFunctionality Description: TThis test case is
 * used to validate Remember Me functionality on Login Page in watchable
 * application. Author: Manoj
 * **/

public class ValidateRememberMeFunctionality extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testValidateRememberMeFunctionality() throws Exception {

		try {

			log.info("Script: ValidateRememberMeFunctionality");
			log.info("***************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Click on Login button from header menu
			LoginFun.clickOnLoginButton();

			// Verify login pop up opened successfully
			assertTrue(
					"Login form is not opened",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.loginForm_XPATH)));
			log.info("Login form opened successfully");

			// Verify Remember me check box present in login form
			assertTrue(
					"Remember me check box is not present in login form",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.loginFormRememberMeCheckBox_XPATH)));
			log.info("Remember me check box is present in login form");

			// Verify Remember me label present in login form
			assertTrue(
					"Remember me label is not present in login form",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.loginFormRememberMeLabel_XPATH)));
			log.info("Remember me label is present in login form");

			// Login to Watchable application
			LoginFun.loginToWatchableApplicationRememberMe(driver,
					UILablesRepo.EMAIL, UILablesRepo.PASSWORD);

			Thread.sleep(sleepTime);
			// This assertion is to verify updated email on Home page.

			assertEquals(
					"Email id not updated in home page",
					UILablesRepo.USERNAME,
					driver.findElement(
							By.xpath(XpathObjectRepo.loginUserEmailTitle_XPATH))
							.getText());

			log.info("Successfully updated the email id in home page with logged in user email id");

			// Close the browser.
			// driver.close();
			Thread.sleep(sleepTime);
			// setupChromeDriver();
			// Open the application again
			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully Re-opened the application");
			driver.manage().window().maximize();
			Thread.sleep(sleepTime);

			// This assertion is to verify updated email on Home page.

			assertEquals(
					"Updated email id is not present in home page",
					UILablesRepo.USERNAME,
					driver.findElement(
							By.xpath(XpathObjectRepo.loginUserEmailTitle_XPATH))
							.getText());

			log.info("Updated email id is present in home page");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}