package comcast.test.app.common.homePageTestCases.BeforeLoginFeatureTestCases;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
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
 * Class Name: VerifySignUpFunctionalityFromHomeURL Description: This test case
 * is to verify Sign Up page functionality from Home page URL when user clicks
 * on Sign Up Link it should navigate to Registration page.
 * **/

public class VerifySignUpFunctionalityFromHomeURL extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testVerifySignUpFunctionalityFromHomeURL() throws Exception {

		List<VideoDetails> videoDetails = RestAPIServices.nhomePageAPIs();

		driver.get(proUtil.getProperty("HOMEAPPURL"));
		try {
			// This method is to ensure Home is Active page before Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			Thread.sleep(sleepTime);
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEFEATUREDCONTAINERLABEL_XPATH))
					.getText().equalsIgnoreCase(UILablesRepo.HOMEPAGE_FEATURED));
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEFEATUREDCONTAINER_FIRSTELEMENT_XPATH))
					.getText().equalsIgnoreCase(videoDetails.get(0).getTitle()));

			Thread.sleep(sleepTime);
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEPOPULARSHOWSTITLELABEL_XPATH))
					.getText()
					.equalsIgnoreCase(UILablesRepo.HOMEPAGE_POPULARSHOWS));
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEPOPULARSHOWS_FIRSTELEMENT_XPATH))
					.getText()
					.equalsIgnoreCase((videoDetails.get(1).getTitle())));

			Thread.sleep(sleepTime);
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSTITLELABEL_XPATH))
					.getText()
					.equalsIgnoreCase(UILablesRepo.HOMEPAGE_FEATUREDCHANNELS));
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELS_FIRSTELEMENT_XPATH))
					.getText().equalsIgnoreCase(videoDetails.get(2).getTitle()));

			driver.findElement(By.xpath(XpathObjectRepo.TOPMENUSIGNUP_XPATH))
					.click();

			/*
			 * This method asserts Home and My Channels inactive link when user
			 * clicks on 'My Channels' link before logging into Application.
			 */
			assertionFunction.assertAllInActiveLink();

			// This method asserts Watchable Logo.
			assertionFunction.assertWatchableLogo();

			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method is to assert Watchable Top Middle Menu and to ensure
			// its collapsed.
			// assertionFunction.assertGazeeboTopMiddleMenu();

			// This method is to asserts all the Header of different categories
			// after logging into App.
			assertionFunction.assertCategoryHeadersAfterLogin();

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Copy Right Links.
			assertionFunction.assertFooterCopyRight();

			assertionFunction.assertSignOutLink();

			// This method is used to logout from application.
			userLogin.LogOut(driver);
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
