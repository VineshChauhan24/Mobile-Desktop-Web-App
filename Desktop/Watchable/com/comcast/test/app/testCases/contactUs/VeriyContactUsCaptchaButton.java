package comcast.test.app.testCases.contactUs;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.contactUs.contactUsFunctions.ContactUsFun;
//import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VeriyContactUsCaptchaButton Description: This test case verifies
 * the The captcha Anchor button is clickable in cpacha contains of conatach us
 * form. 
 * Author: Manoj
 * **/

public class VeriyContactUsCaptchaButton extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVeriyContactUsCaptchaButton() throws Exception {

		try {

			log.info("Script: VeriyContactUsCaptchaButton");
			log.info("************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();
			Thread.sleep(sleepTime);

			// Click on Contact Us link from footer
			ContactUsFun.clickOnContactUsLink();

			// Verify Contact Us form opened successfully
			assertTrue(
					"Contact Us form is not opened",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.contactUsForm_XPATH)));
			log.info("Contact Us form opened successfully");

			if (driver.findElements(
					By.xpath(XpathObjectRepo.contactUsFormCaptchaImage_XPATH))
					.size() > 0) {

				// Verify captcha image present in Contact Us form
				assertTrue(
						"Captcha image is not present in Contact Us form",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.contactUsFormCaptchaImage_XPATH)));
				log.info("Captcha image is present in Contact Us form");
			}

			driver.switchTo().frame(3);

			// Verify Anchor button is present in captcha container
			assertTrue(
					"Anchor button is not present in captcha container",
					CommonFun.isElementPresent(driver,
							By.id(XpathObjectRepo.recaptchaAnchorButton_ID)));
			log.info("Anchor button is present in captcha container");

			// Verify captcha Anchor button is clickable
			boolean match = true;

			CommonFun.isElementClickable(driver.findElement(By
					.id(XpathObjectRepo.recaptchaAnchorButton_ID)),
					"Captcha Anchor button", true);

			log.info(match);
			assertTrue("The captcha Anchor button is not clickable", match);

			log.info("The captcha Anchor button is clickable");

			// Click on captcha Anchor button

			// HomeFun.clickOnCaptchaAnchorButton();

			driver.switchTo().defaultContent();

			/*
			 * // Verify captcha text box present in Contact Us form assertTrue(
			 * "Captcha text box is not present in Contact Us form",
			 * CommonFun.isElementPresent(driver,
			 * By.id(XpathObjectRepo.contactUsFormCaptchaText_ID)));
			 * log.info("Captcha text box is present in Contact Us form");
			 */
			if (driver.findElements(
					By.xpath(XpathObjectRepo.contactUsFormCaptchaImage_XPATH))
					.size() > 0) {

				if (driver
						.findElement(
								By.xpath(XpathObjectRepo.contactUsFormCaptchaImage_XPATH))
						.isDisplayed() == true) {

					// Close the Contact Us form
					ContactUsFun.clickOnContactUsFormCloseIcon();

					// Verify Contact Us form is closed successfully
					assertFalse(
							"Contact Us Form is not closed",
							driver.findElement(
									By.xpath(XpathObjectRepo.contactUsForm_XPATH))
									.isDisplayed());
					log.info("Contact Us form is closed");
				}
			}

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
