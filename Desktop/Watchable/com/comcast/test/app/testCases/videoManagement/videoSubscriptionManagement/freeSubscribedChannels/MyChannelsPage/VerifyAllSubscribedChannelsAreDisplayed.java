package comcast.test.app.testCases.videoManagement.videoSubscriptionManagement.freeSubscribedChannels.MyChannelsPage;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.subscriptionsPage.common.SubscriptionsPageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.subscribeFreeFeaturedChannelFromHome.DS_SubscribeAFreeChannelFromHomeFeatured;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyAllSubscribedChannelsAreDisplayed Description: This test
 * case is to verify all Subscribed channels are displayed and its clickable
 * comparing with API response for registered Watchable Application user.
 * **/

public class VerifyAllSubscribedChannelsAreDisplayed extends BaseTest {

	DS_SubscribeAFreeChannelFromHomeFeatured subscribeFreeHomeChannel = new DS_SubscribeAFreeChannelFromHomeFeatured();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	SubscriptionsPageCommonFunctions subscriptionsPageCommonFun = new SubscriptionsPageCommonFunctions();

	@Test
	public void testVerifyAllSubscribedChannelsAreDisplayed() throws Exception {

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			subscribeFreeHomeChannel
					.RegisterAndSubscribeAFreeChannelHomeFeatured();

			Map<String, List<VideoDetails>> videoDetails = RestAPIServices
					.SubscriptionsAPI();
			List<VideoDetails> subscribedChannels = videoDetails
					.get("allSubscribedChannelsList");
			Thread.sleep(sleepTime);

			if (!driver
					.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
					.getAttribute("class").contains("active")) {
				driver.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
						.click();

			}

			// This Method is used to enter user name and password credentials
			userLogin.UserLoginCredentials(driver);

			driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
					.click();
			Thread.sleep(sleepTime);
			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

				// This method is to navigate My Channels Page.
				subscriptionsPageCommonFun.navigateToMyChannelsPage();

				// This method is to ensure My Channels is Active page when
				// Login
				// into Application.
				Thread.sleep(sleepTime);
				assertionFunction.assertMyChannelsActiveLink();

				if (subscribedChannels != null) {
					int loopIndexMax = 0;
					if (subscribedChannels.size() < 4)
						loopIndexMax = subscribedChannels.size();
					else
						loopIndexMax = 4;

					for (int index = 0; index < loopIndexMax; index++) {
						VideoDetails channelDetails = subscribedChannels
								.get(index);
						Thread.sleep(sleepTime);

						driver.findElement(
								By.linkText(channelDetails.getTitle())).click();

						Thread.sleep(sleepTime);
						assertTrue(driver
								.findElement(
										By.xpath(XpathObjectRepo.CHANNELDETAILCHANNELTITLE_XPATH))
								.getText()
								.equalsIgnoreCase(channelDetails.getTitle()));

						driver.navigate().back();
						Thread.sleep(sleepTime);
					}
				}

				// This method asserts Footer Logo and It's Text.
				assertionFunction.assertFooterLogo();

				// This method asserts Footer Links.
				assertionFunction.assertFooterCopyRight();

				// This method un-subscribe a subscribed channel.
				subscriptionsPageCommonFun.unSubscribeASubscribedChannels();

				// This method is used to logout from Watchable Application.
				userLogin.LogOut(driver);
			}
			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
