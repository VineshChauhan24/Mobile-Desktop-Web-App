package comcast.test.app.testCases.videoManagement.publisher.PublisherPageTestCases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.JsonParser;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.configServices.utils.UrlFactoryUtil;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyPublisherShowsCount Description: This test case validates
 * whether Shows count is displayed in publisher details page by logging
 * registered user into Watchable application.
 * **/

public class VerifyPublisherShowsCount extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	List<VideoDetails> publishersDetails;

	@Test
	public void testVerifyPublisherShowsCount() throws Exception {

		String sessionToken = RestAPIServices.executeGenreAuthentication();

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

			// This method is to ensure Home is Active page when Login into
			// Application.
			// assertionFunction.assertHomeActiveLink();

			if (channelList != null) {
				int loopIndex = 0;
				if (channelList.size() < 2)
					loopIndex = channelList.size();
				else
					loopIndex = 2;
				for (int index = 0; index < loopIndex; index++) {
					VideoDetails channelDetails = channelList.get(index);

					// This Method verifies Channel present in Featured list and
					// selects a Channel.
					homePageCommonFun.selectFeaturedChannel(channelDetails
							.getTitle());

					publishersDetails = new ArrayList<VideoDetails>();

					// This is to get publisher details result
					String publisherResponse = RestAPIServices
							.getSessionTokenResponse(
									UrlFactoryUtil.getInstance()
											.getPublisherDetailsResponse(
													channelDetails
															.getPublisherURL()),
									sessionToken);
					List<VideoDetails> publisherDetails = JsonParser
							.parsePublisherDetailsResponse(publisherResponse);

					if (publisherDetails != null) {
						for (VideoDetails publisherResult : publisherDetails) {
							publishersDetails.add(publisherResult);
						}
					}

					// This Method verifies Channel present in Featured list and
					// selects a Channel.
					Thread.sleep(sleepTime);
					homePageCommonFun.selectPublisher(publishersDetails.get(0)
							.getPublisherName());

					String showsCount = driver
							.findElement(
									By.xpath(XpathObjectRepo.PUBLISHERPAGE_SHOWSCOUNT_XPATH))
							.getText();
					assertEquals("Shows "
							+ publishersDetails.get(0).getNumOfShows() + "",
							showsCount);

					for (int i = 0; i < 2; i++) {
						driver.navigate().back();
						Thread.sleep(sleepTime);
					}
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
