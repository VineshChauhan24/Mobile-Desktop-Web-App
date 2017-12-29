package comcast.test.app.testCases.userManagement.userLogin.Rememberme;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.TestDataGenerator;
import comcast.test.config.dataServices.userRegistration.UserRegistrationUsingComcast;

/**
 * Class Name: ValidateRememberMeFunctionality Description: This test case is
 * used to validate Remember Me functionality on Login Page in Gazeebo
 * application.
 * **/

public class ValidateRememberMeFunctionality {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	UserRegistrationUsingComcast userReg = new UserRegistrationUsingComcast();

	@Test
	public void testValidateRememberMeFunctionality() throws Exception {

		try {

			ProfilesIni profileIni = new ProfilesIni();

			FirefoxProfile foxprofile = profileIni.getProfile("Comcast_User");

			FirefoxDriver driver = new FirefoxDriver(foxprofile);

			// FirefoxProfile profile = new FirefoxProfile(new
			// File("D:\\SeleniumProfile"));
			// driver = new FirefoxDriver(profile);

			// This method is used to register new user into Comcast Application
			// userReg.testUserRegistrationUsingComcast(driver);

			driver.get(DataServiceProperties.APPURL);
			// driver.get(proUtil.getProperty("HOMEAPPURL"));

			driver.manage().window().maximize();

			// driver.findElement(By.xpath("")).click();

			// This method is used to enter user name and password credential
			Thread.sleep(2000);
			userLogin.UserLoginCredentials(driver);

			// driver.findElement(By.id("user_login")).click();
			driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
					.click();

			Thread.sleep(2000);

			TestDataGenerator proUtil = new TestDataGenerator();
			proUtil.load(new FileInputStream(new File("com/data.properties")));
			// This assertion is to verify updated email on Home page.

			// assertEquals(proUtil.getProperty("USER_NAME"),
			// driver.findElement(By.xpath(".//*[@id='topright_menu']/ul/li[3]/span")).getText());
			assertEquals(
					UILablesRepo.USERNAME,
					driver.findElement(
							By.xpath(XpathObjectRepo.LOGIN_USER_XPATH))
							.getText());

			// Quit the browser.
			driver.close();
			// driver.quit();

			Thread.sleep(2000);
			driver = new FirefoxDriver(foxprofile);
			// driver.manage().window().maximize();
			driver.get(proUtil.getProperty("HOMEAPPURL"));
			driver.manage().window().maximize();
			driver.close();

			Thread.sleep(2000);
			// This assertion is to verify updated email on Home page.
			// assertEquals(proUtil.getProperty("USER_NAME"),
			// driver.findElement(By.xpath(".//*[@id='topright_menu']/ul/li[3]/span")).getText());
			assertEquals(
					UILablesRepo.USERNAME,
					driver.findElement(
							By.xpath(XpathObjectRepo.LOGIN_USER_XPATH))
							.getText());

			// This method asserts Footer Logo and It's Text.
			// assertionFunction.assertFooterCategoryLinks();

			// This method asserts footer links.
			// assertionFunction.assertFooterCopyRight();

		} catch (Throwable t) {
			// captureScreenshot();
			// collector.addError(t);
		}

	}

}
