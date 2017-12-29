package comcast.test.app.testCases.videoManagement.videoHomeManagement.WatchingCategoryTestCases.HomePageTCs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.subscribeFreeFeaturedChannelFromHome.DS_SubscribeAFreeChannelFromHomeFeatured;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifySubscribedChannelVideosUnderWatching Description: This test
 * case validates whether the Watching section is displayed subscribed channels
 * videos under watching by logging registered user into Watchable application.
 * **/

public class VerifySubscribedChannelVideosUnderWatching extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	DS_SubscribeAFreeChannelFromHomeFeatured subscribeFreeHomeChannel = new DS_SubscribeAFreeChannelFromHomeFeatured();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testVerifySubscribedChannelVideosUnderWatching()
			throws Exception {

		subscribeFreeHomeChannel.RegisterAndSubscribeAFreeChannelHomeFeatured();

		Thread.sleep(sleepTime);
		List<VideoDetails> UpNextvideoDetails = RestAPIServices.upNextAPI();

		try {
			// This method is used to enter user name and password credential
			Thread.sleep(sleepTime);

			if (!driver
					.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
					.getAttribute("class").contains("active")) {
				driver.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
						.click();

			}

			userLogin.UserLoginCredentials(driver);

			driver.findElement(
					By.xpath(XpathObjectRepo.MYACCOUNT_SAVE_BTN_XPATH)).click();

			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

				// driver.findElement(By.id("user_login")).click();
				// driver.findElement(By.xpath("//div[@id='access-menu']/descendant::a[@class='login-bttn']")).click();

				Thread.sleep(sleepTime);

				// Additional action for below assertion to pass

				if (!driver
						.findElement(
								By.xpath(XpathObjectRepo.TOP_MENU_HOME_BUTTON_XPATH))
						.getAttribute("class").contains("active")) {
					driver.findElement(
							By.xpath(XpathObjectRepo.TOP_MENU_HOME_BUTTON_XPATH))
							.click();

				}
				// This method is to ensure Home is Active page when Login into
				// Application.

				assertionFunction.assertHomeActiveLink();

				Thread.sleep(sleepTime);
				// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Featured[\\s\\S]*$"));
				assertTrue(driver
						.findElement(
								By.xpath(XpathObjectRepo.HOMEFEATUREDCONTAINERLABEL_XPATH))
						.getText()
						.equalsIgnoreCase(UILablesRepo.HOMEPAGE_FEATURED));

				int loopIndex = 0;
				boolean isVideoPresent = false;
				if (UpNextvideoDetails != null) {
					if (UpNextvideoDetails.size() < 10)
						loopIndex = UpNextvideoDetails.size();
					else
						loopIndex = 10;
					boolean isPresent;
					for (int index = 0; index < loopIndex; index++) {
						int position = index + 1;
						Thread.sleep(sleepTime);
						VideoDetails upNextVideo = UpNextvideoDetails
								.get(index);

						isPresent = driver
								.findElements(
										By.xpath(
												XpathObjectRepo.HOMEPAGEYOUAREWATCHINGVIDEOSLINK_XPATH)
												.linkText(
														upNextVideo.getTitle()))
								.size() > 0;
						if (isPresent == true) {
							isVideoPresent = true;

							int ele = driver
									.findElements(
											By.xpath(XpathObjectRepo.HOMEPAGEYOUAREWATCHINGVIDEOSLINK_XPATH
													+ "["
													+ position
													+ "]"
													+ XpathObjectRepo.LINKHREF_XPATH))
									.size();
							if (ele == 1) {
								assertTrue(driver
										.findElement(
												By.xpath(XpathObjectRepo.HOMEPAGEYOUAREWATCHINGVIDEOSLINK_XPATH
														+ "["
														+ position
														+ "]"
														+ XpathObjectRepo.LINKHREF_XPATH))
										.getText()
										.equalsIgnoreCase(
												upNextVideo.getTitle()));
							}
						} else {
							boolean nextLink = driver
									.findElements(
											By.xpath(XpathObjectRepo.HOMEPAGEYOUAREWATCHINGPAGINATIONNEXTBUTTON_XPATH))
									.size() > 0;
							if (nextLink == true)
								driver.findElement(
										By.xpath(XpathObjectRepo.HOMEPAGEYOUAREWATCHINGPAGINATIONNEXTBUTTON_XPATH))
										.click();
							position = index - 1;
							Thread.sleep(sleepTime);

						}
					}
				}
				if (!isVideoPresent) {

					assertFalse(driver
							.findElement(
									By.xpath(XpathObjectRepo.YOUAREWATCHINGHEADERLABEL_XPATH))
							.getText()
							.equalsIgnoreCase(
									UILablesRepo.HOMEPAGE_YOUAREWATCHING));
				}
				// This method is used to logout from Watchable Application.
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
