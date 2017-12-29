package comcast.test.app.testCases.videoManagement.videoHomeManagement.WatchingCategoryTestCases.HomePageTCs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;

/**
 * Class Name: VerifyWatchingForNewlyRegisteredUser Description: This test case
 * validates whether the Watching section is displyed or not for newly
 * registered user logging registered user into Watchable application.
 * **/

public class VerifyWatchingForNewlyRegisteredUser extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testVerifyWatchingForNewlyRegisteredUser() throws Exception {

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			/*
			 * assertFalse(driver.findElement(By.cssSelector("BODY")).getText().
			 * matches("^[\\s\\S]*Watching[\\s\\S]*$"));
			 * assertFalse(driver.findElement
			 * (By.cssSelector("BODY")).getText().matches
			 * ("^[\\s\\S]*Featured[\\s\\S]*$"));
			 * assertFalse(driver.findElement(
			 * By.cssSelector("BODY")).getText().matches
			 * ("^[\\s\\S]*Popular Shows[\\s\\S]*$"));
			 * assertFalse(driver.findElement
			 * (By.cssSelector("BODY")).getText().matches
			 * ("^[\\s\\S]*Popular Channels[\\s\\S]*$"));
			 * assertFalse(driver.findElement
			 * (By.cssSelector("BODY")).getText().matches
			 * ("^[\\s\\S]*Shows[\\s\\S]*$"));
			 */

			Thread.sleep(sleepTime);
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.YOUAREWATCHINGHEADERLABEL_XPATH))
					.getText().matches(UILablesRepo.HOMEPAGE_YOUAREWATCHING));
			Thread.sleep(sleepTime);
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEFEATUREDCONTAINERLABEL_XPATH))
					.getText().matches(UILablesRepo.HOMEPAGE_FEATURED));
			Thread.sleep(sleepTime);
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEPOPULARSHOWSTITLELABEL_XPATH))
					.getText().matches(UILablesRepo.HOMEPAGE_POPULARSHOWS));
			Thread.sleep(sleepTime);
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSTITLELABEL_XPATH))
					.getText().matches(UILablesRepo.HOMEPAGE_FEATUREDCHANNELS));
			Thread.sleep(sleepTime);
			assertFalse(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEPAGE_GENRES_SHOWS_HEADER_XPATH))
					.getText()
					.matches(UILablesRepo.CHANNELDETAILSPAGE_SHOWS_TEXT));

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
