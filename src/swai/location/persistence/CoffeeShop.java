package swai.location.persistence;

import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class CoffeeShop {
	
	// yelp ID
    @PrimaryKey
    private String key;
    
    @Persistent
    private String name;
    
    @Persistent
    private String address;
    
    @Persistent
    private String city;
    
    @Persistent
    private String phone;
    
    @Persistent
    private boolean active;
    
    @Persistent
    private Set<String> beanTags;
    
    @Persistent
    private Set<String> storeTags;

	public CoffeeShop(String key, String name, String address, String city,
			String phone, boolean active, Set<String> beanTags,
			Set<String> storeTags) {
		super();
		this.key = key;
		this.name = name;
		this.address = address;
		this.city = city;
		this.phone = phone;
		this.active = active;
		this.beanTags = beanTags;
		this.storeTags = storeTags;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<String> getBeanTags() {
		return beanTags;
	}

	public void setBeanTags(Set<String> beanTags) {
		this.beanTags = beanTags;
	}

	public Set<String> getStoreTags() {
		return storeTags;
	}

	public void setStoreTags(Set<String> storeTags) {
		this.storeTags = storeTags;
	}

	@Override
	public String toString() {
		return "CoffeeShop [key=" + key + ", name=" + name + ", address="
				+ address + ", city=" + city + ", phone=" + phone + ", active="
				+ active + ", beanTags=" + beanTags + ", storeTags="
				+ storeTags + "]";
	}
    
}
