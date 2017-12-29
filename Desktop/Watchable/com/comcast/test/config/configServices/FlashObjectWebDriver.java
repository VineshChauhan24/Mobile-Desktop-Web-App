package comcast.test.config.configServices;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class FlashObjectWebDriver {

	public final WebDriver webDriver;
	private final String flashObjectId;

	public FlashObjectWebDriver(final WebDriver webDriver,
			final String flashObjectId) {
		this.webDriver = webDriver;
		this.flashObjectId = flashObjectId;
	}

	public String callFlashObject(final String functionName,
			final String... args) {
		String jsFunction = makeJsFunction(functionName, args);
		System.out.println("The javascript function to be called is ::"
				+ jsFunction);

		final Object result = ((JavascriptExecutor) webDriver).executeScript(
				jsFunction, new Object[0]);
		return result != null ? result.toString() : null;
	}

	private String makeJsFunction(final String functionName,
			final String... args) {
		final StringBuffer functionArgs = new StringBuffer();

		if (args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				if (i > 0) {
					functionArgs.append(",");
				}
				functionArgs.append(String.format("'%1$s'", args[i]));
			}
		}
		return String.format("return document.%1$s.%2$s(%3$s);", flashObjectId,
				functionName, functionArgs);
	}
}