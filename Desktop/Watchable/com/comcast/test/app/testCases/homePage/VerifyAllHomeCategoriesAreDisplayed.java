package comcast.test.app.testCases.homePage;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyAllHomeCategoriesAreDisplayed Description: This test case
 * validates whether all categories are displayed in Home page into Watchable
 * application.
 * Author: Manoj
 * **/



public class VerifyAllHomeCategoriesAreDisplayed extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyAllHomeCategoriesAreDisplayed() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyAllHomeCategoriesAreDisplayed");
			log.info("*******************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method asserts Watchable Logo.
			assertionFunction.assertWatchableLogo();

			// This method is to assert all Header links Before logging into
			// application.
			assertionFunction.assertHeaderLinksWithoutLogin();

			log.info("Header section is present in home page");

			Thread.sleep(sleepTime);

			// This method is to scroll UI to What We're
			// WATCHING section.
			HomePageCommonFunctions.scrollToVideoSectionFromChannelSection();

			// This method is to assert the What We're WATCHING title
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.featuredSectionTitle_XPATH))
					.getText().matches(UILablesRepo.HOMEPAGE_FEATURED_VIDEOS));

			log.info("What we're watching section is present in home page");

			Thread.sleep(sleepTime);

			// This method is to scroll UI to featured Shows Section.
			// HomePageCommonFunctions.scrollToPopularChannelsSection();

			// This method is to assert the Watchable SHOWS OF THE WEEK title
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.featuredChannelsTitle_XPATH))
					.getText().matches(UILablesRepo.HOMEPAGE_FEATURED_CHANNELS));

			log.info("Watchable shows of the week section is present in home page");

			Thread.sleep(sleepTime);

			/*
			 * //Channels by Genre section is removed from home page now
			 * 
			 * // This method is to assert the Channels by Genre title
			 * 
			 * assertTrue(driver .findElement(
			 * By.xpath(XpathObjectRepo.genreChannelsTitle_XPATH))
			 * .getText().matches(UILablesRepo.HOMEPAGE_CHANNELS_BY_GENRE));
			 * 
			 * log.info("Shows by genre section is present in home page");
			 * 
			 * Thread.sleep(sleepTime);
			 */

			// This method is to scroll UI to Footer section.
			HomePageCommonFunctions.scrollToFooterSection();

			// Verify Footer section is displayed
			assertTrue(
					"Footer section is not present in home page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerSection_XPATH)));
			log.info("Footer section is present in home page");
			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
