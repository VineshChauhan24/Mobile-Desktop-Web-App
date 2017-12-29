package comcast.test.app.testCases.videoManagement.publisher.PublisherPageTestCases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
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
 * Class Name: VerifyPublisherDetails Description: This test case validates
 * Publisher page details. by logging registered user into Watchable
 * application.
 * **/

public class VerifyPublisherDetails extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	List<VideoDetails> publishersDetails;
	String pageTitle = "";

	@Test
	public void testVerifyPublisherDetails() throws Exception {

		String sessionToken = RestAPIServices.executeGenreAuthentication();

		// Lekshmi : Change the FeaturedAPI to PopularChannelAPI, as there is no
		// channels in the Featured section of the application.
		// Map<String, List<VideoDetails>>
		// videoDetails=RestAPIServices.featuredChannelsList();
		// List <VideoDetails>
		// channelList=videoDetails.get("featuredChannelsList");
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.allPopularChannelsList();
		List<VideoDetails> channelList = videoDetails
				.get("popularChannelsList");

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */

			// This test case does not required login. So commenting below Login
			// method
			// RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// Opening application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);
			pageTitle = driver.getTitle();

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method is to assert Gazeebo Top Middle Menu and to ensure
			// its collapsed.
			// assertionFunction.assertGazeeboTopMiddleMenu();

			if (channelList != null) {
				int loopIndex = 0;
				if (channelList.size() < 2)
					loopIndex = channelList.size();
				else
					loopIndex = 2;

				for (int index = 0; index < loopIndex - 1; index++) {
					VideoDetails channelDetails = channelList.get(index);

					// This Method verifies Channel present in Featured list and
					// selects a Channel.

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
					Thread.sleep(sleepTime);
					// This Method verifies Channel present in Featured list and
					// selects a Channel.
					VideoDetails popularChannels = channelList.get(index);
					homePageCommonFun.selectPopularChannel(popularChannels
							.getTitle());
					homePageCommonFun.selectPublisher(publishersDetails.get(
							index).getPublisherName());
					Thread.sleep(sleepTime);
					// Map<String, List<VideoDetails>>
					// publisherDetail=RestAPIServices.publisherDetails(channelDetails.getPublisherId());
					// List <VideoDetails>
					// publisherList=publisherDetails.get("publishersDetails");

					Thread.sleep(sleepTime);
					// Lekshmi : change the object identifier for the publisher
					// details page.
					/*
					 * assertTrue(driver.findElement(By.cssSelector("BODY")).getText
					 * ().matches("^[\\s\\S]*"+publishersDetails.get(index).
					 * getPublisherName()+"[\\s\\S]*$"));
					 * 
					 * assertTrue(driver.findElement(By.cssSelector("BODY")).getText
					 * ().matches("^[\\s\\S]*"+publishersDetails.get(index).
					 * getPublisherDescription()+"[\\s\\S]*$"));
					 * 
					 * assertTrue(driver.findElement(By.cssSelector("BODY")).getText
					 * ().matches("^[\\s\\S]*CHANNELS[\\s\\S]*$"));
					 * 
					 * assertTrue(driver.findElement(By.cssSelector("BODY")).getText
					 * ().matches("^[\\s\\S]*CATEGORIES[\\s\\S]*$"));
					 */

					assertTrue(driver
							.findElement(
									By.xpath(XpathObjectRepo.PUBLISHERPAGE_PUBLISHERTITLE_XPATH))
							.getText()
							.equalsIgnoreCase(
									publishersDetails.get(index)
											.getPublisherName()));

					// assertTrue(driver.findElement(By.xpath(XpathObjectRepo.PUBLISHERPAGE_PUBLISHERDESCRPTN_XPATH)).getText().contains(publishersDetails.get(index).getPublisherDescription()));

					assertTrue(driver
							.findElement(
									By.xpath(XpathObjectRepo.PUBLISHERPAGE_CHANNELSHEADER_XPATH))
							.getText().equalsIgnoreCase("CHANNELS"));

					assertTrue(driver
							.findElement(
									By.xpath(XpathObjectRepo.PUBLISHERPAGE_CATEGORIESHEADER_XPATH))
							.getText().equalsIgnoreCase("CATEGORIES"));

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
			// userLogin.LogOut(driver);

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			// assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {

			if (pageTitle.contains(UILablesRepo.HOMEPAGE_TITLE)) {
				captureScreenshot();
				collector.addError(t);
			}

		}
	}
}
