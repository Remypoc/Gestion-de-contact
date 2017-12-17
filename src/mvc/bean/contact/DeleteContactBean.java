package mvc.bean.contact;

import domain.Contact;
import mvc.bean.BeanManager;
import service.ContactService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


//@ManagedBean(name="deleteContact")
//@ViewScoped
public class DeleteContactBean {
    private BeanManager beanManager;
//    @ManagedProperty(value = "#{contactService}")
    private ContactService contactService;

    private long contactId;

    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    public DeleteContactBean() {
        this.contactId = 0;
    }

    public BeanManager getBeanManager() {
        return beanManager;
    }

    public void setBeanManager(BeanManager beanManager) {
        this.beanManager = beanManager;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public void reset() {
        this.contactId = 0;
    }

    public void deleteContact(long contactId) {
        contactService.deleteContact(contactId);
        beanManager.notifyDeleteContact(new Contact(contactId));
        reset();
    }
}
