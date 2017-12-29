package comcast.test.app.testCases.homePage;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyHeaderLogoMenuSeparaterDisplay Description: This test case
 * verifies a seperater present between header watchable log and menu items.
 *  Author: Manoj
 * **/

// Manoj Added

public class VerifyHeaderLogoMenuSeparaterDisplay extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyHeaderLogoMenuSeparaterDisplay() throws Exception {

		try {

			log.info("Script: VerifyHeaderLogoMenuSeparaterDisplay");
			log.info("********************************************");
			// Navigate to the Home page of the application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			Thread.sleep(LessSleepTime);
			
			// Verifying a seperater present between header watchable log and menu items.
			assertTrue(
					"Seperater not present between header watchable log and menu items.",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.headerLogoMenuSeparater_XPATH)));
			log.info("Seperater present between header watchable log and menu items.");

										
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
