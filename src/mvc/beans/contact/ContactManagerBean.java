package mvc.beans.contact;

import domain.Contact;
import service.ContactService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "contactManager")
@ViewScoped
public class ContactManagerBean implements Serializable{

    private List<Contact> contacts;
    private transient DataModel<Contact> model;

    @PostConstruct
    public void init() {
        final ContactService cs = new ContactService();
        contacts = cs.loadContacts();
        System.out.println("Display contacts : " + contacts);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public DataModel<Contact> getModel() {
        if (model == null) {
            model = new ListDataModel<>(contacts);
        }
        return model;
    }
}
