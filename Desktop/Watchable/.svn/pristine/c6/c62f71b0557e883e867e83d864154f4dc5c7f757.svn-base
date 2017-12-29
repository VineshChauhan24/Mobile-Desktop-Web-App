package comcast.test.app.testCases.signUp;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifySignUpPageDirectURL Description: This test case verifies by
 * accessting directsignup page URL, Whether it is opening signup pop up.
 * Author: Manoj
 * **/

public class VerifySignUpPageDirectURL extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifySignUpPageDirectURL() throws Exception {

		try {

			log.info("Script: VerifySignUpPageDirectURL");
			log.info("******************************");

			// Navigate to the Home page of the application
			driver.get(UILablesRepo.SIGNUPAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			Thread.sleep(sleepTime);

			// Verify sign up form opened successfully
			assertTrue(
					"Sign Up form is not opened using direct URL",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.signUpForm_XPATH)));
			log.info("Sign Up form opened successfully using direct URL");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}