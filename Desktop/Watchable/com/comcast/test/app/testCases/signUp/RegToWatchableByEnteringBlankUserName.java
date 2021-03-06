package comcast.test.app.testCases.signUp;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.signUp.signUpFunctions.SignUpFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: RegToWatchableByEnteringBlankUserName Description: This test case
 * is to validated registration by providing blank user name 
 * Author: Manoj
 * **/

public class RegToWatchableByEnteringBlankUserName extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testRegToWatchableByEnteringBlankUserName() throws Exception {

		try {

			log.info("Script: RegToWatchableByEnteringBlankUserName");
			log.info("***********************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Click on Sign UP button from header menu
			SignUpFun.clickOnSignUpButton();

			// Verify sign up form opened successfully
			assertTrue(
					"Sign Up form is not opened",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.signUpForm_XPATH)));
			log.info("Sign Up form opened successfully");

			// Enter Sign Up details
			SignUpFun.enterSignUpDetails(driver, "", UILablesRepo.NEWUSER,
					UILablesRepo.PASSWORD);

			// Select Terms And conditions check box
			SignUpFun.selectTermsConditionsCheck();

			// click on SIGN UP button after entering existing user email
			SignUpFun.clickOnSignUpFormButton();

			// Verify User Name error message
			assertTrue(
					"User Name Error Message is not displayed",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.signUpFormUserNameError_XPATH)));
			log.info("User Name Error Message '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.signUpFormUserNameError_XPATH))
							.getText() + "' is displayed");
			if (driver.findElements(
					By.xpath(XpathObjectRepo.signUpFormCaptchaImage_XPATH))
					.size() > 0) {
				if (driver.findElement(
						By.xpath(XpathObjectRepo.signUpFormCaptchaImage_XPATH))
						.isDisplayed() == true) {

					// Close the Sign Up form
					SignUpFun.clickOnSignUpFormCloseIcon();

					// Verify Sign Up form is closed successfully
					assertFalse(
							"Sign Up Form is not closed",
							driver.findElement(
									By.xpath(XpathObjectRepo.signUpForm_XPATH))
									.isDisplayed());
					log.info("Sign Up form is closed");
				}
			}

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}