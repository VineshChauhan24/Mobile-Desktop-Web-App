package comcast.test.config.dataServices.playVideos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

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
 * Class Name: DS_PlayHomePopularChannelsVideo Description: This data service is
 * to play the video from SHOWS category by directly selecting the videos in the
 * channel page for 'Popular Channels' section in Home page.
 * **/

public class DS_PlayHomePopularChannelsVideo extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testDS_PlayHomePopularChannelsVideo() throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nPopularAPI();
		List<VideoDetails> channelList = videoDetails
				.get("popularChannelsList");
		List<VideoDetails> videoList = videoDetails
				.get("popularChannelShowVideosList");

		Actions actions = new Actions(driver);
		int durationInSeconds = 0;
		int durationInMins = 0;

		try {

			if (channelList != null) {
				// This method asserts Popular Channels title.
				homePageCommonFun.scrollToPopularChannelsSection();

				// This Method verifies Channel present in Popular Channel
				// Section and selects a Channel.
				homePageCommonFun.selectPopularChannel(channelList.get(
						XidioConstant.selectPopularChannel).getTitle());

				driver.findElement(
						By.linkText(videoList.get(XidioConstant.selectVideo)
								.getTitle())).click();

				boolean isPopupPresent = false;
				/*
				 * isPopupPresent=driver.findElement(By.xpath(XpathObjectRepo.
				 * VIDEONOWPLAYINGOPOUP_XPATH
				 * )).findElements(By.xpath(XpathObjectRepo
				 * .VIDEOSTARTPOINTBUTTON_XPATH)).size()>0;
				 * if(isPopupPresent==true)
				 * driver.findElement(By.xpath(XpathObjectRepo
				 * .VIDEOSTARTPOINTBUTTON_XPATH)).click();
				 */

				Thread.sleep(sleepTime);
				Thread.sleep(sleepTime);
				WebElement playFromStart = driver.findElement(By
						.xpath(XpathObjectRepo.VIDEOPLAYSTARTBUTTON_XPATH));

				if (playFromStart.isDisplayed()) {
					Thread.sleep(sleepTime);
					Thread.sleep(sleepTime);
					driver.findElement(
							By.xpath(XpathObjectRepo.VIDEOPLAYSTARTBUTTON_XPATH))
							.click();

				}
				Thread.sleep(sleepTime);

				durationInSeconds = videoList.get(XidioConstant.selectVideo)
						.getDuration();
				durationInMins = durationInSeconds / 60;
				int duration = 0;

				if (durationInMins < 2)
					duration = durationInMins;
				else
					duration = 2;
				for (int j = 0; j < duration; j++) {
					Thread.sleep(XidioConstant.halfMin);
					WebElement videoPage = driver.findElement(By
							.xpath(XpathObjectRepo.VIDEODETAILSPAGE_XPATH));
					actions.moveToElement(videoPage);
					actions.perform();

					String getVideoPlayState = driver
							.findElement(
									By.xpath(XpathObjectRepo.VIDEOPLAYORPAUSEBUTTON_XPATH))
							.getAttribute("class");
					assertEquals(UILablesRepo.VIDEOPAUSEBUTTON_TEXT,
							getVideoPlayState);

					// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videoList.get(XidioConstant.selectVideo).getTitle()+"[\\s\\S]*$"));

					// This method is to assert Up Next Header
					assertionFunction.assertUpNextTitle();
				}
				// This method Navigate to Home page.
				homePageCommonFun.navigateToHomePage();
			}
			// This method Navigate to Home page.
			homePageCommonFun.navigateToHomePage();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
