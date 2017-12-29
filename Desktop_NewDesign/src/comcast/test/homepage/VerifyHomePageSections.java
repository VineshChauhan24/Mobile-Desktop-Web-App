package comcast.test.homepage;

import org.testng.Reporter;
import org.testng.annotations.Test;
import comcast.config.BaseTest;
import comcast.pages.HomePage;

/**
 * 
 * @author Manoj.Paragen 
 * Description: This test script verify the main contents of home page sections
 * Created on 06-September-2016 
 * Last updated on 16-September-2016
 * 
 */

public class VerifyHomePageSections extends BaseTest {
	
	static HomePage homePage;
	
	
	
	/**
	 * Open the browser, Enter the Watchable URL
	 */
	@Test(description = "Step 1: Open the browser, Enter the Watchable URL", priority = 1)
	public void Step01_NavigeteToURL() throws Exception {
				
		// Home page object instance creation
		//homePage = new HomePage(driver);
		
		// Navigates to Watchable application URL
		homePage = HomePage.navigateToWatchableHomePage(driver, baseUrl);
		

		log.info("Successfully navigated to Watchable Home page  \n");
		Reporter.log("<p>Successfully navigated to Watchable Home page");

	}

	
}
