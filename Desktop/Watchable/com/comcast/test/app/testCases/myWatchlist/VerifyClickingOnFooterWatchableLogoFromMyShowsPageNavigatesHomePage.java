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
import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.app.testCases.loginPageAndLogin.loginPageFunctions.LoginFun;
import comcast.test.app.testCases.myWatchlist.myWatchlistFunctions.MyWatchlistFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name:
 * VerifyClickingOnFooterWatchableLogoFromMyShowsPageNavigatesHomePage
 * Description: This test script click on watchable logo from footer from My
 * Shows page, and verifies user navigating back to Home page. 
 * Author: Manoj
 * **/

public class VerifyClickingOnFooterWatchableLogoFromMyShowsPageNavigatesHomePage
		extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyClickingOnFooterWatchableLogoFromMyShowsPageNavigatesHomePage()
			throws Exception {

		try {

			log.info("Script: VerifyClickingOnFooterWatchableLogoFromMyShowsPageNavigatesHomePage");
			log.info("***************************************************************************");

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

				// Verify user is navigated to My Shows list page
				AssertionRepoFunctions.assertMyWatchlistPageTitle();

				// Scroll to Footer
				HomePageCommonFunctions.scrollToFooterSection();
				Thread.sleep(sleepTime);

				// Verify Watchable logo is present in footer
				assertTrue(
						"Watchable logo is not present in footer",
						CommonFun.isElementPresent(
								driver,
								By.xpath(XpathObjectRepo.footerwatchableLogo_XPATH)));
				log.info("Watchable logo is present in footer");

				// Click on footer Watchable logo from My shows page to
				// navigate back
				// to home page

				HomeFun.clickOnBottomWatchableLogo();

				AssertionRepoFunctions.assertWatchableTitle();
				log.info("Successfully Navigated to Home page after clicking on footer Watchable logo from My Shows page");

				log.info("");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
