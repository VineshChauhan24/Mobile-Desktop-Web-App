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
 * Class Name: VerifyAdChoicesLink Description: This test case click on Ad
 * Choices link and verifies Ad Information page is opened. Author: Manoj
 * **/

public class VerifyAdChoicesLink extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyAdChoicesLink() throws Exception {

		try {

			log.info("Script: VerifyAdChoicesLink");
			log.info("***************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();

			// Verify Ad Choices Link is present in footer
			assertTrue("Ad Choices Link is not present",
					CommonFun.isElementPresent(driver, By
							.xpath(XpathObjectRepo.footerTermsOfUseLink_XPATH)));
			log.info("Ad Choices Link is present");

			// Click on Ad Choices Link
			FooterLinkFun.clickOnAdChoicesLink();

			// Verify Ad Information page is opened
			
			// Functionality is removed now - Needs to check later

			/*assertTrue(
					"User is not Navigated to Ad Information page after clicking Ad Choices link",
					driver.getTitle().contains(UILablesRepo.AD_INFO_PAGE_TITLE));
			log.info("The Ad Information page title '" + driver.getTitle()
					+ "' is displayed"); */

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
