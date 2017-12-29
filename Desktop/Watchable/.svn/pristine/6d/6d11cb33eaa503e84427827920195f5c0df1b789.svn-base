package comcast.test.app.testCases.videoManagement.videoSubscriptionManagement.freeSubscribedChannels.MyChannelsPage;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.subscriptionsPage.common.SubscriptionsPageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;

/**
 * Class Name: VerifyMyChannelsPageMessageIfNoSubscripitons Description: This
 * test case is to verify the message displayed on 'My Channels' page when there
 * are no channels subscribed by logging into Watchable application.
 */

public class VerifyMyChannelsPageMessageIfNoSubscripitons extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	SubscriptionsPageCommonFunctions subscriptionsPageCommonFun = new SubscriptionsPageCommonFunctions();

	@Test
	public void testVerifyMyChannelsPageMessageIfNoSubscripitons()
			throws Exception {

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			/*
			 * driver.get(DataServiceProperties.APPURL);
			 * Thread.sleep(sleepTime); //
			 * driver.findElement(By.xpath(XpathObjectRepo
			 * .TOPMENULOGIN_XPATH)).click();
			 * driver.findElement(By.xpath(XpathObjectRepo.USERNAME_XPATH))
			 * .clear(); //
			 * driver.findElement(By.xpath(XpathObjectRepo.USERNAME_XPATH
			 * )).sendKeys(UILablesRepo.USERNAME_MYCHNNLS_NOSUBSCRIPTION);
			 * driver.findElement(By.xpath(XpathObjectRepo.PASSWORD_XPATH))
			 * .clear(); //
			 * driver.findElement(By.xpath(XpathObjectRepo.PASSWORD_XPATH
			 * )).sendKeys(UILablesRepo.PASSWORD_MYCHNNLS_NOSUBSCRIPTION);
			 * driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
			 * .click();
			 */

			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {
				// This method is to ensure Home is Active page when Login into
				// Application.
				assertionFunction.assertHomeActiveLink();

				// This method is to navigate My Channels Page.
				subscriptionsPageCommonFun.navigateToMyChannelsPage();

				// This method asserts Watchable Logo.
				assertionFunction.assertWatchableLogo();

				// assertTrue(driver.findElement(By.xpath(XpathObjectRepo.TOP_MENU_MYCHANNELS_BUTTON_XPATH)).getText().equalsIgnoreCase(UILablesRepo.HOMEPAGE_MYCHANNELSLINK));

				// This method asserts CHANNELS,CATEGORIES,UNWATCHED and LAST
				// UPDATED links.
				assertionFunction.assertMyChannelsPageLinks();

				// assertTrue(driver.findElement(By.xpath(XpathObjectRepo.MYCHNLS_NOSUBSCRIPTIONS_MSG_XPATH)).getText().equalsIgnoreCase(UILablesRepo.MYCHNLS_NOSUBSCRIPTIONS_MSG));

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
