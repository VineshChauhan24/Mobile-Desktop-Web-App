package comcast.test.app.testCases.videoManagement.videoSubscriptionManagement.unSubscribeAChannels;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.constant.XidioConstant;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.subscriptionsPage.common.SubscriptionsPageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.subscribeFreeFeaturedChannelFromHome.DS_SubscribeAFreeChannelFromHomeFeatured;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: UnsubscribeAHomeFeaturedSubscribedChannel Description: This test
 * case allows a user to unsubscribe the already free subscribed channel from
 * the 'Featured' section of 'Home' Page by logging into Watchable application.
 */

public class UnsubscribeAHomeFeaturedSubscribedChannel extends BaseTest {

	DS_SubscribeAFreeChannelFromHomeFeatured subscribeFreeHomeChannel = new DS_SubscribeAFreeChannelFromHomeFeatured();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	SubscriptionsPageCommonFunctions subscriptionsPageCommonFun = new SubscriptionsPageCommonFunctions();

	@Test
	public void testUnsubscribeAHomeFeaturedSubscribedChannel()
			throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.HomeFeaturedAPI();
		List<VideoDetails> ChannelsList = videoDetails.get("show");

		try {
			// This method registers new user and subscribe a free channel For
			// Home Featured
			subscribeFreeHomeChannel
					.RegisterAndSubscribeAFreeChannelHomeFeatured();

			// driver.findElement(By.linkText("Log In")).click();
			if (!driver
					.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
					.getAttribute("class").contains("active")) {
				driver.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
						.click();

			}

			// This Method is used to enter user name and password credentials
			userLogin.UserLoginCredentials(driver);

			// driver.findElement(By.id("user_login")).click();
			driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
					.click();
			Thread.sleep(sleepTime);
			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

				driver.findElement(
						By.xpath(XpathObjectRepo.TOP_MENU_HOME_BUTTON_XPATH))
						.click();

				Thread.sleep(sleepTime);
				// This method is to ensure Home is Active page when Login into
				// Application.
				assertionFunction.assertHomeActiveLink();

				// This method is to navigate My Channels Page.
				subscriptionsPageCommonFun.navigateToMyChannelsPage();

				// This method is to ensure My Channels is Active page when
				// Login
				// into Application.
				assertionFunction.assertMyChannelsActiveLink();

				driver.findElement(
						By.linkText(ChannelsList.get(
								XidioConstant.selectFeaturedChannel).getTitle()))
						.click();

				// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+ChannelsList.get(XidioConstant.selectFeaturedChannel).getTitle()+"[\\s\\S]*$"));

				assertTrue(driver
						.findElement(
								By.xpath(XpathObjectRepo.CHANNELDETAILCHANNELTITLE_XPATH))
						.getText()
						.matches(
								ChannelsList.get(
										XidioConstant.selectFeaturedChannel)
										.getTitle()));

				Thread.sleep(sleepTime);
				// driver.findElement(By.xpath(".//*[@id='content-wrap']/article/div/div[1]/div/span[2]/a/img")).click();

				// driver.findElement(By.xpath(".//*[@id='cluetip']/div[1]/div/div[2]/form/fieldset/input")).click();

				boolean isFollowNowPresent = false;
				// Verify for the Channel subscription by checking the
				// "Follow Now" Button
				try {
					driver.findElement(By
							.xpath(XpathObjectRepo.CHANNELUNFOLLOWNOWIMAGE_XPATH));
					isFollowNowPresent = true;
				} catch (NoSuchElementException nse) {
					System.out.println("In exception");
				}
				// click on the "Follow Now" button if present else go back to
				// Home Page
				if (isFollowNowPresent) {
					driver.findElement(
							By.xpath(XpathObjectRepo.CHANNELUNFOLLOWNOWIMAGE_XPATH))
							.click();

				}

				Thread.sleep(sleepTime);
				// assertEquals("You have been unsubscribed.",
				// driver.findElement(By.xpath(".//*[@id='cluetip']/div[1]/div/div[2]/div")).getText());

				// Verify Successful Un-subscription message is displayed

				assertTrue(driver.findElement(
						By.xpath(XpathObjectRepo.FOLLOWUP_MSG_XPATH))
						.isDisplayed());

				// This method is to navigate My Channels Page.
				subscriptionsPageCommonFun.navigateToMyChannelsPage();

				// This method is to ensure My Channels is Active page when
				// Login
				// into Application.
				assertionFunction.assertMyChannelsActiveLink();

				// assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+ChannelsList.get(XidioConstant.selectFeaturedChannel).getTitle())+"[\\s\\S]*$");

				assertFalse(driver
						.findElement(
								By.xpath(XpathObjectRepo.MYCHANNEL_SUBSCRIBEDCHANNELS_XPATH))
						.getText()
						.contains(
								ChannelsList.get(
										XidioConstant.selectFeaturedChannel)
										.getTitle()));

				// This method asserts Footer Logo and It's Text.
				assertionFunction.assertFooterLogo();

				// This method asserts Footer Copy Right Links.
				assertionFunction.assertFooterCopyRight();

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
