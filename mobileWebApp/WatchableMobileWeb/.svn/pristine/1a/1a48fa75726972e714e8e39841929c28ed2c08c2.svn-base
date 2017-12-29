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

/**
 * 
 * @author Manoj.Paragen
 * @description: This test script verifies the metadata contents displaying on a
 *               play list
 * @Created on 26-April-2016 Last updated on 26-April-2016
 * 
 */

public class VerifyPlaylistMetadataContents extends BaseTest {

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
	 * Verify Play list metadata section is present in home page.
	 */
	@Test(description = "Step 2: VVerify Play list metadata section is present in home page.", priority = 2)
	public void Step02_VerifyPlaylistMetadataSection() throws Exception {

		// Verify Play list metadata Section
		Assert.assertTrue(CustomFun.isElementPresent(By.id(ObjRepoProp
				.getProperty("homePagePlaylistMetadataSection_ID")), driver),
				"Play list metadata section is present in home page.");

		log.info("Play list metadata section is present in home page.\n");
		Reporter.log("<p>Play list metadata section is not present in home page.");

	}

	/**
	 * Verify the contents of the meta data.
	 */
	@Test(description = "Step 3: Verify the contents of the metadata.", priority = 3)
	public void Step03_VerifyContentsOfMetadata() throws Exception {

		
		// Verify play list Genre name

		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("playlistMetadadaGenre_XPATH")), driver),
				"Playlist Genre is not present in metadata.");

		log.info("Playlist Genre is present in metadata.\n");
		Reporter.log("<p>Playlist Genre is present in metadata.");

		// Verify Play list name

		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("playlistMetadadaTitle_XPATH")), driver),
				"Playlist name is not present in metadata.");

		log.info("Playlist name is present in metadata.\n");
		Reporter.log("<p>Playlist name is present in metadata.");

		// Verify Total number of videos and total duration of play list.

		Assert.assertTrue(
				CustomFun.isElementPresent(
						By.xpath(ObjRepoProp
								.getProperty("playlistMetadadaTotalVideoDuration_XPATH")),
						driver),
				"Total number of videos and total duration of playlist is not present in metadata.");

		log.info("Total number of videos and total duration of playlist is present in metadata.\n");
		Reporter.log("<p>Total number of videos and total duration of playlist is present in metadata.");

	}

	/**
	 * Verify other options displaying over play list.
	 */
	@Test(description = "Step 4: Verify other options displaying over playlist.", priority = 4)
	public void Step04_VerifyOtherOptionsDisplayingOverMetadata()
			throws Exception {

		// Verify Next navigation button play list

		Assert.assertTrue(CustomFun.isElementPresent(
				By.xpath(ObjRepoProp.getProperty("nextPlaylistButton_XPATH")),
				driver), "Next navigation button is not present in play list.");

		log.info("Next navigation button is present in play list.\n");
		Reporter.log("<p>Next navigation button is present in play list.");

		// Verify Previous navigation button play list

		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("previousPlaylistButton_XPATH")), driver),
				"Previous navigation button is not present in play list.");

		log.info("Previous navigation button is present in play list.\n");
		Reporter.log("<p>Previous navigation button is present in play list.");

		// Verify TELL ME MORE button play list

		Assert.assertTrue(CustomFun.isElementPresent(
				By.xpath(ObjRepoProp.getProperty("tellMeMoreButton_XPATH")),
				driver), "TELL ME MORE button is not present in play list.");

		log.info("TELL ME MORE button is present in play list.\n");
		Reporter.log("<p>TELL ME MORE button is present in play list.");

		// Verify play logo left to total video text

		/*Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("playlistMetadadaTotalVideoDuration_XPATH")),
				driver),
				"Play logo left to total video text is not present in metadata.");

		log.info("Play logo left to total video text is present in metadata.\n");
		Reporter.log("<p>Play logo left to total video text is present in metadata.");*/

	}

}
