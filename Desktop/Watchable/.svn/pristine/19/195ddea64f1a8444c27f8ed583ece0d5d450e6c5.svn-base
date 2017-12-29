package comcast.test.app.testCases.videoManagement.videoHomeManagement.WatchingCategoryTestCases.HomePageTCs;

import static org.junit.Assert.assertFalse;
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
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.subscribeFreeFeaturedChannelFromHome.DS_SubscribeAFreeChannelFromHomeFeatured;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyUnSubscribedChannelVideosUnderWatching Description: This
 * test case validates whether the Watching section is displayed or not for
 * newly registered user logging registered user into Watchable application.
 * **/

public class VerifyUnSubscribedChannelVideosUnderWatching extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	DS_SubscribeAFreeChannelFromHomeFeatured subscribeFreeHomeChannel = new DS_SubscribeAFreeChannelFromHomeFeatured();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	SubscriptionsPageCommonFunctions subscriptionsPageCommonFun = new SubscriptionsPageCommonFunctions();

	@Test
	public void testVerifySubscribedChannelVideosUnderWatching()
			throws Exception {

		subscribeFreeHomeChannel.RegisterAndSubscribeAFreeChannelHomeFeatured();

		Thread.sleep(sleepTime);
		List<VideoDetails> UpNextvideoDetails = RestAPIServices.upNextAPI();

		try {
			// This method is used to enter user name and password credential
			Thread.sleep(sleepTime);

			// Click on Login link
			// driver.findElement(By.linkText("Log In")).click();
			if (!driver
					.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
					.getAttribute("class").contains("active")) {
				driver.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
						.click();

			}

			userLogin.UserLoginCredentials(driver);

			// driver.findElement(By.id("user_login")).click();
			driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
					.click();

			Thread.sleep(sleepTime);
			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

				// Addition action to pas below assertion

				if (!driver
						.findElement(
								By.xpath(XpathObjectRepo.TOP_MENU_HOME_BUTTON_XPATH))
						.getAttribute("class").contains("active")) {
					driver.findElement(
							By.xpath(XpathObjectRepo.TOP_MENU_HOME_BUTTON_XPATH))
							.click();

				}
				Thread.sleep(sleepTime);

				// This method is to ensure Home is Active page when Login into
				// Application.
				assertionFunction.assertHomeActiveLink();

				int loopIndex = 0;
				boolean isVideoPresent = false;
				if (UpNextvideoDetails != null) {
					if (UpNextvideoDetails.size() < 10)
						loopIndex = UpNextvideoDetails.size();
					else
						loopIndex = 10;
					boolean isPresent;
					for (int index = 0; index < loopIndex - 2; index++) {
						Thread.sleep(sleepTime);
						VideoDetails upNextVideo = UpNextvideoDetails
								.get(index);

						// isPresent=driver.findElements(By.xpath(".//*[@id='upNext']/div/section").linkText(upNextVideo.getTitle())).size()>0;
						isPresent = driver
								.findElements(
										By.xpath(
												XpathObjectRepo.YOUAREWATCHINGROW_XPATH)
												.linkText(
														upNextVideo.getTitle()))
								.size() > 0;
						if (isPresent == true) {
							isVideoPresent = true;

							// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+upNextVideo.getTitle()+"[\\s\\S]*$"));
							// assertTrue(driver.findElement(By.xpath(XpathObjectRepo.YOUAREWATCHINGROW_XPATH)).getText().contains(upNextVideo.getTitle()));
						} else {
							// boolean
							// nextLink=driver.findElements(By.xpath(".//*[@id='next_up_next']/span")).size()>0;
							boolean nextLink = driver
									.findElements(
											By.xpath(XpathObjectRepo.YOUAREWATCHINGROW_XPATH))
									.size() > 0;
							if (nextLink == true)
								// driver.findElement(By.xpath(".//*[@id='next_up_next']/span")).click();
								driver.findElement(
										By.xpath(XpathObjectRepo.YOUAREWATCHINGROW_XPATH))
										.click();
							Thread.sleep(sleepTime);
						}
					}
				}

				if (!isVideoPresent) {
					// assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Watching[\\s\\S]*$"));
					assertFalse(driver
							.findElement(
									By.xpath(XpathObjectRepo.YOUAREWATCHINGHEADERLABEL_XPATH))
							.getText()
							.contains(UILablesRepo.HOMEPAGE_YOUAREWATCHING));
				}

				// This list contains all the subscribed channels.
				Thread.sleep(sleepTime);
				Map<String, List<VideoDetails>> videoDetails = RestAPIServices
						.SubscriptionsAPI();
				List<VideoDetails> subscribedChannels = videoDetails
						.get("subscribedChannelsList");

				if (subscribedChannels != null) {
					int loopIndexMax = 0;
					if (subscribedChannels.size() < 2) {
						loopIndexMax = subscribedChannels.size();
					} else {
						loopIndexMax = 2;
					}

					for (int index = 0; index < loopIndexMax; index++) {
						VideoDetails subscribedChannelDetails = subscribedChannels
								.get(index);

						// This method is to navigate My Channels Page.
						subscriptionsPageCommonFun.navigateToMyChannelsPage();

						Thread.sleep(sleepTime);
						// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+subscribedChannelDetails.getTitle()+"[\\s\\S]*$"));
						assertTrue(driver
								.findElement(
										By.xpath(XpathObjectRepo.MYCHANNEL_SUBSCRIBEDCHANNELS_XPATH))
								.getText()
								.contains(subscribedChannelDetails.getTitle()));

						// Unsubscribe a channel - Below code is commented
						// because
						// of for Unsubscribe action
						// It is searching channels in Featured section and now
						// featured section contains no channels
						// subscriptionsPageCommonFun.unSubscribeAChannel(subscribedChannelDetails.getTitle());

						// Navigate to Home page
						// driver.findElement(By.linkText("Home")).click();
						driver.findElement(
								By.xpath(XpathObjectRepo.TOP_MENU_HOME_BUTTON_XPATH))
								.click();
					}
				}

				Thread.sleep(sleepTime);
				List<VideoDetails> updatedUpNextvideoDetails = RestAPIServices
						.upNextAPI();

				if (updatedUpNextvideoDetails != null) {
					if (updatedUpNextvideoDetails.size() < 10)
						loopIndex = updatedUpNextvideoDetails.size();
					else
						loopIndex = 10;
					boolean isPresent;
					for (int index = 0; index < loopIndex; index++) {
						Thread.sleep(sleepTime);
						VideoDetails upNextVideo = updatedUpNextvideoDetails
								.get(index);

						// isPresent=driver.findElements(By.xpath(".//*[@id='upNext']/div/section").linkText(upNextVideo.getTitle())).size()>0;
						isPresent = driver
								.findElements(
										By.xpath(
												XpathObjectRepo.YOUAREWATCHINGROW_XPATH)
												.linkText(
														upNextVideo.getTitle()))
								.size() > 0;
						if (isPresent == true) {
							isVideoPresent = true;
							// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+upNextVideo.getTitle()+"[\\s\\S]*$"));
							assertTrue(driver
									.findElement(
											By.xpath(XpathObjectRepo.YOUAREWATCHINGROW_XPATH))
									.getText().contains(upNextVideo.getTitle()));
						} else {
							// boolean
							// nextLink=driver.findElements(By.xpath(".//*[@id='next_up_next']/span")).size()>0;
							boolean nextLink = driver
									.findElements(
											By.xpath(XpathObjectRepo.YOUAREWATCHINGROW_XPATH))
									.size() > 0;
							if (nextLink == true)
								// driver.findElement(By.xpath(".//*[@id='next_up_next']/span")).click();
								driver.findElement(
										By.xpath(XpathObjectRepo.YOUAREWATCHINGROW_XPATH))
										.click();
							Thread.sleep(sleepTime);
						}
					}

					// This method is to ensure Watching title not exeists in
					// home
					// page.
					assertionFunction.assertNotWatchingTitle();
				}
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
