package comcast.test.app.common.userManagement.userRegistration.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.TestDataGenerator;

/**
 * Class Name: UserRegistrationUsingComcast Description: This test case allows
 * the user to register into Comcast application including credit card details.
 **/

public class UserRegistrationFunction extends BaseTest {
	TestDataGenerator proUtil = new TestDataGenerator();

	/**
	 * Method: CreditCardDetials Description: This method is used to enter all
	 * credit card detail fields in Comcast Application.
	 * 
	 * @param driver
	 *            : Native browser driver
	 * @param ccNumber
	 * @param ccExpiryMonth
	 * @param ccSecurityCode
	 * @param ccPincode
	 * 
	 *            Lekshmi : As the credit card functionality has been removed
	 *            from the Watchable application, the function is not
	 *            refactoring.
	 */
	public void CreditCardDetials(WebDriver driver, String ccNumber,
			String ccExpiryMonth, String ccSecurityCode, String ccPincode)
			throws FileNotFoundException, IOException, InterruptedException {
		// driver.findElement(By.name("user[cc_number]")).clear();
		driver.findElement(By.name("user[cc_number]")).sendKeys(ccNumber);
		new Select(driver.findElement(By.name("user[cc_expiration_month]")))
				.selectByVisibleText(ccExpiryMonth);
		driver.findElement(By.cssSelector("option[value=\"2\"]")).click();
		driver.findElement(By.name("user[cc_security_code]")).clear();
		driver.findElement(By.name("user[cc_security_code]")).sendKeys(
				ccSecurityCode);
		driver.findElement(By.name("user[cc_pin_code]")).clear();
		driver.findElement(By.name("user[cc_pin_code]")).sendKeys(ccPincode);
	}

	/**
	 * Method: RegistrationDetails Description: This method is used to register
	 * new user in in Comcast Application.
	 * 
	 * @param driver
	 *            : Native browser driver
	 **/
	public void RegistrationDetails(WebDriver driver, String zipcode,
			String regPass, String confirmPass) throws FileNotFoundException,
			IOException, InterruptedException {
		TestDataGenerator.modifyDataProperties();
		proUtil.load(new FileInputStream(new File("com/data.properties")));

		/*
		 * driver.findElement(By.name("user[email]")).clear();
		 * driver.findElement
		 * (By.name("user[email]")).sendKeys(TestDataGenerator.emailId);
		 * 
		 * driver.findElement(By.name("user[zip]")).clear();
		 * driver.findElement(By.name("user[zip]")).sendKeys(zipcode);
		 * 
		 * driver.findElement(By.name("user[password]")).clear();
		 * driver.findElement(By.name("user[password]")).sendKeys(regPass);
		 * 
		 * driver.findElement(By.name("confirm_signup_password")).clear();
		 * driver
		 * .findElement(By.name("confirm_signup_password")).sendKeys(confirmPass
		 * );
		 */

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
				.sendKeys(confirmPass);

		driver.findElement(
				By.xpath(XpathObjectRepo.SIGNUPFORM_ZIP_TEXTFIELD_XPATH))
				.clear();
		driver.findElement(
				By.xpath(XpathObjectRepo.SIGNUPFORM_ZIP_TEXTFIELD_XPATH))
				.sendKeys(zipcode);

		TestDataGenerator.updateRegPasswordToPasswordDataPropertiesFields();
	}

	/**
	 * Method: termsAndConfitionChkBox Description: This method is used to check
	 * terms and condition check box. in Comcast Application.
	 **/
	public void termsAndConfitionChkBox() throws FileNotFoundException,
			IOException, InterruptedException {
		driver.findElement(
				By.xpath(XpathObjectRepo.SIGNUPFORM_TERMSCOND_CHECKBOX_XPATH))
				.click();
	}
}
