package comcast.test.app.testCases.homePage;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyMoreLinkDisplayedInGenreShowSection Description: Verifies
 * More link are displayed in show section of genre section Of show rows when
 * there are more than 8 shows in a row. 
 * Author: Manoj
 * **/

public class VerifyMoreLinkDisplayedInGenreShowSection extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyMoreLinkDisplayedInGenreShowSection()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyMoreLinkDisplayedInGenreShowSection");
			log.info("***********************************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Verify Browse Shows menu is present
			assertTrue(
					"All Shows menu is not present",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.allChannelsMenu_XPATH)));

			// Click on Browse Shows Menu
			driver.findElement(By.xpath(XpathObjectRepo.allChannelsMenu_XPATH))
					.click();

			log.info("Successfully clicked on Browse Shows Menu");
			Thread.sleep(sleepTime);

			// Verify Shows By Genre Page title

			assertTrue(
					"User is not Navigated to Shows By Genre page after clicking Browse Shows menu",
					driver.getTitle().contains(UILablesRepo.ALL_CHANNEL_TITLE));
			log.info("The Shows By Genre page title '" + driver.getTitle()
					+ "' is displayed");
			// This method asserts Genre shows title.
			assertionFunction.assertGenreChannelsTitle();

			// Verify GENRE CHANNELS row
			assertTrue("Genre shows section is not present in home page",
					CommonFun.isElementPresent(driver, By
							.xpath(XpathObjectRepo.genreChannelsSection_XPATH)));
			log.info("Genre shows section is present in home page");

			// Verify Genre category row present in GENRE CHANNELS section

			int categorylCount = driver.findElements(
					By.xpath(XpathObjectRepo.genreCategoryRow_XPATH)).size();
			categorylCount = categorylCount / 2;
			HomePageCommonFunctions.scrollToSection();
			HomePageCommonFunctions.scrollToSection();
			Thread.sleep(sleepTime);
			if (categorylCount > 0) {

				int moreLink = driver.findElements(
						By.xpath(XpathObjectRepo.genreCategoryMoreLink_XPATH))
						.size();
				if (moreLink > 0) {

					log.info("More Link is present when more that 8 shows in a row in Genre section");
					log.info("Total number of Generic Category Present: "
							+ categorylCount);
					log.info("Number of category 'More link Present: "
							+ moreLink);

				}

				// Verify the genre buttons are displayed over videos
				/*
				 * for (int i = 1; i <= genreButton; i++) {
				 * 
				 * driver.findElement(
				 * By.xpath("//div[@id='featured_playlist']/descendant::li[" + i
				 * +
				 * "]/descendant::div[contains(@class,'channel_title_box')]/descendant::div"
				 * )) .isDisplayed();
				 * log.info("Genre button is displayed on video " + i);
				 * 
				 * }
				 */
				log.info("");

			} else {
				log.error("Genre section does not contain categories");
				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
