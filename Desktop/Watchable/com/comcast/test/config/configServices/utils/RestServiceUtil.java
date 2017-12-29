package comcast.test.config.configServices.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Util class will be used for Rest service call.
 * 
 * @author Dillip.Lenka
 */
public class RestServiceUtil {
	/**
	 * A generic method to execute all of HTTP Get request
	 * 
	 * @param {@link HttpGet}
	 * @return {@link HttpResponse}
	 */
	public String executeHTTPGet(HttpGet get) throws Exception {
		// HTTP Client Library
		HttpClient httpClient = new DefaultHttpClient();
		try {
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpClient.execute(get, responseHandler); // "{\"ApiKey\":\"e59fa293-d822-43bb-a7c3-a5774e7b25ab\"}";

			return responseBody;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
}
