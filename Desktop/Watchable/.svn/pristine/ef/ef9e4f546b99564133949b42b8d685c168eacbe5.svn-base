package comcast.test.app.testCases.userManagement.userLogin.ForgotPassword;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.xml.sax.SAXException;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyForgotPasswordFunctionalityWithNoEmail Description: This
 * test case is to verify Forgot Password functionality by providing blank
 * Email.
 **/

public class VerifyForgotPasswordFunctionalityWithNoEmail extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testVerifyForgotPasswordFunctionalityWithNoEmail()
			throws InterruptedException, XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {

		driver.get(DataServiceProperties.APPURL);
		try {
			// driver.findElement(By.linkText("Forgot?")).click();

			// Clicks on Forgot Password link
			driver.findElement(
					By.xpath(XpathObjectRepo.FORGOT_PASSWD_LINK_XPATH)).click();

			// This method is to assert Join watchable Header in Sign Up page.
			// assertionFunction.assertJoinGazeeboBanner();
			// This method is to assert Join watchable Header in Sign Up page.
			assertionFunction.assertJoinWatchableBanner();

			// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Enter your email[\\s\\S]*$"));

			// driver.findElement(By.id("user_login")).click();
			driver.findElement(
					By.xpath(XpathObjectRepo.MYACCOUNT_SAVE_BTN_XPATH)).click();

			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.FORGOT_PASSWD_INVALID_EMAILFORMAT_XPATH))
					.getText().matches(UILablesRepo.SIGNUP_EMPTYEMAIL_MESSAGE));

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterCategoryLinks();

			// This method asserts footer links.
			assertionFunction.assertFooterCopyRight();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}

	}

}
