package comcast.test.app.testCases.search;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.app.testCases.search.searchFunctions.searchFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyUserNavigatingToHomePageLogoutFromSearchResultPage
 * Description: This test case verifies user is navigating back to home page if
 * user logout from Search result page. Author: Manoj
 * **/

public class VerifyUserNavigatingToHomePageLogoutFromSearchResultPage extends
		BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyUserNavigatingToHomePageLogoutFromSearchResultPage()
			throws Exception {

		try {

			log.info("Script: VerifyUserNavigatingToHomePageLogoutFromSearchResultPage");
			log.info("****************************************************************");
			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Login to Watchable application
			LoginFun.loginToWatchableApplication(driver, UILablesRepo.EMAIL,
					UILablesRepo.PASSWORD);
			WebElement loginError = driver.findElement(By
					.id(XpathObjectRepo.loginError_ID));

			if (loginError.isDisplayed() == false) {

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

				// Logout from Watchable Application.
				LoginFun.logOut(driver);

				// Verify user is navigate back to Home Page after logout from
				// My Shows Page
				AssertionRepoFunctions.assertWatchableTitle();
				log.info("Successfully navigate back to Home Page after logout from search result Page");

				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
