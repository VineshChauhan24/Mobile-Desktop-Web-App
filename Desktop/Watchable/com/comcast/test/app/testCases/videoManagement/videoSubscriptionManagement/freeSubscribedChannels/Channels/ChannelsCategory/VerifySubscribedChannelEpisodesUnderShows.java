package comcast.test.app.testCases.videoManagement.videoSubscriptionManagement.freeSubscribedChannels.Channels.ChannelsCategory;

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
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.subscribeFreeFeaturedChannelFromHome.DS_SubscribeAFreeChannelFromHomeFeatured;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifySubscribedChannelEpisodesUnderShows Description: This test
 * case is to verify the available options of videos after sorting by 'SHOWS'
 * category by selecting a particular show on 'Shows' page for a subscribed
 * channel displayed on My Channels screen by logging into Watchable
 * application.
 */

public class VerifySubscribedChannelEpisodesUnderShows extends BaseTest {

	DS_SubscribeAFreeChannelFromHomeFeatured subscribeFreeHomeChannel = new DS_SubscribeAFreeChannelFromHomeFeatured();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	SubscriptionsPageCommonFunctions subscriptionsPageCommonFun = new SubscriptionsPageCommonFunctions();
	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	int loginError;

	@Test
	public void testVerifySubscribedChannelEpisodesUnderShows()
			throws Exception {

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */

			subscribeFreeHomeChannel
					.RegisterAndSubscribeAFreeChannelHomeFeatured();

			driver.findElement(By.xpath(XpathObjectRepo.WATCHABLE_LOGO))
					.click();
			// driver.findElement(By.linkText("Log In")).click();
			driver.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
					.click();

			// This Method is used to enter user name and password credentials
			userLogin.UserLoginCredentials(driver);

			// driver.findElement(By.id("user_login")).click();
			driver.findElement(
					By.xpath(XpathObjectRepo.MYACCOUNT_SAVE_BTN_XPATH)).click();

			loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

				Thread.sleep(sleepTime);
				Map<String, List<VideoDetails>> videoDetails = RestAPIServices
						.subscribedChannelDetails();
				List<VideoDetails> subscribedChannels = videoDetails
						.get("subscribedChannels");
				List<VideoDetails> subscribedChannelsShows = videoDetails
						.get("subscribedShowsUnderChannel");
				List<VideoDetails> subscribedChannelsVideo = videoDetails
						.get("subscribedVideo");

				// This method is to navigate My Channels Page.
				subscriptionsPageCommonFun.navigateToMyChannelsPage();

				// This method is to ensure My Channels is Active page when
				// Login
				// into Application.
				assertionFunction.assertMyChannelsActiveLink();

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
						// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+channelDetails.getTitle()+"[\\s\\S]*$"));
						assertTrue(driver
								.findElement(
										By.xpath(XpathObjectRepo.MYCHANNEL_SUBSCRIBEDCHANNELS_XPATH))
								.getText().contains(channelDetails.getTitle()));
						driver.findElement(
								By.linkText(channelDetails.getTitle())).click();

						int showloopIndexMax = 0;
						if (subscribedChannelsShows.size() < 4)
							showloopIndexMax = subscribedChannels.size();
						else
							showloopIndexMax = 4;
						for (index = 0; index < showloopIndexMax; index++) {
							VideoDetails showlist = subscribedChannelsShows
									.get(index);
							Thread.sleep(sleepTime);
							// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+showlist.getTitle()+"[\\s\\S]*$"));

							/*
							 * assertTrue(driver .findElement(
							 * By.xpath(XpathObjectRepo
							 * .SHOWDETAILSHOWLIST_XPATH))
							 * .getText().contains(showlist.getTitle()));
							 */

							int videoloopIndexMax = 0;
							if (subscribedChannelsVideo.size() < 4)
								videoloopIndexMax = subscribedChannelsVideo
										.size();
							else
								videoloopIndexMax = 4;
							for (index = 0; index < videoloopIndexMax; index++) {
								VideoDetails videolist = subscribedChannelsShows
										.get(index);
								// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videolist.getTitle()+"[\\s\\S]*$"));
								assertTrue(driver
										.findElement(
												By.xpath(XpathObjectRepo.SHOWDETAILSHOWLIST_XPATH))
										.getText()
										.contains(videolist.getTitle()));
							}
						}
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
			// }
			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
