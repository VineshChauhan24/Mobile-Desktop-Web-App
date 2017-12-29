package comcast.test.app.testCases.videoManagement.videoPlay.HomeCategoryVideos.FeaturedVideos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.constant.XidioConstant;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: PlayHomeFeaturedVideoFromRows Description: This test case is to
 * play the video from SHOWS category by directly selecting the videos in the
 * channel page for featured section in Home page.
 * **/

public class PlayHomeFeaturedVideoFromRows extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testPlayHomeFeaturedVideoFromRows() throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nHomeFeaturedAPI();

		Actions actions = new Actions(driver);

		int durationInSeconds = 0;
		int durationInMins = 0;

		try {
			List<VideoDetails> channelList = videoDetails
					.get("featuredChannelsList");
			List<VideoDetails> videoList = videoDetails.get("video");
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method asserts featured title.
			assertionFunction.assertFeaturedTitle();

			if (channelList != null && !channelList.isEmpty()) {
				// This Method verifies Channel present in Featured list and
				// selects a Channel.
				homePageCommonFun.selectFeaturedChannel(channelList.get(
						XidioConstant.selectFeaturedChannel).getTitle());

				// This Method is to navigate Shows detail page.
				homePageCommonFun.clickOnShowsLink();

				// Select Video under Channel show.
				driver.findElement(
						By.partialLinkText(videoList.get(
								XidioConstant.selectVideo).getTitle())).click();

				durationInSeconds = videoList.get(XidioConstant.selectVideo)
						.getDuration();
				durationInMins = durationInSeconds / 60;

				if (durationInMins < 5) {
					for (int i = 0; i < durationInMins; i++) {
						Thread.sleep(XidioConstant.OneMinSTForVideoPlay);
						WebElement videoPage = driver.findElement(By
								.xpath("//div[@id='content-video']"));
						actions.moveToElement(videoPage);
						actions.perform();

						String getVideoPlayState = driver.findElement(
								By.id("playorpause")).getAttribute("class");
						assertEquals("play", getVideoPlayState);

						// Lekshmi : Change the object identifier to fetch the
						// video title.
						// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videoList.get(XidioConstant.selectVideo).getTitle()+"[\\s\\S]*$"));
						assertTrue(driver
								.findElement(
										By.xpath("//div[@class='video-metadata']/descendant::h3"))
								.getText()
								.equalsIgnoreCase(
										videoList
												.get(XidioConstant.selectVideo)
												.getTitle()));

						// This method is to assert Up Next Header
						assertionFunction.assertUpNextTitle();
					}
				} else {
					for (int j = 0; j < 5; j++) {
						Thread.sleep(XidioConstant.OneMinSTForVideoPlay);
						WebElement videoPage = driver.findElement(By
								.xpath("//div[@id='content-wrap']"));
						actions.moveToElement(videoPage);
						actions.perform();

						String getVideoPlayState = driver.findElement(
								By.id("playorpause")).getAttribute("class");
						assertEquals("play", getVideoPlayState);

						// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videoList.get(XidioConstant.selectVideo).getTitle()+"[\\s\\S]*$"));
						assertTrue(driver
								.findElement(
										By.xpath("//div[@class='video-metadata']/descendant::h3"))
								.getText()
								.equalsIgnoreCase(
										videoList
												.get(XidioConstant.selectVideo)
												.getTitle()));

						// This method is to assert Up Next Header
						assertionFunction.assertUpNextTitle();
					}
				}
			}

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Copy Right Links.
			assertionFunction.assertFooterCopyRight();

			// This method is used to logout from Watchable Application.
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
