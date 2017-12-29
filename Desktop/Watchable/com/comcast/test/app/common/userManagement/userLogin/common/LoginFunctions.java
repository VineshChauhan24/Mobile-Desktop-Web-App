package comcast.test.app.common.userManagement.userLogin.common;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: LoginFunctions Description: This class provides all login module
 * function. in Comcast application
 * **/
public class LoginFunctions extends BaseTest {

	/**
	 * Method Name: LoginToXidioApplication Description: Enter Invalid UserName
	 * and Password.
	 * 
	 * @param driver
	 *            : Native browser driver
	 * @param userName
	 *            : Comcast application user name
	 * @param password
	 *            : Comcast application password
	 */
	public void LoginToXidioApplication(WebDriver driver, String userName,
			String password) {
		/*
		 * driver.findElement(By.name("user[user_name]")).clear();
		 * driver.findElement(By.name("user[user_name]")).sendKeys(userName);
		 * driver.findElement(By.name("user[password]")).clear();
		 * driver.findElement(By.name("user[password]")).sendKeys(password);
		 */
		driver.findElement(By.xpath(XpathObjectRepo.USERNAME_XPATH)).clear();
		driver.findElement(By.xpath(XpathObjectRepo.USERNAME_XPATH)).sendKeys(
				userName);
		driver.findElement(By.xpath(XpathObjectRepo.PASSWORD_XPATH)).clear();
		driver.findElement(By.xpath(XpathObjectRepo.PASSWORD_XPATH)).sendKeys(
				password);
	}

	/**
	 * Method Name: LoginToWatchableApplication Description: This method used to
	 * login to Watchable application.
	 * 
	 * @param driver
	 *            : Native browser driver
	 * @param userName
	 *            : Watchable application user name
	 * @param password
	 *            : Watchable application password
	 * @throwsdException
	 */
	public static void loginToWatchableApplication(WebDriver driver,
			String userName, String password) throws Exception {

		// Click on Login button from header menu

		driver.findElement(By.xpath(XpathObjectRepo.loginMenu_XPATH)).click();

		Thread.sleep(sleepTime);

		// Verify login pop up opened successfully

		assertTrue(
				"Login pop up is not opened",
				CommonFun.isElementPresent(driver,
						By.xpath(XpathObjectRepo.loginForm_XPATH)));
		log.info("Login form opened successfully");

		// Enter User Name
		driver.findElement(By.id(XpathObjectRepo.loginFormEmailText_ID))
				.clear();
		driver.findElement(By.id(XpathObjectRepo.loginFormEmailText_ID))
				.sendKeys(userName);

		log.info("User name entered: " + userName);

		// Enter Password
		driver.findElement(By.id(XpathObjectRepo.loginFormPasswordText_ID))
				.clear();
		driver.findElement(By.id(XpathObjectRepo.loginFormPasswordText_ID))
				.sendKeys(password);

		log.info("Password entered: " + password);

		// Click on Login button
		driver.findElement(By.id(XpathObjectRepo.loginFormLoginButton_ID))
				.click();
		Thread.sleep(sleepTime);
		log.info("Clicked on Login button from Login form");

		WebElement loginError = driver.findElement(By
				.id(XpathObjectRepo.loginError_ID));

		if (loginError.isDisplayed() == false) {

			// Verifying successful login
			assertTrue(
					"Login to Watchable application is failed...!",
					CommonFun.isElementPresent(driver,
							By.xpath(XpathObjectRepo.loginUserEmailTitle_XPATH)));
			log.info("Successfully login Watchable application...!");
		} else {
			log.error("Login to Watchable application is failed with invalid credentials...!");
		}

	}

	/**
	 * Method: LogOut Description: This method is to Log out from Watchable
	 * Application.
	 * 
	 * @throws InterruptedException
	 */
	public static void LogOut(WebDriver driver) throws Exception {

		Thread.sleep(sleepTime);

		// Mouse over on user profile icon

		CommonFun
				.mouseOverElement(driver, driver.findElement(By
						.xpath(XpathObjectRepo.profileIcon_XPATH)));

		Thread.sleep(sleepTime);
		// Click on Logout Menu
		driver.findElement(By.xpath(XpathObjectRepo.logoutButton_XPATH))
				.click();
		Thread.sleep(sleepTime);

		// Verifying successful logout from the application

		driver.navigate().refresh();

		assertTrue(
				"Failed to logout from the application...!",
				CommonFun.isElementPresent(driver,
						By.xpath(XpathObjectRepo.loginMenu_XPATH)));
		log.info("Successfully logout from the application...!");
		Thread.sleep(sleepTime);

	}

	/**
	 * Method: navigateToLoginPage Description: This method is to navigate Login
	 * Page.
	 * 
	 * @throws InterruptedException
	 */
	public void navigateToLoginPage() throws FileNotFoundException, IOException {
		// Navigate to Log In page.
		driver.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
				.click();
	}

}
