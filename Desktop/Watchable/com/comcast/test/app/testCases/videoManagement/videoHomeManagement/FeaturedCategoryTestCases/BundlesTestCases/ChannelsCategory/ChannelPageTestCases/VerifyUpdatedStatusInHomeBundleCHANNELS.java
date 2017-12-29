package comcast.test.app.testCases.videoManagement.videoHomeManagement.FeaturedCategoryTestCases.BundlesTestCases.ChannelsCategory.ChannelPageTestCases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.constant.XidioConstant;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyUpdatedStatusInHomeBundleCHANNELS Description: This test
 * case is to verify the last updated status/day for a Channels in Bundle
 * CHANNELS category displayed under 'Featured' section on 'Home' screen by
 * logging into Gazeebo application.
 */

public class VerifyUpdatedStatusInHomeBundleCHANNELS extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyUpdatedStatusInHomeBundleCHANNELS() throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.FeaturedBundleAPI();
		List<VideoDetails> BundleChannelList = videoDetails
				.get("showsInBundle");

		try {

			/*
			 * This Method is to register new user using Gazeebo application and
			 * to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			Thread.sleep(sleepTime);
			assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
					.matches("^[\\s\\S]*Featured[\\s\\S]*$"));

			// This Method verifies bundle present in Featured list and selects
			// a bundle.
			homePageCommonFun.selectBundle();

			driver.findElement(By.linkText("CHANNELS")).click();

			Thread.sleep(sleepTime);
			driver.findElement(
					By.linkText(BundleChannelList.get(
							XidioConstant.selectBundleChannel).getTitle()))
					.click();

			Thread.sleep(sleepTime);
			assertTrue(driver
					.findElement(By.cssSelector("BODY"))
					.getText()
					.matches(
							"^[\\s\\S]*"
									+ BundleChannelList.get(
											XidioConstant.selectBundleChannel)
											.getTitle() + "[\\s\\S]*$"));

			/* Have to Rest Service Call to get Updated value */
			String lastUpdated = driver
					.findElement(
							By.xpath(".//*[@id='content-wrap']/article/div/div[2]/div[2]/div[1]/ul/li[3]"))
					.getText();
			assertEquals("Updated -", lastUpdated);

			// This method is used to logout from Gazeebo Application.
			userLogin.LogOut(driver);

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
