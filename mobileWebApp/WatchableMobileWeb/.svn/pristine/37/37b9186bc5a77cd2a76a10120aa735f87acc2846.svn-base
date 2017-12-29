package comcast.test.homepage;

import static comcast.util.PropertyFileReader.ObjRepoProp;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import comcast.common.asserts.Assertions;
import comcast.config.BaseTest;
import comcast.custom.CustomFun;
import comcast.test.homepage.homePageFunctions.HomeFun;


/**
 * 
 * @author Karthik.ashoka
 * @description: Test script verifies the functionality of email sharing pop-up window.
 * @Created on 6-May-2016 Last updated on 6-May-2016
 * 
 */

public class VerifyCloseButtonInEmailSharingPopUp extends BaseTest {

	/**
	 * Open the browser, Enter the Watchable URL
	 */
	@Test(description = "Step 1: Open the browser, Enter the Watchable URL", priority = 1)
	public void Step01_NavigeteToURL() throws Exception {

		// Navigates to Watchable mobile web URL
		HomeFun.navigateToWatchable_URL();

		// Verify successfully navigate to home page
		Assertions.assertWatchableTitle();

		log.info("Successfully navigated to Watchable Mobile web Home page  \n");
		Reporter.log("<p>Successfully navigated to Watchable Mobile web Home page");

	}
	
	/**
	 * Verify SPREAD THE LOVE! BUTTON! Button is displaying in playlist section.
	 */
	@Test(description = "Step 2: Verify SPREAD THE LOVE! BUTTON! Button is displaying in playlist section", priority = 2)
	public void Step02_VerifySPREADTHELOVEBUTTON() throws Exception
	{
		//Verifying SPREAD THE LOVE BUtton dispalying or not
		Assertions.spreadTheLoveButton();
		log.info("SPREAD THE LOVE! BUTTON! button is displaying on playlist section");
		Reporter.log("<p>SPREAD THE LOVE! BUTTON! button is displaying on playlist section</p>");
	}
	
	/**
	 * Click on SPREAD THE LOVE! BUTTON! Button.
	 */
	@Test(description = "Step 3: Click on SPREAD THE LOVE! BUTTON! Button.", priority = 3)
	public void Step03_ClickOnSpreadLoveButton() throws Exception
	{
		//Clicking on the SPREAD THE LOVE Button
		HomeFun.clickOnTheSpreadLoveButton();
		log.info("Playlist sharing popup window is opened successfully.");
		Reporter.log("<p>Playlist sharing popup window is opened successfully.</p>");
	}
	
	/**
	 * Verify Email share option displaying in sharing popup window.
	 */
	@Test(description = "Step 4: Verify Email share option displaying in sharing popup window.", priority = 4)
	public void Step04_VerifyEmailShareOption() throws Exception
	{
		//Verifying Email share option
		Assertions.emailShareOption();
		log.info("Email sharing option is present in social sharing pop up window.");
		Reporter.log("<p>Email sharing option is present in social sharing pop up window.</p>");
	}
	
	/**
	 * Verify Email share option can be click.
	 */
	@Test(description = "Step 5: Verify Email share option can be clickable.", priority = 5)
	public void Step05_ClickOnEmailShareOption() throws Exception
	{
		//Click on Email share option from SPREAD THE LOVE Button
		HomeFun.clickOnEmailShareOption();
		
		//To verify email share popup displaying successfully
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("emailSharePopUp_XPATH")),
						driver), "Email share popup not displaying");
		
		log.info("Email sharing pop-up window opened successfully.");
		Reporter.log("<p>Email sharing pop-up window opened successfully.</p>");
	}
	
	/**
	 * Verify Close icon is present at top right corner of email sharing pop-up window.
	 */
	@Test(description = "Step 6: Verify Close icon is present at top right corner of email sharing pop-up window.", priority = 6)
	public void Step06_VerifyCloseIconInEmailPopup() throws Exception
	{
		//Verify the close icon displaying in email share popup
		Assertions.closeIconOfEmailPopup();
		log.info("Close icon is present at top right corner of email sharing pop-up window.");
		Reporter.log("<p>Close icon is present at top right corner of email sharing pop-up window.</p>");
	}
	
	/**
	 * Verify Email sharing pop-up window closed successfully.
	 */
	@Test(description = "Step 7: Email sharing pop-up window closed successfully..", priority = 7)
	public void Step07_ClickOnEmailShareCloseIcon() throws Exception
	{
		//Click on Email share option from SPREAD THE LOVE Button
		HomeFun.clickOnCloseIconFromEmailPopUp();
		
		//To verify Email share popup closed successfully
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("emailSharePopUp_XPATH")),
				driver), "Email share popup not displaying");
		
		log.info("Email sharing pop-up window closed successfully..");
		Reporter.log("<p>Email sharing pop-up window closed successfully.</p>");
	}
	
	
}
