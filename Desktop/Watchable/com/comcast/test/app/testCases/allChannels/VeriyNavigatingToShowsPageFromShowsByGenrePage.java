package comcast.test.app.testCases.allChannels;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VeriyNavigatingToShowsPageFromShowsByGenrePage Description:This
 * test case verify navigating to shows page from Shows by genre page
 * Author: Manoj
 * **/

public class VeriyNavigatingToShowsPageFromShowsByGenrePage extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	boolean channelPresent = false;

	@Test
	public void testVeriyNavigatingToShowsPageFromShowsByGenrePage()
			throws Exception {

		try {

			log.info("Script: VeriyNavigatingToShowsPageFromShowsByGenrePage");
			log.info("*****************************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Verify Browse Shows menu is present
			assertTrue(
					"Browse Shows menu is not present",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.allChannelsMenu_XPATH)));

			log.info("Browse Shows menu '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.allChannelsMenu_XPATH))
							.getText() + "' is present");

			// Click on Browse Shows Menu
			driver.findElement(By.xpath(XpathObjectRepo.allChannelsMenu_XPATH))
					.click();
			log.info("Successfully clicked on Browse Shows Menu");
			Thread.sleep(sleepTime);

			// Verify User Successfully Navigated to Shows by genre page

			assertTrue(
					"User is not Navigated to Shows by genre page after clicking Browse Showsmenu",
					driver.getTitle().contains(UILablesRepo.ALL_CHANNEL_TITLE));
			log.info("The Shows by genre page title '" + driver.getTitle()
					+ "' is displayed");

			// This method asserts Genre Channels title is displaying after
			// clicking on All Channels Menu.
			assertionFunction.assertGenreChannelsTitle();

			// Verify Browse Shows row
			assertTrue(
					"Genre shows section is not present in home page clicking on All Channels Menu.",
					CommonFun.isElementPresent(driver, By
							.xpath(XpathObjectRepo.genreChannelsSection_XPATH)));
			log.info("Genre shows section is present in home page clicking on All Channels Menu.");

			// Verify Genre category row present in GENRE CHANNELS section

			int categorylCount = driver.findElements(
					By.xpath(XpathObjectRepo.genreCategoryRow_XPATH)).size();

			categorylCount = categorylCount / 2;

			if (categorylCount <= 12) {

				log.info("Genre shows section contains Genre categories clicking on B Menu.");
				log.info(categorylCount
						+ " Genre categories are present in Genre shows section in home page clicking on All shows Menu.");

				// Click on first Show from Genre shows section
				HomeFun.clickOnFirstGenreChannelLink();

				// Verify User successfully navigate to show page
				AssertionRepoFunctions.assertChannelPageTitle();

			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
