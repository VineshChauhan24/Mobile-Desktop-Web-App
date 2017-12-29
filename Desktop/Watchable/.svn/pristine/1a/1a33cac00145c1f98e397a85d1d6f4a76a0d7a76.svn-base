package comcast.test.app.testCases.allChannels;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyDisplayOfHeaderAndFooterInShowByGenrePage Description:This
 * test script verifies header and footer sections are present in show by genre
 * page. 
 * Author: Manoj
 * **/

public class VerifyDisplayOfHeaderAndFooterInShowByGenrePage extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	boolean channelPresent = false;

	@Test
	public void testVerifyDisplayOfHeaderAndFooterInShowByGenrePage()
			throws Exception {

		try {

			log.info("Script: VerifyDisplayOfHeaderAndFooterInShowByGenrePage");
			log.info("*********************************************************");

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

			// Verify header section is present in Shows by genre page
			assertTrue(
					"Header section is NOT present in Shows by genre page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.header_XPATH)));
			log.info("Header section is present in Shows by genre page");

			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();
			Thread.sleep(sleepTime);

			// Verify footer section is present in Shows by genre page
			assertTrue(
					"Footer section is NOT present in Shows by genre page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footer_XPATH)));
			log.info("Fotter section is present in Shows by genre page");

			// Verifying Footer Copy Right is present in Shows by genre page
			Thread.sleep(LessSleepTime);
			assertTrue(
					"Copyright text is not present in in Shows by genre page",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.footerCopyRightText_XPATH)));
			log.info("Copyright text is present in in Shows by genre page");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
