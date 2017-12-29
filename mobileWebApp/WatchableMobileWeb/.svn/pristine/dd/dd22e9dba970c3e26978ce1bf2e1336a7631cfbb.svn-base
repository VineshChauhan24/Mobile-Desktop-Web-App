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
 * @description: This test script  Verify footer link at the bottom of the home page 
 * watchable mobile app home page
 * @Created on 6-May-2016 Last updated on 11-May-2016
 * 
 */

public class VerifyFooterLinks extends BaseTest {

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
	 * Verify footer links are present in footer.
	 */
	@Test(description = "Step 2: Verify footer links are present in footer", priority = 2)
	public void Step02_FooterLinkSection() throws Exception
	{
		//Scrolling down up-to footer of the home page
		HomeFun.scrollToFooterSection();
		
		Assert.assertTrue(driver.findElement(By.xpath(ObjRepoProp.getProperty("homePageFooterLinkSection_XPATH")))
			      .isEnabled(),"Footer links not present in footer section");
		
		Assert.assertTrue(driver.findElement(By.xpath(ObjRepoProp.getProperty("homePageFooterContactUsLink_XPATH")))
			      .isEnabled(),"Footer links not present in footer section");
		
		log.info("Footer links are present in footer above and below the social sharing options.");
		Reporter.log("<p>Footer links are present in footer above and below the social sharing options.</p>");
		
	}
	
	/**
	 * Verify the links present in footer link section
	 */
	@Test(description = "Step 3: Verify the links present in footer link section", priority = 3)
	
	public void Step03_VerifyForFooterLinks() throws Exception
	{
		//Verifying the link which is displaying in  Footer sections 
		Assertions.footerLinkSection();
		
		log.info("The following links should be present among footer links :Privacy Policy,"
				+ "\n Terms Of Us, "
				+ "\n AdChoices above to social share icons"
				+ "\nContact Us below to social share icons");
		Reporter.log("<p>The following links should be present among footer links :Privacy Policy,"
				+ "\n Terms Of Us, "
				+ "\n AdChoices above to social share icons"
				+ "\nContact Us below to social share icons</p>");
	}
	
	
	
	/**
	 * Verify Privacy Policy link is clickable.
	 */
	@Test(description = "Step 4:  Verify Privacy Policy link is clickable.", priority = 4)
	public void Step04_VerifyPrivacyPolicyLink() throws Exception
	{
		Assert.assertTrue(driver.findElement(By.xpath(ObjRepoProp.getProperty("footerPrivacyPolicyLink_XPATH")))
			      .isEnabled(),"Privacy policy link not clikable");
		log.info("Privacy Policy link is clickable.");
		Reporter.log("<p>Privacy Policy link is clickable.</p>");
	}
	
	/**
	 * Verify Terms Of Use link is clickable.
	 */
	@Test(description = "Step 5:  Verify Terms Of Use link is clickable.", priority = 5)
	public void Step05_VerifyTermsOfUseLink() throws Exception
	{
		Assert.assertTrue(driver.findElement(By.xpath(ObjRepoProp.getProperty("footerTermsOfServiceLink_XPATH")))
			      .isEnabled(),"Terms Of Use link not clikable");
		log.info("Terms Of Use link is clickable..");
		Reporter.log("<p>Terms Of Use link is clickable..</p>");
	}
	
	/**
	 * AdChoices link is clickable.
	 */
	@Test(description = "Step 6:  AdChoices link is clickable.", priority = 6)
	public void Step06_VerifyAdChoicesLink() throws Exception
	{
		Assert.assertTrue(driver.findElement(By.xpath(ObjRepoProp.getProperty("footerAdChoicesLink_XPATH")))
			      .isEnabled(),"AdChoices link not clikable");
		log.info("AdChoices link is clickable.");
		Reporter.log("<p>AdChoices link is clickable.</p>");
	}
	
	/**
	 * ContactUs link is clickable.
	 */
	@Test(description = "Step 7:  ContactUs link is clickable.", priority = 7)
	public void Step07_VerifyContactUsLink() throws Exception
	{
		Assert.assertTrue(driver.findElement(By.xpath(ObjRepoProp.getProperty("homePageFooterContactUsLink_XPATH")))
			      .isEnabled(),"Contact Us link not clikable");
		log.info("Contact Us is clickable.");
		Reporter.log("<p>Contact Us is clickable.</p>");
	}
	
	
}
