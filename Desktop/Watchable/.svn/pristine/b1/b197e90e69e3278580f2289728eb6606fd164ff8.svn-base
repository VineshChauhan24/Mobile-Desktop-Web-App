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
 * Class Name: VeriyDataDisplayingInAllChannelsPage 
 * Description:This test scripts verify data is displaying in Shows by genre page. 
 * Author: Manoj
 * **/

public class VeriyDataDisplayingInAllChannelsPage extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	boolean channelPresent = false;

	@Test
	public void testVeriyDataDisplayingInAllChannelsPage() throws Exception {

		try {

			log.info("Script: VeriyDataDisplayingInAllChannelsPage");
			log.info("****************************");

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

			// Click on Browse Shows Menu
			driver.findElement(By.xpath(XpathObjectRepo.allChannelsMenu_XPATH))
					.click();
			log.info("Successfully clicked on Browse Shows Menu");
			Thread.sleep(sleepTime);

			// Verify User Successfully Navigated to Shows by genre page

			assertTrue(
					"User is not Navigated to Shows by genre page after clicking Browse Showsmenu",
					driver.getTitle().contains(UILablesRepo.ALL_CHANNEL_TITLE));
			log.info("The Shows by genre page title '" + driver.getTitle()
					+ "' is displayed");

			
			// Scroll to Footer
			HomePageCommonFunctions.scrollToFooterSection();
			Thread.sleep(sleepTime);

			// Verify shows present in shows by genre page

			int showCount = driver.findElements(
					By.xpath(XpathObjectRepo.allShowsPageVideoIcon_XPATH))
					.size();

			if (showCount > 0) {

				log.info("Data is present in shows by genre page.");
				log.info(showCount
						+ " shows are present in shows by genre page.");
		

			} else {
				log.error("Data is not present in shows by genre page.");
			}

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
