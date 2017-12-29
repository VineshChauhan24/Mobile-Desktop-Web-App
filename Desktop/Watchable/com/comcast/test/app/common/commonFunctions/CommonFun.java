package comcast.test.app.common.commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import comcast.test.config.configServices.utils.BaseTest;

public class CommonFun extends BaseTest {

	public static void waitObjectToLoad(WebDriver driver, By by, int timeSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeSec);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public static boolean isElementPresent(WebDriver driver, By by) {
		boolean isPresent = false;
		try {
			isPresent = driver.findElement(by) != null;
		} catch (NoSuchElementException nse) {
		}
		return isPresent;
	}

	public static void mouseOverElementAndClick(WebDriver driver,
			WebElement ele, String eleName) throws Exception {
		try {

			// Mouse hover/roll over on element
			Actions builder = new Actions(driver);
			builder.moveToElement(ele).build().perform();
			builder.click().perform();

		} catch (NoSuchElementException e) {
			e.printStackTrace();

		}

	}

	/**
	 * Method Name: mouseOverElement Description: This method used to scroll to
	 * an element which not visible in the screen
	 * 
	 * @param driver
	 *            : WebDriver
	 * @param ele
	 *            : WebElement locator
	 * */
	public static void mouseOverElement(WebDriver driver, WebElement ele)
			throws Exception {
		try {

			// Mouse hover/roll over on element
			Thread.sleep(sleepTime);
			Actions builder = new Actions(driver);
			builder.moveToElement(ele).build().perform();

		} catch (NoSuchElementException e) {
			log.error("Element to mouse over is not present " + e);

		}

	}

	/**
	 * MethodName: isElementClickable Description: To check whether element is
	 * clickable or not
	 * 
	 * @param ele
	 *            : WebElement
	 * @param eleName
	 *            : Element for which Clickable need to be checked
	 * 
	 * 
	 * @return Boolean value
	 * 
	 * 
	 */
	public static boolean isElementClickable(WebElement ele, String eleName,
			boolean match) throws Exception {

		String eleCursor = ele.getCssValue("cursor");
		log.info(eleCursor);

		//match = false;

		// Check element is not clickable
		// if (eleCursor.equals("auto") || eleCursor.equals("text")) {
		//if (eleCursor.equals("auto") || eleCursor.equals("text")) {
		if (eleCursor.equals("text")) {

			// If not clickable, log success message
			log.info(eleName + " is not clickable");
			match = false;

		}
		
		else if (eleCursor.equals("auto")) {

			// If not clickable, log success message
			log.info(eleName + " is not clickable");
			match = false;

		}
		// Check element is clickable
		//else if (eleCursor.equals("pointer")) {
		else {
			// if element is clickable, log error message
			log.info(eleName + " is clickable");
			match = true;
		}

		return match;
	}

	/**
	 * Method Name: navigateBack Description: Method to used to go back to home
	 * page by clicking on browser back button
	 * 
	 * @param driver
	 *            :WebDriver
	 * @return driver
	 */
	public static WebDriver navigateBack(WebDriver driver) throws Exception {

		// Navigate back to Previous page
		driver.navigate().back();
		Thread.sleep(sleepTime);
		return (driver);
	}

}
