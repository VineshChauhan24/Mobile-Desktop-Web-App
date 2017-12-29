package comcast.test.app.testCases.misc;

import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.testCases.misc.miscFunctions.MiscFun;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyNavigatingToShowPageFromErrorPage Description: This test
 * case verifies navigating to show page from error page clicking on any show title
 * from What we're watching section. 
 * Author: Manoj
 **/

public class VerifyNavigatingToShowPageFromErrorPage extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyNavigatingToShowPageFromErrorPage() throws Exception {

		try {

			log.info("Script: VerifyNavigatingToShowPageFromErrorPage");
			log.info("*************************************************");

			// Navigate to the Error page (Page Not Found)
			driver.get(UILablesRepo.PAGENOTFOUNDURL);
			Thread.sleep(sleepTime);

			// Verify Error page (Page Not Found) displayed
			AssertionRepoFunctions.assertErrorPageTitle();

			// Verify videos are present in What we're watching section

			int videoCount = driver
					.findElements(
							By.xpath(XpathObjectRepo.pageNotFoundVideoSectionIcon_XPATH))
					.size();

			if (videoCount > 0) {
				log.info(videoCount
						+ " Videos are present in What we're watching section.");

				// Click on first show title from What we're watching section
				MiscFun.clickOnShowTitle();

				// Verify successfully navigate to Show page
				AssertionRepoFunctions.assertChannelPageTitle();

			} else {
				log.info(videoCount
						+ "Videos are present in What we're watching section.");
			}

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}