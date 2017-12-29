package comcast.test.app.testCases.signUp;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.app.testCases.signUp.signUpFunctions.SignUpFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyCaptchaButton Description: This test case verifies the The
 * captcha Anchor button is clickable in cpacha container of Sign up form.
 * Author: Manoj
 * **/

public class VerifyCaptchaButton extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyCaptchaButton() throws Exception {

		try {

			log.info("Script: VerifyCaptchaButton");
			log.info("******************************");

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
			if (driver.findElements(
					By.xpath(XpathObjectRepo.signUpFormCaptchaImage_XPATH))
					.size() > 0) {

				// Verify captcha container present in login form

				assertTrue(
						"Captcha image is not present in Sign Up form",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.signUpFormCaptchaImage_XPATH)));
				log.info("Captcha image is present in Sign Up form");

				driver.switchTo().frame(3);

				// Verify Anchor button is present in captcha container
				assertTrue(
						"Anchor button is not present in captcha container",
						CommonFun.isElementPresent(driver,
								By.id(XpathObjectRepo.recaptchaAnchorButton_ID)));
				log.info("Anchor button is present in captcha container");

				// Verify captcha Anchor button is clickableL
				boolean match = true;

				CommonFun.isElementClickable(driver.findElement(By
						.id(XpathObjectRepo.recaptchaAnchorButton_ID)),
						"Captcha Anchor button", true);

				log.info(match);
				assertTrue("The captcha Anchor button is not clickable", match);

				log.info("The captcha Anchor button is clickable");

				// Click on captcha Anchor button

				HomeFun.clickOnCaptchaAnchorButton();
			}

			driver.switchTo().defaultContent();

			// Close the Sign Up form
			/*
			 * SignUpFun.clickOnSignUpFormCloseIcon();
			 * 
			 * // Verify Sign Up form is closed successfully assertFalse(
			 * "Sign Up Form is not closed", driver.findElement(
			 * By.xpath(XpathObjectRepo.signUpForm_XPATH)) .isDisplayed());
			 * log.info("Sign Up form is closed");
			 */

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}