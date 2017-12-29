package comcast.test.app.testCases.playLists;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.app.testCases.playLists.playListsFunctions.PlayListsFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name:
 * VerifyClickingOnFooterWatchableLogoFromPlayListsPageNavigatesHomePage
 * Description: This test script click on footer watchable log from All play
 * lists page, verifies user navigating back to Home page. 
 * Author: Manoj
 * **/

public class VerifyClickingOnFooterWatchableLogoFromPlayListsPageNavigatesHomePage
		extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickingOnFooterWatchableLogoFromPlayListsPageNavigatesHomePage()
			throws Exception {

		try {

			log.info("Script: VerifyClickingOnFooterWatchableLogoFromPlayListsPageNavigatesHomePage");
			log.info("*****************************************************************************");

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

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();
			Thread.sleep(sleepTime);

			// Verify Watchable logo is present in footer
			assertTrue(
					"Watchable logo is not present in footer",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerwatchableLogo_XPATH)));
			log.info("Watchable logo is present in footer");

			// Click on footer Watchable logo from All Play lists page to
			// navigate back
			// to home page

			HomeFun.clickOnBottomWatchableLogo();

			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully Navigated to Home page after clicking on footer Watchable logo from All Play lists page");
			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
