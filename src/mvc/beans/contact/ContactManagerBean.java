package mvc.beans.contact;

import domain.Address;
import domain.Contact;
import domain.PhoneNumber;
import service.ContactService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.OptimisticLockException;
import java.io.Serializable;
import java.util.*;

@ManagedBean(name = "contactManager")
@ViewScoped
public class ContactManagerBean implements Serializable {

    private List<Contact> contacts = new ArrayList<>();
    private List<Contact> contactsSearched = new ArrayList<>();

    private transient DataModel<Contact> model;
    private transient DataModel<Contact> modelSearch;

    private Contact contact = new Contact();
    private Address address = new Address();
    private Set<PhoneNumber> phones = new LinkedHashSet<>();

    private boolean edit;
    private boolean search;

    @PostConstruct
    public void init() {
        contact.setAddress(address);
        contact.setPhones(phones);
        final ContactService cs = new ContactService();
        contacts = cs.loadContacts();
    }

    // Reset placeholder
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

    public List<Contact> getContactsSearched() {
        return contactsSearched;
    }

    public void setContactsSearched(List<Contact> contactsSearched) {
        this.contactsSearched = contactsSearched;
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

    public void setModelSearch(DataModel<Contact> modelSearch) {
        this.modelSearch = modelSearch;
    }

    public DataModel<Contact> getModelSearch() {
        if (modelSearch == null) {
            modelSearch = new ListDataModel<>(contactsSearched);
        }
        return modelSearch;
    }

    public Contact getItem() {
        return contact;
    }

    public boolean isEdit() {
        return edit;
    }

    public Boolean getSearch() {
        return search;
    }

    public void edit() {
        final ContactService cs = new ContactService();

        Object lError;
        if (!search) {
            lError = cs.loadContact(model.getRowData().getId());
        } else {
            lError = cs.loadContact(modelSearch.getRowData().getId());
        }

        if (lError instanceof Contact) {
            contact = (Contact) lError;
            if (contact.getAddress() == null) {
                contact.setAddress(address);
            }
            if (contact.getPhones() == null) {
                contact.setPhones(phones);
            }
        }
        edit = true;
    }

    public String save() {
        final ContactService cs = new ContactService();
        try {
            final Object lError = cs.updateContact(contact);
        } catch (OptimisticLockException e) {
            e.printStackTrace();
        }
        reset();
        edit = false;
        return "home";
    }

    public void delete() {
        final ContactService cs = new ContactService();
        Object lError;
        if (!search) {
            lError = cs.deleteContact(model.getRowData().getId());
            contacts.remove(model.getRowData());
        } else {
            lError = cs.deleteContact(modelSearch.getRowData().getId());
            Contact c = modelSearch.getRowData();
            contacts.remove(c);
            contactsSearched.remove(c);
        }
        edit = false;
        reset();
    }

    // Add new contact
    public void add() {
        if (!contact.getAddress().isValid()) {
            contact.setAddress(null);
        }
        final ContactService cs = new ContactService();
        final Object lError = cs.addContact(contact);
        contacts.add(contact);
        reset();
    }

    // Add new phone fields
    public void addPhone() {
        if (contact.getPhones().size() < 100) {
            contact.getPhones().add(new PhoneNumber());
        }
    }

    // Remove phone
    public void remove(PhoneNumber phoneNumber) {
        contact.getPhones().remove(phoneNumber);
    }

    // Cancel and reset form edit
    public void cancel() {
        reset();
        edit = false;
    }

    public void search() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String searchInput = ec.getRequestParameterMap().get("search:searchInput");
        reset();
        edit = false;
        if (searchInput != null && !searchInput.trim().isEmpty()) {
            initSearch(searchInput);
        } else {
            search = false;
        }
    }

    public void initSearch(String searchInput) {
        contactsSearched.clear();
        searchInput = searchInput.toLowerCase().trim();
        for(Contact contact : contacts) {
            if (contact.getLastName().toLowerCase().trim().contains(searchInput)
                    || contact.getFirstName().toLowerCase().trim().contains(searchInput)
                    || contact.getEmail().toLowerCase().trim().contains(searchInput)) {
                contactsSearched.add(contact);
            }
        }
        search = true;
    }

    public void cancelSearch() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String searchInput = ec.getRequestParameterMap().get("search:searchInput");
        search = false;
    }
}
