package comcast.test.app.testCases.misc;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyPageNotFoundPageContent Description: This test case
 * verifies the content of error page (Page not found) which displays when any
 * error occurs in application. 
 * Author: Manoj
 **/

public class VerifyPageNotFoundPageContent extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyPageNotFoundPageContentL() throws Exception {

		try {

			log.info("Script: VerifyPageNotFoundPageContent");
			log.info("*************************************");

			// Navigate to the Error page (Page Not Found)
			driver.get(UILablesRepo.PAGENOTFOUNDURL);
			Thread.sleep(sleepTime);

			// Verify Error page (Page Not Found) displayed
			AssertionRepoFunctions.assertErrorPageTitle();

			// Verify Page Heading
			assertTrue(
					"Page heading is not present in error page.",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.pageNotFoundPageHeading_XPATH)));
			log.info("The page heading '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.pageNotFoundPageHeading_XPATH))
							.getText() + "' is present in error page");

			// Verify back to the home page link
			assertTrue(
					"The back to the home page link is not present in error page.",
					CommonFun.isElementPresent(driver, By
							.xpath(XpathObjectRepo.pageNotFoundHomeLink_XPATH)));
			log.info("The back to the home page link is present in error page.");

			// Verify Contact Us link
			assertTrue(
					"The Contact Us link is not present in error page.",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.pageNotFoundContactUsLink_XPATH)));
			log.info("The Contact Us link is present in error page.");

			// Verify What we're watching section title
			assertTrue(
					"What we're watching section is not present in error page.",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.pageNotFoundVideoSectionTitle_XPATH)));
			log.info("The video section title '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.pageNotFoundVideoSectionTitle_XPATH))
							.getText() + "' is present in error page");

			// Verify videos are present in What we're watching section

			int videoCount = driver
					.findElements(
							By.xpath(XpathObjectRepo.pageNotFoundVideoSectionIcon_XPATH))
					.size();

			if (videoCount > 0) {
				log.info(videoCount
						+ " Videos are present in What we're watching section.");
			} else {
				log.info(videoCount
						+ "Videos are present in What we're watching section.");
			}

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}