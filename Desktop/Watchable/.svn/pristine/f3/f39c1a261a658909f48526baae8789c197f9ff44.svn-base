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
 * Class Name: VerifyAdChoicesLinkTitle Description: This test case verifies the
 * presence of Ad Choices link in the footer and title of Ad Choices link.
 * Author: Manoj
 * **/

public class VerifyAdChoicesLinkTitle extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyAdChoicesLinkTitle() throws Exception {

		try {

			log.info("Script: VerifyAdChoicesLinkTitle");
			log.info("********************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();

			// Verify Ad Choices Link is present in footer
			assertTrue(
					"Ad Choices Link is not present",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerAdChoicesLink_XPATH)));
			log.info("Ad Choices Link is present");

			// Verify Privacy Policy Link Title
			assertTrue(
					"Title not present for Ad Choices link in Footer",
					driver.findElement(
							By.xpath(XpathObjectRepo.footerAdChoicesLink_XPATH))
							.getText()
							.toUpperCase()
							.trim()
							.replaceAll(" ", "")
							.matches(
									UILablesRepo.FOOTER_ADCHOICES_LINK
											.toUpperCase().trim()));
			log.info("The Ad Choices Link title '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.footerAdChoicesLink_XPATH))
							.getText() + "' is present");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
