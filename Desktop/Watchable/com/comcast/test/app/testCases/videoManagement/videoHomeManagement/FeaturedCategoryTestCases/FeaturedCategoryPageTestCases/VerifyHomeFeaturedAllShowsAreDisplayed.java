package comcast.test.app.testCases.videoManagement.videoHomeManagement.FeaturedCategoryTestCases.FeaturedCategoryPageTestCases;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyHomeFeaturedAllShowsAreDisplayed Description: This test
 * case is to verify Home/Featured category all Shows are displayed and
 * clickable for registered Watchable Application user.
 * **/

public class VerifyHomeFeaturedAllShowsAreDisplayed extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();

	@Rule
	public ErrorCollector errCol = new ErrorCollector();

	@Test
	public void testVerifyHomeFeaturedAllShowsAreDisplayed() throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nFeaturedAPI();
		List<VideoDetails> featuredShowsList = videoDetails
				.get("featuredShowList");

		try {
			/*
			 * This Method is to register new user using Gazeebo application and
			 * to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method is to assert Gazeebo Top Middle Menu and to ensure
			// its collapsed.
			// assertionFunction.assertGazeeboTopMiddleMenu();

			// This method asserts featured title.
			assertionFunction.assertFeaturedTitle();

			if (featuredShowsList != null) {
				int loopMaxIndex = 0;
				if (featuredShowsList.size() < 10)
					loopMaxIndex = featuredShowsList.size();
				else
					loopMaxIndex = 10;

				for (int index = 0; index < loopMaxIndex; index++) {
					VideoDetails showList = featuredShowsList.get(index);
					// This Method verifies Show present in Home Featured list
					// and selects a Show.
					homePageCommonFun.selectHomeFeaturedShow(showList
							.getTitle());

					Thread.sleep(sleepTime);
					// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+showList.getTitle()+"[\\s\\S]*$"));
					// Manoj: Objected modified and moved to XpathObjectRepo
					// file
					/*
					 * assertTrue(driver .findElement(
					 * By.xpath(".//*[@id='content-wrap']/article/div/div[2]/h1[2]"
					 * )) .getText().matches(showList.getTitle()));
					 */

					assertTrue(driver
							.findElement(
									By.xpath(XpathObjectRepo.SHOWDETAILSHOWTITLE_XPATH))
							.getText().matches(showList.getTitle()));

					// This method asserts Gazeebo Logo.
					assertionFunction.assertWatchableLogo();

					driver.navigate().back();
					Thread.sleep(sleepTime);
				}
			}

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Copy Right Links.
			assertionFunction.assertFooterCopyRight();

			// This method is used to logout from Gazeebo Application.
			Thread.sleep(sleepTime);
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
