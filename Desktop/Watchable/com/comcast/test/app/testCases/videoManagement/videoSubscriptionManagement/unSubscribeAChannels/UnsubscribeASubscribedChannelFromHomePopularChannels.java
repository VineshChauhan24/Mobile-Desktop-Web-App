package comcast.test.app.testCases.videoManagement.videoSubscriptionManagement.unSubscribeAChannels;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.constant.XidioConstant;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.subscriptionsPage.common.SubscriptionsPageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.configServices.utils.TestDataGenerator;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.subscribeFreePopularChannelFromHome.DS_SubscribeAFreeChannelFromHomePopularChannels;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: UnsubscribeASubscribedChannelFromHomePopularChannels Description:
 * This test case allows a user to unsubscribe the already subscribed channel
 * from the 'Popular Channels' section of 'Home' Page by logging into Watchable
 * application.
 */

public class UnsubscribeASubscribedChannelFromHomePopularChannels extends
		BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	DS_SubscribeAFreeChannelFromHomePopularChannels subHomePopularChannels = new DS_SubscribeAFreeChannelFromHomePopularChannels();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	SubscriptionsPageCommonFunctions subscriptionsPageCommonFun = new SubscriptionsPageCommonFunctions();
	TestDataGenerator proUtil = new TestDataGenerator();

	@Test
	public void testUnsubscribeASubscribedChannelFromHomePopularChannels()
			throws Exception {
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nPopularAPI();
		List<VideoDetails> ChannelsList = videoDetails
				.get("popularChannelsList");

		proUtil.load(new FileInputStream(new File("com/data.properties")));

		// String myChnlErrorMessage =
		// proUtil.getProperty("MYCHANNELS_ERROR_MESSAGE");

		try {
			/*
			 * This Method is to register new user using Gazeebo application and
			 * to change a password.
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
											+ XpathObjectRepo.LINKHREF_XPATH))
							.getText().equalsIgnoreCase(popChnl.getTitle()));
					// Click on the title to land in Channels Page
					String hghg = "driver.findElement(By.xpath("
							+ XpathObjectRepo.HOMEPOPULARCHANNELSALLLIST_XPATH
							+ "[" + i + "]" + XpathObjectRepo.LINKHREF_XPATH
							+ ")";

					if (driver
							.findElement(
									By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSALLLIST_XPATH
											+ "["
											+ i
											+ "]"
											+ XpathObjectRepo.LINKHREF_XPATH))
							.isEnabled()) {

						try {

							WebElement channel = driver
									.findElement(By
											.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSALLLIST_XPATH
													+ "["
													+ i
													+ "]"
													+ XpathObjectRepo.LINKHREF_XPATH));

							channel.click();
						} catch (NoSuchElementException nse) {
							log.error("Channel is not present");
						}

						Thread.sleep(sleepTime);
						boolean isFollowNowPresent = false;
						// Verify for the Channel subscription by checking the
						// "Follow Now" Button
						try {
							driver.findElement(By
									.xpath(XpathObjectRepo.CHANNELUNFOLLOWNOWIMAGE_XPATH));
							channelTitle = popChnl.getTitle();
							isFollowNowPresent = true;
						} catch (NoSuchElementException nse) {
							log.error("Channel not present");
						}
						// click on the "Follow Now" button if present else go
						// back to
						// Home Page
						if (isFollowNowPresent) {
							driver.findElement(
									By.xpath(XpathObjectRepo.CHANNELUNFOLLOWNOWIMAGE_XPATH))
									.click();
							break;
						} else {
							driver.navigate().back();
							i++;
							Thread.sleep(sleepTime);
						}
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

				int myChannelCount = driver
						.findElements(
								By.xpath(XpathObjectRepo.MYCHANNEL_SUBSCRIBEDCHANNELS_XPATH))
						.size();
				if (myChannelCount > 0) {
					Map<String, List<VideoDetails>> subChnlVideoDetails = RestAPIServices
							.subscribedChannelDetails();
					List<VideoDetails> subscribedChannels = subChnlVideoDetails
							.get("subscribedChannels");

					for (int j = 0; j < subscribedChannels.size(); j++) {
						assertFalse(subscribedChannels.get(j).getTitle()
								.equalsIgnoreCase(channelTitle));
						int pos = j + 1;
						assertFalse(driver
								.findElement(
										By.xpath(XpathObjectRepo.MYCHANNELSCHANNELTITLELABEL_XPATH
												+ "["
												+ pos
												+ "]"
												+ XpathObjectRepo.LINKHREF_XPATH))
								.getText().equalsIgnoreCase(channelTitle));
					}
				} else {
					assertTrue(
							UILablesRepo.MYCHANNEL_EMPTY_MESSAGE_TEXT,
							driver.findElement(
									By.xpath(XpathObjectRepo.MYCHANNEL_EMPTY_MESSAGE_XPATH))
									.isDisplayed());
				}

				// This method asserts Footer Logo and It's Text.
				assertionFunction.assertFooterLogo();

				// This method asserts Footer Copy Right Links.
				assertionFunction.assertFooterCopyRight();

				// This method is used to logout from Watchable Application.
				userLogin.LogOut(driver);
			}
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
