package comcast.test.app.testCases.myWatchlist;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.app.testCases.myWatchlist.myWatchlistFunctions.MyWatchlistFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyDisplayOfHeaderAndFooterInMyShowsPage Description: This
 * test case verifies header and footer sections are present in My Shows page.
 * Author: Manoj
 * **/

public class VerifyDisplayOfHeaderAndFooterInMyShowsPage extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyDisplayOfHeaderAndFooterInMyShowsPage()
			throws Exception {

		try {

			log.info("Script: VerifyDisplayOfHeaderAndFooterInMyShowsPage");
			log.info("***********************************************");

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

				// Verify My Shows menu is present
				assertTrue(
						"My Shows menu is not present",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.myWatchlistMenu_XPATH)));
				log.info("My Shows menu is present");

				// Click on My Shows menu
				MyWatchlistFun.clickOnMyWatchlistMenu();

				// Verify user is navigated to My Shows page
				AssertionRepoFunctions.assertMyWatchlistPageTitle();

				// Verify header section is present in My Shows page
				assertTrue(
						"Header section is NOT present in My Shows page",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.header_XPATH)));
				log.info("Header section is present in My Shows page");

				// Scroll to Footer
				HomePageCommonFunctions.scrollToFooterSection();
				Thread.sleep(sleepTime);

				// Verify footer section is present in My Shows page
				assertTrue(
						"Footer section is NOT present in My Shows page",
						CommonFun.isElementPresent(driver,
								By.xpath(XpathObjectRepo.footer_XPATH)));
				log.info("Fotter section is present in My Shows page");

				// Verifying Footer Copy Right is present in My Shows page
				Thread.sleep(LessSleepTime);
				assertTrue(
						"Copyright text is not present in in My Shows page",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.footerCopyRightText_XPATH)));
				log.info("Copyright text is present in in My Shows page");

				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
