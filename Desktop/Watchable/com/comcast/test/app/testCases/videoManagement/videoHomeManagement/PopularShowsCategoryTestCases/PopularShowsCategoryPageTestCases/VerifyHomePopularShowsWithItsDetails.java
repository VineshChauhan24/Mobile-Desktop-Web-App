package comcast.test.app.testCases.videoManagement.videoHomeManagement.PopularShowsCategoryTestCases.PopularShowsCategoryPageTestCases;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
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
 * Class Name: VerifyHomePopularShowsWithItsDetails Description: This test case
 * is to verify Home/Popular Shows category all Shows are displayed and
 * clickable by comparing with API for registered Watchable Application user.
 * **/

public class VerifyHomePopularShowsWithItsDetails extends BaseTest {

	// Manoj: Code refactoring done

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	String pageTitle = "";

	@Rule
	public ErrorCollector errCol = new ErrorCollector();

	@Test
	public void testVerifyHomePopularShowsWithItsDetails() throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nHomePopularShowsAPIs();
		List<VideoDetails> popularShowList = videoDetails.get("popularShows");

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
			Thread.sleep(sleepTime);
			pageTitle = driver.getTitle();
			if (pageTitle.contains(UILablesRepo.HOMEPAGE_TITLE)) {
				log.info("Successfully Opened the application");

				// This method is to ensure Home is Active page when Login into
				// Application.
				// assertionFunction.assertHomeActiveLink();

				// This method is to assert Gazeebo Top Middle Menu and to
				// ensure
				// its collapsed.
				// assertionFunction.assertGazeeboTopMiddleMenu(); -
				// Functionality
				// is not implemented

				// This method asserts Popular Shows Header Title.
				assertionFunction.assertPopularShowsTitle();

				// This method is to scroll UI to Popular Channels Section.
				homePageCommonFun.scrollToPopularShowsSection();

				int loopMaxIndex = 0;
				if (popularShowList != null) {
					if (popularShowList.size() < 5)
						loopMaxIndex = popularShowList.size();
					else
						loopMaxIndex = 5;

					for (int index = 0; index < loopMaxIndex; index++) {
						VideoDetails showDetails = popularShowList.get(index);
						Thread.sleep(sleepTime);
						boolean isPresent;
						do {
							// isPresent=driver.findElements(By.xpath(".//*[@id='popular_shows']/div/section").linkText(showDetails.getTitle())).size()>0;

							// Manoj: Element Modified and add to
							// XpathObjectRepo
							// file
							// isPresent=driver.findElement(By.xpath(".//*[@id='popular_shows']/div/section/div/div/ul[1]")).findElements(By.linkText(showDetails.getTitle())).size()>0;

							isPresent = driver
									.findElement(
											By.xpath(XpathObjectRepo.HOMEPOPULARSHOWSVIEWROW_XPATH))
									.findElements(
											By.linkText(showDetails.getTitle()))
									.size() > 0;

							if (isPresent == true) {
								// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+showDetails.getTitle()+"[\\s\\S]*$"));

								assertTrue(driver
										.findElement(
												By.xpath(XpathObjectRepo.HOMEPOPULARSHOWSVIEWROW_XPATH))
										.getText()
										.contains(showDetails.getTitle()));
								// driver.findElement(By.xpath(".//*[@id='popular_shows']/div/section").linkText(showDetails.getTitle())).click();
								// driver.findElement(By.xpath(".//*[@id='popular_shows']/div/section")).findElement(By.linkText(showDetails.getTitle())).click();
								driver.findElement(
										By.xpath(XpathObjectRepo.HOMEPOPULARSHOWSVIEWROW_XPATH))
										.findElement(
												By.linkText(showDetails
														.getTitle())).click();

								// Get Video Details and verify all response.
								String videoResponse = RestAPIServices
										.getSessionTokenResponse(
												UrlFactoryUtil
														.getInstance()
														.getVideoDetailsURL(
																showDetails
																		.getId(),
																10),
												sessionToken);
								List<VideoDetails> homePopularShowsVideoList = JsonParser
										.parseChannelShowsVideosResponse(videoResponse);

								if (homePopularShowsVideoList != null) {
									if (homePopularShowsVideoList.size() < 5)
										loopMaxIndex = homePopularShowsVideoList
												.size();
									else
										loopMaxIndex = 5;
									for (int videos = 0; videos < loopMaxIndex; videos++) {
										VideoDetails videoList = homePopularShowsVideoList
												.get(videos);
										// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videoList.getTitle()+"[\\s\\S]*$"));

										/*
										 * assertTrue(driver .findElement(
										 * By.xpath(XpathObjectRepo.
										 * SHOWDETAILVIDEOLIST_XPATH))
										 * .getText()
										 * .contains(videoList.getTitle()));
										 */

									}
									Thread.sleep(sleepTime);
									driver.navigate().back();
									Thread.sleep(sleepTime);
								}
							} else {
								// Manoj: Element Modified and add to
								// XpathObjectRepo file
								// String
								// isNextEnable=driver.findElement(By.xpath(".//*[@id='popular_shows']/div/section/a[2]/span")).getAttribute("class");
								String isNextEnable = driver
										.findElement(
												By.xpath(XpathObjectRepo.HOMEPOPULARSHOWSBUTTONNEXT_XPATH))
										.getAttribute("class");
								if (!isNextEnable
										.equalsIgnoreCase("next hidden")
										&& !isNextEnable
												.equalsIgnoreCase("next disabled")) {

									// driver.findElement(By.xpath(".//*[@id='popular_shows']/div/section/a[2]/span")).click();
									driver.findElement(
											By.xpath(XpathObjectRepo.HOMEPOPULARSHOWSBUTTONNEXT_XPATH))
											.click();
									Thread.sleep(5000);
								} else {
									// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+showDetails.getTitle()+"[\\s\\S]*$"));
									assertTrue(driver
											.findElement(
													By.xpath(XpathObjectRepo.HOMEPOPULARSHOWSVIEWROW_XPATH))
											.getText()
											.contains(showDetails.getTitle()));
									Thread.sleep(sleepTime);
								}
							}
						} while (isPresent == false);
					}
				}
			}
			// This method is used to logout from watchable Application.
			// userLogin.LogOut(driver);

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
