package comcast.test.app.testCases.search;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.footerLink.footerLinkFunctions.FooterLinkFun;
import comcast.test.app.testCases.search.searchFunctions.searchFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name:
 * VerifyClickingOnHomeFooterLinkFromSearchResultPageNavigatesHomePage
 * Description: This test case click on home footer from search result page,
 * navigating back to Home page. the title of the page. Author: Manoj
 * **/

public class VerifyClickingOnHomeFooterLinkFromSearchResultPageNavigatesHomePage
		extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickingOnHomeFooterLinkFromSearchResultPageNavigatesHomePage()
			throws Exception {

		try {

			log.info("Script: VerifyClickingOnHomeFooterLinkFromSearchResultPageNavigatesHomePage");
			log.info("***************************************************************************");
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

			// Verify Home Link is present in footer
			assertTrue(
					"Home Link is not present",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerHelpLink_XPATH)));
			log.info("Home Link is present");

			// Click on footer Home link from search result detail page to
			// navigate back
			// to home page

			FooterLinkFun.clickOnHomeLink();
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully Navigated to Home page after clicking on Home link from search result page");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
