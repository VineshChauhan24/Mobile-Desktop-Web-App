package comcast.test.app.testCases.misc;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.misc.miscFunctions.MiscFun;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyClickOnBacktohomepageLinkFromErrorPageUserNavigatesToHomePage Description: This test case
 * verifies clicking on 'back to homepage' link from error page, user
 * navigates back to home page. Author: Manoj
 **/

public class VerifyClickOnBacktohomepageLinkFromErrorPageUserNavigatesToHomePage
		extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickOnBacktohomepageLinkFromErrorPageUserNavigatesToHomePage()
			throws Exception {

		try {

			log.info("Script: VerifyClickOnBacktohomepageLinkFromErrorPageUserNavigatesToHomePage");
			log.info("***************************************************************************");

			// Navigate to the Error page (Page Not Found)
			driver.get(UILablesRepo.PAGENOTFOUNDURL);
			Thread.sleep(sleepTime);

			// Verify Error page (Page Not Found) displayed
			AssertionRepoFunctions.assertErrorPageTitle();

			// Verify back to the home page link
			assertTrue(
					"The back to the home page link is not present in error page.",
					CommonFun.isElementPresent(driver, By
							.xpath(XpathObjectRepo.pageNotFoundHomeLink_XPATH)));
			log.info("The back to the home page link is present in error page.");

			// Click on 'back to home page'link

			MiscFun.clickOnBacktohomepageLink();

			// Verify user navigate back to home page after clicking on
			// 'back to home page'link from error page

			AssertionRepoFunctions.assertWatchableTitle();

			log.info("Successfully Navigated to Home page after clicking on 'back to home page'link from error page");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}