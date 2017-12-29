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
 * Class Name: VerifyTermsAndConditionsFormCloseButton Description: This test
 * script verifies clicking on close button from Terms and conditions pop up
 * window closing the Terms and conditions window. Author: Manoj
 * **/

public class VerifyTermsAndConditionsFormCloseButton extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyTermsAndConditionsFormCloseButton() throws Exception {

		try {

			log.info("Script: VerifyTermsAndConditionsFormCloseButton");
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

			// Verify terms and conditions link present in sign up form
			assertTrue(
					"Terms and conditionslink is not present in Sign Up form",
					CommonFun.isElementPresent(
							driver,
							By.id(XpathObjectRepo.signUpFormTermsConditionLink_ID)));
			log.info("Terms and conditions link is present in Sign Up form");

			// Click On Terms and Conditions Link
			SignUpFun.clickOnTermsConditionsLink();

			// Verify Terms and Conditions page opened successfully
			assertTrue(
					"Terms and Conditions form is not opened",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.termsAndConditionsForm_XPATH)));
			log.info("Terms and Conditions form opened successfully");

			// Verify Close icon present in terms and conditions page
			assertTrue(
					"Close icon is not present in Sign Up form",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.termsAndConditionsFormCloseButton_XPATH)));
			log.info("Close button is present in Terms and Conditions page");

			// Close the Terms & Conditions Page
			SignUpFun.clickOntermsAndConditionsFormCloseIcon();

			// Verify Terms & Conditions Page is closed successfully
			assertFalse(
					"Terms & Conditions Form is not closed",
					driver.findElement(
							By.xpath(XpathObjectRepo.termsAndConditionsForm_XPATH))
							.isDisplayed());
			log.info("Terms & Conditionsp form is closed");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}