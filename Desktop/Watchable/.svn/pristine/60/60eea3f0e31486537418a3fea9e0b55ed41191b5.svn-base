package comcast.test.app.testCases.homePage;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;
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
 * Class Name: VerifyEightVideosDisplayedInFeaturedVideosSection Description:
 * Verifies there are a total of four video displayed under fWhat We're WATCHING
 * section. Author: Manoj
 * **/

public class VerifyEightVideosDisplayedInFeaturedVideosSection extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyEightVideosDisplayedInFeaturedVideosSection()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyEightVideosDisplayedInFeaturedVideosSection");
			log.info("*********************************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method asserts What We're WATCHING title.
			AssertionRepoFunctions.assertFeaturedTitle();

			// This method is to scroll UI to What We're WATCHING Section.
			HomePageCommonFunctions.scrollToPopularShowsSection();

			// VerifyWhat We're WATCHING row
			assertTrue(
					"What We're WATCHING row is not present in home page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.featuredVideosRow_XPATH)));
			log.info("What We're WATCHING row is present in home page");

			// Verify Video present in What We're WATCHING row

			int videoCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredVideosIcon_XPATH)).size();
			if (videoCount > 0) {

				assertEquals(
						"What We're WATCHING section does not contains 4 videos",
						4, videoCount);
				log.info("What We're WATCHING section contains 4 videos");

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
