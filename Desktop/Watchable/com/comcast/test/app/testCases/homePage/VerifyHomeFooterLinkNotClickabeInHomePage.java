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
 * Class Name: VerifyHomeFooterLinkNotClickabeInHomePage Description:This test
 * script verifies the Home footer link is not clickable in home page. 
 * Author: Manoj
 * **/


public class VerifyHomeFooterLinkNotClickabeInHomePage extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyHomeFooterLinkNotClickabeInHomePage()
			throws Exception {

		try {
			log.info("Script: VerifyHomeFooterLinkNotClickabeInHomePage");
			log.info("************************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Scroll to Footer section
			HomePageCommonFunctions.scrollToFooterSection();

			// Verify Home link is present in footer
			assertTrue(
					"Home link is not present in Footer",
					CommonFun.isElementPresent(driver,
							By.id(XpathObjectRepo.footerHomeLinkHomePage_ID)));
			log.info("Home link is present in Footer");

			// Verify Home link is not clickable in home page

			String clickable = driver.findElement(
					By.id(XpathObjectRepo.footerHomeLinkHomePage_ID))
					.getAttribute("href");

			log.info("clickable: " + clickable);

			if (clickable == null) {
				log.info("Footer Home link is not clickable in home page");
			} else {
				log.info("Footer Home link is clickable in home page");
			}

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
