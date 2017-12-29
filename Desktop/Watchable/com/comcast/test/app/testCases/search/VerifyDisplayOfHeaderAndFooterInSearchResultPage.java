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
 * Class Name: VerifyDisplayOfHeaderAndFooterInSearchResultPage Description:
 * This test case verifies the header and footer sections are present in search
 * result page. 
 * Author: Manoj
 * **/

public class VerifyDisplayOfHeaderAndFooterInSearchResultPage extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyDisplayOfHeaderAndFooterInSearchResultPage()
			throws Exception {

		try {

			log.info("Script: VerifyDisplayOfHeaderAndFooterInSearchResultPage");
			log.info("********************************************************");
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
					.enterSearchStringPressEnterKey(UILablesRepo.SEARCH_CHANNEL_ID);

			// Verify header section is present in Search Result page
			assertTrue(
					"Header section is NOT present in Search Result page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.header_XPATH)));
			log.info("Header section is present in Search Result page");

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();
			Thread.sleep(sleepTime);

			// Verify footer section is present in Search Result page
			assertTrue(
					"Footer section is NOT present in Search Result page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footer_XPATH)));
			log.info("Fotter section is present in Search Result page");

			// Verifying Footer Copy Right is present in Search Result
			// page
			Thread.sleep(LessSleepTime);
			assertTrue(
					"Copyright text is not present in in Search Result page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerCopyRightText_XPATH)));
			log.info("Copyright text is present in in Search Result page");
			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
