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
 * Class Name: VeriyVideoLastUpdatedInChannelPage Description: This test cases
 * verifies last update value is displaying for videos of video section in
 * channel detail page. Author: Manoj
 * **/

public class VeriyVideoLastUpdatedInChannelPage extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVeriyVideoLastUpdatedInChannelPage() throws Exception {

		try {

			// Test script
			log.info("Script: VeriyVideoLastUpdatedInChannelPage");
			log.info("******************************************");

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

			// Verify shows present in Watchable SHOWS OF THE WEEK row

			int channelCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
					.size();
			if (channelCount > 0) {

				// Click on first show from Watchable SHOWS OF THE WEEK section

				ChannelPageFun.clickOnFirstFeaturedChannelLink();

				// Verify successfully navigate to show details page
				AssertionRepoFunctions.assertChannelPageTitle();

				// This method is to scroll UI to video Section.
				ChannelPageFun.scrollToVideoSection();
				Thread.sleep(sleepTime);

				// Verify video title in show detail page
				assertTrue(
						"Video title is not present in shows detail page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.channelPageVideoSectionTitle_XPATH)));
				log.info("Video title is present in shows detail page");

				int videoCount = driver.findElements(
						By.xpath(XpathObjectRepo.channelPageVideoIcon_XPATH))
						.size();

				if (videoCount > 0) {
					log.info(videoCount

							+ " Videos are present in first page of the video section in shows page");
					log.info("Following Videos are present in first page of the video section in shows page");
					log.info("-------------------------------------------------------------------------------");

					for (int i = 1; i <= videoCount; i++) {

						log.info(driver.findElement(
										By.xpath(XpathObjectRepo.channelPagePageVideoNamePartOne_XPATH

												+ i
												+ XpathObjectRepo.channelPagePageVideoNamePartTwo_XPATH))
										.getText());

						// Verify last updated value is displaying in Video
						assertTrue(
								"Last updated value is not displaying over Video",
								CommonFun.isElementPresent(
										driver,
										By.xpath(XpathObjectRepo.featuredVideoChannelTitle_XPATH
												+ i
												+ XpathObjectRepo.featuredVideoLastModified_XPATH)));
						log.info("Last updated value is displaying over the Video");

						String lastUpdatedd = driver
								.findElement(
										By.xpath(XpathObjectRepo.featuredVideoChannelTitle_XPATH
												+ i
												+ XpathObjectRepo.featuredVideoLastModified_XPATH))
								.getText().trim();

						// String array[] = channelNameAndLastUpdate.split("/");

						log.info("Last Updated: " + lastUpdatedd);
						log.info("");
					}

				} else {
					log.error("Videos are not present in first page of the video section in shows page");
				}
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
