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
import comcast.test.config.dataServices.subscribeFreeFeaturedChannelFromHome.DS_SubscribeAFreeChannelFromHomeFeatured;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: PlaySubscribedChannelVideoFromShowPage Description: This test
 * case is to play the video by clicking a channel followed by a show for a
 * subscribed channel displayed on 'My Channels' screen by logging into
 * Watchable application with registered user.
 */

public class PlaySubscribedChannelVideoFromShowPage extends BaseTest {

	DS_SubscribeAFreeChannelFromHomeFeatured subscribeFreeHomeChannel = new DS_SubscribeAFreeChannelFromHomeFeatured();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	SubscriptionsPageCommonFunctions subscriptionsPageCommonFun = new SubscriptionsPageCommonFunctions();
	int loginError;

	@Test
	public void testPlaySubscribedChannelVideoFromShowPage() throws Exception {

		try {
			/*
			 * This Method is to login to Watchable application and subscribe to
			 * a channel
			 */

			subscribeFreeHomeChannel
					.RegisterAndSubscribeAFreeChannelHomeFeatured();

			if (!driver
					.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
					.getAttribute("class").contains("active")) {
				driver.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
						.click();

			}
			Thread.sleep(sleepTime);
			userLogin.UserLoginCredentials(driver);

			driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
					.click();
			Thread.sleep(sleepTime);

			loginError = driver
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
				List<VideoDetails> subscribedChannelsVideo = videoDetails
						.get("subscribedVideo");

				Actions actions = new Actions(driver);
				int durationInSeconds = 0;
				int durationInMins = 0;

				// driver.get(DataServiceProperties.APPURL);

				// This method is used to enter user name and password
				// credential

				// This method is used to enter user name and password
				// credentials

				// Adding this action to pass below assertion

				if (!driver
						.findElement(
								By.xpath(XpathObjectRepo.TOP_MENU_HOME_BUTTON_XPATH))
						.getAttribute("class").contains("active")) {
					driver.findElement(
							By.xpath(XpathObjectRepo.TOP_MENU_HOME_BUTTON_XPATH))
							.click();

				}

				// This method is to ensure Home is Active page when Login into
				// Application.
				assertionFunction.assertHomeActiveLink();

				// This method is to navigate My Channels Page.
				subscriptionsPageCommonFun.navigateToMyChannelsPage();

				// This method is to ensure My Channels is Active page when
				// Login
				// into Application.
				assertionFunction.assertMyChannelsActiveLink();

				// Click on Subscribed Channel.
				Thread.sleep(sleepTime);
				driver.findElement(
						By.linkText(subscribedChannels.get(
								XidioConstant.selectSubscribedChannel)
								.getTitle())).click();

				// Click on Channels > Show.
				Thread.sleep(sleepTime);
				driver.findElement(
						By.linkText(subscribedChannelsShows.get(
								XidioConstant.selectSubscribedShow).getTitle()))
						.click();

				// Checking episode present in selected show

				/*
				 * int episodePresent = driver.findElements(
				 * By.linkText(subscribedChannelsVideo.get(
				 * XidioConstant.selectVideo).getTitle())).size();
				 */
				int episodePresent = driver.findElements(
						By.xpath(XpathObjectRepo.MYCHANNELS_CHANNELS_XPATH))
						.size();

				if (episodePresent > 0) {

					// Click on Subscribed Channel > Shows > Episode.
					Thread.sleep(sleepTime);
					driver.findElement(
							By.linkText(subscribedChannelsVideo.get(
									XidioConstant.selectVideo).getTitle()))
							.click();

					Thread.sleep(XidioConstant.OneMinSTForVideoPlay);
					// Manoj: Checking Selected video is partially played
					Thread.sleep(sleepTime);
					WebElement playFromPresent = driver.findElement(By
							.xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH));

					if (playFromPresent.isDisplayed() == true) {
						Thread.sleep(sleepTime);
						Thread.sleep(sleepTime);
						driver.findElement(
								By.xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH))
								.click();
						Thread.sleep(XidioConstant.OneMinSTForVideoPlay);
					}

					Thread.sleep(sleepTime);
					Thread.sleep(sleepTime);
					// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches(subscribedChannelsVideo.get(XidioConstant.selectVideo).getTitle()));
					assertTrue(driver
							.findElement(
									By.xpath(XpathObjectRepo.VIDEODECURRENTVIDEOTITLE_XPATH))
							.getText()
							.contains(
									subscribedChannelsVideo.get(
											XidioConstant.selectVideo)
											.getTitle()));

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
						WebElement videoPage = driver.findElement(By
								.xpath(XpathObjectRepo.VIDEODETAILSPAGE_XPATH));
						actions.moveToElement(videoPage);
						actions.perform();

						/*
						 * String getVideoPlayState = driver.findElement(
						 * By.xpath
						 * (XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
						 * .getText();
						 */

						boolean nowPlaying = driver
								.findElement(
										By.xpath(XpathObjectRepo.VIDEONOWPLAYINGOPOUP_XPATH))
								.isDisplayed();
						System.out.println("NOW PLAYING....." + nowPlaying);
						if (nowPlaying) {
							String getVideoPlayState = driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
									.getAttribute("class");

							assertEquals(UILablesRepo.VIDEOPLAYBUTTON_TEXT,
									getVideoPlayState);

							assertTrue(driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEODECURRENTVIDEOTITLE_XPATH))
									.getText()
									.contains(
											subscribedChannelsVideo.get(
													XidioConstant.selectVideo)
													.getTitle()));
							break;
						} else {
							String getVideoPlayState = driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
									.getAttribute("class");

							assertEquals(UILablesRepo.VIDEOPAUSEBUTTON_TEXT,
									getVideoPlayState);

							assertTrue(driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEODECURRENTVIDEOTITLE_XPATH))
									.getText()
									.contains(
											subscribedChannelsVideo.get(
													XidioConstant.selectVideo)
													.getTitle()));
						}

						// This method asserts Home and My Channels inactive
						// link
						// when
						// user clicks on Bundle/Channel/Show.
						assertionFunction.assertAllInActiveLink();

						// This method is to assert Up Next Header
						assertionFunction.assertUpNextTitle();
					}

					// This method asserts Footer Logo and It's Text.
					assertionFunction.assertFooterLogo();

					// This method asserts Footer Copy Right Links.
					assertionFunction.assertFooterCopyRight();
				}

				else {
					log.error("Episode is not present under selected show");
				}

				// This method is used to logout from Watchable Application.
				userLogin.LogOut(driver);
			}

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			// assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			if (loginError == 1) {
				captureScreenshot();
				collector.addError(t);
			}
		}
	}
}
