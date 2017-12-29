package comcast.test.app.testCases.homePage;

import static org.junit.Assert.assertTrue;
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
 * Class Name: VerifySelectedVideoDetailsInFeaturedPlaylists Description: This
 * test case verifies the details of selected video in featured Playlists
 * section 
 * Author: Manoj
 * **/

public class VerifySelectedVideoDetailsInFeaturedPlaylists extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifySelectedVideoDetailsInFeaturedPlaylists()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifySelectedVideoDetailsInFeaturedPlaylists");
			log.info("*****************************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Section title is removed from new design
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

				log.info("Featured Playlists row contains videos");
				log.info("The details of selected video in featured Playlists section");
				log.info("--------------------------------------------------------------");

				log.info("Name: "
						+ driver.findElement(
								By.xpath(XpathObjectRepo.playlistsSelectedVideoTitle_XPATH))
								.getText());

				log.info("Description: "
						+ driver.findElement(
								By.xpath(XpathObjectRepo.playlistsSelectedVideoDesc_XPATH))
								.getText());

				log.info("   Number of videos and Total duration: "
						+ driver.findElement(
								By.xpath(XpathObjectRepo.playlistsSelectedVideoData_XPATH))
								.getText());

				/*
				 * //Video detail is removed now log.info("Video detail: " +
				 * driver.findElement(
				 * By.xpath(XpathObjectRepo.playlistsSelectedVideoDetail_XPATH))
				 * .getText());
				 */

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
