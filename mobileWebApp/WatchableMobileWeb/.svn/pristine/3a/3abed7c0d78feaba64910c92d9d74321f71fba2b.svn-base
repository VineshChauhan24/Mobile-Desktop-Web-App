package comcast.common.asserts;

import static comcast.util.PropertyFileReader.ObjRepoProp;
import static comcast.util.PropertyFileReader.TextProp;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import comcast.config.BaseTest;
import comcast.custom.CustomFun;




public class Assertions extends BaseTest {

	// For logging
	public static Logger log = Logger.getLogger(Assertions.class);

	/**
	 * Method Name: assertWatchableLogo This is method verify Watchable
	 * application is successfully opened 
	 * 
	 * @return
	 */
	public static void assertWatchableTitle() throws Exception {
		
		//Verify use successfully navigated to mobile web home page (Asserting watchable Logo)
		Assert.assertTrue(CustomFun.isElementPresent(
				By.xpath(ObjRepoProp.getProperty("homePageWatchableLogo_XPATH")),
				driver), "Failed to navigate to Watchable Mobile web Home Page");
		
			}
	/** 
	 * Method Name:This is method verify UpSell Banner present or Not
	 * UpSell Banner are present
	 * 
	 * @return
	 */
	public static void mobileWebUpSellSection() throws Exception
	{
		//verify UpSell Banner in Watchable Mobile Web Home Page
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("homePageUpSellBar_XPATH")),
				driver), "UpSell Banner not present");
	}
	
	/** 
	 * Method Name:This is method verify UpSell Banner Contents
	 * UpSell Banner Content are present
	 * 
	 * @return
	 */
	public static void mobileWebUpSellContent() throws Exception
	{
				
		//verify UpSell Banner Watchable label is displaying or not
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("watchableHeaderlLabel_XPATH")),
				driver), "UpSell Banner Watchable label not present");
				
		//verify UpSell Banner Google Play button is displaying or not
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("homePageUpSellBarGooglePlayButton_XPATH")),
				driver), "UpSell Banner Google Play button not present");
					
	}
	
	/** 
	 * Method Name:This is method Verify “i” icon functionality in header section
	 * 'i' icon present under header section
	 * 
	 * @return
	 */
	public static void mobileWebHeaderContents() throws Exception
	{
		//verify UpSell Banner close icon is displaying or not
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("homePageIIcon_XPATH")),
		driver), "i-icon in header section not displaying");
	}

	/** 
	 * Method Name:This is method Verify home page description text content
	 * home page description text content
	 * 
	 * @return
	 */
	public static void homePageDecriptionText() throws Exception
	{
		//Verify home description text is present in home page or not
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("homePageDescriptionText_XPATH")),
				driver), "Home description text not present in home page");
	}
	
	/** 
	 * Method Name:This is method to Verify Footer Links
	 * Footer link s i.e Privacy Policy, Terms Of Use, AdChoices
	 * 
	 * @return
	 */
	public static void footerLinkSection() throws Exception
	{
		//Verify the footerLinks in mobile web page
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("footerPrivacyPolicyLink_XPATH")),
						driver), "Privacy Policy link not displaying");
		
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("footerTermsOfServiceLink_XPATH")),
				driver), "Terms of Service link not displaying");
			
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("footerAdChoicesLink_XPATH")),
				driver), "AdChoices link not displaying");
		
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("homePageFooterContactUsLink_XPATH")),
				driver), "Contact Us link not displaying");
		
		
	}
	
	/** 
	 * Method Name:This is method to Verify the functionality of close icon in email sharing pop-up windows
	 * functionality of close icon in email sharing pop-up windows
	 * 
	 * @return
	 */
	public static void spreadTheLoveButton() throws Exception
	{
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("shareSpreadTheLoveButton_XPATH")),
				driver), "SPREAD THE LOVE BUTTON not displaying");
	}
	
	/** 
	 * Method Name:This is method to Verify Email sharing option is present in playlist sharing popup window.
	 * Email sharing option is present in playlist sharing popup window
	 * 
	 * @return
	 */
	public static void emailShareOption() throws Exception
	{
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("socialshareemail_XPATH")),
				driver), "Email sharing option is not present in playlist sharing popup window");
	}
	
	/** 
	 * Method Name:This is method to close Email sharing popup.
	 * close icon displaying in email sharing popup
	 * 
	 * @return
	 */
	public static void closeIconOfEmailPopup() throws Exception
	{
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("emailCloseIcon_XPATH")),
				driver), "Close Icon not displaying in email share popup");
	}
	
	/** 
	 * Method Name:This is method Verify  home page description text content
	 * 
	 * @return
	 */	
	public static void homePageDescriptionTextContent() throws Exception
	{
		Assert.assertEquals(driver.findElement(By.xpath(ObjRepoProp.getProperty("homepageDescriptionFirstLineText_XPATH"))).getText(), TextProp.getProperty("homepage_firstlinedescription"));
		Assert.assertEquals(driver.findElement(By.xpath(ObjRepoProp.getProperty("homepageDescriptionSecondLineText_XPATH"))).getText(), TextProp.getProperty("homepage_secondlinedescription"));
		Assert.assertEquals(driver.findElement(By.xpath(ObjRepoProp.getProperty("homepageDescriptionThridLineText_XPATH"))).getText(), TextProp.getProperty("homepage_thridlinedescription"));
	}
	
	/** 
	 * Method Name:This is method to Verify Social Share Option
	 * @return
	 */
	public static void socialShareOptions() throws Exception
	{
		//Verify the Social Share options in mobile web page
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("footerTwitterButton_XPATH")),
						driver), "Twitter socail share option not present");
		
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("footerInstagramButton_XPATH")),
				driver), "Facebook share option not present");
			
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("footerFacebookButton_XPATH")),
				driver), "Email share option not present");
	}
	
	/** 
	 * Method Name:This is method Verify  twitter page title 
	 * 
	 * @return
	 */	
	public static void twitterpagetitle() throws Exception
	{
		
//		System.out.println("my tiwtter title : " + driver.getTitle());
//		Assert.assertEquals(driver.getTitle(), TextProp.getProperty("twitterPageTitle"));
		
	}
	
	/** 
	 * Method Name:This is method VerifyAppStoreButton
	 * 
	 * @return
	 */	
	public static void AppStoreButton() throws Exception
	{
		
		//App Store button is present at the bottom of home page
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("homePageAppStoreButton_XPATH")),
		driver), "App Store button is not present at the bottom of home page");
		
	}
	
	/** 
	 * Method Name:This is method VerifyGooglePlayButton
	 * 
	 * @return
	 */	
	public static void GooglePlayButton() throws Exception
	{
		
		//Google Play button is present at the bottom of home page
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("homePageGooglePlayButton_XPATH")),
		driver), "Google Play button is not present at the bottom of home page");
		
	}
	
	/** 
	 * Method Name:This is method Verify Prev button in video section
	 * 
	 * @return
	 */	
	public static void PrevPage() throws Exception
	{
		
		//prev page button is present or not in video section
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("videosectionpreviouspagebutton_XPATH")),
		driver), "Previous button in video section not displaying");
		
	}
	
	/** 
	 * Method Name:This is method Verify Next button in video section
	 * 
	 * @return
	 */	
	public static void NextPage() throws Exception
	{
		
		//next page button is present or not in video section
		Assert.assertTrue(CustomFun.isElementPresent(By.xpath(ObjRepoProp.getProperty("videosectionnextpagebutton_XPATH")),
		driver), "Next Page button in video section not displaying");
		
	}
	
	/** 
	 * Method Name:This is method Verify Prev Button in disabled status
	 * 
	 * @return
	 */	
	public static void PrevPagelinkStatus() throws Exception
	{
		
		//Verify the status of prev button status
		Assert.assertTrue(driver.findElement(By.xpath(ObjRepoProp.getProperty("videosectionpreviouspagebutton_XPATH"))).isEnabled(),"Prev button is in Enabled status");
	}
	
	
	/** 
	 * Method Name:This is method Verify Next Button in Enabled status
	 * 
	 * @return
	 */	
	public static void NextPagelinkStatus() throws Exception
	{
		
		//Verify the status of prev button status
		Assert.assertTrue(driver.findElement(By.xpath(ObjRepoProp.getProperty("videosectionnextpagebutton_XPATH"))).isEnabled(),"Next page button is in disabled status");
	}
}
