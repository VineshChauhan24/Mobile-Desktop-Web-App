package comcast.test.app.testCases.footerLink.footerLinkFunctions;

import org.openqa.selenium.By;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.config.configServices.utils.BaseTest;

public class FooterLinkFun extends BaseTest {

	/**
	 * Method Name: clickOnHelpLink Description: This method used click on Help
	 * link from footer
	 * 
	 * @throwsdException
	 */
	public static void clickOnHelpLink() throws Exception {

		// click on Help link

		driver.findElement(By.xpath(XpathObjectRepo.footerHelpLink_XPATH))
				.click();
		Thread.sleep(sleepTime);

		log.info("Successfully clicked On Help link");

	}

	/**
	 * Method Name: clickOnHomeLink Description: This method used click on Home
	 * link from footer
	 * 
	 * @throwsdException
	 */
	public static void clickOnHomeLink() throws Exception {

		// click on Home link
		
		 CommonFun.mouseOverElementAndClick(driver, driver.findElement(By
		 .xpath(XpathObjectRepo.footerHomeLinkOtherPage_XPATH)), "Home Link");
		 

		/*driver.findElement(
				By.xpath(XpathObjectRepo.footerHomeLinkOtherPage_XPATH))
				.click();*/
		Thread.sleep(sleepTime);

		log.info("Successfully clicked On Home link");

	}

	/**
	 * Method Name: clickOnPrivacyPolicyLink Description: This method used click
	 * on Privacy Policy link from footer
	 * 
	 * @throwsdException
	 */
	public static void clickOnPrivacyPolicyLink() throws Exception {

		// click on Privacy Policy link

		driver.findElement(
				By.xpath(XpathObjectRepo.footerPrivacyPolicyLink_XPATH))
				.click();
		Thread.sleep(sleepTime);

		log.info("Successfully clicked On Privacy Policy link");

	}

	/**
	 * Method Name: clickOnTermsOfUseLink Description: This method used click on
	 * Terms of Use link from footer
	 * 
	 * @throwsdException
	 */
	public static void clickOnTermsOfUseLink() throws Exception {

		// click on Terms of Use link

		driver.findElement(By.xpath(XpathObjectRepo.footerTermsOfUseLink_XPATH))
				.click();
		Thread.sleep(sleepTime);

		log.info("Successfully clicked On Terms of Use link");

	}

	/**
	 * Method Name: clickOnAdChoicesLink Description: This method used click on
	 * Ad Choices link from footer
	 * 
	 * @throwsdException
	 */
	public static void clickOnAdChoicesLink() throws Exception {

		// click on Ad Choices link

		driver.findElement(By.xpath(XpathObjectRepo.footerAdChoicesLink_XPATH))
				.click();
		Thread.sleep(sleepTime);

		log.info("Successfully clicked On Ad Choices link");

	}
}
