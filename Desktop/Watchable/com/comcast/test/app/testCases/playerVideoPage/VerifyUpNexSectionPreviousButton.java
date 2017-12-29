package comcast.test.app.testCases.playerVideoPage;

import static org.junit.Assert.*;

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
 * Class Name: VerifyUpNexSectionPreviousButton Description: This test scripts
 * verifies Previous button is present in second page of up next section and it
 * is clickable. 
 * Author: Manoj
 * **/

public class VerifyUpNexSectionPreviousButton extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyUpNexSectionPreviousButton() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyUpNexSectionPreviousButton");
			log.info("***********************************************");

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

				// Scroll UI to Up Next section.
				HomePageCommonFunctions.scrollToPopularShowsSection();
				Thread.sleep(sleepTime);

				int upNextVideo = driver
						.findElements(
								By.xpath(XpathObjectRepo.videoPageUpNexSectionVideoIcon_XPATH))
						.size();
				if (upNextVideo > 0) {
					log.info("Up Next vidos section is prsent in Video page with videos");

					if (driver
							.findElement(
									By.xpath(XpathObjectRepo.videoPageUpNexSectionNextButton_XPATH))
							.isDisplayed() == true) {
						// Verify Next button is present in up next section
						assertTrue(
								"Next button is not present in up next section",
								CommonFun.isElementPresent(
										driver,
										By.xpath(XpathObjectRepo.videoPageUpNexSectionNextButton_XPATH)));
						log.info("Next button is present in up next section");

						// Click on Next button

						PlayerPageFun.clickOnNextButton();

						// Verify Previous button is displaying in second page
						// of
						// up next section

						boolean isDisplayed = false;

						isDisplayed = driver
								.findElement(
										By.xpath(XpathObjectRepo.videoPageUpNexSectionPreviousButton_XPATH))
								.isDisplayed();
						log.info("isDisplayed :" + isDisplayed);

						assertEquals(isDisplayed, true);
						log.info("Previous button is displaying in second page of up next section");

						// Verify Previous button is clickable in up next
						// section

						log.info(driver
								.findElement(
										By.xpath(XpathObjectRepo.videoPageUpNexSectionPreviousButton_XPATH))
								.getAttribute("class"));

						assertTrue(
								"Previous button is not clickable in up next section",
								driver.findElement(
										By.xpath(XpathObjectRepo.videoPageUpNexSectionPreviousButton_XPATH))
										.getAttribute("class")
										.contains("active"));
						log.info("Previous button is clickaable in up next section");
					}
				} else {
					log.info("Up Next vidos section is NOT prsent in Video page");
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
