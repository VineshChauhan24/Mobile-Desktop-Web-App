package comcast.test.app.testCases.videoManagement.videoHomeManagement.FeaturedCategoryTestCases.ChannelTestCases.ChannelPageTestCases;

import static org.junit.Assert.assertFalse;

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
 * Class Name: EnsureShowCategoryIsDisplayedInFeaturedChannelDetailsPage
 * Description: This test case validates whether SHOWS,UNWATCHED and LAST
 * UPDATED links are displayed in channel page for the channel present under
 * Featured section in Home page by logging registered user into Watchable
 * application.
 * **/

public class EnsureShowCategoryIsDisplayedInFeaturedChannelDetailsPage extends
		BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testEnsureShowCategoryIsDisplayedInFeaturedChannelDetailsPage()
			throws Exception {

		/*
		 * Map<String, List<VideoDetails>>
		 * videoDetails=RestAPIServices.featuredChannelsList(); List
		 * <VideoDetails> channelList=videoDetails.get("featuredChannelsList");
		 */

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.featuredChannelsList();
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

				// This method is to assert Watchable Top Middle Menu and to
				// ensure its collapsed.
				// assertionFunction.assertGazeeboTopMiddleMenu(); -
				// Functionality not present now

				// This method asserts featured title.
				assertionFunction.assertFeaturedTitle();

				if (channelList != null) {
					int loopMaxIndex = 0;
					if (channelList.size() < 2)
						loopMaxIndex = channelList.size();
					else
						loopMaxIndex = 2;

					for (int index = 0; index < loopMaxIndex; index++) {
						VideoDetails channels = channelList.get(index);

						// This Method verifies Channel present in Featured list
						// and selects a Channel.
						homePageCommonFun.selectFeaturedChannel(channels
								.getTitle());

						// This method asserts SHOWS,UNWATCHED and LAST UPDATED
						// links.
						assertionFunction.assertChannelDetailsPageLinks();

						driver.navigate().back();
						Thread.sleep(sleepTime);
					}
				} else {
					boolean isPresent;
					isPresent = driver
							.findElement(
									By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSALLLIST_XPATH))
							.findElements(
									By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELS_IMG_XPATH))
							.size() > 0;
					if (isPresent == true) {
						String channelName = driver
								.findElement(
										By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSALLLIST_XPATH))
								.findElement(
										By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELS_IMG_XPATH))
								.getText();
						assertFalse(driver.findElement(By.cssSelector("BODY"))
								.getText().matches(channelName));
					}
				}
				// This method asserts Watchable Logo.
				assertionFunction.assertWatchableLogo();

				// This method asserts Footer Logo and It's Text.
				assertionFunction.assertFooterLogo();

				// This method asserts Footer Copy Right Links.
				assertionFunction.assertFooterCopyRight();

				// This method is used to logout from Watchable Application.
				userLogin.LogOut(driver);
			}

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
