package comcast.test.app.testCases.channelPage;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.channelPage.channelPageFunctions.ChannelPageFun;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyUserNavigatingToHomePageLogoutFromShowsPage Description:
 * This test case verifies user is navigating back to home page if user logout
 * from Shows page. Author: Manoj
 * **/

public class VerifyUserNavigatingToHomePageLogoutFromShowsPage extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyUserNavigatingToHomePageLogoutFromShowsPage()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyUserNavigatingToHomePageLogoutFromShowsPage");
			log.info("**********************************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Login to Watchable application
			LoginFun.loginToWatchableApplication(driver, UILablesRepo.EMAIL,
					UILablesRepo.PASSWORD);
			WebElement loginError = driver.findElement(By
					.id(XpathObjectRepo.loginError_ID));

			if (loginError.isDisplayed() == false) {

				// This method is to scroll UI to Watchable SHOWS OF THE WEEK
				// Section.
				HomePageCommonFunctions.scrollToPopularChannelsSection();
				Thread.sleep(sleepTime);

				// Verify shows present in Watchable SHOWS OF THE WEEK row

				int channelCount = driver.findElements(
						By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
						.size();
				if (channelCount > 0) {

					// Click on first show from Watchable SHOWS OF THE WEEK
					// section

					ChannelPageFun.clickOnFirstFeaturedChannelLink();

					// Verify successfully navigate to show details page
					AssertionRepoFunctions.assertChannelPageTitle();

					// Verify channel page title
					log.info("The Shows page title displayed is '"
							+ driver.getTitle() + "'");

					// Logout from Watchable Application.
					LoginFun.logOut(driver);

					// Verify user is navigate back to Home Page after logout
					// from
					// My Shows Page
					AssertionRepoFunctions.assertWatchableTitle();
					log.info("Successfully navigate back to Home Page after logout from Shows Page");

					log.info("");

				} else {
					log.error("Shows are not present in Watchable SHOWS OF THE WEEK section in home page");
					log.info("");
				}
			}
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
