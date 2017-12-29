package comcast.test.app.testCases.channelPage;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.channelPage.channelPageFunctions.ChannelPageFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyNavigationToProviderPageFromOtherChannelsSection
 * Description: This test cases verifies clicking on provide name from other
 * channel section title in channel detail page navigating to channel detail
 * page. Author: Manoj
 * **/

public class VerifyNavigationToProviderPageFromOtherChannelsSection extends
		BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyNavigationToProviderPageFromOtherChannelsSection()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyNavigationToProviderPageFromOtherChannelsSection");
			log.info("**************************************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method is to scroll UI toWatchable Watchable shows of the
			// week Section.
			HomePageCommonFunctions.scrollToPopularChannelsSection();
			Thread.sleep(sleepTime);

			// Verify shows present in Watchable shows of the week row

			int channelCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
					.size();
			if (channelCount > 0) {

				// Click on first show from Watchable shows of the week section

				ChannelPageFun.clickOnFirstFeaturedChannelLink();

				// Verify successfully navigate to show details page
				AssertionRepoFunctions.assertChannelPageTitle();

				// This method is to scroll UI to other shows Section.
				ChannelPageFun.scrollToOtherChannelsSection();
				Thread.sleep(sleepTime);

				int otherShowPresent = driver
						.findElements(
								By.xpath(XpathObjectRepo.channelPageOtherSectionTitle_XPATH))
						.size();
				if (otherShowPresent > 0) {

					// Verify other show section in show detail page
					assertTrue(
							"Other shows section is not present in shows detail page",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.channelPageOtherSectionTitle_XPATH)));
					log.info("Other shows section is present in shows detail page");

					// Verify other shows section title contains Provider name
					assertTrue(
							"Other shows section title does not contain Provider name",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.channelPageOtherSectionProviderTitle_XPATH)));
					log.info("Other shows section title contain Provider name");

					// Click on Provider name from other shows title
					ChannelPageFun.clickOnProviderLinkFromOtherChannel();

					// Verify successfully navigate to Provider details page
					assertionFunction.assertProviderPageTitle();
				} else {
					log.info("Other show section is NOT present in show detail page of selected show");
				}

				log.info("");

			} else {
				log.error("Shows are not present in Watchable Watchable shows of the week section in home page");
				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
