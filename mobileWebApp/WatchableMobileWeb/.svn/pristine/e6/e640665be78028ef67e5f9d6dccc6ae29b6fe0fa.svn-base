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
 * @description: This test script verifies the functionality of social sharing options present at bottom of the home page 
 * watchable mobile app home page
 * @Created on 11-May-2016 Last updated on 18-May-2016
 * 
 */

public class VerifyFooterSocialSharing extends BaseTest {

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
	 * Verify social sharing options section are present in footer.
	 */
	@Test(description = "Step 2: Verify social sharing options are present in footer.", priority = 2)
	public void Step02_VerifySocailShareSection() throws Exception
	{
		//Scrolling down up-to footer of the home page
		HomeFun.scrollToFooterSection();
		
		Assert.assertTrue(driver.findElement(By.xpath(ObjRepoProp.getProperty("homePageFooterSocialSharingSection_XPATH")))
			      .isEnabled(),"Social Share Option Section not present in footer section");
				
		log.info("Social sharing options are present in footer above the Contact Us link..");
		Reporter.log("<p>Social sharing options are present in footer above the Contact Us link..</p>");
		
	}
	
	/**
	 * Verify the options present in social sharing  section
	 */
	@Test(description = "Step 3: Verify the options present in social sharing section", priority = 3)
	
	public void Step03_VerifySocailShareOption() throws Exception
	{
		//Verifying the link which is displaying in  Footer sections 
		Assertions.socialShareOptions();
		
		log.info("The following options should be present among footer social sharing : Twitter"
				+ "\n Instagram, "
				+ "\n Facebook");
				
		Reporter.log("<p>The following options should be present among footer social sharing : Twitter"
				+ "\n Instagram, "
				+ "\n Facebook</p>");
	}
	
	/**
	 * Verify user able to Click on Twitter icon and navigate to twiiter page.
	 */
	@Test(description = "Step 4: Verify user able to Click on Twitter icon", priority = 4)
	
	public void Step04_ClickTwitterIcon() throws Exception
	{
		HomeFun.clickOnTwitterIcon();
		// Verify Twitter follow button
		Assert.assertTrue(driver.findElement(By.xpath(ObjRepoProp.getProperty("twitterfollow_XPATH")))
			      .isEnabled(),"twitter follow button not present");
		HomeFun.backToMainApplication();
		log.info("Successfully navigate to twitter login page.");
		Reporter.log("<p>Successfully navigate to twitter login page.</p>");
				
	}
	
	/**
	 * Verify user able to Click on Instagram icon.
	 */
	@Test(description = "Step 5: Verify user able to Click on Instagram icon", priority = 5)
	
	public void Step05_ClickInstagramIcon() throws Exception
	{
		HomeFun.clickOnInstagramIcon();
		// Verify Instagram follow button
		Assert.assertTrue(driver.findElement(By.xpath(ObjRepoProp.getProperty("instagramfollow_XPATH")))
					      .isEnabled(),"Instagram follow button not present");
		HomeFun.backToMainApplication();
		log.info("Successfully navigate to Instagram follow page.");
		Reporter.log("<p>Successfully navigate to Instagram follow page.</p>");
	}
	
	/**
	 * Verify user able to Click on facebook icon.
	 */
	@Test(description = "Step 6: Verify user able to Click on facebook icon", priority = 6)
	
	public void Step06_clickFacebookIcon() throws Exception
	{
		HomeFun.clickOnFacebookIcon();
		// Verify facebook follow button
		Assert.assertTrue(driver.findElement(By.xpath(ObjRepoProp.getProperty("facebookfollow_XPATH")))
			      .isEnabled(),"facebook follow button not present");
		HomeFun.backToMainApplication();
		log.info("Successfully navigate to facebook follow page.");
		Reporter.log("<p>Successfully navigate to facebook follow page.</p>");
	}
	
}