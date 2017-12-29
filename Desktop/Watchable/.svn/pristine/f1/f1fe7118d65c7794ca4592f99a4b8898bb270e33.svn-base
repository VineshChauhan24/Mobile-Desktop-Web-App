package comcast.test.app.common.userManagement.profileManagement.common;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.config.configServices.DataServiceProperties;

/**
 * Class Name: ProfileManagementScenarioBasedFunctions Description: This class
 * is contains all scenario based functions for profile management module.
 */
public class ProfileManagementScenarioBasedFunctions {
	/**
	 * Method: VerifyNewPassAndConfirmPassMatch Description: This method
	 * verifies new password and confirm pass are same.
	 * 
	 * @param driver
	 *            : Native browser driver
	 */
	public void VerifyNewPassAndConfirmPassMatch(WebDriver driver,
			String odlPassword, String password, String confPassword) {
		/*
		 * driver.findElement(By.name("old_password")).clear();
		 * driver.findElement(By.name("old_password")).sendKeys(odlPassword);
		 * driver.findElement(By.id("password")).clear();
		 * driver.findElement(By.id("password")).sendKeys(password);
		 * driver.findElement(By.name("confirm_password")).clear();
		 * driver.findElement
		 * (By.name("confirm_password")).sendKeys(confPassword);
		 * driver.findElement(By.name("commit")).click();
		 */

		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_CURRENTPASSWORD_FIELD_XPATH))
				.clear();
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_CURRENTPASSWORD_FIELD_XPATH))
				.sendKeys(odlPassword);
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_NEWPASSWORD_FIELD_XPATH))
				.clear();
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_NEWPASSWORD_FIELD_XPATH))
				.sendKeys(password);
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
	 * Method: ValidateInvalidCreditCardDetials Description: This method is to
	 * validates invalid credit card Details.
	 * 
	 * @param driver
	 *            : Native browser driver
	 * 
	 *            Lekshmi : As the Credit card functionality is removed from the
	 *            Watchable application, the assertion is not refactoring.
	 */
	public void ValidateInvalidCreditCardDetials(WebDriver driver,
			String ccNumber, String ccExpiryMonth, String ccYear,
			String ccSecuritycode) {
		driver.findElement(By.name("user[cc_number]")).clear();
		driver.findElement(By.name("user[cc_number]")).sendKeys(ccNumber);
		new Select(driver.findElement(By.name("user[cc_expiration_month]")))
				.selectByVisibleText(ccExpiryMonth);
		new Select(driver.findElement(By.name("user[cc_expiration_year]")))
				.selectByVisibleText(ccYear);

		// Below lines are committed because of Requirement change.
		/*
		 * driver.findElement(By.name("user[cc_expiration_month]")).clear();
		 * driver
		 * .findElement(By.name("user[cc_expiration_month]")).sendKeys(ccExpiryMonth
		 * ); driver.findElement(By.name("user[cc_expiration_year]")).clear();
		 * driver
		 * .findElement(By.name("user[cc_expiration_year]")).sendKeys(ccYear);
		 */

		driver.findElement(By.name("user[cc_security_code]")).clear();
		driver.findElement(By.name("user[cc_security_code]")).sendKeys(
				ccSecuritycode);
	}

	/**
	 * Method: ValidatePaymentInfoMandatoryField Description: This method is to
	 * validates Payment Info mandatory fields.
	 * 
	 * @param driver
	 *            : Native browser driver
	 * 
	 *            Lekshmi : As the Payment info functionality is removed from
	 *            the Watchable application, the assertion is not refactoring.
	 */
	public void ValidatePaymentInfoMandatoryField(WebDriver driver,
			String ccNumber, String ccYear, String ccSecuritycode)
			throws FileNotFoundException, IOException {
		driver.findElement(By.name("user[cc_number]")).clear();
		driver.findElement(By.name("user[cc_number]")).sendKeys(ccNumber);
		new Select(driver.findElement(By.name("user[cc_expiration_year]")))
				.selectByVisibleText(ccYear);

		// Below lines are committed because of Requirement change.
		/*
		 * driver.findElement(By.name("user[cc_expiration_year]")).clear();
		 * driver
		 * .findElement(By.name("user[cc_expiration_year]")).sendKeys(ccYear);
		 */

		driver.findElement(By.name("user[cc_security_code]")).clear();
		driver.findElement(By.name("user[cc_security_code]")).sendKeys(
				ccSecuritycode);
	}

	/**
	 * Method: ValidatePasswordFieldByEnteringInvalidPass Description: This
	 * method is to validates Password field with in valid pass.
	 * 
	 * @param driver
	 *            : Native browser driver
	 */
	public void ValidateChangePinCodeFields(WebDriver driver, String ccPinCode,
			String password) {
		/*
		 * driver.findElement(By.name("cc_pin_code")).clear();
		 * driver.findElement(By.name("cc_pin_code")).sendKeys(ccPinCode);
		 * driver.findElement(By.name("password")).clear();
		 * driver.findElement(By.name("password")).sendKeys(password);
		 */
		driver.findElement(By.xpath(XpathObjectRepo.ACCOUNT_ZIP_FIELD_XPATH))
				.clear();
		driver.findElement(By.xpath(XpathObjectRepo.ACCOUNT_ZIP_FIELD_XPATH))
				.sendKeys(ccPinCode);
	}

	/**
	 * Method: UpdateActivationCode Description: This method is to update
	 * activation code.
	 * 
	 * @param driver
	 *            : Native browser driver
	 * 
	 *            Lekshmi : As the Activation code functionality is removed from
	 *            the Watchable application, the assertion is not refactoring.
	 */
	public void UpdateActivationCode(WebDriver driver, String activationCode) {
		driver.findElement(By.name("user[activation_code]")).clear();
		driver.findElement(By.name("user[activation_code]")).sendKeys(
				activationCode);
	}

	/**
	 * Method: ValidatePinCodeFieldByEnteringInvalidPinLength Description: This
	 * method is to validates Pin code field by entering in valid Pin length.
	 * 
	 * @param driver
	 *            : Native browser driver
	 */
	public void ValidatePinCodeFieldByEnteringInvalidPinLength(WebDriver driver) {
		/*
		 * driver.findElement(By.name("cc_pin_code")).clear();
		 * driver.findElement
		 * (By.name("cc_pin_code")).sendKeys(DataServiceProperties
		 * ._INVALID_CC_PIN_CODE_LENGTH);
		 * driver.findElement(By.name("password")).clear();
		 * driver.findElement(By
		 * .name("password")).sendKeys(DataServiceProperties._PASSWORD);
		 */
		driver.findElement(By.xpath(XpathObjectRepo.ACCOUNT_ZIP_FIELD_XPATH))
				.clear();
		driver.findElement(By.xpath(XpathObjectRepo.ACCOUNT_ZIP_FIELD_XPATH))
				.sendKeys(DataServiceProperties._INVALID_CC_PIN_CODE_LENGTH);
	}

	public void ValidatePassword(WebDriver driver, String nonDigitPass,
			String restPass, String invalidPass) {
		/*
		 * driver.findElement(By.name("old_password")).clear();
		 * driver.findElement(By.name("old_password")).sendKeys(nonDigitPass);
		 * 
		 * driver.findElement(By.id("password")).clear();
		 * driver.findElement(By.id("password")).sendKeys(restPass);
		 * 
		 * driver.findElement(By.name("confirm_password")).clear();
		 * driver.findElement
		 * (By.name("confirm_password")).sendKeys(invalidPass);
		 */
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_CURRENTPASSWORD_FIELD_XPATH))
				.clear();
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_CURRENTPASSWORD_FIELD_XPATH))
				.sendKeys(nonDigitPass);
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_NEWPASSWORD_FIELD_XPATH))
				.clear();
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_NEWPASSWORD_FIELD_XPATH))
				.sendKeys(restPass);
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_CONFIRMPASSWORD_FIELD_XPATH))
				.clear();
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_CONFIRMPASSWORD_FIELD_XPATH))
				.sendKeys(invalidPass);
		driver.findElement(
				By.xpath(XpathObjectRepo.CHANGEPASSWORD_SUBMIT_BUTTON_XPATH))
				.click();
	}
}
