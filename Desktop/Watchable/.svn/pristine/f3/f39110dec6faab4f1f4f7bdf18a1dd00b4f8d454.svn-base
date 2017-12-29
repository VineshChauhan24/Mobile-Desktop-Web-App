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
 * Class Name: VerifyTermsOfUseLinkTitle Description: This test case verifies
 * the presence of Terms of Use link in the footer and title of Terms of Use
 * link. Author: Manoj
 * **/

public class VerifyTermsOfUseLinkTitle extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyTermsOfUseLinkTitle() throws Exception {

		try {

			log.info("Script: VerifyTermsOfUseLinkTitle");
			log.info("*********************************");

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

			// Verify Terms of Use Link Title
			assertTrue(
					"Title not present for Terms of Use link in Footer",
					driver.findElement(
							By.xpath(XpathObjectRepo.footerTermsOfUseLink_XPATH))
							.getText()
							.toUpperCase()
							.trim()
							.matches(
									UILablesRepo.FOOTER_TERMSOFUSE_LINK
											.toUpperCase().trim()));
			log.info("The Terms of Use Link title '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.footerTermsOfUseLink_XPATH))
							.getText() + "' is present");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
