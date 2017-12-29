package comcast.test.app.testCases.videoManagement.videoHomeManagement.FeaturedCategoryTestCases.FeaturedCategoryPageTestCases;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

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
 * Class Name: VerifyFeaturedListHasOnlyEpisodesUnderChannels Description: This
 * test case verify Featured Channels which contains Episodes directly under
 * channels by logging registered user into Gazeebo application.
 * **/

public class VerifyFeaturedListHasOnlyEpisodesUnderChannels extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyFeaturedListHasOnlyEpisodesUnderChannels()
			throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nFeaturedAPI();
		List<VideoDetails> featuredChannelList = videoDetails
				.get("featuredChannelsHasOnlyEpisodes");
		String sessionToken = RestAPIServices.executeGenreAuthentication();

		try {
			/*
			 * This Method is to register new user using Gazeebo application and
			 * to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method is to assert Gazeebo Top Middle Menu and to ensure
			// its collapsed.
			// assertionFunction.assertGazeeboTopMiddleMenu();

			if (featuredChannelList != null) {
				int loopMaxIndex = 0;
				if (featuredChannelList.size() < 4)
					loopMaxIndex = featuredChannelList.size();
				else
					loopMaxIndex = 4;

				for (int index = 0; index < loopMaxIndex; index++) {
					VideoDetails channelList = featuredChannelList.get(index);
					// This Method verifies Channel present in Featured list has
					// only episodes under channels.
					homePageCommonFun.selectFeaturedChannel(channelList
							.getTitle());

					String videoResponse = RestAPIServices
							.getSessionTokenResponse(
									UrlFactoryUtil.getInstance()
											.getVideoDetailsURL(
													channelList.getId(), 10),
									sessionToken);
					List<VideoDetails> featuredVideoList = JsonParser
							.parseChannelShowsVideosResponse(videoResponse);

					// This Method is to navigate Last Updated detail page.
					// homePageCommonFun.clickOnLastUpdatedLink();

					Thread.sleep(sleepTime);
					// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches(featuredVideoList.get(XidioConstant.selectVideo).getTitle()));
					assertTrue(driver
							.findElement(
									By.xpath(XpathObjectRepo.HOMEFEATUREDFIRSTTITLELABEL_XPATH))
							.getText()
							.matches(
									featuredVideoList.get(
											XidioConstant.selectVideo)
											.getTitle()));

					driver.navigate().back();
					Thread.sleep(sleepTime);
				}
			}

			// This method asserts Watchable Logo.
			assertionFunction.assertWatchableLogo();

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method is used to logout from Gazeebo Application.
			userLogin.LogOut(driver);

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
