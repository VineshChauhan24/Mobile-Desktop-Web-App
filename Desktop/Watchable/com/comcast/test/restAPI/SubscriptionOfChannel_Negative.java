package comcast.test.restAPI;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.TestDataGenerator;
import comcast.test.config.dataServices.userRegistration.UserRegistrationUsingComcast;

public class SubscriptionOfChannel_Negative extends BaseTest {
	UserLoginFunctions userLogin = new UserLoginFunctions();
	UserRegistrationUsingComcast userReg = new UserRegistrationUsingComcast();
	public long sleepTime = 2000;
	public static WebDriver driver;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
	}

	@Test
	public void testVerifyStoreCategoryVideosAreDisplayed() throws Exception {

		// This method is used to register new user into Comcast Application
		// TestDataGenerator proUtil = new TestDataGenerator();
		// proUtil.load(new FileInputStream(new
		// File("com/apidata.properties")));

		driver.get(DataServiceProperties.APPURL);

		driver.findElement(By.name("user[user_name]")).clear();
		driver.findElement(By.name("user[user_name]")).sendKeys("test_309");

		driver.findElement(By.name("user[password]")).clear();
		driver.findElement(By.name("user[password]")).sendKeys("Demo1111");

		driver.findElement(By.id("user_login")).click();

		Thread.sleep(sleepTime);
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*Popular[\\s\\S]*$"));

		Thread.sleep(sleepTime);
		driver.findElement(By.linkText("Cinematics")).click();

		Thread.sleep(sleepTime);
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*Cinematics[\\s\\S]*$"));

		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*Free[\\s\\S]*$"));

		Thread.sleep(sleepTime);
		driver.findElement(
				By.cssSelector("a.subscribe > span.translation_missing"))
				.click();

		Thread.sleep(sleepTime);
		driver.findElement(By.cssSelector("fieldset > input[type=\"submit\"]"))
				.click();

		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*You have been subscribed.[\\s\\S]*$"));

		Thread.sleep(sleepTime);
		driver.findElement(By.linkText("Subscriptions")).click();

		Thread.sleep(sleepTime);
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*Trailer Mania[\\s\\S]*$"));

		driver.findElement(By.linkText("Cinematics")).click();

		Thread.sleep(sleepTime);
		driver.findElement(By.linkText("Log Out")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
