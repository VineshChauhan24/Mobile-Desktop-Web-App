package comcast.test.app.testCases.playLists;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.playLists.playListsFunctions.PlayListsFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name:
 * VerifyNavigatingToVideoPageClickingOnPlayListVideoDataFromPlayList
 * Description: This test script verifies navigating video page after clicking
 * on play data from play list. 
 * Author: Manoj
 * **/

public class VerifyNavigatingToVideoPageClickingOnPlayListVideoDataFromPlayList
		extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyNavigatingToVideoPageClickingOnPlayListVideoDataFromPlayList()
			throws Exception {

		try {

			log.info("Script: VerifyNavigatingToVideoPageClickingOnPlayListVideoDataFromPlayList");
			log.info("********************************************************************");

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

				// Verify play list data in Play List
				assertTrue(
						"Play list data not present in play list",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.playlistData_XPATH)));
				log.info("Play list data present in play list");

				// Click on play list data
				PlayListsFun.clickOnPlayListDataFromPlayList();

				// Verify Video page title
				AssertionRepoFunctions.assertVideoPageTitleFromPlayList();
				log.info("The Video page title displayed after navigating from play list is '"
						+ driver.getTitle() + "'");

				// Verify video player is displayed in video page
				AssertionRepoFunctions.assertVideoPlayer();

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
