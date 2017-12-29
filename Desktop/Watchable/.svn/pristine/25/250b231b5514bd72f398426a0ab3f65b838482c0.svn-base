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
 * Class Name: VerifyClickOnWatchableLogoFromPrivacyPolicyPageNavigatesHomePage
 * Description: This test script verifies clicking on watchable logo from
 * Privacy Policy page user is taking back to home page.. Author: Manoj
 * **/

public class VerifyClickOnWatchableLogoFromPrivacyPolicyPageNavigatesHomePage
		extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickOnWatchableLogoFromPrivacyPolicyPageNavigatesHomePage()
			throws Exception {

		try {

			log.info("Script: VerifyClickOnWatchableLogoFromPrivacyPolicyPageNavigatesHomePage");
			log.info("************************************************************************");

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
			assertTrue(
					"Privacy Policy form is not opened",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.privacyPolicyForm_XPATH)));
			log.info("Privacy Policy form is opened");

			// Click on Top Watchable logo from Privacy Policy page to navigate
			// back
			// to home page

			HomeFun.clickOnTopWatchableLogo();

			// Verify user navigate back to home page after clicking on
			// Watchable log from Privacy Policy page
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully Navigated to Home page after clicking on Watchable logo from Privacy Policy Page");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
