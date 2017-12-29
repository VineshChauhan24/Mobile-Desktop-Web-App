package comcast.test.app.testCases.videoManagement.videoPlay.HomeCategoryVideos.FeaturedVideos;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.constant.XidioConstant;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.common.videoManagement.homePage.common.VideoPlay;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: PlayHomeFeaturedVideoSelectingFromShows Description: This test
 * case is to play the video from UNWATCHED category by selecting a show from
 * channel page under featured section in Home page.
 * **/

public class PlayHomeFeaturedVideoSelectingFromShows extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	VideoPlay videoplay = new VideoPlay();

	@Test
	public void testPlayHomeFeaturedVideoSelectingFromShows() throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nHomeFeaturedAPI();
		List<VideoDetails> channelList = videoDetails
				.get("featuredChannelsList");
		List<VideoDetails> showsListUnderChannel = videoDetails
				.get("showsUnderChannel");
		List<VideoDetails> videoList = videoDetails.get("video");

		int durationInSeconds = 0;
		int durationInMins = 0;

		try {
			/*
			 * This Method is to register new user using Gazeebo application and
			 * to change a password.
			 */
			// RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			driver.get(proUtil.getProperty("HOMEAPPURL"));
			Thread.sleep(sleepTime);

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method asserts featured title.
			assertionFunction.assertFeaturedTitle();

			if (channelList != null) {
				// This Method verifies Channel present in Featured list and
				// selects a Channel.
				homePageCommonFun.selectFeaturedChannel(channelList.get(
						XidioConstant.selectFeaturedChannel).getTitle());

				// This Method is to navigate Unwatched detail page.
				// homePageCommonFun.clickOnUnwatchedLink();

				// select show from channel
				driver.findElement(
						By.linkText(showsListUnderChannel.get(
								XidioConstant.selectShow).getTitle())).click();

				// Select Video under Channel show.
				String videoName = videoList.get(XidioConstant.selectVideo)
						.getTitle();
				driver.findElement(By.partialLinkText(videoName)).click();

				durationInSeconds = videoList.get(XidioConstant.selectVideo)
						.getDuration();
				durationInMins = durationInSeconds / 60;

				// This method is to validate video play functionality.
				videoplay.videoPlayVerification(durationInMins, videoName);
			}

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Copy Right Links.
			assertionFunction.assertFooterCopyRight();

			// This method is used to logout from Gazeebo Application.
			// userLogin.LogOut(driver);

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
