package comcast.test.app.testCases.homePage;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.testCases.homePage.homePageFunctions.HomeFun;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyFaceBookSharingFromHomePage Description: This
 * test case verifies the face book sharing from home page.
 * Author: Manoj
 * **/

public class VerifyFaceBookSharingFromHomePage extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyFaceBookSharingFromHomePage()
			throws Exception {

		try {

			// Test script
			log.info("Script: VerifyFaceBookSharingFromHomePage");
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

			// Verify Presence of Face Book sharing option
			assertTrue(
					"Face Book sharing option is not present in home page",
					CommonFun.isElementPresent(
							driver,
							By.xpath(XpathObjectRepo.homePageFaceBookButton_XPATH)));
			log.info("Face Book sharing option is present in home page");
			
			//Click on face book sharing button
			HomeFun.clickOnFaceBookButton();
			
			String parentWindow = driver.getWindowHandle();
			Set<String> handles = driver.getWindowHandles();

			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					driver.switchTo().window(windowHandle);
					Thread.sleep(sleepTime);
					log.info("Share a link on Face book popup window opened after clicking on Face book button");

					assertTrue(
							"Face Book pop up window NOT opened after clicking on FB button",
							driver.getTitle().contains(
									UILablesRepo.FB_TITLE));
					log.info("Face Book pop up window opened after clicking on FB button");
					log.info("Face book page title displayed is: " + driver.getTitle());

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
