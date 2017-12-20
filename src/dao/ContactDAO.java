package dao;

import domain.Company;
import domain.Contact;
import domain.PhoneNumber;
import exception.DAOException;

import java.util.Set;

public interface ContactDAO {
    String addContact(Contact contact) throws DAOException;
    Object updateContact(final Contact contact) throws DAOException;
    Object deleteContact(final Long id) throws DAOException;
    Object addPhoneNumber(final PhoneNumber phoneNumber) throws DAOException;
    Set<Contact> loadContacts() throws DAOException;
    Object loadContacts(String search) throws DAOException;
    Object loadContact(Long id) throws DAOException;
    void refreshContact(Contact contact);
}
