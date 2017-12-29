package comcast.test.testSuite;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

})
public class runSeleniumTestCasesForRegressionQA {

	@AfterClass
	public static void tearDown() throws FileNotFoundException, IOException,
			InterruptedException {
		// Changes the browser for multiple browser execution.
		// @Note: Comment if single browser
		// TestDataGenerator.ChangeBrowser();
	}
}
