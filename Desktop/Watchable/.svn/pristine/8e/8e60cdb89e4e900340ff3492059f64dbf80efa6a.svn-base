package comcast.test.app.testCases.channelPage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.channelPage.channelPageFunctions.ChannelPageFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;


/**
 * Class Name: VerifyPreviousButton Description: This test cases verifies the
 * functionality of Previous button in video section of shows detail page.
 * Author: Manoj
 * **/

public class VerifyPreviousButton extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyPreviousButton() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyPreviousButton");
			log.info("****************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method is to scroll UI to Watchable SHOWS OF THE WEEK Section.
			HomePageCommonFunctions.scrollToPopularChannelsSection();
			Thread.sleep(sleepTime);

			// Verify shows present in Watchable SHOWS OF THE WEEK row

			int channelCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredChannelsIcon_XPATH))
					.size();
			if (channelCount > 0) {

				// Click on first show from Watchable SHOWS OF THE WEEK section

				ChannelPageFun.clickOnFirstFeaturedChannelLink();

				// Verify successfully navigate to show details page
				AssertionRepoFunctions.assertChannelPageTitle();

				// This method is to scroll UI to video Section.
				ChannelPageFun.scrollToVideoSection();
				Thread.sleep(sleepTime);

				// Verify videos present in show detail page
				boolean isPreviousPresent = false;
				int videoCount = driver.findElements(
						By.xpath(XpathObjectRepo.channelPageVideoIcon_XPATH))
						.size();
				if (videoCount > 0) {

					log.info("Videos are present in shows detail page");

					// Verify next page button in video section of channel
					// detail
					// page
					assertTrue(
							"Previous page button is not present in video section of shows detail page",
							CommonFun.isElementPresent(
									driver,
									By.xpath(XpathObjectRepo.channelPagePreviousPageButton_XPATH)));
					log.info("Previous page button is present in video section of shows detail page");

					// Getting Total Page count in Video section
					String page = driver
							.findElement(
									By.xpath(XpathObjectRepo.channelPagePageNumberButton_XPATH))
							.getText().substring(5);

					int pageCount = Integer.parseInt(page);

					log.info(pageCount + " Pages present in video section");

					// Verify Next button is enabled if page count is more than
					// 1

					if (pageCount > 1) {
						String isPreviousEnable = driver
								.findElement(
										By.xpath(XpathObjectRepo.channelPagePreviousPageButton_XPATH))
								.getAttribute("class");

						assertFalse(
								"Previous button is enabled in first page of video section",
								isPreviousEnable.contains("active"));
						log.info("Previous button is not enabled in first page of video section");
						isPreviousPresent = false;

						if (isPreviousPresent == false) {

							for (int i = 1; i <= pageCount; i++) {

								// Click on Next Page button to go to last page
								ChannelPageFun.clickOnNextPageButton();

							}

						}

					}

					String isPreviousEnable = driver
							.findElement(
									By.xpath(XpathObjectRepo.channelPagePreviousPageButton_XPATH))
							.getAttribute("class");

					assertTrue(
							"Previous button is not enabled in last page of video section",
							isPreviousEnable.contains("active"));
					log.info("Previous button is enabled in last page of video section");
					isPreviousPresent = true;

					if (isPreviousPresent == true) {

						for (int i = 1; i <= pageCount; i++) {

							// Click on Previous Page button to go to first page
							ChannelPageFun.clickOnPreviousPageButton();

							int j = pageCount - i;
							if (j > 0) {
								log.info("Navigate back to '" + j
										+ "' Page in video section");
							}

						}
					}

					isPreviousEnable = driver
							.findElement(
									By.xpath(XpathObjectRepo.channelPagePreviousPageButton_XPATH))
							.getAttribute("class");

					assertFalse(
							"Previous button is enabled in first page of video section",
							isPreviousEnable.contains("active"));
					log.info("Previous button is not enabled in first page of video section");

				} else {
					log.info("Videos are not present in shows detail page");
				}

				log.info("");

			} else {
				log.error("Shows are not present in Watchable SHOWS OF THE WEEK section in home page");
				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
