package comcast.test.app.testCases.providerPage;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.channelPage.channelPageFunctions.ChannelPageFun;
import comcast.test.app.testCases.providerPage.providerPageFunctions.providerPageFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyChannelVideoCount Description: This test case verified the
 * channel and video count displaying in provider detail page. Author: Manoj
 * **/

public class VerifyChannelVideoCount extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyChannelVideoCount() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyChannelVideoCount");
			log.info("*******************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method is to scroll UI to Watchable SHOWS OF THE WEEK
			// Section.
			HomePageCommonFunctions.scrollToPopularChannelsSection();
			Thread.sleep(sleepTime);

			// This method asserts Watchable SHOWS OF THE WEEK title.
			AssertionRepoFunctions.assertFeaturedChannelsTitle();

			// Verify shows present inWatchable SHOWS OF THE WEEK row

			int channelCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
					.size();
			if (channelCount > 0) {

				log.info(channelCount
						+ " Shows are present in Watchable SHOWS OF THE WEEK section in home page");

				// Click on first shows from featured Channel section

				ChannelPageFun.clickOnFirstFeaturedChannelLink();

				// Verify successfully navigate to shows details page
				AssertionRepoFunctions.assertChannelPageTitle();

				// Verify provider name in shows detail page
				assertTrue(
						"Provider name is not present in channel detail page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.channelPageProviderTitle_XPATH)));
				log.info("Provider name is present in channel detail page");

				// Click on Provider title from shows detail page
				providerPageFun.clickOnProviderLinkFromChannelPage();

				// Verify successfully navigate to Provider details page
				assertionFunction.assertProviderPageTitle();

				// Verify channel/video count in provider page
				assertTrue(
						"Shows/video count is not present in provider detail page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.providerPageChannelVideoCount_XPATH)));
				log.info("Shows/video count is present in provider detail page");

				String channelVideoCount = driver
						.findElement(
								By.xpath(XpathObjectRepo.providerPageChannelVideoCount_XPATH))
						.getText();
				String array[] = channelVideoCount.split("/");
				log.info("   Channel Count: " + array[0]);
				log.info("   Video Count: " + array[1]);
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
