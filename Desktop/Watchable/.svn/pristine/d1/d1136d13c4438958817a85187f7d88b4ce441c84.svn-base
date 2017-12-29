package comcast.test.app.testCases.videoManagement.publisher.PublisherChannels;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
 * Class Name: VerifyVideosUnderPublisherChannels Description: This test case
 * verifies publisher channels videos in CHANNELS page. by logging registered
 * user into Watchable application.
 * **/

public class VerifyVideosUnderPublisherChannels extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	List<VideoDetails> publishersDetails;

	@Test
	public void testVerifyVideosUnderPublisherChannels() throws Exception {

		String sessionToken = RestAPIServices.executeGenreAuthentication();

		/*
		 * Map<String, List<VideoDetails>>
		 * videoDetails=RestAPIServices.featuredChannelsList(); List
		 * <VideoDetails> channelList=videoDetails.get("featuredChannelsList");
		 */
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

				if (channelList != null) {
					int loopIndexMax = 0;
					if (channelList.size() < 1) {
						loopIndexMax = channelList.size();
					} else {
						loopIndexMax = 1;
					}

					for (int j = 0; j < loopIndexMax; j++) {
						VideoDetails channelDetails = channelList.get(j);
						// This Method verifies Channel present in Featured list
						// and selects a Channel.
						homePageCommonFun.selectPopularChannel(channelDetails
								.getTitle());

						publishersDetails = new ArrayList<VideoDetails>();

						// This is to get publisher details result
						String publisherResponse = RestAPIServices
								.getSessionTokenResponse(
										UrlFactoryUtil
												.getInstance()
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

						// This Method verifies Channel present in Featured list
						// and selects a Channel.
						Thread.sleep(sleepTime);
						homePageCommonFun.selectPublisher(publishersDetails
								.get(j).getPublisherName());

						Map<String, List<VideoDetails>> publisherDetail = RestAPIServices
								.publisherChannelsDetails(publishersDetails
										.get(j).getPublisherId());
						List<VideoDetails> publisherChannelsList = publisherDetail
								.get("publishersChannels");

						Thread.sleep(sleepTime);
						if (publisherChannelsList != null) {
							int channelsLoopIndexMax = 0;
							if (publisherChannelsList.size() < 2) {
								channelsLoopIndexMax = publisherChannelsList
										.size();
							} else {
								channelsLoopIndexMax = 2;
							}

							for (int index = 0; index < channelsLoopIndexMax; index++) {
								VideoDetails publisherChannels = publisherChannelsList
										.get(index);
								String videoResponse = RestAPIServices
										.getSessionTokenResponse(
												UrlFactoryUtil
														.getInstance()
														.getVideoDetailsURL(
																publisherChannels
																		.getId(),
																10),
												sessionToken);
								List<VideoDetails> publisherChannelsDetail = JsonParser
										.parseChannelShowsVideosResponse(videoResponse);

								if (publisherChannelsDetail != null) {
									int videoLoopIndexMax = 0;
									if (publisherChannelsDetail.size() < 4) {
										videoLoopIndexMax = publisherChannelsDetail
												.size();
									} else {
										videoLoopIndexMax = 4;
									}

									for (int k = 0; k < videoLoopIndexMax; k++) {
										VideoDetails videoList = publisherChannelsDetail
												.get(k);
										// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videoList.getTitle()+"[\\s\\S]*$"));
									}
								}
							}
						}

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
