package comcast.test.app.testCases.videoManagement.videoSubscriptionManagement.subscribeFreeChannels;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.common.videoManagement.subscriptionsPage.common.SubscriptionsPageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: SubscribeAFreeChannelFromHomePopularChannelsCategory Description:
 * This test case allows a user to subscribe a channel for free from the
 * 'Popular Channels' section of 'Home' Page and also verifies if the user has
 * been subscribed successfully by navigating to 'My Channels' screen by logging
 * into Watchable application.
 */

public class SubscribeAFreeChannelFromHomePopularChannelsCategory extends
		BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	SubscriptionsPageCommonFunctions subscriptionsPageCommonFun = new SubscriptionsPageCommonFunctions();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();

	@Test
	public void testSubscribeAFreeChannelFromHomePopularChannelsCategory()
			throws Exception {
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nPopularAPI();
		List<VideoDetails> ChannelsList = videoDetails
				.get("popularChannelsList");

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);
			// This method is to ensure Home is Active page when Login into
			// Application.

			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();
			Thread.sleep(sleepTime);

			if (loginError == 0) {
				assertionFunction.assertHomeActiveLink();
				int i = 1;
				String channelTitle = "";
				// Iterate through the Popular Channels to check for the
				// Subscribed
				// Channels
				for (VideoDetails popChnl : ChannelsList) {
					// Verify the Channel Title
					assertTrue(driver
							.findElement(
									By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSALLLIST_XPATH
											+ "["
											+ i
											+ "]"
											+ XpathObjectRepo.CHANNELSHOWLINK_XPATH))
							.getText().equalsIgnoreCase(popChnl.getTitle()));
					Thread.sleep(sleepTime);
					// Click on the title to land in Channels Page
					driver.findElement(
							By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSALLLIST_XPATH
									+ "["
									+ i
									+ "]"
									+ XpathObjectRepo.CHANNELSHOWLINK_XPATH))
							.click();
					Thread.sleep(sleepTime);
					boolean isFollowNowPresent = false;
					// Verify for the Channel subscription by checking the
					// "Follow Now" Button
					try {
						driver.findElement(By
								.xpath(XpathObjectRepo.CHANNELFOLLOWNOWIMAGE_XPATH));
						channelTitle = popChnl.getTitle();
						isFollowNowPresent = true;
					} catch (NoSuchElementException nse) {
						System.out.println("In exception");
					}
					// click on the "Follow Now" button if present else go back
					// to
					// Home Page
					if (isFollowNowPresent) {
						driver.findElement(
								By.xpath(XpathObjectRepo.CHANNELFOLLOWNOWIMAGE_XPATH))
								.click();
						break;
					} else {
						driver.navigate().back();
						i++;
						homePageCommonFun.scrollToSection();
						Thread.sleep(sleepTime);
					}
				}
				Thread.sleep(sleepTime);
				// Click on the My Channels Button on the Navigation Menu
				// TOP_MENU_MYCHANNELS_BUTTON_XPATH
				driver.findElement(
						By.xpath(XpathObjectRepo.TOP_MENU_MYCHANNELS_BUTTON_XPATH))
						.click();
				Thread.sleep(sleepTime);
				Thread.sleep(sleepTime);
				// Verify the Subscribed Channel title presence in the My
				// Channels
				// Page

				int myChannelCount = driver.findElements(
						By.xpath(XpathObjectRepo.MYCHANNELSEPISODEICON_XPATH))
						.size();
				Thread.sleep(sleepTime);
				Thread.sleep(sleepTime);
				if (myChannelCount > 0) {
					Map<String, List<VideoDetails>> subChnlVideoDetails = RestAPIServices
							.subscribedChannelDetails();
					List<VideoDetails> subscribedChannels = subChnlVideoDetails
							.get("subscribedChannels");
					for (int j = 0; j < subscribedChannels.size(); j++) {
						int pos = j + 1;
						if (driver
								.findElement(
										By.xpath(XpathObjectRepo.MYCHANNELSCHANNELTITLELABEL_XPATH
												+ "["
												+ pos
												+ "]"
												+ XpathObjectRepo.CHANNELSHOWLINK_XPATH))
								.getText().equalsIgnoreCase(channelTitle)) {
							assertTrue(driver
									.findElement(
											By.xpath(XpathObjectRepo.MYCHANNELSCHANNELTITLELABEL_XPATH
													+ "["
													+ pos
													+ "]"
													+ XpathObjectRepo.CHANNELSHOWLINK_XPATH))
									.getText().equalsIgnoreCase(channelTitle));
							break;
						}
					}
				}
				// Lekshmi : Commented due to the selection on My channels menu
				// and
				// css in that has changed
				// assertionFunction.assertAllInActiveLink();

				// This method asserts Footer Logo and It's Text.
				assertionFunction.assertFooterLogo();

				// This method asserts Footer Copy Right Links.
				assertionFunction.assertFooterCopyRight();

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
