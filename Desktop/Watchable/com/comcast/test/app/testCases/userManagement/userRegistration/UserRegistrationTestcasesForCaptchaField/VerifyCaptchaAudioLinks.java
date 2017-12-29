package comcast.test.app.testCases.userManagement.userRegistration.UserRegistrationTestcasesForCaptchaField;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userRegistration.common.UserRegistrationValidationFuncitons;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyCaptchaHelpPage Description: This test case verifies
 * captcha help page in Watchable application
 * **/

public class VerifyCaptchaAudioLinks extends BaseTest {

	UserRegistrationValidationFuncitons userRegValidationFun = new UserRegistrationValidationFuncitons();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyCaptchaHelpPage() throws Exception {

		driver.get(DataServiceProperties.APPURL);

		try {
			driver.findElement(By.linkText("Sign Up")).click();

			// This method is used to verify Email field in registration page
			userRegValidationFun.validateUserRegistrationFields(driver,
					proUtil.getProperty("ZIP"),
					proUtil.getProperty("REG_PASSWORD"),
					proUtil.getProperty("REG_PASSWORD"));

			// Click on captcha audio icon
			driver.findElement(
					By.xpath(".//*[@id='recaptcha_widget']/div[3]/a/img"))
					.click();

			Thread.sleep(sleepTime);
			assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
					.matches("^[\\s\\S]*Play sound again[\\s\\S]*$"));
			assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
					.matches("^[\\s\\S]*Download sound as MP3[\\s\\S]*$"));

			// Click on captcha audio icon
			driver.findElement(
					By.xpath(".//*[@id='recaptcha_widget']/div[4]/a/img"))
					.click();

			Thread.sleep(sleepTime);
			assertFalse(driver.findElement(By.cssSelector("BODY")).getText()
					.matches("^[\\s\\S]*Play sound again[\\s\\S]*$"));
			assertFalse(driver.findElement(By.cssSelector("BODY")).getText()
					.matches("^[\\s\\S]*Download sound as MP3[\\s\\S]*$"));

			// This method asserts Home and My Channels inactive link when user
			// clicks on Bundle/Channel/Show.
			assertionFunction.assertAllInActiveLink();

			// This method asserts footer links.
			assertionFunction.assertFooterCopyRight();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
