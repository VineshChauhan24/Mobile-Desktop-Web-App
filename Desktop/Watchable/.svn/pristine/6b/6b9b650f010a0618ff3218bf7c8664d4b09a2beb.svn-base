package comcast.test.app.common.objectProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import comcast.test.app.common.constant.XidioConstant;
import comcast.test.config.configServices.utils.RestAPIServices;
import comcast.test.config.dataServices.vo.VideoDetails;

public class StorePageElements {

	public static List<VideoDetails> getFeaturedBundleAPIDetails()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.FeaturedBundleAPI();
		List<VideoDetails> bundlesList = videoDetails.get("bundlesList");

		List<VideoDetails> bundleList = new ArrayList<VideoDetails>();

		VideoDetails bundleName = bundlesList.get(XidioConstant.selectBundle);

		return bundlesList;
	}

	public static List<String> getBundleDetails()
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		Map<String, List<VideoDetails>> videoDetails = RestAPIServices
				.FeaturedBundleAPI();
		Map<String, List<VideoDetails>> episodesDetails = RestAPIServices
				.episodeListUnderBundleSHOWS();
		List<VideoDetails> BundlesList = videoDetails.get("bundlesList");
		List<VideoDetails> ShowsListUnderBundleSHOWS = videoDetails
				.get("subShowInBundleChannel");
		List<VideoDetails> EpisodeListUnderBundleSHOWS = episodesDetails
				.get("episodeCountInBundleSHOWS");

		List<String> bundleDetailsList = new ArrayList<String>();
		String bundleName = BundlesList.get(XidioConstant.selectBundle)
				.getTitle();
		String bundleChannel = ShowsListUnderBundleSHOWS.get(
				XidioConstant.selectBundleChannel).getTitle();
		String showsInBundleChannel = EpisodeListUnderBundleSHOWS.get(
				XidioConstant.selectBundleChannel).getTitle();

		bundleDetailsList.add(bundleName);
		bundleDetailsList.add(bundleChannel);
		bundleDetailsList.add(showsInBundleChannel);

		return bundleDetailsList;
	}
}
