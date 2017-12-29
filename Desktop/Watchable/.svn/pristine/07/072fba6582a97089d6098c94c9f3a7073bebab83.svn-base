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
 * Class Name: VerifyChannelDetailPageContent Description: This test cases
 * verifies the contents of channel detail page. Author: Manoj
 * **/

public class VerifyChannelDetailPageContent extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyChannelDetailPageContent() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyChannelDetailPageContent");
			log.info("**************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method is to scroll UI to Watchable shows of the week
			// Section.
			HomePageCommonFunctions.scrollToPopularChannelsSection();
			Thread.sleep(sleepTime);

			// Verify Channel present in Watchable shows of the week row

			int channelCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
					.size();
			if (channelCount > 0) {

				// Click on first show from Watchable shows of the week section

				ChannelPageFun.clickOnFirstFeaturedChannelLink();

				// Verify successfully navigate to show details page
				AssertionRepoFunctions.assertChannelPageTitle();

				// Verify channel icon in show detail page
				assertTrue(
						"Shows icon is not present in Shows detail page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.channelPageChannelIcon_XPATH)));
				log.info("Shows icon is present in Shows detail page");

				// Verify channel title in show detail page
				assertTrue(
						"Shows name title is not present in Shows detail page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.channelPageChannelTitle_XPATH)));
				log.info("Shows name title is present in Shows detail page");

				// Verify provider name in show detail page
				assertTrue(
						"Provider name is not present in Shows detail page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.channelPageProviderTitle_XPATH)));
				log.info("Provider name is present in Shows detail page");

				// Verify channel description in show detail page
				assertTrue(
						"Shows description is not present in Shows detail page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.channelPageChannelDescription_XPATH)));
				log.info("Shows description is present in Shows detail page");

				// Verify video title in show detail page
				assertTrue(
						"Video title is not present in Shows detail page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.channelPageVideoSectionTitle_XPATH)));
				log.info("Video title is present in Shows detail page");

				// Verify videos present in show detail page

				int videoCount = driver.findElements(
						By.xpath(XpathObjectRepo.channelPageVideoIcon_XPATH))
						.size();
				if (videoCount > 0) {

					log.info("Videos are present in Shows detail page");

				} else {
					log.info("Videos are not present in Shows detail page");
				}

				// This method is to scroll UI to other Channels Section.
				ChannelPageFun.scrollToOtherChannelsSection();
				Thread.sleep(sleepTime);
				// Verify next page button in video section of show detail
				// page
				assertTrue(
						"Next page button is not present in video section of Shows detail page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.channelPageNextPageButton_XPATH)));
				log.info("Next page button is present in video section of Shows detail page");

				// Verify previous page button in video section of show
				// detail page
				assertTrue(
						"Previous page button is not present in video section of Shows detail page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.channelPagePreviousPageButton_XPATH)));
				log.info("Previous page button is present in video section of Shows detail page");

				// Verify page number in video section of show detail page
				assertTrue(
						"Page number is not present in video section of Shows detail page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.channelPagePageNumberButton_XPATH)));
				log.info("Page number is present in video section of Shows detail page");

				int otherShowPresent = driver
						.findElements(
								By.xpath(XpathObjectRepo.channelPageOtherSectionTitle_XPATH))
						.size();
				if (otherShowPresent > 0) {
					// Verify other channel section in show detail page
					assertTrue(
							"Other show section is not present in Shows detail page",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.channelPageOtherSectionTitle_XPATH)));
					log.info("Other show section is present in Shows detail page for selected show");
				} else {
					log.info("Other show section is NOT present in Shows detail page for selected show");
				}

				log.info("");

			} else {
				log.error("shows are not present in Watchable shows of the week section in home page");
				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
