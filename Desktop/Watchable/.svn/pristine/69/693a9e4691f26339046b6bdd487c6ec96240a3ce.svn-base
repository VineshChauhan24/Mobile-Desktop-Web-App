package comcast.test.app.testCases.footerLink;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.footerLink.footerLinkFunctions.FooterLinkFun;
import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyClickingOnHomeFooterLinkNavigatesHomePage: This test case
 * click on home footer from any page, navigating back to Home page. Author: Manoj
 * **/

public class VerifyClickingOnHomeFooterLinkNavigatesHomePage extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();

	@Test
	public void testVerifyClickingOnHomeFooterLinkNavigatesHomePage()
			throws Exception {

		try {

			log.info("Script: VerifyClickingOnHomeFooterLinkNavigatesHomePage");
			log.info("*******************************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method is to scroll UI to Popular Channels Section.
			HomePageCommonFunctions.scrollToPopularChannelsSection();
			Thread.sleep(sleepTime);

			// Click on first channel name from FEATURED CHANNELS section

			HomeFun.clickOnFirstChannelLink();

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();
			Thread.sleep(sleepTime);

			// Verify Home Link is present in footer
			assertTrue(
					"Home Link is not present",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerHelpLink_XPATH)));
			log.info("Home Link is present");

			// Click on footer Home link from channel detail page to
			// navigate back
			// to home page

			FooterLinkFun.clickOnHomeLink();
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully Navigated to Home page after clicking on Home link");

			// Verify successfully navigated to Home page after clicking on Home
			// link from footer

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
