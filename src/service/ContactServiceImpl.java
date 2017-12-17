package service;

import dao.ContactDAO;
import dao.ContactDAOImpl;
import domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ContactServiceImpl implements ContactService {
	private ContactDAO contactDAO;

	public void setContactDAO(ContactDAO contactDAO) {
		this.contactDAO = contactDAO;
	}

	public Object addContact(Contact contact) {
		return contactDAO.addContact(contact);
	}

	public Object updateContact(Contact contact) {
		return contactDAO.updateContact(contact);
	}

	public Object deleteContact(Long contactId) {
		return contactDAO.deleteContact(contactId);
	}

    public Set<Contact> loadContacts() {
        return contactDAO.loadContacts();
    }

    public Object loadContact(Long id) {
        return contactDAO.loadContact(id);
    }

}
