/*Zoom Out[Full Screen] functionality has an issue when there is 'Now Playing' displayed in the Video Player page.
Because of that issue the test script is expected to be failed.
ISSUE : When the Full screen control is clicked and when there is Now Playing displayed in the Full screen, 
video controls are not getting displayed. 
JIRA ID:  XWC-1584
 */

package comcast.test.app.testCases.videoManagement.videoPlay.videoStreamingTestCases;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

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
 * Class Name: VerifyCurrentPosition Description: This test case is to verify
 * Zoom Out and Zoom In functionality in video player by logging into Watchable
 * application.
 * **/

public class VerifyZoomInAndZoomOutFunctonality extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyZoomInAndZoomOutFunctonality() throws Exception {

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
					homePageCommonFun.mouseOverOnVideoPlayer();
					driver.findElement(
							By.xpath(XpathObjectRepo.VIDEOFULLSCREENBUTTON_XPATH))
							.click();
					Thread.sleep(XidioConstant.tenSec);

					String id = driver.findElement(
							By.xpath(XpathObjectRepo.VIDEOPLAYER_XPATH))
							.getAttribute("id");
					assertEquals("PlayerPlatformAPI", id);

					String getWidth = driver.findElement(
							By.xpath(XpathObjectRepo.VIDEOPLAYER_XPATH))
							.getAttribute("width");
					String getHeitht = driver.findElement(
							By.xpath(XpathObjectRepo.VIDEOPLAYER_XPATH))
							.getAttribute("height");

					homePageCommonFun.mouseOverOnVideoPlayer();
					driver.findElement(
							By.xpath(XpathObjectRepo.VIDEOFULLSCREENRESTOREBUTTON_XPATH))
							.click();
					Thread.sleep(XidioConstant.tenSec);
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
