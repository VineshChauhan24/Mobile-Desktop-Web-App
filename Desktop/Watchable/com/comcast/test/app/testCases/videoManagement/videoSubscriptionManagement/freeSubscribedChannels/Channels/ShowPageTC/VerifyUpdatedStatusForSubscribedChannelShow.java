package comcast.test.app.testCases.videoManagement.videoSubscriptionManagement.freeSubscribedChannels.Channels.ShowPageTC;

import static org.junit.Assert.assertEquals;
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
 * Class Name: VerifyUpdatedStatusForSubscribedChannelShow Description: This
 * test case validates whether 'Updated' status is displayed in shows page for
 * the show sorted by 'UNWATCHED' category present for a subscribed channel in
 * 'My Channels' page by logging registered user into Watchable application.
 * **/

public class VerifyUpdatedStatusForSubscribedChannelShow extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	DS_SubscribeAFreeChannelFromHomeFeatured subscribeFreeHomeChannel = new DS_SubscribeAFreeChannelFromHomeFeatured();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	SubscriptionsPageCommonFunctions subscriptionsPageCommonFun = new SubscriptionsPageCommonFunctions();

	@Test
	public void testVerifyUpdatedStatusForSubscribedChannelShow()
			throws Exception {

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			subscribeFreeHomeChannel
					.RegisterAndSubscribeAFreeChannelHomeFeatured();

			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

				Map<String, List<VideoDetails>> videoDetails = RestAPIServices
						.subscribedChannelDetails();
				List<VideoDetails> subscribedChannels = videoDetails
						.get("subscribedChannels");
				List<VideoDetails> subscribedChannelsShows = videoDetails
						.get("subscribedShowsUnderChannel");

				// driver.findElement(By.linkText("Log In")).click();

				// This method is to ensure Home is Active page when Login into
				// Application.
				Thread.sleep(sleepTime);

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

				if (subscribedChannels != null) {
					int loopIndexMax = 0;
					if (subscribedChannels.size() < 4) {
						loopIndexMax = subscribedChannels.size();
					} else {
						loopIndexMax = 4;
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

						Thread.sleep(sleepTime);
						// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+channelDetails.getTitle()+"[\\s\\S]*$"));
						assertTrue(driver
								.findElement(
										By.xpath(XpathObjectRepo.CHANNELDETAILCHANNELTITLE_XPATH))
								.getText().matches(channelDetails.getTitle()));

						if (subscribedChannelsShows != null) {
							int showloopIndexMax = 0;
							if (subscribedChannelsShows.size() < 4)
								showloopIndexMax = subscribedChannelsShows
										.size();
							else
								showloopIndexMax = 4;
							for (int shows = 0; shows < showloopIndexMax; shows++) {
								VideoDetails showList = subscribedChannelsShows
										.get(showloopIndexMax - 1);
								driver.findElement(
										By.linkText(showList.getTitle()))
										.click();

								Thread.sleep(sleepTime);

								// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+showList.getTitle()+"[\\s\\S]*$"));
								assertTrue(driver
										.findElement(
												By.xpath(XpathObjectRepo.SHOWDETAILSHOWTITLE_XPATH))
										.getText()
										.contains(showList.getTitle()));

								// String
								// publishedDate=driver.findElement(By.xpath(".//*[@id='content-wrap']/article/div/div[2]/div/div[1]/ul/li[2]")).getText();
								String publishedDate = driver
										.findElement(
												By.xpath(XpathObjectRepo.SHOWDETAILVIDEOSUPDATED_XPATH))
										.getText();
								assertEquals(showList.getLastPublished(),
										publishedDate);

								driver.navigate().back();
								Thread.sleep(sleepTime);
							}
						}
					}
				}

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
