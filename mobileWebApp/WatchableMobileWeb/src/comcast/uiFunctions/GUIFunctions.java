package comcast.uiFunctions;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;



public class GUIFunctions {

	public static Logger log = Logger.getLogger(GUIFunctions.class.getName());

	/**
	 * Method Name: isElementPresent Description:Method to verify the Element
	 * 
	 * @param by
	 *            :Element locator
	 * @param driver
	 *            :WebDriver
	 * @return true: if element is present, false: if element is not present
	 */
	public static boolean isElementPresent(By by, WebDriver driver) {
		try {
			// Verify element is present
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			log.error("No such element present--> \n" + e);
			Reporter.log("<p>No such element present");
			return false;
		}
	}

	/**
	 * Method Name: navigateBack Description: Method to used to go back to home
	 * page by clicking on browser back button
	 * 
	 * @param driver
	 *            :WebDriver
	 * @return driver
	 */
	public static WebDriver navigateBack(WebDriver driver) {
		try {

			
				// Navigate back to Previous page
				driver.navigate().back();
			}

		 catch (Exception e) {
			log.error("Navigation to previous page failed--> \n" + e);
			Reporter.log("<p>Navigation to previous page failed");
		}

		return (driver);
	}
	
	/**
	 * Method Name: navigateBack Description: Method to used to go Forward 
	 	 * @param driver
	 *            :WebDriver
	 * @return driver
	 */
	public static WebDriver navigateForward(WebDriver driver) {
		try {

			
				// Navigate back to Forward page
				driver.navigate().forward();
			}

		 catch (Exception e) {
			log.error("Navigation to Forward page failed--> \n" + e);
			Reporter.log("<p>Navigation to Forward page failed");
		}

		return (driver);
	}
	
	
	/**
	 * Method Name: clickElement Description: This method clicks on WebElement
	 * specified
	 * 
	 * @param driver
	 *            : WebDriver
	 * @param ele
	 *            : WebElement locator
	 * @param eleName
	 *            : Name of the element to be clicked
	 */
	public static void clickElement(WebDriver driver, By by, String eleName) {
		try {

			WebElement element=driver.findElement(by);
			
			// Click on element
			element.click();

			log.info("Successfully clicked on element: " + eleName);
			Reporter.log("<p>Successfully clicked on element: " + eleName);

		} catch (NoSuchElementException e) {
			log.error("Element to click is not present " + e);
			Reporter.log("<p>Element to click is not present");
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
				Thread.sleep(5000);
				Actions builder = new Actions(driver);
				builder.moveToElement(ele).build().perform();
			
		} catch (NoSuchElementException e) {
			log.error("Element to mouse over is not present " + e);
			Reporter.log("<p>Element to mouse over is not present");

		}

	}
	
	/**
	 * This Function selects the radio button iFrame.
	 * 
	 * @param driver
	 *            : WebDriver instance.
	 */

	public static void selectRaidoButton(WebDriver driver, By by, String eleName) {
		// Initialize WebElement
		WebElement ele = driver.findElement(by);

		try {
			if (!ele.isSelected()) {
				ele.click();
			}
			log.info("Successfully selected the raido button '" + eleName + "'");
		}

		catch (NoSuchElementException e) {

			log.error("Exception element not present : " + eleName);
		}

	}

	/**
	 * Method Name: mouseOverElementAndClick Description: This method used to
	 * scroll to an element which not visible in the screen/ or Not Clickable by
	 * GUIFunctions.clickOnElement method and clicks on it
	 * 
	 * @param driver
	 * @param ele
	 * @param eleName
	 * @throws Exception
	 */
	public static void mouseOverElementAndClick(WebDriver driver, WebElement ele,String eleName)
 throws Exception {
		try {

			
				// Mouse hover/roll over on element
				Thread.sleep(5000);
				Actions builder = new Actions(driver);
				builder.moveToElement(ele).build().perform();
				builder.click().perform();

				log.info("Successfully mouse over and clicked on: " + eleName);
				Reporter.log("<p>Successfully mouse over and clicked on: "
						+ eleName);

			
		} catch (NoSuchElementException e) {
			log.error("Element to mouse over and click is not present " + e);
			Reporter.log("<p>Element to mouse over and click is not present");

		}

	}
	
	/**
	 * Method Name: clickElement Description: This method clicks on WebElement
	 * specified
	 * 
	 * @param driver
	 *            : WebDriver
	 * @param ele
	 *            : WebElement locator
	 * @param eleName
	 *            : Name of the element to be clicked
	 */
	public static void typeTxtboxValue(WebDriver driver, By by, String value) {
		try {

			// Click on element
			//driver.findElement(by).clear();
			driver.findElement(by).sendKeys(value);
		} catch (NoSuchElementException e) {
			log.error("Element to type data is not present " + e);
			Reporter.log("<p>Element to type data is not present");

		}
	}
	
	
	
	
	
	/**
	 * 
	 * Method Name: keyPressPageUp 
	 * Description: This method Press on Page up Key Once
	 * @param driver : WebDriver
	 * @throws Exception
	 *  
	 */
	public static void keyPressPageUp(WebDriver driver) throws Exception {
		
		
			Thread.sleep(2500);
			Actions action = new Actions(driver);
			action.sendKeys(Keys.PAGE_UP).build().perform();
		
	}
	
			
	/**
	 * Method Name: selectDropDownValue_new Description: This method selects
	 * dropdown value
	 * 
	 * @param ele
	 *            : DropDown webelement
	 * @param dropDownValue
	 *            : dropDown Value to be selected
	 * */
	public static void selectDropDownValue(WebElement ele,
			String dropDownValue, String optType) throws Exception {
		try {
			// Dropdown in which value to be selected
			Select dropDown = new Select(ele);

			// Selecting dropdown value
			if (optType.equalsIgnoreCase("text")) {
				dropDown.selectByVisibleText(dropDownValue.trim());
			} else if (optType.equalsIgnoreCase("value")) {
				dropDown.selectByValue(dropDownValue);
			}

		} catch (NoSuchElementException e) {
			log.error("Element to select drop down is not present " + e);
			Reporter.log("<p>Element to drop down is not present");

		}

	}
	
	
	
	/**
	 * Method Name: checkOrUncheckCheckBox Description: This method
	 * checks/Unchecks the checkbox
	 * 
	 * @param driver
	 *            : WebDriver
	 * @param By
	 *            : WebElement locator
	 * @param eleName
	 *            : Name of the element to be checked
	 * @param toCheck
	 *            : True: To check the checkbox false: To uncheck the checkbox
	 * 
	 * */
	public static void checkOrUncheckCheckBox(WebDriver driver, By by,
			String eleName, Boolean toCheck) throws Exception {
		try {

			if (toCheck) {
				// check checkbox if not checked
				if (!driver.findElement(by).isSelected()) {
					driver.findElement(by).click();

					log.info("Successfully checked the checkbox: " + eleName);
					Reporter.log("<p>Successfully checked the checkbox: "
							+ eleName);
				}
			} else if (!toCheck) {
				// Uncheck checkbox if it is checked
				if (driver.findElement(by).isSelected()) {
					driver.findElement(by).click();

					log.info("Successfully unchecked the checkbox: " + eleName);
					Reporter.log("<p>Successfully unchecked the checkbox: "
							+ eleName);
				}
			}

		} catch (NoSuchElementException e) {
			log.error("Checkbox element is not present " + e);
			Reporter.log("<p>Checkbox element is not present ");

		}

	}

}
