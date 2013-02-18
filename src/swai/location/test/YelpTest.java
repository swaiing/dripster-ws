package swai.location.test;

import swai.location.data.LocationRequest;
import swai.location.data.LocationResponse;
import swai.location.service.impl.YelpService;

public class YelpTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting YelpTest");
		System.out.println("--------------");

		// Create request
		String term = "coffee";
		
		// somerville
		//double lat = 42.393865;
		//double lg = -71.120693;
		
		// hanover, nh
		//double lat = 43.70535;
		//double lg = -72.70535;
		
		// sloan
		double lat = 42.36051;
		double lg = -71.083928;
		
		LocationRequest req = new LocationRequest();
		req.setLatitude(lat);
		req.setLongitude(lg);
		// req.setTerm(term);
		req.setCategoryFilter(term);

		// Run request
		YelpService ys = new YelpService();
		LocationResponse res = ys.list(req);
		

		System.out.println(res.toString());

		System.out.println("Ending YelpTest");
	}

}
