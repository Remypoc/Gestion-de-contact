package mvc.beans.contact;

import domain.Address;
import domain.Contact;
import domain.PhoneNumber;
import service.ContactService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;

@ManagedBean(name = "contactManager")
@ViewScoped
public class ContactManagerBean implements Serializable {

    private List<Contact> contacts;
    private transient DataModel<Contact> model;
    private Contact contact = new Contact();
    private Address address = new Address();
    private LinkedHashSet<PhoneNumber> phones = new LinkedHashSet<>();
    private boolean edit;

    @PostConstruct
    public void init() {
        contact.setAddress(address);
        contact.setPhones(phones);
        final ContactService cs = new ContactService();
        contacts = cs.loadContacts();
    }

    // Reset placeholder.
    public void reset() {
        contact = new Contact();
        address = new Address();
        phones = new LinkedHashSet<>();
        contact.setAddress(address);
        contact.setPhones(phones);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void add() {
        if (!contact.getAddress().isValid()) {
            contact.setAddress(null);
        }
        final ContactService cs = new ContactService();
        final Object lError = cs.addContact(contact);
        contacts.add(contact);
        reset();
    }

    public void setModel(DataModel<Contact> model) {
        this.model = model;
    }

    public DataModel<Contact> getModel() {
        if (model == null) {
            model = new ListDataModel<>(contacts);
        }
        return model;
    }

    public void edit() {
        final ContactService cs = new ContactService();
        final Object lError = cs.loadContact(model.getRowData().getId());
        if (lError instanceof Contact) {
            contact = (Contact) lError;
        }
        if (contact.getAddress() == null) {
            contact.setAddress(address);
        }
        if (contact.getPhones() == null) {
            contact.setPhones(phones);
        }

        edit = true;
    }

    public String save() {
        if (contact.getAddress() != null && !contact.getAddress().isValid()) {
            contact.setAddress(null);
        }
        final ContactService cs = new ContactService();
        final Object lError = cs.updateContact(contact);
        reset();
        edit = false;
        return "home";
    }

    public void delete() {
        final ContactService cs = new ContactService();
        final Object lError = cs.deleteContact(model.getRowData().getId());
        contacts.remove(model.getRowData());
        edit = false;
        reset();
    }

    public Contact getItem() {
        return contact;
    }

    public boolean isEdit() {
        return edit;
    }

    public void cancel() {
        reset();
        edit = false;
    }

    public void addPhone() {
        if (contact.getPhones().size() < 100) {
            contact.getPhones().add(new PhoneNumber());
        }
    }

    public void remove(PhoneNumber phoneNumber) {
        contact.getPhones().remove(phoneNumber);
    }
}