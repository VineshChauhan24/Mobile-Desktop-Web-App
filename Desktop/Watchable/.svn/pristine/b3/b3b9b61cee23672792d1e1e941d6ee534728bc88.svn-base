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
 * Class Name: VerifySixVideosDisplayedInFeaturedPlaylistsSection Description:
 * Verifies there are a total of six video displayed under featured Play list
 * section. Author: Manoj
 * **/

public class VerifySixVideosDisplayedInFeaturedPlaylistsSection extends
		BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifySixVideosDisplayedInFeaturedPlaylistsSection()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifySixVideosDisplayedInFeaturedPlaylistsSection");
			log.info("***********************************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Section heading removed fron new design
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

				assertEquals(
						"Featured Playlists section does not contains 4 videos",
						6, videoCount);
				log.info("Featured Playlists section contains 6 videos");

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
