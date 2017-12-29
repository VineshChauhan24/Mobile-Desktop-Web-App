package comcast.test.restAPI;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;

public class Temp extends BaseTest {
	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();

	@Test
	public void testLoginToXidioApplicationUsingInValidUName() throws Exception {

		driver.get(proUtil.getProperty("HOMEAPPURL"));

		driver.findElement(By.linkText("Sign Up")).click();

		String a = driver.findElement(
				By.xpath(".//*[@id='recaptcha_challenge_image']")).getText();

		String b = driver.findElement(
				By.xpath(".//*[@id='recaptcha_challenge_image']"))
				.getAttribute("src");

		String c = driver.findElement(By.id("recaptcha_challenge_image"))
				.getText();

		Thread.sleep(sleepTime);
		// driver.findElement(By.xpath("(//a[contains(text(),'Super Mario Brothers')])[3]")).click();
		// driver.findElement(By.xpath("//div[@id='genre']/div/div[2]/div[2]/section/div/div/ul/li[4]/article/h1/a")).click();
		// driver.findElement(By.xpath("(//a[contains(@href, '/shows/2011136216-super-mario-brothers')])[6]")).click();
		// driver.findElement(By.xpath("//div[2]/section/div/div/ul/li[4]/article/h1/a")).click();
		// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Super Mario Brothers[\\s\\S]*$"));
		boolean test;
		test = driver
				.findElements(
						By.xpath(".//*[@id='featured']/div/section/div/div/ul[1]/li[1]/article/a/div/div"))
				.size() > 0;
		Actions action = new Actions(driver);
		action.sendKeys(Keys.DOWN).perform();
		action.sendKeys(Keys.UP).perform();
		for (int i = 0; i < 15; i++) {
			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
			Thread.sleep(1000);
		}

		driver.findElement(
				By.xpath(".//*[@id='genre']/div/div[1]/header/nav/ul/li[2]/a"))
				.click();
		boolean proj;
		proj = driver
				.findElements(
						By.xpath(".//*[@id='genre']/div/div[2]/div[3]/section/div/div/ul/li[3]/article/a"))
				.size() > 0;

	}
}
