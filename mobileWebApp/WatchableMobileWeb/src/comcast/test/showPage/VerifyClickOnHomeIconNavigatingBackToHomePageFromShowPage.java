package comcast.test.showPage;

import static comcast.util.PropertyFileReader.ObjRepoProp;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import comcast.common.asserts.Assertions;
import comcast.config.BaseTest;
import comcast.custom.CustomFun;
import comcast.test.homepage.homePageFunctions.HomeFun;
import comcast.test.showPage.showPageFunctions.ShowFun;
import comcast.uiFunctions.GUIFunctions;

/**
 * 
 * @author Manoj.Paragen
 * @description: This test script verifies navigating back to home page from
 *               show page by clicking on Home icon.
 * @Created on 10-June-2016 Last updated on 10-June-2016
 * 
 */

public class VerifyClickOnHomeIconNavigatingBackToHomePageFromShowPage extends
		BaseTest {

	int videoCount = 0;

	/**
	 * Open the browser, Enter the Watchable URL
	 */
	@Test(description = "Step 1: Open the browser, Enter the Watchable URL", priority = 1)
	public void Step01_NavigeteToURL() throws Exception {

		// Navigates to Watchable mobile web URL
		HomeFun.navigateToWatchable_URL();

		// Verify successfully navigate to home page
		Assertions.assertWatchableTitle();
		log.info("Successfully navigated to Watchable Mobile web Home page  \n");
		Reporter.log("<p>Successfully navigated to Watchable Mobile web Home page");

	}

	/**
	 * Verify videos are present in playlist.
	 */
	@Test(description = "Step 2: Verify videos are present in playlist.", priority = 2)
	public void Step02_VerifyVideosPresentInPlaylist() throws Exception {

		// Move to Play list video section
		GUIFunctions.mouseOverElement(driver, driver.findElement(By
				.xpath(ObjRepoProp
						.getProperty("homePagePlaylistVideoSection_XPATH"))));

		// Verify videos are present in playlist.

		videoCount = driver.findElements(
				By.xpath(ObjRepoProp.getProperty("playlistVideIcon_XPATH")))
				.size();

		Assert.assertTrue(videoCount > 0, "Videos are not present in playlist.");

		log.info("Videos are present in playlist.\n");
		Reporter.log("<p>Videos are present in playlist.");

		log.info(videoCount + " Videos are present in playlist.\n");
		Reporter.log("<p>" + videoCount + " Videos are present in playlist.");

	}

	/**
	 * Click on show name from first video.
	 */
	@Test(description = "Step 3: Click on show name from first video.", priority = 3)
	public void Step03_VerifyNavigateToShowPage() throws Exception {

		if (videoCount > 0) {

			String showTitleClicked = driver.findElement(
					By.xpath(ObjRepoProp
							.getProperty("playlistFirstVideShowTitle_XPATH")))
					.getText();

			log.info("showTitleClicked: " + showTitleClicked);

			// Click on first show title from video section
			ShowFun.clickOnFirstVideoTitle();

			// Verify user navigated to show page

			String showPageShowTitle = driver.findElement(
					By.xpath(ObjRepoProp
							.getProperty("ShowPageFirstVideShowTitle_XPATH")))
					.getText();

			log.info("showPageShowTitle: " + showPageShowTitle);

			// User assert equal (clicked show name and show page show name)

			Assert.assertEquals(showTitleClicked, showPageShowTitle,
					"Failed to navigate to Show Page");

			log.info("successfully navigates to show page.\n");
			Reporter.log("<p>successfully navigates to show page.");

		}

	}

	/**
	 * Verify home icon is present in show page.
	 */
	@Test(description = "Step 4: Verify home icon  is present in show page.", priority = 4)
	public void Step04_VerifyHomeIconInShowPage() throws Exception {

		// Verify home icon is present in show page.

		Assert.assertTrue(CustomFun.isElementPresent(
				By.xpath(ObjRepoProp.getProperty("ShowPageHomeIcon_XPATH")),
				driver), "Home icon is not present in show page.");

		log.info("Home icon is present in show page.\n");
		Reporter.log("<p>Home icon is present in show page.");

	}

	/**
	 * Click on Home icon from show page.
	 */
	@Test(description = "Step 5: Click on Home icon from show page.", priority = 5)
	public void Step05_ClickOnHomeIconFromShowPage() throws Exception {

		// Click on Home icon from show page.
		ShowFun.clickOnHomeIcont();

		// Verify User successfully navigates back to home page.

		Assertions.assertWatchableTitle();
		log.info("Successfully navigate back to Watchable Mobile web Home page after clicking Home icon from show page\n");
		Reporter.log("<p>Successfully navigate back to Watchable Mobile web Home page after clicking Home icon from show page");

	}

}
