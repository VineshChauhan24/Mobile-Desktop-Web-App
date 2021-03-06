package comcast.test.app.testCases.contactUs;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.contactUs.contactUsFunctions.ContactUsFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VeriyContactUsPageContent Description: Verifying all required
 * objects present in contact us form. 
 * Author: Manoj
 * **/

public class VeriyContactUsPageContent extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVeriyContactUsPageContent() throws Exception {

		try {

			log.info("Script: VeriyContactUsPageContent");
			log.info("******************************");

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

			// select reason from reason list to display Message and email text
			// box in contact us form
			ContactUsFun.selectReason();

			// Verify title present in Contact Us form
			assertTrue(
					"Contact Us form does not contain title",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.contactUsFormTitle_XPATH)));
			log.info("The title '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.contactUsFormTitle_XPATH))
							.getText() + "' Present in Contact Us form");

			// Verify Close icon present in Contact Us form
			assertTrue(
					"Close icon is not present in Contact Us form",
					CommonFun.isElementPresent(driver,
							By.id(XpathObjectRepo.contactUsFormCloseButton_ID)));
			log.info("Close icon is present in Contact Us form");

			// Verify Contact Reason drop down List present in Contact Us form
			assertTrue(
					"Reason drop down List is not present in Contact Us form",
					CommonFun.isElementPresent(
							driver,
							By.id(XpathObjectRepo.contactUsFormContactReasonList_ID)));
			log.info("Reason drop down List is present in Contact Us form");

			// Verify Reason description text box present in Contact Us form
			assertTrue(
					"Reason description text box is not present in Contact Us form",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.contactUsFormReasonDesc_XPATH)));
			log.info("Reason description text box is present in Contact Us form");

			// Verify email text box present in Contact Us form
			assertTrue(
					"Email text box is not present in Contact Us form",
					CommonFun.isElementPresent(driver,
							By.id(XpathObjectRepo.contactUsFormEmailText_ID)));
			log.info("Email text box is present in Contact Us form");

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

				driver.switchTo().frame(3);

				// Verify Anchor button is present in captcha container
				assertTrue(
						"Anchor button is not present in captcha container",
						CommonFun.isElementPresent(driver,
								By.id(XpathObjectRepo.recaptchaAnchorButton_ID)));
				log.info("Anchor button is present in captcha container");

				// Verify Anchor Label is present in captcha container
				assertTrue(
						"Anchor Label is not present in captcha container",
						CommonFun.isElementPresent(driver,
								By.id(XpathObjectRepo.recaptchaAnchorLabel_ID)));
				log.info("Anchor Label is present in captcha container");

				// Verify Logo Image is present in captcha container
				assertTrue("Logo Image is not present in captcha container",
						CommonFun.isElementPresent(driver, By
								.xpath(XpathObjectRepo.recaptchaLogoImg_XPATH)));
				log.info("Logo Image is present in captcha container");

				// Verify Logo Text is present in captcha container
				assertTrue(
						"Logo Text is not present in captcha container",
						CommonFun.isElementPresent(driver, By
								.xpath(XpathObjectRepo.recaptchaLogoText_XPATH)));
				log.info("Logo Text is present in captcha container");

				// Verify Captcha Privacy Link is present in captcha container
				assertTrue(
						"Captcha Privacy Link is not present in captcha container",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.recaptchaPrivacyLink_XPATH)));
				log.info("Captcha Privacy Linkis present in captcha container");

				// Verify Captcha Terms Link is present in captcha container
				assertTrue(
						"Captcha Terms Link is not present in captcha container",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.recaptchaTermsLink_XPATH)));
				log.info("Captcha Terms Linkis present in captcha container");
			}

			driver.switchTo().defaultContent();

			/*
			 * // Verify captcha text box present in Contact Us form assertTrue(
			 * "Captcha text box is not present in Contact Us form",
			 * CommonFun.isElementPresent(driver,
			 * By.id(XpathObjectRepo.contactUsFormCaptchaText_ID)));
			 * log.info("Captcha text box is present in Contact Us form");
			 */

			// Verify Submit button present in Contact Us form
			assertTrue(
					"Submit button is not present in Contact Us form",
					CommonFun.isElementPresent(driver,
							By.id(XpathObjectRepo.contactUsFormSubmitButton_ID)));
			log.info("Submit button is present in Contact Us form");

			// Reset button removed from contact us form
			/*
			 * // Verify Reset button present in Contact Us form assertTrue(
			 * "Reset button is not present in Contact Us form",
			 * CommonFun.isElementPresent(driver,
			 * By.id(XpathObjectRepo.contactUsFormResetButton_ID)));
			 * log.info("Reset button is present in Contact Us form");
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
