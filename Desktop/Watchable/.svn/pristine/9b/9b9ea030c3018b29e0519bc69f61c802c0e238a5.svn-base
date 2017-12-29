package comcast.test.app.testCases.videoManagement.videoHomeManagement.PopularChannelsCategoryTestCases.PopularChannelCategoryPageTestCases;

import static org.junit.Assert.assertTrue;

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
 * Class Name: VerifyPopularListHasOnlyEpisodesUnderChannels Description: This
 * test case verify Popular Channels which contains Episodes directly under
 * channels by logging registered user into Watchable application.
 * **/

public class VerifyPopularListHasOnlyEpisodesUnderChannels extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	String pageTitle = "";

	@Test
	public void testVerifyPopularListHasOnlyEpisodesUnderChannels()
			throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.popularChannelsHasShowsOrEpisodes();
		List<VideoDetails> popularChannelsList = videoDetails
				.get("popularChannelHasOnlyEpisodes");

		String sessionToken = RestAPIServices.executeGenreAuthentication();

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */

			// Login is not required to run this test case
			// RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);
			pageTitle = driver.getTitle();
			if (pageTitle.contains(UILablesRepo.HOMEPAGE_TITLE)) {
				log.info("Successfully Opened the application");
				// This method is to ensure Home is Active page when Login into
				// Application.
				assertionFunction.assertHomeActiveLink();

				// This method is to assert Gazeebo Top Middle Menu and to
				// ensure
				// its collapsed.
				// assertionFunction.assertGazeeboTopMiddleMenu();

				int loopMaxIndex = 0;
				if (popularChannelsList != null) {
					// This method is to scroll UI to Popular Channels Section.
					homePageCommonFun.scrollToPopularChannelsSection();

					if (popularChannelsList.size() < 5)
						loopMaxIndex = popularChannelsList.size();
					else
						loopMaxIndex = 5;
					for (int index = 0; index < loopMaxIndex; index++) {
						VideoDetails channelList = popularChannelsList
								.get(index);
						// This Method selects and returns all the channels
						// contains
						// episode directly under it.
						boolean isPresent;
						do {

							// Manoj: Objects modified and added to
							// XpathObjectRepo
							// file
							/*
							 * isPresent = driver .findElement(
							 * By.xpath(orProUtil
							 * .getProperty("POPULAR_CHANNELS_SECTION")))
							 * .findElements( By.partialLinkText(channelList
							 * .getTitle())).size() > 0;
							 */

							isPresent = driver
									.findElement(
											By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSROW_XPATH))
									.findElements(
											By.partialLinkText(channelList
													.getTitle())).size() > 0;

							if (isPresent == true) {
								/*
								 * assertTrue(driver
								 * .findElement(By.cssSelector("BODY"))
								 * .getText() .matches( "^[\\s\\S]*" +
								 * channelList.getTitle() + "[\\s\\S]*$"));
								 */
								/*
								 * driver.findElement( By.xpath(orProUtil
								 * .getProperty("POPULAR_CHANNELS_SECTION")))
								 * .findElement( By.partialLinkText(channelList
								 * .getTitle())).click();
								 */

								driver.findElement(
										By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSROW_XPATH))
										.findElement(
												By.partialLinkText(channelList
														.getTitle())).click();

								String videoResponse = RestAPIServices
										.getSessionTokenResponse(
												UrlFactoryUtil
														.getInstance()
														.getVideoDetailsURL(
																channelList
																		.getId(),
																10),
												sessionToken);
								List<VideoDetails> popularVideoList = JsonParser
										.parseChannelShowsVideosResponse(videoResponse);

								// This Method is to navigate Last Updated
								// detail
								// page.
								// homePageCommonFun.clickOnLastUpdatedLink();
								if (popularVideoList.size() < 4)
									loopMaxIndex = popularVideoList.size();
								else
									loopMaxIndex = 4;

								for (int videos = 0; videos < loopMaxIndex; videos++) {
									VideoDetails videoList = popularVideoList
											.get(videos);

									/*
									 * assertTrue(driver
									 * .findElement(By.cssSelector("BODY"))
									 * .getText() .matches( "^[\\s\\S]*" +
									 * videoList.getTitle() + "[\\s\\S]*$"));
									 */

									assertTrue(driver
											.findElement(
													By.xpath(XpathObjectRepo.SHOWDETAILSHOWLIST_XPATH))
											.getText()
											.contains(videoList.getTitle()));

								}
							} else {
								String isNextEnable = driver
										.findElement(
												By.xpath(orProUtil
														.getProperty("POPULAR_SECTION_NEXTLINK")))
										.getAttribute("class");
								if (!isNextEnable
										.equalsIgnoreCase("next hidden")
										&& !isNextEnable
												.equalsIgnoreCase("next disabled")) {
									driver.findElement(
											By.xpath(orProUtil
													.getProperty("POPULAR_SECTION_NEXTLINK")))
											.click();
									Thread.sleep(sleepTime);
								} else {

									/*
									 * assertTrue(driver
									 * .findElement(By.cssSelector("BODY"))
									 * .getText() .matches( "^[\\s\\S]*" +
									 * channelList .getTitle() + "[\\s\\S]*$"));
									 */

									assertTrue(driver
											.findElement(
													By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSROW_XPATH))
											.getText()
											.contains(channelList.getTitle()));
									break;
								}
							}

							driver.navigate().back();
							Thread.sleep(sleepTime);
						} while (isPresent == false);
					}
				}
				/*
				 * else { boolean isPresent;
				 * isPresent=driver.findElement(By.xpath(
				 * ".//*[@id='popular_channels']/div/section/div/div/ul"
				 * )).findElements(By.xpath(
				 * ".//*[@id='popular_channels']/div/section/div/div/ul/li[1]/article/h1/a"
				 * )).size()>0; if(isPresent==true) { String
				 * channelName=driver.findElement
				 * (By.xpath(".//*[@id='popular_channels']/div/section/div/div/ul"
				 * )).findElement(By.xpath(
				 * ".//*[@id='popular_channels']/div/section/div/div/ul/li[1]/article/h1/a"
				 * )).getText();
				 * assertFalse(driver.findElement(By.cssSelector("BODY"
				 * )).getText().matches("^[\\s\\S]*"+channelName+"[\\s\\S]*$"));
				 * } }
				 */

				// This method asserts Watchable Logo.
				assertionFunction.assertWatchableLogo();
			}
			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			if (pageTitle.contains(UILablesRepo.HOMEPAGE_TITLE)) {
				captureScreenshot();
				collector.addError(t);
			}
		}
	}
}
