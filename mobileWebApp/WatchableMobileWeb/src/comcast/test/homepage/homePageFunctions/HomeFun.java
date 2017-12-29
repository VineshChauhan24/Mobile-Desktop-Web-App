package comcast.test.homepage.homePageFunctions;

import static comcast.util.PropertyFileReader.ObjRepoProp;
import static comcast.util.PropertyFileReader.TextProp;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import comcast.config.BaseTest;
import comcast.custom.CustomFun;
import comcast.uiFunctions.GUIFunctions;

public class HomeFun extends BaseTest {

	public Logger log = Logger.getLogger(this.getClass().getName());

	/**
	 * This function navigates to Watchable mobile application URL
	 * 
	 * 
	 * @param baseUrl
	 *            : Application URL
	 * @return HomePage object
	 * @throws Exception
	 */

	public static void navigateToWatchable_URL() throws Exception {

		System.out.println("Application Url = " + TextProp.getProperty("url"));

		// navigate to Watchable mobile web URL
		driver.get(TextProp.getProperty("url"));

		Thread.sleep(5000);
	}

	/**
	 * Method Name:This is method verify UpSell banner close UpSell banner close
	 * after clicking on close icon
	 * 
	 * @return
	 */
	public static void clickOnUpSellCloseButton() throws Exception {
		// Click on UpSellClose close icon
		GUIFunctions.clickElement(driver, By.xpath(ObjRepoProp
				.getProperty("homePageUpSellBarCloseButton_XPATH")),
				"UpSell Close button");
	}

	/**
	 * Method Name:This is method verify UpSell banner display after refresh
	 * page UpSell banner display after refresh page
	 * 
	 * @return
	 */
	public static void refreshHomePage() throws Exception {
		// To Refresh the page
		CustomFun.refreshPage(driver);
	}

	/**
	 * Method Name:This is method verify click on i-icon button Able to click on
	 * i-icon button
	 * 
	 * @return
	 */

	public static void clickoniicon() throws Exception {
		// Click on i-icon
		GUIFunctions.clickElement(driver,
				By.xpath(ObjRepoProp.getProperty("homePageIIcon_XPATH")),
				"i-icon");
		Thread.sleep(5000);
	}

	/**
	 * Method Name:This is method click on next page button from play list
	 * section
	 * 
	 * @return
	 */

	public static void clickOnNextButton() throws Exception {

		for (int i = 1; i <= 10; i++) {

			if (driver.findElement(
					By.xpath(ObjRepoProp
							.getProperty("nextPlaylistButton_XPATH")))
					.isEnabled()) {
				GUIFunctions.clickElement(driver, By.xpath(ObjRepoProp
						.getProperty("nextPlaylistButton_XPATH")),
						"Next button");
			}
		}

	}

	/**
	 * Method Name:This is method scroll down to footer of home page
	 * 
	 * @return
	 */
	public static void scrollToFooterSection() throws Exception {
		// Scrolling down to footer of the page
		for (int second = 0; second < 30; second++) {
			((JavascriptExecutor) driver).executeScript(
					"window.scrollBy(0,200)", "");
			Thread.sleep(1000);
		}

	}

	/**
	 * Method Name:This is method functionality of clicking SPREAD THE LOVE
	 * Button Social sharing option should display
	 * 
	 * @return
	 */
	public static void clickOnTheSpreadLoveButton() throws Exception

	{

		// Move to SPREAD THE LOVE BUtton
		GUIFunctions.mouseOverElement(driver,
				driver.findElement(By.xpath(ObjRepoProp
						.getProperty("homePageDescriptionText_XPATH"))));

		// To verify SPREAD THE LOVE BUtton can be clickable
		if (driver.findElement(
				By.xpath(ObjRepoProp
						.getProperty("shareSpreadTheLoveButton_XPATH")))
				.isEnabled()) {
			// Click on SPREAD THE LOVE Button
			GUIFunctions.clickElement(driver, By.xpath(ObjRepoProp
					.getProperty("shareSpreadTheLoveButton_XPATH")),
					"SPREAD THE LOVE");
			Thread.sleep(2000);
		}

	}

	/**
	 * Method Name:This is method click out side SPREAD THE LOVE button to close
	 * Social sharing popup window
	 * 
	 * @return
	 */
	public static void clickOutSideSpreadLoveButton() throws Exception

	{

		// Click out side SPREAD THE LOVE button
		GUIFunctions.clickElement(driver, By.xpath(ObjRepoProp
				.getProperty("homePageDescriptionText_XPATH")),
				"SPREAD THE LOVE");
		Thread.sleep(2000);

	}

	/**
	 * Method Name:This is method for functionality of Click on email option.
	 * Email sharing pop-up window opening
	 * 
	 * @return
	 */
	public static void clickOnEmailShareOption() throws Exception {
		// To verify email option can clickable
		if (driver.findElement(
				By.xpath(ObjRepoProp.getProperty("socialshareemail_XPATH")))
				.isEnabled()) {
			// Click on SPREAD THE LOVE Button
			GUIFunctions
					.clickElement(driver, By.xpath(ObjRepoProp
							.getProperty("socialshareemail_XPATH")),
							"Email Share Option");
			Thread.sleep(2000);
		}
	}
	
