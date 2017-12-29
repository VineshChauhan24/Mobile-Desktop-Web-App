package comcast.test.app.testCases.allChannels;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.footerLink.footerLinkFunctions.FooterLinkFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyClickOnHomeFooterLinkFromAllShowsPageNavigatesHomePage
 * Description:This test script verifies clicking on Home footer link from all
 * shows page user is taking back to home page. 
 * Author: Manoj
 * **/

public class VerifyClickOnHomeFooterLinkFromAllShowsPageNavigatesHomePage
		extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	boolean channelPresent = false;

	@Test
	public void testVerifyClickOnHomeFooterLinkFromAllShowsPageNavigatesHomePage()
			throws Exception {

		try {

			log.info("Script: VerifyClickOnHomeFooterLinkFromAllShowsPageNavigatesHomePage");
			log.info("*******************************************************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Verify Browse Shows menu is present
			assertTrue(
					"Browse Shows menu is not present",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.allChannelsMenu_XPATH)));

			log.info("Browse Shows menu '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.allChannelsMenu_XPATH))
							.getText() + "' is present");

			// Click on Browse Shows Menu
			driver.findElement(By.xpath(XpathObjectRepo.allChannelsMenu_XPATH))
					.click();
			log.info("Successfully clicked on Browse Shows Menu");
			Thread.sleep(sleepTime);

			// Verify User Successfully Navigated to Shows by genre page

			assertTrue(
					"User is not Navigated to all Shows page after clicking ALL Shows menu",
					driver.getTitle().contains(UILablesRepo.ALL_CHANNEL_TITLE));
			log.info("The All Shows page title '" + driver.getTitle()
					+ "' is displayed");

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();
			Thread.sleep(sleepTime);

			// Verify Home Link is present in footer
			assertTrue(
					"Home Link is not present",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerHelpLink_XPATH)));
			log.info("Home Link is present");

			// Click on Top home footer link from all shows page to navigate
			// back
			// to home page

			FooterLinkFun.clickOnHomeLink();

			// Verify user navigate back to home page after clicking on
			// Watchable log from All shows page
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully Navigated to Home page after clicking on Watchable logo from All Shows Page");

			

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
