package comcast.custom;



import java.io.File;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomFun {

	

	public static Logger log = Logger.getLogger(CustomFun.class.getName());

	

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
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Method Name: getRootDir Description: Method to get Root directory
	 * 
	 * @return :rootDir
	 */
	public static String getRootDir() {
		File path = new File("");
		String absPath = path.getAbsolutePath();
		File dir = new File(absPath);
		String rootDir = dir.getParent();
		return rootDir;

		// return absPath;
	}

	/**
	 * Method Name: refreshPage Description: Method to used refresh a page after
	 * doing some action (if required page refresh)
	 * 
	 * @param driver
	 *            :WebDriver
	 * @return driver
	 */
	public static WebDriver refreshPage(WebDriver driver) {

		driver.navigate().refresh();
		return (driver);
	}

	/**
	 * Method Name: switchToNewWindow Description: This function switches the
	 * browser control to new window and verifies title
	 * 
	 * @param driver
	 *            : WebDriver
	 * @param pageTitle
	 *            : title of the page
	 * @return newWindow(driver)
	 */
	public static WebDriver switchToNewWindow(WebDriver driver, String pageTitle) {

		WebDriver newWindow = null;
		Set<String> windowIterator = driver.getWindowHandles();
		System.err.println("No of windows :  " + windowIterator.size());
		for (String s : windowIterator) {
			String windowHandle = s;
			newWindow = driver.switchTo().window(windowHandle);
			System.out.println("Window Title : " + newWindow.getTitle());
			System.out.println("Window Url : " + newWindow.getCurrentUrl());
			if (newWindow.getTitle().equals(pageTitle)) {
				System.out.println("Selected Window Title : "
						+ newWindow.getTitle());
				return newWindow;
			}

		}
		System.out.println("Window Title :" + newWindow.getTitle());
		System.out.println();
		return newWindow;
	}
	
	/**
	 * This function switches back the handle to parent window.
	 * 
	 * @param driver
	 * @param winHandleBefore
	 */
	public static void switchBackToParentWindow(WebDriver driver, String winHandleBefore) {

		try {
			//Close opened browser
			driver.close();
			
			//Switch back to parent window 
			driver.switchTo().window(winHandleBefore);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * Method Name: isElementHighlighted Description: This function verifies
	 * whether the element is highlighted or not on rollover/mouseOver of the
	 * element based on the action specified
	 * 
	 * @param driver
	 *            : WebDriver instance
	 * 
	 * @param WebElement
	 *            : WebElement that needs to rollover or click to check element
	 *            is highlighted or not
	 * 
	 * @param isHighlight
	 *            : If true checks element is highlighted If false checks
	 *            element is not highlighted
	 * 
	 * @param action
	 *            : "rollOver" : mousehover / roll overs on specified element
	 *            "click" : clicks on specified element
	 * 
	 * @param eleName
	 *            : Name of the element
	 * 
	 * @param prop
	 *            : property of the element
	 * 
	 * @throws Exception
	 *             ,ElementNotHiglightedException, ElementHiglightedException
	 * 
	 */
	

	/**
	 * Method Name: waitObjectToLoad Description: This function waits until the
	 * specific element to load
	 * 
	 * @param driver
	 *            : WebDriver instance
	 * @param by
	 *            :Element locator
	 * @param timeSec
	 *            :time in seconds
	 */
	public static void waitObjectToLoad(WebDriver driver, By by, int timeSec) {

		WebDriverWait wait = new WebDriverWait(driver, timeSec);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));

	}


	
	



	
	
	
	
	
	
	

}
	
	

