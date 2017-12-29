package comcast.test.app.testCases.playerVideoPage;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.playerVideoPage.playerPageFunctions.PlayerPageFun;
import comcast.test.app.testCases.search.searchFunctions.searchFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyNavigatingToVideoPageFromSearchResultPage Description: This
 * test script verifies navigating to video page by selecting any video from
 * videos section of search result page. Author: Manoj
 * **/

public class VerifyNavigatingToVideoPageFromSearchResultPage extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyNavigatingToVideoPageFromSearchResultPage()
			throws Exception {

		try {

			log.info("Script: VerifyNavigatingToVideoPageFromSearchResultPage");
			log.info("*******************************************************");
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
					.enterSearchStringPressEnterKey(UILablesRepo.SEARCH_CHANNEL_NAME);

			int noSearchResult = driver.findElements(
					By.xpath(XpathObjectRepo.searchNoResultTitle_XPATH)).size();

			if (noSearchResult == 0) {

							
				// Verifying video count in search result
				int videoCount = driver.findElements(
						By.xpath(XpathObjectRepo.searchResultVideoIcon_XPATH))
						.size();
				if (videoCount > 0) {
					
					// Click on first video from featured video section
					PlayerPageFun.clickOnSearchResultVideoTitle();

					// Verify User is navigated to video page
					AssertionRepoFunctions.assertVideoPageTitle();

					// Verify video player is displayed in video page
					AssertionRepoFunctions.assertVideoPlayer();

					
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
