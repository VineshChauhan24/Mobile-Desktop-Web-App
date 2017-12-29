package comcast.test.app.testCases.videoManagement.publisher.PublisherChannels;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.testng.Assert;

import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.JsonParser;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.configServices.utils.UrlFactoryUtil;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyPublisherChannels Description: This test case verifies
 * publisher channels in CHANNELS page. by logging registered user into
 * Watchable application.
 * **/

public class VerifyPublisherChannels extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	List<VideoDetails> publishersDetails;
	private static List<VideoDetails> publishersChannelsDetail;

	@Test
	public void testVerifyPublisherChannels() throws Exception {

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
			 * This Method is to register new user using Gazeebo application and
			 * to change a password.
			 */

			// This test case does not required login. So commenting below Login
			// method
			// RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// Opening application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method is to assert Gazeebo Top Middle Menu and to ensure
			// its collapsed.
			// assertionFunction.assertGazeeboTopMiddleMenu();

			if (channelList != null) {
				int loopIndexMax = 0;
				if (channelList.size() < 2) {
					loopIndexMax = channelList.size();
				} else {
					loopIndexMax = 2;
				}

				for (int j = 0; j < loopIndexMax; j++) {
					VideoDetails channelDetails = channelList.get(j);
					// This Method verifies Channel present in Featured list and
					// selects a Channel.
					homePageCommonFun.selectPopularChannel(channelDetails
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

					publishersChannelsDetail = new ArrayList<VideoDetails>();

					// This is to get publisher details result
					String publisherResp = RestAPIServices
							.getSessionTokenResponse(
									UrlFactoryUtil.getInstance()
											.getPublisherChannelsDetailsURL(
													publishersDetails.get(0)
															.getPublisherId()),
									sessionToken);
					List<VideoDetails> publisherChannelDetails = JsonParser
							.parseGenresChannelsResponse(publisherResp);

					if (publisherChannelDetails != null
							&& publisherChannelDetails.size() < 10) {
						for (VideoDetails publisherResult : publisherChannelDetails) {
							publishersChannelsDetail.add(publisherResult);
						}
					} else {
						for (int index = 0; index < 10; index++) {
							VideoDetails publisherResult = publisherChannelDetails
									.get(index);
							publishersChannelsDetail.add(publisherResult);
						}
					}

					// Map<String, List<VideoDetails>>
					// publisherDetail=RestAPIServices.publisherChannelsDetails(publishersDetails.get(j).getPublisherId());
					// List <VideoDetails>
					// publisherChannelsList=publisherDetail.get("publishersChannels");

					Thread.sleep(sleepTime);
					if (publishersChannelsDetail != null) {
						int channelsLoopIndexMax = 0;
						if (publishersChannelsDetail.size() < 4) {
							channelsLoopIndexMax = publishersChannelsDetail
									.size();
						} else {
							channelsLoopIndexMax = 4;
						}

						for (int index = 0; index < channelsLoopIndexMax; index++) {
							VideoDetails publisherChannels = publishersChannelsDetail
									.get(index);
							// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+publisherChannels.getTitle()+"[\\s\\S]*$"));
							// Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='seq-row-list']/div[2]/div[2]/section/ul/li/article/h1/a")).getText(),publisherChannels.getTitle());
						}
					}

					for (int i = 0; i < 2; i++) {
						driver.navigate().back();
						Thread.sleep(sleepTime);
					}
				}
			}
			/*
			 * else { boolean isPresent; isPresent=driver.findElement(By.xpath(
			 * ".//*[@id='featured']/div/section/div/div/ul[1]"
			 * )).findElements(By.xpath(
			 * ".//*[@id='featured']/div/section/div/div/ul[1]/li[1]/article/a/div/div"
			 * )).size()>0; if(isPresent==true) { String
			 * channelName=driver.findElement
			 * (By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]"
			 * )).findElement(By.xpath(
			 * ".//*[@id='featured']/div/section/div/div/ul[1]/li[1]/article/a/div/div"
			 * )).getText();
			 * assertFalse(driver.findElement(By.cssSelector("BODY"
			 * )).getText().matches("^[\\s\\S]*"+channelName+"[\\s\\S]*$")); } }
			 */// commented as not applicable for popular channels .
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
