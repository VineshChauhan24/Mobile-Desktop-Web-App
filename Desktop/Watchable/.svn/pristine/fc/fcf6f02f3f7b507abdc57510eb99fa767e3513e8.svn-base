package comcast.test.app.testCases.homePage;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
//import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyFooterHomeLinkNotClickableInHomePage Description: This
 * test case validates Footer Home log is not clickable when user is in
 * home page n.
 * **/

public class VerifyFooterHomeLinkNotClickableInHomePage extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyFooterHomeLinkNotClickableInHomePage()
			throws Exception {

		try {
			log.info("Script: VerifyFooterHomeLinkNotClickableInHomePage");
			log.info("******************************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Scroll to Footer section
			HomePageCommonFunctions.scrollToFooterSection();

			// Verifying Home link is present in the footer
			Thread.sleep(LessSleepTime);
			assertTrue("Home link is not present in footer",
					CommonFun.isElementPresent(driver, By
							.id(XpathObjectRepo.footerHomeLinkHomePage_ID)));
			log.info("Home link is present in footer");

			// Verify Footer Home Link is not clickable in Home page

			boolean match = false;
			CommonFun.isElementClickable(driver.findElement(By
					.id(XpathObjectRepo.footerHomeLinkHomePage_ID)),
					"Home Link", false);

			log.info(match);
			assertFalse("The footer Home Link is clickable in Home Page",
					match);

			log.info("The footer Home Link is NOT clickable in Home Page");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
