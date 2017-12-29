package comcast.test.app.testCases.loginPageAndLogin;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyRememberMeCheckedByDefault Description: This test case is
 * used to verify Remember Me check box is selected by default in login form.
 * Author: Manoj
 * **/

public class VerifyRememberMeCheckedByDefault extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyRememberMeCheckedByDefault() throws Exception {

		try {

			log.info("Script: VerifyRememberMeCheckedByDefault");
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

			// Verify Remember me check box is selected by default

			boolean rememberMeCheckStatus = false;

			CommonFun.mouseOverElement(driver, driver.findElement(By
					.xpath(XpathObjectRepo.loginFormRememberMeCheckBox_XPATH)));

			Thread.sleep(sleepTime);

			rememberMeCheckStatus = driver
					.findElement(
							By.xpath(XpathObjectRepo.loginFormRememberMeCheckBox_XPATH))
					.isSelected();

			log.info(rememberMeCheckStatus);

			if (rememberMeCheckStatus == true) {

				log.info("Remember me check box is selected by default");
			} else {
				log.error("Remember me check box is not selected by default");
			}

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}