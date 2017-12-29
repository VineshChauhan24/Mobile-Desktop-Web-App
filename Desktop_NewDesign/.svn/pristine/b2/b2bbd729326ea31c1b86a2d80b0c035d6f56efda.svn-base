package comcast.common.asserts.pageAssertions;

import static comcast.util.PropertyFileReader.ObjRepoProp;
import static comcast.util.PropertyFileReader.TextProp;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class HomePageAssertions {

	// For logging
	public static Logger log = Logger.getLogger(HomePageAssertions.class);

	/**
	 * This Method verifies successful login to watchable Apps
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public static void verifySuccessfulLogin(WebDriver driver) throws Exception {

		// Verify Play list title is displaying after successful login 
		Assert.assertTrue(
				driver.findElement(
						By.id(ObjRepoProp.getProperty("loginLink_ID")))
						.getText().contains(TextProp.getProperty("email")),
				"Login to Watachable Apps Failed...!"); 
		
		
		log.info("Successfully login to Watchable application...!");
		Reporter.log("<p>Successfully login to Watchable application...!");

	}

}
