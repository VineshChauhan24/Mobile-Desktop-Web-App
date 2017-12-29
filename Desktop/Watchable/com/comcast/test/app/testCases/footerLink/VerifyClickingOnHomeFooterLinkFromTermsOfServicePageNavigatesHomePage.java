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
 * Class Name:
 * VerifyClickingOnHomeFooterLinkFromTermsOfServicePageNavigatesHomePage
 * Description: This test case click on home footer from This test case click on
 * home footer from show page, navigating back to Home page. Author: Manoj
 * **/

public class VerifyClickingOnHomeFooterLinkFromTermsOfServicePageNavigatesHomePage
		extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickingOnHomeFooterLinkFromTermsOfServicePageNavigatesHomePage()
			throws Exception {

		try {

			log.info("Script: VerifyClickingOnHomeFooterLinkFromTermsOfServicePageNavigatesHomePage");
			log.info("*****************************************************************************");

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

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();
			Thread.sleep(sleepTime);

			// Verify Home Link is present in footer
			assertTrue(
					"Home Link is not present",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerHelpLink_XPATH)));
			log.info("Home Link is present");

			// Click on footer Home link from erms of Service detail page to
			// navigate back
			// to home page

			FooterLinkFun.clickOnHomeLink();
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully Navigated to Home page after clicking on Home link from erms of Service page");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
