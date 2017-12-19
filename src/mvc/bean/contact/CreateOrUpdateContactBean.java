package mvc.bean.contact;

import domain.Address;
import domain.Contact;
import domain.PhoneNumber;
import exception.DAOException;
import mvc.bean.BeanManager;
import mvc.bean.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import service.ContactService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

@ManagedBean(name = "createOrUpdateContact")
@ViewScoped
public class CreateOrUpdateContactBean extends SpringBeanAutowiringSupport implements Serializable {
	private BeanManager beanManager;
	@Autowired
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
		try {
			final Object lError = contactService.addContact(contact);
			beanManager.notifyCreateContact(contact);
		} catch (DAOException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
//			context.addMessage(null, new FacesMessage(text.getString(e.getMessageBundleName())));
			beanManager.addError(text.getString(e.getMessageBundleName()));
		}
		reset();
	}

	public void updateContact() {
		try {
			final Object lError = contactService.updateContact(contact);
			beanManager.notifyUpdateContact(contact);
		} catch (DAOException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
//			context.addMessage(null, new FacesMessage(text.getString(e.getMessageBundleName())));
			beanManager.addError(text.getString(e.getMessageBundleName()));
		}
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
		contact.removePhoneNumber(phoneNumber);
	}

	public void loadContact(Contact contact) {
		this.contact = contact;
		if (this.contact.getAddress() == null)
			this.contact.setAddress(new Address());
		if (this.contact.getPhones() == null)
			this.contact.setPhones(new LinkedHashSet<>());
	}
}
