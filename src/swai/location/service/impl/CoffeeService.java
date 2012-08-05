package swai.location.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import swai.location.data.ArtisanCoffeeShop;
import swai.location.data.Location;
import swai.location.data.LocationRequest;
import swai.location.data.LocationResponse;
import swai.location.persistence.CoffeeShop;
import swai.location.persistence.CoffeeShopDatastore;
import swai.location.service.api.LocationService;
import swai.location.service.api.LocationServiceFactory;

public class CoffeeService implements LocationService {

	// logger
	private static final Logger log = Logger.getLogger(CoffeeService.class
			.getName());

	// Yelp service
	private static final LocationService ys = LocationServiceFactory
			.getInstance().getService(LocationServiceFactory.YELP_SERVICE);

	/*
	 * (non-Javadoc)
	 * 
	 * @see swai.location.service.api.LocationService#list(swai.location.data.
	 * LocationRequest)
	 */
	@Override
	public LocationResponse list(LocationRequest req) {

		// get Yelp results
		LocationRequest coffeeReq = CoffeeService.filterCoffeeRequest(req);
		log.info("Calling Yelp service with LocationRequest:"
				+ coffeeReq.toString());
		LocationResponse yelpRes = ys.list(coffeeReq);

		// decorate response with datastore data
		log.info("Matching locations against datastore");
		return CoffeeService.decorateCoffeeShopResponse(yelpRes);
	}

	/**
	 * Annotates LocationResponse with artisan shop data
	 * 
	 * @param res
	 * @return
	 */
	private static final LocationResponse decorateCoffeeShopResponse(
			LocationResponse res) {

		if (res == null)
			return null;

		@SuppressWarnings("unchecked")
		List<Location> locations = (List<Location>) res.getLocations();

		// send IDs to Datastore to find matches
		log.info("Calling CoffeeShopDatastore");
		List<String> locationIds = CoffeeService.formatIdList(locations);
		Map<String, CoffeeShop> artisanLocationMap = CoffeeShopDatastore
				.getInstance().getAll(locationIds);

		// annotate artisan coffee shops
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<? super Location> decoratedCoffeeShops = new ArrayList(
				locations.size());
		for (Location loc : locations) {
			CoffeeShop shop = artisanLocationMap.get(loc.getId());
			if (shop != null)
				decoratedCoffeeShops.add(CoffeeService.formatCoffeeShop(loc,
						shop));
			else
				decoratedCoffeeShops.add(loc);
		}

		return new LocationResponse(decoratedCoffeeShops,
				res.getRegionCenter(), res.getRegionSpanDelta());
	}

	/**
	 * Formats ArtisanCoffeeShop object from Location and CoffeeShop
	 * 
	 * @param loc
	 * @param shop
	 * @return
	 */
	private static final ArtisanCoffeeShop formatCoffeeShop(Location loc,
			CoffeeShop shop) {
		return new ArtisanCoffeeShop(loc.getLatitude(), loc.getLongitude(),
				loc.getId(), loc.getName(), loc.isClaimed(), loc.isClosed(),
				loc.getMobile_url(), loc.getUrl(), loc.getImageUrl(),
				loc.getPhone(), loc.getDisplayPhone(), loc.getDistance(),
				loc.getReviewCount(), loc.getRating(), loc.getRatingImgUrl(),
				loc.getRatingImgUrlSmall(), loc.getRatingImgUrlLarge(),
				loc.getSnippetText(), loc.getSnippetImageUrl(),
				loc.getAddress(), loc.getDisplayAddress(), loc.getCity(),
				loc.getStateCode(), loc.getPostalCode(), loc.getCrossStreets(),
				loc.getNeighborhoods(), loc.getGeoAccuracy(),
				shop.getStoreTags(), shop.getBeanTags());
	}

	/**
	 * Helper for list of Yelp IDs for datastore call
	 * 
	 * @param locations
	 * @return
	 */
	private static final List<String> formatIdList(
			List<? extends Location> locations) {
		List<String> idList = null;
		if (locations != null) {
			idList = new ArrayList<String>(locations.size());
			for (Location loc : locations)
				idList.add(loc.getId());
		}
		return idList;
	}

	/**
	 * Adds coffee category filter from Yelp API
	 * 
	 * @param req
	 * @return
	 */
	private static final LocationRequest filterCoffeeRequest(LocationRequest req) {
		if (req != null)
			req.setCategoryFilter("coffee");
		return req;
	}

}
