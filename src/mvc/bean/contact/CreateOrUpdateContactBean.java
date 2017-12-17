package mvc.bean.contact;

import domain.Address;
import domain.Contact;
import domain.PhoneNumber;
import mvc.bean.BeanManager;
import service.ContactService;

import java.io.Serializable;
import java.util.LinkedHashSet;

//@ManagedBean(name="createOrUpdateContact")
//@ViewScoped
public class CreateOrUpdateContactBean implements Serializable {
    private BeanManager beanManager;
//    @ManagedProperty(value = "#{contactService}")
    private ContactService contactService;

    private Contact contact;

    public CreateOrUpdateContactBean() {
        this.beanManager = beanManager;
        this.contact = new Contact();
        this.contact.setAddress(new Address());
    }

    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
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
        this.contact = new Contact();
        this.contact.setAddress(new Address());
    }

    public void createContact() {
        System.out.println("createOrUpdateContactBean => createContact");
        if (contact == null)
            System.out.println("Contact is null");
        if (contact.getAddress() != null && !contact.getAddress().isValid()) {
            contact.setAddress(null);
        }
        final Object lError = contactService.addContact(contact);
        beanManager.notifyCreateContact(contact);
        reset();
    }

    public void updateContact() {
        if (contact.getAddress() != null && !contact.getAddress().isValid()) {
            contact.setAddress(null);
        }
        final Object lError = contactService.updateContact(contact);
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
