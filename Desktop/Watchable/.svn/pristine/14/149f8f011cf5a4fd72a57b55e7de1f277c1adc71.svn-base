package comcast.test.app.testCases.playerVideoPage;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.playerVideoPage.playerPageFunctions.PlayerPageFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyCurrentBandwidthValueDisplay Description: This test script
 * verified the current bandwidth value is displauing in video. Author: Manoj
 * **/

public class VerifyCurrentBandwidthValueDisplay extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyCurrentBandwidthValueDisplay() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyCurrentBandwidthValueDisplay");
			log.info("***********************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method asserts Featured Channels title.
			AssertionRepoFunctions.assertFeaturedTitle();

			// Verify FEATURED VIDEOS row
			assertTrue(
					"Featured video row is not present in home page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.featuredVideosRow_XPATH)));
			log.info("Featured video row is present in home page");

			// Verify Video present in FEATURED VIDEOS row

			int videoCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredVideosIcon_XPATH)).size();
			if (videoCount > 0) {

				// Click on first video from featured video section
				PlayerPageFun.clickOnFeaturedVideoTitle();

				// Verify User is navigated to video page
				AssertionRepoFunctions.assertVideoPageTitle();

				// Verify video player is displayed in video page
				AssertionRepoFunctions.assertVideoPlayer();

				// Verify current bandwidth is displaying over video control
				assertTrue(
						"Current bandwidth is not displaying over video control",
						CommonFun.isElementPresent(driver,
								By.id(XpathObjectRepo.bandwidthDisplay_ID)));
				log.info("Current bandwidth is displaying over video control");

				// Verify the value displaying for current bandwidth

				String currentBandWidth = driver.findElement(
						By.id(XpathObjectRepo.bandwidthDisplay_ID)).getText();

				currentBandWidth = currentBandWidth.substring(18);

				log.info("Current band width value is: "
						+ currentBandWidth.trim());
				log.info("");
			} else {
				log.error("Featured video section does not contain videos");
				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
