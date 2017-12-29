package comcast.test.app.testCases.playLists;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.playLists.playListsFunctions.PlayListsFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyPlaylistDetailDisplayingInVideoPage Description: This test
 * script verified the display of playlist details in Video page user user
 * navigate to video page from play list. 
 * Author: Manoj
 * **/

public class VerifyPlaylistDetailDisplayingInVideoPage extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyPlaylistDetailDisplayingInVideoPage()
			throws Exception {

		try {

			log.info("Script: VerifyPlaylistDetailDisplayingInVideoPage");
			log.info("**************************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			Thread.sleep(sleepTime);

			// Verify Play List menu is present
			assertTrue(
					"Play Lists menu is not present",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.playLists_XPATH)));
			log.info("Play Lists menu is present");

			// Click on 'PLAYLISTS' menu
			PlayListsFun.clickOnPlaylistsMenu();

			// Verify All Play Lists page title
			AssertionRepoFunctions.assertAllPlayListsPageTitle();
			int playListCount = driver.findElements(
					By.xpath(XpathObjectRepo.allPlaylistsIcon_XPATH)).size();
			if (playListCount > 0) {

				// Verify Play Icon in Play List
				assertTrue("Play Icon not present in play list",
						CommonFun.isElementPresent(driver, By
								.xpath(XpathObjectRepo.playlistPlayIcon_XPATH)));
				log.info("Play Icon present in play list");

				// Click on Play Icon
				PlayListsFun.clickOnPlayIconFromPlayList();

				// Verify Video page title
				AssertionRepoFunctions.assertVideoPageTitleFromPlayList();
				log.info("The Video page title displayed after navigating from play list is '"
						+ driver.getTitle() + "'");

				// Scroll UI to Up Next section.
				HomePageCommonFunctions.scrollToPopularShowsSection();
				Thread.sleep(sleepTime);

				// Verify Play list details is displayed in Video page

				assertTrue(
						"Play list details is not displayed in Video page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.videoPagePlaylistDetails_XPATH)));
				log.info("The play list details '"
						+ driver.findElement(
								By.xpath(XpathObjectRepo.videoPagePlaylistDetails_XPATH))
								.getText() + "' displayed in video page");

				log.info("");
			} else {
				log.info("Play Lists does NOT contains Play list");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
