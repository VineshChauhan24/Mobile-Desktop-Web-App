package comcast.test.app.testCases.userManagement.userRegistration.UserRegistrationTestcasesForCaptchaField;

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
 * Class Name: ValidateCaptchaMandarotyField Description: This test case
 * validates captcha mandatory field in Watchable application
 */

public class ValidateCaptchaMandarotyField extends BaseTest {

	UserRegistrationValidationFuncitons userRegValidationFun = new UserRegistrationValidationFuncitons();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testValidateCaptchaMandarotyField() throws Exception {

		driver.get(DataServiceProperties.APPURL);

		try {
			driver.findElement(By.xpath(XpathObjectRepo.TOPMENUSIGNUP_XPATH))
					.click();

			// This method is used to verify Email field in registration page
			userRegValidationFun.validateUserRegistrationFields(driver,
					proUtil.getProperty("ZIP"),
					proUtil.getProperty("REG_PASSWORD"),
					proUtil.getProperty("REG_PASSWORD"));

			// This method is to validate captcha field
			// userRegValidationFun.validateCaptchaField(driver,
			// proUtil.getProperty(""));

			driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
					.click();

			Thread.sleep(sleepTime);

			assertTrue(driver
					.findElement(By.xpath(XpathObjectRepo.CAPTCHA_ERROR_XPATH))
					.getText().equalsIgnoreCase(UILablesRepo.CAPTCHA_ERROR_MSG));
			// This method asserts Home and My Channels inactive link when user
			// clicks on Bundle/Channel/Show.
			assertionFunction.assertAllInActiveLink();

			// This method asserts Watchable Logo.
			// assertionFunction.assertGazeeboLogo();

			// This method is to assert Join Gazeebo Header in Sign Up page.
			// assertionFunction.assertJoinGazeeboBanner();

			// This method asserts footer links.
			assertionFunction.assertFooterCopyRight();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
