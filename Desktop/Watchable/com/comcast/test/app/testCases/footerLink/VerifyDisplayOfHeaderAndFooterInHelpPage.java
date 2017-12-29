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
 * Class Name: VerifyDisplayOfHeaderAndFooterInHelpPage Description: This test
 * case verifies user is navigating back to home page if user logout from Help
 * page. 
 * Author: Manoj
 * **/

public class VerifyDisplayOfHeaderAndFooterInHelpPage extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyDisplayOfHeaderAndFooterInHelpPage() throws Exception {

		try {

			log.info("Script: VerifyDisplayOfHeaderAndFooterInHelpPage");
			log.info("***********************************************");

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
			// Verify header section is present in Provider details page
			assertTrue(
					"Header section is NOT present in Help page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.header_XPATH)));
			log.info("Header section is present in Help page");

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();
			Thread.sleep(sleepTime);

			// Verify footer section is present in Helps page
			assertTrue(
					"Footer section is NOT present in Help page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footer_XPATH)));
			log.info("Fotter section is present in Help page");

			// Verifying Footer Copy Right is present in Help
			// page
			Thread.sleep(LessSleepTime);
			assertTrue(
					"Copyright text is not present in in Help page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerCopyRightText_XPATH)));
			log.info("Copyright text is present in in Help page");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
