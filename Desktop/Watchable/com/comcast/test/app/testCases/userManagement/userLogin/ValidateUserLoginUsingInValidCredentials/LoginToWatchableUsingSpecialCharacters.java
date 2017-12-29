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
 * Class Name: LoginToGazeeboUsingSpecialCharacters Description: This test case
 * is used to login to Watchable application by entering username as special
 * characters and valid password.
 * **/

public class LoginToWatchableUsingSpecialCharacters extends BaseTest {

	LoginFunctions loginFuntion = new LoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testLoginToEatchableUsingSpecialCharacters() throws Exception {

		driver.get(DataServiceProperties.APPURL);

		assertTrue(driver
				.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
				.getText().equalsIgnoreCase(UILablesRepo.TOPMENU_LOGIN));

		// This method is used to enter invalid UserName with valid Password
		loginFuntion.LoginToXidioApplication(driver,
				proUtil.getProperty("SPL_USERNAME"),
				proUtil.getProperty("PASSWORD"));

		driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH)).click();

		Thread.sleep(sleepTime);
		assertTrue(driver
				.findElement(
						By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
				.getText()
				.equalsIgnoreCase(
						UILablesRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG));

		// This method is to assert Join Gazeebo Header in Sign Up page.

		// This method is to ensure Display of Login page.
		assertionFunction.assertLoginPageDetails();
	}
}
