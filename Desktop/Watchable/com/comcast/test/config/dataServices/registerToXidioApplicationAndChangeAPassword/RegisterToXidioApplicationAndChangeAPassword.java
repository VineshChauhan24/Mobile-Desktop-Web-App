package comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.dataServices.userRegistration.UserRegistrationUsingComcast;

/**
 * Class Name: RegisterToXidioApplicationAndChangeAPassword Description: This
 * DataService Registers new user using Comcast application and change the
 * password
 * 
 * @param <driver>: Native browser driver
 **/

public class RegisterToXidioApplicationAndChangeAPassword extends BaseTest {

	UserLoginFunctions userLogin = new UserLoginFunctions();
	UserRegistrationUsingComcast userReg = new UserRegistrationUsingComcast();

	@Test
	public void RegisterToComcastAppAndChangePassword(WebDriver driver)
			throws Exception {
		// This method is used to register new user into Comcast Application
		// userReg.testUserRegistrationUsingComcast(driver);

		driver.get(DataServiceProperties.APPURL);

		// This method is used to enter user name and password credential
		Thread.sleep(sleepTime);
		userLogin.UserLoginCredentials(driver);

		driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH)).click();
		Thread.sleep(sleepTime);

		/*
		 * proUtil.load(new FileInputStream(new File("com/data.properties")));
		 * 
		 * //This method is used to Change Password (Its Commented because in
		 * current Application its Removed)
		 * userLogin.ChangePassword(driver,proUtil
		 * .getProperty("PASSWORD"),proUtil
		 * .getProperty("RESET_PASSWORD"),proUtil
		 * .getProperty("CONFIRM_PASSWORD"));
		 * driver.findElement(By.name("commit")).click();
		 * 
		 * TestDataGenerator.updateResetPasswordDataPropertiesFields();
		 * proUtil.load(new FileInputStream(new File("com/data.properties")));
		 */
	}
}
