package comcast.test.homepage;

import static comcast.util.PropertyFileReader.ObjRepoProp;
import java.util.Set;
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
 * @author karthik.ashoka
 * @description: This test script verifies twitter sharing functionality from SPREAD THE LOVE! BUTTON!.
 * @Created on 24-May-2016 Last updated on 24-May-2016
 * 
 */

public class VerifyTwitterSharingFromPlaylist extends BaseTest {

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

		Assert.assertTrue(CustomFun.isElementPresent(
				By.xpath(ObjRepoProp.getProperty("spreadTheLoveButton_XPATH")),
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

	}
	
	/**
	 * Click on Twitter button which is present under SPREAD THE LOVE! button.
	 */
	@Test(description = "Step 5: Click on Twitter button which is present under SPREAD THE LOVE! button.", priority = 5)
	public void Step05_ClickOnTwitterIconOfPlaylistSection() throws Exception {

		HomeFun.clickOnPlaylistSectionTwitterIcon();
		String parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();

		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);

				Thread.sleep(5000);
				
				Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("twitterloginpage_XPATH")),
		driver), "Twitter login page not present");
				log.info("twitter login button displaying");

				driver.close(); // closing child window
				driver.switchTo().window(parentWindow); // Control to parent window
														

			}

		}
		log.info("Twitter login window is opened successfully..\n");
		Reporter.log("<p>Twitter login window is opened successfully.");
	}
	
}
