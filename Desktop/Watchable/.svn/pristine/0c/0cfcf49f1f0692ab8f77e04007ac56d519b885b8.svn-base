package comcast.test.app.testCases.homePage;

import org.junit.Test;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyClickingOnWatchableHeaderLogoNavigatesHomePage Description:
 * This test case verifies Clicking on header Logo from any other page user is
 * navigating back to home page Author: Manoj
 * **/

public class VerifyClickingOnWatchableHeaderLogoNavigatesHomePage extends
		BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickingOnWatchableHeaderLogoNavigatesHomePage()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyClickingOnWatchableHeaderLogoNavigatesHomePage");
			log.info("************************************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method is to scroll UI to featured shows Section.
			HomePageCommonFunctions.scrollToPopularChannelsSection();
			Thread.sleep(sleepTime);

			// Click on first show name from Watchable SHOWS OF THE WEEK section

			HomeFun.clickOnFirstChannelLink();

			// Click on Top Watchable logo from channel detail page to navigate
			// back
			// to home page

			HomeFun.clickOnTopWatchableLogo();

			// Verify user navigate back to home page after clicking on
			// Watchable log from other page
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully Navigated to Home page after clicking on Home link");
			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
