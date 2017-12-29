package comcast.test.app.testCases.videoManagement.videoHomeManagement.PopularChannelsCategoryTestCases.ChannelTestCases.VideosCategory;

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
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyVideosUnderChannelForHomePopularChannels Description: This
 * test case validates whether videos are displayed after clicking on LAST
 * UPDATED link in channel page for the channel present under Popular Channels
 * section in Home page by logging registered user into Watchable application.
 * **/

public class VerifyVideosUnderChannelForHomePopularChannels extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyVideosUnderChannelForHomePopularChannels()
			throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nPopularAPI();
		List<VideoDetails> channelList = videoDetails
				.get("popularChannelsList");
		List<VideoDetails> popularChannelShowList = videoDetails
				.get("popularChannelShowsList");
		List<VideoDetails> popularChannelShowVideoList = videoDetails
				.get("popularChannelShowVideosList");

		try {

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method is to assert Watchable Top Middle Menu and to ensure
			// its collapsed.
			// assertionFunction.assertGazeeboTopMiddleMenu();

			// This method asserts Popular Channels title.
			assertionFunction.assertPopularChannelsTitle();

			if (channelList != null) {
				// This method is to scroll UI to Popular Channels Section.
				homePageCommonFun.scrollToPopularChannelsSection();

				// This Method verifies Channel present in Popular Channel
				// Section and selects a Channel.
				homePageCommonFun.selectPopularChannel(channelList.get(
						XidioConstant.selectPopularChannel).getTitle());
				if (popularChannelShowList != null) {
					driver.findElement(
							By.linkText(popularChannelShowList.get(
									XidioConstant.selectShow).getTitle()))
							.click();
					Thread.sleep(sleepTime);

					for (VideoDetails finalPopularChannelShowVideoList : popularChannelShowVideoList) {
						if (finalPopularChannelShowVideoList == null) {
							popularChannelShowVideoList
									.remove(finalPopularChannelShowVideoList);
						}
					}

					int loopMaxIndex = 0;
					if (popularChannelShowVideoList.size() < 4)
						loopMaxIndex = popularChannelShowVideoList.size();
					else
						loopMaxIndex = 4;

					for (int index = 0; index < loopMaxIndex; index++) {
						VideoDetails popularShowVideoList = popularChannelShowVideoList
								.get(index);

						assertTrue(driver
								.findElement(
										By.xpath(XpathObjectRepo.SHOWDETAILVIDEOLIST_XPATH))
								.getText()
								.contains(popularShowVideoList.getTitle()));

					}
				}
			} else {
				boolean isPresent;
				// Lekshmi : Changed Object identifier to fetch the popular
				// channel title
				isPresent = driver
						.findElement(
								By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSROW_XPATH))
						.findElements(
								By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSFIRSTTITLELABEL_XPATH))
						.size() > 0;
				if (isPresent == true) {
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

			// This method asserts Watchable Logo.
			assertionFunction.assertWatchableLogo();

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Copy Right Links.
			assertionFunction.assertFooterCopyRight();

			assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
