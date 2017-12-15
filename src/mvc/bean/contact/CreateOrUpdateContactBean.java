package mvc.bean.contact;

import domain.Address;
import domain.Contact;
import domain.PhoneNumber;
import mvc.bean.BeanManager;
import service.ContactService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.LinkedHashSet;

@ManagedBean(name="createOrUpdateContact")
@ViewScoped
public class CreateOrUpdateContactBean implements Serializable {
    private BeanManager beanManager;

    private Contact contact;
    private String firtName;
    private String lastName;
    private String email;

    public String getFirtName() {
        return firtName;
    }

    public void setFirtName(String firtName) {
        this.firtName = firtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CreateOrUpdateContactBean() {
        this.beanManager = beanManager;
        this.contact = new Contact();
        this.contact.setAddress(new Address());
    }

    public void setBeanManager(BeanManager beanManager) {
        this.beanManager = beanManager;
        this.contact = new Contact();
        this.contact.setAddress(new Address());
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void reset() {
        this.contact = null;
        this.firtName = null;
        this.lastName = null;
        this.email = null;
    }

    public void createContact() {
        System.out.println("createOrUpdateContactBean => createContact");
        if (contact == null)
            System.out.println("Contact is null");
        if (contact.getAddress() != null && !contact.getAddress().isValid()) {
            contact.setAddress(null);
        }
        contact.setFirstName(firtName);
        contact.setLastName(lastName);
        contact.setEmail(email);
        final ContactService service = new ContactService();
        final Object lError = service.addContact(contact);
        beanManager.notifyCreateContact(contact);
        reset();
    }

    public void updateContact() {
        if (contact.getAddress() != null && !contact.getAddress().isValid()) {
            contact.setAddress(null);
        }
        final ContactService service = new ContactService();
        final Object lError = service.updateContact(contact);
        beanManager.notifyUpdateContact(contact);
        reset();
    }

    public void cancelUpdateContact() {
        this.reset();
        this.beanManager.notifyUpdateContact(null);
    }

    public void cancelCreateContact() {
        this.reset();
        this.beanManager.notifyCreateContact(null);
    }

    public void addPhone() {
        if (contact.getPhones().size() < 5) {
            contact.addPhoneNumber(new PhoneNumber(this.contact));
        }
        System.out.println(String.format("Number of phones: %d", contact.getPhones().size()));
    }

    public void remove(PhoneNumber phoneNumber) {
        System.out.println(phoneNumber);
        contact.removePhoneNumber(phoneNumber);
        System.out.println(contact.getPhones().size());
    }

    public void loadContact(Contact contact) {
        this.contact = new Contact();
        this.contact.copy(contact);
        if (this.contact.getAddress() == null) {
            this.contact.setAddress(new Address());
        }
        if (this.contact.getPhones() == null) {
            this.contact.setPhones(new LinkedHashSet<>());
        }
    }
}
