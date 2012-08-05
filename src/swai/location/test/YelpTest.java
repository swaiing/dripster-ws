package swai.location.test;

import swai.location.data.LocationRequest;
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
		double lat = 42.393865;
		double lg = -71.120693;
		LocationRequest req = new LocationRequest();
		req.setLatitude(lat);
		req.setLongitude(lg);
		req.setTerm(term);
		
		// Run request
		YelpService ys = new YelpService();
		ys.list(req);
		
		System.out.println("Ending YelpTest");
	}

}
