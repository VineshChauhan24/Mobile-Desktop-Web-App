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
 * Class Name: LoginToGazeeboUsingInValidUserName Description: This test case is
 * used to login to Gazeebo application by entering invalid username and valid
 * password.
 * **/

public class LoginToWatchableUsingInValidUserName extends BaseTest {

	LoginFunctions loginFuntion = new LoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testLoginToWatchableUsingInValidUserName() throws Exception {

		driver.get(DataServiceProperties.APPURL);

		// This method is used to enter invalid UserName with valid Password
		// loginFuntion.LoginToXidioApplication(driver,proUtil.getProperty("INVALID_USER_NAME"),proUtil.getProperty("PASSWORD"));

		loginFuntion.LoginToXidioApplication(driver,
				UILablesRepo.INVALID_USER_NAME, UILablesRepo.PASSWORD);

		// driver.findElement(By.id("user_login")).click();
		driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH)).click();

		Thread.sleep(sleepTime);
		// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Incorrect email or password[\\s\\S]*$"));
		assertTrue(driver
				.findElement(
						By.xpath(XpathObjectRepo.LOGINPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
				.getText().matches(UILablesRepo.LOGIN_ERROR_MSG));

		// This method is to assert Join Gazeebo Header in Sign Up page.
		// assertionFunction.assertJoinGazeeboBanner();

		// This method is to ensure Login page is displayed.
		assertionFunction.assertLoginPageDetails();
	}
}
