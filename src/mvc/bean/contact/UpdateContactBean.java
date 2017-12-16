package mvc.bean.contact;

import domain.Address;
import domain.Contact;
import domain.PhoneNumber;
import exception.DAOException;
import mvc.bean.BeanManager;
import service.ContactService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

@ManagedBean(name="updateContact")
@ViewScoped
public class UpdateContactBean implements Serializable {
    private BeanManager beanManager;

    private Contact contact;

    public UpdateContactBean() {
        this.beanManager = beanManager;
        this.contact = null;
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

    public void save() {
        if (contact.getAddress() != null && !contact.getAddress().isValid()) {
            contact.setAddress(null);
        }
        final ContactService service = new ContactService();
        try {
            final Object lError = service.updateContact(contact);
        } catch (DAOException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
            context.addMessage(null, new FacesMessage(text.getString(e.getMessageBundleName())));
        }
        beanManager.notifyUpdateContact(contact);
        reset();
    }

    public void cancel() {
        this.reset();
        this.beanManager.notifyUpdateContact(null);
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
