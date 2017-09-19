package domain;

public class Address {
	private int id;
	private String street;
	private String city;
	private String zip;
	private String country;
	
	public Address() {
	}
	
	public Address(int id, String street, String city, String zip, String country) {
		this.id = id;
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
		sb.append("Address:\n").append("id=").append(id);
		sb.append("street=").append(street).append("\n");
		sb.append("city=").append(city).append("\n");
		sb.append("zip=").append(zip).append("\n");
		sb.append("country=").append(country).append("\n");
		return sb.toString();
	}
}
