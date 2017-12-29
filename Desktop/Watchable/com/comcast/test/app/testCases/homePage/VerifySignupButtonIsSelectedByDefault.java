package comcast.test.app.testCases.homePage;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifySignupButtonIsSelectedByDefault Description: This test case
 * verifies Sign up button is selected as default in home page. 
 * Author: Manoj
 * **/

// Manoj Added

public class VerifySignupButtonIsSelectedByDefault extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifySignupButtonIsSelectedByDefault() throws Exception {

		try {

			log.info("Script: VerifySignupButtonIsSelectedByDefault");
			log.info("*********************************************");
			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			Thread.sleep(LessSleepTime);

			// Verifying Sign Up menu is present in header

			assertTrue(
					"Sign Up menu is not present in header",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.signUpMenu_XPATH)));
			log.info("Sign Up menu is present in header");

			String signupButtonBackgroundColor = driver.findElement(
					By.xpath(XpathObjectRepo.signUpMenu_XPATH)).getCssValue(
					"background-color");

			log.info("Signup Button Background Color: "
					+ signupButtonBackgroundColor);

			// Verifying Log In menu is present in header
			Thread.sleep(LessSleepTime);
			assertTrue(
					"Log In menu is not present in header",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.loginMenu_XPATH)));
			log.info("Log In menu is present in header");

			String loinButtonBackgroundColor = driver.findElement(
					By.xpath(XpathObjectRepo.loginMenu_XPATH)).getCssValue(
					"background-color");

			log.info("Loin Button Background Color: "
					+ loinButtonBackgroundColor);

			// Verify the Sign up button is selected as default in home page (By
			// checking the background color of both buttons are not same)

			assertNotSame(
					"Sign up button is not selected as default in home page.",
					signupButtonBackgroundColor, loinButtonBackgroundColor);
			log.info("Sign up button is selected as default in home page.");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
