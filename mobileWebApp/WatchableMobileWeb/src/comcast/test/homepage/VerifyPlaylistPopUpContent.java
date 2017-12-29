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
 * @description: This test script verifies the content of play list pop-up
 *               window
 * @Created on 23-May-2016 Last updated on 24-May-2016
 * 
 */

public class VerifyPlaylistPopUpContent extends BaseTest {

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
	 * Verify “TELL ME MORE” button is present in play list meta data section.
	 */
	@Test(description = "Step 2: Verify “TELL ME MORE” button is present in play list metadata section.", priority = 2)
	public void Step02_VerifyTellMeMoreButton() throws Exception {

		// Verify Tell me more

		Assert.assertTrue(CustomFun.isElementPresent(
				By.xpath(ObjRepoProp.getProperty("tellMeMoreButton_XPATH")),
				driver),
				"Tell Me More button is not present in Play list meta data section.");

		log.info("Tell Me More button is present in Play list meta data section.\n");
		Reporter.log("<p>Tell Me More button is present in Play list meta data section.");

	}

	/**
	 * Click on “TELL ME MORE” button.
	 */
	@Test(description = "Step 3: Click on “TELL ME MORE” button.", priority = 3)
	public void Step03_ClickOnTellMeMoreButton() throws Exception {

		// Click on TELL ME MORE button

		HomeFun.clickOnTellMeMoreButton();

		// Verify Play list detail pop-up window is opened
		Assert.assertTrue(
				driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("playlistDetailPopup_XPATH")))
						.isDisplayed(),
				"Play list detail pop-up window is not opened.");

		log.info("Play list detail pop-up window opened successfully.\n");
		Reporter.log("<p>Play list detail pop-up window opened successfully.</p>");

	}

	/**
	 * Verify content of Play list detail pop-up window.
	 */
	@Test(description = "Step 4: Verify content of Play list detail pop-up window.", priority = 4)
	public void Step04_VerifyPlaylistDetailPopUpContent() throws Exception {

		// Verify close button

		Assert.assertTrue(
				CustomFun.isElementPresent(By.xpath(ObjRepoProp
						.getProperty("playlistDetailPopupCloseButton_XPATH")),
						driver),
				"Close button is not present at Right top corner of Play list detail pop-up window.");

		log.info("Close button is present at Right top corner of Play list detail pop-up window.\n");
		Reporter.log("<p>Close button is present at Right top corner of Play list detail pop-up window.</p>");

		// Verify Watchable Logo

		Assert.assertTrue(
				CustomFun.isElementPresent(
						By.xpath(ObjRepoProp
								.getProperty("playlistDetailPopupWatchableLogo_XPATH")),
						driver),
				"Watchable logo is not present in Play list detail pop-up window.");

		log.info("Watchable logo is present in Play list detail pop-up window.\n");
		Reporter.log("<p>Watchable logo is present in Play list detail pop-up window.</p>");

		// Verify Play list name

		Assert.assertTrue(
				CustomFun.isElementPresent(
						By.xpath(ObjRepoProp
								.getProperty("playlistDetailPopupPlaylistTitle_XPATH")),
						driver),
				"Play list name is not present in Play list detail pop-up window.");

		log.info("Play list name is present in Play list detail pop-up window.\n");
		Reporter.log("<p>Play list name is present in Play list detail pop-up window.</p>");
		log.info("Play list name displayed is: "
				+ driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("playlistDetailPopupPlaylistTitle_XPATH")))
						.getText() + "\n");
		Reporter.log("<p>Play list name displayed is: "
				+ driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("playlistDetailPopupPlaylistTitle_XPATH")))
						.getText() + "</p>");

		// Verify Play list genre name

		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("playlistDetailPopupPlaylistGenreTitle_XPATH")),
				driver),
				"Play list genre name is not present in Play list detail pop-up window.");

		log.info("Play list genre name is present in Play list detail pop-up window.\n");
		Reporter.log("<p>Play list genre name is present in Play list detail pop-up window.</p>");
		log.info("Play list genre name displayed is: "
				+ driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("playlistDetailPopupPlaylistGenreTitle_XPATH")))
						.getText() + "\n");
		Reporter.log("<p>Play list genre name displayed is: "
				+ driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("playlistDetailPopupPlaylistGenreTitle_XPATH")))
						.getText() + "</p>");

		// Verify Play list description name

		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("playlistDetailPopupPlaylistDescription_XPATH")),
				driver),
				"Play list description  is not present in Play list detail pop-up window.");

		log.info("Play list description is present in Play list detail pop-up window.\n");
		Reporter.log("<p>Play list description is present in Play list detail pop-up window.</p>");
		log.info("Play list description displayed is: "
				+ driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("playlistDetailPopupPlaylistDescription_XPATH")))
						.getText() + "\n");
		Reporter.log("<p>Play list description displayed is: "
				+ driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("playlistDetailPopupPlaylistDescription_XPATH")))
						.getText() + "</p>");

	}

	/**
	 * Click on close button.
	 */
	@Test(description = "Step 5: Click on close button", priority = 5)
	public void Step05_ClickOnCloseButton() throws Exception {

		// Click on close button

		HomeFun.clickOnPlaylistDetailCloseButton();

		// Verify play list details pop-up closed

		// Verify forgot password form is closed successfully
		int frmPresent = driver.findElements(
				By.xpath(ObjRepoProp.getProperty("playlistDetailPopup_XPATH")))
				.size();
		if (frmPresent > 0) {
			Assert.assertFalse(
					driver.findElement(
							By.xpath(ObjRepoProp
									.getProperty("playlistDetailPopup_XPATH")))
							.isDisplayed(),
					"Play list detail pop-up window is not closed.");

			log.info("Play list detail pop-up window closed successfully.\n");
			Reporter.log("<p>Play list detail pop-up window closed successfully.</p>");
		} else {
			log.info("Play list detail pop-up window closed successfully.\n");
			Reporter.log("<p>Play list detail pop-up window closed successfully.</p>");
		}

	}

}
