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
 * Class Name: VerifySearchResultPageContentWithResult Description: This test
 * case verifies the contents (Objects) of search result page when there are
 * search results. Author: Manoj
 * **/

public class VerifySearchResultPageContentWithResult extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifySearchResultPageContentWithResult() throws Exception {

		try {

			log.info("Script: VerifySearchResultPageContentWithResult");
			log.info("***********************************************");
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

			// Enter search text and press enter key
			searchFun
					.enterSearchStringPressEnterKey(UILablesRepo.SEARCH_CHANNEL_NAME);

			int noSearchResult = driver.findElements(
					By.xpath(XpathObjectRepo.searchNoResultTitle_XPATH)).size();

			if (noSearchResult == 0) {

				HomePageCommonFunctions.scrollToVideoResultMsgSection();
				// Verify Search Result Title
				assertTrue(
						"Search result title not present in search result page",
						CommonFun.isElementPresent(driver, By
								.xpath(XpathObjectRepo.searchResultTitle_XPATH)));
				log.info(" The Search result title '"
						+ driver.findElement(
								By.xpath(XpathObjectRepo.searchResultTitle_XPATH))
								.getText() + "' present in search result page");

				// Verify Channel Title
				assertTrue(
						"Channel title not present in search result page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.searchResultTChannelLabel_XPATH)));
				log.info("The Channel title '"
						+ driver.findElement(
								By.xpath(XpathObjectRepo.searchResultTChannelLabel_XPATH))
								.getText() + "' present in search result page");

				// Verify Channel row
				assertTrue(
						"Channel row not present in search result page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.searchResultChannelRow_XPATH)));

				log.info("Channel row present in search result page");

				// Verify video Title
				assertTrue(
						"Video title not present in search result page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.searchResultTVideoLabel_XPATH)));
				log.info("The Video title '"
						+ driver.findElement(
								By.xpath(XpathObjectRepo.searchResultTVideoLabel_XPATH))
								.getText() + "' present in search result page");

				// Verify video row
				assertTrue(
						"Video row not present in search result page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.searchResultVideoRow_XPATH)));

				log.info("Video row present in search result page");

			} else {
				log.info(driver.findElement(
						By.xpath(XpathObjectRepo.searchNoResultTitle_XPATH))
						.getText());
			}

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
