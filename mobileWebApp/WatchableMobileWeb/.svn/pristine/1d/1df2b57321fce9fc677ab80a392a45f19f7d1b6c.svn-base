package comcast.test.homepage;

import static comcast.util.PropertyFileReader.ObjRepoProp;
import static comcast.util.PropertyFileReader.TextProp;

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
 * @description: This test script verifies the message field in email sharing
 *               pop up window is optional
 * @Created on 17-May-2016 Last updated on 17-May-2016
 * 
 */

public class VerifyEmaiSharingMessageIsOptional extends BaseTest {

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
	 * Enter valid email address in From and To email fields , clear the default
	 * value in message field and click on Send button
	 */
	@Test(description = "Step 6: Enter valid email address in From and To email fields , clear the default value in message field and click on Send button", priority = 6)
	public void Step06_EnterFromToEmailClearMsg() throws Exception {

		String fromEmail = TextProp.getProperty("fromEmail");
		String toEmail = TextProp.getProperty("toEmail");

		// Enter from and to email address
		HomeFun.enterdetailsInEmailSharingForm(fromEmail, toEmail);

		// Clear default message displaying in message field
		HomeFun.clearMessage();

		// Verify default message is cleared
		Assert.assertTrue(
				driver.findElement(
						By.id(ObjRepoProp
								.getProperty("emailSharePopUpMessageText_ID")))
						.getAttribute("value").isEmpty(),
				"Default message is not cleared from message field");

		log.info("Default message is cleared from message field\n");
		Reporter.log("<p>Default message is not cleared from message field.</p>");

		// Click on send button
		HomeFun.clickOnSendButton();

		// Verify Successful playlist shared message is displayed.

		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("emailShareConfirmMessage_XPATH")), driver),
				"Successful playlist shared message is not displayed.");

		log.info("The Successful message '"
				+ driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("emailShareConfirmMessage_XPATH")))
						.getText() + "' is displayed.\n");
		Reporter.log("<p>The Successful message '"
				+ driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("emailShareConfirmMessage_XPATH")))
						.getText() + "' is displayed.");

	}

}
