/**
 * @Author Manoj.P
 * @Created Date: 16 DEC 2014
 * @Last Updated Date: 22 DEC 2014
 */

package comcast.test.app.testCases.videoManagement.videoPlay.videoStreamingTestCases;

import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.constant.XidioConstant;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifySocialMediaTweetWithLogin Description: This test case is to
 * verify Social Media Tweet functionality in video player by logging into
 * Watchable application.
 * **/

public class VerifySocialMediaTweetWithLogin extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifySocialMediaTweetWithLogin() throws Exception {

		// Map <String,List<VideoDetails>>
		// videoDetails=RestAPIServices.featuredSectionVideos();
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.featuredSectionVideos();
		List<VideoDetails> videoList = videoDetails.get("featuredVideoList");

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
				log.info("Successfully logged in to the application");

				// This method is to ensure Home is Active page when Login into
				// Application.
				assertionFunction.assertHomeActiveLink();

				if (videoList != null) {

					log.info("Videos present in featured section");
					// Selecting first video from the list
					driver.findElement(
							By.xpath(XpathObjectRepo.FEATUREDICONSFIRSTTITLE_XPATH))
							.click();
					log.info("Selected video from featured list");
					Thread.sleep(XidioConstant.halfMin);

					Thread.sleep(sleepTime);
					Thread.sleep(sleepTime);
					Thread.sleep(sleepTime);

					// Checking Selected video is partially played
					Thread.sleep(sleepTime);

					WebElement playFromPresent = driver.findElement(By
							.xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH));

					if (playFromPresent.isDisplayed() == true) {
						// Thread.sleep(sleepTime);
						// Thread.sleep(sleepTime);
						driver.findElement(
								By.xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH))
								.click();
						Thread.sleep(sleepTime);
						Thread.sleep(sleepTime);
					}

					Thread.sleep(sleepTime);
					Thread.sleep(sleepTime);
					Thread.sleep(sleepTime);
					String videoUrl = driver.getCurrentUrl();
					log.info("Currently playing video: " + videoUrl);
					homePageCommonFun.scrollToSocilaMediaSection();
					driver.switchTo().frame(0);

					boolean isTweetPresent = false;
					isTweetPresent = CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.TWEET_BUTTON_XPATH));

					if (isTweetPresent == true) {
						log.info("Tweet funtcionality present in Video screen");

						assertTrue(
								"Tweet button is NOT enabled",
								driver.findElement(
										By.xpath(XpathObjectRepo.TWEET_BUTTON_XPATH))
										.isEnabled());
						log.info("Tweet button is enabled");
						driver.findElement(
								By.xpath(XpathObjectRepo.TWEET_BUTTON_XPATH))
								.click();
						Thread.sleep(sleepTime);

						String parentWindow = driver.getWindowHandle();
						Set<String> handles = driver.getWindowHandles();

						for (String windowHandle : handles) {
							if (!windowHandle.equals(parentWindow)) {
								driver.switchTo().window(windowHandle);

								Thread.sleep(sleepTime);
								log.info("Share a link on Twitter popup window opened after clicking on Tweet button");
								String twitterVideoUrl = driver
										.findElement(
												By.xpath(XpathObjectRepo.TWEET_VIDEO_URL_TXT_XPATH))
										.getText();
								if (twitterVideoUrl.trim().length() > 0) {
									log.info("Text present in share text box in Twitter pop up window");
									log.info("Share text box content : "
											+ twitterVideoUrl);

									assertTrue(
											"Text present in share text box in Twitter pop up window NOT contains currently playing video URL",
											twitterVideoUrl.contains(videoUrl));
									log.info("Text present in share text box in Twitter pop up window contains currently playing video URL");

								} else {
									log.error("No text present in share text box in Twitter pop up window");
								}

								driver.close(); // closing child window
								driver.switchTo().window(parentWindow); // Control
																		// to
																		// parent
																		// window

							}

						}

					} else {
						log.error("Tweet funcionality not present in Video screen");

						Thread.sleep(sleepTime);
					}

				}

				else {
					log.error("Video not present in featured section");
				}

				driver.switchTo().defaultContent();

				// This method is used to logout from Watchable Application.
				userLogin.LogOut(driver);
			}
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}

	}
}
