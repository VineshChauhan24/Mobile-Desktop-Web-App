package comcast.test.app.testCases.videoManagement.videoPlay.videoStreamingTestCases.SeekFunctionalityTestCases;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.constant.XidioConstant;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.FlashObjectWebDriver;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.configServices.utils.TestDataGenerator;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifySeekToBackPositionChangeFunctionality Description: This
 * test case is to verify seek to back position functionality in video player by
 * logging into Watchable application.
 * **/

public class VerifySeekToBackPositionChangeFunctionality extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifySeekToBackPositionChangeFunctionality()
			throws Exception {

		FlashObjectWebDriver flashApp = new FlashObjectWebDriver(driver,
				"PlayerPlatformAPI");

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.featuredSectionVideos();
		List<VideoDetails> videoList = videoDetails.get("featuredVideoList");

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

				if (videoList != null) {
					// This Method verifies Channel present in Featured list and
					// selects a Channel.
					homePageCommonFun.selectFeaturedChannel(videoList.get(
							XidioConstant.selectVideo).getTitle());
					// homePageCommonFun.selectPopularChannel(videoList.get(XidioConstant.selectVideo).getTitle());

					Thread.sleep(XidioConstant.OneMinSTForVideoPlay);

					String getVideoPlayState = driver
							.findElement(
									By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
							.getAttribute("class");
					if (getVideoPlayState.equals("pause")) {
						flashApp.callFlashObject("setPosition",
								XidioConstant.setPrevPosition);
						flashApp.callFlashObject("pause");
						Thread.sleep(XidioConstant.halfMin);
						String currentPosition = flashApp
								.callFlashObject("getCurrentPosition");

						assertEquals(XidioConstant.setPrevPosition,
								currentPosition);

						Double tempcurrentPosition = Double
								.parseDouble(currentPosition);
						currentPosition = Integer.valueOf(
								tempcurrentPosition.intValue()).toString();
						currentPosition = TestDataGenerator
								.getFormattedTime(currentPosition);

						String pausedPositionFromUI = driver
								.findElement(
										By.xpath(XpathObjectRepo.VIDEOCURRENTPOSITIONLABEL_XPATH))
								.getText();
						pausedPositionFromUI = pausedPositionFromUI.trim();
						assertEquals(currentPosition, pausedPositionFromUI);
						flashApp.callFlashObject("play");
					} else {
						// assertEquals(UILablesRepo.VIDEOPAUSEBUTTON_TEXT,getVideoPlayState);
						boolean nowPlaying = driver
								.findElement(
										By.xpath(XpathObjectRepo.VIDEONOWPLAYINGOPOUP_XPATH))
								.isDisplayed();
						System.out.println("NOW PLAYING....." + nowPlaying);
						if (nowPlaying) {
							String getVideoPlayState1 = driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEOSTARTPOINTBUTTON_XPATH))
									.getText();
							assertEquals(
									UILablesRepo.VIDEOPLAYFROMSTARTBUTTON_TEXT,
									getVideoPlayState1);
							// This method is to assert Up Next Header
							assertionFunction.assertUpNextTitle();
							// break;
							nowPlaying = false;
						} else {
							String getVideoPlayState2 = driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
									.getAttribute("class");
							assertEquals(UILablesRepo.VIDEOPAUSEBUTTON_TEXT,
									getVideoPlayState2);
						}
					}
				}

				Thread.sleep(sleepTime);
				// This method is used to logout from Watchable Application.
				userLogin.LogOut(driver);
			}
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
