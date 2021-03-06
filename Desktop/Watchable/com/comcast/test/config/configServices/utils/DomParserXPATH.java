package comcast.test.config.configServices.utils;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.http.client.methods.HttpGet;
import org.xml.sax.SAXException;

public class DomParserXPATH {

	public static String getCategories(String url)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		String response = null;
		HttpGet get = new HttpGet(url);
		get.setHeader("Content-Type", "application/json");
		get.setHeader("Accept", "application/json;fields=data+counts");

		try {
			RestServiceUtil restApi = new RestServiceUtil();
			response = restApi.executeHTTPGet(get);
			System.out.println("Getting Response>>>>>>>" + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public static String getSubscriptionList(String subUrl, List<String> userId) {
		// subUrl +="?userId="+userId.get(0)+"&sessionId="+userId.get(1);

		System.out.println("Subscription URL 00 >>>>>>>" + subUrl);
		String restAPIResponse = null;

		try {
			HttpGet get = new HttpGet(subUrl.toString());
			get.setHeader("Content-Type", "application/xml");
			get.setHeader("Accept", "application/json");
			get.setHeader("userId", userId.get(0));
			get.setHeader("reference", userId.get(1));

			// Service call
			RestServiceUtil restAPICall = new RestServiceUtil();
			restAPIResponse = restAPICall.executeHTTPGet(get);

		} catch (Exception exe) {
			exe.printStackTrace();
		}

		// subUrl = "http://www.valtech.co.in/";
		/*
		 * String subscriptionResponse = null;
		 * 
		 * URL url; try {
		 * 
		 * url = new URL(subUrl); URLConnection conn = url.openConnection();
		 * StringBuilder myCookies = new StringBuilder();
		 * myCookies.append("userId="
		 * ).append(userId.get(0)).append("; reference=").append(userId.get(1));
		 * conn.addRequestProperty("Cookie",myCookies.toString());
		 * conn.setDoOutput(true); // conn.connect(); OutputStreamWriter wr =
		 * new OutputStreamWriter(conn.getOutputStream());
		 * 
		 * wr.flush(); BufferedReader reader = new BufferedReader(new
		 * InputStreamReader(conn.getInputStream())); StringBuilder sb = new
		 * StringBuilder(); String line = null;
		 * 
		 * while((line = reader.readLine()) != null) { sb.append(line + "\n"); }
		 * 
		 * subscriptionResponse = sb.toString();
		 * 
		 * 
		 * } catch (Exception exe) { exe.printStackTrace(); }
		 */

		/*
		 * try
		 * 
		 * { URL url = new URL(subUrl); HttpURLConnection urlConnection =
		 * (HttpURLConnection) url.openConnection(); try { InputStream in = new
		 * BufferedInputStream(urlConnection.getInputStream()); String
		 * abc=convertStreamToString(in); // return new JSONObject(abc);
		 * 
		 * } catch(Exception e) { e.printStackTrace(); //
		 * Log.e(this.getClass().getCanonicalName(),
		 * "Failed to get subscription lis " + params[0], e); } finally {
		 * urlConnection.disconnect(); } }
		 * 
		 * catch (Exception e) { e.printStackTrace(); //
		 * Log.e(this.getClass().getCanonicalName(),
		 * "Failed to get subscription list " + params[0], e);
		 * 
		 * return null; }
		 */

		/*
		 * try
		 * 
		 * { String credentials = "test_302" + ":" + "Demo1111"; //String
		 * encoding = Base64Converter.encode(credentials.getBytes("UTF-8"));
		 * String encoding=Base64Encoder(credentials.getBytes("UTF-8")); URL url
		 * = new URL(subUrl); URLConnection uc = url.openConnection();
		 * uc.setRequestProperty("Authorization", String.format("Basic %s",
		 * encoding));
		 * 
		 * // HttpURLConnection urlConnection = (HttpURLConnection)
		 * uc.openConnection(); try { InputStream in = new
		 * BufferedInputStream(uc.getInputStream());
		 * subscriptionResponse=convertStreamToString(in);
		 * 
		 * 
		 * HttpHost targetHost = new HttpHost("api.stage2.xidio.com",30,
		 * "http"); HttpGet get = new HttpGet(subUrl);
		 * get.setHeader("Content-Type", "application/json");
		 * get.setHeader("Accept", "application/json;fields=data+counts");
		 * HttpClient httpClient = new DefaultHttpClient();
		 * 
		 * ((AbstractHttpClient)
		 * httpClient).getCredentialsProvider().setCredentials( new
		 * AuthScope(targetHost.getHostName(), targetHost.getPort()), new
		 * UsernamePasswordCredentials("test_302", "Demo1111"));
		 * 
		 * 
		 * ResponseHandler<String> responseHandler = new BasicResponseHandler();
		 * subscriptionResponse = httpClient.execute(get, responseHandler);
		 * 
		 * return subscriptionResponse;
		 * 
		 * } catch(Exception e) { e.printStackTrace(); }
		 */
		return restAPIResponse;

	}

	private static String Base64Encoder(byte[] bytes) {
		// TODO Auto-generated method stub
		return null;
	}

	private static String convertStreamToString(java.io.InputStream is) {
		Scanner s = new Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

}
