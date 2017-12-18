package service;

import domain.Contact;
import exception.DAOException;

import java.util.Set;

public interface ContactService {
    Object addContact(Contact contact);
    Object updateContact(Contact contact) throws DAOException;
    Object deleteContact(Long contactId);
    Set<Contact> loadContacts();
    Object loadContact(Long id);
    void refreshContact(Contact contact);
}
