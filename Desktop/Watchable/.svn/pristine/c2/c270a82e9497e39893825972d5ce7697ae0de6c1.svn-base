package comcast.test.app.testCases.homePage;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyFeaturedVideosAreDisplayed Description: This test case
 * verifies videos are displayed in What We're WATCHING section Author: Manoj
 * **/

public class VerifyFeaturedVideosAreDisplayed extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyFeaturedVideosAreDisplayed() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyFeaturedVideosAreDisplayed");
			log.info("****************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method is to scroll UI to What We're WATCHING section.
			HomePageCommonFunctions.scrollToVideoSectionFromChannelSection();

			// This method asserts What We're WATCHING title.
			AssertionRepoFunctions.assertFeaturedTitle();

			// Verify What We're WATCHING row
			assertTrue(
					"What We're WATCHING video row is not present in home page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.featuredVideosRow_XPATH)));
			log.info("What We're WATCHING video row is present in home page");

			// Verify Video present in What We're WATCHING row

			int videoCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredVideosIcon_XPATH)).size();
			if (videoCount > 0) {

				log.info("What We're WATCHING video row contains videos");
				log.info(videoCount
						+ " Videos are present in What We're WATCHING video section in home page");
				log.info("The following videos are present in What We're WATCHING video section");
				log.info("----------------------------------------------------------");
				for (int i = 1; i <= videoCount; i++) {
					log.info(i
							+ ". "
							+ driver.findElement(
									By.xpath(XpathObjectRepo.featuredVideoTitle_XPATH
											+ i + "]")).getText());

					log.info("   Video duration: "
							+ driver.findElement(
									By.xpath(XpathObjectRepo.featuredVideoDuration_XPATH
											+ i + "]")).getText());

					String channelNameAndLastUpdate = driver
							.findElement(
									By.xpath(XpathObjectRepo.featuredVideoShowTitle_XPATH
											+ i
											+ XpathObjectRepo.featuredShowLastModified_XPATH))
							.getText();
					String array[] = channelNameAndLastUpdate.split("/");
					log.info("   Show Name: " + array[0]);
					log.info("   Last Updated: " + array[1]);

					log.info(" ");

				}
				log.info("");

			} else {
				log.error("What We're WATCHING row does not contain videos");
				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
