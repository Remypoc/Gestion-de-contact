package actionForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class SearchAddressValidationForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private long id = 0;   
	private String country 	= null;
	private String city 	= null;
	private String street 	= null;
	private String zip 		= null;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	  	this.id = 0;
	    this.country	= null;
	    this.city 		= null;
	    this.street 	= null;
	    this.zip 		= null;
	}

	public ActionErrors validate( 
			ActionMapping mapping, HttpServletRequest request ) {
			ActionErrors errors = new ActionErrors();
			
			// At least one field must not be empty
			if((getCountry() == null || getCountry().length() < 1) &&
					(getCity()== null || getCity().length() < 1) && 
					(getStreet() == null || getStreet().length() < 1) &&
					(getZip() == null || getZip().length() < 1)) {
				errors.add("address ", new ActionMessage("address.search.error"));
			}
				
			return errors;
	}
}
