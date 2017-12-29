package comcast.test.app.testCases.videoManagement.videoSubscriptionManagement.freeSubscribedChannels.Channels.ChannelsCategory;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.subscriptionsPage.common.SubscriptionsPageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.subscribeFreeFeaturedChannelFromHome.DS_SubscribeAFreeChannelFromHomeFeatured;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifySubscribedChannelPageDetails Description: This test case is
 * to verify the available options of channels after sorting by
 * 'CHANNELS'category for a subscribed channel displayed on My Channels screen
 * by logging into Watchable application.
 */

public class VerifySubscribedChannelPageDetails extends BaseTest {

	DS_SubscribeAFreeChannelFromHomeFeatured subscribeFreeHomeChannel = new DS_SubscribeAFreeChannelFromHomeFeatured();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	SubscriptionsPageCommonFunctions subscriptionsPageCommonFun = new SubscriptionsPageCommonFunctions();

	@Test
	public void testVerifySubscribedChannelPageDetails() throws Exception {

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
					.get("subscribedChannelsList");

			// driver.findElement(By.linkText("Log In")).click();

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

				// This method is to ensure Home is Active page when Login into
				// Application.

				if (!driver
						.findElement(
								By.xpath(XpathObjectRepo.TOP_MENU_HOME_BUTTON_XPATH))
						.getAttribute("class").contains("active")) {
					driver.findElement(
							By.xpath(XpathObjectRepo.TOP_MENU_HOME_BUTTON_XPATH))
							.click();

				}

				assertionFunction.assertHomeActiveLink();

				// This method is to navigate My Channels Page.
				subscriptionsPageCommonFun.navigateToMyChannelsPage();

				// This method is to ensure My Channels is Active page when
				// Login
				// into Application.
				assertionFunction.assertMyChannelsActiveLink();

				// This method is to navigate Channels page.
				subscriptionsPageCommonFun.clickChannelsLink();

				if (subscribedChannels != null) {
					int loopIndexMax = 0;
					if (subscribedChannels.size() < 2) {
						loopIndexMax = subscribedChannels.size();
					} else {
						loopIndexMax = 2;
					}

					for (int index = 0; index < loopIndexMax; index++) {
						VideoDetails channelDetails = subscribedChannels
								.get(index);
						/*
						 * assertTrue(driver
						 * .findElement(By.cssSelector("BODY")) .getText()
						 * .matches( "^[\\s\\S]*" + channelDetails.getTitle() +
						 * "[\\s\\S]*$"));
						 */

						assertTrue(driver
								.findElement(
										By.xpath(XpathObjectRepo.MYCHANNEL_SUBSCRIBEDCHANNELS_XPATH))
								.getText().contains(channelDetails.getTitle()));

						driver.findElement(
								By.linkText(channelDetails.getTitle())).click();

						Thread.sleep(sleepTime);
						/*
						 * assertTrue(driver
						 * .findElement(By.cssSelector("BODY")) .getText()
						 * .matches( "^[\\s\\S]*" + channelDetails.getTitle() +
						 * "[\\s\\S]*$"));
						 */

						assertTrue(driver
								.findElement(
										By.xpath(XpathObjectRepo.CHANNELDETAILCHANNELTITLE_XPATH))
								.getText().matches(channelDetails.getTitle()));

						// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+channelDetails.getPublisherName()+"[\\s\\S]*$"));

						// This method asserts Home and My Channels inactive
						// link
						// when user clicks on Bundle/Channel/Show.
						assertionFunction.assertAllInActiveLink();

						/*
						 * assertTrue(driver.findElement(By.cssSelector("BODY"))
						 * .getText().matches("^[\\s\\S]*SHOWS[\\s\\S]*$"));
						 */

						assertTrue(driver
								.findElement(
										By.xpath(XpathObjectRepo.CHANNELDETAILSHOWTITLELABEL_XPATH))
								.getText()
								.matches(
										UILablesRepo.CHHANNEL_DETAILPAGE_SHOWSLINK_XPATH));

						driver.navigate().back();
						Thread.sleep(sleepTime);
					}
				}

				// This method asserts Footer Logo and It's Text.
				assertionFunction.assertFooterLogo();

				// This method asserts Footer Copy Right Links.
				assertionFunction.assertFooterCopyRight();

				// This method unsubscribe a subscribed channels.
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
