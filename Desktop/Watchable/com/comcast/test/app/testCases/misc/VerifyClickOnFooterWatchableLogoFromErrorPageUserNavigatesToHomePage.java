package comcast.test.app.testCases.misc;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name:
 * VerifyClickOnFooterWatchableLogoFromErrorPageUserNavigatesToHomePage
 * Description: This test scripts verifies clicking on footer watchable logo
 * from error page, user navigates back to home page. 
 * Author: Manoj
 **/

public class VerifyClickOnFooterWatchableLogoFromErrorPageUserNavigatesToHomePage
		extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickOnFooterWatchableLogoFromErrorPageUserNavigatesToHomePage()
			throws Exception {

		try {

			log.info("Script: VerifyClickOnFooterWatchableLogoFromErrorPageUserNavigatesToHomePage");
			log.info("****************************************************************************");

			// Navigate to the Error page (Page Not Found)
			driver.get(UILablesRepo.PAGENOTFOUNDURL);
			Thread.sleep(sleepTime);

			// Verify Error page (Page Not Found) displayed
			AssertionRepoFunctions.assertErrorPageTitle();

			// Scroll to Footer
			HomePageCommonFunctions.scrollToSocilaMediaSection();
			Thread.sleep(sleepTime);

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();
			Thread.sleep(sleepTime);

			// Verify Watchable logo is present in footer
			assertTrue(
					"Watchable logo is not present in footer",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerwatchableLogo_XPATH)));
			log.info("Watchable logo is present in footer");

			// Click on footer Watchable logo from error page to
			// navigate back
			// to home page

			HomeFun.clickOnBottomWatchableLogo();

			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully Navigated to Home page after clicking on footer Watchable logo from error page");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}