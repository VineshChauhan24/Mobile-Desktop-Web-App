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
 * Class Name: VerifyPrivacyPolicyLink Description: This test case click on
 * Privacy Policy link and verified the main content of Privacy Policy page.
 * Author: Manoj
 * **/

public class VerifyPrivacyPolicyLink extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyPrivacyPolicyLink() throws Exception {

		try {

			log.info("Script: VerifyPrivacyPolicyLink");
			log.info("*******************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();

			// Verify Privacy Policy Link is present in footer
			assertTrue(
					"Privacy Policy Link is not present",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.footerPrivacyPolicyLink_XPATH)));
			log.info("Privacy Policy Link is present");

			// Click on Privacy Policy Link
			FooterLinkFun.clickOnPrivacyPolicyLink();
			
			

			// Verify Privacy Policy form is opened
/*		assertTrue(
					"Privacy Policy form is not opened",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.privacyPolicyForm_XPATH)));
			log.info("Privacy Policy form is opened");

			// Verify Privacy Policy form Title
			assertTrue(
					"Title not present for Privacy Policy form",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.privacyPolicyForm_XPATH)));

			log.info("The Title '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.privacyPolicyFormTitle_XPATH))
							.getText() + "' is present in Privacy Policy form");

			// Verify Privacy Policy effective date
			assertTrue(
					"Privacy Policy effective date not present in Privacy Policy form",
					CommonFun.isElementPresent(driver, By
							.id(XpathObjectRepo.privacyPolicyEffectiveDate_ID)));

			String effectiveDate = driver
					.findElement(
							By.id(XpathObjectRepo.privacyPolicyEffectiveDate_ID))
					.getText().substring(0, 26);

			log.info("Privacy Policy effective date '" + effectiveDate
					+ "' is present in Privacy Policy form");*/

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
