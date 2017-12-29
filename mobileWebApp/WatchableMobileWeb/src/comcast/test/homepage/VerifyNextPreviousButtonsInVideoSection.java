package comcast.test.homepage;
import org.testng.Reporter;
import org.testng.annotations.Test;
import comcast.common.asserts.Assertions;
import comcast.config.BaseTest;
import comcast.test.homepage.homePageFunctions.HomeFun;

/**
 * 
 * @author karthik.ashoka
 * @description: This test script verifies the functionality of Next and Previous button in play list video section
 * @Created on 18-May-2016 Last updated on 18-May-2016
 * 
 */

public class VerifyNextPreviousButtonsInVideoSection extends BaseTest {

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
	 * Verify “PREVIOUS” button is present in play list video section.
	 */
	@Test(description = "Step 2: Verify “PREVIOUS” button is present in play list video section", priority = 2)
	public void Step02_VerifyPrevPageButton() throws Exception
	{
		//Verify the prev page icon displaying or not
		Assertions.PrevPage();
		log.info("“PREVIOUS” button is present in play list video section.");
		Reporter.log("<p>“PREVIOUS” button is present in play list video section.</p>");
		
	}
	
	/**
	 * “NEXT” button is present in play list video section.
	 */
	@Test(description = "Step 3: “NEXT” button is present in play list video section.", priority = 3)
	public void Step03_VerifyNextPageButton() throws Exception
	{
		//Verify the prev page icon displaying or not
		Assertions.NextPage();
		log.info("Next Page button is present in play list video section.");
		Reporter.log("<p>Next Page button is present in play list video section.</p>");
		
	}
	
	/**
	 * Verify “PREVIOUS” button is disabled in first page.
	 */
	@Test(description = "Step 4: Verify “PREVIOUS” button is disabled in first page.", priority = 4)
	public void Step04_VerifyPrevPageButtonStatus() throws Exception
	{
		//Verify the prev page icon displaying or not
		Assertions.PrevPagelinkStatus();
		log.info("“PREVIOUS” button is disabled in first page.");
		Reporter.log("<p>“PREVIOUS” button is disabled in first page..</p>");
		
	}
	
	/**
	 * Verify “NEXT” button is enabled in first page.
	 */
	@Test(description = "Step 5: Verify “NEXT” button is enabled in first page..", priority = 5)
	public void Step05_VerifyNextPageButtonStatus() throws Exception
	{
		//Verify the Next page icon displaying or not
		Assertions.NextPagelinkStatus();
		log.info("“NEXT” button is enabled in first page.");
		Reporter.log("<p>“NEXT” button is enabled in first page.</p>");
		
	}
	
	
	/**
	 * Verify application navigate to Next page by click on “NEXT” button.
	 */
	@Test(description = "Step 6: Verify application navigate to Next page by click on “NEXT” button.", priority = 6)
	public void Step06_clickNextVideoButton() throws Exception
	{
		//Verify application navigate to next video page
		HomeFun.clickOnNextPageVideoButton();
		
		log.info("User is taken to second/next page");
		Reporter.log("<p>User is taken to second/next page.</p>");
		
	}
	
	/**
	 *Verify “NEXT” button is disabled in second page (Or in last Page)..
	 */
	@Test(description = "Step 7: Verify “NEXT” button is disabled in second page (Or in last Page).", priority = 7)
	public void Step07_VerifyLastNextPageButtonStatus() throws Exception
	{
		//Verify the last next page button status
		Assertions.NextPagelinkStatus();
		
		log.info("“NEXT” button is disabled in second/last page");
		Reporter.log("<p>“NEXT” button is disabled in second/last page.</p>");
		
	}
	
	/**
	 *Verify “PREVIOUS” button is enabled in second page/last page.
	 */
	@Test(description = "Step 8: Verify “PREVIOUS” button is enabled in second page/last page.", priority = 8)
	public void Step08_VerifyLastPrevPageButtonStatus() throws Exception
	{
		//Verify last page prev button status
		Assertions.PrevPagelinkStatus();
		
		log.info("“PREVIOUS” button is enabled in second page (Or in last page).");
		Reporter.log("<p>“PREVIOUS” button is enabled in second page (Or in last page).</p>");
		
	}
	
	/**
	 * Verify application navigate to prev page by click on “Prev” button.
	 */
	@Test(description = "Step 9: Verify application navigate to prev page by click on “Prev” button.", priority = 9)
	public void Step09_clickPrevVideoButton() throws Exception
	{
		
		//Verify application navigate to next video page
		HomeFun.clickOnPrevPageVideoButton();
		
		log.info("User is taken back to first/prev page");
		Reporter.log("<p>User is taken back to first/prev page.</p>");
		
	}
}