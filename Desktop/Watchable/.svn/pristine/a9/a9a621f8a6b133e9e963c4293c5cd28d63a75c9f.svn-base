package comcast.test.app.common.userManagement.userRegistration.common;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.TestDataGenerator;

/**
 * Class Name: UserRegistrationValidationFuncitons Description: This class file
 * provides all Registration field related function.
 **/
public class UserRegistrationValidationFuncitons extends BaseTest {

	/**
	 * Method: validateFirstName Description: This method is used to validate
	 * First Name field in registration page using Comcast Application.
	 * 
	 * @param driver
	 *            : Native browser driver
	 **/
	public void validateUserRegistrationFields(WebDriver driver,
			String zipcode, String regPass, String confirmationPass)
			throws FileNotFoundException, IOException, InterruptedException {
		TestDataGenerator.modifyDataProperties();

		driver.findElement(
				By.xpath(XpathObjectRepo.SIGNUPFORM_EMAIL_TEXTFIELD_XPATH))
				.clear();
		driver.findElement(
				By.xpath(XpathObjectRepo.SIGNUPFORM_EMAIL_TEXTFIELD_XPATH))
				.sendKeys(TestDataGenerator.emailId);

		driver.findElement(
				By.xpath(XpathObjectRepo.SIGNUPFORM_PASSWD_TEXTFIELD_XPATH))
				.clear();
		driver.findElement(
				By.xpath(XpathObjectRepo.SIGNUPFORM_PASSWD_TEXTFIELD_XPATH))
				.sendKeys(regPass);

		driver.findElement(
				By.xpath(XpathObjectRepo.SIGNUPFORM_CONFPASSWD_TEXTFIELD))
				.clear();
		driver.findElement(
				By.xpath(XpathObjectRepo.SIGNUPFORM_CONFPASSWD_TEXTFIELD))
				.sendKeys(confirmationPass);

		driver.findElement(
				By.xpath(XpathObjectRepo.SIGNUPFORM_ZIP_TEXTFIELD_XPATH))
				.clear();
		driver.findElement(
				By.xpath(XpathObjectRepo.SIGNUPFORM_ZIP_TEXTFIELD_XPATH))
				.sendKeys(zipcode);

		driver.findElement(
				By.xpath(XpathObjectRepo.SIGNUPFORM_TERMSCOND_CHECKBOX_XPATH))
				.click();
		Thread.sleep(sleepTime);
	}

	/**
	 * Method: validateInvalidZipEmail Description: This method is used to
	 * validate Invalid Email in registration page using Comcast Application.
	 * 
	 * @param driver
	 *            : Native browser driver
	 **/
	public void validateInvalidEmailID(WebDriver driver, String emailId,
			String zipcode, String regPass) throws InterruptedException,
			FileNotFoundException, IOException {
		// This is commented because registration feature is not working, once
		// its started working remove below comments.
		// TestDataGenerator.modifyDataProperties();

		// driver.findElement(By.name("user[email]")).clear();
		driver.findElement(By.xpath(".//*[@id='signup_username']")).clear();
		driver.findElement(By.xpath(".//*[@id='signup_username']")).sendKeys(
				emailId);

		// driver.findElement(By.name("user[zip]")).sendKeys(zipcode);
		driver.findElement(
				By.xpath(".//*[@id='step-0']/fieldset[2]/div[2]/label/input"))
				.sendKeys(zipcode);

		// driver.findElement(By.name("user[password]")).clear();

		driver.findElement(By.xpath(".//*[@id='password']")).clear();
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys(regPass);

		// driver.findElement(By.name("confirm_signup_password")).clear();

		driver.findElement(
				By.xpath(".//*[@id='step-0']/fieldset[4]/div[2]/label/input"))
				.clear();
		driver.findElement(
				By.xpath(".//*[@id='step-0']/fieldset[4]/div[2]/label/input"))
				.sendKeys(regPass);

		driver.findElement(
				By.xpath(".//*[@id='step-0']/fieldset[6]/div[1]/label"))
				.click();
		Thread.sleep(sleepTime);
	}

	/**
	 * Method: validateCaptchaField Description: This method is used to validate
	 * captcha field in registration page using Comcast Application.
	 * 
	 * @param driver
	 *            : Native browser driver
	 **/
	public void validateCaptchaField(WebDriver driver, String captcha)
			throws InterruptedException, FileNotFoundException, IOException {
		driver.findElement(By.name("recaptcha_response_field")).clear();
		driver.findElement(By.name("recaptcha_response_field")).sendKeys(
				captcha);
	}

}
