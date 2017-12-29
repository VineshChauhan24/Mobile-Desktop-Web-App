package comcast.test.app.testCases.playLists;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.playLists.playListsFunctions.PlayListsFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyDisplayOfHeaderAndFooterInPlayListsPage Description: This
 * test case verify the header and footer sections are present in All play lists page. 
 * Author: Manoj
 * **/

public class VerifyDisplayOfHeaderAndFooterInPlayListsPage extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyDisplayOfHeaderAndFooterInPlayListsPage()
			throws Exception {

		try {

			log.info("Script: VerifyDisplayOfHeaderAndFooterInPlayListsPage");
			log.info("*****************************************************");

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

			// Verify header section is present in All Play Lists page
			assertTrue(
					"Header section is NOT present in All Play Lists page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.header_XPATH)));
			log.info("Header section is present in All Play Lists page");

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();
			Thread.sleep(sleepTime);

			// Verify footer section is present in All Play Lists page
			assertTrue(
					"Footer section is NOT present in All Play Lists page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footer_XPATH)));
			log.info("Fotter section is present in All Play Lists page");

			// Verifying Footer Copy Right is present in All Play Lists page
			Thread.sleep(LessSleepTime);
			assertTrue(
					"Copyright text is not present in in All Play Lists page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerCopyRightText_XPATH)));
			log.info("Copyright text is present in in All Play Lists page");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
