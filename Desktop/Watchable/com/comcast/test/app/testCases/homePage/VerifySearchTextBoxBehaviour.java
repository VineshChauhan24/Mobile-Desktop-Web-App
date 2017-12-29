package comcast.test.app.testCases.homePage;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifySearchTextBoxBehaviour 
 * Description: This test script verify the Behaviour of the search text bow in header in home page
 * Author: Manoj
 * **/

public class VerifySearchTextBoxBehaviour extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifySearchTextBoxBehaviour() throws Exception {

		try {

			log.info("Script: VerifySearchTextBoxBehaviour");
			log.info("*************************************");
			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			Thread.sleep(LessSleepTime);

			// Verifying Search icon is present in header
			Thread.sleep(LessSleepTime);
			assertTrue(
					"Search icon is not present in header",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.searchButton_XPATH)));
			log.info("Search icon is present in header");

			// Verify search text box is hidden by default
			assertFalse("Search text box is not hidden by default", driver
					.findElement(By.xpath(XpathObjectRepo.searchText_XPATH))
					.isDisplayed());
			log.info("Search text box is hidden by default");

			// Mouse over on Search icon to make search text box visible
			CommonFun.mouseOverElement(driver, driver.findElement(By
					.xpath(XpathObjectRepo.searchButton_XPATH)));

			//Thread.sleep(LessSleepTime);

			// Verify search text box is visible after mouse over search icon
			assertTrue(
					"Search text box is not visible after mouse over search icon",
					driver.findElement(
							By.xpath(XpathObjectRepo.searchText_XPATH))
							.isDisplayed());
			log.info("Search text box is visible after mouse over search icon");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
