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
 * @description: This test script verify all the main sections are present in
 *               mobile web home page
 * @Created on 22-March-2016 Last updated on 06-June-2016
 * 
 */

public class VerifyHomePageContent extends BaseTest {

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
	 * Verify Play list metadata section is present in home page.
	 */
	@Test(description = "Step 3: VVerify Play list metadata section is present in home page.", priority = 3)
	public void Step03_VerifyPlaylistMetadataSection() throws Exception {

		// Move to meta data section
		GUIFunctions.mouseOverElement(driver, driver.findElement(By
				.id(ObjRepoProp
						.getProperty("homePagePlaylistMetadataSection_ID"))));

		// Verify Play list metadata Section
		Assert.assertTrue(CustomFun.isElementPresent(By.id(ObjRepoProp
				.getProperty("homePagePlaylistMetadataSection_ID")), driver),
				"Play list metadata section is not in home page.");

		log.info("Play list metadata section is present in home page.\n");
		Reporter.log("<p>Play list metadata section is not present in home page.");

	}

	/**
	 * Verify video player is present in home page.
	 */
	@Test(description = "Step 4: Verify video player is present in home page.", priority = 4)
	public void Step04_VerifyVideoPlayer() throws Exception {

		// Move to video player section
		GUIFunctions.mouseOverElement(driver, driver.findElement(By
				.xpath(ObjRepoProp.getProperty("homePageVideoPlayer_XPATH"))));

		// Verify video player
		Assert.assertTrue(
				CustomFun.isElementPresent(By.xpath(ObjRepoProp
						.getProperty("homePageVideoPlayer_XPATH")), driver),
				"Video player is not present in home page below the Play list metadata section.");

		log.info("Video player is present in home page below the Play list metadata section.\n");
		Reporter.log("<p>Video player is present in home page below the Play list metadata section.");

	}

