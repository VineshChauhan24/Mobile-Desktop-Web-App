package comcast.test.app.testCases.videoManagement.videoSubscriptionManagement.freeSubscribedChannels.MyChannelsPage;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.subscriptionsPage.common.SubscriptionsPageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.subscribeFreeFeaturedChannelFromHome.DS_SubscribeAFreeChannelFromHomeFeatured;
import comcast.test.config.dataServices.subscribeFreePopularChannelFromHome.DS_SubscribeAFreeChannelFromHomePopularChannels;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyNextAndPrevFunctionality Description: This test case is to
 * verify Next and prev links functionality in My Channels page for registered
 * Watchable Application user.
 * **/

public class VerifyNextAndPrevFunctionality extends BaseTest {

	// DS_SubscribeAFreeChannelFromHomeFeatured subscribeFreeHomeChannel=new
	// DS_SubscribeAFreeChannelFromHomeFeatured();
	DS_SubscribeAFreeChannelFromHomePopularChannels subscribeFreeHomeChannel = new DS_SubscribeAFreeChannelFromHomePopularChannels();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	SubscriptionsPageCommonFunctions subscriptionsPageCommonFun = new SubscriptionsPageCommonFunctions();

	@Test
	public void testVerifyNextAndPrevFunctionality() throws Exception {

		try {
			/*
			 * This Method is to register new user using Gazeebo application and
			 * to change a password.
			 */
			// subscribeFreeHomeChannel.RegisterAndSubscribeAFreeChannelHomeFeatured();
			subscribeFreeHomeChannel
					.RegisterAndSubscribeAFreeChannelFromHomePopularChannels();

			Map<String, List<VideoDetails>> videoDetails = RestAPIServices
					.SubscriptionsAPI();
			List<VideoDetails> subscribedChannels = videoDetails
					.get("subscribedChannelsList");

			if (!driver
					.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
					.getAttribute("class").contains("active")) {
				driver.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
						.click();

			}

			// This Method is used to enter user name and password credentials
			userLogin.UserLoginCredentials(driver);

			driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
					.click();
			Thread.sleep(sleepTime);
			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

				// This method is to ensure Home is Active page when Login into
				// Application.
				// assertionFunction.assertHomeActiveLink();

				// This method is to navigate My Channels Page.
				subscriptionsPageCommonFun.navigateToMyChannelsPage();

				// This method is to ensure My Channels is Active page when
				// Login into Application.
				// assertionFunction.assertMyChannelsActiveLink();

				if (subscribedChannels != null) {
					int loopIndexMax = 0;
					if (subscribedChannels.size() < 4)
						loopIndexMax = subscribedChannels.size();
					else
						loopIndexMax = 4;

					for (int index = 0; index < loopIndexMax; index++) {
						VideoDetails subscribedChannelDetails = subscribedChannels
								.get(index);
						Thread.sleep(sleepTime);
						// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+subscribedChannelDetails.getTitle()+"[\\s\\S]*$"));

						String videoCount = subscribedChannelDetails
								.getNumOfVideos();
						int count = Integer.parseInt(videoCount);
						Thread.sleep(sleepTime);

						if (count > 4) {
							int sectionNo = index + 2;
							// Click on Next link.
							// driver.findElement(By.xpath(".//*[@id='seq-row-list']/div["+sectionNo+"]/div/section/a[2]")).click();
							driver.findElement(
									By.xpath(XpathObjectRepo.MYCHANNELPAGE_CHANNELS_NEXTBTN1_XPATH
											+ sectionNo
											+ "]"
											+ XpathObjectRepo.MYCHANNELPAGE_CHANNELS_NEXTBTN2_XPATH))
									.click();
							// driver.findElement(By.xpath((XpathObjectRepo.FOOTER_VIDEO_CATEGORIES_XPATH
							// +category+"]"+XpathObjectRepo.FOOTER_VIDEO_CATEGORIES_TITLES+section+"]"))).click();

							// Click on Prev link.
							Thread.sleep(sleepTime);
							// driver.findElement(By.xpath(".//*[@id='seq-row-list']/div["+sectionNo+"]/div/section/a[1]")).click();

							driver.findElement(
									By.xpath(XpathObjectRepo.MYCHANNELPAGE_CHANNELS_NEXTBTN1_XPATH
											+ sectionNo
											+ "]"
											+ XpathObjectRepo.MYCHANNELPAGE_CHANNELS_PREVBTN_XPATH))
									.click();
						}
					}
				}

				// This method asserts Footer Logo and It's Text.
				assertionFunction.assertFooterLogo();

				// This method asserts Footer Links.
				assertionFunction.assertFooterCopyRight();

				// This method is used to logout from Gazeebo Application.
				userLogin.LogOut(driver);
			}
			/*
			 * Below lines are commented because this script is to verify only
			 * NEXT and PREVIOUS links in channels details page. Below methods
			 * are not applicable for this functionality.
			 * 
			 * //This method unsubscribe a subscribed channels.
			 * subscriptionsPageCommonFun.unSubscribeASubscribedChannels();
			 * 
			 * 
			 * 
			 * //This method is to ensure Login page is displayed when user Sign
			 * Out from Application. assertionFunction.assertLoginPageDetails();
			 */
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
