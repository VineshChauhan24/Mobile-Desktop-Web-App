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
 * @description: This test script verifies the contents of email sharing pop-up
 *               window.
 * @Created on 11-May-2016 Last updated on 17-May-2016
 * 
 */

public class VerifyEmailSharingPopUpContent extends BaseTest {

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

		log.info(driver.findElements(
				By.xpath(ObjRepoProp
						.getProperty("shareSpreadTheLoveButton_XPATH"))).size());

		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("shareSpreadTheLoveButton_XPATH")), driver),
				"SPREAD THE LOVE button is not present in play list.");

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
	 * Verify Email sharing option is present in playlist sharing popup window.
	 */
	@Test(description = "Step 4: Verify Email sharing option is present in playlist sharing popup window.", priority = 4)
	public void Step04_VerifyPlaylistEmailOptions() throws Exception {

		// Verify Email option is present in Playlist sharing popup window

		Assert.assertTrue(CustomFun.isElementPresent(
				By.xpath(ObjRepoProp.getProperty("socialshareemail_XPATH")),
				driver),
				"Email option is not present in Playlist sharing popup window.");

		log.info("Email option is present in Playlist sharing popup window.\n");
		Reporter.log("<p>Email option is present in Playlist sharing popup window.");

	}

	/**
	 * Click on email button.
	 */
	@Test(description = "Step 5: Click on email button.", priority = 5)
	public void Step05_ClickOnEmailShareOption() throws Exception {
		// Click on email button.
		HomeFun.clickOnEmailShareOption();

		Assert.assertTrue(
				driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("emailSharePopUp_XPATH")))
						.isDisplayed(),
				"Playlist sharing popup window is not opened.");

		log.info("Email sharing pop-up window opened successfully.\n");
		Reporter.log("<p>Email sharing pop-up window opened successfully.</p>");
	}

	/**
	 * Verify the content of email sharing pop-up window
	 */
	@Test(description = "Step 6: Verify the content of email sharing pop-up window.", priority = 6)
	public void Step06_VerifyEmailShareWindowContent() throws Exception {

		// Verify The title “Email This Playlist”
		Assert.assertTrue(
				CustomFun.isElementPresent(By.xpath(ObjRepoProp
						.getProperty("emailSharePopUpTitle_XPATH")), driver),
				"Title is not present in Playlist email sharing popup window.");

		log.info("Title is present '"
				+ driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("emailSharePopUpTitle_XPATH")))
						.getText()
				+ "' is present in Playlist email sharing popup window.\n");
		Reporter.log("<p>Title is present '"
				+ driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("emailSharePopUpTitle_XPATH")))
						.getText()
				+ "' is present in Playlist email sharing popup window.");

		// Verify close icon in email share pop up

		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("emailSharePopUpCloseButton_XPATH")), driver),
				"Close Icon not displaying in email share popup");

		log.info("Close icon is present at top right corner of email sharing pop-up window.\n");
		Reporter.log("<p>Close icon is present at top right corner of email sharing pop-up window.</p>");

		// Verify From email address text field in email share pop up

		Assert.assertTrue(CustomFun.isElementPresent(By.id(ObjRepoProp
				.getProperty("emailSharePopUpFromEmailText_ID")), driver),
				"From email address text field not displaying in email share popup");

		log.info("From email address text field displaying in email share popup.\n");
		Reporter.log("<p>From email address text field displaying in email share popup.</p>");

		// Verify To email address text field in email share pop up

		Assert.assertTrue(
				CustomFun.isElementPresent(By.id(ObjRepoProp
						.getProperty("emailSharePopUpToEmailText_ID")), driver),
				"To email address text field not displaying in email share popup");

		log.info("To email address text field displaying in email share popup.\n");
		Reporter.log("<p>To email address text field displaying in email share popup.</p>");

		// Verify message text area in email share pop up

		Assert.assertTrue(
				CustomFun.isElementPresent(By.id(ObjRepoProp
						.getProperty("emailSharePopUpMessageText_ID")), driver),
				"Message text area not displaying in email share popup");

		log.info("Message text area is present in email sharing pop-up window.\n");
		Reporter.log("<p>Message text area is present in email sharing pop-up window.</p>");

		// Verify send button in email share pop up

		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("emailSharePopUpSendButton_XPATH")), driver),
				"Send button not displaying in email share popup");

		log.info("Send button displaying in email share popup.\n");
		Reporter.log("<p>Send button displaying in email share popup</p>");

	}

	/**
	 * Verify default message displaying in message field.
	 */
	@Test(description = "Step 7: Verify default message displaying in message field.", priority = 7)
	public void Step07_VerifyDefaultMessageDisplayInMessageBox()
			throws Exception {

		// Verify default message display in text area in email share pop up

		if (driver
				.findElement(
						By.id(ObjRepoProp
								.getProperty("emailSharePopUpMessageText_ID")))
				.getText().length() > 0)

		{
			log.info("Default message is displaying in text area in email share pop up \n");
			Reporter.log("<p>Default message is displaying in text area in email share pop up.</p>");

			log.info("The Default message displayed is :"
					+ driver.findElement(
							By.id(ObjRepoProp
									.getProperty("emailSharePopUpMessageText_ID")))
							.getText() + "\n");
			Reporter.log("<p>The Default message displayed is :"
					+ driver.findElement(
							By.id(ObjRepoProp
									.getProperty("emailSharePopUpMessageText_ID")))
							.getText() + "</p>");
		}

		else {
			log.info("Default message is not displaying in text area in email share pop up \n");
			Reporter.log("<p>Default message is not displaying in text area in email share pop up.</p>");
		}

	}

}
