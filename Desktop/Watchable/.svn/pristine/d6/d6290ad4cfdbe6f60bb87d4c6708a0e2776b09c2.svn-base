package comcast.test.app.testCases.footerLink;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyPrivacyPolicyLinkTitle Description: This test case verifies
 * the presence of Privacy Policy link in the footer and title of help link.
 * Author: Manoj
 * **/

public class VerifyPrivacyPolicyLinkTitle extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyPrivacyPolicyLinkTitle() throws Exception {

		try {

			log.info("Script: VerifyPrivacyPolicyLinkTitle");
			log.info("************************************");

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

			// Verify Privacy Policy Link Title
			assertTrue(
					"Title not present for Privacy Policy link in Footer",
					driver.findElement(
							By.xpath(XpathObjectRepo.footerPrivacyPolicyLink_XPATH))
							.getText()
							.toUpperCase()
							.trim()
							.matches(
									UILablesRepo.FOOTER_PRIVACYPOLICY_LINK
											.toUpperCase().trim()));
			log.info("The Privacy Policy Link title '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.footerPrivacyPolicyLink_XPATH))
							.getText() + "' is present");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
