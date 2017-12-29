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
 * Class Name: VerifySinglePlayListContents Description: This test script
 * verifies the contents of a single Play List. 
 * Author: Manoj
 * **/

public class VerifySinglePlayListContents extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifySinglePlayListContents() throws Exception {

		try {

			log.info("Script: VerifySinglePlayListContents");
			log.info("**************************************");

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

			// Click on 'Playlists' menu
			PlayListsFun.clickOnPlaylistsMenu();

			// Verify All Play Lists page title
			AssertionRepoFunctions.assertAllPlayListsPageTitle();

			int playListCount = driver.findElements(
					By.xpath(XpathObjectRepo.allPlaylistsIcon_XPATH)).size();
			if (playListCount > 0) {

				/*
				 * //Genre button and watch links are removed now // Verify
				 * Genre button in Play List assertTrue(
				 * "Genre button not present in play list",
				 * CommonFun.isElementPresent( driver,
				 * By.xpath(XpathObjectRepo.playlistGenreButton_XPATH)));
				 * log.info("Genre button present in play list");
				 * 
				 * // Verify Watch link in Play List assertTrue(
				 * "Watch link not present in play list",
				 * CommonFun.isElementPresent(driver, By
				 * .xpath(XpathObjectRepo.playlistWatchLink_XPATH)));
				 * log.info("Watch link present in play list");
				 */
				// Verify Play Icon in Play List
				assertTrue("Play Icon not present in play list",
						CommonFun.isElementPresent(driver, By
								.xpath(XpathObjectRepo.playlistPlayIcon_XPATH)));
				log.info("Play Icon present in play list");

				// Verify Play List title
				assertTrue(
						"Play list Title name not present in play list",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.playlistTitle_XPATH)));
				log.info("The title '"
						+ driver.findElement(
								By.xpath(XpathObjectRepo.playlistTitle_XPATH))
								.getText() + "' is present in play list");

				// Verify Play List description
				assertTrue(
						"Play list description not present in play list",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.playlistDesc_XPATH)));
				log.info("The description '"
						+ driver.findElement(
								By.xpath(XpathObjectRepo.playlistDesc_XPATH))
								.getText() + "' is present in play list");

				/*
				 * //Detail removed now // Verify Play List video detail
				 * assertTrue(
				 * "Play list video detail not present in play list",
				 * CommonFun.isElementPresent( driver,
				 * By.xpath(XpathObjectRepo.playlistVideoDetail_XPATH)));
				 * log.info("The video detail '" + driver.findElement(
				 * By.xpath(XpathObjectRepo.playlistVideoDetail_XPATH))
				 * .getText() + "' is present in play list");
				 */

				// Verify featured video data display

				assertTrue(
						"Play list data detail is not displaying in play list",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.playlistData_XPATH)));

				log.info("Number of videos and Total duration: "
						+ driver.findElement(
								By.xpath(XpathObjectRepo.playlistData_XPATH))
								.getText());

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
