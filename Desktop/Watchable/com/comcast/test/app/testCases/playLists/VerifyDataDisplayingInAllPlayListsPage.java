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
 * Class Name: VerifyDataDisplayingInAllPlayListsPage 
 * Description: This test script verifies data is displaying in All Play Lists page. 
 * Author: Manoj
 * **/

public class VerifyDataDisplayingInAllPlayListsPage extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyDataDisplayingInAllPlayListsPage() throws Exception {

		try {

			log.info("Script: VerifyDataDisplayingInAllPlayListsPage");
			log.info("**********************************************");

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

			// Verify All Play Lists page title allPlaylistsIcon_XPATH
			AssertionRepoFunctions.assertAllPlayListsPageTitle();

			// Verify Play Lists Title
			assertTrue(
					"Title not present for all play lists",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.allPlaylistsTitle_XPATH)));

			log.info("The Title '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.allPlaylistsTitle_XPATH))
							.getText() + "' is present for all play lists");

			// Verify all play lists contains play lists

			int playListCount = driver.findElements(
					By.xpath(XpathObjectRepo.allPlaylistsIcon_XPATH)).size();
			if (playListCount > 0) {
				log.info("All Play Lists page contains Data");
				log.info(playListCount
						+ " Play Lists are displaying in All Play lista page");
			} else {
				log.info("All Play Lists page does not contains data");
			}
			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
