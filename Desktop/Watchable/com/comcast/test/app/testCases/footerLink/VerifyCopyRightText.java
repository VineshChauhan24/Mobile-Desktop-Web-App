package comcast.test.app.testCases.footerLink;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyCopyRightText Description: This test case verifies the
 * presence of copy right text in the footer. Author: Manoj
 * **/

public class VerifyCopyRightText extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyCopyRightText() throws Exception {

		try {

			log.info("Script: VerifyCopyRightText");
			log.info("***************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();

			// Verify Copy right text is present in footer
			assertTrue(
					"Copy right text is not present in the footer",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerCopyRightText_XPATH)));
			log.info("Copy right text is present in the footer");

			// Logging copy right text content

			log.info("Copy right text displayed is '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.footerCopyRightText_XPATH))
							.getText() + "'");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
