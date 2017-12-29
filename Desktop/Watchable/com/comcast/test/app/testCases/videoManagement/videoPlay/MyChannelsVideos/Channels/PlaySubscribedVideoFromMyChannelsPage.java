package comcast.test.app.testCases.videoManagement.videoPlay.MyChannelsVideos.Channels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.constant.XidioConstant;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.subscriptionsPage.common.SubscriptionsPageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.subscribeFreeFeaturedChannelFromHome.DS_SubscribeAFreeChannelFromHomeFeatured;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: PlaySubscribedVideoFromMyChannelsPage Description: This test case
 * is to play the video by clicking a channel followed by 'LAST UPDATED' link
 * for a subscribed channel displayed in 'My Channels' screen by logging into
 * Watchable application with registered user.
 */

public class PlaySubscribedVideoFromMyChannelsPage extends BaseTest {

	DS_SubscribeAFreeChannelFromHomeFeatured subscribeFreeHomeChannel = new DS_SubscribeAFreeChannelFromHomeFeatured();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	SubscriptionsPageCommonFunctions subscriptionsPageCommonFun = new SubscriptionsPageCommonFunctions();
	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	int loginError;

	@Test
	public void testPlaySubscribedVideoFromMyChannelsPage() throws Exception {

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			// subscribeFreeHomeChannel.RegisterAndSubscribeAFreeChannelHomeFeatured();

			// Login to application
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);
			loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

				// This method is to ensure Home is Active page when Login into
				// Application.
				assertionFunction.assertHomeActiveLink();

				// This method is to navigate My Channels Page.
				subscriptionsPageCommonFun.navigateToMyChannelsPage();
				Thread.sleep(sleepTime);

				// Verifying videos present under 'MY CHANNEL'

				int myChannelCount = driver.findElements(
						By.xpath(XpathObjectRepo.MYCHANNELS_CHANNELS_XPATH))
						.size();
				if (myChannelCount > 0) {

					Map<String, List<VideoDetails>> videoDetails = RestAPIServices
							.subscribedChannelDetails();
					List<VideoDetails> subscribedChannels = videoDetails
							.get("subscribedChannels");
					List<VideoDetails> subscribedChannelsVideo = videoDetails
							.get("subscribedVideo");

					Actions actions = new Actions(driver);
					int durationInSeconds = 0;
					int durationInMins = 0;

					Thread.sleep(sleepTime);
					// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+subscribedChannels.get(XidioConstant.selectSubscribedChannel).getTitle()+"[\\s\\S]*$"));
					assertTrue(driver
							.findElement(
									By.xpath(XpathObjectRepo.MYCHANNELFIRSTCHANNELTITLE_XPATH))
							.getText()
							.equalsIgnoreCase(
									subscribedChannels
											.get(XidioConstant.selectSubscribedChannel)
											.getTitle()));

					// Click on My Channels Link > Episode
					Thread.sleep(sleepTime);
					/*
					 * driver.findElement(
					 * By.linkText(subscribedChannelsVideo.get(
					 * XidioConstant.selectVideo).getTitle())) .click();
					 */

					driver.findElement(
							By.xpath(XpathObjectRepo.MYCHANNELS_FIRSTEPISODE_TITLE_XPATH))
							.click();

					Thread.sleep(XidioConstant.OneMinSTForVideoPlay);

					WebElement playFromPresent = driver.findElement(By
							.xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH));

					if (playFromPresent.isDisplayed() == true) {
						driver.findElement(
								By.xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH))
								.click();
					}

					Thread.sleep(XidioConstant.OneMinSTForVideoPlay);
					// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+subscribedChannelsVideo.get(XidioConstant.selectVideo).getTitle()+"[\\s\\S]*$"));

					/*
					 * assertTrue(driver .findElement(
					 * By.xpath(XpathObjectRepo.VIDEODECURRENTVIDEOTITLE_XPATH))
					 * .getText() .equalsIgnoreCase(
					 * subscribedChannelsVideo.get( XidioConstant.selectVideo)
					 * .getTitle()));
					 */

					durationInSeconds = subscribedChannelsVideo.get(
							XidioConstant.selectVideo).getDuration();
					durationInMins = durationInSeconds / 60;

					int duration = 0;
					if (durationInMins < 5)
						duration = durationInMins;
					else
						duration = 5;

					for (int i = 0; i < duration; i++) {
						Thread.sleep(XidioConstant.OneMinSTForVideoPlay);

						// Manoj: Object changed during Re-Working
						// WebElement videoPage =
						// driver.findElement(By.xpath(".//*[@id='content-video']"));
						WebElement videoPage = driver.findElement(By
								.xpath(XpathObjectRepo.VIDEODETAILSPAGE_XPATH));
						actions.moveToElement(videoPage);
						actions.perform();

						if (driver
								.findElement(
										By.xpath(XpathObjectRepo.VIDEOSTARTPOINTBUTTON_XPATH))
								.isDisplayed()) {
							String getVideoPlayState = driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEOSTARTPOINTBUTTON_XPATH))
									.getText();
							assertEquals(
									UILablesRepo.VIDEOPLAYFROMSTARTBUTTON_TEXT,
									getVideoPlayState);
						} else {
							String getVideoPlayState = driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
									.getAttribute("class");
							assertEquals(UILablesRepo.VIDEOPAUSEBUTTON_TEXT,
									getVideoPlayState);
						}

						// This method asserts Home and My Channels inactive
						// link
						// when user clicks on Bundle/Channel/Show.
						assertionFunction.assertAllInActiveLink();

						// This method is to assert Up Next Header
						assertionFunction.assertUpNextTitle();
					}

					// This method asserts Footer Logo and It's Text.
					assertionFunction.assertFooterLogo();

					// This method asserts Footer Copy Right Links.
					assertionFunction.assertFooterCopyRight();

					Thread.sleep(sleepTimeForVideoPlay);

				}

				else {
					// This method is used to logout from Watchable Application.
					userLogin.LogOut(driver);
				}
				// This method is to ensure Login page is displayed when user
				// Sign Out from Application.
				assertionFunction.assertLoginPageDetails();
			}
		} catch (Throwable t) {

			captureScreenshot();
			collector.addError(t);

		}
	}
}
