package comcast.test.app.testCases.signUp;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.signUp.signUpFunctions.SignUpFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyOpeningSignUpPageClickingOnSignUPLinkFromFooterWithoutLogin
 * Description: This test case try registers to Watchable application entering
 * valid credentials without accepting Terms And Conditions. 
 * Author: Manoj
 * **/

public class VerifyOpeningSignUpPageClickingOnSignUPLinkFromFooterWithoutLogin
		extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyOpeningSignUpPageClickingOnSignUPLinkFromFooterWithoutLogin()
			throws Exception {

		try {

			log.info("Script: VerifyOpeningSignUpPageClickingOnSignUPLinkFromFooterWithoutLogin");
			log.info("*************************************************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Scroll to Footer section
			HomePageCommonFunctions.scrollToFooterSection();

			// Click on Sign UP link from Footer menu
			SignUpFun.clickOnFooterSignUpLink();

			// Verify sign up form opened successfully
			assertTrue(
					"Sign Up form is not opened",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.signUpForm_XPATH)));
			log.info("Sign Up form opened successfully");
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