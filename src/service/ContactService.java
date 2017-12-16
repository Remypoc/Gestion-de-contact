package service;

import domain.*;
import exception.DAOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ContactService {
	
	@SuppressWarnings("unused")
	private DAOContact cdao;

	public Object addContact(Contact contact) {
		return (cdao = new DAOContact()).addContact(contact);
	}

	public Object updateContact(Contact contact) throws DAOException {
		return (cdao = new DAOContact()).updateContact(contact);
	}

	public Object deleteContact(Long contactId) {
		return (cdao = new DAOContact()).deleteContact(contactId);
	}

	//TODO TMP remove
	public List<Contact> loadContactsList() {
		return new ArrayList<>((cdao = new DAOContact()).loadContacts());
	}

    public Set<Contact> loadContacts() {
        return (cdao = new DAOContact()).loadContacts();
    }

    public Object loadContact(Long id) {
        return (cdao = new DAOContact()).loadContact(id);
    }

}
