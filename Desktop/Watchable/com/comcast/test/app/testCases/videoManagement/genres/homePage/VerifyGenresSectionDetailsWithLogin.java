package comcast.test.app.testCases.videoManagement.genres.homePage;

import org.junit.Test;

import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;

/**
 * Class Name: VerifyGenresAllLinksInHomePageWithLogin Description: This test
 * case validates whether Categories, Channels and LastUpdated link are
 * displayed in home page for the genres section by logging registered user into
 * Comcast application.
 * **/

public class VerifyGenresSectionDetailsWithLogin extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyGenresAllLinksInHomePageWithLogin() throws Exception {

		try {
			/*
			 * This Method is to register new user using Comcast application and
			 * to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// This method is ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method is to assert Watchable Top Middle Menu and to ensure
			// its collapsed.
			// assertionFunction.assertGazeeboTopMiddleMenu();??this doesnt
			// exist now.

			// This method asserts Watchable Logo.
			assertionFunction.assertWatchableLogo();

			// This method asserts Categories, Channels and Last Updated links.
			// assertionFunction.assertGenresSectionLinks();

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Copy Right Links.
			assertionFunction.assertFooterCopyRight();

			Thread.sleep(sleepTime);
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
