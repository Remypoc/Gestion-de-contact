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
import java.util.Set;

@ManagedBean(name="updateContact")
@ViewScoped
public class UpdateContactBean implements Serializable {
    private BeanManager beanManager;

    private Contact contact = new Contact();
//    private Address address = new Address();
//    private Set<PhoneNumber> phones;

    public UpdateContactBean() {
        this.beanManager = beanManager;
        this.contact = null;
//        this.address = null;
//        this.phones = new LinkedHashSet<>();
    }

    public void setBeanManager(BeanManager beanManager) {
        this.beanManager = beanManager;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void reset() {
        this.contact = null;
    }

//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
//
//    public Set<PhoneNumber> getPhones() {
//        return phones;
//    }
//
//    public void setPhones(Set<PhoneNumber> phones) {
//        this.phones = phones;
//    }

    public void add() {
        if (!contact.getAddress().isValid()) {
            contact.setAddress(null);
        }
        final ContactService cs = new ContactService();
        final Object lError = cs.addContact(contact);
//        contacts.add(contact);
//        reset();
    }

    public void save() {
        if (contact.getAddress() != null && !contact.getAddress().isValid()) {
            contact.setAddress(null);
        }
        final ContactService cs = new ContactService();
        final Object lError = cs.updateContact(contact);
        reset();
        beanManager.notifyUpdateContact(contact);
    }

    public void cancel() {
        this.reset();
        this.beanManager.notifyUpdateContact(null);
    }

    public void addPhone() {
        if (contact.getPhones().size() < 100) {
            contact.getPhones().add(new PhoneNumber());
        }
    }

    public void remove(PhoneNumber phoneNumber) {
        contact.getPhones().remove(phoneNumber);
    }

    public void loadContact(Contact contact) {
        this.contact = contact;
    }
}
