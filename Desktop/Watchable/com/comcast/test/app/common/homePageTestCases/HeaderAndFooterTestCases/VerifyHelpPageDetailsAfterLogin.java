package comcast.test.app.common.homePageTestCases.HeaderAndFooterTestCases;

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
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;

/**
 * Class Name: VerifyHelpPageDetailsAfterLogin Description: This test case is to
 * verify 'Help' page details by logging registered user into Watchable
 * application.
 **/

public class VerifyHelpPageDetailsAfterLogin extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testVerifyHelpPageDetailsAfterLogin()
			throws InterruptedException, XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {

		driver.get(DataServiceProperties.APPURL);
		try {

			// This method is used to enter user name and password credentials
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

				// This method is to ensure Login is Active link.
				assertionFunction.assertHomeActiveLink();

				// This method is to assert all Header links.
				assertionFunction.assertAllHeaderLinkAfterLogin();

				driver.findElement(
						By.xpath(XpathObjectRepo.TOP_MENU_HELP_BUTTON_XPATH))
						.click();

				Thread.sleep(sleepTime);
				// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Sorry. No content for this page yet. Please come back later.[\\s\\S]*$"));
				assertTrue(driver
						.findElement(By.xpath(XpathObjectRepo.HELP_TITLE_XPATH))
						.getText().equalsIgnoreCase(UILablesRepo.HELP_TITLE));

				// This method is to ensure Help is Active page before Login
				// into Application.
				assertionFunction.assertHelpActiveLinkAfterLogin();

				// This method asserts Watchable Logo.
				assertionFunction.assertWatchableLogo();

				// This method asserts Search Text Box and its value.
				assertionFunction.assertSearchTextBox();

				// This method asserts Footer Logo and It's Text.
				assertionFunction.assertFooterCategoryLinks();

				// This method asserts footer links.
				assertionFunction.assertFooterCopyRight();

				// This method is used to logout from application.
				userLogin.LogOut(driver);
			}
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}

	}

}
