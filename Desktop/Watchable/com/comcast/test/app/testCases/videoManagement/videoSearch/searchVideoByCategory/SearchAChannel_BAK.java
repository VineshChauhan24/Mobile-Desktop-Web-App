package comcast.test.app.testCases.videoManagement.videoSearch.searchVideoByCategory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: SearchAChannel Description: This test case validates Channel
 * search functionality in Home page by logging registered user into Watchable
 * application.
 * **/

public class SearchAChannel_BAK extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testSearchAChannel() throws Exception {

		// Map <String,List<VideoDetails>>
		// videoDetails=RestAPIServices.featuredChannelsList();
		// List <VideoDetails>
		// channelList=videoDetails.get("featuredChannelsList");
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.allPopularChannelsList();
		List<VideoDetails> channelList = videoDetails
				.get("popularChannelsList");
		Map<String, List<VideoDetails>> searchVideoDetails = null;

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method asserts Gazeebo Logo.
			// assertionFunction.assertGazeeboLogo();

			if (channelList != null) {
				int loopIndexMax = 0;
				if (channelList.size() < 2) {
					loopIndexMax = channelList.size();
				} else {
					loopIndexMax = 2;
				}
				for (int index = 0; index < loopIndexMax; index++) {
					VideoDetails channels = channelList.get(index);

					// This Method verifies Channel present in Featured list and
					// selects a Channel.
					homePageCommonFun.searchAnAsset(channels.getTitle());

					searchVideoDetails = RestAPIServices.searchResultDetails(
							channels.getTitle(), channels.getTitle(),
							channels.getTitle());
					List<VideoDetails> searchChannelResult = searchVideoDetails
							.get("searchChannelsResult");
					List<VideoDetails> searchShowResult = searchVideoDetails
							.get("searchShowsResult");
					List<VideoDetails> searchEpisodeResult = searchVideoDetails
							.get("searchEpisodesResult");

					if (searchChannelResult != null) {
						// assertEquals(channels.getTitle(),searchedChannel.getTitle());
						for (VideoDetails searchedChannel : searchChannelResult) {
							assertTrue(driver
									.findElement(By.cssSelector("BODY"))
									.getText()
									.matches(searchedChannel.getTitle()));
						}
					}

					if (searchShowResult != null) {
						for (VideoDetails searchedShow : searchShowResult) {
							assertTrue(driver
									.findElement(By.cssSelector("BODY"))
									.getText().matches(searchedShow.getTitle()));
						}
					}

					if (searchEpisodeResult != null) {
						for (VideoDetails searchedEpisoed : searchEpisodeResult) {
							assertTrue(driver
									.findElement(By.cssSelector("BODY"))
									.getText()
									.matches(searchedEpisoed.getTitle()));
						}
					}

					driver.navigate().back();
					Thread.sleep(sleepTime);
				}
			} else {
				boolean isPresent;
				isPresent = driver
						.findElement(
								By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]"))
						.findElements(
								By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]/li[1]/article/a/div/div"))
						.size() > 0;
				if (isPresent == true) {
					String channelName = driver
							.findElement(
									By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]"))
							.findElement(
									By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]/li[1]/article/a/div/div"))
							.getText();
					assertFalse(driver.findElement(By.cssSelector("BODY"))
							.getText()
							.matches("^[\\s\\S]*" + channelName + "[\\s\\S]*$"));
				}
			}

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Copy Right Links.
			assertionFunction.assertFooterCopyRight();

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
