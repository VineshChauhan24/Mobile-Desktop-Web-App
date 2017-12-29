package comcast.test.showPage.showPageFunctions;

import static comcast.util.PropertyFileReader.ObjRepoProp;
//import static comcast.util.PropertyFileReader.TextProp;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import comcast.config.BaseTest;
//import comcast.custom.CustomFun;
import comcast.uiFunctions.GUIFunctions;

public class ShowFun extends BaseTest {

	public Logger log = Logger.getLogger(this.getClass().getName());

	/**
	 * Method Name:This method click on first show title from play list video
	 * list
	 * 
	 * @return
	 */

	public static void clickOnFirstVideoTitle() throws Exception {

		// Click on first video show title
		GUIFunctions
				.clickElement(driver, By.xpath(ObjRepoProp
						.getProperty("playlistFirstVideShowTitle_XPATH")),
						"Show Title");
		Thread.sleep(5000);
	}

	/**
	 * Method Name:This method click on WATCHABLE header text from show page
	 * 
	 * @return
	 */

	public static void clickOnWatchableHeaderText() throws Exception {

		// Click on WATCHABLE header text
		GUIFunctions.clickElement(driver, By.xpath(ObjRepoProp
				.getProperty("homePageWatchableLogo_XPATH")),
				"WATCHABLE header text ");
		Thread.sleep(5000);
	}

	/**
	 * Method Name:This method click on home icon from show page
	 * 
	 * @return
	 */

	public static void clickOnHomeIcont() throws Exception {

		// Click on home icon
		GUIFunctions.clickElement(driver,
				By.xpath(ObjRepoProp.getProperty("ShowPageHomeIcon_XPATH")),
				"Home Icon");
		Thread.sleep(5000);
	}

}
