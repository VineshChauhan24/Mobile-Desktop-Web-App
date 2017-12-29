package comcast.test.app.testCases.videoManagement.videoPlay.HomeCategoryVideos.FeaturedVideos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.constant.XidioConstant;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: PlayVideoSelectingFromHomeFeaturedCategory Description: This test
 * case is to play the video by clicking video from 'Featured' section on 'Home'
 * screen by logging into Watchable application.
 */

public class PlayVideoSelectingFromHomeFeaturedCategory extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();

	@Test
	public void testPlayVideoSelectingFromHomeFeaturedCategory()
			throws Exception {

		Map<String, List<VideoDetails>> videoList = RestAPIServices
				.nFeaturedAPI();
		List<VideoDetails> featuredVideoList = videoList
				.get("featuredVideoList");
		Actions actions = new Actions(driver);
		int durationInSeconds = 0;
		int durationInMins = 0;

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);
			Thread.sleep(sleepTime);
			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

				// This method is to ensure Home is Active page when Login into
				// Application.
				assertionFunction.assertHomeActiveLink();

				// This method asserts featured title.
				assertionFunction.assertFeaturedTitle();

				if (featuredVideoList != null) {
					// This Method verifies Episoed present in Featured list and
					// selects a Episode.
					homePageCommonFun.selectHomeFeaturedShow(featuredVideoList
							.get(XidioConstant.selectVideo).getTitle());

					durationInSeconds = featuredVideoList.get(
							XidioConstant.selectVideo).getDuration();
					durationInMins = durationInSeconds / 60;

					if (durationInMins < 5) {
						for (int i = 0; i < durationInMins; i++) {
							Thread.sleep(XidioConstant.OneMinSTForVideoPlay);
							WebElement videoPage = driver.findElement(By
									.xpath(".//*[@id='content-video']"));
							actions.moveToElement(videoPage);
							actions.perform();

							// Lekshmi : Incorporates the Now Playing logic in
							// the script.

							boolean nowPlaying = driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEONOWPLAYINGOPOUP_XPATH))
									.isDisplayed();
							System.out.println("NOW PLAYING....." + nowPlaying);

							if (nowPlaying) {
								String getVideoPlayState = driver
										.findElement(
												By.xpath(XpathObjectRepo.VIDEOSTARTPOINTBUTTON_XPATH))
										.getText();
								assertEquals(
										UILablesRepo.VIDEOPLAYFROMSTARTBUTTON_TEXT,
										getVideoPlayState);
								// This method is to assert Up Next Header
								assertionFunction.assertUpNextTitle();
								break;
							} else {
								String getVideoPlayState = driver
										.findElement(
												By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
										.getAttribute("class");
								assertEquals(
										UILablesRepo.VIDEOPAUSEBUTTON_TEXT,
										getVideoPlayState);
							}

							// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+featuredVideoList.get(XidioConstant.selectVideo).getTitle()+"[\\s\\S]*$"));
							assertTrue(driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEODETAILSPAGETITLE_XPATH))
									.getText()
									.equalsIgnoreCase(
											featuredVideoList.get(
													XidioConstant.selectVideo)
													.getTitle()));

							// This method is to assert Up Next Header
							assertionFunction.assertUpNextTitle();
						}
					} else {
						for (int j = 0; j < 5; j++) {
							Thread.sleep(XidioConstant.OneMinSTForVideoPlay);
							WebElement videoPage = driver.findElement(By
									.xpath(".//*[@id='content-video']"));
							actions.moveToElement(videoPage);
							actions.perform();

							// Lekshmi : Incorporates the Now Playing logic in
							// the script.

							boolean nowPlaying = driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEONOWPLAYINGOPOUP_XPATH))
									.isDisplayed();
							System.out.println("NOW PLAYING....." + nowPlaying);

							if (nowPlaying) {
								String getVideoPlayState = driver
										.findElement(
												By.xpath(XpathObjectRepo.VIDEOSTARTPOINTBUTTON_XPATH))
										.getText();
								assertEquals(
										UILablesRepo.VIDEOPLAYFROMSTARTBUTTON_TEXT,
										getVideoPlayState);
								// This method is to assert Up Next Header
								assertionFunction.assertUpNextTitle();
								break;
							} else {
								String getVideoPlayState = driver
										.findElement(
												By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
										.getAttribute("class");
								assertEquals(
										UILablesRepo.VIDEOPAUSEBUTTON_TEXT,
										getVideoPlayState);
							}

							// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+featuredVideoList.get(XidioConstant.selectVideo).getTitle()+"[\\s\\S]*$"));
							assertTrue(driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEODETAILSPAGETITLE_XPATH))
									.getText()
									.equalsIgnoreCase(
											featuredVideoList.get(
													XidioConstant.selectVideo)
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
