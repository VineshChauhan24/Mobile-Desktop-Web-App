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
 * Class Name: VerifyContactUsFormCloseButton Description: This test script
 * verifies clicking on close button from contact us pop up window closing the
 * contact us window. 
 * Author: Manoj
 * **/

public class VerifyContactUsFormCloseButton extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyContactUsFormCloseButton() throws Exception {

		try {

			log.info("Script: VerifyContactUsFormCloseButton");
			log.info("**************************************");

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

			// Verify Close icon present in Contact Us form
			assertTrue(
					"Close icon is not present in Contact Us form",
					CommonFun.isElementPresent(driver,
							By.id(XpathObjectRepo.contactUsFormCloseButton_ID)));
			log.info("Close icon is present in Contact Us form");
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
