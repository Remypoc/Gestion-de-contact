package actionForm.update;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import service.ContactService;

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

    // Phone
    private long idPhone = 0;
    private String phoneKind = null;
    private String phoneNumber = null;
    private String hasPhone = "false";

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

    /**
     * @return ID Returns ID
     */
    public long getId() {
        return id;
    }

    /**
     * @param l Sets the ID
     */
    public void setId(long l) {
        id = l;
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

    public long getIdPhone() {
        return idPhone;
    }

    public void setIdPhone(long idPhone) {
        this.idPhone = idPhone;
    }

    public String getPhoneKind() {
        return phoneKind;
    }

    public void setPhoneKind(String phoneKind) {
        this.phoneKind = phoneKind;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHasPhone() {
        return hasPhone;
    }

    public void setHasPhone(String hasPhone) {
        this.hasPhone = hasPhone;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.id = 0;
        this.firstName = null;
        this.lastName = null;
        this.email = null;

        this.hasAddress = "false";
        this.idAddress = 0;
        this.country = null;
        this.city = null;
        this.street = null;
        this.zip = null;

        this.hasPhone = "false";
        this.idPhone = 0;
        this.phoneKind = null;
        this.phoneNumber = null;
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

        ActionErrors errors = new ActionErrors();

        System.out.println("UpdateContactValidationForm " + getId());

        System.out.println("hasAddress" + getHasAddress() + getCity());

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

        if (getHasPhone().equals("true")) {
            if (getPhoneNumber() == null || getPhoneNumber().length() < 2) {
                errors.add("phonenumber ", new ActionMessage("phonenumber.phonenumber.error"));
            }
            if (getPhoneKind() == null || getPhoneKind().length() < 1) {
                errors.add("phonekind", new ActionMessage("phonenumber.phonekind.error"));
            }
        }

        final ContactService cs = new ContactService();
        final Object contact = cs.loadContact(id);

        request.setAttribute("contact", contact);

        return errors;
    }
}
