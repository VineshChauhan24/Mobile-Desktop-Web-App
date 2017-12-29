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
 * Class Name: VeriyMessageEmailFieldDisplayedSelectingReason Description: This
 * test scripts verifies the message and email fields are displayed in contact
 * us form after selecting value from reason drop down list. 
 * Author: Manoj
 * **/

public class VeriyMessageEmailFieldDisplayedSelectingReason extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVeriyMessageEmailFieldDisplayedSelectingReason()
			throws Exception {

		try {

			log.info("Script: VeriyMessageEmailFieldDisplayedSelectingReason");
			log.info("******************************************************");

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

			// select reason from reason list
			ContactUsFun.selectReason();

			// Verify email text box is displayed in Contact Us form after
			// selection reason
			assertTrue(
					"Email text box is not displayed in Contact Us form after selection reason",
					driver.findElement(
							By.id(XpathObjectRepo.contactUsFormEmailText_ID))
							.isDisplayed());
			log.info("Email text box is displayed in Contact Us form after selection reason");

			// Verify Reason description text box is displayed in Contact Us
			// form
			// after selection reason

			if (driver.findElement(
					By.xpath(XpathObjectRepo.contactUsFormReasonDesc_XPATH))
					.isDisplayed()) {
				;

				log.info(driver
						.findElement(
								By.xpath(XpathObjectRepo.contactUsFormReasonDesc_XPATH))
						.isDisplayed());

				log.info("Reason description text box is displayed in Contact Us form after selection reason");
			} else {
				log.info("Reason description text box is not displayed in Contact Us form after selection reason");
			}

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
