package comcast.test.restAPI;

import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;

public class Testing extends BaseTest {
	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();

	@Test
	public void testLoginToXidioApplicationUsingInValidUName() throws Exception {
		/*
		 * This Method is to register new user using Comcast application and to
		 * change a password.
		 */
		RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

		driver.findElement(By.linkText("HOME")).click();

		Thread.sleep(sleepTime);
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*Up Next[\\s\\S]*$"));

		boolean present;
		int a;
		int count = 0;
		do {
			try {
				a = driver.findElements(By.linkText("The big buck bunny"))
						.size();
				// a=driver.findElements(By.xpath(".//*[@id='slider_up_next']/ul[1]/li[2]/article/h1/a")).size();
				if (a == 0)
					present = false;
				else
					present = true;
				count++;
			} catch (NoSuchElementException e) {
				present = false;
			}
			if (present == false)
				driver.findElement(By.xpath(".//*[@id='next_up_next']/span"))
						.click();
			Thread.sleep(sleepTime);
		} while (present = true && count < 4);

		assertTrue(driver.findElement(By.cssSelector("BODY")).getText()
				.matches("^[\\s\\S]*The big buck bunny[\\s\\S]*$"));

		Thread.sleep(sleepTime);
		driver.findElement(By.linkText("Log Out")).click();
	}
}
