package comcast.test.app.testCases.misc;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyClickOnWatchableLogoFromErrorPageUserNavigatesToHomePage
 * Description: This test case verifies clicking on watchable logo from
 * error page, user navigates back to home page. 
 * Author: Manoj
 **/

public class VerifyClickOnWatchableLogoFromErrorPageUserNavigatesToHomePage
		extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickOnWatchableLogoFromErrorPageUserNavigatesToHomePage()
			throws Exception {

		try {

			log.info("Script: VerifyClickOnWatchableLogoFromErrorPageUserNavigatesToHomePage");
			log.info("**********************************************************************");

			// Navigate to the Error page (Page Not Found)
			driver.get(UILablesRepo.PAGENOTFOUNDURL);
			Thread.sleep(sleepTime);

			// Verify Error page (Page Not Found) displayed
			AssertionRepoFunctions.assertErrorPageTitle();

			// Verify Watchable logo
			assertTrue(
					"Watchable logo is not present in error page.",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.watchableTopLogo_XPATH)));
			log.info("Watchable logo is present in error page.");

			// Click on top watchable logo

			HomeFun.clickOnTopWatchableLogo();

			// Verify user navigate back to home page after clicking on
			// watchable logo from error page

			AssertionRepoFunctions.assertWatchableTitle();

			log.info("Successfully Navigated to Home page after clicking on Watchable logo from error page");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}