	/**
	 * Method Name:This is method for functionality of Click on facebook option.
	 * facebook sharing pop-up window opening
	 * 
	 * @return
	 */
	public static void clickOnfacebookShareOption() throws Exception {
		// To verify email option can clickable
		if (driver.findElement(
				By.xpath(ObjRepoProp.getProperty("socailsharefacebook_XPATH")))
				.isEnabled()) {
			// Click on SPREAD THE LOVE Button
			GUIFunctions
					.clickElement(driver, By.xpath(ObjRepoProp
							.getProperty("socailsharefacebook_XPATH")),
							"Facebook Option");
			Thread.sleep(5000);
		}
	}

	
	
	
	
	
	
	
	

	/**
	 * Method Name:This is method functionality of close icon from email sharing
	 * pop-up window Email sharing pop-up window closed
	 * 
	 * @return
	 */
	public static void clickOnCloseIconFromEmailPopUp() throws Exception {
		// To verify email option popup can be clsoed
		if (driver.findElement(
				By.xpath(ObjRepoProp.getProperty("emailCloseIcon_XPATH")))
				.isEnabled()) {
			// Click on SPREAD THE LOVE Button
			GUIFunctions.clickElement(driver,
					By.xpath(ObjRepoProp.getProperty("emailCloseIcon_XPATH")),
					"Close Icon of Email share Popup");
			Thread.sleep(2000);
		}
	}


	/**
	 * This method fill the playlist email sharing form
	 * 
	 * @return
	 */
	public static void enterdetailsInEmailSharingForm(String fromEmail,
			String toEmail) throws Exception

	{

		// Enter From email address
		GUIFunctions.typeTxtboxValue(driver, By.id(ObjRepoProp
				.getProperty("emailSharePopUpFromEmailText_ID")), fromEmail);

		// Enter To email address
		GUIFunctions
				.typeTxtboxValue(driver, By.id(ObjRepoProp
						.getProperty("emailSharePopUpToEmailText_ID")), toEmail);
	}

	/**
	 * Method Name:This is method click on send button From email sharing window
	 * 
	 * @return
	 */
	public static void clickOnSendButton() throws Exception

	{
		//Click on any where to close the keyboard to make send buttonvisible
		
		GUIFunctions
		.clickElement(driver, By.xpath(ObjRepoProp
				.getProperty("emailSharePopUpTitle_XPATH")),
				"Title text");
		

		Thread.sleep(2000);
		// Click on Send button
		GUIFunctions
				.clickElement(driver, By.xpath(ObjRepoProp
						.getProperty("emailSharePopUpSendButton_XPATH")),
						"Send Button");
		Thread.sleep(5000);

	}
	
	
	/**
	 * Method Name:This is method Clear default message displaying in message field
	 * @return
	 */
	public static void clearMessage() throws Exception

	{
		//Clear default message displaying in message field
		driver.findElement(
				By.id(ObjRepoProp
						.getProperty("emailSharePopUpMessageText_ID"))).clear();
		
		Thread.sleep(1000);

	}


	
	/**
	 * Method Name:This is method click on twitter icon
	 * twitter follow page should open
	 * 
	 * @return
	 */
	public static void clickOnTwitterIcon() throws Exception

	{
		//Click on twitter icon
		GUIFunctions.clickElement(driver, By.xpath(ObjRepoProp
						.getProperty("footerTwitterButton_XPATH")),
						"Twitter Icon");
		Thread.sleep(10000);
	}
	
	/**
	 * Method Name:This is method for back to page purpose
	 * navigate to previous page
	 * 
	 * @return
	 */
	public static void backToMainApplication() throws Exception
	{
		GUIFunctions.navigateBack(driver);
		Thread.sleep(10000);
	}
	
	
	
	/**
	 * Method Name:This is method click on instagram icon
	 * instagram follow page should open
	 * 
	 * @return
	 */
	public static void clickOnInstagramIcon() throws Exception

	{

		//Click out side SPREAD THE LOVE button
		GUIFunctions.clickElement(driver, By.xpath(ObjRepoProp
				.getProperty("footerInstagramButton_XPATH")),
				"Instagram Icon");
		Thread.sleep(10000);
	}
	
	/**
	 * Method Name:This is method click on Facebook icon
	 * facebook follow page should open
	 * 
	 * @return
	 */
	public static void clickOnFacebookIcon() throws Exception

	{

		//Click out side SPREAD THE LOVE button
		GUIFunctions.clickElement(driver, By.xpath(ObjRepoProp
				.getProperty("footerFacebookButton_XPATH")),
				"Facebook Icon");
		Thread.sleep(10000);
	}


