package comcast.test.homepage;

import static comcast.util.PropertyFileReader.ObjRepoProp;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import comcast.common.asserts.Assertions;
import comcast.config.BaseTest;
import comcast.test.homepage.homePageFunctions.HomeFun;


/**
 * 
 * @author Karthik.Ashoka
 * @description: This test script Verify “i” icon functionality in header section
 * @Created on 28-April-2016 Last updated on 29-April-2016
 * 
 */

public class VerifyiIconInHomePageHeaderSection extends BaseTest {

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
	 * Verify i- icon display in header section
	 */
	
	@Test(description = "Step 2: Verify “i” icon is present in header section of home page.", priority = 2)
	public void Step02_InfoiconPresence() throws Exception
	{
		//Verify I-icon in header.
		Assertions.mobileWebHeaderContents();
		log.info("i-icon is present in header section of home page");
		Reporter.log("<p>i-icon is present in header section of home page</p>");
	}
	
	/**
	 * Verify i- icon display in header section is to be clickable
	 */
	
	@Test(description = "Step 3: Verify “i” icon is present in header section able to click.", priority = 3)
	public void Step03_ClickOnInfoIcon() throws Exception
	{
		//verify i-icon in enabled status
		Assert.assertTrue(driver.findElement(By.xpath(ObjRepoProp.getProperty("homePageIIcon_XPATH")))
			      .isEnabled(),"i-icon in enabled status");
		log.info("i-icon is clickable");
		Reporter.log("<p>i-icon is clickable</p>");
			
	}
	
	/**
	 * Verify UpSell overlay pop up opened successfully after click on i-icon
	 */
	@Test(description = "Step 4: UpSell overlay pop up opened successfully after click on i-icon.", priority = 4)
	public void Step04_UpSellOverLayPopUp() throws Exception
	{
				
		//Verify able to click on i-icon
		HomeFun.clickoniicon();
		log.info("Up cell overlay pop up opened successfully.");
		Reporter.log("<p>Up cell overlay pop up opened successfully</p>");

	}
	

}
