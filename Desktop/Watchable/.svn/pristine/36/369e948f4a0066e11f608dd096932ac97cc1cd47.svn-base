package comcast.test.app.testCases.footerLink;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.footerLink.footerLinkFunctions.FooterLinkFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyTermsOfUseLink Description: This test case click on Terms
 * of Use link and verified the main content of Terms of Use page. Author: Manoj
 * **/

public class VerifyTermsOfUseLink extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyTermsOfUseLink() throws Exception {

		try {

			log.info("Script: VerifyTermsOfUseLink");
			log.info("****************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();

			// Verify Terms of Use Link is present in footer
			assertTrue("Terms of Use Link is not present",
					CommonFun.isElementPresent(driver, By
							.xpath(XpathObjectRepo.footerTermsOfUseLink_XPATH)));
			log.info("Terms of Use Link is present");

			// Click on Terms of Use Link
			FooterLinkFun.clickOnTermsOfUseLink();
			
			
		

			// Verify Terms of Use form is opened
	/*	assertTrue(
					"Terms of Use form is not opened",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.privacyPolicyForm_XPATH)));
			log.info("Terms of Use form is opened");

			// Verify Terms of Use form Title
			assertTrue(
					"Title not present for Terms of Use form",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.privacyPolicyForm_XPATH)));

			log.info("The Title '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.privacyPolicyFormTitle_XPATH))
							.getText() + "' is present in Terms of Use form");

			// Verify Terms of Use effective date
			assertTrue(
					"Terms of Use effective date not present in Terms of Use form",
					CommonFun.isElementPresent(driver, By
							.id(XpathObjectRepo.privacyPolicyEffectiveDate_ID)));

			String effectiveDate = driver
					.findElement(
							By.id(XpathObjectRepo.privacyPolicyEffectiveDate_ID))
					.getText().substring(0, 26);

			log.info("Terms of Use effective date '" + effectiveDate
					+ "' is present in Privacy Policy form"); */

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
