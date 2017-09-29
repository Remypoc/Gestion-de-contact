package domain;

import javax.persistence.Entity;

@Entity(name = "Address")
public class Address {
	private long id;
	private String street;
	private String city;
	private String zip;
	private String country;
	
	public Address() {
	}
	
	public Address(long id, String country, String city, String street, String zip) {
		this.id = id;
		this.country = country;
		this.city = city;
		this.street = street;
		this.zip = zip;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Address {\n").append("id=").append(id).append("\n");
		sb.append("country=").append(country).append("\n");
		sb.append("city=").append(city).append("\n");
		sb.append("street=").append(street).append("\n");
		sb.append("zip=").append(zip).append("\n");
		sb.append("}\n");
		return sb.toString();
	}
}
