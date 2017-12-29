package comcast.test.app.testCases.contactUs.contactUsFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.config.configServices.utils.BaseTest;

public class ContactUsFun extends BaseTest {

	/**
	 * Method Name: clickOnContactUsLink Description: This method used click on
	 * Contact Us link from header
	 * 
	 * @throwsdException
	 */
	public static void clickOnContactUsLink() throws Exception {

		// click on Contact Us link

		driver.findElement(By.xpath(XpathObjectRepo.footerContactUsLink_XPATH))
				.click();
		Thread.sleep(sleepTime);

		log.info("Successfully clicked On Contact Us link");

	}

	/**
	 * Method Name: clickOnContactUsFormCloseIcon Description: This method used
	 * click on close button from contact us form
	 * 
	 * @throwsdException
	 */
	public static void clickOnContactUsFormCloseIcon() throws Exception {

		// click on close icon

		driver.findElement(
				By.id(XpathObjectRepo.contactUsFormCloseButton_ID))
				.click();
		Thread.sleep(sleepTime);

		log.info("Successfully clicked On close icon");

	}

	/**
	 * Method Name: clickOnSubmitButton Description: This method used click on
	 * submit button from contact us form
	 * 
	 * @throwsdException
	 */
	public static void clickOnSubmitButton() throws Exception {

		// click on Submit button

		driver.findElement(By.id(XpathObjectRepo.contactUsFormSubmitButton_ID))
				.click();
		Thread.sleep(sleepTime);

		log.info("Successfully clicked On submit button");

	}

	/**
	 * Method Name: clickOnResetButton Description: This method used click on
	 * reset button from contact us form
	 * 
	 * @throwsdException
	 */
	public static void clickOnResetButton() throws Exception {

		// click on Reset button

		driver.findElement(By.id(XpathObjectRepo.contactUsFormResetButton_ID))
				.click();
		Thread.sleep(sleepTime);

		log.info("Successfully clicked On Reset button");

	}

	/**
	 * Method Name: selectReason Description: This method used select Reason
	 * from contact us form reason list
	 * 
	 * @throwsdException
	 */
	public static void selectReason() throws Exception {

		// click on close icon

		new Select(driver.findElement(By
				.id(XpathObjectRepo.contactUsFormContactReasonList_ID)))
				.selectByIndex(2);
		Thread.sleep(sleepTime);
		log.info("Successfully selected value from reason list");

	}

	/**
	 * Method Name: enterContachUsDetails Description: This method used to fill
	 * contact us form
	 * 
	 * @param driver
	 *            : Native browser driver
	 * @param Reason
	 *            , email and Captcha
	 * 
	 * @throwsdException
	 */
	public static void enterContachUsDetails(WebDriver driver,
			String reasonDesc, String email) throws Exception {

		// Enter Reason Description
		Thread.sleep(sleepTime);

		Actions action = new Actions(driver);

		action.sendKeys(driver.findElement(By
				.xpath(XpathObjectRepo.contactUsFormReasonDesc_XPATH)),
				reasonDesc);
		driver.findElement(By.id(XpathObjectRepo.contactUsFormEmailText_ID))
		.sendKeys(reasonDesc);

		log.info("Reason Description entered: " + reasonDesc);

		// Enter Email

		driver.findElement(By.id(XpathObjectRepo.contactUsFormEmailText_ID))
				.clear();
		driver.findElement(By.id(XpathObjectRepo.contactUsFormEmailText_ID))
				.sendKeys(email);

		log.info("Email entered: " + email);

		

	}

}
