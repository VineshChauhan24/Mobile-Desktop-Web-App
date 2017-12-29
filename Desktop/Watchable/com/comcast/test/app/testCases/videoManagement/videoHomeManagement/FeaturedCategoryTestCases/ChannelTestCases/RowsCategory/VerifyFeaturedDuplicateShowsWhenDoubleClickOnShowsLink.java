package comcast.test.app.testCases.videoManagement.videoHomeManagement.FeaturedCategoryTestCases.ChannelTestCases.RowsCategory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.constant.XidioConstant;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyFeaturedDuplicateShowsWhenDoubleClickOnShowsLink
 * Description: This test case validates whether duplicate Shows are displayed
 * after clicking double clicking on SHOWS link, in channel page for the channel
 * present under Featured section in Home page by logging registered user into
 * Watchable application.
 * **/

public class VerifyFeaturedDuplicateShowsWhenDoubleClickOnShowsLink extends
		BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyFeaturedDuplicateShowsWhenDoubleClickOnShowsLink()
			throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.featuredChannelsList();
		List<VideoDetails> channelList = videoDetails
				.get("featuredChannelsList");

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is to ensure Home is Active page when Login into
			// Application.
			// assertionFunction.assertHomeActiveLink();

			// This method is to assert Watchable Top Middle Menu and to ensure
			// its collapsed.
			// assertionFunction.assertGazeeboTopMiddleMenu();

			// This method asserts featured title.
			assertionFunction.assertFeaturedTitle();

			// This Method verifies Channel present in Home Featured list and
			// selects a Channel.
			homePageCommonFun.selectFeaturedChannel(channelList.get(
					XidioConstant.selectFeaturedChannel).getTitle());

			assertTrue(driver
					.findElement(By.cssSelector("BODY"))
					.getText()
					.matches(
							"^[\\s\\S]*"
									+ channelList
											.get(XidioConstant.selectFeaturedChannel)
											.getTitle() + "[\\s\\S]*$"));

			Thread.sleep(sleepTime);
			Actions action = new Actions(driver);
			action.doubleClick(driver.findElement(By.linkText(orProUtil
					.getProperty("SHOWSLINK"))));
			action.perform();

			int divSectionNo = 2 + Integer.parseInt(channelList.get(0)
					.getNumOfShows());
			Thread.sleep(sleepTime);
			boolean isDuplicate = driver
					.findElements(
							By.xpath(".//*[@id='content-wrap']/article/section/article/div/div["
									+ divSectionNo + "]/section")).size() > 0;
			if (isDuplicate == true) {
				String getFirstSectionShowTitle = driver
						.findElement(
								By.xpath(".//*[@id='content-wrap']/article/section/article/div/div[2]/section/ul/li/article/h1/a"))
						.getText();
				String getSectondSectionShowTitle = driver
						.findElement(
								By.xpath(".//*[@id='content-wrap']/article/section/article/div/div["
										+ divSectionNo
										+ "]/section/ul/li/article/h1/a"))
						.getText();

				if (getFirstSectionShowTitle
						.equalsIgnoreCase(getSectondSectionShowTitle)) {
					assertFalse(driver
							.findElement(By.cssSelector("BODY"))
							.getText()
							.matches(
									"^[\\s\\S]*" + getSectondSectionShowTitle
											+ "[\\s\\S]*$"));
				} else
					assertTrue(driver
							.findElement(By.cssSelector("BODY"))
							.getText()
							.matches(
									"^[\\s\\S]*" + getFirstSectionShowTitle
											+ "[\\s\\S]*$"));
			}

			// This method asserts Watchable Logo.
			assertionFunction.assertWatchableLogo();

			Thread.sleep(sleepTime);
			assertTrue(driver
					.findElement(By.cssSelector("BODY"))
					.getText()
					.matches(
							"^[\\s\\S]*"
									+ channelList.get(XidioConstant.selectShow)
											.getTitle() + "[\\s\\S]*$"));

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Copy Right Links.
			assertionFunction.assertFooterCopyRight();

			// This method is used to logout from Watchable Application.
			userLogin.LogOut(driver);

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
