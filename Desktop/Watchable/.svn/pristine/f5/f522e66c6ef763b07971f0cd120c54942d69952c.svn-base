package comcast.test.app.testCases.socialSharing;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.playerVideoPage.playerPageFunctions.PlayerPageFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyPresenceOfSocialSharingFunctionalityInVideoPage
 * Description: This test case verifies The presence of social sharing
 * functionality in video page. Author: Manoj
 * **/

public class VerifyPresenceOfSocialSharingFunctionalityInVideoPage extends
		BaseTest {
	@Test
	public void testVerifyPresenceOfSocialSharingFunctionalityInVideoPage()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyPresenceOfSocialSharingFunctionalityInVideoPage");
			log.info("*************************************************************");

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

				// Verify video player is displayed in page
				AssertionRepoFunctions.assertVideoPlayer();

				// Verify Social sharing title in sharing section
				assertTrue(
						"Social sharing title is not present in sharing section",
						CommonFun.isElementPresent(driver, By
								.xpath(XpathObjectRepo.socialShareTitle_XPATH)));
				log.info("The social sharing title '"
						+ driver.findElement(
								By.xpath(XpathObjectRepo.socialShareTitle_XPATH))
								.getText() + "' is present");

				// Verify Face book button in sharing section
				assertTrue(
						"Face book button is not present in sharing section",
						CommonFun.isElementPresent(driver,
								By.id(XpathObjectRepo.facebookButton_ID)));

				log.info("Face book button is present in sharing section");

				// Verify Twitter button in sharing section
				assertTrue(
						"Twitter button is not present in sharing section",
						CommonFun.isElementPresent(driver,
								By.id(XpathObjectRepo.twitterButton_ID)));

				log.info("Twitter button is present in sharing section");

				// Verify Copy URL button in sharing section
				assertTrue(
						"Copy URL button is not present in sharing section",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.copyUrlButton_XPATH)));

				log.info("Copy URL button is present in sharing section");

				// Verify Email button in sharing section
				assertTrue(
						"Email button is not present in sharing section",
						CommonFun.isElementPresent(driver,
								By.id(XpathObjectRepo.emailButton_ID)));

				log.info("Email button is present in sharing section");

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
