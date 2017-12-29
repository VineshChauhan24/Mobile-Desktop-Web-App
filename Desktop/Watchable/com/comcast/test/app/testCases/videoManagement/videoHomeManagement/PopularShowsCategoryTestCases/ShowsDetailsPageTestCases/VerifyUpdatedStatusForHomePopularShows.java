package comcast.test.app.testCases.videoManagement.videoHomeManagement.PopularShowsCategoryTestCases.ShowsDetailsPageTestCases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.UILablesRepo;
import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifyUpdatedStatusForHomePopularShows Description: This test
 * case validates whether 'Updated' status is displayed in shows page for the
 * show present under Popular Shows section in Home page by logging registered
 * user into Watchable application.
 * **/

public class VerifyUpdatedStatusForHomePopularShows extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyUpdatedStatusForHomePopularShows() throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.nHomePopularShowsAPIs();
		List<VideoDetails> popularShowList = videoDetails.get("popularShows");

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			// RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// Opening application
			driver.get(DataServiceProperties.HOMEAPPURL);

			/*
			 * int loginError = driver .findElements(
			 * By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH
			 * )) .size();
			 * 
			 * if (loginError == 0) {
			 */

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// This method asserts Popular Shows Header Title.
			assertionFunction.assertPopularShowsTitle();

			// This method is to scroll UI to Popular Channels Section.
			homePageCommonFun.scrollToPopularShowsSection();

			int loopMaxIndex = 0;
			if (popularShowList != null) {
				if (popularShowList.size() < 4)
					loopMaxIndex = popularShowList.size();
				else
					loopMaxIndex = 4;

				for (int index = 0; index < loopMaxIndex; index++) {
					VideoDetails ShowList = popularShowList.get(index);

					// This Method verifies Shows present in Popular Shows
					// Section and selects a Show.
					homePageCommonFun.selectPopularShows(ShowList.getTitle());

					// Lekshmi : Changed Object identifier
					// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+ShowList.getTitle()+"[\\s\\S]*$"));

					// Manoj : Modified Object identifier and moved to
					// XpathObjectRepo file
					// assertTrue(driver.findElement(By.xpath("//div[@id='content-wrap']/descendant::div[@class='content-main']/descendant::h1[2]")).getText().matches("^[\\s\\S]*"+ShowList.getTitle()+"[\\s\\S]*$"));

					assertTrue(driver
							.findElement(
									By.xpath(XpathObjectRepo.SHOWDETAILSHOWTITLE_XPATH))
							.getText().matches(ShowList.getTitle()));

					// String
					// publishedDate=driver.findElement(By.xpath("//div[@id='content-wrap']/descendant::div[@class='content-metadata']/descendant::li[2]")).getText();

					String publishedDate = driver
							.findElement(
									By.xpath(XpathObjectRepo.SHOWDETAILVIDEOSUPDATED_XPATH))
							.getText();
					assertEquals(ShowList.getLastPublished(), publishedDate);

					driver.navigate().back();
					Thread.sleep(sleepTime);
				}
			} else {
				boolean isPresent;
				// Lekshmi : Changed Object identifier
				// isPresent=driver.findElement(By.xpath("//div[@id='popular_channels']/descendant::section[@class='view-row']")).findElements(By.xpath("//div[@id='popular_channels']/descendant::li[contains(@class, 'cell-channel')][1]/descendant::h1/a")).size()>0;

				// Manoj : Changed Object identifier And moved to
				// XpathObjectRepo file
				// isPresent=driver.findElement(By.xpath("//div[@id='popular_channels']/descendant::section[@class='view-row']")).findElements(By.xpath("//div[@id='popular_channels']/descendant::li[contains(@class, 'cell-channel')][1]/descendant::h1/a")).size()>0;

				isPresent = driver
						.findElement(
								By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSROW_XPATH))
						.findElements(
								By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSFIRSTTITLELABEL_XPATH))
						.size() > 0;

				if (isPresent == true) {

					// Manoj : Changed Object identifier And moved to
					// XpathObjectRepo file

					// String
					// channelName=driver.findElement(By.xpath("//div[@id='popular_channels']/descendant::section[@class='view-row']")).findElement(By.xpath("//div[@id='popular_channels']/descendant::li[contains(@class, 'cell-channel')][1]/descendant::h1/a")).getText();

					String channelName = driver
							.findElement(
									By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSROW_XPATH))
							.findElement(
									By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSFIRSTTITLELABEL_XPATH))
							.getText();
					// assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+channelName+"[\\s\\S]*$"));

					// Manoj : Modified the assertion
					assertTrue(driver
							.findElement(
									By.xpath(XpathObjectRepo.HOMEPOPULARCHANNELSTITLELABEL_XPATH))
							.getText().equalsIgnoreCase(channelName));
				}
			}

			// This method is used to logout from Watchable Application.
			// userLogin.LogOut(driver);
			// }
			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			// assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
