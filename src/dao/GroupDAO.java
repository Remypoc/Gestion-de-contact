package dao;

import domain.Contact;
import domain.ContactGroup;
import exception.DAOException;

import java.util.List;
import java.util.Set;

public interface GroupDAO {
    void addGroup(ContactGroup group) throws DAOException;
    void updateGroup(ContactGroup group) throws DAOException;
    ContactGroup getGroup(long id) throws DAOException;
    ContactGroup getGroupWithContacts(long id) throws DAOException;
    Set<ContactGroup> getAllGroups() throws DAOException;
    void deleteGroup(long id) throws DAOException;
    void deleteContactFromGroup(long contactId, long groupId) throws DAOException;
    Set<ContactGroup> searchGroups(String groupName) throws DAOException;
    ContactGroup searchContactInGroup(final long groupId, final String name) throws DAOException;
    Set<Contact> getContactsNotInSet(Set<Contact> contacts) throws DAOException;
    void addContactToGroup(long contactId, long groupId) throws DAOException;
}
