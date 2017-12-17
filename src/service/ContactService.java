package service;

import domain.Contact;

import java.util.Set;

public interface ContactService {
    Object addContact(Contact contact);
    Object updateContact(Contact contact);
    Object deleteContact(Long contactId);
    Set<Contact> loadContacts();
    Object loadContact(Long id);
}
