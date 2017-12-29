package comcast.test.app.testCases.videoManagement.videoPlay.videoStreamingTestCases;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
 * Class Name: VerifyBackwardFunctionality Description: This test case is to
 * verify Backward functionality in video player by logging into Watchable
 * application.
 * **/

public class VerifyBackwardFunctionality extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyBackwardFunctionality() throws Exception {

		FlashObjectWebDriver flashApp = new FlashObjectWebDriver(driver,
				"PlayerPlatformAPI");

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.featuredSectionVideos();
		List<VideoDetails> videoList = videoDetails.get("featuredVideoList");
		double endPosition, startPosition, Postion, nextPosition;

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

					Thread.sleep(sleepTime);
					Thread.sleep(sleepTime);
					Thread.sleep(sleepTime);

					// Manoj: Checking Selected video is partially played
					Thread.sleep(sleepTime);

					WebElement playFromPresent = driver.findElement(By
							.xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH));

					if (playFromPresent.isDisplayed() == true) {
						// Thread.sleep(sleepTime);
						// Thread.sleep(sleepTime);
						driver.findElement(
								By.xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH))
								.click();
					}

					Thread.sleep(sleepTime);
					Thread.sleep(sleepTime);
					Thread.sleep(sleepTime);

					Thread.sleep(XidioConstant.halfMin);
					String getEndPosition = flashApp
							.callFlashObject("getDuration");
					endPosition = Float.parseFloat((getEndPosition));
					startPosition = 10;
					Postion = Math.floor(endPosition) / 5;
					nextPosition = Math.floor(endPosition) - Postion;

					for (int i = 0; i < 2; i++) {
						// String clickOnPosition = Double.toString(Postion);
						// flashApp.callFlashObject("setPosition",
						// clickOnPosition);
						flashApp.callFlashObject("pause");
						Thread.sleep(XidioConstant.tenSec);
						String currentPosition = flashApp
								.callFlashObject("getCurrentPosition");
						Double tempcurrentPosition = Double
								.parseDouble(currentPosition);
						currentPosition = Integer.valueOf(
								tempcurrentPosition.intValue()).toString();
						int currPostion = Integer.parseInt(currentPosition) - 10;

						if (currPostion > startPosition) {
							homePageCommonFun.mouseOverOnVideoPlayer();
							driver.findElement(
									By.xpath(XpathObjectRepo.VIDEOREWINDBUTTON_XPATH))
									.click();
							Thread.sleep(XidioConstant.tenSec);
							String backwardPosition = flashApp
									.callFlashObject("getCurrentPosition");
							Double tempForwardedPosition = Double
									.parseDouble(backwardPosition);
							backwardPosition = Integer.valueOf(
									tempForwardedPosition.intValue())
									.toString();
							int forwarPositon = Integer
									.parseInt(backwardPosition);

							assertEquals(forwarPositon, currPostion);

							currentPosition = TestDataGenerator
									.getFormattedTime(currentPosition);
							backwardPosition = TestDataGenerator
									.getFormattedTime(backwardPosition);

							String pausedPositionFromUI = driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEOCURRENTPOSITIONLABEL_XPATH))
									.getText();
							pausedPositionFromUI = pausedPositionFromUI.trim();
							assertEquals(backwardPosition, pausedPositionFromUI);
						}

						flashApp.callFlashObject("play");
						Thread.sleep(XidioConstant.tenSec);
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