	/**
	 * Method Name:This is method verify AppStore is clickable
	 * navigate to App Store Page
	 * 
	 * @return
	 */
	public static void clickOnUpAppStoreButton() throws Exception {
		// Click on App Store Button

		GUIFunctions.clickElement(driver, By.xpath(ObjRepoProp
				.getProperty("homePageAppStoreButton_XPATH")),
				"App Store button");
		Thread.sleep(10000);
		//GUIFunctions.navigateBack(driver);
	   // GUIFunctions.navigateForward(driver);
		
	}
	
	
	/**
	 * Method Name:This is method verify Google Play is clickable
	 * navigate to Google Play Page
	 * 
	 * @return
	 */
	public static void clickOnGooglePlayButton() throws Exception {
		
		// Click on Google Play button
		GUIFunctions.clickElement(driver, By.xpath(ObjRepoProp
				.getProperty("homePageGooglePlayButton_XPATH")),
				"Google Play button");
		Thread.sleep(10000);
		GUIFunctions.navigateBack(driver);
	}

	
	/**
	 * Method Name:This is method click on Next page button
	 * Next page video should display
	 * 
	 * @return
	 */
	public static void clickOnNexpageButton() throws Exception

	{

		//Click out side SPREAD THE LOVE button
		GUIFunctions.clickElement(driver, By.xpath(ObjRepoProp
				.getProperty("videosectionnextpagebutton_XPATH")),
				"Next page button");
		Thread.sleep(5000);
	}
	
	/**
	 * Method Name:This is method click on next page button from video section
	 * section
	 * 
	 * @return
	 */

	public static void clickOnNextPageVideoButton() throws Exception {
		
		String nextPageVal=driver.findElement(By.xpath(ObjRepoProp
						.getProperty("videocountpage_XPATH"))).getText();
		String nextPageResult[]=nextPageVal.split("/");
		String returnNextPageVal= nextPageResult[nextPageResult.length-1];
		int j=Integer.valueOf(returnNextPageVal.trim());
		for (int i = 1; i < j; i++) {

			// To verify SPREAD THE LOVE BUtton can be clickable
			if (driver.findElement(
					By.xpath(ObjRepoProp
						.getProperty("videosectionnextpagebutton_XPATH")))
					.isEnabled()) {
				GUIFunctions.clickElement(driver, By.xpath(ObjRepoProp
						.getProperty("videosectionnextpagebutton_XPATH")),
						"Next button");
				Thread.sleep(5000);
			}
		}
	}
	
	/**
	 * Method Name:This is method click on last prev page button from video section
	 * section
	 * 
	 * @return
	 */

	public static void clickOnPrevPageVideoButton() throws Exception {

	String prevPageVal=driver.findElement(By.xpath(ObjRepoProp
				.getProperty("videocountpage_XPATH"))).getText();
	String prevPageResult[]=prevPageVal.split("/");
	String returnPrePageVal= prevPageResult[prevPageResult.length-1];
	int j=Integer.valueOf(returnPrePageVal.trim());
	for (int i = 1; i < j; i++) {

		// To verify SPREAD THE LOVE BUtton can be clickable
		if (driver.findElement(
				By.xpath(ObjRepoProp
					.getProperty("videosectionpreviouspagebutton_XPATH")))
				.isEnabled()) {
			GUIFunctions.clickElement(driver, By.xpath(ObjRepoProp
					.getProperty("videosectionpreviouspagebutton_XPATH")),
					"Prev button");
			Thread.sleep(5000);
			}
		
		}
		
	}		
	
	/**
	 * Method Name:This is method click on playlist twitter icon
	 * twitter follow page should open
	 * 
	 * @return
	 */
	public static void clickOnPlaylistSectionTwitterIcon() throws Exception

	{
		//Click on twitter icon
		GUIFunctions.clickElement(driver, By.xpath(ObjRepoProp
						.getProperty("socialPopUpTwitterButton_XPATH")),
						"Twitter Icon");
		Thread.sleep(10000);
	}

	
	
	/**
	 * Method Name:This method click on contact us link
	 * @return
	 */

	public static void clickOnContactUsLink() throws Exception {
		
		// Click on contact us link
		GUIFunctions.clickElement(driver,
				By.xpath(ObjRepoProp.getProperty("homePageFooterContactUsLink_XPATH")),
				"Contact Us Link");
		Thread.sleep(5000);
	}
	
	
	/**
	 * Method Name:This method click on Tell me more
	 * @return
	 */

	public static void clickOnTellMeMoreButton() throws Exception {
		
				
		// Click on Tell me more button
		GUIFunctions.clickElement(driver,
				By.xpath(ObjRepoProp.getProperty("tellMeMoreButton_XPATH")),
				"Tell Me More button");
		Thread.sleep(5000);
	}
	
	
	/**
	 * Method Name:This method click on close button from play list detail popup
	 * @return
	 */

	public static void clickOnPlaylistDetailCloseButton() throws Exception {
		
				
		// Click on close button
		GUIFunctions.clickElement(driver,
				By.xpath(ObjRepoProp.getProperty("playlistDetailPopupCloseButton_XPATH")),
				"Close button");
		Thread.sleep(5000);
	}
	
	

}
