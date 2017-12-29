package comcast.test.app.testCases.search;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.app.testCases.search.searchFunctions.searchFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyClickingOnFooterWatchableLogoFromSearchResultPageNavigatesHomePage
 * Description: This test script click on footer watchable logo from search
 * result page, verifies user navigating back to Home page. the title of the
 * page. 
 * Author: Manoj
 * **/

public class VerifyClickingOnFooterWatchableLogoFromSearchResultPageNavigatesHomePage
		extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickingOnFooterWatchableLogoFromSearchResultPageNavigatesHomePage()
			throws Exception {

		try {

			log.info("Script: VerifyClickingOnFooterWatchableLogoFromSearchResultPageNavigatesHomePage");
			log.info("********************************************************************************");
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
					.enterSearchStringPressEnterKey(UILablesRepo.SEARCH_VIDEO_ID);

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();
			Thread.sleep(sleepTime);

			// Verify Watchable logo is present in footer
			assertTrue(
					"Watchable logo is not present in footer",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerwatchableLogo_XPATH)));
			log.info("Watchable logo is present in footer");

			// Click on footer Watchable logo from search result page to
			// navigate back
			// to home page

			HomeFun.clickOnBottomWatchableLogo();

			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully Navigated to Home page after clicking on footer Watchable logo from search result page");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
