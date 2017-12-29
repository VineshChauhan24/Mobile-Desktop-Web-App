package comcast.test.app.testCases.videoManagement.videoPlay.HomeCategoryVideos.PopularChannelsVideos;

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
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.JsonParser;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.configServices.utils.UrlFactoryUtil;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: PlayPopularVideoDisplayedDirectlyUnderChannels Description: This
 * test case play Popular Channels which contains Episodes directly under
 * channels by logging registered user into Watchable application.
 * **/

public class PlayPopularVideoDisplayedDirectlyUnderChannels extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testPlayPopularVideoDisplayedDirectlyUnderChannels()
			throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.popularChannelsHasShowsOrEpisodes();
		List<VideoDetails> channelList = videoDetails
				.get("popularChannelHasOnlyEpisodes");

		for (VideoDetails channels : channelList) {
			System.out.println("channels:" + channels.getTitle());
		}
		String sessionToken = RestAPIServices.executeGenreAuthentication();
		Actions actions = new Actions(driver);
		int durationInSeconds = 0;
		int durationInMins = 0;

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);
			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

				// This method is to ensure Home is Active page when Login into
				// Application.
				assertionFunction.assertHomeActiveLink();

				// This method is to assert Gazeebo Top Middle Menu and to
				// ensure its collapsed.
				// assertionFunction.assertGazeeboTopMiddleMenu();

				if (channelList != null) {
					// This method asserts Popular Channels title.
					homePageCommonFun.scrollToPopularChannelsSection();

					if (channelList.size() < 2) {
						for (VideoDetails channels : channelList) {
							// This Method verifies Channel present in Popular
							// Channel Section and selects a Channel.
							homePageCommonFun.selectPopularChannel(channels
									.getTitle());

							String channelId = channels.getId();
							String videoResponse = RestAPIServices
									.getSessionTokenResponse(
											UrlFactoryUtil.getInstance()
													.getVideoDetailsURL(
															channelId, 10),
											sessionToken);
							List<VideoDetails> videoList = JsonParser
									.parseChannelShowsVideosResponse(videoResponse);

							// This Method is to navigate Last Updated detail
							// page.
							// homePageCommonFun.clickOnLastUpdatedLink();

							// Select Popular Video.
							driver.findElement(
									By.linkText(videoList.get(
											XidioConstant.selectVideo)
											.getTitle())).click();

							durationInSeconds = videoList.get(
									XidioConstant.selectVideo).getDuration();
							durationInMins = durationInSeconds / 60;

							if (durationInMins < 5) {
								for (int i = 0; i < durationInMins; i++) {
									Thread.sleep(XidioConstant.OneMinSTForVideoPlay);
									WebElement videoPage = driver
											.findElement(By
													.xpath(XpathObjectRepo.VIDEODETAILSPAGECONTENT_XPATH));
									actions.moveToElement(videoPage);
									actions.perform();

									String getVideoPlayState = driver
											.findElement(
													By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
											.getAttribute("class");
									assertEquals(
											UILablesRepo.VIDEOPAUSEBUTTON_TEXT,
											getVideoPlayState);

									// Lekshmi : Change the object identifier to
									// fetch the
									// video title.
									// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videoList.get(XidioConstant.selectVideo).getTitle()+"[\\s\\S]*$"));

									assertTrue(driver
											.findElement(
													By.xpath(XpathObjectRepo.VIDEODETAILSPAGETITLE_XPATH))
											.getText()
											.equalsIgnoreCase(
													videoList
															.get(XidioConstant.selectVideo)
															.getTitle()));

									// This method is to assert Up Next Header
									assertionFunction.assertUpNextTitle();
								}
							} else {
								for (int j = 0; j < 5; j++) {
									Thread.sleep(XidioConstant.OneMinSTForVideoPlay);
									WebElement videoPage = driver
											.findElement(By
													.xpath(XpathObjectRepo.VIDEODETAILSPAGECONTENT_XPATH));
									actions.moveToElement(videoPage);
									actions.perform();

									String getVideoPlayState = driver
											.findElement(
													By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
											.getAttribute("class");
									assertEquals(
											UILablesRepo.VIDEOPAUSEBUTTON_TEXT,
											getVideoPlayState);

									// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videoList.get(XidioConstant.selectVideo).getTitle()+"[\\s\\S]*$"));
									assertTrue(driver
											.findElement(
													By.xpath(XpathObjectRepo.VIDEODETAILSPAGETITLE_XPATH))
											.getText()
											.equalsIgnoreCase(
													videoList
															.get(XidioConstant.selectVideo)
															.getTitle()));

									// This method is to assert Up Next Header
									assertionFunction.assertUpNextTitle();
								}
							}

							driver.findElement(
									By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
									.click();
							for (int i = 0; i < 2; i++) {
								driver.navigate().back();
								Thread.sleep(sleepTime);
							}
						}
					} else {
						for (int index = 0; index < 2; index++) {
							VideoDetails channels = channelList.get(index);
							// This Method verifies Channel present in Popular
							// Channel Section and selects a Channel.
							homePageCommonFun.selectPopularChannel(channels
									.getTitle());

							String channelId = channels.getId();
							String videoResponse = RestAPIServices
									.getSessionTokenResponse(
											UrlFactoryUtil.getInstance()
													.getVideoDetailsURL(
															channelId, 10),
											sessionToken);
							List<VideoDetails> videoList = JsonParser
									.parseChannelShowsVideosResponse(videoResponse);

							// This Method is to navigate Last Updated detail
							// page.
							// homePageCommonFun.clickOnLastUpdatedLink();

							// Select Popular Video.
							driver.findElement(
									By.linkText(videoList.get(
											XidioConstant.selectVideo)
											.getTitle())).click();

							durationInSeconds = videoList.get(
									XidioConstant.selectVideo).getDuration();
							durationInMins = durationInSeconds / 60;

							if (durationInMins < 5) {
								for (int i = 0; i < durationInMins; i++) {
									Thread.sleep(XidioConstant.OneMinSTForVideoPlay);
									WebElement videoPage = driver
											.findElement(By
													.xpath(XpathObjectRepo.VIDEODETAILSPAGECONTENT_XPATH));
									actions.moveToElement(videoPage);
									actions.perform();

									String getVideoPlayState = driver
											.findElement(
													By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
											.getAttribute("class");
									assertEquals(
											UILablesRepo.VIDEOPLAYBUTTON_TEXT,
											getVideoPlayState);

									// Lekshmi : Change the object identifier to
									// fetch the
									// video title.
									// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videoList.get(XidioConstant.selectVideo).getTitle()+"[\\s\\S]*$"));

									assertTrue(driver
											.findElement(
													By.xpath(XpathObjectRepo.VIDEODETAILSPAGETITLE_XPATH))
											.getText()
											.equalsIgnoreCase(
													videoList
															.get(XidioConstant.selectVideo)
															.getTitle()));

									// This method is to assert Up Next Header
									assertionFunction.assertUpNextTitle();
								}
							} else {
								for (int j = 0; j < 5; j++) {
									Thread.sleep(XidioConstant.OneMinSTForVideoPlay);
									WebElement videoPage = driver
											.findElement(By
													.xpath(XpathObjectRepo.VIDEODETAILSPAGECONTENT_XPATH));
									actions.moveToElement(videoPage);
									actions.perform();

									String getVideoPlayState = driver
											.findElement(
													By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
											.getAttribute("class");
									assertEquals(
											UILablesRepo.VIDEOPLAYBUTTON_TEXT,
											getVideoPlayState);

									// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videoList.get(XidioConstant.selectVideo).getTitle()+"[\\s\\S]*$"));
									assertTrue(driver
											.findElement(
													By.xpath(XpathObjectRepo.VIDEODETAILSPAGETITLE_XPATH))
											.getText()
											.equalsIgnoreCase(
													videoList
															.get(XidioConstant.selectVideo)
															.getTitle()));

									// This method is to assert Up Next Header
									assertionFunction.assertUpNextTitle();
								}
							}
							// driver.findElement(By.id(orProUtil.getProperty("videoPlayStatePauseBtn_ID"))).click();
							for (int i = 0; i < 2; i++) {
								driver.navigate().back();
								Thread.sleep(sleepTime);
							}
						}
					}
				}

				// This method asserts Watchable Logo.
				assertionFunction.assertWatchableLogo();

				// This method asserts Footer Logo and It's Text.
				assertionFunction.assertFooterLogo();

				// This method is used to logout from Watchable Application.
				userLogin.LogOut(driver);
			}
			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			// assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
