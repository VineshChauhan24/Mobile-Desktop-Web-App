package comcast.test.app.testCases.homePage;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyDataDisplayingInAllHomePageSections 
 * Description: This test script verifies data is displaying in all categories of home page. 
 * Author: Manoj
 * **/

public class VerifyDataDisplayingInAllHomePageSections extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyDataDisplayingInAllHomePageSections()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyDataDisplayingInAllHomePageSections");
			log.info("**************************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			Thread.sleep(sleepTime);

			// Verifies data displaying in play list section

			int playListData = driver.findElements(
					By.xpath(XpathObjectRepo.playlistsVideosIcon_XPATH)).size();

			if (playListData > 0) {
				log.info("Data is displaying in play list section of home page");
				log.info(playListData
						+ "  Play lists are displaying in play list section of home page");
			}

			else {
				log.info("Data is not displaying in play list section of home page");
			}

			// This method is to scroll UI to What We're
			// WATCHING section.
			HomePageCommonFunctions.scrollToVideoSectionFromChannelSection();

			// This method is to assert the What We're WATCHING title
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.featuredSectionTitle_XPATH))
					.getText().matches(UILablesRepo.HOMEPAGE_FEATURED_VIDEOS));

			log.info("What we're watching section is present in home page");

			int videoData = driver.findElements(
					By.xpath(XpathObjectRepo.featuredVideosIcon_XPATH)).size();

			if (videoData > 0) {
				log.info("Data is displaying in What we're watching section of home page");
				log.info(videoData
						+ "  Videos are displaying in What we're watching section of home page");
			}

			else {
				log.info("Data is not displaying in What we're watching section of home page");
			}

			// This method is to assert the Watchable SHOWS OF THE WEEK title
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.featuredChannelsTitle_XPATH))
					.getText().matches(UILablesRepo.HOMEPAGE_FEATURED_CHANNELS));

			log.info("Watchable shows of the week section is present in home page");

			int showData = driver.findElements(
					By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
					.size();

			if (showData > 0) {
				log.info("Data is displaying in Watchable shows of the week section of home page");
				log.info(showData
						- 1
						+ "  Shows are displaying in Watchable shows of the week section of home page");
			}

			else {
				log.info("Data is not displaying in Watchable shows of the week section of home page");
			}

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
