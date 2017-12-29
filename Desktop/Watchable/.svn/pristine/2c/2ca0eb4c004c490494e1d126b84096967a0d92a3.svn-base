package comcast.test.app.testCases.signUp.signUpFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.config.configServices.utils.BaseTest;

public class SignUpFun extends BaseTest {

	/**
	 * Method Name: clickOnSignUpButton Description: This method used click on
	 * Sign Up button from header
	 * 
	 * @throwsdException
	 */
	public static void clickOnSignUpButton() throws Exception {

		// click on Sign Up button

		driver.findElement(By.xpath(XpathObjectRepo.signUpMenu_XPATH)).click();
		Thread.sleep(sleepTime);
		Thread.sleep(sleepTime);
		log.info("Successfully clicked On Sign Up button");

	}

	/**
	 * Method Name: clickOnFooterSignUpLink Description: This method used click
	 * on Sign Up link from footer
	 * 
	 * @throwsdException
	 */
	public static void clickOnFooterSignUpLink() throws Exception {

		// click on Sign Up button

		driver.findElement(By.xpath(XpathObjectRepo.footerBeforeLoginSignUpLink_XPATH))
				.click();
		Thread.sleep(sleepTime);

		log.info("Successfully clicked On Sign Up link from footer");

	}

	/**
	 * Method Name: clickOnSignUpFormCloseIcon Description: This method used
	 * click on close button from sign up form
	 * 
	 * @throwsdException
	 */
	public static void clickOnSignUpFormCloseIcon() throws Exception {

		// click on close icon

		driver.findElement(
				By.xpath(XpathObjectRepo.signUpFormCloseButton_XPATH)).click();
		Thread.sleep(sleepTime);

		log.info("Successfully clicked On close icon");
		Thread.sleep(sleepTime);

	}

	/**
	 * Method Name: clickOntermsAndConditionsFormCloseIcon Description: This
	 * method used click on close button from terms and conditions form
	 * 
	 * @throwsdException
	 */
	public static void clickOntermsAndConditionsFormCloseIcon()
			throws Exception {

		// click on close icon

		driver.findElement(
				By.xpath(XpathObjectRepo.termsAndConditionsFormCloseButton_XPATH))
				.click();
		Thread.sleep(sleepTime);

		log.info("Successfully clicked On close icon");

	}

	/**
	 * Method Name: clickOntermsAndConditionsFormDeclinetButton Description:
	 * This method used click on Decline button from terms and conditions form
	 * 
	 * @throwsdException
	 */
	public static void clickOntermsAndConditionsFormDeclinetButton()
			throws Exception {

		// click on Decline button

		driver.findElement(
				By.id(XpathObjectRepo.termsAndConditionsFormDeclineButton_ID))
				.click();
		Thread.sleep(sleepTime);

		log.info("Successfully clicked On Decline button");

	}

	/**
	 * Method Name: clickOntermsAndConditionsFormAcceptButton Description: This
	 * method used click on Accept button from terms and conditions form
	 * 
	 * @throwsdException
	 */
	public static void clickOntermsAndConditionsFormAcceptButton()
			throws Exception {

		// click on Accept button

		driver.findElement(
				By.id(XpathObjectRepo.termsAndConditionsFormAcceptButton_ID))
				.click();
		Thread.sleep(sleepTime);
		

		log.info("Successfully clicked On Accept button");

	}

	/**
	 * Method Name: clickOnSignUpButton Description: This method used click on
	 * Sign Up button from sign up form
	 * 
	 * @throwsdException
	 */
	public static void clickOnSignUpFormButton() throws Exception {

		// click on Sign Up button

		driver.findElement(
				By.xpath(XpathObjectRepo.signUpFormSignupButton_XPATH)).click();
		Thread.sleep(sleepTime);
		

		log.info("Successfully clicked On Sign Up button from sign up form");

	}

	/**
	 * Method Name: enterSignUpDetails Description: This method used to fill
	 * sign up form
	 * 
	 * @param driver
	 *            : Native browser driver
	 * @param userName, email, password
	 * 
	 * @throwsdException 
	 */
	public static void enterSignUpDetails(WebDriver driver, String userName,
			String email, String password) throws Exception {

		// Enter User Name
		driver.findElement(By.id(XpathObjectRepo.signUpFormUserNameText_ID))
				.clear();
		driver.findElement(By.id(XpathObjectRepo.signUpFormUserNameText_ID))
				.sendKeys(userName);

		log.info("User name entered: " + userName);

		// Enter Email
		driver.findElement(By.id(XpathObjectRepo.signUpFormEmailText_ID))
				.clear();
		driver.findElement(By.id(XpathObjectRepo.signUpFormEmailText_ID))
				.sendKeys(email);

		log.info("Email entered: " + email);

		// Enter Password
		driver.findElement(By.id(XpathObjectRepo.signUpFormPasswordText_ID))
				.clear();
		driver.findElement(By.id(XpathObjectRepo.signUpFormPasswordText_ID))
				.sendKeys(password);

		log.info("Password entered: " + password);

	}

	/**
	 * Method Name: selectTermsConditionsCheck Description: This method used to
	 * fill sign up form
	 * 
	 * @param driver
	 *            : Native browser driver
	 * @param userName
	 *            , password, confirm password, Captcha
	 * 
	 * @throwsdException
	 */
	public static void selectTermsConditionsCheck() throws Exception {

		// If terms and conditions check box is not selected, selecting it

		boolean termsCondition = false;

		termsCondition = driver.findElement(
				By.xpath(XpathObjectRepo.signUpFormTermsCheckBox_XPATH))
				.isSelected();

		log.info(termsCondition);

		if (termsCondition == false) {
			Thread.sleep(sleepTime);

			CommonFun.mouseOverElementAndClick(driver, driver.findElement(By
					.xpath(XpathObjectRepo.signUpFormTermsCheckBox_XPATH)),
					"Terms Condition check");

		}

	}

	/**
	 * Method Name: clickOnCaptchaHelpButton Description: This method used click
	 * on Captcha help button from sign up form
	 * 
	 * @throwsdException
	 */
	public static void clickOnCaptchaHelpButton() throws Exception {

		// click on Captcha button

		driver.findElement(
				By.xpath(XpathObjectRepo.signUpFormCaptchaHelp_XPATH)).click();

		Thread.sleep(sleepTime);

		log.info("Successfully clicked On Captcha button from sign up form");

	}

	/**
	 * Method Name: clickOnTermsCondtionsLink Description: This method used
	 * click on Terms and Conditions Link from sign up form
	 * 
	 * @throwsdException
	 */
	public static void clickOnTermsConditionsLink() throws Exception {

		// click on Terms and Conditions Link

		driver.findElement(
				By.id(XpathObjectRepo.signUpFormTermsConditionLink_ID)).click();

		Thread.sleep(sleepTime);

		log.info("Successfully clicked On Terms and Conditions Link from sign up form");

	}

	/**
	 * Method Name: clickOnCaptchaAudioButton Description: This method used
	 * click on captcha audio button from captcha image
	 * 
	 * @throwsdException
	 */
	public static void clickOnCaptchaAudioButton() throws Exception {

		// click on captcha audio button

		driver.findElement(
				By.xpath(XpathObjectRepo.signUpFormCaptchaAudio_XPATH)).click();

		Thread.sleep(sleepTime);

		log.info("Successfully clicked On captcha audion button from captcha image");

	}

}
