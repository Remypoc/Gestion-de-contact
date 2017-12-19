package service;

import dao.ContactDAO;
import dao.ContactDAOImpl;
import domain.*;
import exception.DAOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ContactServiceImpl implements ContactService {
	private ContactDAO contactDAO;

	public void setContactDAO(ContactDAO contactDAO) {
		this.contactDAO = contactDAO;
	}

	public Object addContact(Contact contact) throws DAOException {
		return contactDAO.addContact(contact);
	}

	public Object updateContact(Contact contact) throws DAOException {
		return contactDAO.updateContact(contact);
	}

	public Object deleteContact(Long contactId) throws DAOException {
		return contactDAO.deleteContact(contactId);
	}

	public Set<Contact> loadContacts() throws DAOException {
		return contactDAO.loadContacts();
	}

	public Object loadContact(Long id) throws DAOException {
		return contactDAO.loadContact(id);
	}

	@Override
	public void refreshContact(Contact contact) {
		contactDAO.refreshContact(contact);
	}

}
