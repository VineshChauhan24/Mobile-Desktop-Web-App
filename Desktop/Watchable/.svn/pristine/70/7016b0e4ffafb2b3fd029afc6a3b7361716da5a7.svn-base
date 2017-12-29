package comcast.test.app.testCases.socialSharing;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.playerVideoPage.playerPageFunctions.PlayerPageFun;
import comcast.test.app.testCases.socialSharing.socialSharingFunctions.SocialSharingFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyTwitterFunctionalityFromVideoPage Description: This test
 * script verifies the opening of twitter sharing window after clicking on
 * Twitter button from video page.Author: Manoj
 * **/

public class VerifyTwitterFunctionalityFromVideoPage extends BaseTest {
	@Test
	public void testVerifyTwitterFunctionalityFromVideoPage() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyTwitterFunctionalityFromVideoPage");
			log.info("************************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

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
				
				// Scroll to Social sharing section
				HomePageCommonFunctions.scrollToVideoResultSection();

				// Verify Twitter button in sharing section
				assertTrue(
						"Twitter button is not present in sharing section",
						CommonFun.isElementPresent(driver,
								By.id(XpathObjectRepo.twitterButton_ID)));

				log.info("Twitter button is present in sharing section");
				String videoUrl = driver.getCurrentUrl();
				log.info("Currently playing video: " + videoUrl);

				// Click on Twitter button from video page
				SocialSharingFun.clickonTwitterButton();

				String parentWindow = driver.getWindowHandle();
				Set<String> handles = driver.getWindowHandles();

				for (String windowHandle : handles) {
					if (!windowHandle.equals(parentWindow)) {
						driver.switchTo().window(windowHandle);

						Thread.sleep(sleepTime);
						log.info("Share a link on Twitter popup window opened after clicking on Tweet button");
						String twitterVideoUrl = driver
								.findElement(
										By.xpath(XpathObjectRepo.tweetVideoUrlText_XPATH))
								.getText();
						if (twitterVideoUrl.trim().length() > 0) {
							log.info("Text present in share text box in Twitter pop up window");
							log.info("Share text box content : "
									+ twitterVideoUrl);

							assertTrue(
									"Text present in share text box in Twitter pop up window NOT contains currently playing video URL",
									twitterVideoUrl.contains(videoUrl));
							log.info("Text present in share text box in Twitter pop up window contains currently playing video URL");

						} else {
							log.error("No text present in share text box in Twitter pop up window");
						}

						driver.close(); // closing child window
						driver.switchTo().window(parentWindow); // Control
																// to
																// parent
																// window

					}

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
