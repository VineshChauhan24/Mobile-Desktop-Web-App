package comcast.test.app.testCases.videoManagement.videoHomeManagement.FeaturedCategoryTestCases.FeaturedCategoryPageTestCases;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.DomParserXPATH;
import comcast.test.config.configServices.utils.JsonParser;
import comcast.test.config.configServices.utils.UrlFactoryUtil;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyHomeFeaturedAllBundlesWithItsDetails Description: This test
 * case is to validate Home/Featured category all bundle with its details is
 * available and clickable, by comparing with API Response for e.x. all the
 * channel in a bundle, all the Shows in a bundle, all the videos in a bundle
 * for registered Gazeebo Application user.
 * **/

public class VerifyHomeFeaturedAllBundlesWithItsDetails extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testVerifyHomeFeaturedAllBundlesWithItsDetails()
			throws Exception {

		Map<String, List<VideoDetails>> videoDetails1 = RestAPIServices
				.FeaturedAPI();
		List<VideoDetails> featuredBundlesList = videoDetails1
				.get("featuredBundleList");

		List<VideoDetails> featuredShowsListUnderBundle = new ArrayList<VideoDetails>();
		List<VideoDetails> featuredBundleChannelsSubShowsList = new ArrayList<VideoDetails>();
		List<VideoDetails> storeFeaturedVideoListUnderChannel = new ArrayList<VideoDetails>();

		try {
			/*
			 * This method is to register new user using Gazeebo application and
			 * to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			Thread.sleep(sleepTime);
			assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
					.matches("^[\\s\\S]*Featured[\\s\\S]*$"));

			if (featuredBundlesList != null && featuredBundlesList.size() < 3) {
				for (VideoDetails bundleList : featuredBundlesList) {
					System.out.println("featuredBundlesList<<<<<<<<"
							+ bundleList.getTitle());
					Thread.sleep(sleepTime);
					boolean isbundlePresent;
					do {
						isbundlePresent = driver.findElements(
								By.xpath(".//*[@id='featured']/div/section")
										.linkText(bundleList.getTitle()))
								.size() > 0;
						if (isbundlePresent == true) {
							assertTrue(driver
									.findElement(By.cssSelector("BODY"))
									.getText()
									.matches(
											"^[\\s\\S]*"
													+ bundleList.getTitle()
													+ "[\\s\\S]*$"));
							driver.findElement(
									By.xpath(".//*[@id='featured']/div/section")
											.linkText(bundleList.getTitle()))
									.click();

							Thread.sleep(sleepTime);
							assertTrue(driver
									.findElement(By.cssSelector("BODY"))
									.getText()
									.matches(
											"^[\\s\\S]*"
													+ bundleList.getTitle()
													+ "[\\s\\S]*$"));

							// Script is to take bundle channel
							String BundleShowsResponse = DomParserXPATH
									.getCategories(UrlFactoryUtil.getInstance()
											.getBundleChannelsURL(
													bundleList.getId(), 12));
							List<VideoDetails> FeaturedShowsListUnderBundle = JsonParser
									.parseHomeFeaturedShowsResponse(BundleShowsResponse);

							if (FeaturedShowsListUnderBundle != null
									&& FeaturedShowsListUnderBundle.size() <= 5) {
								for (VideoDetails channelList : FeaturedShowsListUnderBundle) {
									if (channelList.getLevel() != null
											&& channelList.getLevel()
													.equalsIgnoreCase("SHOW")) {
										int showCount = 0;
										String showCountResponse = DomParserXPATH
												.getCategories(UrlFactoryUtil
														.getInstance()
														.getSubShowURL(
																channelList
																		.getId()));
										if (showCountResponse != null
												&& showCountResponse.length() > 0)
											showCount = Integer
													.parseInt(JsonParser
															.parseHomeFeaturedShowCountResponse(showCountResponse));
										if (showCount > 0)
											featuredShowsListUnderBundle
													.add(channelList);
										System.out
												.println("Bundle Channels List:>>"
														+ channelList
																.getTitle());

										// Verify Channels and click on it.
										Thread.sleep(sleepTime);
										boolean isChannelPresent;
										do {
											isChannelPresent = driver
													.findElements(
															By.linkText(channelList
																	.getTitle()))
													.size() > 0;
											if (isChannelPresent == true) {
												assertTrue(driver
														.findElement(
																By.cssSelector("BODY"))
														.getText()
														.matches(
																"^[\\s\\S]*"
																		+ channelList
																				.getTitle()
																		+ "[\\s\\S]*$"));
												driver.findElement(
														By.linkText(channelList
																.getTitle()))
														.click();

												Thread.sleep(sleepTime);
												assertTrue(driver
														.findElement(
																By.cssSelector("BODY"))
														.getText()
														.matches(
																"^[\\s\\S]*"
																		+ channelList
																				.getTitle()
																		+ "[\\s\\S]*$"));

												// Verify Shows and click on it.
												String bundleChannelsSubShowResponse = DomParserXPATH
														.getCategories(UrlFactoryUtil
																.getInstance()
																.getSubShowURL(
																		channelList
																				.getId()));
												List<VideoDetails> featuredBundleChannelSubShowsList = JsonParser
														.parseHomeFeaturedShowsResponse(bundleChannelsSubShowResponse);

												if (featuredBundleChannelSubShowsList != null
														&& featuredBundleChannelSubShowsList
																.size() < 5) {
													for (VideoDetails showList : featuredBundleChannelSubShowsList) {
														featuredBundleChannelsSubShowsList
																.add(showList);
														System.out
																.println("Bundle Sub Shows List:>>"
																		+ showList
																				.getTitle());

														System.out
																.println("ShowsListUnderBundleSHOWS<<<<<<<<"
																		+ showList
																				.getTitle());
														Thread.sleep(sleepTime);
														boolean isShowPresent;
														do {
															isShowPresent = driver
																	.findElements(
																			By.linkText(showList
																					.getTitle()))
																	.size() > 0;
															if (isShowPresent == true) {
																assertTrue(driver
																		.findElement(
																				By.cssSelector("BODY"))
																		.getText()
																		.matches(
																				"^[\\s\\S]*"
																						+ showList
																								.getTitle()
																						+ "[\\s\\S]*$"));
																driver.findElement(
																		By.linkText(showList
																				.getTitle()))
																		.click();

																Thread.sleep(sleepTime);
																assertTrue(driver
																		.findElement(
																				By.cssSelector("BODY"))
																		.getText()
																		.matches(
																				"^[\\s\\S]*"
																						+ showList
																								.getTitle()
																						+ "[\\s\\S]*$"));

																// Get Video
																// Details and
																// verify all
																// response.
																String videoResponse = DomParserXPATH
																		.getCategories(UrlFactoryUtil
																				.getInstance()
																				.getVideoDetailsURL(
																						showList.getId(),
																						12));
																List<VideoDetails> storeFeaturedVideoList = JsonParser
																		.parseHomeFeaturedVideosResponse(videoResponse);

																if (storeFeaturedVideoList != null
																		&& storeFeaturedVideoList
																				.size() < 5) {
																	for (VideoDetails videoList : storeFeaturedVideoList) {
																		storeFeaturedVideoListUnderChannel
																				.add(videoList);
																		System.out
																				.println("Bundle Sub Shows Video List:>>"
																						+ videoList
																								.getTitle());
																		System.out
																				.println("VideoListUnderBundleRows<<<<<<<<"
																						+ videoList
																								.getTitle());
																		Thread.sleep(sleepTime);
																		boolean isVideoPresent;
																		do {
																			isVideoPresent = driver
																					.findElements(
																							By.linkText(videoList
																									.getTitle()))
																					.size() > 0;
																			if (isVideoPresent == true) {
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));
																				driver.findElement(
																						By.linkText(videoList
																								.getTitle()))
																						.click();

																				Thread.sleep(sleepTime);
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));

																				driver.navigate()
																						.back();
																				Thread.sleep(sleepTime);
																			}
																		} while (isVideoPresent == false);
																	}
																} else {
																	for (int index = 0; index < 5; index++) {
																		VideoDetails videoList = storeFeaturedVideoList
																				.get(index);
																		storeFeaturedVideoListUnderChannel
																				.add(videoList);
																		System.out
																				.println("VideoListUnderBundleRows<<<<<<<<"
																						+ videoList
																								.getTitle());
																		Thread.sleep(sleepTime);
																		boolean isVideoPresent;
																		do {
																			isVideoPresent = driver
																					.findElements(
																							By.linkText(videoList
																									.getTitle()))
																					.size() > 0;
																			if (isVideoPresent == true) {
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));
																				driver.findElement(
																						By.linkText(videoList
																								.getTitle()))
																						.click();

																				Thread.sleep(sleepTime);
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));

																				driver.navigate()
																						.back();
																				Thread.sleep(sleepTime);
																			}
																		} while (isVideoPresent == false);
																	}
																}
																driver.navigate()
																		.back();
																Thread.sleep(sleepTime);
															}
														} while (isShowPresent == false);
													}
												} else {
													for (int index = 0; index < 5; index++) {
														VideoDetails showList = featuredBundleChannelSubShowsList
																.get(index);
														featuredBundleChannelsSubShowsList
																.add(showList);
														System.out
																.println("ShowsListUnderBundleSHOWS<<<<<<<<"
																		+ showList
																				.getTitle());
														Thread.sleep(sleepTime);
														boolean isShowPresent;
														do {
															isShowPresent = driver
																	.findElements(
																			By.linkText(showList
																					.getTitle()))
																	.size() > 0;
															if (isShowPresent == true) {
																assertTrue(driver
																		.findElement(
																				By.cssSelector("BODY"))
																		.getText()
																		.matches(
																				"^[\\s\\S]*"
																						+ showList
																								.getTitle()
																						+ "[\\s\\S]*$"));
																driver.findElement(
																		By.linkText(showList
																				.getTitle()))
																		.click();

																Thread.sleep(sleepTime);
																assertTrue(driver
																		.findElement(
																				By.cssSelector("BODY"))
																		.getText()
																		.matches(
																				"^[\\s\\S]*"
																						+ showList
																								.getTitle()
																						+ "[\\s\\S]*$"));

																// Get Video
																// Details and
																// verify all
																// response.
																String videoResponse = DomParserXPATH
																		.getCategories(UrlFactoryUtil
																				.getInstance()
																				.getVideoDetailsURL(
																						showList.getId(),
																						12));
																List<VideoDetails> storeFeaturedVideoList = JsonParser
																		.parseHomeFeaturedVideosResponse(videoResponse);

																if (storeFeaturedVideoList != null
																		&& storeFeaturedVideoList
																				.size() < 5) {
																	for (VideoDetails videoList : storeFeaturedVideoList) {
																		storeFeaturedVideoListUnderChannel
																				.add(videoList);
																		System.out
																				.println("Bundle Sub Shows Video List:>>"
																						+ videoList
																								.getTitle());
																		System.out
																				.println("VideoListUnderBundleRows<<<<<<<<"
																						+ videoList
																								.getTitle());
																		Thread.sleep(sleepTime);
																		boolean isVideoPresent;
																		do {
																			isVideoPresent = driver
																					.findElements(
																							By.linkText(videoList
																									.getTitle()))
																					.size() > 0;
																			if (isVideoPresent == true) {
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));
																				driver.findElement(
																						By.linkText(videoList
																								.getTitle()))
																						.click();

																				Thread.sleep(sleepTime);
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));

																				driver.navigate()
																						.back();
																				Thread.sleep(sleepTime);
																			}
																		} while (isVideoPresent == false);
																	}
																} else {
																	for (int videoIndex = 0; videoIndex < 5; videoIndex++) {
																		VideoDetails videoList = storeFeaturedVideoList
																				.get(videoIndex);
																		storeFeaturedVideoListUnderChannel
																				.add(videoList);
																		System.out
																				.println("VideoListUnderBundleRows<<<<<<<<"
																						+ videoList
																								.getTitle());
																		Thread.sleep(sleepTime);
																		boolean isVideoPresent;
																		do {
																			isVideoPresent = driver
																					.findElements(
																							By.linkText(videoList
																									.getTitle()))
																					.size() > 0;
																			if (isVideoPresent == true) {
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));
																				driver.findElement(
																						By.linkText(videoList
																								.getTitle()))
																						.click();

																				Thread.sleep(sleepTime);
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));

																				driver.navigate()
																						.back();
																				Thread.sleep(sleepTime);
																			}
																		} while (isVideoPresent == false);
																	}
																}
																driver.navigate()
																		.back();
																Thread.sleep(sleepTime);
															}
														} while (isShowPresent == false);

													}
												}
												driver.navigate().back();
												Thread.sleep(sleepTime);

											}
										} while (isChannelPresent == false);
									}
								}
								driver.navigate().back();
								Thread.sleep(sleepTime);
							} else {
								for (int index = 0; index < 5; index++) {
									VideoDetails channelList = FeaturedShowsListUnderBundle
											.get(index);

									if (channelList.getLevel() != null
											&& channelList.getLevel()
													.equalsIgnoreCase("SHOW")) {

										int showCount = 0;
										String showCountResponse = DomParserXPATH
												.getCategories(UrlFactoryUtil
														.getInstance()
														.getSubShowURL(
																channelList
																		.getId()));
										if (showCountResponse != null
												&& showCountResponse.length() > 0)
											showCount = Integer
													.parseInt(JsonParser
															.parseHomeFeaturedShowCountResponse(showCountResponse));
										if (showCount > 0)
											featuredShowsListUnderBundle
													.add(channelList);
										System.out
												.println("Bundle Channels List:>>"
														+ channelList
																.getTitle());

										// Verify Channels and click on it.
										Thread.sleep(sleepTime);
										boolean isChannelPresent;
										do {
											isChannelPresent = driver
													.findElements(
															By.linkText(channelList
																	.getTitle()))
													.size() > 0;
											if (isChannelPresent == true) {
												assertTrue(driver
														.findElement(
																By.cssSelector("BODY"))
														.getText()
														.matches(
																"^[\\s\\S]*"
																		+ channelList
																				.getTitle()
																		+ "[\\s\\S]*$"));
												driver.findElement(
														By.linkText(channelList
																.getTitle()))
														.click();

												Thread.sleep(sleepTime);
												assertTrue(driver
														.findElement(
																By.cssSelector("BODY"))
														.getText()
														.matches(
																"^[\\s\\S]*"
																		+ channelList
																				.getTitle()
																		+ "[\\s\\S]*$"));

												// Verify Shows and click on it.
												String bundleChannelsSubShowResponse = DomParserXPATH
														.getCategories(UrlFactoryUtil
																.getInstance()
																.getSubShowURL(
																		channelList
																				.getId()));
												List<VideoDetails> featuredBundleChannelSubShowsList = JsonParser
														.parseHomeFeaturedShowsResponse(bundleChannelsSubShowResponse);

												if (featuredBundleChannelSubShowsList != null
														&& featuredBundleChannelSubShowsList
																.size() < 5) {
													for (VideoDetails showList : featuredBundleChannelSubShowsList) {
														featuredBundleChannelsSubShowsList
																.add(showList);
														System.out
																.println("Bundle Sub Shows List:>>"
																		+ showList
																				.getTitle());

														System.out
																.println("ShowsListUnderBundleSHOWS<<<<<<<<"
																		+ showList
																				.getTitle());
														Thread.sleep(sleepTime);
														boolean isShowPresent;
														do {
															isShowPresent = driver
																	.findElements(
																			By.linkText(showList
																					.getTitle()))
																	.size() > 0;
															if (isShowPresent == true) {
																assertTrue(driver
																		.findElement(
																				By.cssSelector("BODY"))
																		.getText()
																		.matches(
																				"^[\\s\\S]*"
																						+ showList
																								.getTitle()
																						+ "[\\s\\S]*$"));
																driver.findElement(
																		By.linkText(showList
																				.getTitle()))
																		.click();

																Thread.sleep(sleepTime);
																assertTrue(driver
																		.findElement(
																				By.cssSelector("BODY"))
																		.getText()
																		.matches(
																				"^[\\s\\S]*"
																						+ showList
																								.getTitle()
																						+ "[\\s\\S]*$"));

																// Get Video
																// Details and
																// verify all
																// response.
																String videoResponse = DomParserXPATH
																		.getCategories(UrlFactoryUtil
																				.getInstance()
																				.getVideoDetailsURL(
																						showList.getId(),
																						12));
																List<VideoDetails> storeFeaturedVideoList = JsonParser
																		.parseHomeFeaturedVideosResponse(videoResponse);

																if (storeFeaturedVideoList != null
																		&& storeFeaturedVideoList
																				.size() < 5) {
																	for (VideoDetails videoList : storeFeaturedVideoList) {
																		storeFeaturedVideoListUnderChannel
																				.add(videoList);
																		System.out
																				.println("Bundle Sub Shows Video List:>>"
																						+ videoList
																								.getTitle());
																		System.out
																				.println("VideoListUnderBundleRows<<<<<<<<"
																						+ videoList
																								.getTitle());
																		Thread.sleep(sleepTime);
																		boolean isVideoPresent;
																		do {
																			isVideoPresent = driver
																					.findElements(
																							By.linkText(videoList
																									.getTitle()))
																					.size() > 0;
																			if (isVideoPresent == true) {
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));
																				driver.findElement(
																						By.linkText(videoList
																								.getTitle()))
																						.click();

																				Thread.sleep(sleepTime);
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));

																				driver.navigate()
																						.back();
																				Thread.sleep(sleepTime);
																			}
																		} while (isVideoPresent == false);
																	}
																} else {
																	for (int videoIndex = 0; index < 5; index++) {
																		VideoDetails videoList = storeFeaturedVideoList
																				.get(videoIndex);
																		storeFeaturedVideoListUnderChannel
																				.add(videoList);
																		System.out
																				.println("VideoListUnderBundleRows<<<<<<<<"
																						+ videoList
																								.getTitle());
																		Thread.sleep(sleepTime);
																		boolean isVideoPresent;
																		do {
																			isVideoPresent = driver
																					.findElements(
																							By.linkText(videoList
																									.getTitle()))
																					.size() > 0;
																			if (isVideoPresent == true) {
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));
																				driver.findElement(
																						By.linkText(videoList
																								.getTitle()))
																						.click();

																				Thread.sleep(sleepTime);
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));

																				driver.navigate()
																						.back();
																				Thread.sleep(sleepTime);
																			}
																		} while (isVideoPresent == false);
																	}
																}
																driver.navigate()
																		.back();
																Thread.sleep(sleepTime);
															}
														} while (isShowPresent == false);
													}
												} else {
													for (int showIndex = 0; index < 5; index++) {
														VideoDetails showList = featuredBundleChannelSubShowsList
																.get(showIndex);
														featuredBundleChannelsSubShowsList
																.add(showList);
														System.out
																.println("ShowsListUnderBundleSHOWS<<<<<<<<"
																		+ showList
																				.getTitle());
														Thread.sleep(sleepTime);
														boolean isShowPresent;
														do {
															isShowPresent = driver
																	.findElements(
																			By.linkText(showList
																					.getTitle()))
																	.size() > 0;
															if (isShowPresent == true) {
																assertTrue(driver
																		.findElement(
																				By.cssSelector("BODY"))
																		.getText()
																		.matches(
																				"^[\\s\\S]*"
																						+ showList
																								.getTitle()
																						+ "[\\s\\S]*$"));
																driver.findElement(
																		By.linkText(showList
																				.getTitle()))
																		.click();

																Thread.sleep(sleepTime);
																assertTrue(driver
																		.findElement(
																				By.cssSelector("BODY"))
																		.getText()
																		.matches(
																				"^[\\s\\S]*"
																						+ showList
																								.getTitle()
																						+ "[\\s\\S]*$"));

																// Get Video
																// Details and
																// verify all
																// response.
																String videoResponse = DomParserXPATH
																		.getCategories(UrlFactoryUtil
																				.getInstance()
																				.getVideoDetailsURL(
																						showList.getId(),
																						12));
																List<VideoDetails> storeFeaturedVideoList = JsonParser
																		.parseHomeFeaturedVideosResponse(videoResponse);

																if (storeFeaturedVideoList != null
																		&& storeFeaturedVideoList
																				.size() < 5) {
																	for (VideoDetails videoList : storeFeaturedVideoList) {
																		storeFeaturedVideoListUnderChannel
																				.add(videoList);
																		System.out
																				.println("Bundle Sub Shows Video List:>>"
																						+ videoList
																								.getTitle());
																		System.out
																				.println("VideoListUnderBundleRows<<<<<<<<"
																						+ videoList
																								.getTitle());
																		Thread.sleep(sleepTime);
																		boolean isVideoPresent;
																		do {
																			isVideoPresent = driver
																					.findElements(
																							By.linkText(videoList
																									.getTitle()))
																					.size() > 0;
																			if (isVideoPresent == true) {
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));
																				driver.findElement(
																						By.linkText(videoList
																								.getTitle()))
																						.click();

																				Thread.sleep(sleepTime);
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));

																				driver.navigate()
																						.back();
																				Thread.sleep(sleepTime);
																			}
																		} while (isVideoPresent == false);
																	}
																} else {
																	for (int videoIndex = 0; videoIndex < 5; videoIndex++) {
																		VideoDetails videoList = storeFeaturedVideoList
																				.get(videoIndex);
																		storeFeaturedVideoListUnderChannel
																				.add(videoList);
																		System.out
																				.println("VideoListUnderBundleRows<<<<<<<<"
																						+ videoList
																								.getTitle());
																		Thread.sleep(sleepTime);
																		boolean isVideoPresent;
																		do {
																			isVideoPresent = driver
																					.findElements(
																							By.linkText(videoList
																									.getTitle()))
																					.size() > 0;
																			if (isVideoPresent == true) {
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));
																				driver.findElement(
																						By.linkText(videoList
																								.getTitle()))
																						.click();

																				Thread.sleep(sleepTime);
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));

																				driver.navigate()
																						.back();
																				Thread.sleep(sleepTime);
																			}
																		} while (isVideoPresent == false);
																	}
																}
																driver.navigate()
																		.back();
																Thread.sleep(sleepTime);
															}
														} while (isShowPresent == false);

													}
												}
												driver.navigate().back();
												Thread.sleep(sleepTime);

											}
										} while (isChannelPresent == false);
									}
								}
							}
							driver.navigate().back();
							Thread.sleep(sleepTime);
						} else {
							driver.findElement(
									By.xpath("//*[@id='featured']/div/section/a[2]/span"))
									.click();
							Thread.sleep(sleepTime);
						}
					} while (isbundlePresent == false);
				}
			} else {
				for (int bundles = 0; bundles < 2; bundles++) {
					VideoDetails bundleList = featuredBundlesList.get(bundles);

					System.out.println("featuredBundlesList<<<<<<<<"
							+ bundleList.getTitle());
					Thread.sleep(sleepTime);
					boolean isbundlePresent;
					do {
						isbundlePresent = driver.findElements(
								By.xpath(".//*[@id='featured']/div/section")
										.linkText(bundleList.getTitle()))
								.size() > 0;
						if (isbundlePresent == true) {
							assertTrue(driver
									.findElement(By.cssSelector("BODY"))
									.getText()
									.matches(
											"^[\\s\\S]*"
													+ bundleList.getTitle()
													+ "[\\s\\S]*$"));
							driver.findElement(
									By.xpath(".//*[@id='featured']/div/section")
											.linkText(bundleList.getTitle()))
									.click();

							Thread.sleep(sleepTime);
							assertTrue(driver
									.findElement(By.cssSelector("BODY"))
									.getText()
									.matches(
											"^[\\s\\S]*"
													+ bundleList.getTitle()
													+ "[\\s\\S]*$"));

							// Script is to take bundle channel
							String BundleShowsResponse = DomParserXPATH
									.getCategories(UrlFactoryUtil.getInstance()
											.getBundleChannelsURL(
													bundleList.getId(), 12));
							List<VideoDetails> FeaturedShowsListUnderBundle = JsonParser
									.parseHomeFeaturedShowsResponse(BundleShowsResponse);

							if (FeaturedShowsListUnderBundle != null
									&& FeaturedShowsListUnderBundle.size() <= 5) {
								for (VideoDetails channelList : FeaturedShowsListUnderBundle) {
									if (channelList.getLevel() != null
											&& channelList.getLevel()
													.equalsIgnoreCase("SHOW")) {
										int showCount = 0;
										String showCountResponse = DomParserXPATH
												.getCategories(UrlFactoryUtil
														.getInstance()
														.getSubShowURL(
																channelList
																		.getId()));
										if (showCountResponse != null
												&& showCountResponse.length() > 0)
											showCount = Integer
													.parseInt(JsonParser
															.parseHomeFeaturedShowCountResponse(showCountResponse));
										if (showCount > 0)
											featuredShowsListUnderBundle
													.add(channelList);
										System.out
												.println("Bundle Channels List:>>"
														+ channelList
																.getTitle());

										// Verify Channels and click on it.
										Thread.sleep(sleepTime);
										boolean isChannelPresent;
										do {
											isChannelPresent = driver
													.findElements(
															By.linkText(channelList
																	.getTitle()))
													.size() > 0;
											if (isChannelPresent == true) {
												assertTrue(driver
														.findElement(
																By.cssSelector("BODY"))
														.getText()
														.matches(
																"^[\\s\\S]*"
																		+ channelList
																				.getTitle()
																		+ "[\\s\\S]*$"));
												driver.findElement(
														By.linkText(channelList
																.getTitle()))
														.click();

												Thread.sleep(sleepTime);
												assertTrue(driver
														.findElement(
																By.cssSelector("BODY"))
														.getText()
														.matches(
																"^[\\s\\S]*"
																		+ channelList
																				.getTitle()
																		+ "[\\s\\S]*$"));

												// Verify Shows and click on it.
												String bundleChannelsSubShowResponse = DomParserXPATH
														.getCategories(UrlFactoryUtil
																.getInstance()
																.getSubShowURL(
																		channelList
																				.getId()));
												List<VideoDetails> featuredBundleChannelSubShowsList = JsonParser
														.parseHomeFeaturedShowsResponse(bundleChannelsSubShowResponse);

												if (featuredBundleChannelSubShowsList != null
														&& featuredBundleChannelSubShowsList
																.size() < 5) {
													for (VideoDetails showList : featuredBundleChannelSubShowsList) {
														featuredBundleChannelsSubShowsList
																.add(showList);
														System.out
																.println("Bundle Sub Shows List:>>"
																		+ showList
																				.getTitle());

														System.out
																.println("ShowsListUnderBundleSHOWS<<<<<<<<"
																		+ showList
																				.getTitle());
														Thread.sleep(sleepTime);
														boolean isShowPresent;
														do {
															isShowPresent = driver
																	.findElements(
																			By.linkText(showList
																					.getTitle()))
																	.size() > 0;
															if (isShowPresent == true) {
																assertTrue(driver
																		.findElement(
																				By.cssSelector("BODY"))
																		.getText()
																		.matches(
																				"^[\\s\\S]*"
																						+ showList
																								.getTitle()
																						+ "[\\s\\S]*$"));
																driver.findElement(
																		By.linkText(showList
																				.getTitle()))
																		.click();

																Thread.sleep(sleepTime);
																assertTrue(driver
																		.findElement(
																				By.cssSelector("BODY"))
																		.getText()
																		.matches(
																				"^[\\s\\S]*"
																						+ showList
																								.getTitle()
																						+ "[\\s\\S]*$"));

																// Get Video
																// Details and
																// verify all
																// response.
																String videoResponse = DomParserXPATH
																		.getCategories(UrlFactoryUtil
																				.getInstance()
																				.getVideoDetailsURL(
																						showList.getId(),
																						12));
																List<VideoDetails> storeFeaturedVideoList = JsonParser
																		.parseHomeFeaturedVideosResponse(videoResponse);

																if (storeFeaturedVideoList != null
																		&& storeFeaturedVideoList
																				.size() < 5) {
																	for (VideoDetails videoList : storeFeaturedVideoList) {
																		storeFeaturedVideoListUnderChannel
																				.add(videoList);
																		System.out
																				.println("Bundle Sub Shows Video List:>>"
																						+ videoList
																								.getTitle());
																		System.out
																				.println("VideoListUnderBundleRows<<<<<<<<"
																						+ videoList
																								.getTitle());
																		Thread.sleep(sleepTime);
																		boolean isVideoPresent;
																		do {
																			isVideoPresent = driver
																					.findElements(
																							By.linkText(videoList
																									.getTitle()))
																					.size() > 0;
																			if (isVideoPresent == true) {
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));
																				driver.findElement(
																						By.linkText(videoList
																								.getTitle()))
																						.click();

																				Thread.sleep(sleepTime);
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));

																				driver.navigate()
																						.back();
																				Thread.sleep(sleepTime);
																			}
																		} while (isVideoPresent == false);
																	}
																} else {
																	for (int index = 0; index < 5; index++) {
																		VideoDetails videoList = storeFeaturedVideoList
																				.get(index);
																		storeFeaturedVideoListUnderChannel
																				.add(videoList);
																		System.out
																				.println("VideoListUnderBundleRows<<<<<<<<"
																						+ videoList
																								.getTitle());
																		Thread.sleep(sleepTime);
																		boolean isVideoPresent;
																		do {
																			isVideoPresent = driver
																					.findElements(
																							By.linkText(videoList
																									.getTitle()))
																					.size() > 0;
																			if (isVideoPresent == true) {
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));
																				driver.findElement(
																						By.linkText(videoList
																								.getTitle()))
																						.click();

																				Thread.sleep(sleepTime);
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));

																				driver.navigate()
																						.back();
																				Thread.sleep(sleepTime);
																			}
																		} while (isVideoPresent == false);
																	}
																}
																driver.navigate()
																		.back();
																Thread.sleep(sleepTime);
															}
														} while (isShowPresent == false);
													}
												} else {
													for (int index = 0; index < 5; index++) {
														VideoDetails showList = featuredBundleChannelSubShowsList
																.get(index);
														featuredBundleChannelsSubShowsList
																.add(showList);
														System.out
																.println("ShowsListUnderBundleSHOWS<<<<<<<<"
																		+ showList
																				.getTitle());
														Thread.sleep(sleepTime);
														boolean isShowPresent;
														do {
															isShowPresent = driver
																	.findElements(
																			By.linkText(showList
																					.getTitle()))
																	.size() > 0;
															if (isShowPresent == true) {
																assertTrue(driver
																		.findElement(
																				By.cssSelector("BODY"))
																		.getText()
																		.matches(
																				"^[\\s\\S]*"
																						+ showList
																								.getTitle()
																						+ "[\\s\\S]*$"));
																driver.findElement(
																		By.linkText(showList
																				.getTitle()))
																		.click();

																Thread.sleep(sleepTime);
																assertTrue(driver
																		.findElement(
																				By.cssSelector("BODY"))
																		.getText()
																		.matches(
																				"^[\\s\\S]*"
																						+ showList
																								.getTitle()
																						+ "[\\s\\S]*$"));

																// Get Video
																// Details and
																// verify all
																// response.
																String videoResponse = DomParserXPATH
																		.getCategories(UrlFactoryUtil
																				.getInstance()
																				.getVideoDetailsURL(
																						showList.getId(),
																						12));
																List<VideoDetails> storeFeaturedVideoList = JsonParser
																		.parseHomeFeaturedVideosResponse(videoResponse);

																if (storeFeaturedVideoList != null
																		&& storeFeaturedVideoList
																				.size() < 5) {
																	for (VideoDetails videoList : storeFeaturedVideoList) {
																		storeFeaturedVideoListUnderChannel
																				.add(videoList);
																		System.out
																				.println("Bundle Sub Shows Video List:>>"
																						+ videoList
																								.getTitle());
																		System.out
																				.println("VideoListUnderBundleRows<<<<<<<<"
																						+ videoList
																								.getTitle());
																		Thread.sleep(sleepTime);
																		boolean isVideoPresent;
																		do {
																			isVideoPresent = driver
																					.findElements(
																							By.linkText(videoList
																									.getTitle()))
																					.size() > 0;
																			if (isVideoPresent == true) {
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));
																				driver.findElement(
																						By.linkText(videoList
																								.getTitle()))
																						.click();

																				Thread.sleep(sleepTime);
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));

																				driver.navigate()
																						.back();
																				Thread.sleep(sleepTime);
																			}
																		} while (isVideoPresent == false);
																	}
																} else {
																	for (int videoIndex = 0; videoIndex < 5; videoIndex++) {
																		VideoDetails videoList = storeFeaturedVideoList
																				.get(videoIndex);
																		storeFeaturedVideoListUnderChannel
																				.add(videoList);
																		System.out
																				.println("VideoListUnderBundleRows<<<<<<<<"
																						+ videoList
																								.getTitle());
																		Thread.sleep(sleepTime);
																		boolean isVideoPresent;
																		do {
																			isVideoPresent = driver
																					.findElements(
																							By.linkText(videoList
																									.getTitle()))
																					.size() > 0;
																			if (isVideoPresent == true) {
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));
																				driver.findElement(
																						By.linkText(videoList
																								.getTitle()))
																						.click();

																				Thread.sleep(sleepTime);
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));

																				driver.navigate()
																						.back();
																				Thread.sleep(sleepTime);
																			}
																		} while (isVideoPresent == false);
																	}
																}
																driver.navigate()
																		.back();
																Thread.sleep(sleepTime);
															}
														} while (isShowPresent == false);

													}
												}
												driver.navigate().back();
												Thread.sleep(sleepTime);

											}
										} while (isChannelPresent == false);
									}
								}
								driver.navigate().back();
								Thread.sleep(sleepTime);
							} else {
								for (int index = 0; index < 5; index++) {
									VideoDetails channelList = FeaturedShowsListUnderBundle
											.get(index);

									if (channelList.getLevel() != null
											&& channelList.getLevel()
													.equalsIgnoreCase("SHOW")) {

										int showCount = 0;
										String showCountResponse = DomParserXPATH
												.getCategories(UrlFactoryUtil
														.getInstance()
														.getSubShowURL(
																channelList
																		.getId()));
										if (showCountResponse != null
												&& showCountResponse.length() > 0)
											showCount = Integer
													.parseInt(JsonParser
															.parseHomeFeaturedShowCountResponse(showCountResponse));
										if (showCount > 0)
											featuredShowsListUnderBundle
													.add(channelList);
										System.out
												.println("Bundle Channels List:>>"
														+ channelList
																.getTitle());

										// Verify Channels and click on it.
										Thread.sleep(sleepTime);
										boolean isChannelPresent;
										do {
											isChannelPresent = driver
													.findElements(
															By.linkText(channelList
																	.getTitle()))
													.size() > 0;
											if (isChannelPresent == true) {
												assertTrue(driver
														.findElement(
																By.cssSelector("BODY"))
														.getText()
														.matches(
																"^[\\s\\S]*"
																		+ channelList
																				.getTitle()
																		+ "[\\s\\S]*$"));
												driver.findElement(
														By.linkText(channelList
																.getTitle()))
														.click();

												Thread.sleep(sleepTime);
												assertTrue(driver
														.findElement(
																By.cssSelector("BODY"))
														.getText()
														.matches(
																"^[\\s\\S]*"
																		+ channelList
																				.getTitle()
																		+ "[\\s\\S]*$"));

												// Verify Shows and click on it.
												String bundleChannelsSubShowResponse = DomParserXPATH
														.getCategories(UrlFactoryUtil
																.getInstance()
																.getSubShowURL(
																		channelList
																				.getId()));
												List<VideoDetails> featuredBundleChannelSubShowsList = JsonParser
														.parseHomeFeaturedShowsResponse(bundleChannelsSubShowResponse);

												if (featuredBundleChannelSubShowsList != null
														&& featuredBundleChannelSubShowsList
																.size() < 5) {
													for (VideoDetails showList : featuredBundleChannelSubShowsList) {
														featuredBundleChannelsSubShowsList
																.add(showList);
														System.out
																.println("Bundle Sub Shows List:>>"
																		+ showList
																				.getTitle());

														System.out
																.println("ShowsListUnderBundleSHOWS<<<<<<<<"
																		+ showList
																				.getTitle());
														Thread.sleep(sleepTime);
														boolean isShowPresent;
														do {
															isShowPresent = driver
																	.findElements(
																			By.linkText(showList
																					.getTitle()))
																	.size() > 0;
															if (isShowPresent == true) {
																assertTrue(driver
																		.findElement(
																				By.cssSelector("BODY"))
																		.getText()
																		.matches(
																				"^[\\s\\S]*"
																						+ showList
																								.getTitle()
																						+ "[\\s\\S]*$"));
																driver.findElement(
																		By.linkText(showList
																				.getTitle()))
																		.click();

																Thread.sleep(sleepTime);
																assertTrue(driver
																		.findElement(
																				By.cssSelector("BODY"))
																		.getText()
																		.matches(
																				"^[\\s\\S]*"
																						+ showList
																								.getTitle()
																						+ "[\\s\\S]*$"));

																// Get Video
																// Details and
																// verify all
																// response.
																String videoResponse = DomParserXPATH
																		.getCategories(UrlFactoryUtil
																				.getInstance()
																				.getVideoDetailsURL(
																						showList.getId(),
																						12));
																List<VideoDetails> storeFeaturedVideoList = JsonParser
																		.parseHomeFeaturedVideosResponse(videoResponse);

																if (storeFeaturedVideoList != null
																		&& storeFeaturedVideoList
																				.size() < 5) {
																	for (VideoDetails videoList : storeFeaturedVideoList) {
																		storeFeaturedVideoListUnderChannel
																				.add(videoList);
																		System.out
																				.println("Bundle Sub Shows Video List:>>"
																						+ videoList
																								.getTitle());
																		System.out
																				.println("VideoListUnderBundleRows<<<<<<<<"
																						+ videoList
																								.getTitle());
																		Thread.sleep(sleepTime);
																		boolean isVideoPresent;
																		do {
																			isVideoPresent = driver
																					.findElements(
																							By.linkText(videoList
																									.getTitle()))
																					.size() > 0;
																			if (isVideoPresent == true) {
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));
																				driver.findElement(
																						By.linkText(videoList
																								.getTitle()))
																						.click();

																				Thread.sleep(sleepTime);
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));

																				driver.navigate()
																						.back();
																				Thread.sleep(sleepTime);
																			}
																		} while (isVideoPresent == false);
																	}
																} else {
																	for (int videoIndex = 0; index < 5; index++) {
																		VideoDetails videoList = storeFeaturedVideoList
																				.get(videoIndex);
																		storeFeaturedVideoListUnderChannel
																				.add(videoList);
																		System.out
																				.println("VideoListUnderBundleRows<<<<<<<<"
																						+ videoList
																								.getTitle());
																		Thread.sleep(sleepTime);
																		boolean isVideoPresent;
																		do {
																			isVideoPresent = driver
																					.findElements(
																							By.linkText(videoList
																									.getTitle()))
																					.size() > 0;
																			if (isVideoPresent == true) {
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));
																				driver.findElement(
																						By.linkText(videoList
																								.getTitle()))
																						.click();

																				Thread.sleep(sleepTime);
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));

																				driver.navigate()
																						.back();
																				Thread.sleep(sleepTime);
																			}
																		} while (isVideoPresent == false);
																	}
																}
																driver.navigate()
																		.back();
																Thread.sleep(sleepTime);
															}
														} while (isShowPresent == false);
													}
												} else {
													for (int showIndex = 0; index < 5; index++) {
														VideoDetails showList = featuredBundleChannelSubShowsList
																.get(showIndex);
														featuredBundleChannelsSubShowsList
																.add(showList);
														System.out
																.println("ShowsListUnderBundleSHOWS<<<<<<<<"
																		+ showList
																				.getTitle());
														Thread.sleep(sleepTime);
														boolean isShowPresent;
														do {
															isShowPresent = driver
																	.findElements(
																			By.linkText(showList
																					.getTitle()))
																	.size() > 0;
															if (isShowPresent == true) {
																assertTrue(driver
																		.findElement(
																				By.cssSelector("BODY"))
																		.getText()
																		.matches(
																				"^[\\s\\S]*"
																						+ showList
																								.getTitle()
																						+ "[\\s\\S]*$"));
																driver.findElement(
																		By.linkText(showList
																				.getTitle()))
																		.click();

																Thread.sleep(sleepTime);
																assertTrue(driver
																		.findElement(
																				By.cssSelector("BODY"))
																		.getText()
																		.matches(
																				"^[\\s\\S]*"
																						+ showList
																								.getTitle()
																						+ "[\\s\\S]*$"));

																// Get Video
																// Details and
																// verify all
																// response.
																String videoResponse = DomParserXPATH
																		.getCategories(UrlFactoryUtil
																				.getInstance()
																				.getVideoDetailsURL(
																						showList.getId(),
																						12));
																List<VideoDetails> storeFeaturedVideoList = JsonParser
																		.parseHomeFeaturedVideosResponse(videoResponse);

																if (storeFeaturedVideoList != null
																		&& storeFeaturedVideoList
																				.size() < 5) {
																	for (VideoDetails videoList : storeFeaturedVideoList) {
																		storeFeaturedVideoListUnderChannel
																				.add(videoList);
																		System.out
																				.println("Bundle Sub Shows Video List:>>"
																						+ videoList
																								.getTitle());
																		System.out
																				.println("VideoListUnderBundleRows<<<<<<<<"
																						+ videoList
																								.getTitle());
																		Thread.sleep(sleepTime);
																		boolean isVideoPresent;
																		do {
																			isVideoPresent = driver
																					.findElements(
																							By.linkText(videoList
																									.getTitle()))
																					.size() > 0;
																			if (isVideoPresent == true) {
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));
																				driver.findElement(
																						By.linkText(videoList
																								.getTitle()))
																						.click();

																				Thread.sleep(sleepTime);
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));

																				driver.navigate()
																						.back();
																				Thread.sleep(sleepTime);
																			}
																		} while (isVideoPresent == false);
																	}
																} else {
																	for (int videoIndex = 0; videoIndex < 5; videoIndex++) {
																		VideoDetails videoList = storeFeaturedVideoList
																				.get(videoIndex);
																		storeFeaturedVideoListUnderChannel
																				.add(videoList);
																		System.out
																				.println("VideoListUnderBundleRows<<<<<<<<"
																						+ videoList
																								.getTitle());
																		Thread.sleep(sleepTime);
																		boolean isVideoPresent;
																		do {
																			isVideoPresent = driver
																					.findElements(
																							By.linkText(videoList
																									.getTitle()))
																					.size() > 0;
																			if (isVideoPresent == true) {
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));
																				driver.findElement(
																						By.linkText(videoList
																								.getTitle()))
																						.click();

																				Thread.sleep(sleepTime);
																				assertTrue(driver
																						.findElement(
																								By.cssSelector("BODY"))
																						.getText()
																						.matches(
																								"^[\\s\\S]*"
																										+ videoList
																												.getTitle()
																										+ "[\\s\\S]*$"));

																				driver.navigate()
																						.back();
																				Thread.sleep(sleepTime);
																			}
																		} while (isVideoPresent == false);
																	}
																}
																driver.navigate()
																		.back();
																Thread.sleep(sleepTime);
															}
														} while (isShowPresent == false);

													}
												}
												driver.navigate().back();
												Thread.sleep(sleepTime);
											}
										} while (isChannelPresent == false);
									}
								}
							}
							driver.navigate().back();
							Thread.sleep(sleepTime);
						} else {
							driver.findElement(
									By.xpath("//*[@id='featured']/div/section/a[2]/span"))
									.click();
							Thread.sleep(sleepTime);
						}
					} while (isbundlePresent == false);
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
