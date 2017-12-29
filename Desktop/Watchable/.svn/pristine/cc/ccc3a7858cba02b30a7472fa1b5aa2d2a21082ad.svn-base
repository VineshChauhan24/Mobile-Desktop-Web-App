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
 * Class Name: VerifyClickOnContachUsLinkFromErrorPageOpensContactUsForm
 * Description: This test case verifies clicking on Contact Us link from error
 * page, Opening contact us form. 
 * Author: Manoj
 **/

public class VerifyClickOnContachUsLinkFromErrorPageOpensContactUsForm extends
		BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickOnContachUsLinkFromErrorPageOpensContactUsForm()
			throws Exception {

		try {

			log.info("Script: VerifyClickOnContachUsLinkFromErrorPageOpensContactUsForm");
			log.info("******************************************************************");

			// Navigate to the Error page (Page Not Found)
			driver.get(UILablesRepo.PAGENOTFOUNDURL);
			Thread.sleep(sleepTime);

			// Verify Error page (Page Not Found) displayed
			AssertionRepoFunctions.assertErrorPageTitle();

			// Verify Contact Us link
			assertTrue(
					"The Contact Us link is not present in error page.",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.pageNotFoundContactUsLink_XPATH)));
			log.info("The Contact Us link is present in error page.");

			// Click on contact us link

			MiscFun.clickOnContactUsLink();

			// Verify Contact Us form opened successfully
			assertTrue(
					"Contact Us form is not opened",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.contactUsForm_XPATH)));
			log.info("Contact Us form opened successfully");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}