package comcast.test.app.testCases.videoManagement.genres.channels.Sports;

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
 * Class Name: VerifySportsChannels Description: This test case validates
 * whether channels are displayed in Sports Category CHANNELS PAGE in Home page
 * by with/without logging into Watchable application.
 * **/

public class VerifySportsChannels extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifySportsChannels() throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.GenreCategoriesDetail(UILablesRepo.SPORTS);
		List<VideoDetails> genresCategoriesList = videoDetails
				.get("genresCategoryChannelsList");
		List<VideoDetails> genreChannelsList = videoDetails
				.get("genreCategoriesChannelsList");

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			// RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// Opening application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// This method is to ensure Home is Active page when Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			// Navigate to Genre CHANNELS page.
			driver.findElement(
					By.xpath(XpathObjectRepo.HOMEGENRESCHNNLHEADERLABEL_XPATH))
					.click();
			Thread.sleep(sleepTime);

			boolean categoryFound = false;
			int noOfCategoriesDisplayed = genresCategoriesList.size();
			if (genresCategoriesList != null && noOfCategoriesDisplayed > 0
					&& genreChannelsList != null) {
				for (int category = 1; category <= noOfCategoriesDisplayed; category++) {
					if (noOfCategoriesDisplayed <= 8) {
						String getCategoryTitle = driver
								.findElement(
										By.xpath(XpathObjectRepo.HOMEGENRECHANNELSTITLELIST_XPATH
												+ "[" + category + "]"))
								.getText();
						if (getCategoryTitle
								.equalsIgnoreCase(UILablesRepo.SPORTS)) {
							categoryFound = true;
							if (genreChannelsList != null) {
								int loopMaxIndex = 0;
								if (genreChannelsList.size() < 10)
									loopMaxIndex = genreChannelsList.size();
								else
									loopMaxIndex = 10;

								int i = 0;
								for (int index = 0; index < loopMaxIndex; index++) {
									VideoDetails channelsList = genreChannelsList
											.get(index);
									int listIndex = genreChannelsList
											.indexOf(channelsList);
									for (; i <= listIndex; i++) {
										int j = (i % 10) + 1;
										Thread.sleep(sleepTime);
										// assertEquals(channelsList.getTitle(),driver.findElement(By.xpath("//*[@id='genre']/div/div[2]/div["+category+"]/div/section/div/div/ul/li["+j+"]/article/h1/a")).getText());
										// driver.findElement(By.xpath(".//*[@id='genre']/div/div[2]/div["+category+"]/div/section/div/div/ul").xpath("//*[@id='genre']/div/div[2]/div["+category+"]/div/section/div/div/ul/li["+j+"]/article/h1/a")).click();
										driver.findElement(
												By.xpath(XpathObjectRepo.HOMEGENRECHANNELSLIST_XPATH
														+ "["
														+ category
														+ "]"
														+ XpathObjectRepo.LINK_XPATH
														+ "["
														+ j
														+ "]"
														+ XpathObjectRepo.HREF_XPATH))
												.click();

										Thread.sleep(sleepTime);
										// Lekshmi : Changed the object
										// identifier
										// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+channelsList.getTitle()+"[\\s\\S]*$"));
										assertTrue(driver
												.findElement(
														By.xpath(XpathObjectRepo.CHANNELDETAILSPAGETITLE_XPATH))
												.getText()
												.equalsIgnoreCase(
														channelsList.getTitle()));

										driver.navigate().back();
										Thread.sleep(sleepTime);
										Thread.sleep(sleepTime);

										// Navigate to Genre CHANNELS page.
										driver.findElement(
												By.xpath(XpathObjectRepo.HOMEGENRESCHNNLHEADERLABEL_XPATH))
												.click();

										for (int k = 0; k < (i + 1) / 10; k++) {
											String isNextEnable = driver
													.findElement(
															By.xpath(XpathObjectRepo.HOMEGENRECHANNELSPAGINATIONBUTTON_XPATH))
													.getAttribute("class");
											if (!isNextEnable
													.equalsIgnoreCase(UILablesRepo.PAGINATIONNEXTHIDDEN_CLASS)
													&& !isNextEnable
															.equalsIgnoreCase(UILablesRepo.PAGINATIONNEXTDISABLED_CLASS)) {
												driver.findElement(
														By.xpath(XpathObjectRepo.HOMEGENRECHANNELSPAGINATIONBUTTON_XPATH))
														.click();
												Thread.sleep(5000);
											}
										}
									}
								}
							}
						}
						if (categoryFound == true)
							break;
					}
				}
			}
			// Lekshmi : Commented
			/*
			 * if(categoryFound==false)
			 * assertTrue(driver.findElement(By.cssSelector
			 * ("BODY")).getText().matches
			 * ("^[\\s\\S]*"+orProUtil.getProperty("ANIMATION")+"[\\s\\S]*$"));
			 */
			// This method is used to logout from Watchable Application.
			// userLogin.LogOut(driver);

			// This method is to ensure Login page is displayed when user Sign
			// Out from Application.
			// assertionFunction.assertLoginPageDetails();
		} catch (Throwable t) {
			captureScreenshot();
			collector.addError(t);
		}
	}
}