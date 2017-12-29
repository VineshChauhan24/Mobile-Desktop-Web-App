package comcast.test.restAPI;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;

public class PauseVideo extends BaseTest {
	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();

	@SuppressWarnings("static-access")
	@Test
	public void testVideoTesting() throws Exception {
		/*
		 * This Method is to register new user using Comcast application and to
		 * change a password.
		 */
		RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

		Thread.currentThread().sleep(sleepTime);
		driver.findElement(By.linkText("My Dog Eve")).click();

		Thread.sleep(sleepTimeForVideoPlay);
		driver.findElement(By.cssSelector("button.pause")).click();

		Thread.sleep(sleepTime);
		driver.findElement(By.linkText("Log Out")).click();
	}
}
