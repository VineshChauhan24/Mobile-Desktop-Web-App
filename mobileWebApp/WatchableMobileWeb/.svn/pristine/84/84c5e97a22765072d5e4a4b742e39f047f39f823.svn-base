package comcast.test.homepage;


import org.testng.Reporter;
import org.testng.annotations.Test;
import comcast.common.asserts.Assertions;
import comcast.config.BaseTest;
import comcast.test.homepage.homePageFunctions.HomeFun;


/**
 * 
 * @author Karthik.Ashoka
 * @description: This test script Verify  home page description text content
 * @Created on 4-May-2016 Last updated on 10-May-2016
 * 
 */

public class VerifyHomePageDescriptionTextContent extends BaseTest {

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
	 * Verify home description text is present in home page.
	 */
	@Test(description = "Step 2: Verify home description text is present in home page.", priority = 2)
	public void Step02_VerifyHomePageDescriptiontext() throws Exception
	{
		//Scrolling down up-to footer of the home page
		HomeFun.scrollToFooterSection();
		
		//Verify home description text is present in home page or not
		Assertions.homePageDecriptionText();

		log.info("Home description text is present in home page below the playlist.");
		Reporter.log("<p>Home description text is present in home page below the playlist.</p>");
	}
	
	/**
	 * Verify the content of the description text present in home page.
	 */
	@Test(description = "Step 3: Verify the content of the description present in home page.", priority = 3)
	public void Step03_VerfiyContentDescriptionText() throws Exception
	{
		// Verify the content of description text in home page
		Assertions.homePageDescriptionTextContent();
				
		log.info("The description text displayed is: Ready for a better way to discover great videos?"
				+"\n Welcome to Watchable. From social issues to Imao comedy, our editors crawl the internet to bring you the best videos.All you have to do is queue up a playlist and kick back."
				+ "\n Download the free app and see for yourself"
				);
		Reporter.log("The description text displayed is: Ready for a better way to discover great videos?"
			+"\n Welcome to Watchable. From social issues to Imao comedy, our editors crawl the internet to bring you the best videos.All you have to do is queue up a playlist and kick back."
				+ "\n Download the free app and see for yourself");
	}
	
}
