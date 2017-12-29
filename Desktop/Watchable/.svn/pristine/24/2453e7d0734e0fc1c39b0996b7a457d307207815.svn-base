package comcast.test.app.testCases.videoManagement.videoHomeManagement.FeaturedCategoryTestCases.FeaturedCategoryPageTestCases;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.JsonParser;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.configServices.utils.UrlFactoryUtil;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyFeaturedShowWithItsDetails Description: This test case
 * validates whether the Show with its details is displayed under Featured
 * section in Home page by logging registered user into Gazeebo application.
 * **/

public class VerifyFeaturedShowWithItsDetails extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyFeaturedShowWithItsDetails() throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nFeaturedAPI();
		List<VideoDetails> featuredShowsList = videoDetails
				.get("featuredShowList");

		String sessionToken = RestAPIServices.executeGenreAuthentication();

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is to ensure Home is Active page when Login into
			// Application.
			// assertionFunction.assertHomeActiveLink();

			// This method is to assert Watchable Top Middle Menu and to ensure
			// its collapsed.
			// assertionFunction.assertGazeeboTopMiddleMenu();

			// This method asserts featured title.
			assertionFunction.assertFeaturedTitle();

			if (featuredShowsList != null) {
				int loopMaxIndex = 0;
				if (featuredShowsList.size() < 4)
					loopMaxIndex = featuredShowsList.size();
				else
					loopMaxIndex = 4;

				for (int index = 0; index < loopMaxIndex; index++) {
					VideoDetails showList = featuredShowsList.get(index);
					// This Method verifies Show present in Home Featured list
					// and selects a Show.
					homePageCommonFun.selectHomeFeaturedShow(showList
							.getTitle());

					String videoResponse = RestAPIServices
							.getSessionTokenResponse(
									UrlFactoryUtil.getInstance()
											.getVideoDetailsURL(
													showList.getId(), 10),
									sessionToken);
					List<VideoDetails> channelShowsVideoList = JsonParser
							.parseChannelShowsVideosResponse(videoResponse);

					Thread.sleep(sleepTime);
					// Manoj: Object modified and added to XpathObjectRepo file
					// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+channelShowsVideoList.get(index).getShowTitle()+"[\\s\\S]*$"));
					assertTrue(driver
							.findElement(
									By.xpath(XpathObjectRepo.SHOWDETAILSHOWTITLE_XPATH))
							.getText()
							.matches(
									channelShowsVideoList.get(index)
											.getShowTitle()));

					// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+channelShowsVideoList.get(index).getChannelTitle()+"[\\s\\S]*$"));
					assertTrue(driver
							.findElement(
									By.xpath(XpathObjectRepo.SHOWDETAILCHANNELTITLE_XPATH))
							.getText()
							.matches(
									channelShowsVideoList.get(index)
											.getChannelTitle()));

					// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+showsDetail.get(showds).getShortDescription()+"[\\s\\S]*$"));

					driver.navigate().back();
					Thread.sleep(sleepTime);
				}
			}

			Thread.sleep(sleepTime);
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
