package comcast.test.app.testCases.allChannels;

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
 * Class Name: VeriyAllChannelsPage Description:This test case verify The
 * content of Shows by genre page. 
 * Author: Manoj
 * **/

public class VeriyAllChannelsPage extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	boolean channelPresent = false;

	@Test
	public void testVeriyAllChannelsPage() throws Exception {

		try {

			log.info("Script: VeriyAllChannelsPage");
			log.info("****************************");

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

				log.info("Genre shows section contains Genre categories clicking on All Channels Menu.");
				log.info(categorylCount
						+ " Genre categories are present in Genre shows section in home page clicking on All shows Menu.");
				log.info("The following categories are present in categories");
				log.info("--------------------------------------------------");
				for (int i = 0; i < categorylCount; i++) {
					log.info(i
							+ 1
							+ ". "
							+ driver.findElement(
									By.xpath(XpathObjectRepo.genreCategoryTitlePartOne_XPATH
											+ i
											+ XpathObjectRepo.genreCategoryTitlePartTwoe_XPATH))
									.getText());
					int channelCount = driver
							.findElements(
									By.xpath(XpathObjectRepo.genreCategoryTitlePartOne_XPATH
											+ i
											+ XpathObjectRepo.genreCategoryChannelIcon_XPATH))
							.size();
					if (channelCount > 0) {

						channelPresent = true;
						if (channelCount > 8) {
							channelCount = 8;
						}

						log.info("   " + channelCount
								+ " Shows present under this category");
						log.info("   The shows presented are..");
						log.info("   ------------------------------");

						for (int j = 1; j <= channelCount; j++) {

							if (driver
									.findElements(
											By.xpath(XpathObjectRepo.genreCategoryTitlePartOne_XPATH
													+ i
													+ XpathObjectRepo.genreCategoryChannelCount_XPATH
													+ j
													+ XpathObjectRepo.genreCategoryChannelCountOne_XPATH))

									.size() > 0) {
								log.info("     "
										+ j
										+ ". "
										+ driver.findElement(
												By.xpath(XpathObjectRepo.genreCategoryTitlePartOne_XPATH
														+ i
														+ XpathObjectRepo.genreCategoryChannelCount_XPATH
														+ j
														+ XpathObjectRepo.genreCategoryChannelCountOne_XPATH))
												.getText());
							}
						}

						log.info("");
						HomePageCommonFunctions.scrollToSection();

					} else {
						log.error("No channel present under this categories");
					}

				}

			} else {
				log.error("Genre channel section not contains Genre categories");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
