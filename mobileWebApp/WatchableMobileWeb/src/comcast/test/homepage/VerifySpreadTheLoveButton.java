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
 * @description: This test script verifies clicking on SPREAD THE LOVE! BUTTON!,
 *               it should open social sharing pop up and content of social
 *               sharing pop up
 * @Created on 29-April-2016 Last updated on 29-April-2016
 * 
 */

public class VerifySpreadTheLoveButton extends BaseTest {

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
	 * Verify SPREAD THE LOVE! BUTTON! Button is displaying in playlist section.
	 */
	@Test(description = "Step 2: Verify SPREAD THE LOVE! BUTTON! Button is displaying in playlist section.", priority = 2)
	public void Step02_VerifySpreadTheLoveButton() throws Exception {

		// Verify SPREAD THE LOVE! BUTTON! button play list
		
		log.info(driver.findElements(By.xpath(ObjRepoProp.getProperty("shareSpreadTheLoveButton_XPATH"))).size());
		

		Assert.assertTrue(CustomFun.isElementPresent(
				By.xpath(ObjRepoProp.getProperty("shareSpreadTheLoveButton_XPATH")),
				driver), "SPREAD THE LOVE button is not present in play list.");

		log.info("SPREAD THE LOVE button is present in play list.\n");
		Reporter.log("<p>SPREAD THE LOVE button is present in play list.");

	}

	/**
	 * Click on SPREAD THE LOVE! BUTTON! Button.
	 */
	@Test(description = "Step 3: Click on SPREAD THE LOVE! BUTTON! Button.", priority = 3)
	public void Step03_ClickOnSpreadTheLoveButton() throws Exception {

		// Click on SPREAD THE LOVE! BUTTON! Button.

		HomeFun.clickOnTheSpreadLoveButton();

		// Verify Playlist sharing popup window is opened

		Assert.assertTrue(
				driver.findElement(
						By.xpath(ObjRepoProp.getProperty("socialPopUp_XPATH")))
						.isDisplayed(),
				"Playlist sharing popup window is not opened.");

		log.info("Playlist sharing popup window is opened successfully.\n");
		Reporter.log("<p>Playlist sharing popup window is opened successfully.");

	}

	/**
	 * Verify options present in playlist sharing popup window.
	 */
	@Test(description = "Step 4: Verify options present in playlist sharing popup window.", priority = 4)
	public void Step04_VerifyPlaylistSharingOptions() throws Exception {

		// Verify Twitter option is present in Playlist sharing popup window

		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("socialPopUpTwitterButton_XPATH")), driver),
				"Twitter option is not present in Playlist sharing popup window.");

		log.info("Twitter option is present in Playlist sharing popup window.\n");
		Reporter.log("<p>Twitter option is present in Playlist sharing popup window.");

		// Verify Facebook option is present in Playlist sharing popup window

		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("socialPopUpFaceBookButton_XPATH")), driver),
				"Facebook option is not present in Playlist sharing popup window.");

		log.info("Facebook option is present in Playlist sharing popup window.\n");
		Reporter.log("<p>Facebook option is present in Playlist sharing popup window.");

		// Verify Email option is present in Playlist sharing popup window

		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("socialshareemail_XPATH")), driver),
				"Email option is not present in Playlist sharing popup window.");

		log.info("Email option is present in Playlist sharing popup window.\n");
		Reporter.log("<p>Email option is present in Playlist sharing popup window.");

	}

	/**
	 * Click anywhere outside sharing popup window and verify popup window is closed.
	 */
	@Test(description = "Step 5: Click anywhere outside sharing popup window and verify popup window is closed..", priority = 5)
	public void Step05_ClickOnSpreadTheLoveButton() throws Exception {

		// Click outside sharing popup window


		HomeFun.clickOnTheSpreadLoveButton();

		HomeFun.clickOutSideSpreadLoveButton();


		// Verify Playlist sharing popup window is closed

		Assert.assertFalse(
				driver.findElement(
						By.xpath(ObjRepoProp.getProperty("socialPopUp_XPATH")))
						.isDisplayed(),
				"Playlist sharing popup window is not closed.");

		log.info("Playlist sharing popup window is closed successfully.\n");
		Reporter.log("<p>Playlist sharing popup window is closed successfully.");

	}

}
