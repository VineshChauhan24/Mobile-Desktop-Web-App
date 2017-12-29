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
 * Class Name: VerifyTermsOfServicePageTitle Description: This test case verify the title of
 * Terms of Use Service page. Author: Manoj
 * **/

public class VerifyTermsOfServicePageTitle extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyTermsOfServicePageTitle() throws Exception {

		try {

			log.info("Script: VerifyTermsOfServicePageTitle");
			log.info("************************************");

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

			// Click on Terms of Service Link
			FooterLinkFun.clickOnTermsOfUseLink();
			
					
			// Verify Terms of Service page title
			AssertionRepoFunctions.assertTermsOfServicePageTitle();
			log.info("The Terms of Service page title displayed is '" + driver.getTitle()
					+ "'");
			
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
