package comcast.test.app.testCases.videoManagement.videoPlay.HomeCategoryVideos.PopularShowsVideo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
 * Class Name: PlayHomePopularShowsVideoFromList Description: This test case is
 * to play the video by selecting the shows present under 'Popular Shows'
 * section in Home page.
 * **/

public class PlayHomePopularShowsVideoFromList extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testPlayHomePopularShowsVideoFromList() throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nHomePopularShowsAPIs();
		List<VideoDetails> showList = videoDetails.get("popularShows");
		List<VideoDetails> videoList = videoDetails.get("popularvideos");

		Actions actions = new Actions(driver);
		int durationInSeconds = 0;
		int durationInMins = 0;

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

				// This method asserts Home link to ensure Home is Active page
				// when
				// Login into Application.
				assertionFunction.assertHomeActiveLink();

				// This method asserts Popular Shows Header Title.
				assertionFunction.assertPopularShowsTitle();

				if (showList != null) {
					// This method is to scroll UI to Popular Channels Section.
					homePageCommonFun.scrollToPopularShowsSection();

					// This Method verifies Shows present in Popular Shows
					// Section
					// and selects a Show.
					homePageCommonFun.selectPopularShows(showList.get(
							XidioConstant.selectShow).getTitle());

					// Lekshmi : Modified the link with object identifiers.

					// driver.findElement(By.linkText(videoList.get(XidioConstant.selectVideo).getTitle())).click();

					// Manoj: Object changed during Re-Working
					// driver.findElement(By.xpath(orProUtil.getProperty("showEpisodeList_XPATH"))).click();

					driver.findElement(
							By.xpath(XpathObjectRepo.SHOWEPISODELIST_XPATH))
							.click();

					durationInSeconds = videoList
							.get(XidioConstant.selectVideo).getDuration();
					durationInMins = durationInSeconds / 60;

					// Manoj: Checking Selected video is partially played
					Thread.sleep(sleepTime);
					WebElement playFromPresent = driver.findElement(By
							.xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH));

					Thread.sleep(sleepTime);
					Thread.sleep(sleepTime);

					if (playFromPresent.isDisplayed() == true) {
						Thread.sleep(sleepTime);
						Thread.sleep(sleepTime);
						driver.findElement(
								By.xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH))
								.click();
						Thread.sleep(sleepTime);
						Thread.sleep(sleepTime);
					}
					Thread.sleep(sleepTime);
					if (durationInMins < 5) {
						for (int i = 0; i < durationInMins; i++) {
							Thread.sleep(XidioConstant.OneMinSTForVideoPlay);

							// Manoj: Object changed during Re-Working
							/*
							 * WebElement videoPage = driver.findElement(By
							 * .xpath(orProUtil
							 * .getProperty("videoContentDetailsLabel_XPATH")));
							 */
							WebElement videoPage = driver
									.findElement(By
											.xpath(XpathObjectRepo.VIDEODETAILSPAGE_XPATH));

							actions.moveToElement(videoPage);
							actions.perform();

							// Manoj: Object changed during Re-Working
							// Lekshmi : Change the object identifier.

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

							// Lekshmi : Change the object identifier to fetch
							// the
							// video title.
							// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videoList.get(XidioConstant.selectVideo).getTitle()+"[\\s\\S]*$"));

							// Manoj: Object changed during Re-Working

							assertTrue(driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEODETAILSPAGETITLE_XPATH))
									.getText()
									.equalsIgnoreCase(
											videoList.get(
													XidioConstant.selectVideo)
													.getTitle()));

							// This method is to assert Up Next Header
							assertionFunction.assertUpNextTitle();
						}
					} else {
						for (int j = 0; j < 5; j++) {
							Thread.sleep(XidioConstant.OneMinSTForVideoPlay);
							// Manoj: Object changed during Re-Working
							/*
							 * WebElement videoPage = driver.findElement(By
							 * .xpath(orProUtil
							 * .getProperty("videoContentDetailsLabel_XPATH")));
							 */

							WebElement videoPage = driver
									.findElement(By
											.xpath(XpathObjectRepo.VIDEODETAILSPAGE_XPATH));
							actions.moveToElement(videoPage);
							actions.perform();

							// Manoj: Object changed during Re-Working
							// String getVideoPlayState = driver.findElement(
							// By.id(orProUtil.getProperty("videoPlayStateBtn_ID"))).getAttribute("class");
							// assertEquals("play", getVideoPlayState);

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

							// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videoList.get(XidioConstant.selectVideo).getTitle()+"[\\s\\S]*$"));

							assertTrue(driver
									.findElement(
											By.xpath(XpathObjectRepo.VIDEODETAILSPAGETITLE_XPATH))
									.getText()
									.equalsIgnoreCase(
											videoList.get(
													XidioConstant.selectVideo)
													.getTitle()));

							// This method is to assert Up Next Header
							assertionFunction.assertUpNextTitle();
						}
					}
				}

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
