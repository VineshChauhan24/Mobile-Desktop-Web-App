package comcast.test.app.testCases.videoManagement.videoPlay.videoStreamingTestCases;

import static org.junit.Assert.*;

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
 * Class Name: VerifyCurrentPosition Description: This test case is to verify
 * current positon in video player by logging into Watchable application.
 * **/

public class VerifyCurrentPosition extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyCurrentPosition() throws Exception {

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

					Thread.sleep(XidioConstant.halfMin);
					// flashApp.callFlashObject("pause");

					// Lekshmi : Adding the logic which incorporates the Now
					// Playing section of the application
					boolean nowPlaying = driver
							.findElement(
									By.xpath(XpathObjectRepo.VIDEONOWPLAYINGOPOUP_XPATH))
							.isDisplayed();
					System.out.println("NOW PLAYING....." + nowPlaying);

					if (nowPlaying) {
						flashApp.callFlashObject(UILablesRepo.VIDEOPLAYBUTTON_TEXT);
						Thread.sleep(XidioConstant.tenSec);
						String currentPosition = flashApp
								.callFlashObject("getCurrentPosition");

						Double tempcurrentPosition = Double
								.parseDouble(currentPosition);
						currentPosition = Integer.valueOf(
								tempcurrentPosition.intValue()).toString();
						currentPosition = TestDataGenerator
								.getFormattedTime(currentPosition);
						Thread.sleep(sleepTime);
						String pausedPositionFromUI = driver
								.findElement(
										By.xpath(XpathObjectRepo.VIDEOCURRENTPOSITIONLABEL_XPATH))
								.getText();
						pausedPositionFromUI = pausedPositionFromUI.trim();

						currentPosition = currentPosition.substring(1, 3);
						pausedPositionFromUI = pausedPositionFromUI.substring(
								1, 3);

						// This is to assert current position in back end and on
						// UI.
						// assertEquals(currentPosition, pausedPositionFromUI);

						flashApp.callFlashObject(UILablesRepo.VIDEOPAUSEBUTTON_TEXT);
						Thread.sleep(sleepTime);
					} else {
						flashApp.callFlashObject(UILablesRepo.VIDEOPAUSEBUTTON_TEXT);
						Thread.sleep(XidioConstant.tenSec);
						String currentPosition = flashApp
								.callFlashObject("getCurrentPosition");

						Double tempcurrentPosition = Double
								.parseDouble(currentPosition);
						currentPosition = Integer.valueOf(
								tempcurrentPosition.intValue()).toString();
						currentPosition = TestDataGenerator
								.getFormattedTime(currentPosition);
						Thread.sleep(sleepTime);
						String pausedPositionFromUI = driver
								.findElement(
										By.xpath(XpathObjectRepo.VIDEOCURRENTPOSITIONLABEL_XPATH))
								.getText();
						pausedPositionFromUI = pausedPositionFromUI.trim();

						// This is to assert current position in back end and on
						// UI.
						currentPosition = currentPosition.substring(1, 3);
						pausedPositionFromUI = pausedPositionFromUI.substring(
								1, 3);

						assertEquals(currentPosition, pausedPositionFromUI);

						flashApp.callFlashObject(UILablesRepo.VIDEOPLAYBUTTON_TEXT);
						Thread.sleep(sleepTime);
					}

				}

				// This method is used to logout from Watchable Application.
				userLogin.LogOut(driver);
			}
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
