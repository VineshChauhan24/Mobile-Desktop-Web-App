package comcast.test.app.testCases.homePage;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.commonFunctions.CommonFun;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;

/**
 * Class Name: VerifyAllGenreCategories ,Description: This test case verifies
 * Categories are displayed in CHANNELS BY GENRE section
 * **/

public class VerifyAllGenreCategories extends BaseTest {

	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyAllGenreCategories() throws Exception {

		try {

			// Test script
			log.info("Script: VerifyAllGenreCategories");
			log.info("********************************");

			// Open Application
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// Verify application is opened successfully.
			AssertionRepoFunctions.assertWatchableTitle();
			log.info("Successfully opened the application");

			// This method is to scroll UI to Genre Channels Section.
			homePageCommonFun.scrollToGenresSection();
			Thread.sleep(sleepTime);
			
			//verify the the More shows button
			

			/*// This method asserts Genre Channels title.
			//assertionFunction.assertGenreChannelsTitle();

			// Verify GENRE CHANNELS row
			assertTrue("Genre channel section is not present in home page",
					CommonFun.isElementPresent(driver, By
							.xpath(XpathObjectRepo.featuredChannelsTitle_XPATH)));
			log.info("Genre channel section is present in home page");

			// Verify Genre category row present in GENRE CHANNELS section

			int categorylCount = driver.findElements(
					By.xpath(XpathObjectRepo.featuredChannelsTitle_XPATH)).size();

			categorylCount = categorylCount / 2;
			if (categorylCount > 0) 
			{
				log.info("Genre channel section contains Genre categories");
				log.info(categorylCount
						+ " Genre categories are present in Genre channel section in home page");
				log.info("The following categories are present in categories");
				log.info("--------------------------------------------------");
				for (int i = 0; i < categorylCount; i++) {
					
					if(driver.findElements(
									By.xpath(XpathObjectRepo.featuredChannelsTitle_XPATH
											+ i
											+ XpathObjectRepo.featuredChannelsTitle_XPATH)).size()>0){
					log.info(i
							+ 1
							+ ". "
							+ driver.findElement(
									By.xpath(XpathObjectRepo.featuredChannelsTitle_XPATH
											+ i
											+ XpathObjectRepo.featuredChannelsTitle_XPATH))
									.getText());
					}

				}
				log.info("");

			} else {
				log.error("Genre channel section not contains Genre categories");
				log.info("");*/
			
		

			
		} 
	catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
