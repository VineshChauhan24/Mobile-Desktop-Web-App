package comcast.test.app.common.homePageTestCases.BeforeLoginFeatureTestCases;

import static org.junit.Assert.assertEquals;

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
 * Class Name: VerifyReportFunctionalityWithoutLogin Description: This test case
 * is to verify Report functionality without logging into application in video
 * player by logging into Watchable application.
 * **/

public class VerifyReportFunctionalityWithoutLogin extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyReportFunctionalityWithoutLogin() throws Exception {

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
				if (videoList.size() < 2) {
					for (VideoDetails videos : videoList) {
						// This Method verifies Channel present in Featured list
						// and selects a Channel.
						homePageCommonFun.selectFeaturedChannel(videos
								.getTitle());

						// This method performs mouse over on video player.
						Thread.sleep(XidioConstant.tenSec);
						homePageCommonFun.mouseOverOnVideoPlayer();

						// Click on Like a video control
						driver.findElement(
								By.xpath(XpathObjectRepo.VIDEO_DETAILSPAGE_REPORT_IMAGE_XPATH))
								.click();

						String popUpMsg = driver
								.findElement(
										By.xpath(XpathObjectRepo.VIDEO_DETAILSPAGE_REPORTVIDEO_HEADER_XPATH))
								.getText();
						assertEquals(UILablesRepo.VIDEO_REPORT_HEADER, popUpMsg);

						String popUpAlertContent = driver
								.findElement(
										By.xpath(XpathObjectRepo.VIDEO_REPORTVIDEO_ALERTWITHOUTLOGIN_SPAN_XPATH))
								.getText();
						assertEquals(UILablesRepo.VIDEO_REPORT_ALERT_CONTENT,
								popUpAlertContent);

						String popUpOkButton = driver
								.findElement(
										By.xpath(XpathObjectRepo.VIDEO_REPORTVIDEO_ALERTWITHOUTLOGIN_OKBTN_XPATH))
								.getText();
						assertEquals(
								UILablesRepo.VIDEO_LIKEANDREPORT_OK_BUTTON,
								popUpOkButton);

						// Click on OK report button.
						Thread.sleep(sleepTime);
						driver.findElement(
								By.xpath(XpathObjectRepo.VIDEO_REPORTVIDEO_ALERTWITHOUTLOGIN_OKBTN_XPATH))
								.click();

						flashApp.callFlashObject("pause");
						Thread.sleep(sleepTime);
						driver.navigate().back();
						Thread.sleep(XidioConstant.fivesec);
					}
				} else {
					for (int index = 0; index < 2; index++) {
						VideoDetails videos = videoList.get(index);
						// This Method verifies Channel present in Featured list
						// and selects a Channel.
						homePageCommonFun.selectFeaturedChannel(videos
								.getTitle());

						// This method performs mouse over on video player.
						Thread.sleep(XidioConstant.tenSec);
						homePageCommonFun.mouseOverOnVideoPlayer();

						// Click on Like a video control
						driver.findElement(
								By.xpath(XpathObjectRepo.VIDEO_DETAILSPAGE_REPORT_IMAGE_XPATH))
								.click();

						String popUpMsg = driver
								.findElement(
										By.xpath(XpathObjectRepo.VIDEO_DETAILSPAGE_REPORTVIDEO_HEADER_XPATH))
								.getText();
						assertEquals(UILablesRepo.VIDEO_REPORT_HEADER, popUpMsg);

						String popUpAlertContent = driver
								.findElement(
										By.xpath(XpathObjectRepo.VIDEO_REPORTVIDEO_ALERTWITHOUTLOGIN_SPAN_XPATH))
								.getText();
						assertEquals(UILablesRepo.VIDEO_REPORT_ALERT_CONTENT,
								popUpAlertContent);

						String popUpOkButton = driver
								.findElement(
										By.xpath(XpathObjectRepo.VIDEO_REPORTVIDEO_ALERTWITHOUTLOGIN_OKBTN_XPATH))
								.getText();
						assertEquals(
								UILablesRepo.VIDEO_LIKEANDREPORT_OK_BUTTON,
								popUpOkButton);

						// Click on OK report button.
						Thread.sleep(sleepTime);
						driver.findElement(
								By.xpath(XpathObjectRepo.VIDEO_REPORTVIDEO_ALERTWITHOUTLOGIN_OKBTN_XPATH))
								.click();

						flashApp.callFlashObject("pause");
						Thread.sleep(sleepTime);
						driver.navigate().back();
						Thread.sleep(XidioConstant.fivesec);
					}
				}
			}
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
