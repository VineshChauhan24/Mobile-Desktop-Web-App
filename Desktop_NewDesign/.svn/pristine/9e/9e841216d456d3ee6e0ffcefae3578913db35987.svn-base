package comcast.pages;

import static comcast.util.PropertyFileReader.ObjRepoProp;
import static comcast.util.PropertyFileReader.TextProp;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import comcast.custom.CustomFun;

public class HomePage {

	private final WebDriver driver;
	// For logging
	public Logger log = Logger.getLogger(this.getClass().getName());
	
	// Homepage Constructor
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		
		// Check that we're on the right page-Home Page.

		if (!(CustomFun
				.isElementPresent(
						By.xpath(ObjRepoProp.getProperty("homePageType_XPATH")),
						driver))) {

			throw new IllegalStateException("This is not the Web cliemt home page");
		}
	}

	
	//By loginButton = By.id(ObjRepoProp.getProperty("homePageLoginButton_ID"));
	//By signUpButton = By.id(ObjRepoProp.getProperty("homePageSignUpButton_ID"));
	

	/*
	 * *************************************************************************
	 * Home Page Functions
	 * *************************************************************************
	 */
	
	
	
		
	/**
	 * This function navigates to Watchable application URL
	 * 
	 * 
	 * @param baseUrl:  Application URL
	 * @return HomePage object
	 * @throws Exception
	 */
	public static HomePage navigateToWatchableHomePage(WebDriver driver, String baseUrl) throws Exception {

		//Navigate to WATCHABLE URL
		driver.get(baseUrl);
		Thread.sleep(1000);
		
		//Refresh Marketing page to navigate to WATCHABLE home page.
		
		CustomFun.refreshPage(driver);
		
		return new HomePage(driver);

	}
	
	
	/**
	 * This Method clicks on left menu
	 * 
	 * @return: HomePage object
	 * @throws Exception
	 */
/*	public HomePage clickOnLoginButton(WebDriver driver) throws Exception {

		// Click on Menu
		GUIFunctions.clickElement(driver, menuButton, "Menu Button");
		Thread.sleep(5000);

		return new HomePage(driver);

	}*/

	

	/**
	 * This Method logout from watchable App
	 * 
	 * @return: HomePage object
	 * @throws Exception
	 */
	/*public HomePage logoutFromWatchable(WebDriver driver) throws Exception {

		// Click on user name to display logout button
		GUIFunctions.clickElement(driver, loginLink, "Login Link");
		Thread.sleep(1000);

		// Click on logout button
		GUIFunctions.clickElement(driver, logoutButton, "Logout Button");
		Thread.sleep(5000);
		return new HomePage(driver);

	}*/

}
