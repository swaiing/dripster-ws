package swai.location.service.api;

import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class LocationServiceFactory implements ApplicationContextAware {

	// service constants
	public static final String YELP_SERVICE = "YelpService";
	public static final String COFFEE_SERVICE = "CoffeeService";
	
	private static final LocationServiceFactory instance = new LocationServiceFactory();

	private static ApplicationContext context = null;

	// logger
	private static final Logger log = Logger.getLogger(LocationServiceFactory.class
			.getName());

	private LocationServiceFactory() {
	}

	public static LocationServiceFactory getInstance() {
		return instance;
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	/**
	 * @param service
	 * @return
	 * @throws Exception
	 */
	public LocationService getService(String service) {
		if (context != null)
			return (LocationService) context.getBean(service);
		else {
			log.severe("ApplicationContext was not set");
			return null;
		}
	}

}