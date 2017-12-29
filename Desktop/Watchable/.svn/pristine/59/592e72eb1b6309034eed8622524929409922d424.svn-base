package comcast.test.app.testCases.videoManagement.footerVideoCategories.Animation;

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
 * Class Name: VerifyAnimationAndGamesShowsFromFooter Description: This test
 * case validates whether shows are displayed in Animations and Games Category
 * in Home page by clicking on Animation And Games category in footer link
 * with/without logging into Watchable application.
 * **/

public class VerifyAnimationShowsFromFooter extends BaseTest {

	RegisterToXidioApplicationAndChangeAPassword RegUserAndChangePass = new RegisterToXidioApplicationAndChangeAPassword();
	HomePageCommonFunctions homePageCommonFun = new HomePageCommonFunctions();
	UserLoginFunctions userLogin = new UserLoginFunctions();
	AssertionRepoFunctions assertionFunction = new AssertionRepoFunctions();

	@Test
	public void testVerifyAnimationShowsFromFooter() throws Exception {

		String genreCategoryName = UILablesRepo.ANIMATION;
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.GenreCategoriesDetail(genreCategoryName);
		List<VideoDetails> genresCategoryList = videoDetails
				.get("genresCategoryList");
		List<VideoDetails> genreCategoriesShowsList = videoDetails
				.get("genreCategoriesShowsList");

		try {
			/*
			 * This Method is to register new user using Watchable application
			 * and to change a password.
			 */
			driver.get(DataServiceProperties.HOMEAPPURL);
			Thread.sleep(sleepTime);

			// This method is to ensure Home is Active page before Login into
			// Application.
			// assertionFunction.assertHomeActiveLink();

			boolean categoryFound = false;

			int noOfCategoriesDisplayed = genresCategoryList.size();
			if (genresCategoryList != null && noOfCategoriesDisplayed > 0
					&& genreCategoriesShowsList != null) {
				for (VideoDetails genreCategories : genresCategoryList) {
					if (genreCategories.getTitle().equalsIgnoreCase(
							genreCategoryName)) {
						for (int category = 1; category <= 3; category++) {
							for (int section = 1; section < 4; section++) {
								// Navigate to footer Video Categories section.
								// driver.findElement(By.xpath(".//*[@id='footercategory']/div[1]")).click();
								driver.findElement(
										By.xpath(XpathObjectRepo.FOOTERCATEGORIES_ANIMATION_XPATH))
										.click();

								String getCategoryTitle = driver
										.findElement(
												By.xpath(XpathObjectRepo.FOOTER_GENRES_LISTS_XPATH
														+ "["
														+ category
														+ "]"
														+ XpathObjectRepo.HREF_LINK_XPATH))
										.getText();
								if (getCategoryTitle
										.equalsIgnoreCase(genreCategoryName)) {
									// Click on Genre Category from footer.
									driver.findElement(
											By.xpath(XpathObjectRepo.FOOTER_GENRES_LISTS_XPATH
													+ "["
													+ category
													+ "]"
													+ XpathObjectRepo.HREF_LINK_XPATH))
											.click();

									Thread.sleep(sleepTime);
									// assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*CATEGORY[\\s\\S]*$"));
									assertTrue(driver
											.findElement(
													By.xpath(XpathObjectRepo.GENRES_PAGE_TITLE_XPATH))
											.getText()
											.equalsIgnoreCase(
													UILablesRepo.GENRES_PAGE_TITLE_TEXT));
									assertEquals(
											genreCategories.getTitle(),
											driver.findElement(
													By.xpath(XpathObjectRepo.GENRECATEGORYDETAILTITLE_XPATH))
													.getText());

									String getTypeState = driver
											.findElement(
													By.xpath(XpathObjectRepo.GENRECATEGORYTYPELABLE_XPATH))
											.getAttribute("class");
									assertEquals(
											UILablesRepo.GENRES_TYPE_LABEL_TEXT,
											getTypeState);

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
												// Lekshmi : Changed the object
												// identifier.
												// assertEquals(showList.getTitle(),driver.findElement(By.xpath(".//*[@id='content-wrap']/article/section/article/div/div[3]/div/div[1]/div/section/div/div/ul/li["+j+"]/article/h1/a")).getText());
												assertEquals(
														showList.getTitle(),
														driver.findElement(
																By.xpath(XpathObjectRepo.GENRES_PAGE_SHOWS_LIST_XPATH
																		+ "["
																		+ j
																		+ "]"
																		+ XpathObjectRepo.LINKHREF_XPATH))
																.getText());
												driver.findElement(
														By.xpath(XpathObjectRepo.GENRES_PAGE_SHOWS_LIST_XPATH
																+ "["
																+ j
																+ "]"
																+ XpathObjectRepo.LINKHREF_XPATH))
														.click();
												Thread.sleep(sleepTime);
												assertTrue(driver
														.findElement(
																By.cssSelector("BODY"))
														.getText()
														.matches(
																"^[\\s\\S]*"
																		+ showList
																				.getTitle()
																		+ "[\\s\\S]*$"));

												driver.navigate().back();
												Thread.sleep(sleepTime);
												homePageCommonFun
														.scrollToSection();

												for (int k = 0; k < (i + 1) / 10; k++) {
													String isNextEnable = driver
															.findElement(
																	By.xpath(XpathObjectRepo.HOMEGENRECHANNELSPAGINATIONBUTTON_XPATH))
															.getAttribute(
																	"class");
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
						/*
						 * if(categoryFound==false)
						 * assertTrue(driver.findElement
						 * (By.cssSelector("BODY")).
						 * getText().matches("^[\\s\\S]*"
						 * +orProUtil.getProperty("ANIMATIONANDGAMES"
						 * )+"[\\s\\S]*$"));
						 */}

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
