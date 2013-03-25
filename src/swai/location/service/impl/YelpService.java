package swai.location.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.springframework.util.StringUtils;

import swai.framework.util.PropertiesHelper;
import swai.location.data.Coordinate;
import swai.location.data.Location;
import swai.location.data.LocationRequest;
import swai.location.data.LocationResponse;
import swai.location.data.yelp.Business;
import swai.location.data.yelp.Center;
import swai.location.data.yelp.Region;
import swai.location.data.yelp.Span;
import swai.location.data.yelp.YelpResponse;
import swai.location.service.api.LocationService;
import swai.location.service.api.YelpApi2;

import com.google.gson.Gson;

public class YelpService implements LocationService {

	// logger
	private static final Logger log = Logger.getLogger(YelpService.class
			.getName());

	// OAuth keys
	private static final String CONSUMER_KEY;
	private static final String CONSUMER_SECRET;
	private static final String TOKEN;
	private static final String TOKEN_SECRET;
	private static final String API_CALL;
	private static final String API_SEARCH_RADIUS;
	private static final String API_SEARCH_SORT;

	static {
		PropertiesHelper props = PropertiesHelper.getInstance();
		CONSUMER_KEY = props.get("yelp.consumerkey");
		CONSUMER_SECRET = props.get("yelp.consumersecret");
		TOKEN = props.get("yelp.token");
		TOKEN_SECRET = props.get("yelp.tokensecret");
		API_CALL = props.get("yelp.api.search");
		API_SEARCH_RADIUS = props.get("yelp.api.search.radius");
		API_SEARCH_SORT = props.get("yelp.api.search.sort");
	}

	private OAuthService service;
	private Token accessToken;
	private static Gson gson;

	public YelpService() {
		service = new ServiceBuilder().provider(YelpApi2.class)
				.apiKey(CONSUMER_KEY).apiSecret(CONSUMER_SECRET).build();
		accessToken = new Token(TOKEN, TOKEN_SECRET);
		// gson = new GsonBuilder().setPrettyPrinting().create();
		gson = new Gson();
	}

	@Override
	public LocationResponse list(LocationRequest req) {

		OAuthRequest request = new OAuthRequest(Verb.GET, API_CALL);

		// set location
		double lat = req.getLatitude();
		double lg = req.getLongitude();
		request.addQuerystringParameter("ll", lat + "," + lg);

		// set search term
		String term = req.getTerm();
		if (StringUtils.hasText(term))
			request.addQuerystringParameter("term", term);

		// set category
		String categoryFilter = req.getCategoryFilter();
		if (StringUtils.hasText(categoryFilter))
			request.addQuerystringParameter("category_filter", categoryFilter);

		// set additional params 
		request.addQuerystringParameter("radius_filter", API_SEARCH_RADIUS);
		request.addQuerystringParameter("sort", API_SEARCH_SORT);

		// sign request and send
		service.signRequest(accessToken, request);
		Response response = request.send();

		// log headers
		log.info("Headers: " + response.getHeaders());

		return YelpService.getLocationData(response.getBody());
	}

	private static final LocationResponse getLocationData(String jsonBody) {

		// deserialize
		YelpResponse res = gson.fromJson(jsonBody, YelpResponse.class);

		// debug
		// log.info("YelpResponse: " + gson.toJson(res));

		// iterate over businesses
		List<Location> locs = new ArrayList<Location>();
		List<Business> businesses = res.getBusinesses();
		if (businesses != null) {
			for (Business biz : businesses)
				locs.add(YelpService.formatLocation(biz));
		}

		// debug
		// log.info("LocationResponse: " + gson.toJson(locs));

		// add region
		Coordinate center = null;
		Coordinate delta = null;
		Region region = res.getRegion();
		if (region != null) {
			Center c = region.getCenter();
			if (c != null)
				center = new Coordinate(c.getLatitude(), c.getLongitude());
			Span s = region.getSpan();
			if (s != null)
				delta = new Coordinate(s.getLatitude_delta(),
						s.getLongitude_delta());
		}

		return new LocationResponse(locs, center, delta);
	}

	private static final Location formatLocation(Business biz) {
		swai.location.data.yelp.Location loc = biz.getLocation();
		swai.location.data.yelp.Coordinate coor = loc.getCoordinate();
		return new Location(coor.getLatitude(), coor.getLongitude(),
				biz.getId(), biz.getName(), biz.isIs_claimed(),
				biz.isIs_closed(), biz.getMobile_url(), biz.getUrl(),
				biz.getImage_url(), biz.getPhone(), biz.getDisplay_phone(),
				biz.getDistance(), biz.getReview_count(), biz.getRating(),
				biz.getRating_img_url(), biz.getRating_img_url_small(),
				biz.getRating_img_url_large(), biz.getSnippet_text(),
				biz.getSnippet_image_url(), loc.getAddress(),
				loc.getDisplay_address(), loc.getCity(), loc.getState_code(),
				loc.getPostal_code(), loc.getCross_streets(),
				loc.getNeighborhoods(), loc.getGeo_accuracy());
	}

}
