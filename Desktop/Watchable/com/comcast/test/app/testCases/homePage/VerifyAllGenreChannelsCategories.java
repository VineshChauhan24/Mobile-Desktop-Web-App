package comcast.test.app.testCases.homePage;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyAllGenreChannelsCategories Description: This test case
 * verifies channels are present under Genre Categories Author: Manoj
 * **/

public class VerifyAllGenreChannelsCategories extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	boolean channelPresent = false;

	@Test
	public void testVerifyAllGenreChannelsCategories() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyAllGenreChannelsCategories");
			log.info("****************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method is to scroll UI to Genre Channels Section.
			homePageCommonFun.scrollToGenresSection();
			Thread.sleep(sleepTime);

			// This method asserts Genre Channels title.
			assertionFunction.assertGenreChannelsTitle();

			// Verify GENRE CHANNELS row
			assertTrue("Genre channel section is not present in home page",
					CommonFun.isElementPresent(driver, By
							.xpath(XpathObjectRepo.genreChannelsSection_XPATH)));
			log.info("Genre channel section is present in home page");

			// Verify Genre category row present in GENRE CHANNELS section

			int categorylCount = driver.findElements(
					By.xpath(XpathObjectRepo.genreCategoryRow_XPATH)).size();
			categorylCount = categorylCount / 2;
			if (categorylCount > 0) {

				log.info("Genre channel section contains Genre categories");
				log.info(categorylCount
						+ " Genre categories are present in Genre channel section in home page");
				log.info("The following categories are present in categories");
				log.info("--------------------------------------------------");
				for (int i = 0; i < categorylCount; i++) {
					if (driver
							.findElements(
									By.xpath(XpathObjectRepo.genreCategoryTitlePartOne_XPATH
											+ i
											+ XpathObjectRepo.genreCategoryTitlePartTwoe_XPATH))
							.size() > 0) {
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
							log.info("   " + channelCount
									+ " Channel(s) present under this category");
							log.info("   The channel(s) presented are..");
							log.info("   ------------------------------");

							for (int j = 1; j <= channelCount; j++) {
								log.info("     "
										+ j
										+ ". "
										+ driver.findElement(
												By.xpath(XpathObjectRepo.genreCategoryTitlePartOne_XPATH
														+ i
														+ XpathObjectRepo.genreCategoryChannelIcon_XPATH
														+ "["
														+ j
														+ XpathObjectRepo.genreCategoryChannelCount_XPATH))
												.getText());
							}

							log.info("");

						} else {
							log.error("No channel present under this categories");
						}

					}
				}

			} else {
				log.error("Genre channel section not contains Genre categories");
			}
			if (channelPresent == true) {

				// Click on any channel from GENRE CHANNELS section and verify
				// user is taking to channel page

				HomeFun.clickOnFirstGenreChannelLink();
				log.info("");

			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
