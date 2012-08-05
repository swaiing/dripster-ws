package swai.location.data;

import java.util.List;
import java.util.Set;

public class ArtisanCoffeeShop extends Location {
	
	private final boolean artisan = true;

	private final Set<String> storeTags;

	private final Set<String> beanTags;

	/**
	 * @param latitude
	 * @param longitude
	 * @param id
	 * @param name
	 * @param claimed
	 * @param closed
	 * @param mobileUrl
	 * @param url
	 * @param imageUrl
	 * @param phone
	 * @param displayPhone
	 * @param distance
	 * @param reviewCount
	 * @param rating
	 * @param ratingImgUrl
	 * @param ratingImgUrlSmall
	 * @param ratingImgUrlLarge
	 * @param snippetText
	 * @param snippetImageUrl
	 * @param address
	 * @param displayAddress
	 * @param city
	 * @param stateCode
	 * @param postalCode
	 * @param crossStreets
	 * @param neighborhoods
	 * @param geoAccuracy
	 * @param storeTags
	 * @param beanTags
	 */
	public ArtisanCoffeeShop(double latitude, double longitude, String id,
			String name, boolean claimed, boolean closed, String mobileUrl,
			String url, String imageUrl, String phone, String displayPhone,
			double distance, int reviewCount, double rating,
			String ratingImgUrl, String ratingImgUrlSmall,
			String ratingImgUrlLarge, String snippetText,
			String snippetImageUrl, List<String> address,
			List<String> displayAddress, String city, String stateCode,
			String postalCode, String crossStreets, List<String> neighborhoods,
			int geoAccuracy, Set<String> storeTags, Set<String> beanTags) {
		super(latitude, longitude, id, name, claimed, closed, mobileUrl, url,
				imageUrl, phone, displayPhone, distance, reviewCount, rating,
				ratingImgUrl, ratingImgUrlSmall, ratingImgUrlLarge,
				snippetText, snippetImageUrl, address, displayAddress, city,
				stateCode, postalCode, crossStreets, neighborhoods, geoAccuracy);
		this.storeTags = storeTags;
		this.beanTags = beanTags;
	}

	public boolean isArtisan() {
		return artisan;
	}

	public Set<String> getStoreTags() {
		return storeTags;
	}

	public Set<String> getBeanTags() {
		return beanTags;
	}

}
