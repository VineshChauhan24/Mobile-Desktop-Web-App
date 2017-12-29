package comcast.test.app.testCases.footerLink;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.footerLink.footerLinkFunctions.FooterLinkFun;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyUserNavigatingToHomePageLogoutFromHelpPage Description:
 * This test case verifies user is navigating back to home page if user logout
 * from Help page. Author: Manoj
 * **/

public class VerifyUserNavigatingToHomePageLogoutFromHelpPage extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyUserNavigatingToHomePageLogoutFromHelpPage()
			throws Exception {

		try {

			log.info("Script: VerifyUserNavigatingToHomePageLogoutFromHelpPage");
			log.info("*******************************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			Thread.sleep(sleepTime);

			// Login to Watchable application
			LoginFun.loginToWatchableApplication(driver, UILablesRepo.EMAIL,
					UILablesRepo.PASSWORD);
			WebElement loginError = driver.findElement(By
					.id(XpathObjectRepo.loginError_ID));

			if (loginError.isDisplayed() == false) {

				// Scroll to Footer
				HomePageCommonFunctions.scrollToFooterSection();

				// Verify Help Link is present in footer
				assertTrue(
						"Help Link is not present",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.footerHelpLink_XPATH)));
				log.info("Help Link is present");

				// Click on Help Link
				FooterLinkFun.clickOnHelpLink();

				// Verify Help page title
				AssertionRepoFunctions.assertHelpPageTitle();

				// Logout from Watchable Application.
				LoginFun.logOut(driver);

				// Verify user is navigate back to Home Page after logout from
				// My Shows Page
				AssertionRepoFunctions.assertWatchableTitle();
				log.info("Successfully navigate back to Home Page after logout from Help Page");
				log.info("");

			}
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
