package comcast.test.app.testCases.userManagement.userLogin.ValidateUserLoginUsingInValidCredentials;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.xml.sax.SAXException;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.LoginFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyLoginPageMandatoryFields Description: This test case is
 * used to validate login mandatory field message in Gazeebo application.
 * **/

public class VerifyLoginPageMandatoryFields extends BaseTest {

	LoginFunctions loginFuntion = new LoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	// Manoj: Re-factory done

	@Test
	public void testVerifyLoginPageMandatoryFields()
			throws InterruptedException, XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {

		// Opening application
		driver.get(DataServiceProperties.HOMEAPPURL);

		// Verifying Login link is present in top menu
		assertTrue(CommonFun.isElementPresent(driver,
				By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH)));
		log.info("Login link is present in homepage");

		// Click on Login link
		driver.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
				.click();

		// Click on login button by keeping email and password field blank

		driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH)).click();

		Thread.sleep(sleepTime);
		assertTrue(driver
				.findElement(
						By.xpath(XpathObjectRepo.SIGNUP_EMPTYEMAIL_MESSAGE_XPATH))
				.getText().contains(UILablesRepo.SIGNUP_EMPTYEMAIL_MESSAGE));

		assertTrue(driver
				.findElement(
						By.xpath(XpathObjectRepo.LOGIN_PASSWORD_ERROR_XPATH))
				.getText()
				.contains(UILablesRepo.SIGNUPPAGE_EMPTYPASSWD_WARNMSG));
		log.info("Error messages are displayed as expected");

		// This method is to ensure Display of Login page.
		assertionFunction.assertLoginPageDetails();
	}
}
