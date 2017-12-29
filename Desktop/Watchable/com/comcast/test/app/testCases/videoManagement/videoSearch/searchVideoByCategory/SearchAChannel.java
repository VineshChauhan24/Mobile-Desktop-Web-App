package comcast.test.app.testCases.videoManagement.videoSearch.searchVideoByCategory;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.testng.Reporter;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: SearchAChannel Description: This test case validates Channel
 * search functionality in Home page by logging registered user into Watchable
 * application.
 * **/

public class SearchAChannel extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	String pageTitle = "";

	@Test
	public void testSearchAChannel() throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.allPopularChannelsList();
		List<VideoDetails> channelList = videoDetails
				.get("popularChannelsList");
		Map<String, List<VideoDetails>> searchVideoDetails = null;

		try {

			// Opening application

			driver.get(DataServiceProperties.HOMEAPPURL);
			pageTitle = driver.getTitle();
			Thread.sleep(sleepTime);

			if (pageTitle.contains(UILablesRepo.HOMEPAGE_TITLE)) {
				log.info("Successfully Opened the application");

				// This method is to ensure Home is Active page when Login into
				// Application.
				assertionFunction.assertHomeActiveLink();

				if (channelList != null) {
					int loopIndexMax = 0;
					if (channelList.size() < 2) {
						loopIndexMax = channelList.size();
					} else {
						loopIndexMax = 2;
					}
					for (int index = 0; index < loopIndexMax; index++) {
						VideoDetails channels = channelList.get(index);

						// This Method search a Channel. SEARCH_RESULT_ROW_XPATH
						homePageCommonFun.searchAnAsset(channels.getTitle());
						Thread.sleep(sleepTime);

						int ele = driver
								.findElements(
										By.xpath(XpathObjectRepo.SEARCH_RESULT_ROW_XPATH))
								.size();
						if (ele == 1) {

							Thread.sleep(sleepTime);
							assertTrue(
									"Search Result not contains channels",
									driver.findElement(
											By.xpath(XpathObjectRepo.SEARCH_RESULT_ROW_XPATH))
											.getText()
											.contains(
													UILablesRepo.CHANNEL_LABEL));
							Reporter.log("Search Result contains channels");
							log.info("Search Result contains channels");

							Thread.sleep(sleepTime);
							assertTrue(
									"Search Result not contains the searched channel",
									driver.findElement(
											By.xpath(XpathObjectRepo.SEARCH_RESULT_ROW_XPATH))
											.getText()
											.contains(channels.getTitle()));
							Reporter.log("Search Result contains the searched channel: "
									+ channels.getTitle());
							log.info("Search Result contains the searched channel: "
									+ channels.getTitle());
						} else {
							Reporter.log("No Search Result found");
							log.error("No Search Result found");
						}

					}
				}

				// This method asserts Footer Logo and It's Text.
				assertionFunction.assertFooterLogo();

				// This method asserts Footer Copy Right Links.
				assertionFunction.assertFooterCopyRight();

				// This method is to ensure Login page is displayed when user
				// Sign
				// Out from Application.
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
