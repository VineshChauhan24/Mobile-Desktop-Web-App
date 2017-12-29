package comcast.test.app.testCases.signUp;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.signUp.signUpFunctions.SignUpFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyCaptchaAudio Description: This test case will verify the
 * audio functionality in captcha. 
 * Author: Manoj
 * **/

public class VerifyCaptchaAudio extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyCaptchaAudio() throws Exception {

		try {

			log.info("Script: VerifyCaptchaAudio");
			log.info("**************************");

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

			// Verify Cpatcha audio button is available
			assertTrue(
					"Captcha audio buttin is not present in captcha image",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.signUpFormCaptchaAudio_XPATH)));
			log.info("Captcha audio buttin is present in captcha image");

			// Click On captcha audio button
			SignUpFun.clickOnCaptchaAudioButton();

			// Verify captcha audio button changed to txt button
			assertTrue(
					"Captcha audio button is not changed to text button",
					CommonFun.isElementPresent(driver, By
							.xpath(XpathObjectRepo.signUpFormCaptchaText_XPATH)));
			log.info("Captcha audio button is changed to text button");

			// Verify play sound link displayed on captcha image after clicking
			// on audio button
			assertTrue(
					"Play sound link is not displayed on captcha after clicking on audio button",
					CommonFun.isElementPresent(driver,
							By.id(XpathObjectRepo.signUpFormCaptchaPlay_ID)));
			log.info("Play sound link is displayed on captcha after clicking on audio button");
			log.info("The link '"
					+ driver.findElement(
							By.id(XpathObjectRepo.signUpFormCaptchaPlay_ID))
							.getText()
					+ "' displayed on captcha image after clicking on audio button");
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

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}