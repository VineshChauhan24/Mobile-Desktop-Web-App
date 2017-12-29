package comcast.test.app.testCases.playLists;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VeriyPlayListsTitle Description: This test case verifies Presence
 * of Play Lists Menu. Author: Manoj
 * **/

public class VeriyPlayListsTitle extends BaseTest {
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVeriyPlayListsTitle() throws Exception {

		try {

			log.info("Script: VeriyPlayListsTitle");
			log.info("*****************************");

			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			Thread.sleep(sleepTime);

			// Verify Play List menu is present
			assertTrue(
					"Play List menu is not present",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.playLists_XPATH)));
			log.info("Play List menu is present");

			// Verify Play List menu Title
			assertTrue(driver
					.findElement(By.xpath(XpathObjectRepo.playLists_XPATH))
					.getText().matches(UILablesRepo.TOPMENU_PLAYLISTS));
			log.info("The menu title '"
					+ driver.findElement(
							By.xpath(XpathObjectRepo.playLists_XPATH))
							.getText() + "' is present");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}

}
