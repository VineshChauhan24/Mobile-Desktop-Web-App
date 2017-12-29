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
 * @description: This test script verifies the functionality of Contact Us link
 *               present at bottom of the home page
 * @Created on 23-May-2016 Last updated on 23-May-2016
 * 
 */

public class VerifyContactUsLink extends BaseTest {

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
	 * Verify Contact Us present in footer.
	 */
	@Test(description = "Step 2: Verify Contact Us present in footer.", priority = 2)
	public void Step02_VerifyContactUsLink() throws Exception {

		// Move to contact us link
		GUIFunctions.mouseOverElement(driver, driver.findElement(By
				.xpath(ObjRepoProp
						.getProperty("homePageFooterCopyRightText_XPATH"))));

		// Verify Contact Us link

		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("homePageFooterContactUsLink_XPATH")), driver),
				"Contact Us link is not present in footer");

		log.info("Contact Us link is present in footer.\n");
		Reporter.log("<p>Contact Us link is present in footer.");

	}

	/**
	 * Click on Contact Us Link
	 */
	@Test(description = "Step 3: Click on Contact Us Link.", priority = 3)
	public void Step03_ClickOnContactUsLink() throws Exception {

		// Click on Contact Us Link

		HomeFun.clickOnContactUsLink();

		// Verify contact us form is opened
		Assert.assertTrue(
				driver.findElement(
						By.xpath(ObjRepoProp.getProperty("contactUsForm_XPATH")))
						.isDisplayed(),
				"Contact us popup window is not opened.");

		log.info("Contact us pop-up window opened successfully.\n");
		Reporter.log("<p>Contact us pop-up window opened successfully.</p>");

	}

}
