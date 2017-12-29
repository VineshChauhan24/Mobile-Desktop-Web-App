package comcast.test.app.testCases.search;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.search.searchFunctions.searchFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyNavigatingToShowPageFromShowSectionFromSearchResult
 * Description: This test script verify Navigating to show page by clicking on
 * show title from shows section of search result. Author: Manoj
 * **/

public class VerifyNavigatingToShowPageFromShowSectionFromSearchResult extends
		BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyNavigatingToShowPageFromShowSectionFromSearchResult()
			throws Exception {

		try {

			log.info("Script: VerifyNavigatingToShowPageFromShowSectionFromSearchResult");
			log.info("***************************************************************");
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

				log.info(driver.findElement(
						By.xpath(XpathObjectRepo.searchResultTitle_XPATH))
						.getText());
				log.info("--------------------------------------------------");

				// Verifying shows count in search result
				int channelCount = driver
						.findElements(
								By.xpath(XpathObjectRepo.searchResultChannelIcon_XPATH))
						.size();

				if (channelCount > 0) {
					log.info(channelCount + " Shows present in search result");

					log.info("The Shows present in search result are:");

					// Click on first shows title
					searchFun.clickonShowTitleFromShowSection();

					// Verify successfully navigate to shows details page
					AssertionRepoFunctions.assertChannelPageTitle();

				}

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
