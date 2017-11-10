package actionForm.update;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class UpdateContactValidationForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    // Contact
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
    private String hasAddress = "false";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return First Name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return Last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param string Sets the Email
     */
    public void setEmail(String string) {
        email = string;
    }

    /**
     * @param string Sets the First Name
     */
    public void setFirstName(String string) {
        firstName = string;
    }

    /**
     * @param string sets the Last Name
     */
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

    public String getHasAddress() {
        return hasAddress;
    }

    public void setHasAddress(String hasAddress) {
        this.hasAddress = hasAddress;
    }

    @SuppressWarnings("Duplicates")
    public ActionErrors validate(
            ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        if (getId() < 1) {
            errors.add("id", new ActionMessage("contact.display.error"));
        }
        if (getFirstName() == null || getFirstName().length() < 1) {
            errors.add("firstName", new ActionMessage("contact.creation.fn.error.required"));
        }
        if (getLastName() == null || getLastName().length() < 1) {
            errors.add("lastName", new ActionMessage("contact.creation.ln.error.required"));
        }
        if (getEmail().trim().length() < 1) {
            errors.add("email", new ActionMessage("creation.email.error.required"));
        }
        if (!getEmail().matches(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            errors.add("email", new ActionMessage("creation.email.error.syntax"));
        }
        if (getHasAddress().equals("true")) {
            if (getCountry() == null || getCountry().length() < 1) {
                errors.add("country ", new ActionMessage("address.country.error"));
            }
            if (getCity() == null || getCity().length() < 1) {
                errors.add("city ", new ActionMessage("address.city.error"));
            }
            if (getStreet() == null || getStreet().length() < 1) {
                errors.add("street ", new ActionMessage("address.street.error"));
            }
            if (getZip() == null || getZip().length() < 1) {
                errors.add("zip ", new ActionMessage("address.zip.error"));
            }
        }

        return errors;
    }
}
