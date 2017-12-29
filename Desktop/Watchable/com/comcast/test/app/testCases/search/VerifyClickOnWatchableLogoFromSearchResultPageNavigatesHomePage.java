package comcast.test.app.testCases.search;

import org.junit.Test;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.app.testCases.search.searchFunctions.searchFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyClickOnWatchableLogoFromSearchResultPageNavigatesHomePage
 * Description: This test script verifies clicking on watchable logo from search
 * result page user is taking back to home page. Author: Manoj
 * **/

public class VerifyClickOnWatchableLogoFromSearchResultPageNavigatesHomePage
		extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickOnWatchableLogoFromSearchResultPageNavigatesHomePage()
			throws Exception {

		try {

			log.info("Script: VerifyClickOnWatchableLogoFromSearchResultPageNavigatesHomePage");
			log.info("***********************************************************************");
			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");
			
			// Click on Search icon to make search text box visible
			searchFun.clickOnSearchButton();

			// Enter search text and press enter key
			searchFun
					.enterSearchStringPressEnterKey(UILablesRepo.SEARCH_CHANNEL_NAME);

			// Click on Top Watchable logo from search result page to navigate
			// back
			// to home page

			HomeFun.clickOnTopWatchableLogo();

			// Verify user navigate back to home page after clicking on
			// Watchable log from search result page
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully Navigated to Home page after clicking on Watchable logo from search result Page");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
