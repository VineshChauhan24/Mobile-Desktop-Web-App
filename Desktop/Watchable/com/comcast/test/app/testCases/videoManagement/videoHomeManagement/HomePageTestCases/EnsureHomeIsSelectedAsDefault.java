package comcast.test.app.testCases.videoManagement.videoHomeManagement.HomePageTestCases;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;

/**
 * Class Name: EnsureHomeIsSelectedAsDefault Description: This test case is to
 * verify that 'Home' is selected as default by logging into Watchable
 * application.
 */

public class EnsureHomeIsSelectedAsDefault extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testEnsureHomeIsSelectedAsDefault() throws Exception {

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

				// This method is to verify Watchable Top Middle Menu and to
				// ensure its collapsed.
				// assertionFunction.assertWatchableTopMiddleMenu(); - No middle
				// menu present now

				// This method verify Watchable Logo.
				assertionFunction.assertWatchableLogo();

				// This method is to verify all Header links After logging into
				// application.
				assertionFunction.assertAllHeaderLinkAfterLogin();

				// This method is to verify all the Header of different
				// categories after logging into App.
				assertionFunction.assertCategoryHeadersAfterLogin();

				// This method verify Footer Logo and It's Text.
				assertionFunction.assertFooterLogo();

				// This method verify Footer Copy Right Links.
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
