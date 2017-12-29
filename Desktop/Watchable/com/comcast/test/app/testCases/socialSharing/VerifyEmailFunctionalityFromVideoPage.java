package comcast.test.app.testCases.socialSharing;

import static org.junit.Assert.assertTrue;

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
 * Class Name: VerifyEmailFunctionalityFromVideoPage Description: This test
 * script verifies the opening of Email sharing popup window after clicking on
 * Email button from video page. Author: Manoj
 * **/

public class VerifyEmailFunctionalityFromVideoPage extends BaseTest {
	@Test
	public void testVerifyEmailFunctionalityFromVideoPage() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyEmailFunctionalityFromVideoPage");
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

				// Verify Email button in sharing section
				assertTrue(
						"Email button is not present in sharing section",
						CommonFun.isElementPresent(driver,
								By.id(XpathObjectRepo.emailButton_ID)));

				log.info("Email button is present in sharing section");

				// Click on Face book button from video page
				SocialSharingFun.clickonEmailButton();

				// Verify Email sharing pop up window is displayed
				assertTrue(
						"Email sharing pop up window is not displayed after clicking on Email button",
						CommonFun.isElementPresent(driver, By
								.xpath(XpathObjectRepo.emailSharingForm_XPATH)));

				log.info("Email sharing pop up window is displayed after clicking on Email button");
				// Verify The Video URL is displaying in message text box in
				// email pop up window
				if (driver
						.findElement(
								By.id(XpathObjectRepo.emailPopupMessageText_ID))
						.getText().length() > 0) {
					log.info("The video URL "
							+ driver.findElement(
									By.id(XpathObjectRepo.emailPopupMessageText_ID))
									.getText()
							+ " is displaying in message area of email pop up window");
				} else {
					log.error("The video URL is not displaying in message area of email pop up window");
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
