package comcast.test.app.common.userManagement.userLogin.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: UserLoginFunctions Description: This class provides login module
 * function.
 */
public class UserLoginFunctions extends BaseTest {

	// TestDataGenerator proUtil = new TestDataGenerator();
	// XpathObjectRepo xpathObjectRepo = new UserLoginFunctions();

	/**
	 * Method: UserLoginCredentials Description: This method is used to login
	 * into comcast application.
	 * 
	 * @param driver
	 *            : Native browser driver.
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void UserLoginCredentials(WebDriver driver)
			throws FileNotFoundException, IOException {
		proUtil.load(new FileInputStream(new File("com/data.properties")));
		// Lekshmi : Change the location of the user credentials.
		// String userName=proUtil.getProperty("USER_NAME");
		// String regPassword=proUtil.getProperty("REG_PASSWORD");
		// String regPassword=proUtil.getProperty("PASSWORD");
		String userName = UILablesRepo.USERNAME;
		String regPassword = UILablesRepo.PASSWORD;
		// Lekshmi : Change the object identifiers as part of refactoring
		/*
		 * //driver.findElement(By.name("user[user_name]")).clear();
		 * driver.findElement(By.xpath(".//*[@id='login_username']")).clear();
		 * driver
		 * .findElement(By.xpath(XpathObjectRepo.USERNAME_XPATH)).sendKeys(
		 * userName);
		 * driver.findElement(By.xpath(".//*[@id='login_psd']")).clear();
		 * driver.
		 * findElement(By.xpath(".//*[@id='login_psd']")).sendKeys(regPassword);
		 */

		driver.findElement(By.xpath(XpathObjectRepo.USERNAME_XPATH)).clear();
		driver.findElement(By.xpath(XpathObjectRepo.USERNAME_XPATH)).sendKeys(
				userName);
		driver.findElement(By.xpath(XpathObjectRepo.PASSWORD_XPATH)).clear();
		driver.findElement(By.xpath(XpathObjectRepo.PASSWORD_XPATH)).sendKeys(
				regPassword);

	}

	/**
	 * Method: ChangePassword Description: This method is used to change
	 * password.
	 * 
	 * @param driver
	 *            : Native browser driver.
	 */
	public void ChangePassword(WebDriver driver, String password,
			String regPassword, String confPassword) {
		/*
		 * driver.findElement(By.name("old_password")).clear();
		 * driver.findElement(By.name("old_password")).sendKeys(password);
		 * driver.findElement(By.id("password")).clear();
		 * driver.findElement(By.id("password")).sendKeys(regPassword);
		 * driver.findElement(By.name("confirm_password")).clear();
		 * driver.findElement
		 * (By.name("confirm_password")).sendKeys(confPassword);
		 */
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_CURRENTPASSWORD_FIELD_XPATH))
				.clear();
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_CURRENTPASSWORD_FIELD_XPATH))
				.sendKeys(password);
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_NEWPASSWORD_FIELD_XPATH))
				.clear();
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_NEWPASSWORD_FIELD_XPATH))
				.sendKeys(regPassword);
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_CONFIRMPASSWORD_FIELD_XPATH))
				.clear();
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_CONFIRMPASSWORD_FIELD_XPATH))
				.sendKeys(confPassword);
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_SUBMIT_BUTTON_XPATH))
				.click();
	}

	/**
	 * Method: ChangePasswordFromPM Description: This method is used to change
	 * password in Profile Management.
	 */
	public void ChangePasswordFromPM(WebDriver driver, String password,
			String regPassword, String confPassword) {
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_CURRENTPASSWORD_FIELD_XPATH))
				.clear();
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_CURRENTPASSWORD_FIELD_XPATH))
				.sendKeys(password);
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_NEWPASSWORD_FIELD_XPATH))
				.clear();
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_NEWPASSWORD_FIELD_XPATH))
				.sendKeys(regPassword);
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_CONFIRMPASSWORD_FIELD_XPATH))
				.clear();
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_CONFIRMPASSWORD_FIELD_XPATH))
				.sendKeys(confPassword);
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_SUBMIT_BUTTON_XPATH))
				.click();
	}

	/**
	 * Method: LogOut Description: This method is to Log out from Watchable
	 * Application.
	 * 
	 * @throws InterruptedException
	 */
	public void LogOut(WebDriver driver) throws FileNotFoundException,
			IOException, InterruptedException {
		driver.findElement(By.xpath(XpathObjectRepo.USERNAME_TOPMENU_XPATH))
				.click();
		Thread.sleep(sleepTime);
		driver.findElement(By.xpath(XpathObjectRepo.LOGOUT_XPATH)).click();
		Thread.sleep(sleepTime);

	}

	/**
	 * Method: LogOut Description: This method is to navigate Profile Management
	 * Account details page.
	 * 
	 * @throws InterruptedException
	 */
	public void navigateToAccountDetailsPage(WebDriver driver)
			throws FileNotFoundException, IOException, InterruptedException {
		// driver.findElement(By.id("user-arrow")).click();
		// driver.findElement(By.linkText("Account")).click();
		// Lekshmi : Changed the object identifier
		// driver.findElement(By.xpath(".//*[@id='tool-menu']/div[3]/div")).click();
		// driver.findElement(By.xpath(".//*[@id='tool-menu']/div[4]/ul/li[1]/a")).click();
		driver.findElement(
				By.xpath(XpathObjectRepo.TOPMENU_USERNAME_LINK_XPATH)).click();
		driver.findElement(
				By.xpath(XpathObjectRepo.TOPMENU_DROPDOWN_ACCOUNT_LINK_XPATH))
				.click();

	}

}
