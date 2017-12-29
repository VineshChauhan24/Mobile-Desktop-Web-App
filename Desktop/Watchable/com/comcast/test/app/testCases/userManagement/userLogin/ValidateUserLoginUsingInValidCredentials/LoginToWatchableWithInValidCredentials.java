package comcast.test.app.testCases.userManagement.userLogin.ValidateUserLoginUsingInValidCredentials;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.xml.sax.SAXException;

import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.LoginFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: LoginToGazeeboWithInValidCredentials Description: This test case
 * is used to login to Gazeebo application with an invalid UserName and
 * Password.
 * **/

public class LoginToWatchableWithInValidCredentials extends BaseTest {

	LoginFunctions loginFuntion = new LoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testLoginToWatchableWithInValidCredentials()
			throws InterruptedException, XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {

		driver.get(DataServiceProperties.APPURL);

		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*Log In[\\s\\S]*$"));

		// This method is used to enter invalid UserName and Password
		loginFuntion.LoginToXidioApplication(driver,
				proUtil.getProperty("INVALID_USER_NAME"),
				proUtil.getProperty("INVALID_PASSWORD"));

		driver.findElement(By.id("user_login")).click();

		Thread.sleep(sleepTime);
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*Incorrect email or password[\\s\\S]*$"));

		// This method is to assert Join Gazeebo Header in Sign Up page.

		// This method is to ensure Display of Login page.
		assertionFunction.assertLoginPageDetails();
	}
}
