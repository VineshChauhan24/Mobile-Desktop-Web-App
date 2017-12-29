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
 * @description: This test script verifies the content of copy right text bottom
 *               of the home page
 * @Created on 20-May-2016 Last updated on 20-May-2016
 * 
 */

public class VerifyCopyRightText extends BaseTest {

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
	 * Verify copy right text present in footer
	 */
	@Test(description = "Step 2: Verify copy right text present in footer", priority = 2)
	public void Step02_VerifyCopyRightText() throws Exception {

		// Move to copy right text
		GUIFunctions.mouseOverElement(driver, driver.findElement(By
				.xpath(ObjRepoProp
						.getProperty("homePageFooterCopyRightText_XPATH"))));

		// Verify copy right text

		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("homePageFooterCopyRightText_XPATH")), driver),
				"Copy right text is not present in footer");

		log.info("Copy right text is present in footer.\n");
		Reporter.log("<p>Copy right text is present in footer.");

	}

	/**
	 * Verify the content of copy right text
	 */
	@Test(description = "Step 3: Verify video name is displaying on first video.", priority = 3)
	public void Step03_VerifyCopyRightTextContent() throws Exception {

		int textContent = driver
				.findElement(
						By.xpath(ObjRepoProp
								.getProperty("homePageFooterCopyRightText_XPATH")))
				.getText().length();

		Assert.assertTrue(textContent > 0, "Copy righ text is blank.");

		log.info("Copy righ text is not blank.\n");
		Reporter.log("<p>Copy righ text is not blank.");

		// Display copy right text content

		log.info("Copy right text is displayed as : "
				+ driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("homePageFooterCopyRightText_XPATH")))
						.getText() + "\n");
		Reporter.log("<p>Copy right text is displayed as : "
				+ driver.findElement(
						By.xpath(ObjRepoProp
								.getProperty("homePageFooterCopyRightText_XPATH")))
						.getText());

	}

}
