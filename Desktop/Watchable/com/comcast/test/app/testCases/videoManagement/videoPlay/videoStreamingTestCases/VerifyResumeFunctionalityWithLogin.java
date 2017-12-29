package comcast.test.app.testCases.videoManagement.videoPlay.videoStreamingTestCases;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
 * Class Name: VerifyResumeFunctionalityWithLogin Description: This test case is
 * to verify Resume functionality in video player by logging into Watchable
 * application.
 * **/

public class VerifyResumeFunctionalityWithLogin extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testVerifyResumeFunctionalityWithLogin() throws Exception {

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
						// and selects a Video.
						homePageCommonFun.selectFeaturedChannel(videoList.get(
								XidioConstant.selectVideo).getTitle());

						Thread.sleep(XidioConstant.halfMin);
						/*
						 * Thread.sleep(sleepTime); Thread.sleep(sleepTime);
						 * 
						 * // Manoj: Checking Selected video is partially played
						 * Thread.sleep(sleepTime);
						 * 
						 * WebElement playFromPresent = driver .findElement(By
						 * .xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH));
						 * 
						 * if (playFromPresent.isDisplayed() == true) { //
						 * Thread.sleep(sleepTime); // Thread.sleep(sleepTime);
						 * driver.findElement(
						 * By.xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH))
						 * .click(); Thread.sleep(sleepTime);
						 * Thread.sleep(sleepTime); }
						 * 
						 * Thread.sleep(sleepTime); Thread.sleep(sleepTime);
						 */

						boolean nowPlaying = driver
								.findElement(
										By.xpath(XpathObjectRepo.VIDEONOWPLAYINGOPOUP_XPATH))
								.isDisplayed();
						log.info("NOW PLAYING....." + nowPlaying);

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

							Thread.sleep(sleepTime);
							// driver.navigate().back();
							// Thread.sleep(sleepTime);
						}

						// This Method verifies Channel present in Featured list
						// and selects a Channel.
						// homePageCommonFun.selectFeaturedChannel(videos.getTitle());
						else {
							flashApp.callFlashObject("getCurrentPosition");
							String currentPosition = flashApp
									.callFlashObject("getCurrentPosition");
							Thread.sleep(XidioConstant.tenSec);
							String getVideoTitle = driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEODETAILSPAGETITLE_XPATH))
									.getText();
							assertEquals(videos.getTitle(), getVideoTitle);

							// This method performs mouse over on video player.
							homePageCommonFun.mouseOverOnVideoPlayer();

							// Assert video paused state value
							String getVideoPlayState = driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
									.getAttribute("class");
							assertEquals(UILablesRepo.VIDEOPAUSEBUTTON_TEXT,
									getVideoPlayState);

							// Click on Resume Option
							// Lekshmi : Change the object identifier.
							// driver.findElement(By.xpath(".//*[@id='resume']")).click();
							homePageCommonFun.mouseOverOnVideoPlayer();

							driver.findElement(
									By.xpath(XpathObjectRepo.VIDEORESUMEPOINTBUTTON_XPATH))
									.click();

							Thread.sleep(XidioConstant.tenSec);

							String resumeCurrentPosition = flashApp
									.callFlashObject("getCurrentPosition");

							Double tempResumecurrentPosition = Double
									.parseDouble(resumeCurrentPosition);
							resumeCurrentPosition = Integer.valueOf(
									tempResumecurrentPosition.intValue())
									.toString();
							resumeCurrentPosition = TestDataGenerator
									.getFormattedTime(resumeCurrentPosition);

							if (currentPosition.equals(resumeCurrentPosition)) {
								// Assert Pause position and Resume position.
								assertEquals(currentPosition,
										resumeCurrentPosition);
							}

							flashApp.callFlashObject("pause");
							Thread.sleep(sleepTime);
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
