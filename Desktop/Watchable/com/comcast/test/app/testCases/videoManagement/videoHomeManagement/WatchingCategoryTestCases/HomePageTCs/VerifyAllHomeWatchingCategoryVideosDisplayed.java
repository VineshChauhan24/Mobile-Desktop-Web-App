package comcast.test.app.testCases.videoManagement.videoHomeManagement.WatchingCategoryTestCases.HomePageTCs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyAllHomeWatchingCategoryVideosDisplayed Description: This
 * test case is to verify Home Watching category all videos are displayed on UI
 * by comparing with UpNext API response for registered Watchable Application
 * user.
 * **/

public class VerifyAllHomeWatchingCategoryVideosDisplayed extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Rule
	public ErrorCollector errCol = new ErrorCollector();

	@Test
	public void testVerifyAllHomeWatchingCategoryVideosDisplayed()
			throws Exception {

		List<VideoDetails> UpNextvideoDetails = RestAPIServices.upNextAPI();

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
												XpathObjectRepo.HOMEPAGEYOUAREWATCHINGVIDEOSLIST_XPATH)
												.linkText(
														upNextVideo.getTitle()))
								.size() > 0;
						if (isPresent == true) {
							isVideoPresent = true;
							// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+upNextVideo.getTitle()+"[\\s\\S]*$"));

							if (driver
									.findElement(
											By.xpath(XpathObjectRepo.HOMEPAGEYOUAREWATCHINGVIDEOSLINK_XPATH
													+ "["
													+ position
													+ "]"
													+ XpathObjectRepo.LINKHREF_XPATH))
									.getText().trim().length() > 0) {

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
							// boolean
							// nextLink=driver.findElements(By.xpath("//div[@id='upNext']/descendant::a[@class='next']/span")).size()>0;
							// if(nextLink==true)
							// driver.findElement(By.xpath("//div[@id='upNext']/descendant::a[@class='next']/span")).click();
							// Thread.sleep(sleepTime);
							break;
						}
					}
				}
				if (!isVideoPresent) {

					int ele = driver
							.findElements(
									By.xpath(XpathObjectRepo.YOUAREWATCHINGHEADERLABEL_XPATH))
							.size();

					if (ele == 1) {
						assertTrue(driver
								.findElement(
										By.xpath(XpathObjectRepo.YOUAREWATCHINGHEADERLABEL_XPATH))
								.getText()
								.trim()
								.equalsIgnoreCase(
										UILablesRepo.HOMEPAGE_YOUAREWATCHING
												.trim()));
					}
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
