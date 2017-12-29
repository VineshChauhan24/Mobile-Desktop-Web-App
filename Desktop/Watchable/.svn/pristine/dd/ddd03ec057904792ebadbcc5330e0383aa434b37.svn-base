package comcast.test.app.common.homePageTestCases.HeaderAndFooterTestCases;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.TestDataGenerator;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;

/**
 * Class Name: VerifyHomePageAllLinks Description: This test case validates Home
 * page all Link by logging registered user into Gazeebo application.
 * **/

public class VerifyHomePageAllLinks extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	TestDataGenerator proUtil = new TestDataGenerator();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testVerifyHomePageAllLinks() throws Exception {

		// This method is used to GET update Data Properties fields
		proUtil.load(new FileInputStream(new File("com/data.properties")));

		/*
		 * This Method is to register new user using Gazeebo application and to
		 * change a password.
		 */
		RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

		try {

			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

				// This method is to ensure Home is Active page when Login into
				// Application.
				assertionFunction.assertHomeActiveLink();

				// This method is to assert all Header links After logging into
				// application.
				assertionFunction.assertAllHeaderLinkAfterLogin();

				// This method asserts Search Text Box and its value.
				assertionFunction.assertSearchTextBox();

				// This method is to assert Gazeebo Top Middle Menu and to
				// ensure
				// its collapsed.
				// assertionFunction.assertGazeeboTopMiddleMenu(); -
				// Functionality
				// is not present now

				proUtil.load(new FileInputStream(
						new File("com/data.properties")));

				// String
				// userName=driver.findElement(By.xpath("//*[@id='topright_menu']/ul/li[3]/span")).getText();
				String userName = driver.findElement(
						By.xpath(XpathObjectRepo.USERNAME_TOPMENU_XPATH))
						.getText();

				// assertEquals(proUtil.getProperty("USER_NAME"),userName);
				assertEquals(UILablesRepo.USERNAME, userName);

				// This method is to asserts all the Header of different
				// categories
				// after logging into App.
				assertionFunction.assertCategoryHeadersAfterLogin();

				// This method is to assert Profile Management Links.
				assertionFunction.assertPMLinks();

				// This method is used to logout from application.
				userLogin.LogOut(driver);
			}
		} catch (Throwable e) {
			captureScreenshot();
			collector.addError(e);
		}
	}

}
