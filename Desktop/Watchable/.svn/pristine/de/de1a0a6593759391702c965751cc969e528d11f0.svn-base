package comcast.test.app.common.homePageTestCases.BeforeLoginFeatureTestCases;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import org.openqa.selenium.By;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyDefaultHomePageDetailsWithoutLogin Description: This test
 * case is to verify home page details without logging into Gazeebo application.
 * **/

public class VerifyDefaultHomePageDetailsWithoutLogin extends BaseTest {

	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyDefaultHomePageDetailsWithoutLogin() throws Exception {

		// Manoj: Refactored complete code

		List<VideoDetails> videoDetails = RestAPIServices.nhomePageAPIs();

		// driver.get(proUtil.getProperty("HOMEAPPURL"));
		driver.get(DataServiceProperties.APPURL);
		try {

			// Additional action added to make 'HOME' link active. Otherwise
			// below assertion will fail

			driver.findElement(
					By.xpath(XpathObjectRepo.TOP_MENU_HOME_BUTTON_XPATH))
					.click();

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method asserts watchable Logo.
			assertionFunction.assertWatchableLogo();

			/*
			 * boolean isBannerPresent; HOMEAPPURL
			 * isBannerPresent=driver.findElements(By.xpath(
			 * ".//*[@id='banner']/div/div[1]/div/section/div[1]/div/ul[1]/li/article/div/div/a/img"
			 * )).size()>0; if(isBannerPresent==true) { String
			 * welcomeToGazeeboLinkState=driver.findElement(By.xpath(
			 * ".//*[@id='banner']/div/div[2]/header/nav/ul/li[1]/a"
			 * )).getText(); String
			 * gettingStartedLinkState=driver.findElement(By
			 * .xpath(".//*[@id='banner']/div/div[2]/header/nav/ul/li[2]/a"
			 * )).getText(); String
			 * GazeeboOniPadAndTVLinkState=driver.findElement
			 * (By.xpath(".//*[@id='banner']/div/div[2]/header/nav/ul/li[3]/a"
			 * )).getText();
			 * 
			 * assertEquals("Welcome to Watchable!",welcomeToGazeeboLinkState);
			 * 
			 * assertEquals("Getting Started",gettingStartedLinkState);
			 * 
			 * assertEquals("Gazeebo on iPad and TV",GazeeboOniPadAndTVLinkState)
			 * ; }
			 */

			// Below functionality not present now
			/*
			 * String welcomeToWatchableLink=driver.findElement(By.xpath(
			 * ".//*[@id='topmiddle_menu']/div")).getText();
			 * assertEquals("Welcome to Watchable!",welcomeToWatchableLink);
			 */

			// Below functionality present only for login user
			// assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Up Next[\\s\\S]*$"));

			// This method asserts featured title.
			assertionFunction.assertFeaturedTitle();
			// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videoDetails.get(0).getTitle()+"[\\s\\S]*$"));
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.FEATUREDICONSFIRSTTITLE_XPATH))
					.getText().matches(videoDetails.get(0).getTitle()));

			// This method asserts Popular Shows Header Title.
			assertionFunction.assertPopularShowsTitle();
			// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videoDetails.get(1).getTitle()+"[\\s\\S]*$"));
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEPOPULARSHOWSFIRSTTITLELABEL_XPATH))
					.getText().matches(videoDetails.get(1).getTitle()));

			// This method asserts Popular Channels title.
			assertionFunction.assertPopularChannelsTitle();
			// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+videoDetails.get(2).getTitle()+"[\\s\\S]*$"));
			assertTrue(driver
					.findElement(
							By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSFIRSTTITLELABEL_XPATH))
					.getText().matches(videoDetails.get(2).getTitle()));

			// This method asserts Footer Logo and It's Text.
			assertionFunction.assertFooterLogo();

			// This method asserts Footer Copy Right Links.
			assertionFunction.assertFooterCopyRight();

			// assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Welcome to Watchable![\\s\\S]*$"));
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
