package comcast.test.app.testCases.playerVideoPage;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.app.testCases.playerVideoPage.playerPageFunctions.PlayerPageFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyClickingOnWatchableFooterLogoFromVideoPageNavigatesHomePage
 * Description: This test case click on footer watchable logo from video page, navigating
 * back to Home page. 
 * Author: Manoj
 * **/

public class VerifyClickingOnWatchableFooterLogoFromVideoPageNavigatesHomePage
		extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickingOnWatchableFooterLogoFromVideoPageNavigatesHomePage()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyClickingOnWatchableFooterLogoFromVideoPageNavigatesHomePage");
			log.info("*************************************************************************");

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

				// Scroll to Footer
				HomePageCommonFunctions.scrollToFooterSection();
				Thread.sleep(sleepTime);

				// Verify Watchable logo is present in footer
				assertTrue(
						"Watchable logo is not present in footer",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.footerwatchableLogo_XPATH)));
				log.info("Watchable logo is present in footer");

				
				// Click on footer Watchable logo from Video detail page to
				// navigate back
				// to home page

				HomeFun.clickOnBottomWatchableLogo();
				
				AssertionRepoFunctions.assertWatchableTitle();
				log.info("Successfully Navigated to Home page after clicking on footer Watchable logo from video page");

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
