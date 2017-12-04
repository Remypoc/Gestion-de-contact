package mvc.beans.contact;

import domain.Address;
import domain.Contact;
import domain.PhoneNumber;
import service.ContactService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@ManagedBean(name = "contact")
@ViewScoped
public class ContactBean implements Serializable {

    private Contact contact = new Contact();

    public ContactBean() {
    }

    @PostConstruct
    public void init() {

        contact.setAddress(new Address());
        contact.setPhones(new HashSet<>());
    }

    public String getFirstName() {
        return contact.getFirstName();
    }

    public void setFirstName(String firstName) {
        contact.setFirstName(firstName);
    }

    public String getLastName() {
        return contact.getLastName();
    }

    public void setLastName(String lastName) {
        contact.setLastName(lastName);
    }

    public String getEmail() {
        return contact.getEmail();
    }

    public void setEmail(String email) {
        contact.setEmail(email);
    }

    /*ADDRESS*/

    public String getStreet() {
        if (contact.getAddress() != null) {
            return contact.getAddress().getStreet();
        }
        return null;
    }

    public void setStreet(String street) {
        if (contact.getAddress() != null) {
            contact.getAddress().setStreet(street);
        }
    }

    public String getCity() {
        if (contact.getAddress() != null) {
            return contact.getAddress().getCity();
        }
        return null;
    }

    public void setCity(String city) {
        if (contact.getAddress() != null) {
            contact.getAddress().setCity(city);
        }
    }

    public String getZip() {
        if (contact.getAddress() != null) {
            return contact.getAddress().getZip();
        }
        return null;
    }

    public void setZip(String zip) {
        if (contact.getAddress() != null) {
            contact.getAddress().setZip(zip);
        }
    }

    public String getCountry() {
        if (contact.getAddress() != null) {
            return contact.getAddress().getCountry();
        }
        return null;
    }

    public void setCountry(String country) {
        if (contact.getAddress() != null) {
            contact.getAddress().setCountry(country);
        }
    }

    public Set<PhoneNumber> getPhones() {
        return contact.getPhones();
    }

    public void setPhones(Set<PhoneNumber> phoneNumbers) {
        if (phoneNumbers != null)
            contact.setPhones(phoneNumbers);
    }

    public void add() {
        contact.getPhones().add(new PhoneNumber());
    }

    public void remove(PhoneNumber phoneNumber) {
        contact.getPhones().remove(phoneNumber);
    }


    public String validate() {
        System.out.println("Validated contact : " + contact);
        if (!contact.getAddress().isValid()) {
            contact.setAddress(null);
        }
        final ContactService cs = new ContactService();
        final Object lError = cs.addContact(contact);
        return null;
    }
}