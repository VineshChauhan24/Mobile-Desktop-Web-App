package comcast.test.app.testCases.videoManagement.videoPlay.videoStreamingTestCases.PauseFunctionalityTestCases;

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
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyPauseFunctionality Description: This test case is to verify
 * pause functionality in video player by logging into Watchable application.
 * **/

public class VerifyPauseFunctionality extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyPauseFunctionality() throws Exception {

		FlashObjectWebDriver flashApp = new FlashObjectWebDriver(driver,
				"PlayerPlatformAPI");

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.featuredSectionVideos();
		List<VideoDetails> videoList = videoDetails.get("featuredVideoList");
		int durationInSeconds = 0;
		int durationInMins = 0;

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */

			// Login is not required to run this test case
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
					durationInSeconds = videoList
							.get(XidioConstant.selectVideo).getDuration();
					durationInMins = durationInSeconds / 60;

					// This Method verifies Channel present in Featured list and
					// selects a Channel.
					homePageCommonFun.selectFeaturedChannel(videoList.get(
							XidioConstant.selectVideo).getTitle());

					/*
					 * // Manoj: Checking Selected video is partially played
					 * Thread.sleep(sleepTime); Thread.sleep(sleepTime);
					 * Thread.sleep(sleepTime); WebElement playFromPresent =
					 * driver.findElement(By
					 * .xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH));
					 * 
					 * if (playFromPresent.isDisplayed() == true) {
					 * Thread.sleep(sleepTime); Thread.sleep(sleepTime);
					 * driver.findElement(
					 * By.xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH))
					 * .click(); }
					 */

					// Lekshmi : Change the object identifier.
					// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videoList.get(XidioConstant.selectVideo).getTitle()+"[\\s\\S]*$"));
					assertTrue(driver
							.findElement(
									By.xpath(XpathObjectRepo.VIDEODETAILSPAGETITLE_XPATH))
							.getText()
							.equalsIgnoreCase(
									videoList.get(XidioConstant.selectVideo)
											.getTitle()));

					// This method is to assert Up Next Header
					// assertionFunction.assertUpNextTitle();

					if (durationInMins < 5) {
						for (int i = 0; i < durationInMins; i++) {
							Thread.sleep(XidioConstant.halfMin);
							flashApp.callFlashObject("pause");

							// This method performs mouse over on video player.
							homePageCommonFun.mouseOverOnVideoPlayer();

							boolean nowPlaying = driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEONOWPLAYINGOPOUP_XPATH))
									.isDisplayed();
							System.out.println("NOW PLAYING....." + nowPlaying);
							if (nowPlaying) {
								String getPlayStatus = driver
										.findElement(
												By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
										.getAttribute("id");
								assertEquals(UILablesRepo.VIDEOPLAYBUTTON_TEXT,
										getPlayStatus);

								Thread.sleep(XidioConstant.halfMin);
								flashApp.callFlashObject("play");

								// This method performs mouse over on video
								// player.
								homePageCommonFun.mouseOverOnVideoPlayer();

								String getPauseStatus = driver
										.findElement(
												By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
										.getAttribute("id");
								assertEquals(
										UILablesRepo.VIDEOPAUSEBUTTON_TEXT,
										getPauseStatus);
								break;
							} else {
								String getPlayStatus = driver
										.findElement(
												By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
										.getAttribute("id");
								assertEquals(
										UILablesRepo.VIDEOPAUSEBUTTON_TEXT,
										getPlayStatus);

								Thread.sleep(XidioConstant.halfMin);
								flashApp.callFlashObject("play");

								// This method performs mouse over on video
								// player.
								homePageCommonFun.mouseOverOnVideoPlayer();

								String getPauseStatus = driver
										.findElement(
												By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
										.getAttribute("id");
								assertEquals(UILablesRepo.VIDEOPLAYBUTTON_TEXT,
										getPauseStatus);
							}

						}
					} else {
						for (int j = 0; j < 5; j++) {
							Thread.sleep(XidioConstant.halfMin);
							flashApp.callFlashObject("pause");

							// This method performs mouse over on video player.
							homePageCommonFun.mouseOverOnVideoPlayer();

							// String getPlayStatus =
							// driver.findElement(By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH)).getAttribute("id");

							boolean nowPlaying = driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEONOWPLAYINGOPOUP_XPATH))
									.isDisplayed();
							System.out.println("NOW PLAYING....." + nowPlaying);
							if (nowPlaying) {
								String getPlayStatus = driver
										.findElement(
												By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
										.getAttribute("class");
								assertEquals(UILablesRepo.VIDEOPLAYBUTTON_TEXT,
										getPlayStatus);

								Thread.sleep(XidioConstant.halfMin);
								flashApp.callFlashObject("play");

								// This method performs mouse over on video
								// player.
								homePageCommonFun.mouseOverOnVideoPlayer();

								String getPauseStatus = driver
										.findElement(
												By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
										.getAttribute("id");
								// assertEquals(UILablesRepo.VIDEOPAUSEBUTTON_TEXT,getPauseStatus);
								assertTrue(getPauseStatus
										.contains(UILablesRepo.VIDEOPAUSEBUTTON_TEXT));
								break;
							} else {
								String getPlayStatus = driver
										.findElement(
												By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
										.getAttribute("class");
								assertEquals(UILablesRepo.VIDEOPLAYBUTTON_TEXT,
										getPlayStatus);

								Thread.sleep(XidioConstant.halfMin);
								flashApp.callFlashObject("play");

								// This method performs mouse over on video
								// player.
								homePageCommonFun.mouseOverOnVideoPlayer();

								String getPauseStatus = driver
										.findElement(
												By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
										.getAttribute("id");
								// assertEquals(UILablesRepo.VIDEOPAUSEBUTTON_TEXT,getPauseStatus);
								assertTrue(getPauseStatus
										.contains(UILablesRepo.VIDEOPAUSEBUTTON_TEXT));
							}

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
