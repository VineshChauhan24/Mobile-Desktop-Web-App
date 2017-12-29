package comcast.test.app.testCases.homePage;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyPresenceOfSocialSharingOptionsInHomePage Description: This
 * test case verifies the presence of social sharing options in home page.
 * Author: Manoj
 * **/

public class VerifyPresenceOfSocialSharingOptionsInHomePage extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyPresenceOfSocialSharingOptionsInHomePage()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyPresenceOfSocialSharingOptionsInHomePage");
			log.info("*****************************************************");

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

			// Verify Presence of Face Book sharing option
			assertTrue(
					"Face Book sharing option is not present in home page",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.homePageFaceBookButton_XPATH)));
			log.info("Face Book sharing option is present in home page");

			// Verify Presence of Twitter sharing option
			assertTrue(
					"Twitter sharing option is not present in home page",
					CommonFun.isElementPresent(driver, By
							.xpath(XpathObjectRepo.homePageTwitterButton_XPATH)));
			log.info("Twitter sharing option is present in home page");

			// Verify Presence of Instagram sharing option
			assertTrue(
					"Instagram sharing option is not present in home page",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.homePageInstagramButton_XPATH)));
			log.info("Instagram sharing option is present in home page");

			log.info("");

		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
