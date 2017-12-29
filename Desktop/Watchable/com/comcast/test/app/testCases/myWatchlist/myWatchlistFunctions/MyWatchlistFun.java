package comcast.test.app.testCases.myWatchlist.myWatchlistFunctions;

import org.openqa.selenium.By;

import comcast.test.app.common.XpathObjectRepo;
import comcast.test.config.configServices.utils.BaseTest;

public class MyWatchlistFun extends BaseTest {

	/**
	 * Method Name: clickOnMyWatchlistMenu Description: This method used click
	 * On My Shows menu from header
	 * 
	 * @throwsdException
	 */
	public static void clickOnMyWatchlistMenu() throws Exception {

		// click On Provider name link

		String myWatchlistTitle = driver.findElement(
				By.xpath(XpathObjectRepo.myWatchlistMenu_XPATH)).getText();

		driver.findElement(By.xpath(XpathObjectRepo.myWatchlistMenu_XPATH))
				.click();

		log.info("Successfully clicked on title '" + myWatchlistTitle
				+ "' from Header menu");
		Thread.sleep(sleepTime);
	}

	/**
	 * Method Name: clickOnMyWatchlistMenu Description: This method used click
	 * On My Shows menu from header 
	 * 
	 * @throwsdException
	 */
	public static void clickOnShowsTitle() throws Exception {

		// click On show name from video details

		String showsTitle = driver.findElement(
				By.xpath(XpathObjectRepo.myShowsPageFirstVideoShowTitle_XPATH))
				.getText();

		driver.findElement(
				By.xpath(XpathObjectRepo.myShowsPageFirstVideoShowTitle_XPATH))
				.click();

		log.info("Successfully clicked on shows title '" + showsTitle
				+ "' from video section");
		Thread.sleep(sleepTime);
	}
	
	
	
	
	/**
	 * Method Name: clickOnMyWatchlistMenu Description: This method used click
	 * On My Shows menu from header 
	 * 
	 * @throwsdException
	 */
	public static void clickOnFirstShowsTitle() throws Exception {

		// click On Show title

		String showsTitle = driver.findElement(
				By.xpath(XpathObjectRepo.myShowsPageFirstShowTitle_XPATH))
				.getText();

		driver.findElement(
				By.xpath(XpathObjectRepo.myShowsPageFirstShowTitle_XPATH))
				.click();

		log.info("Successfully clicked on shows title '" + showsTitle
				+ "' from video section");
		Thread.sleep(sleepTime);
	}

}
