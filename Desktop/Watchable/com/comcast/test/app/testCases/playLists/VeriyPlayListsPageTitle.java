package comcast.test.app.testCases.playLists;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.playLists.playListsFunctions.PlayListsFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VeriyPlayListsPageTitle Description: This test case verify the
 * title of All play lists page. Author: Manoj
 * **/

public class VeriyPlayListsPageTitle extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVeriyPlayListsPageTitle() throws Exception {

		try {

			log.info("Script: VeriyPlayListsPageTitle");
			log.info("*******************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			Thread.sleep(sleepTime);

			// Verify Play List menu is present
			assertTrue(
					"Play List menu is not present",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.playLists_XPATH)));
			log.info("Play List menu is present");

			// Verify Play List menu Title
			assertTrue(driver
					.findElement(By.xpath(XpathObjectRepo.playLists_XPATH))
					.getText().matches(UILablesRepo.TOPMENU_PLAYLISTS));

			// Click on 'PLAYLISTS' menu
			PlayListsFun.clickOnPlaylistsMenu();

			// Verify All Play Lists page title
			AssertionRepoFunctions.assertAllPlayListsPageTitle();
			log.info("The All Play Lists page title displayed is '"
					+ driver.getTitle() + "'");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
