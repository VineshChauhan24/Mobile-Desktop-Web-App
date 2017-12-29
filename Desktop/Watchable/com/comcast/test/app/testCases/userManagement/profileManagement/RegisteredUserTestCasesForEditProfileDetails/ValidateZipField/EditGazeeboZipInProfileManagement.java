package comcast.test.app.testCases.userManagement.profileManagement.RegisteredUserTestCasesForEditProfileDetails.ValidateZipField;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
 * Class Name: EditGazeeboZipInProfileManagement Description: This test case is
 * to edit Watchable Zip field in Profile Management by logging registered user
 * into Watchable application.
 **/

public class EditGazeeboZipInProfileManagement extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	TestDataGenerator proUtil = new TestDataGenerator();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();

	@Test
	public void testEditGazeeboZipInProfileManagement() throws Exception {

		try {
			/*
			 * This Method is to register new user using Gazeebo application and
			 * to change a password.
			 */
			RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);
			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

				// This method asserts Home link ensure Home is Active page when
				// Login into Application.
				assertionFunction.assertHomeActiveLink();

				assertTrue(driver
						.findElement(
								By.xpath(XpathObjectRepo.TOP_MENU_HOME_BUTTON_XPATH))
						.getText().equalsIgnoreCase(UILablesRepo.TOPMENUHOME));

				// This method is to navigate Profile Management Account details
				// page.
				Thread.sleep(sleepTime);
				userLogin.navigateToAccountDetailsPage(driver);

				// This method asserts Watchable Logo.
				assertionFunction.assertWatchableLogo();

				// This method is to assert Join Watchable Header in Sign Up
				// page.
				assertionFunction.assertJoinWatchableBanner();

				Thread.sleep(sleepTime);
				// This method is used to update the State Data Properties
				// fields
				// proUtil.updateZipDataPropertiesFields();
				// proUtil.load(new FileInputStream(new
				// File("com/data.properties")));

				driver.findElement(
						By.xpath(XpathObjectRepo.SIGNUPFORM_ZIP_TEXTFIELD_XPATH))
						.clear();
				// driver.findElement(By.xpath(XpathObjectRepo.SIGNUPFORM_ZIP_TEXTFIELD_XPATH)).sendKeys(proUtil.getProperty("UPD_ZIP"));
				driver.findElement(
						By.xpath(XpathObjectRepo.SIGNUPFORM_ZIP_TEXTFIELD_XPATH))
						.sendKeys(UILablesRepo.ZIP_CODE);

				// Click on Save Profile
				driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
						.click();

				Thread.sleep(sleepTime);
				assertTrue(driver
						.findElement(
								By.xpath(XpathObjectRepo.TOP_MENU_HOME_BUTTON_XPATH))
						.getText().equalsIgnoreCase(UILablesRepo.TOPMENU_HOME));

				// This method is to navigate Profile Management Account details
				// page.
				Thread.sleep(sleepTime);
				userLogin.navigateToAccountDetailsPage(driver);

				Thread.sleep(sleepTime);
				// assertEquals(proUtil.getProperty("ZIP"),
				// driver.findElement(By.xpath(XpathObjectRepo.ACCOUNT_ZIP_TEXTFLD_XPATH)).getText());
				// assertTrue(proUtil.getProperty("ZIP").matches(proUtil.getProperty("ZIPXPATH")));

				assertEquals(
						UILablesRepo.ZIP_CODE,
						driver.findElement(
								By.xpath(XpathObjectRepo.SIGNUPFORM_ZIP_TEXTFIELD_XPATH))
								.getAttribute("value"));

				// This method is used to logout from application.
				userLogin.LogOut(driver);
			}
			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
