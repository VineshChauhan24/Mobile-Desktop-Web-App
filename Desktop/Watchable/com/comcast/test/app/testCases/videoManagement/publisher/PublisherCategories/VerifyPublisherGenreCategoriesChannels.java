package comcast.test.app.testCases.videoManagement.publisher.PublisherCategories;

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
 * Class Name: VerifyPublisherGenreCategoriesChannels Description: This test
 * case verifies publisher Genre Categories channels in CATEGORIES page. by
 * logging registered user into Watchable application.
 * **/

public class VerifyPublisherGenreCategoriesChannels extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	List<VideoDetails> publishersDetails;

	@Test
	public void testVerifyPublisherGenreCategoriesChannels() throws Exception {

		String sessionToken = RestAPIServices.executeGenreAuthentication();

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.featuredChannelsList();
		List<VideoDetails> channelList = videoDetails
				.get("featuredChannelsList");

		Map<String, List<VideoDetails>> genreDetails = RestAPIServices
				.GenresAPI();
		List<VideoDetails> genresCategoryList = genreDetails
				.get("genresCategoryChannelsList");

		try {
			/*
			 * This Method is to register new user using Gazeebo application and
			 * to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is to ensure Home is Active page when Login into
			// Application.
			// assertionFunction.assertHomeActiveLink();

			// This method is to assert Gazeebo Top Middle Menu and to ensure
			// its collapsed.
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
					homePageCommonFun.selectPublisher(publishersDetails.get(j)
							.getPublisherName());

					// Click on Categories link
					homePageCommonFun.clickOnCategoryLink();

					if (genresCategoryList != null) {
						for (VideoDetails genreCategory : genresCategoryList) {
							String genreId = genreCategory.getId();

							String publisherGenreResponse = RestAPIServices
									.getSessionTokenResponse(
											UrlFactoryUtil
													.getInstance()
													.getPublisherGenreChannelsDetailsURL(
															publishersDetails
																	.get(j)
																	.getPublisherId(),
															genreId),
											sessionToken);
							List<VideoDetails> publisherChannelDetails = JsonParser
									.parseGenresChannelsResponse(publisherGenreResponse);

							if (publisherChannelDetails != null) {
								int genreChannelsLoopIndexMax = 0;
								if (publisherChannelDetails.size() < 2)
									genreChannelsLoopIndexMax = publisherChannelDetails
											.size();
								else
									genreChannelsLoopIndexMax = 2;

								for (int index = 0; index < genreChannelsLoopIndexMax; index++) {
									VideoDetails publisherChannels = publisherChannelDetails
											.get(index);
									// Assert and Select a Channel
									Thread.sleep(sleepTime);
									assertTrue(driver
											.findElement(By.cssSelector("BODY"))
											.getText()
											.matches(
													publisherChannels
															.getTitle()));

									driver.findElement(
											By.partialLinkText(publisherChannels
													.getTitle())).click();

									Thread.sleep(sleepTime);
									driver.navigate().back();
									Thread.sleep(sleepTime);

									// Click on Categories link
									homePageCommonFun.clickOnCategoryLink();
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
