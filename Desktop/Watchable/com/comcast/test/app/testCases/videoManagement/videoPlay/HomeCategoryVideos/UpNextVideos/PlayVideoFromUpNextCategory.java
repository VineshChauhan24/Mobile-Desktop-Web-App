package comcast.test.app.testCases.videoManagement.videoPlay.HomeCategoryVideos.UpNextVideos;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.constant.XidioConstant;
import comcast.test.app.common.exception.ComcastTestException;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: PlayVideoFromUpNextCategory Description: This test case is to
 * play the video by selecting the videos present under 'Up Next' section in
 * Home page.
 * **/

public class PlayVideoFromUpNextCategory extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testPlayVideoFromUpNextCategory() throws Exception {

		List<VideoDetails> videoDetails = RestAPIServices.upNextAPI();

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);
			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

				// This method asserts Home link to ensure Home is Active page
				// when
				// Login into Application.
				assertionFunction.assertHomeActiveLink();

				assertTrue(driver
						.findElement(
								By.xpath(XpathObjectRepo.YOUAREWATCHINGHEADERLABEL_XPATH))
						.getText()
						.equalsIgnoreCase(UILablesRepo.HOMEPAGE_YOUAREWATCHING));
				Thread.sleep(sleepTime);

				// String YouAreWatchingVideosCount=
				// driver.findElement(By.xpath("//div[@id='upNext']/descendant::li[contains(@class,'episode')]/descendant::h1")).getText();

				// String
				// x=findElements(driver.findElement(By.xpath("//div[@id='upNext']/descendant::li[contains(@class,'episode')]/descendant::h1")).getSize();

				int YouAreWatchingVideosCount = driver
						.findElements(
								(By.xpath(XpathObjectRepo.HOMEPAGEYOUAREWATCHINGVIDEOSLIST_XPATH)))
						.size();
				System.out
						.println("Total number of Videos in YOU ARE WATCHING section ="
								+ YouAreWatchingVideosCount);

				if (YouAreWatchingVideosCount <= 10) {
					System.out
							.println("Total number of Videos in YOU ARE WATCHING section ="
									+ YouAreWatchingVideosCount);
				} else if (YouAreWatchingVideosCount > 10) {
					throw new ComcastTestException(
							"PlayVideoFromUpNextCategory",
							"testPlayVideoFromUpNextCategory",
							"Total number of Videos in YOU ARE WATCHING section is more than 10");
				}

				// driver.findElement(By.linkText(videoDetails.get(XidioConstant.selectVideo).getTitle())).click();
				String videoTitle = driver
						.findElement(
								By.xpath(XpathObjectRepo.HOMEPAGEYOUAREWATCHINGFIRSTVIDEOLINK_XPATH))
						.getText();
				driver.findElement(
						By.xpath(XpathObjectRepo.HOMEPAGEYOUAREWATCHINGFIRSTVIDEOLINK_XPATH))
						.click();
				Thread.sleep(sleepTime);

				// driver.findElement(By.xpath(orProUtil.getProperty("showEpisodeList_XPATH"))).click();

				Thread.sleep(sleepTime);
				Thread.sleep(sleepTime);
				Thread.sleep(sleepTime);

				// Manoj: Checking Selected video is partially played
				Thread.sleep(sleepTime);

				WebElement playFromPresent = driver.findElement(By
						.xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH));

				if (playFromPresent.isDisplayed() == true) {
					// Thread.sleep(sleepTime);
					// Thread.sleep(sleepTime);
					driver.findElement(
							By.xpath(XpathObjectRepo.VIDEOPLAYFROMBUTTON_XPATH))
							.click();
				}

				Thread.sleep(sleepTime);
				Thread.sleep(sleepTime);
				Thread.sleep(sleepTime);

				Thread.sleep(sleepTime);
				// div[@id='upNext']/descendant::li[1]/descendant::h1//div[@id='upNext']/descendant::li[1]/descendant::h1

				// System.out.println("videoDetails.get(XidioConstant.selectVideo).getTitle() : "
				// + videoDetails.get(XidioConstant.selectVideo).getTitle());
				// assertTrue(driver.findElement(By.xpath(XpathObjectRepo.VIDEODETAILSPAGETITLE_XPATH)).getText().contains(videoDetails.get(XidioConstant.selectVideo).getTitle()));
				assertTrue(driver
						.findElement(
								By.xpath(XpathObjectRepo.VIDEODETAILSPAGETITLE_XPATH))
						.getText().contains(videoTitle));

				// This method asserts Home and My Channels inactive link when
				// user
				// clicks on Bundle/Channel/Show.
				assertionFunction.assertAllInActiveLink();

				// This assertion matches "YOU ARE WATCHING" header in Video
				// details
				// page

				assertTrue(driver
						.findElement(
								By.xpath(XpathObjectRepo.VIDEOPAGEYOUAREWATCHINGTITLE_XPATH))
						.getText()
						.matches(UILablesRepo.HOMEPAGE_YOUAREWATCHING));

				// This method asserts Watchable Logo.
				assertionFunction.assertWatchableLogo();

				// This method asserts Watchable Logo.

				// This method asserts Watchable Logo.

				assertionFunction.assertWatchableLogo();

				// This method asserts Help link.
				assertionFunction.assertHelpLink();

				// This method asserts Search Text Box and its value.
				assertionFunction.assertSearchTextBox();

				Thread.sleep(sleepTimeForVideoPlay);
				// This method asserts Footer Logo and It's Text.
				assertionFunction.assertFooterLogo();

				// This method asserts Footer Copy Right Links.
				assertionFunction.assertFooterCopyRight();

				// This method is used to logout from watchable Application.
				userLogin.LogOut(driver);
			}
			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			// assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
