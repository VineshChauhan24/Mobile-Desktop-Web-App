/**
 * @author Manoj.P
 * Created Date: 10 DEC 2014
 * Last Update : 10 DEC 2014
 */
package comcast.test.app.testCases.videoManagement.videoSubscriptionManagement.subscribeFreeChannels;

import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Reporter;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: SubscribeAChannelFromShowDetailPage Description: This test case
 * subscribes a channel from show details page
 * **/

public class SubscribeAChannelFromShowDetailPage extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	String channelSubscribed = "";
	String channelTitle = "";
	boolean subscribeSuccess = false;
	boolean match = false;

	@Test
	public void testSubscribeAChannelFromShowDetailPage() throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nHomePopularShowsAPIs();
		List<VideoDetails> popularShowList = videoDetails.get("popularShows");

		try {

			// Login to application.

			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);
			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

				Thread.sleep(sleepTime);

				// This method is to ensure Home is Active page when Login into
				// Application.
				assertionFunction.assertHomeActiveLink();

				// This method asserts Popular Shows Header Title.
				assertionFunction.assertPopularShowsTitle();

				// This method is to scroll UI to Popular Channels Section.
				homePageCommonFun.scrollToPopularShowsSection();

				// int loopMaxIndex = 0;
				if (popularShowList != null) {

					VideoDetails ShowList = popularShowList.get(0);

					// Section and selects a Show.
					homePageCommonFun.selectPopularShows(ShowList.getTitle());

					VideoDetails showVideo = popularShowList.get(0);

					assertTrue(
							"Show details page is not displayed",
							driver.findElement(
									By.xpath(XpathObjectRepo.SHOWDETAILSHOWTITLE_XPATH))
									.getText().matches(showVideo.getTitle()));

					log.info("Successfully navigated to show detail page");

					channelSubscribed = driver
							.findElement(
									By.xpath(XpathObjectRepo.SHOWDETAILCHANNELTITLE_XPATH))
							.getText();

					Thread.sleep(sleepTime);
					boolean isFollowNowPresent = false;

					// Verify for the Channel subscription by checking the
					// "Follow Now" Button
					try {
						driver.findElement(By
								.xpath(XpathObjectRepo.CHANNELFOLLOWNOWIMAGE_XPATH));

						isFollowNowPresent = true;
					} catch (NoSuchElementException nse) {
						log.info("Selected channel is already subscribed");
						subscribeSuccess = true;

					}
					// click on the "Follow Now" button if present else go back
					// to
					// Home Page
					if (isFollowNowPresent) {
						driver.findElement(
								By.xpath(XpathObjectRepo.CHANNELFOLLOWNOWIMAGE_XPATH))
								.click();
						Thread.sleep(sleepTime);
						log.info("Successfully clicked on 'FOLLOW NOW' button");
						subscribeSuccess = true;
					}

				}

				if (subscribeSuccess == true) {

					// Click on the My Channels Button on the Navigation Menu

					driver.findElement(
							By.xpath(XpathObjectRepo.TOP_MENU_MYCHANNELS_BUTTON_XPATH))
							.click();
					Thread.sleep(sleepTime);

					int myChannelCount = driver
							.findElements(
									By.xpath(XpathObjectRepo.MYCHANNELS_CHANNELS_XPATH))
							.size();
					Thread.sleep(sleepTime);

					if (myChannelCount > 0) {

						for (int i = 0; i <= myChannelCount; i++) {

							channelTitle = driver
									.findElement(
											By.xpath(XpathObjectRepo.MYCHANNELS_CHANNELS_TITLE_PART_ONE_XPATH
													+ i
													+ 2
													+ XpathObjectRepo.MYCHANNELS_CHANNELS_TITLE_PART_TWO_XPATH))
									.getText();
							if (channelTitle.compareTo(channelSubscribed) == 0) {
								match = true;
								break;
							}
						}

						assertTrue(
								"Subscribed channel not present in my channel list",
								match);
						log.info("Subscribed channel '" + channelSubscribed
								+ "' present in my channel list");
						Reporter.log("Subscribed channel '" + channelSubscribed
								+ "' present in my channel list");
					}

				}

				else {
					log.info("Subcription to a channel failed");
				}

				// This method is used to logout from Watchable Application.
				userLogin.LogOut(driver);
			}
			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
