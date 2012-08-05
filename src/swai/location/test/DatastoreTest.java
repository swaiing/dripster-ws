package swai.location.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import swai.location.persistence.CoffeeShop;
import swai.location.persistence.CoffeeShopDatastore;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class DatastoreTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	@Test
	public void testInsert1() {
		doTest();
	}

	/**
	 * @param args
	 */
	public static void doTest() {

		CoffeeShopDatastore csd = CoffeeShopDatastore.getInstance();
		System.out.println("--> CoffeeShopDatastore.build");
		csd.buildFromFile();

		System.out.println("--> CoffeeShopDatastore.get");
		CoffeeShop shop = csd.get("black-brick-brooklyn");
		System.out.println(shop.toString());

		shop = csd.get("café-grumpy-brooklyn-4");
		System.out.println(shop.toString());

		shop = csd.get("diesel-cafe-somerville");
		System.out.println(shop.toString());
		
		shop = csd.get("fort-defiance-brooklyn");
		System.out.println(shop.toString());
	}
}
