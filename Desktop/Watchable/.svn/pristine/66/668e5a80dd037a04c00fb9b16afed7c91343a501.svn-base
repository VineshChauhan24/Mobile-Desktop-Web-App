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
 * Class Name: VerifyVideoTotalDuration Description:This test script verifies
 * whether total duration time value is displaying on the player. Author: Manoj
 * **/

public class VerifyVideoTotalDuration extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyVideoTotalDuration() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyVideoTotalDuration");
			log.info("********************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method is to scroll UI to What we're watching section.
			HomePageCommonFunctions.scrollToVideoSectionFromChannelSection();

			// This method asserts What we're watching title.
			AssertionRepoFunctions.assertFeaturedTitle();

			// Verify What we're watching VIDEOS row
			assertTrue(
					"What we're watching video row is not present in home page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.featuredVideosRow_XPATH)));
			log.info("What we're watching video row is present in home page");

			// Verify Video present in What we're watching VIDEOS row

			int videoCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredVideosIcon_XPATH)).size();
			if (videoCount > 0) {

				// Click on first video from What we're watching video section
				PlayerPageFun.clickOnFeaturedVideoTitle();

				// Verify User is navigated to video page
				AssertionRepoFunctions.assertVideoPageTitle();

				CommonFun.mouseOverElement(driver, driver.findElement(By
						.id(XpathObjectRepo.videoPlayer_ID)));

				// Verify video player is displayed in video page
				AssertionRepoFunctions.assertVideoPlayer();

				// Mouse over video player
				PlayerPageFun.mouseOverVideoPlayer();

				// Verify video current played time display in player
				assertTrue(
						"Video total duration time is not displayed in video player",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.playerDurationButton_XPATH)));
				log.info("Video total duration time is displayed in video player");

				// Mouse over video player
				PlayerPageFun.mouseOverVideoPlayer();

				int duration = driver.findElements(
						By.xpath(XpathObjectRepo.playerDurationButton_XPATH))
						.size();
				if (duration > 0) {

					PlayerPageFun.mouseOverVideoPlayer();
					log.info("Video total duration time is: "
							+ driver.findElement(
									By.xpath(XpathObjectRepo.playerDurationButton_XPATH))
									.getText() + " Minutes");
					PlayerPageFun.mouseOverVideoPlayer();
					log.info("Video total duration time is: "
							+ driver.findElement(
									By.xpath(XpathObjectRepo.playerDurationButton_XPATH))
									.getText() + " Minutes");
				}

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
