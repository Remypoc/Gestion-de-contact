package mvc.bean;

import domain.Contact;
import domain.ContactGroup;
import exception.DAOException;
import service.ContactService;
import service.GroupService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.Set;

public class DataLoader implements Serializable {

	private ContactService contactService;
	private GroupService groupService;

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public Set<ContactGroup> loadGroups() {
		System.out.println("DataLoader => load Groups");
		Set<ContactGroup> groups;
		try {
			groups = groupService.getAllGroups();
			return groups;
		} catch (DAOException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
			context.addMessage(null, new FacesMessage(text.getString(e.getMessageBundleName())));
		}
		return null;
	}

	public Set<Contact> loadContacts() {
		System.out.println("DataLoader => load Contacts");
		try {
			Set<Contact> contacts = contactService.loadContacts();
			return contacts;
		} catch (DAOException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
			context.addMessage(null, new FacesMessage(text.getString(e.getMessageBundleName())));
		}
		return null;
	}

	public ContactGroup loadGroup(long groupId) {
		System.out.println("DataLoader => load Group");
		ContactGroup group = null;
		try {
			group = groupService.getGroupWithContacts(groupId);
			return group;
		} catch (DAOException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
			context.addMessage(null, new FacesMessage(text.getString(e.getMessageBundleName())));
		}
		return null;
	}

	public Contact loadContact(long contactId) {
		System.out.println("DataLoader => load Contact");
		try {
			return (Contact) contactService.loadContact(contactId);
		} catch (DAOException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
			context.addMessage(null, new FacesMessage(text.getString(e.getMessageBundleName())));
		}
		return null;
	}

	public void refreshContact(Contact contact) {
		contactService.refreshContact(contact);
	}
}
