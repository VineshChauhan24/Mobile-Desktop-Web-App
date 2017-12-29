package comcast.test.app.testCases.videoManagement.genres.homePage;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

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
 * Class Name: VerifyAllGenreChannelsCategories Description: This test case
 * validates whether all Genre Channels categories are displayed on UI with same
 * order as in Footer links in Home Genre CHANNELS page by with/without logging
 * into Watchable application.
 * **/

public class VerifyAllGenreChannelsCategories extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyShowsUnderAnimationAndGamesCategory()
			throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.GenresAPI();
		List<VideoDetails> genresCategoryList = videoDetails
				.get("genresCategoryChannelsList");

		try {
			/*
			 * This Method is to register new user using Gazeebo application and
			 * to change a password.
			 */
			// RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// OPening application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// This method is to ensure Home is Active page before Login into
			// Application.
			// assertionFunction.assertHomeActiveLink();

			// Navigate to Genre CHANNELS page.
			driver.findElement(
					By.xpath(XpathObjectRepo.HOMEGENRESCHNNLHEADERLABEL_XPATH))
					.click();
			Thread.sleep(sleepTime);

			int noOfCategoriesDisplayed = genresCategoryList.size();
			if (genresCategoryList != null && noOfCategoriesDisplayed > 0) {
				int category = 1;
				for (VideoDetails genreCategories : genresCategoryList) {
					while (category <= noOfCategoriesDisplayed) {

						if (noOfCategoriesDisplayed <= 8) {
							String getCategoryTitle = driver
									.findElement(
											By.xpath(XpathObjectRepo.HOMEPAGE_GENRES_FULLCONTAINER_XPATH
													+ category
													+ "]"
													+ XpathObjectRepo.HOMEPAGE_GENRES_SHOWS_TITLEHEADER_XPATH))
									.getText();
							// This is to assert Genre category from API
							// response and UI displayed.
							assertEquals(genreCategories.getTitle()
									.toUpperCase(), getCategoryTitle);
							break;
						}
						category++;
					}
				}
			}

			// This method is used to logout from Watchable Application.
			// userLogin.LogOut(driver);

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
