package mvc.bean.contact;

import domain.Contact;
import mvc.bean.BeanManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import service.ContactService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;


@ManagedBean(name="deleteContact")
@ViewScoped
public class DeleteContactBean extends SpringBeanAutowiringSupport implements Serializable {
    private BeanManager beanManager;
    @Autowired
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
