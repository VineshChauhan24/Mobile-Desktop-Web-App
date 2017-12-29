package comcast.test.app.testCases.footerLink;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.footerLink.footerLinkFunctions.FooterLinkFun;
import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyClickOnWatchableLogoFromTermsServicePageNavigatesHomePage
 * Description: This test script verifies clicking on watchable logo from terms
 * of service page user is taking back to home page. Author: Manoj
 * **/

public class VerifyClickOnWatchableLogoFromTermsServicePageNavigatesHomePage
		extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickOnWatchableLogoFromTermsServicePageNavigatesHomePage()
			throws Exception {

		try {

			log.info("Script: VerifyClickOnWatchableLogoFromTermsServicePageNavigatesHomePage");
			log.info("***********************************************************************");

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
			assertTrue(
					"Terms of Use form is not opened",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.privacyPolicyForm_XPATH)));
			log.info("Terms of Use form is opened");

			// Click on Top Watchable logo from Terms of Service page to
			// navigate
			// back
			// to home page

			HomeFun.clickOnTopWatchableLogo();

			// Verify user navigate back to home page after clicking on
			// Watchable log from terms of service page
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully Navigated to Home page after clicking on Watchable logo from terms of service Page");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
