package comcast.test.app.testCases.socialSharing;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.playerVideoPage.playerPageFunctions.PlayerPageFun;
import comcast.test.app.testCases.socialSharing.socialSharingFunctions.SocialSharingFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyFacebookFunctionalityFromVideoPage Description: This test
 * script verifies the opening of face book sharing window after clicking on
 * Face book button from video page Author: Manoj
 * **/

public class VerifyFacebookFunctionalityFromVideoPage extends BaseTest {
	@Test
	public void testVerifyFacebookFunctionalityFromVideoPage() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyFacebookFunctionalityFromVideoPage");
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

				// Verify Face book button in sharing section
				assertTrue(
						"Face book button is not present in sharing section",
						CommonFun.isElementPresent(driver,
								By.id(XpathObjectRepo.facebookButton_ID)));

				log.info("Face book button is present in sharing section");

				// Click on Face book button from video page
				SocialSharingFun.clickonFacebooButton();

				String parentWindow = driver.getWindowHandle();
				Set<String> handles = driver.getWindowHandles();

				for (String windowHandle : handles) {
					if (!windowHandle.equals(parentWindow)) {
						driver.switchTo().window(windowHandle);

						Thread.sleep(sleepTime);
						log.info("Share a link on Face book popup window opened after clicking on Face book button");

						assertTrue(
								"Face Book pop up window NOT opened after clicking on FB button",
								driver.getTitle().contains(
										UILablesRepo.FB_TITLE));
						log.info("Face Book pop up window opened after clicking on FB button");

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