	/**
	 * Verify Play list video section is present in home page.
	 */
	@Test(description = "Step 5: Verify Play list video section is present in home page.", priority = 5)
	public void Step05_VerifyPlaylistSection() throws Exception {

		// Verify Play list section
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("homePagePlaylistVideoSection_XPATH")), driver),
				"Play list video section is not present in home page below the video player.");

		log.info("Play list video section is present in home page below the video player.\n");
		Reporter.log("<p>Play list video section is present in home page below the video player.");

	}

	/**
	 * Verify SPREAD THE LOVE button is present in home page.
	 */
	@Test(description = "Step 6: Verify SPREAD THE LOVE button is present in home page..", priority = 6)
	public void Step06_VerifySpreadTheLoveButton() throws Exception {

		// Verify SPREAD THE LOVE button
		Assert.assertTrue(CustomFun.isElementPresent(
				By.xpath(ObjRepoProp.getProperty("spreadTheLoveButton_XPATH")),
				driver), "SPREAD THE LOVE button is not present in home page.");

		log.info("SPREAD THE LOVE button is present in home page.\n");
		Reporter.log("<p>SPREAD THE LOVE button is present in home page.");

	}

	/**
	 * Verify home description text is present in home page.
	 */
	@Test(description = "Step 7: Verify home description text is present in home page.", priority = 7)
	public void Step07_VerifyHomeDescriptionText() throws Exception {

		// Move to home description text section
		GUIFunctions.mouseOverElement(driver,
				driver.findElement(By.xpath(ObjRepoProp
						.getProperty("homePageDescriptionText_XPATH"))));

		// Verify home description text
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("homePageDescriptionText_XPATH")), driver),
				"Home description text is not present in home page below the playlist.");

		log.info("Home description text is present in home page below the playlist.\n");
		Reporter.log("<p>Home description text is present in home page below the playlist.");

	}

	/**
	 * Verify show image is present in home page.
	 */
	@Test(description = "Step 8: Verify show image is present in home page.", priority = 8)
	public void Step08_VerifyShowImage() throws Exception {

		// Verify home description text
		Assert.assertTrue(CustomFun.isElementPresent(
				By.xpath(ObjRepoProp.getProperty("homePageShowImage_XPATH")),
				driver),
				"Show image is not present in home page below the home description text.");

		log.info("Show image is present in home page below the home description text.\n");
		Reporter.log("<p>Show image is present in home page below the home description text.");

	}

	/**
	 * Verify App Store button is present in home page.
	 */
	@Test(description = "Step 9: Verify App Store button is present in home page.", priority = 9)
	public void Step09_VerifyAppStoreButton() throws Exception {

		// Verify App Store button
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("homePageAppStoreButton_XPATH")), driver),
				"App Store button is not present in home page at the bottom of show rows.");

		log.info("App Store button is present in home page at the bottom of show rows.\n");
		Reporter.log("<p>App Store button is present in home page at the bottom of show rows.");

	}

	/**
	 * Verify Goole Play button is present in home page.
	 */
	@Test(description = "Step 10: Verify Goole Play button is present in home page.", priority = 10)
	public void Step10_VerifyGoolePlayButton() throws Exception {

		// Verify Goole Play button
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("homePageGooglePlayButton_XPATH")), driver),
				"Goole Play button is not present in home page at the bottom of show rows.");

		log.info("Goole Play button is present in home page at the bottom of show rows.\n");
		Reporter.log("<p>Goole Play button is present in home page at the bottom of show rows.");

	}

	/**
	 * Verify Watchable logo is present in footer.
	 */
	@Test(description = "Step 11: Verify Goole Play button is present in home page.", priority = 11)
	public void Step11_VerifyWatchableLogo() throws Exception {

		// Move to Watchable logo in footer
		GUIFunctions.mouseOverElement(driver, driver.findElement(By
				.xpath(ObjRepoProp
						.getProperty("homePageFooterWatchableLogo_XPATH"))));

		// Verify Watchable logo in footer
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("homePageFooterWatchableLogo_XPATH")), driver),
				"Watchable logo is not present in footer above footer link section.");

		log.info("Watchable logo is present in footer above footer link section.\n");
		Reporter.log("<p>Watchable logo is present in footer above footer link section.");

	}

	/**
	 * Verify footer links are present in footer.
	 */
	@Test(description = "Step 12: Verify Goole Play button is present in home page.", priority = 12)
	public void Step12_VerifyFooterLlinksSection() throws Exception {

		// Move to footer links section
		GUIFunctions.mouseOverElement(driver, driver.findElement(By
				.xpath(ObjRepoProp
						.getProperty("homePageFooterLinkSection_XPATH"))));

		// Verify footer links section
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("homePageFooterLinkSection_XPATH")), driver),
				"Footer links are not present in footer above the social sharing options.");

		log.info("Footer links are present in footer above the social sharing options.\n");
		Reporter.log("<p>Footer links are present in footer above the social sharing options.");

	}

	/**
	 * Verify social sharing options are present in footer.
	 */
	@Test(description = "Step 13: Verify Goole Play button is present in home page.", priority = 13)
	public void Step13_VerifyFooterSocialSharingOoptions() throws Exception {

		// Move copy right text section
		GUIFunctions.mouseOverElement(driver, driver.findElement(By
				.xpath(ObjRepoProp
						.getProperty("homePageFooterCopyRightText_XPATH"))));

		// Verify footer social sharing options
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("homePageFooterSocialSharingSection_XPATH")),
				driver),
				"Social sharing options are not present in footer above the Contact Us link.");

		log.info("Social sharing options are present in footer above the Contact Us link.\n");
		Reporter.log("<p>Social sharing options are present in footer above the Contact Us link.");

	}

	/**
	 * Verify Contact Us present in footer.
	 */
	@Test(description = "Step 14: Verify Contact Us present in footer.", priority = 14)
	public void Step14_VerifyFooterContactUsLink() throws Exception {

		// Verify footer Contact Us link
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("homePageFooterContactUsLink_XPATH")), driver),
				"Contact Us link is not present in footer above the copy right text.");

		log.info("Contact Us link is present in footer above the copy right text.\n");
		Reporter.log("<p>Contact Us link is present in footer above the copy right text.");

	}

	/**
	 * Verify copy right text present in footer
	 */
	@Test(description = "Step 15: Verify copy right text present in footer", priority = 15)
	public void Step15_VerifyFooterCopyRightText() throws Exception {

		// Verify footer Contact Us link
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp
				.getProperty("homePageFooterCopyRightText_XPATH")), driver),
				"Copy right text is not present at the bottom of the home page.");

		log.info("Copy right text is present at the bottom of the home page.\n");
		Reporter.log("<p>Copy right text is present at the bottom of the home page.");

	}

}
