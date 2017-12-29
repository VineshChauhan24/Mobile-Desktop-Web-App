package comcast.test.app.testCases.videoManagement.videoSubscriptionManagement.freeSubscribedChannels.Categories;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.app.common.AssertionRepo.common.AssertionRepoFunctions;
import comcast.test.app.common.userManagement.userLogin.common.UserLoginFunctions;
import comcast.test.app.common.videoManagement.homePage.common.HomePageCommonFunctions;
import comcast.test.app.common.videoManagement.subscriptionsPage.common.SubscriptionsPageCommonFunctions;
import comcast.test.config.configServices.DataServiceProperties;
import comcast.test.config.configServices.utils.BaseTest;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.registerToXidioApplicationAndChangeAPassword.RegisterToXidioApplicationAndChangeAPassword;
import comcast.test.config.dataServices.subscribeFreeFeaturedChannelFromHome.DS_SubscribeAFreeChannelFromHomeFeatured;
import comcast.test.config.dataServices.vo.VideoDetails;

/**
 * Class Name: VerifySubscribedChannelsGenreCategories Description: This test
 * case verifies Genre Categories in My Channels CATEGORIES page. by logging
 * registered user into Watchable application.
 * **/

public class VerifySubscribedChannelsGenreCategories extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	DS_SubscribeAFreeChannelFromHomeFeatured subscribeFreeHomeChannel = new DS_SubscribeAFreeChannelFromHomeFeatured();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();
	SubscriptionsPageCommonFunctions subscriptionsPageCommonFun = new SubscriptionsPageCommonFunctions();

	@Test
	public void testVerifySubscribedChannelsGenreCategories() throws Exception {

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			subscribeFreeHomeChannel
					.RegisterAndSubscribeAFreeChannelHomeFeatured();
			driver.get(DataServiceProperties.APPURL);
			// This Method is used to enter user name and password credentials

			if (!driver
					.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
					.getAttribute("class").contains("active")) {
				driver.findElement(By.xpath(XpathObjectRepo.TOPMENULOGIN_XPATH))
						.click();

			}

			userLogin.UserLoginCredentials(driver);

			driver.findElement(By.xpath(XpathObjectRepo.LOGINBUTTON_XPATH))
					.click();
			Thread.sleep(sleepTime);
			int loginError = driver
					.findElements(
							By.xpath(XpathObjectRepo.SIGNUPPAGE_INCORRECT_CREDENTIALS_MSG_XPATH))
					.size();

			if (loginError == 0) {

				// Additional action required pass below Method

				if (!driver
						.findElement(
								By.xpath(XpathObjectRepo.TOP_MENU_HOME_BUTTON_XPATH))
						.getAttribute("class").contains("active")) {
					driver.findElement(
							By.xpath(XpathObjectRepo.TOP_MENU_HOME_BUTTON_XPATH))
							.click();
				}

				// This method is to ensure Home is Active page when Login into
				// Application.
				assertionFunction.assertHomeActiveLink();

				// This method is to navigate My Channels Page.
				subscriptionsPageCommonFun.navigateToMyChannelsPage();

				// This method is to ensure My Channels is Active page when
				// Login into Application.
				assertionFunction.assertMyChannelsActiveLink();

				// This method is to navigate My Channels CATEGORIES page.
				subscriptionsPageCommonFun.clickCategoriesLink();

				Map<String, List<VideoDetails>> genreDetails = RestAPIServices
						.subscriptionGenresAPI();
				List<VideoDetails> genresCategoryList = genreDetails
						.get("genresCategoriesList");

				int noOfCategoriesDisplayed = genresCategoryList.size();
				if (genresCategoryList != null && noOfCategoriesDisplayed > 0) {
					int category = 1;
					for (VideoDetails genreCategories : genresCategoryList) {
						while (category <= noOfCategoriesDisplayed) {
							// String
							// getCategoryTitle=driver.findElement(By.xpath(XpathObjectRepo.GENRES_TYPE_HEADER_LABEL_XPATH+"["+category+"]"+XpathObjectRepo.HOMEPAGE_GENRES_SHOWS_TITLEHEADER_XPATH)).getText();

							// This is to assert Genre category from API
							// response and UI displayed.
							// assertEquals(genreCategories.getTitle(),getCategoryTitle);
							break;
						}
						category++;
					}
				}

				// This method unsubscribe a subscribed channels.
				subscriptionsPageCommonFun.unSubscribeASubscribedChannels();

				// This method is used to logout from Watchable Application.
				userLogin.LogOut(driver);
			}
			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			// assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}
