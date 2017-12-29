package comcast.test.app.testCases.videoManagement.videoPlay.videoStreamingTestCases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
 * Class Name: VerifyResumeAndStartAlertBoxWithLogin Description: This test case
 * is to verify Resume and Start Alert BOx in video player by logging into
 * Watchable application.
 * **/

public class VerifyResumeAndStartAlertBoxWithLogin extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testVerifyResumeAndStartAlertBoxWithLogin() throws Exception {

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
					for (int index = 0; index < 1; index++) {
						VideoDetails videos = videoList.get(index);
						// This Method verifies Channel present in Featured list
						// and selects a Channel.
						homePageCommonFun.selectFeaturedChannel(videoList.get(
								XidioConstant.selectVideo).getTitle());

						Thread.sleep(XidioConstant.halfMin);

						Thread.sleep(sleepTime);

						// Manoj: Checking Selected video is partially played
						Thread.sleep(sleepTime);

						WebElement playFromPresent = driver
								.findElement(By
										.xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH));

						if (playFromPresent.isDisplayed() == true) {
							// Thread.sleep(sleepTime);
							// Thread.sleep(sleepTime);
							driver.findElement(
									By.xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH))
									.click();
							Thread.sleep(sleepTime);
							Thread.sleep(sleepTime);
						}

						Thread.sleep(sleepTime);
						Thread.sleep(sleepTime);
						Thread.sleep(sleepTime);

						boolean nowPlaying = driver
								.findElement(
										By.xpath(XpathObjectRepo.VIDEONOWPLAYINGOPOUP_XPATH))
								.isDisplayed();
						System.out.println("NOW PLAYING....." + nowPlaying);

						if (nowPlaying) {
							// This method performs mouse over on video player.
							Thread.sleep(XidioConstant.halfMin);
							homePageCommonFun.mouseOverOnVideoPlayer();

							flashApp.callFlashObject("pause");

							Thread.sleep(sleepTime);
							String currentPosition = flashApp
									.callFlashObject("getCurrentPosition");

							Double tempcurrentPosition = Double
									.parseDouble(currentPosition);
							currentPosition = Integer.valueOf(
									tempcurrentPosition.intValue()).toString();
							currentPosition = TestDataGenerator
									.getFormattedTime(currentPosition);

							String endPosition = flashApp
									.callFlashObject("getEndPosition");

							Double tempendPosition = Double
									.parseDouble(endPosition);
							endPosition = Integer.valueOf(
									tempendPosition.intValue()).toString();
							endPosition = TestDataGenerator
									.getFormattedTime(endPosition);

							Thread.sleep(sleepTime);
							/*
							 * driver.navigate().back();
							 * Thread.sleep(sleepTime);
							 */

							// This Method verifies Channel present in Featured
							// list and selects a Channel.
							// homePageCommonFun.selectFeaturedChannel(videos.getTitle());

							Thread.sleep(XidioConstant.tenSec);
							Thread.sleep(sleepTime);

							String getResumeBtnTxt = driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEORESUMEPOINTBUTTON_XPATH))
									.getText();

							assertEquals(
									UILablesRepo.VIDEORESUMEPOINTBUTTON_TEXT
											+ currentPosition, getResumeBtnTxt);

							String getStartBtnTxt = driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEOSTARTPOINTBUTTON_XPATH))
									.getText();
							assertEquals(
									UILablesRepo.VIDEOPLAYFROMSTARTBUTTON_TEXT,
									getStartBtnTxt);

						}
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
