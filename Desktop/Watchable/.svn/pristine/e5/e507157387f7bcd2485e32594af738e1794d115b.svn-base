package comcast.test.app.testCases.homePage;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyGenreButtonDisplayedInFeaturedPlaylistsVideos Description:
 * Verifies genre button are displayed in all four videos displayed under
 * featured Play list section. Author: Manoj
 * **/

public class VerifyGenreButtonDisplayedInFeaturedPlaylistsVideos extends
		BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyGenreButtonDisplayedInFeaturedPlaylistsVideos()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyGenreButtonDisplayedInFeaturedPlaylistsVideos");
			log.info("***********************************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Section title removed from new UI
			// This method asserts Featured Playlists title.
			// assertionFunction.assertFeaturedPlaylistsTitle();

			// Verify FEATURED PLAYLIST VIDEOS
			assertTrue(
					"Featured Playlists video row is not present in home page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.playlistsVideosRow_XPATH)));
			log.info("Featured Playlists video row is present in home page");

			// Verify Video present in FEATURED PLAYLIST VIDEOS row

			int videoCount = driver.findElements(
					By.xpath(XpathObjectRepo.playlistsVideosIcon_XPATH)).size();
			if (videoCount > 0) {

				int genreButton = driver.findElements(
						By.xpath(XpathObjectRepo.playlistsGenreButton_XPATH))
						.size();
				if (genreButton > 0) {
					assertEquals(
							"Genre buttons is not present in all 6 videos in playlist",
							6, genreButton);
					log.info("Genre buttons is present in all 6 videos in playlist");
				}

				// Verify the genre buttons are displayed over videos
				for (int i = 1; i <= genreButton; i++) {

					driver.findElement(
							By.xpath("//div[@id='featured_playlist']/descendant::li["
									+ i
									+ "]/descendant::div[contains(@class,'channel_title_box')]/descendant::div"))
							.isDisplayed();
					log.info("Genre button is displayed on video " + i);

				}
				log.info("");

			} else {
				log.error("Featured Playlists row does not contain videos");
				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
