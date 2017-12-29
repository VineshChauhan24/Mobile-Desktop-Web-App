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
 * Class Name: VerifyHelpPageTitle Description: This test case verifies the
 * title of the help page. Author: Manoj
 * **/

public class VerifyHelpPageTitle extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyHelpPageTitle() throws Exception {

		try {

			log.info("Script: VerifyHelpPageTitle");
			log.info("***************************");

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

			// Verify Help page title
			AssertionRepoFunctions.assertHelpPageTitle();
			log.info("The help page title displayed is '" + driver.getTitle()
					+ "'");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
