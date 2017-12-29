package comcast.test.app.testCases.userManagement.userRegistration.UserRegistrationTestCasesForEmail.UserRegWithInvalidEmailID;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userRegistration.common.UserRegistrationValidationFuncitons;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: RegToGazeeboByEnteringInValidEmail Description: This test case is
 * for user registration to Watchable application by entering invalid Email.
 * **/

public class RegToWatchableByEnteringInValidEmail extends BaseTest {

	UserRegistrationValidationFuncitons userRegValidationFun = new UserRegistrationValidationFuncitons();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testRegToWatchableByEnteringInValidEmail() throws Exception {

		try {
			driver.get(DataServiceProperties.APPURL);

			driver.findElement(By.xpath(XpathObjectRepo.TOPMENUSIGNUP_XPATH))
					.click();

			// This method is used to validate invalid Email in registration
			// page
			userRegValidationFun.validateInvalidEmailID(driver,
					proUtil.getProperty("INVALID_EMAIL"),
					proUtil.getProperty("ZIP"),
					proUtil.getProperty("REG_PASSWORD"));

			driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
					.click();

			Thread.sleep(sleepTime);

			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECTEMAILFORMAT_WARNMSG_XPATH))
					.getText()
					.equalsIgnoreCase(
							UILablesRepo.FORGOT_PASSWD_INVALID_EMAILFORMAT_MSG));

			// This method asserts Home and My Channels inactive link when user
			// clicks on Bundle/Channel/Show.
			assertionFunction.assertAllInActiveLink();

			// This method asserts Watchable Logo.
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
