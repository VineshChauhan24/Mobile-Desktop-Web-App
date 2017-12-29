package comcast.test.app.testCases.footerLink;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyHelpLinkTitle Description: This test case verifies the
 * presence of Help link in the footer and title of help link. Author: Manoj
 * **/

public class VerifyHelpLinkTitle extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyHelpLinkTitle() throws Exception {

		try {

			log.info("Script: VerifyHelpLinkTitle");
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

			// Verify Help Link Title
			assertTrue(
					"Tile not present for HELP link in Footer",
					driver.findElement(
							By.xpath(XpathObjectRepo.footerHelpLink_XPATH))
							.getText().matches(UILablesRepo.FOOTER_HELP_LINK));
			log.info("The Help Link title '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.footerHelpLink_XPATH))
							.getText() + "' is present");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
