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
 * @description: This test script verifies the contents of header section in
 *               home page
 * @Created on 27-April-2016 Last updated on 06-June-2016
 * 
 */

public class VerifyHomePageHeaderSectionContents extends BaseTest {

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
	 * Verify header section is present in home page.
	 */
	@Test(description = "Step 2: Verify header section is present in home page.", priority = 2)
	public void Step02_VerifyHeaderSection() throws Exception {

		// Verify header section
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("homePageHeaderSection_XPATH")), driver),
				"Header section is not present in home page.");

		log.info("Header section is present in home page.\n");
		Reporter.log("<p>Header section is present in home page.");

	}

	/**
	 * Verify Verify contents of header section
	 */
	@Test(description = "Step 3: Verify contents of header section.", priority = 3)
	public void Step03_VerifyContentsOfHeaderSection() throws Exception {

		// Verify Watchable header Text
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("homePageWatchableLogo_XPATH")), driver),
				"Watchable header Text is not present in header section of home page.");

		log.info("Watchable header Text is present in header section of home page.\n");
		Reporter.log("<p>Watchable header Text is present in header section of home page.");

		if (driverName.equalsIgnoreCase("ANDROID")) {

			// Verify Google Play button in Android Device
			Assert.assertTrue(
					CustomFun.isElementPresent(By.xpath(ObjRepoProp
							.getProperty("homePageHeadeGooglePlay_XPATH")),
							driver),
					"Google Play button in Android Devices is not present in header section of home page.");

			log.info("Google Play button in Android Device is present in header section of home page.\n");
			Reporter.log("<p>Google Play button in Android Device is present in header section of home page.");
		}

	}

}
