package comcast.test.app.testCases.videoManagement.videoHomeManagement.PopularChannelsCategoryTestCases.ChannelTestCases.ShowsCategory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.constant.XidioConstant;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyShowsUnderChannelForHomePopularChannels Description: This
 * test case validates whether shows are displayed after clicking on UNWATCHED
 * link in channel page for the channel present under Popular Channels section
 * in Home page by logging registered user into Watchable application.
 * **/

public class VerifyShowsUnderChannelForHomePopularChannels extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyShowsUnderChannelForHomePopularChannels()
			throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nPopularAPI();
		List<VideoDetails> channelList = videoDetails
				.get("popularChannelsList");
		List<VideoDetails> popularChannelShowList = videoDetails
				.get("popularChannelShowsList");

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

				// This method asserts Popular Channels title.
				assertionFunction.assertPopularChannelsTitle();

				if (channelList != null) {
					// This Method verifies Channel present in Popular Channel
					// Section and selects a Channel.
					homePageCommonFun.selectPopularChannel(channelList.get(
							XidioConstant.selectPopularChannel).getTitle());

					int loopMaxIndex = 0;
					if (popularChannelShowList.size() < 4)
						loopMaxIndex = popularChannelShowList.size();
					else
						loopMaxIndex = 4;

					for (int index = 0; index < loopMaxIndex; index++) {
						VideoDetails popularShowList = popularChannelShowList
								.get(index);
						// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+popularShowList.getTitle()+"[\\s\\S]*$"));
						assertTrue(driver
								.findElement(
										By.xpath(XpathObjectRepo.CHANNELDETAILSSHOWLIST_XPATH))
								.getText().contains(popularShowList.getTitle()));

					}
				} else {
					boolean isPresent;
					// Lekshmi : Changed Object identifier to fetch the popular
					// channel title
					// Manoj: Object changed and added to XpathObjectRepo file
					// isPresent=driver.findElement(By.xpath("//div[@id='popular_channels']/descendant::section[@class='view-row']")).findElements(By.xpath("//div[@id='popular_channels']/descendant::li[contains(@class, 'cell-channel')][1]/descendant::h1/a")).size()>0;
					isPresent = driver
							.findElement(
									By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSROW_XPATH))
							.findElements(
									By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSFIRSTTITLELABEL_XPATH))
							.size() > 0;
					if (isPresent == true) {
						// String
						// channelName=driver.findElement(By.xpath("//div[@id='popular_channels']/descendant::section[@class='view-row']")).findElement(By.xpath("//div[@id='popular_channels']/descendant::li[contains(@class, 'cell-channel')][1]/descendant::h1/a")).getText();
						String channelName = driver
								.findElement(
										By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSROW_XPATH))
								.findElement(
										By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSFIRSTTITLELABEL_XPATH))
								.getText();
						// assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+channelName+"[\\s\\S]*$"));
						assertFalse(driver
								.findElement(
										By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSFIRSTTITLELABEL_XPATH))
								.getText().matches(channelName));
					}
				}

				// This method asserts Watchable Logo.
				assertionFunction.assertWatchableLogo();

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
