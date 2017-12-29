package comcast.test.app.testCases.videoManagement.videoHomeManagement.PopularChannelsCategoryTestCases.ChannelTestCases.ChannelPageTestCases;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: EnsureShowsCategoryIsDisplayedForPopularChannels Description:
 * This test case validates whether SHOWS link is displayed in channel page for
 * the channel present under Popular Channels section in Home page by logging
 * registered user into Gazeebo application.
 * **/

public class EnsureShowsCategoryIsDisplayedForPopularChannels extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testEnsureShowsCategoryIsDisplayedForPopularChannels()
			throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.allPopularChannelsList();
		List<VideoDetails> channelList = videoDetails
				.get("popularChannelsList");

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

				// This method asserts Popular Channels title.
				assertionFunction.assertPopularChannelsTitle();

				if (channelList != null) {
					// This method is to scroll UI to Popular Channels Section.
					// homePageCommonFun.scrollToPopularChannelsSection();

					int loopMaxIndex = 0;
					if (channelList.size() < 2)
						loopMaxIndex = channelList.size();
					else
						loopMaxIndex = 2;

					for (int index = 0; index < loopMaxIndex; index++) {
						VideoDetails channels = channelList.get(index);
						// This Method verifies Channel present in Popular
						// Channel Section and selects a Channel.
						homePageCommonFun.selectPopularChannel(channels
								.getTitle());

						// This method asserts SHOWS link.
						assertionFunction.assertChannelDetailsPageLinks();

						driver.navigate().back();
						Thread.sleep(sleepTime);
					}
				}
				// This method asserts Gazeebo Logo.
				assertionFunction.assertWatchableLogo();

				// This method is used to logout from Gazeebo Application.
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
