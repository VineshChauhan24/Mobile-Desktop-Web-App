package comcast.test.app.testCases.userManagement.userRegistration.UserRegistrationTestCasesForPassword.UserRegWithNoFirstCapitalLetter;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userRegistration.common.UserRegistrationValidationFuncitons;

/**
 * Class Name: RegisterToWatchableByEnteringNonCapitalLetterPassword
 * Description: This test case registers to Watchable application by entering
 * non capital letter password.
 **/

public class RegisterToWatchableByEnteringNonCapitalLetterPassword extends
		BaseTest {

	UserRegistrationValidationFuncitons userRegValidationFun = new UserRegistrationValidationFuncitons();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testRegisterToWatchableByEnteringNonCapitalLetterPassword()
			throws Exception {

		driver.get(DataServiceProperties.APPURL);
		try {
			driver.findElement(By.xpath(XpathObjectRepo.TOPMENUSIGNUP_XPATH))
					.click();

			// This method is used to validate password containing non capital
			// letter.
			userRegValidationFun.validateUserRegistrationFields(driver,
					proUtil.getProperty("ZIP"),
					proUtil.getProperty("NONCAPITAL_LETTER_PASSWORD"),
					proUtil.getProperty("NONCAPITAL_LETTER_PASSWORD"));

			driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
					.click();

			Thread.sleep(sleepTime);
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_EMPTYPASSWD_WARNMSG_XPATH))
					.getText()
					.equalsIgnoreCase(
							UILablesRepo.SIGNUPPAGE_PASSWD_UPPERECASE_WARNMSG));
			// This method asserts Home and My Channels inactive link when user
			// clicks on Bundle/Channel/Show.
			assertionFunction.assertAllInActiveLink();

			// This method asserts Gazeebo Logo.
			assertionFunction.assertWatchableLogo();

			// This method is to assert Join Watchable Header in Sign Up page.
			assertionFunction.assertJoinWatchableBanner();

			// This method asserts footer links.
			assertionFunction.assertFooterCopyRight();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
