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
 * Class Name: VerifyLikeWithoutLogin Description: This test script verify login
 * popup is displayed when user click on like button from video controls if user
 * is not logged into application. Author: Manoj
 * **/

public class VerifyLikeWithoutLogin extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyLikeWithoutLogin() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyLikeWithoutLogin");
			log.info("******************************");

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
				CommonFun.mouseOverElement(driver, driver.findElement(By
						.id(XpathObjectRepo.videoPlayer_ID)));

				AssertionRepoFunctions.assertVideoPlayer();

				// click on like button from video player controls
				CommonFun.mouseOverElement(driver, driver.findElement(By
						.id(XpathObjectRepo.videoPlayer_ID)));

				// Click on like button
				PlayerPageFun.clickOnLikeButton();

				// Verify login pop up is opened if user is not logged in to
				// application
				assertTrue(
						"Login pop up is not opened on clicking Like button if user is not logged in to application",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.loginForm_XPATH)));
				log.info("Login pop up is opened on clicking Like button if user is not logged in to application");

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
