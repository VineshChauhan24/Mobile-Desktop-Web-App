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
 * Class Name: VerifyNavigatingToVideoPageClickingOnPlayListTitleFromPlayList
 * Description: This test script verifies navigating video page after clicking
 * on play list title from play list. list. Author: Manoj
 * **/

public class VerifyNavigatingToVideoPageClickingOnPlayListTitleFromPlayList
		extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyNavigatingToVideoPageClickingOnPlayListTitleFromPlayList()
			throws Exception {

		try {

			log.info("Script: VerifyNavigatingToVideoPageClickingOnPlayListTitleFromPlayList");
			log.info("***********************************************************************");

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

				// Verify Play List title
				assertTrue(
						"Play list Title name not present in play list",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.playlistTitle_XPATH)));
				log.info("The title '"
						+ driver.findElement(
								By.xpath(XpathObjectRepo.playlistTitle_XPATH))
								.getText() + "' is present in play list");

				// Click on play list title
				PlayListsFun.clickOnPlayListTitleFromPlayList();

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
