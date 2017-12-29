package comcast.test.app.common.homePageTestCases.BeforeLoginFeatureTestCases;

import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.constant.XidioConstant;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.FlashObjectWebDriver;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyResumeAndStartFunctionalityWithoutLogin Description: This
 * test case is to verify Resume and Start functionality for partially played
 * video in video player by logging into Watchable application.
 * **/

public class VerifyResumeAndStartFunctionalityWithoutLogin extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyResumeAndStartFunctionalityWithoutLogin()
			throws Exception {

		FlashObjectWebDriver flashApp = new FlashObjectWebDriver(driver,
				"PlayerPlatformAPI");
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.featuredSectionVideos();
		List<VideoDetails> videoList = videoDetails.get("featuredVideoList");

		// driver.get(proUtil.getProperty("HOMEAPPURL"));
		driver.get(DataServiceProperties.HOMEAPPURL);
		try {

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			if (videoList != null) {
				for (int index = 0; index < 1; index++) {
					VideoDetails videos = videoList.get(index);
					// This Method verifies Channel present in Featured list and
					// selects a Channel.
					homePageCommonFun.selectFeaturedChannel(videos.getTitle());

					// This method performs mouse over on video player.
					Thread.sleep(XidioConstant.halfMin);
					homePageCommonFun.mouseOverOnVideoPlayer();

					flashApp.callFlashObject("pause");
					Thread.sleep(sleepTime);
					driver.navigate().back();
					Thread.sleep(XidioConstant.fivesec);

					// This Method verifies Channel present in Featured list and
					// selects a Channel.
					homePageCommonFun.selectFeaturedChannel(videos.getTitle());

					Thread.sleep(XidioConstant.tenSec);
					// assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Resume[\\s\\S]*$"));
					// assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Start[\\s\\S]*$"));

					// assertFalse(driver.findElement(By.xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH)).getText().matches("^[\\s\\S]*Resume[\\s\\S]*$"));
					// assertFalse(driver.findElement(By.xpath(XpathObjectRepo.VIDEOPLAYSTARTBUTTON_XPATH)).getText().matches("^[\\s\\S]*Start[\\s\\S]*$"));

					assertFalse(driver
							.findElement(
									By.xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH))
							.getText()
							.contains(UILablesRepo.VIDEORESUMEBUTTON_TEXT));
					assertFalse(driver
							.findElement(
									By.xpath(XpathObjectRepo.VIDEOPLAYSTARTBUTTON_XPATH))
							.getText()
							.matches(UILablesRepo.VIDEOSTARTBUTTON_TEXT));
				}
			}
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
