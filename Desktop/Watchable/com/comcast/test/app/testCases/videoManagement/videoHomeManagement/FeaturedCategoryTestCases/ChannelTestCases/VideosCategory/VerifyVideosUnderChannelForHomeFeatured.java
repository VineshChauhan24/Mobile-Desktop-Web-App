package comcast.test.app.testCases.videoManagement.videoHomeManagement.FeaturedCategoryTestCases.ChannelTestCases.VideosCategory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

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
 * Class Name: VerifyVideosUnderChannelForHomeFeatured Description: This test
 * case validates whether videos are displayed after clicking on LAST UPDATED
 * link in channel page for the channel present under Featured section in Home
 * page by logging registered user into Watchable application.
 * **/

public class VerifyVideosUnderChannelForHomeFeatured extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyVideosUnderChannelForHomeFeatured() throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nHomeFeaturedAPI();
		List<VideoDetails> channelList = videoDetails
				.get("featuredChannelsList");
		List<VideoDetails> showListUnderChannel = videoDetails
				.get("showsUnderChannel");
		List<VideoDetails> videoList = videoDetails.get("video");

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method is to assert Watchable Top Middle Menu and to ensure
			// its collapsed.
			// assertionFunction.assertGazeeboTopMiddleMenu(); - Functionality
			// not present now

			// This method asserts featured title.
			assertionFunction.assertFeaturedTitle();

			int loopMaxIndex = 0;
			if (channelList != null) {
				// This Method verifies Channel present in Featured list and
				// selects a Channel.
				homePageCommonFun.selectFeaturedChannel(channelList.get(
						XidioConstant.selectFeaturedChannel).getTitle());

				if (showListUnderChannel != null) {
					if (showListUnderChannel.size() < 1)
						loopMaxIndex = showListUnderChannel.size();
					else
						loopMaxIndex = 1;

					for (int index = 0; index < loopMaxIndex; index++) {
						VideoDetails showList = showListUnderChannel.get(index);
						// Select show under channel
						driver.findElement(By.linkText(showList.getTitle()))
								.click();

						int videoIndex = 0;
						if (videoList != null) {
							if (videoList.size() < 2)
								videoIndex = videoList.size();
							else
								videoIndex = 2;

							for (int i = 0; i < videoIndex; i++) {
								VideoDetails videos = videoList.get(i);
								Thread.sleep(sleepTime);
								// Assert video under channel > show.
								// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videos.getTitle()+"[\\s\\S]*$"));
								assertTrue(driver
										.findElement(
												By.xpath(XpathObjectRepo.CHANNELDETAILSSHOWLIST_XPATH))
										.getText().contains(videos.getTitle()));
							}
							driver.navigate().back();
							Thread.sleep(sleepTime);
						}
					}
				}
			} else {
				boolean isPresent;

				// Manoj: Object modified and moved to XpathObjectProp file
				// isPresent=driver.findElement(By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]")).findElements(By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]/li[1]/article/a/div/div")).size()>0;
				isPresent = driver
						.findElement(
								By.xpath(XpathObjectRepo.FEATUREDROWLIST_XPATH))
						.findElements(
								By.xpath(XpathObjectRepo.FEATUREDICONS_XPATH))
						.size() > 0;
				if (isPresent == true) {
					// String
					// channelName=driver.findElement(By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]")).findElement(By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]/li[1]/article/a/div/div")).getText();
					String channelName = driver
							.findElement(
									By.xpath(XpathObjectRepo.FEATUREDROWLIST_XPATH))
							.findElement(
									By.xpath(XpathObjectRepo.FEATUREDICONSFIRSTTITLE_XPATH))
							.getText();
					assertFalse(driver
							.findElement(
									By.xpath(XpathObjectRepo.FEATUREDROWLIST_XPATH))
							.getText().contains(channelName));
				}
			}

			// This method asserts Watchable Logo.
			assertionFunction.assertWatchableLogo();

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
