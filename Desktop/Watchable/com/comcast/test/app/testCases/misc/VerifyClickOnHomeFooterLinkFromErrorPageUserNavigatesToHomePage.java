package comcast.test.app.testCases.misc;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.footerLink.footerLinkFunctions.FooterLinkFun;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyClickOnHomeFooterLinkFromErrorPageUserNavigatesToHomePage
 * Description: This test case verifies clicking on Home footer link from error
 * page, user navigates back to home page. 
 * Author: Manoj
 **/

public class VerifyClickOnHomeFooterLinkFromErrorPageUserNavigatesToHomePage
		extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickOnHomeFooterLinkFromErrorPageUserNavigatesToHomePage()
			throws Exception {

		try {

			log.info("Script: VerifyClickOnHomeFooterLinkFromErrorPageUserNavigatesToHomePage");
			log.info("**********************************************************************");

			// Navigate to the Error page (Page Not Found)
			driver.get(UILablesRepo.PAGENOTFOUNDURL);
			Thread.sleep(sleepTime);

			// Verify Error page (Page Not Found) displayed
			AssertionRepoFunctions.assertErrorPageTitle();

			// Scroll to Footer
			HomePageCommonFunctions.scrollToSocilaMediaSection();
			Thread.sleep(sleepTime);

			// Verify Home Link is present in footer
			assertTrue(
					"Home Link is not present",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerHelpLink_XPATH)));
			log.info("Home Link is present");

			// Click on footer Home link from error page to
			// navigate back
			// to home page

			FooterLinkFun.clickOnHomeLink();

			// Verify user navigate back to home page after clicking on
			// home footer link from error page

			AssertionRepoFunctions.assertWatchableTitle();

			log.info("Successfully Navigated to Home page after clicking on Home footer from error page");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}