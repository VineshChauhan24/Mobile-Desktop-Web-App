package comcast.test.config.diagnoseServices.ValidateComcastWebApplicationAccessiblity.ValidateComcastAppAccessibility;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.dataServices.userRegistration.UserRegistrationUsingComcast;

/**
 * Class Name: LoginToXidioApplicationWithValidCredentials Description: This
 * Diagnose Service test case is to check whether the Comcast application is up
 * and running or not.
 * 
 * @param <driver>: Native browser driver
 */

public class LoginToXidioApplicationWithValidCredentials extends BaseTest {

	UserRegistrationUsingComcast userReg = new UserRegistrationUsingComcast();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testLoginToComcast() throws Exception {

		// This method is used to register new user into Comcast Application
		userReg.testUserRegistrationUsingComcast(driver);

		driver.get(DataServiceProperties.APPURL);

		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*User name[\\s\\S]*$"));
		driver.findElement(By.name("user[user_name]")).clear();
		driver.findElement(By.name("user[user_name]")).sendKeys(
				DataServiceProperties._USER_NAME);

		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*Password[\\s\\S]*$"));
		driver.findElement(By.name("user[password]")).clear();
		driver.findElement(By.name("user[password]")).sendKeys(
				DataServiceProperties._PASSWORD);
		driver.findElement(By.id("user_login")).click();

		Thread.sleep(sleepTime);
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*Sign out[\\s\\S]*$"));

		// This method is used to logout from Gazeebo Application.
		userLogin.LogOut(driver);
	}
}
