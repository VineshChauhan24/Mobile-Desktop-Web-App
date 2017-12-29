package comcast.test.app.testCases.videoManagement.videoHomeManagement.PopularChannelsCategoryTestCases.PopularChannelCategoryPageTestCases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

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
 * Class Name: VerifyHomePopularChannelsWithItsDetails Description: This test
 * case is to verify Home Popular Channels category channels are displayed on UI
 * with its details by comparing with Popular API Response for registered
 * Watchable Application user.
 * **/

public class VerifyHomePopularChannelsWithItsDetails extends BaseTest {

	// Manoj: Refactoring done.
	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	String pageTitle = "";

	@Test
	public void testVerifyHomePopularChannelsWithItsDetails() throws Exception {
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nPopularAPI();
		List<VideoDetails> popularChannelsList = videoDetails
				.get("popularChannelsList");
		String sessionToken = RestAPIServices.executeGenreAuthentication();

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
			if (pageTitle.contains(UILablesRepo.HOMEPAGE_TITLE)) {
				log.info("Successfully Opened the application");

				// This method is to ensure Home is Active page when Login into
				// Application.
				assertionFunction.assertHomeActiveLink();

				// This method is to assert Gazeebo Top Middle Menu and to
				// ensure
				// its collapsed.
				// assertionFunction.assertGazeeboTopMiddleMenu();

				// This method asserts Popular Channels title.
				assertionFunction.assertPopularChannelsTitle();
				int loopMaxIndex = 0;
				if (popularChannelsList != null) {
					// This method is to scroll UI to Popular Channels Section.
					homePageCommonFun.scrollToPopularChannelsSection();

					if (popularChannelsList.size() < 2)
						loopMaxIndex = popularChannelsList.size();
					else
						loopMaxIndex = 2;

					for (int index = 0; index < loopMaxIndex; index++) {
						VideoDetails channelList = popularChannelsList
								.get(index);
						boolean isChannelPresent;
						do {
							// Manoj: Object changed and moved to
							// XpathObjectRepo
							// file
							// isChannelPresent=driver.findElement(By.xpath("//*[@id='popular_channels']/div/section")).findElements(By.linkText(channelList.getTitle())).size()>0;

							isChannelPresent = driver
									.findElement(
											By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSROW_XPATH))
									.findElements(
											By.linkText(channelList.getTitle()))
									.size() > 0;
							if (isChannelPresent == true) {
								// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+channelList.getTitle()+"[\\s\\S]*$"));

								assertTrue(driver
										.findElement(
												By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSROW_XPATH))
										.getText()
										.contains(channelList.getTitle()));

								// driver.findElement(By.xpath("//*[@id='popular_channels']/div/section")).findElement(By.linkText(channelList.getTitle())).click();
								driver.findElement(
										By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSROW_XPATH))
										.findElement(
												By.linkText(channelList
														.getTitle())).click();
								Thread.sleep(sleepTime);

								// Verify Shows and click on it.
								String ChannelsShowsResponse = RestAPIServices
										.getSessionTokenResponse(
												UrlFactoryUtil
														.getInstance()
														.getSubShowURL(
																channelList
																		.getId()),
												sessionToken);
								List<VideoDetails> popularSubShowsList = JsonParser
										.parseShowsResponse(ChannelsShowsResponse);

								if (popularSubShowsList != null) {

									if (popularSubShowsList.size() < 5) {
										loopMaxIndex = popularSubShowsList
												.size();
									} else {
										loopMaxIndex = 5;

									}

									for (int index2 = 0; index2 < loopMaxIndex - 1; index2++) {

										VideoDetails showList = popularSubShowsList
												.get(index2);
										Thread.sleep(sleepTime);
										boolean isShowPresent;
										do {
											isShowPresent = driver
													.findElements(
															By.linkText(showList
																	.getTitle()))
													.size() > 0;
											if (isShowPresent == true) {
												// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+showList.getTitle()+"[\\s\\S]*$"));
												assertTrue(driver
														.findElement(
																By.xpath(XpathObjectRepo.SHOWDETAILSHOWLIST_XPATH))
														.getText()
														.contains(
																showList.getTitle()));

												driver.findElement(
														By.linkText(showList
																.getTitle()))
														.click();

												Thread.sleep(sleepTime);
												// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+showList.getTitle()+"[\\s\\S]*$"));
												assertTrue(driver
														.findElement(
																By.xpath(XpathObjectRepo.SHOWDETAILSHOWTITLE_XPATH))
														.getText()
														.matches(
																showList.getTitle()));

												// Get Video Details and verify
												// all
												// response.
												String videoResponse = RestAPIServices
														.getSessionTokenResponse(
																UrlFactoryUtil
																		.getInstance()
																		.getVideoDetailsURL(
																				showList.getId(),
																				10),
																sessionToken);
												List<VideoDetails> popularVideoList = JsonParser
														.parseChannelShowsVideosResponse(videoResponse);

												int videoCount = driver
														.findElements(
																By.xpath("//div[@class='icon']"))
														.size();
												// System.out.println(videoCount);
												if (popularVideoList != null) {
													if (popularVideoList.size() < 5)
														loopMaxIndex = popularVideoList
																.size();
													else
														loopMaxIndex = 5;

													if (videoCount > 0) {
														for (int videoIndex = 0; videoIndex < loopMaxIndex; videoIndex++) {
															VideoDetails videoList = popularVideoList
																	.get(videoIndex);
															Thread.sleep(sleepTime);
															boolean isVideoPresent;
															do {
																isVideoPresent = driver
																		.findElements(
																				By.linkText(videoList
																						.getTitle()))
																		.size() > 0;
																if (isVideoPresent == true) {

																	// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videoList.getTitle()+"[\\s\\S]*$"));

																	assertTrue(driver
																			.findElement(
																					By.xpath(XpathObjectRepo.SHOWDETAILVIDEOLIST_XPATH))
																			.getText()
																			.contains(
																					videoList
																							.getTitle()));
																}

															} while (isVideoPresent == false);
														}
													}
													driver.navigate().back();
													Thread.sleep(sleepTime);
												} else {
													break;
												}
											}

										} while (isShowPresent == false);
									}
									driver.navigate().back();
									Thread.sleep(sleepTime);
								}

							} else {
								// String
								// isNextEnable=driver.findElement(By.xpath("//*[@id='popular_channels']/div/section/a[2]")).getAttribute("class");
								String isNextEnable = driver
										.findElement(
												By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSBUTTONNEXT_XPATH))
										.getAttribute("class");
								if (!isNextEnable
										.equalsIgnoreCase("next hidden")
										&& !isNextEnable
												.equalsIgnoreCase("next disabled")) {
									for (int i = 0; i < 6; i++) {
										driver.findElement(By.tagName("body"))
												.sendKeys(Keys.DOWN);
										Thread.sleep(1000);
									}
									driver.findElement(
											By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSBUTTONNEXT_XPATH))
											.click();
									Thread.sleep(sleepTime);
								} else {
									// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+channelList.getTitle()+"[\\s\\S]*$"));
									assertTrue(driver
											.findElement(
													By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSROW_XPATH))
											.getText()
											.contains(channelList.getTitle()));
									break;
								}
							}
						} while (isChannelPresent == false);
					}
				} else {
					boolean isPresent;
					// isPresent=driver.findElement(By.xpath(".//*[@id='popular_channels']/div/section/div/div/ul")).findElements(By.xpath(".//*[@id='popular_channels']/div/section/div/div/ul/li[1]/article/h1/a")).size()>0;
					isPresent = driver
							.findElement(
									By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSROW_XPATH))
							.findElements(
									By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSFIRSTTITLELABEL_XPATH))
							.size() > 0;
					if (isPresent == true) {
						// String
						// channelName=driver.findElement(By.xpath(".//*[@id='popular_channels']/div/section/div/div/ul")).findElement(By.xpath(".//*[@id='popular_channels']/div/section/div/div/ul/li[1]/article/h1/a")).getText();
						String channelName = driver
								.findElement(
										By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSROW_XPATH))
								.findElement(
										By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSFIRSTTITLELABEL_XPATH))
								.getText();
						assertFalse(driver
								.findElement(
										By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSROW_XPATH))
								.getText().contains(channelName));
					}
				}

				// This method is used to logout from Watchable Application.
				// userLogin.LogOut(driver);

				// This method is to ensure Login page is displayed when user
				// Sign
				// Out from Application. o0
				assertionFunction.assertLoginPageDetails();
			}
		} catch (Throwable t) {
			if (pageTitle.contains(UILablesRepo.HOMEPAGE_TITLE)) {
				captureScreenshot();
				collector.addError(t);
			}
		}
	}
}
