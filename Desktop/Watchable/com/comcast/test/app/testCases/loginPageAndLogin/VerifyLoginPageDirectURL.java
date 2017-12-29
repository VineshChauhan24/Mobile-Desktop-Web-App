package comcast.test.app.testCases.loginPageAndLogin;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyLoginPageDirectURL Description: This test case verifies by
 * accessting direct login page URL, Whether it is opening login pop up. Author:
 * Manoj
 * **/

public class VerifyLoginPageDirectURL extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyLoginPageDirectURL() throws Exception {

		try {

			log.info("Script: VerifyLoginPageDirectURL");
			log.info("*********************************");

			// Navigate to the Home page of the application
			driver.get(UILablesRepo.LOGINAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			Thread.sleep(sleepTime);

			// Verify login pop up opened successfully
			assertTrue(
					"Login form is not opened using direct login page URL",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.loginForm_XPATH)));
			log.info("Login form opened successfully using direct login page URL");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}