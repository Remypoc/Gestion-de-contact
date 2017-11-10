package actionForm.display;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class DisplayContactValidationForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    private long id = 0;
    private String firstName = null;
    private String lastName = null;
    private String email = null;

    // Address
    private long idAddress = 0;
    private String country = null;
    private String city = null;
    private String street = null;
    private String zip = null;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String string) {
        email = string;
    }

    public void setFirstName(String string) {
        firstName = string;
    }

    public void setLastName(String string) {
        lastName = string;
    }

    public long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(long idAddress) {
        this.idAddress = idAddress;
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

    public ActionErrors validate(
            ActionMapping mapping, HttpServletRequest request ) {
        ActionErrors errors = new ActionErrors();

        if(getId() < 1) {
            errors.add("id", new ActionMessage("contact.display.error"));
        }

        return errors;
    }
}
