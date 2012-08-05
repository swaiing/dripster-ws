package swai.location.persistence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

public class CoffeeShopDatastore {

	// logger
	private static final Logger log = Logger
			.getLogger(CoffeeShopDatastore.class.getName());

	// artisan shop data
	private static final String SRC_FILE = "coffeeshops.csv";

	// singleton instance
	private final static CoffeeShopDatastore instance = new CoffeeShopDatastore();

	private CoffeeShopDatastore() {
	}

	public static CoffeeShopDatastore getInstance() {
		return instance;
	}

	public CoffeeShop get(String key) {
		PersistenceManager pm = PMF.getInstance().getPersistenceManager();
		CoffeeShop shop = null;
		try {
			shop = pm.getObjectById(CoffeeShop.class, key);
		} catch (JDOObjectNotFoundException e) {
			log.info("No object in datastore with key:" + key);
		} finally {
			pm.close();
		}
		return shop;
	}

	public Map<String, CoffeeShop> getAll(List<String> keys) {

		if (keys == null || keys.isEmpty()) {
			log.warning("CoffeeShopDatastore.getAll passed null or empty keys");
			return null;
		}

		PersistenceManager pm = PMF.getInstance().getPersistenceManager();
		Map<String, CoffeeShop> shops = new HashMap<String, CoffeeShop>(
				keys.size());
		for (String key : keys) {
			CoffeeShop shop = null;
			try {
				shop = pm.getObjectById(CoffeeShop.class, key);
			} catch (JDOObjectNotFoundException e) {
				log.info("No object in datastore with key:" + key);
			}
			if (shop != null)
				shops.put(key, shop);
		}
		pm.close();
		return shops;
	}

	/**
	 * Build datastore from csv file
	 */
	public void buildFromFile() {

		BufferedReader in = null;
		PersistenceManager pm = PMF.getInstance().getPersistenceManager();
		try {

			in = new BufferedReader(new InputStreamReader(this.getClass()
					.getResourceAsStream(SRC_FILE), "UTF-8"));
			String line = in.readLine(); // skip first line, heading

			while ((line = in.readLine()) != null) {

				List<String> parts = Arrays.asList(line.split(","));
				if (parts != null && parts.size() > 0) {

					String name = grab(0, parts);
					String address = grab(1, parts);
					String city = grab(2, parts);

					// TODO: format phone number
					// but it doesn't matter since I'm not using it
					String phone = grab(3, parts);

					String as = grab(4, parts);
					boolean active = false;
					if (as != null)
						active = "yes".equals(as.toLowerCase());

					String yelpID = grab(5, parts);

					String bts = grab(6, parts);
					Set<String> beanTags = null;
					if (bts != null)
						beanTags = new HashSet<String>(Arrays.asList(bts
								.split(":")));

					String sts = grab(7, parts);
					Set<String> storeTags = null;
					if (sts != null)
						storeTags = new HashSet<String>(Arrays.asList(sts
								.split(":")));

					// only consider if ID and active
					if (yelpID != null && active) {
						CoffeeShop shop = new CoffeeShop(yelpID, name, address,
								city, phone, active, beanTags, storeTags);
						pm.makePersistent(shop);
						log.info("Added CoffeeShop to datastore: "
								+ shop.toString());
					}

				}
			}

			in.close();

		} catch (FileNotFoundException e) {
			log.severe("Fatal exception! Could not find file: " + SRC_FILE);
			e.printStackTrace();
		} catch (IOException e) {
			log.severe("Fatal Exception thrown while reading file");
			e.printStackTrace();
		} catch (Exception e) {
			log.severe("Fatal exception thrown while accessing datastore");
			e.printStackTrace();
		} finally {
			pm.close();
		}
	}

	/**
	 * Helper that checks array size before access
	 * 
	 * @param i
	 * @param l
	 * @return
	 */
	private final static String grab(int i, List<String> l) {
		if (l != null && l.size() >= i + 1)
			return l.get(i);
		return null;
	}

}
