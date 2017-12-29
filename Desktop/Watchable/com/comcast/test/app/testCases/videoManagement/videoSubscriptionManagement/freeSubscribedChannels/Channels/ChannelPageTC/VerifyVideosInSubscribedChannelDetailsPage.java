package comcast.test.app.testCases.videoManagement.videoSubscriptionManagement.freeSubscribedChannels.Channels.ChannelPageTC;

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
import comcast.test.config.configServices.utils.JsonParser;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.configServices.utils.UrlFactoryUtil;
import comcast.test.config.dataServices.subscribeFreeFeaturedChannelFromHome.DS_SubscribeAFreeChannelFromHomeFeatured;
import comcast.test.config.dataServices.subscribeFreePopularChannelFromHome.DS_SubscribeAFreeChannelFromHomePopularChannels;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyVideosInSubscribedChannelDetailsPage Description: This test
 * case is to verify if a set of videos is displayed a subscribed channel by
 * logging into Watchable application.
 */

public class VerifyVideosInSubscribedChannelDetailsPage extends BaseTest {

	// DS_SubscribeAFreeChannelFromHomeFeatured subscribeFreeHomeChannel=new
	// DS_SubscribeAFreeChannelFromHomeFeatured();
	DS_SubscribeAFreeChannelFromHomePopularChannels subscribeFreeHomePopularChannel = new DS_SubscribeAFreeChannelFromHomePopularChannels();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	SubscriptionsPageCommonFunctions subscriptionsPageCommonFun = new SubscriptionsPageCommonFunctions();

	@Test
	public void testVerifyVideosInSubscribedChannelDetailsPage()
			throws Exception {

		try {
			/*
			 * This Method is to register new user using Gazeebo application and
			 * to change a password.
			 */
			// subscribeFreeHomeChannel.RegisterAndSubscribeAFreeChannelHomeFeatured();
			subscribeFreeHomePopularChannel
					.RegisterAndSubscribeAFreeChannelFromHomePopularChannels();

			Map<String, List<VideoDetails>> videoDetails = RestAPIServices
					.SubscriptionsAPI();
			List<VideoDetails> subscribedChannels = videoDetails
					.get("subscribedChannelsList");
			String sessionToken = RestAPIServices.executeGenreAuthentication();

			// driver.findElement(By.linkText("LOG IN")).click();

			if (!driver
					.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
					.getAttribute("class").contains("active")) {
				driver.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
						.click();

			}

			// This Method is used to enter user name and password credentials
			userLogin.UserLoginCredentials(driver);

			// driver.findElement(By.id("user_login")).click();
			driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
					.click();
			Thread.sleep(sleepTime);
			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			Thread.sleep(sleepTime);

			if (loginError == 0) {

				// Additional action for below assertion to pass

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

				if (subscribedChannels != null) {
					int loopIndexMax = 0;
					if (subscribedChannels.size() < 4) {
						loopIndexMax = subscribedChannels.size();
					} else {
						loopIndexMax = 4;
					}
					for (int index = 0; index < loopIndexMax; index++) {
						VideoDetails subscribedChannelDetails = subscribedChannels
								.get(index);
						// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+subscribedChannelDetails.getTitle()+"[\\s\\S]*$"));
						assertTrue(driver
								.findElement(
										By.xpath(XpathObjectRepo.MYCHANNEL_SUBSCRIBEDCHANNELS_XPATH))
								.getText()
								.contains(subscribedChannelDetails.getTitle()));

						driver.findElement(
								By.linkText(subscribedChannelDetails.getTitle()))
								.click();

						// Verify Shows and click on it.
						String ChannelsShowsResponse = RestAPIServices
								.getSessionTokenResponse(
										UrlFactoryUtil.getInstance()
												.getSubShowURL(
														subscribedChannelDetails
																.getId()),
										sessionToken);
						List<VideoDetails> featuredShowsList = JsonParser
								.parseShowsResponse(ChannelsShowsResponse);

						if (featuredShowsList != null) {
							int showloopIndexMax = 0;
							if (featuredShowsList.size() < 2) {
								showloopIndexMax = featuredShowsList.size();
							} else {
								showloopIndexMax = 2;
							}
							for (int show = 0; show < showloopIndexMax; show++) {
								VideoDetails showList = featuredShowsList
										.get(show);
								int ele = driver.findElements(
										By.linkText(showList.getTitle()))
										.size();
								if (ele == 1) {
									driver.findElement(
											By.linkText(showList.getTitle()))
											.click();
									Thread.sleep(sleepTime);
									// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+showList.getTitle()+"[\\s\\S]*$"));
									assertTrue(driver
											.findElement(
													By.xpath(XpathObjectRepo.SHOWDETAILSHOWTITLE_XPATH))
											.getText()
											.matches(showList.getTitle()));

									// Get Video Details and verify all
									// response.
									String videoResponse = RestAPIServices
											.getSessionTokenResponse(
													UrlFactoryUtil
															.getInstance()
															.getVideoDetailsURL(
																	showList.getId(),
																	10),
													sessionToken);
									List<VideoDetails> channelShowsVideoList = JsonParser
											.parseChannelShowsVideosResponse(videoResponse);

									if (channelShowsVideoList != null) {
										int loopIndex = 0;
										if (channelShowsVideoList.size() < 5)
											loopIndex = channelShowsVideoList
													.size();
										else
											loopIndex = 5;
										for (int i = 0; i < loopIndex; i++) {
											VideoDetails videoList = channelShowsVideoList
													.get(i);

											// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videoList.getTitle()+"[\\s\\S]*$"));
											int showPresent = driver
													.findElement(
															By.xpath(XpathObjectRepo.SHOWDETAILVIDEOLIST_XPATH))
													.getText().trim().length();

											if (showPresent > 0) {
												assertTrue(
														"Show not present",
														driver.findElement(
																By.xpath(XpathObjectRepo.SHOWDETAILVIDEOLIST_XPATH))
																.getText()
																.contains(
																		videoList
																				.getTitle()));
											} else {
												log.error("Show not present");
											}
										}
									}
									driver.navigate().back();
									Thread.sleep(sleepTime);
								}
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
