package comcast.test.config.dataServices.vo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import comcast.test.config.configServices.utils.RestAPIServices;

public class RestDataFactory {

	public static Map<String, List<VideoDetails>> getBundleDetails()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.FeaturedBundleAPI();
		List<VideoDetails> BundlesList = videoDetails.get("bundlesList");
		List<VideoDetails> ChannelsListUnderBundleRows = videoDetails
				.get("showsInBundle");
		List<VideoDetails> ShowsListUnderBundleSHOWS = videoDetails
				.get("subShowInBundleChannel");
		List<VideoDetails> VideoListUnderBundleSHOWS = videoDetails
				.get("videosInBundleChannel");

		List<VideoDetails> featuredShowsListUnderBundle = new ArrayList<VideoDetails>();
		List<VideoDetails> featuredBundleChannelsSubShowsList = new ArrayList<VideoDetails>();

		VideoDetails featuredChannelDetails = featuredShowsListUnderBundle
				.get(1);
		VideoDetails featuredSubShowDetails = featuredBundleChannelsSubShowsList
				.get(0);
		return videoDetails;
	}

}
