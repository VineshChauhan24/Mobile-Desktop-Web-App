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
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: PlayHomeFeaturedVideoBySelectingShowInHomePage Description: This
 * test case is to play the video by clicking a show that is displayed under
 * 'Featured' section on 'Home' screen.
 * **/

public class PlayHomeFeaturedVideoBySelectingShowInHomePage extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testPlayHomeFeaturedVideoBySelectingShowInHomePage()
			throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nHomeFeaturedAPI();
		List<VideoDetails> showList = videoDetails.get("featuredShowList");
		List<VideoDetails> videoList = videoDetails.get("subShowVideos");
		Actions actions = new Actions(driver);

		int durationInSeconds = 0;
		int durationInMins = 0;

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is to ensure Home is Active page when Login into
			// Application.
			// assertionFunction.assertHomeActiveLink();

			// This method asserts featured title.
			assertionFunction.assertFeaturedTitle();

			if (showList != null) {
				// This Method verifies Show present in Home Featured list and
				// selects a Show.
				homePageCommonFun.selectHomeFeaturedShow(showList.get(
						XidioConstant.selectShow).getTitle());

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
								.xpath(".//*[@id='content-video']"));
						actions.moveToElement(videoPage);
						actions.perform();

						String getVideoPlayState = driver.findElement(
								By.id("playorpause")).getAttribute("class");
						assertEquals("play", getVideoPlayState);

						// assertTrue(driver.findElement(By.xpath("//div[@class='video-metadata']/descendant::h3")).getText().matches("^[\\s\\S]*"+videoList.get(XidioConstant.selectVideo).getTitle()+"[\\s\\S]*$"));
						// Lekshmi : Change the object identifier.
						assertTrue(driver
								.findElement(
										By.xpath("//div[@class='video-metadata']/descendant::h3"))
								.getText()
								.equalsIgnoreCase(
										videoList
												.get(XidioConstant.selectVideo)
												.getTitle()));
						// div[@class='video-metadata']/descendant::h3

						// This method is to assert Up Next Header
						assertionFunction.assertUpNextTitle();
					}
				} else {
					for (int j = 0; j < 5; j++) {
						Thread.sleep(XidioConstant.OneMinSTForVideoPlay);
						WebElement videoPage = driver.findElement(By
								.xpath("//div[@id='content-video']"));
						actions.moveToElement(videoPage);
						actions.perform();

						String getVideoPlayState = driver.findElement(
								By.id("playorpause")).getAttribute("class");
						assertEquals("pause", getVideoPlayState);

						assertTrue(driver
								.findElement(By.cssSelector("BODY"))
								.getText()
								.matches(
										"^[\\s\\S]*"
												+ videoList
														.get(XidioConstant.selectVideo)
														.getTitle()
												+ "[\\s\\S]*$"));

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
