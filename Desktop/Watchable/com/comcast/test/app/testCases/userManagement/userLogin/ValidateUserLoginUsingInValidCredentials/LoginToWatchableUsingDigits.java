package comcast.test.app.testCases.userManagement.userLogin.ValidateUserLoginUsingInValidCredentials;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.LoginFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: LoginToGazeeboUsingDigits Description: This test case is used to
 * login to Watchable application by entering username as digits and valid
 * password.
 * **/

public class LoginToWatchableUsingDigits extends BaseTest {

	LoginFunctions loginFuntion = new LoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testLoginToWatchableUsingDigits() throws Exception {

		driver.get(DataServiceProperties.APPURL);

		// This method is used to enter invalid UserName with valid Password
		// loginFuntion.LoginToXidioApplication(driver,proUtil.getProperty("DIGIT_USERNAME"),proUtil.getProperty("PASSWORD"));
		loginFuntion.LoginToXidioApplication(driver,
				UILablesRepo.DIGIT_USERNAME, UILablesRepo.PASSWORD);

		driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH)).click();

		Thread.sleep(sleepTime);
		// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Incorrect email or password[\\s\\S]*$"));
		assertTrue(driver
				.findElement(
						By.xpath(XpathObjectRepo.LOGINPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
				.getText().matches(UILablesRepo.LOGIN_ERROR_MSG));

		// This method is to assert Join Watchable Header in Sign Up page.

		// assertionFunction.assertJoinGazeeboBanner();
		// This method is to assert Join Watchable Header in Sign Up page.
		assertionFunction.assertJoinWatchableBanner();

		// This method is to ensure Display of Login page.
		assertionFunction.assertLoginPageDetails();
	}
}
