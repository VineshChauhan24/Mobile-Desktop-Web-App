package comcast.test.app.testCases.videoManagement.genres.categories.Entertainment;

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
 * Class Name: VerifyShowsInFilmCategory Description: This test case validates
 * whether shows are displayed in Film Category in Home page by with/without
 * logging into Watchable application.
 * **/

public class VerifyShowsInEntertainmentCategory extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyShowsInEntertainmentCategory() throws Exception {

		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.GenreCategoriesDetail(UILablesRepo.ENTERTAINMENT);
		List<VideoDetails> genresCategoryList = videoDetails
				.get("genresCategoriesList");
		List<VideoDetails> genreCategoriesShowsList = videoDetails
				.get("GenreCategoriesAllShowList");

		try {
			/*
			 * This Method is to register new user using Gazeebo application and
			 * to change a password.
			 */

			// Login is not required for this test script
			// RegUserAndChangePass.RegisterToComcastAppAndChangePassword(driver);

			// Opening application
			driver.get(DataServiceProperties.HOMEAPPURL);

			// This method is to ensure Home is Active page before Login into
			// Application.
			assertionFunction.assertHomeActiveLink();

			boolean categoryFound = false;
			int noOfCategoriesDisplayed = genresCategoryList.size();
			if (genresCategoryList != null && noOfCategoriesDisplayed > 0
					&& genreCategoriesShowsList != null) {
				for (VideoDetails genreCategories : genresCategoryList) {
					if (genreCategories.getTitle().equalsIgnoreCase(
							UILablesRepo.ENTERTAINMENT)) {
						for (int category = 1; category <= noOfCategoriesDisplayed; category++) {

							String getCategoryTitle = driver
									.findElement(
											By.xpath(XpathObjectRepo.HOMEPAGE_GENRES_SHOWS_TITLEHEADERLIST_XPATH
													+ category
													+ "]"
													+ XpathObjectRepo.HOMEPAGE_GENRES_SHOWS_TITLEHEADER_XPATH))
									.getText();
							if (getCategoryTitle
									.equalsIgnoreCase(UILablesRepo.ENTERTAINMENT)) {
								/*
								 * driver.findElement(By.xpath(
								 * ".//*[@id='genre']/div/div[2]/div["
								 * +category+"]/div/section/header/h1/a"
								 * )).click(); Thread.sleep(sleepTime);
								 * driver.navigate().back();
								 * Thread.sleep(sleepTime);
								 */

								categoryFound = true;
								if (genreCategoriesShowsList != null) {
									int loopMaxIndex = 0;
									if (genreCategoriesShowsList.size() < 4)
										loopMaxIndex = genreCategoriesShowsList
												.size();
									else
										loopMaxIndex = 4;

									int i = 0;
									for (int index = 0; index < loopMaxIndex; index++) {
										VideoDetails showList = genreCategoriesShowsList
												.get(index);
										int listIndex = genreCategoriesShowsList
												.indexOf(showList);
										for (; i <= listIndex; i++) {
											int j = (i % 5) + 1;
											// assertEquals(showList.getTitle(),driver.findElement(By.xpath("//*[@id='genre']/div/div[2]/div["+category+"]/div/section/div/div/ul/li["+j+"]/article/h1/a")).getText());
											// driver.findElement(By.xpath(".//*[@id='genre']/div/div[2]/div["+category+"]/div/section").xpath("//*[@id='genre']/div/div[2]/div["+category+"]/div/section/div/div/ul/li["+j+"]/article/h1/a")).click();
											// Thread.sleep(sleepTime);
											int ele = driver
													.findElements(
															By.xpath(
																	XpathObjectRepo.HOMEPAGE_GENRES_SHOWS_TITLEHEADERLIST_XPATH
																			+ category
																			+ "]"
																			+ XpathObjectRepo.HOMEPAGE_GENRES_SHOWSLIST_XPATH)
																	.partialLinkText(
																			showList.getTitle()))
													.size();

											if (ele == 1) {
												Thread.sleep(sleepTime);
												driver.findElement(
														By.xpath(
																XpathObjectRepo.HOMEPAGE_GENRES_SHOWS_TITLEHEADERLIST_XPATH
																		+ category
																		+ "]"
																		+ XpathObjectRepo.HOMEPAGE_GENRES_SHOWSLIST_XPATH)
																.partialLinkText(
																		showList.getTitle()))
														.click();

												assertTrue(driver
														.findElement(
																By.xpath(XpathObjectRepo.SHOWDETAILSHOWTITLE_XPATH))
														.getText()
														.equalsIgnoreCase(
																showList.getTitle()));

												driver.navigate().back();
											}
											Thread.sleep(sleepTime);
											homePageCommonFun
													.scrollToGenresSection();

											for (int k = 0; k < (i + 1) / 5; k++) {
												String isNextEnable = driver
														.findElement(
																By.xpath(XpathObjectRepo.HOMEPAGE_GENRES_SHOWS_TITLEHEADERLIST_XPATH
																		+ category
																		+ "]"
																		+ XpathObjectRepo.HOMEPAGE_GENRES_SHOWLIST_NEXTBTN_XPATH))
														.getText();
												if (!isNextEnable
														.equalsIgnoreCase(UILablesRepo.PAGINATIONNEXTHIDDEN_CLASS)
														&& !isNextEnable
																.equalsIgnoreCase(UILablesRepo.PAGINATIONNEXTDISABLED_CLASS)) {
													driver.findElement(
															By.xpath((XpathObjectRepo.HOMEPAGE_GENRES_SHOWS_TITLEHEADERLIST_XPATH
																	+ category
																	+ "]" + XpathObjectRepo.HOMEPAGE_GENRES_SHOWLIST_NEXTBTN_XPATH)))
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
						}/*
						 * if(categoryFound==false)
						 * assertTrue(driver.findElement
						 * (By.cssSelector("BODY")).
						 * getText().matches("^[\\s\\S]*"
						 * +orProUtil.getProperty("ANIMATIONANDGAMES"
						 * )+"[\\s\\S]*$"));
						 */
					}
					if (categoryFound == true)
						break;
				}
			}
			Thread.sleep(sleepTime);
			// This method is used to logout from Gazeebo Application.
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
