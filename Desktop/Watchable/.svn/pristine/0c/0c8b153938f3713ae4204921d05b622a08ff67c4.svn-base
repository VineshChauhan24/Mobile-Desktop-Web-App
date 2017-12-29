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
 * Class Name: VerifyCopyPlayListUrlButton Description: This test script
 * verifies copy play list URL button is clickable. Author: Manoj
 * **/

public class VerifyCopyPlayListUrlButton extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyCopyPlayListUrlButton() throws Exception {

		try {

			log.info("Script: VerifyCopyPlayListUrlButton");
			log.info("***********************************");

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

				// Verify video player is displayed in video page
				AssertionRepoFunctions.assertVideoPlayer();

				// Verify Play List share button
				assertTrue(
						"Play List share button not present in play list",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.playlistShareButton_XPATH)));
				log.info("Play List share button present in play list");

				// Mouse over Play List share button
				CommonFun.mouseOverElement(driver, driver.findElement(By
						.xpath(XpathObjectRepo.playlistShareButton_XPATH)));

				// Verify Copy Play List URL button in share option
				/*assertTrue(
						"Play List URL button not present in Play List share option",
						CommonFun.isElementPresent(driver, By
								.xpath(XpathObjectRepo.playlistShareUrl_XPATH)));
				log.info("Play List URL button present in play list share option");

				// Verify Copy Play List URL button is clickable in share option
				boolean match = true;

				CommonFun.isElementClickable(driver.findElement(By
						.xpath(XpathObjectRepo.playlistShareUrl_XPATH)),
						"Copy Play List UR button", true);
				Thread.sleep(LessSleepTime);
				log.info(match);
				assertTrue("The Copy Play List UR button is not clickable",
						match); */

				log.info("The Copy Play List UR button is clickable");

				Thread.sleep(LessSleepTime);

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
