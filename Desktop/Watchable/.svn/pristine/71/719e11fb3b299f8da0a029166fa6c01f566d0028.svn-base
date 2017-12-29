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
 * Class Name: VerifyClickOnWatchableLogoFromHelpPageNavigatesHomePage
 * Description: This test script verifies clicking on watchable logo from help
 * page user is taking back to home page. Author: Manoj
 * **/

public class VerifyClickOnWatchableLogoFromHelpPageNavigatesHomePage extends
		BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickOnWatchableLogoFromHelpPageNavigatesHomePage()
			throws Exception {

		try {

			log.info("Script: VerifyClickOnWatchableLogoFromHelpPageNavigatesHomePage");
			log.info("***************************************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();

			// Verify Help Link is present in footer
			assertTrue(
					"Help Link is not present",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerHelpLink_XPATH)));
			log.info("Help Link is present");

			// Click on Help Link
			FooterLinkFun.clickOnHelpLink();

			// Verify Help form is opened
			assertTrue(
					"Help form is not opened",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerHelpLink_XPATH)));
			log.info("Help form is opened");

			// Click on Top Watchable logo from help page to navigate
			// back
			// to home page

			HomeFun.clickOnTopWatchableLogo();

			// Verify user navigate back to home page after clicking on
			// Watchable log from help page
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully Navigated to Home page after clicking on Watchable logo from help Page");

			log.info("");
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
