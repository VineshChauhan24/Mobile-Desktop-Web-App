package comcast.test.app.testCases.homePage;

import static org.junit.Assert.assertTrue;
import java.util.Set;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyInstagramSharingFromHomePage Description: This test case
 * verifies the Instagram sharing from home page. Author: Manoj
 * **/

public class VerifyInstagramSharingFromHomePage extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyInstagramSharingFromHomePage() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyInstagramSharingFromHomePage");
			log.info("*****************************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// Verify social sharing section present in home page
			assertTrue(
					"Social sharing section is not present in home page",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.homePageSocialSharingSection_XPATH)));
			log.info("Social sharing section is present in home page");

			// Verify Presence of Instagram sharing option
			assertTrue(
					"Instagram sharing option is not present in home page",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.homePageInstagramButton_XPATH)));
			log.info("Instagram sharing option is present in home page");

			// Click on Instagram sharing button
			HomeFun.clickOnInstagramButton();

			String parentWindow = driver.getWindowHandle();
			Set<String> handles = driver.getWindowHandles();

			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					driver.switchTo().window(windowHandle);
					Thread.sleep(sleepTime);
					log.info("Share a link on Instagram popup window opened after clicking on Instagram button");
					log.info("Instagram page title displayed is: " + driver.getTitle());
					// closing child window
					driver.close();

					// Control to parent window
					driver.switchTo().window(parentWindow);

				}

			}

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
