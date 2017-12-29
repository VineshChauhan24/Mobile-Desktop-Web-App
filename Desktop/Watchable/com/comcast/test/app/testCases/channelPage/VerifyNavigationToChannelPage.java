package comcast.test.app.testCases.channelPage;

import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.channelPage.channelPageFunctions.ChannelPageFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;


/**
 * Class Name: VerifyNavigationToChannelPage Description: This test cases
 * verifies clicking on any channel from featured channel section, user is
 * navigating to channel detail page. Author: Manoj
 * **/

public class VerifyNavigationToChannelPage extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyNavigationToChannelPage() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyNavigationToChannelPage");
			log.info("*************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method asserts Watchable SHOWS OF THE WEEK title.
			AssertionRepoFunctions.assertFeaturedChannelsTitle();

			// This method is to scroll UI to Watchable SHOWS OF THE WEEK Section.
			HomePageCommonFunctions.scrollToPopularChannelsSection();
			Thread.sleep(sleepTime);

			// Verify shows present in Watchable SHOWS OF THE WEEK row

			int channelCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
					.size();
			if (channelCount > 0) {

				log.info(channelCount
						+ " Shows are present in Watchable SHOWS OF THE WEEK section in home page");

				// Click on first show from Watchable SHOWS OF THE WEEK section

				ChannelPageFun.clickOnFirstFeaturedChannelLink();

				// Verify successfully navigate to show details page
				AssertionRepoFunctions.assertChannelPageTitle();
				log.info("");

			} else {
				log.error("Shows are not present in Watchable SHOWS OF THE WEEK section in home page");
				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
