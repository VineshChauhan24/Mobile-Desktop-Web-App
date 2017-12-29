package comcast.test.app.testCases.userManagement.userRegistration.UserRegistrationTestCasesForEmail.UserRegWithNoEmailID;

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
 * Class Name: RegToWatchableWithoutEnteringEmail Description: This test case is
 * for user registration to Gazeebo application without entering Email.
 * **/

public class RegToWatchableWithoutEnteringEmail extends BaseTest {

	UserRegistrationValidationFuncitons userRegValidationFun = new UserRegistrationValidationFuncitons();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testRegToWatchableWithoutEnteringEmail() throws Exception {

		driver.get(DataServiceProperties.APPURL);

		try {
			driver.findElement(By.xpath(XpathObjectRepo.TOPMENUSIGNUP_XPATH))
					.click();

			// This method is used to verify Email field in registration page
			userRegValidationFun.validateInvalidEmailID(driver, "",
					proUtil.getProperty("ZIP"),
					proUtil.getProperty("REG_PASSWORD"));

			driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
					.click();

			Thread.sleep(sleepTime);
			// assertTrue(driver.findElement(By.xpath(XpathObjectRepo.SIGNUP_EMPTYEMAIL_MESSAGE_XPATH)).getText().equalsIgnoreCase(UILablesRepo.SIGNUP_EMPTYEMAIL_MESSAGE));

			// This method asserts Home and My Channels inactive link when user
			// clicks on Bundle/Channel/Show.
			assertionFunction.assertAllInActiveLink();

			// This method asserts Gazeebo Logo.
			assertionFunction.assertWatchableLogo();

			// This method is to assert Join Gazeebo Header in Sign Up page.
			assertionFunction.assertJoinWatchableBanner();

			// This method asserts footer links.
			assertionFunction.assertFooterCopyRight();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
