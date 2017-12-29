package comcast.test.app.testCases.homePage;

import org.junit.Test;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyHomePageTitle Description: This test case verifies the
 * title of the home page.
 * **/

public class VerifyHomePageTitle extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyHomePageTitle() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyHomePageTitle");
			log.info("********************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify Help page title
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("The home page title displayed is '" + driver.getTitle()
					+ "'");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
