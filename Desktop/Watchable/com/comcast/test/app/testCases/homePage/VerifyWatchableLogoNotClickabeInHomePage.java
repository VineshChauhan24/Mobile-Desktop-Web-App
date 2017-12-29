package comcast.test.app.testCases.homePage;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyWatchableLogoNotClickabeInHomePage Description:This test
 * script verifies the watachble logo is not clickable in home page. 
 * Author : Manoj
 * **/

public class VerifyWatchableLogoNotClickabeInHomePage extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyWatchableLogoNotClickabeInHomePage() throws Exception {

		try {
			log.info("Script: VerifyWatchableLogoNotClickabeInHomePage");
			log.info("************************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Verify Watchable logo is present in header
			assertTrue(
					"Watchable logo is not present in header",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.watchableTopLogo_XPATH)));
			log.info("Watchable logo is present in header");

			// Verify watchable logo is not clickable in home page

			String clickable = driver.findElement(
					By.xpath(XpathObjectRepo.watchableTopLogo_XPATH))
					.getAttribute("href");

			log.info("clickable: " + clickable);

			if (clickable == null) {
				log.info("Top Watchable logo is not clickable in home page");
			} else {
				log.info("Top Watchable logo is clickable in home page");
			}

			// Scroll to Footer section
			HomePageCommonFunctions.scrollToFooterSection();

			// Verify Watchable logo is present in footer
			assertTrue(
					"Watchable logo is not present in Footer",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerwatchableLogo_XPATH)));
			log.info("Watchable logo is present in Footer");

			// Verify watchable logo is not clickable in home page

			clickable = driver.findElement(
					By.xpath(XpathObjectRepo.footerwatchableLogo_XPATH))
					.getAttribute("href");

			log.info("clickable: " + clickable);

			if (clickable == null) {
				log.info("Footer Watchable logo is not clickable in home page");
			} else {
				log.info("Footer Watchable logo is clickable in home page");
			}

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
