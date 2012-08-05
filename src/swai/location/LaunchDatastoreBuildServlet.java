package swai.location;

import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;

import swai.location.persistence.CoffeeShopDatastore;

public class LaunchDatastoreBuildServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	// logger
	private static final Logger log = Logger
			.getLogger(LaunchDatastoreBuildServlet.class.getName());

	// datastore instance
	private static final CoffeeShopDatastore csd = CoffeeShopDatastore
			.getInstance();

	public void init() {
		log.info("----> Starting build of datastore from a file!");
		csd.buildFromFile();
		log.info("----> Finished build of datastore from a file!");
		
	}
	
}