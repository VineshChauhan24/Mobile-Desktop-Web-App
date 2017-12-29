package comcast.test.app.testCases.homePage;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;

//import org.openqa.selenium.WebElement;
//import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
//import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
//import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyHeaderWatchableLogoNotClickableInHomePage Description: This
 * test case validates Header Watchable logo is not clickable when user is in
 * home page n.
 * **/

public class VerifyHeaderWatchableLogoNotClickableInHomePage extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyHeaderWatchableLogoNotClickableInHomePage()
			throws Exception {

		try {
			log.info("Script: VerifyHeaderWatchableLogoNotClickableInHomePage");
			log.info("******************************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Verifying Watchable Logo is present in header
			Thread.sleep(LessSleepTime);
			assertTrue(
					"Watchable Logo is not present in header",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.watchableTopLogo_XPATH)));
			log.info("Watchable Logo is present in header");

			// Verify Header Watchable log is not clickable in Home page

			boolean match = false;
			CommonFun.isElementClickable(driver.findElement(By
					.xpath(XpathObjectRepo.watchableTopLogo_XPATH)),
					"Watchable Logo", false);

			log.info(match);
			assertFalse("The header WATCHABLE logo is clickable in Home Page",
					match);

			log.info("The header WATCHABLE logo is NOT clickable in Home Page");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
