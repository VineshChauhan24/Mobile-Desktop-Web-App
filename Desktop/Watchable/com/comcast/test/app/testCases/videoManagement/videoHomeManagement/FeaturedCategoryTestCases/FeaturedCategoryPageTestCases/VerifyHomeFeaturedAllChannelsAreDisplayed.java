package comcast.test.app.testCases.videoManagement.videoHomeManagement.FeaturedCategoryTestCases.FeaturedCategoryPageTestCases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

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
 * Class Name: VerifyHomeFeaturedAllChannelsAreDisplayed Description: This test
 * case is to verify Home/Featured category all channels are displayed and
 * clickable by comparing with API response for registered Gazeebo Application
 * user.
 **/

public class VerifyHomeFeaturedAllChannelsAreDisplayed extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();

	@Test
	public void testVerifyHomeFeaturedAllChannelsAreDisplayed()
			throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nFeaturedAPI();
		List<VideoDetails> featuredChannelsList = videoDetails
				.get("allFeaturedShowsList");

		try {
			/*
			 * This Method is to register new user using Gazeebo application and
			 * to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is to ensure Home is Active page when Login into
			// Application.
			// assertionFunction.assertHomeActiveLink();

			// This method is to assert Gazeebo Top Middle Menu and to ensure
			// its collapsed.
			// assertionFunction.assertGazeeboTopMiddleMenu(); - No middle menu
			// present now

			// This method asserts featured title.
			assertionFunction.assertFeaturedTitle();

			if (featuredChannelsList != null) {
				int loopMaxIndex = 0;
				if (featuredChannelsList.size() < 5)
					loopMaxIndex = featuredChannelsList.size();
				else
					loopMaxIndex = 2;

				for (int index = 0; index < loopMaxIndex; index++) {
					VideoDetails channelList = featuredChannelsList.get(index);
					// This Method verifies Channel present in Featured list and
					// selects a Channel.
					homePageCommonFun.selectFeaturedChannel(channelList
							.getTitle());

					// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+channelList.getTitle()+"[\\s\\S]*$"));
					// assertTrue(driver.findElement(By.xpath(".//*[@id='content-wrap']/article/div/div[2]/h1[2]")).getText().matches(channelList.getTitle()));
					// Manoj: Modified the object and added to XpathObjectRepo
					// file
					assertTrue(driver
							.findElement(
									By.xpath(XpathObjectRepo.SHOWDETAILSHOWTITLE_XPATH))
							.getText().matches(channelList.getTitle()));
					driver.navigate().back();
					Thread.sleep(sleepTime);
				}
			} else {

				// Channel is not present now in 'FEATURED' section - So not
				// re-working on below code now
				// Objects can be modified only when channels are present in
				// Featured section
				boolean isPresent;
				/*
				 * isPresent = driver .findElement(
				 * By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]"))
				 * .findElements( By.xpath(
				 * ".//*[@id='featured']/div/section/div/div/ul[1]/li[1]/article/a/div/div"
				 * )) .size() > 0;
				 */

				isPresent = driver
						.findElement(
								By.xpath(XpathObjectRepo.FEATUREDROW_XPATH))
						.findElements(
								By.xpath(XpathObjectRepo.FEATUREDICONS_XPATH))
						.size() > 0;

				if (isPresent == true) {
					/*
					 * String channelName = driver .findElement(
					 * By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]"
					 * )) .findElement( By.xpath(
					 * ".//*[@id='featured']/div/section/div/div/ul[1]/li[1]/article/a/div/div"
					 * )) .getText();
					 */

					String channelName = driver
							.findElement(
									By.xpath(XpathObjectRepo.FEATUREDROW_XPATH))
							.findElement(
									By.xpath(XpathObjectRepo.FEATUREDICONS_XPATH))
							.getText();

					/*
					 * assertFalse(driver.findElement(By.cssSelector("BODY"))
					 * .getText() .matches("^[\\s\\S]*" + channelName +
					 * "[\\s\\S]*$"));
					 */

					assertFalse(driver
							.findElement(
									By.xpath(XpathObjectRepo.FEATUREDICONS_XPATH))
							.getText().contains(channelName));
				}
			}

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Copy Right Links.
			assertionFunction.assertFooterCopyRight();

			// This method is used to logout from Gazeebo Application.
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
