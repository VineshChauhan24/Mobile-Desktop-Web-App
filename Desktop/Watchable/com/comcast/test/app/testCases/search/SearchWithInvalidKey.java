package comcast.test.app.testCases.search;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.search.searchFunctions.searchFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: SearchWithInvalidKey Description: This test script verify search
 * functionality by searching with invalid key having no search result.Author:
 * Manoj
 * **/

public class SearchWithInvalidKey extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testSearchWithInvalidKey() throws Exception {

		try {

			log.info("Script: SearchWithInvalidKey");
			log.info("****************************");
			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Verifying Search icon(button) is present in header
			Thread.sleep(LessSleepTime);
			assertTrue(
					"Search icon(button) is not present in header",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.searchButton_XPATH)));
			log.info("Search icon(button) is present in header");
			
			// Click on Search icon to make search text box visible
			searchFun.clickOnSearchButton();

			// Verifying Search text box is present in header
			Thread.sleep(LessSleepTime);
			assertTrue(
					"Search text box is not present in header",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.searchText_XPATH)));
			log.info("Search text box is present in header");
			
			// Enter search text and click on search icon
			searchFun
					.enterSearchStringPressEnterKey(UILablesRepo.SEARCH_INVALID_KEY);

			int noSearchResult = driver.findElements(
					By.xpath(XpathObjectRepo.searchNoResultTitle_XPATH)).size();

			if (noSearchResult == 0) {

				log.info(driver.findElement(
						By.xpath(XpathObjectRepo.searchResultTitle_XPATH))
						.getText());
				log.info("--------------------------------------------------");

				// Verifying show count in search result
				int channelCount = driver
						.findElements(
								By.xpath(XpathObjectRepo.searchResultChannelIcon_XPATH))
						.size();

				if (channelCount > 0) {
					log.info(channelCount + " Shows present in search result");

					log.info("The shows present in search result are:");

					// Click on 'SHOW ALL' Link
					searchFun.clickonShowAllLink();

					for (int i = 1; i <= channelCount; i++) {

						log.info(i
								+ ". "
								+ driver.findElement(
										By.xpath(XpathObjectRepo.searchResultTChannelTitle_XPATH
												+ i + "]/descendant::h1/a"))
										.getText());
					}
				}

				log.info("");
				// Verifying video count in search result
				int videoCount = driver.findElements(
						By.xpath(XpathObjectRepo.searchResultVideoIcon_XPATH))
						.size();
				if (videoCount > 0) {
					log.info(videoCount + " Video(s) present in search result");

					log.info("The Video(s) present in search result are:");

					// Scroll to video section in search result
					HomePageCommonFunctions.scrollToVideoResultSection();

					for (int i = 1; i <= videoCount; i++) {

						log.info(i
								+ ". "
								+ driver.findElement(
										By.xpath(XpathObjectRepo.searchResultVideoTitle_XPATH
												+ i + "]/descendant::h1/a[2]"))
										.getText());
					}
				}
			} else {
				log.info(driver.findElement(
						By.xpath(XpathObjectRepo.searchNoResultTitle_XPATH))
						.getText());

				// Verify Popular videos displayed when no search result
				assertTrue(
						"Popular videos not displayed when no search result",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.searchNoResultPopulaVideoTitle_XPATH)));
				log.info("Popular videos displayed when no search result");

				int videoCount = driver.findElements(
						By.xpath(XpathObjectRepo.featuredVideosIcon_XPATH))
						.size();
				if (videoCount > 0) {

					log.info(videoCount
							+ " Videos are present in Popular videos section");
					log.info("The following videos are present in Popular videos section");
					log.info("----------------------------------------------------------");
					for (int i = 1; i <= videoCount; i++) {
						log.info(i
								+ ". "
								+ driver.findElement(
										By.xpath(XpathObjectRepo.featuredVideoTitle_XPATH
												+ i + "]")).getText());
					}
				}

			}

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
