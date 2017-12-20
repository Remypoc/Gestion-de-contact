package mvc.bean.contact;

import domain.Address;
import domain.Company;
import domain.Contact;
import domain.PhoneNumber;
import exception.DAOException;
import mvc.bean.BeanManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import service.ContactService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

@ManagedBean(name = "createOrUpdateContact")
@ViewScoped
public class CreateOrUpdateContactBean extends SpringBeanAutowiringSupport implements Serializable {
	private BeanManager beanManager;
	@Autowired
	private ContactService contactService;

	private Contact contact;
	private boolean companyForm;
	private String numSiret;
	private String companyName;

	public CreateOrUpdateContactBean() {
		companyForm = false;
		numSiret = null;
		companyName = null;
		this.beanManager = beanManager;
		this.contact = new Contact();
		this.contact.setAddress(new Address());
	}

	public boolean isCompanyForm() {
		return companyForm;
	}

	public void setCompanyForm(boolean companyForm) {
		this.companyForm = companyForm;
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

	public String getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(String numSiret) {
		this.numSiret = numSiret;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void reset() {
		this.contact = new Contact();
		this.contact.setAddress(new Address());
		this.companyName = null;
		this.numSiret = null;
		this.companyForm = false;
	}

	public void createCompany() {
		Company company = new Company(contact, companyName, Long.parseLong(numSiret));
		saveContact(company);
		reset();
	}

	public void createContact() {
//		System.out.println("createOrUpdateContactBean => createContact");

		if (contact.getAddress() != null && !contact.getAddress().isValid()) {
			contact.setAddress(null);
		}
		if (companyForm) {
			createCompany();
		}
		else {
			saveContact(contact);
		}
	}

	public void saveContact(Contact contact) {
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
		if (companyForm) {
			Company company = new Company(contact, companyName, Long.parseLong(numSiret));
			saveUpdateContact(company);
		} else {
			saveUpdateContact(contact);
		}
	}

	public void saveUpdateContact(Contact contact) {
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
		if (this.contact instanceof Company) {
			this.companyForm = true;
			this.companyName = contact.getLastName();
			this.numSiret = String.valueOf(((Company) contact).getNumSiret());
		}
	}

	public void switchToCompanyForm() {
		companyForm = true;
	}

	public void switchToContactForm() {
		companyForm = false;
	}
}
