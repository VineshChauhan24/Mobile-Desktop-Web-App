package comcast.test.homepage;

import static comcast.util.PropertyFileReader.ObjRepoProp;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import comcast.common.asserts.Assertions;
import comcast.config.BaseTest;
import comcast.custom.CustomFun;
import comcast.test.homepage.homePageFunctions.HomeFun;
import comcast.uiFunctions.GUIFunctions;

/**
 * 
 * @author Manoj.Paragen
 * @description: This test script verifies video details are displaying on a
 *               single (first) video in playlist
 * @Created on 18-May-2016 Last updated on 18-May-2016
 * 
 */

public class VerifyVideosDetailsDisplayingOnVideosInPlaylist extends BaseTest {

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
	 * Verify Play list video section is present in home page.
	 */
	@Test(description = "Step 2: Verify Play list video section is present in home page.", priority = 2)
	public void Step02_VerifyPlaylistVideoSection() throws Exception {

		// Verify Play list video section is present
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("homePagePlaylistVideoSection_XPATH")), driver),
				"Play list video section is not present in home page");

		log.info("Play list video section is present in home page.\n");
		Reporter.log("<p>Play list video section is present in home page.");

	}

	/**
	 * Verify videos are present in playlist.
	 */
	@Test(description = "Step 3: Verify videos are present in playlist.", priority = 3)
	public void Step03_VerifyVideosPresentInPlaylist() throws Exception {

		// Move to Play list video section
		GUIFunctions.mouseOverElement(driver, driver.findElement(By
				.xpath(ObjRepoProp
						.getProperty("homePagePlaylistVideoSection_XPATH"))));

		// Verify videos are present in playlist.

		int videoCount = driver.findElements(
				By.xpath(ObjRepoProp.getProperty("playlistVideIcon_XPATH")))
				.size();

		Assert.assertTrue(videoCount > 0, "Videos are not present in playlist.");

		log.info("Videos are present in playlist.\n");
		Reporter.log("<p>Videos are present in playlist.");

	}

	/**
	 * Verify video name is displaying on first video
	 */
	@Test(description = "Step 4: Verify video name is displaying on first video.", priority = 4)
	public void Step04_VerifyVideoNameDisplay() throws Exception {

		// Move to Play list video section
		GUIFunctions.mouseOverElement(driver, driver.findElement(By
				.xpath(ObjRepoProp
						.getProperty("homePagePlaylistVideoSection_XPATH"))));

		log.info(driver.findElements(
				By.xpath(ObjRepoProp
						.getProperty("playlistFirstVideTitle_XPATH"))).size());

		// Verify videos name

		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("playlistFirstVideTitle_XPATH")), driver),
				"Video name is not displaying on first video");

		log.info("Video Name Displayed is: "
				+ driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("playlistFirstVideTitle_XPATH")))
						.getText() + "\n");
		Reporter.log("<p>Video Name Displayed is: "
				+ driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("playlistFirstVideTitle_XPATH")))
						.getText());

	}

	/**
	 * Verify video Show name is displaying on first video
	 */
	@Test(description = "Step 5: Verify video Show name is displaying on first video.", priority = 5)
	public void Step05_VerifyVideoShowNameDisplay() throws Exception {

		// Verify videos Show name

		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("playlistFirstVideShowTitle_XPATH")), driver),
				"Video Show name is not displaying on first video");

		log.info("Video Show Name Displayed is: "
				+ driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("playlistFirstVideShowTitle_XPATH")))
						.getText() + "\n");
		Reporter.log("<p>Video Show Name Displayed is: "
				+ driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("playlistFirstVideShowTitle_XPATH")))
						.getText());

	}

	/**
	 * Verify video duration is displaying on first video
	 */
	@Test(description = "Step 6: Verify video duration is displaying on first video.", priority = 6)
	public void Step06_VerifyVideoDurationDisplay() throws Exception {

		// Verify videos video duration

		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("playlistFirstVideDuration_XPATH")), driver),
				"Video duration is not displaying on first video");

		log.info("Video duration Displayed is: "
				+ driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("playlistFirstVideDuration_XPATH")))
						.getText() + "\n");
		Reporter.log("<p>Video duration Displayed is: "
				+ driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("playlistFirstVideDuration_XPATH")))
						.getText());

	}

}
