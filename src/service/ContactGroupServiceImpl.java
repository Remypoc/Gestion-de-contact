package service;

import dao.ContactGroupDAO;
import dao.ContactGroupDAOImpl;
import domain.Contact;
import domain.ContactGroup;

import java.util.List;

public class ContactGroupServiceImpl implements ContactGroupService {
    private ContactGroupDAO dao = new ContactGroupDAOImpl();

    @Override
    public void add(ContactGroup group) {
        dao.add(group);
    }

    @Override
    public void update(ContactGroup group) {
        dao.update(group);
    }

    @Override
    public ContactGroup get(long id) {
        return dao.get(id);
    }

    @Override
    public ContactGroup getWithContacts(long id) {
        return dao.getWithContacts(id);
    }

    @Override
    public List<ContactGroup> getAll() {
        return dao.getAll();
    }

    @Override
    public void delete(long id) {
        dao.delete(id);
    }

    @Override
    public void deleteContact(long groupId, long contactId) {
        dao.deleteContact(groupId, contactId);
    }

    @Override
    public List<ContactGroup> search(String groupName) {
        return dao.search(groupName);
    }

    @Override
    public ContactGroup searchContactInGroup(final long groupId, final String groupName) {
        return dao.searchContactInGroup(groupId, groupName);
    }
}
