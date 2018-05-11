package service;

import domain.Contact;
import exception.DAOException;

import java.util.Set;

public interface ContactService {
    Object addContact(Contact contact) throws DAOException;
    Object updateContact(Contact contact) throws DAOException;
    Object deleteContact(Long contactId) throws DAOException;
    Set<Contact> loadContacts() throws DAOException;
    Object loadContact(Long id) throws DAOException;
    void refreshContact(Contact contact);
}
