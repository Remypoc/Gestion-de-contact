package service;

import dao.GroupDAO;
import dao.GroupDAOImpl;
import domain.ContactGroup;
import exception.DAOException;

import java.util.Set;

public class GroupServiceImpl implements GroupService {
    private GroupDAO dao = new GroupDAOImpl();

    @Override
    public void addGroup(ContactGroup group) throws DAOException {
        dao.addGroup(group);
    }

    @Override
    public void updateGroup(ContactGroup group) throws DAOException {
        dao.updateGroup(group);
    }

    @Override
    public ContactGroup getGroup(long id) throws DAOException {
        return dao.getGroup(id);
    }

    @Override
    public ContactGroup getGroupWithContacts(long id) throws DAOException {
        return dao.getGroupWithContacts(id);
    }

    @Override
    public Set<ContactGroup> getAllGroups() throws DAOException {
        return dao.getAllGroups();
    }

    @Override
    public void deleteGroup(long id) throws DAOException {
        dao.deleteGroup(id);
    }

    @Override
    public void deleteContactFromGroup(long contactId, long groupId) throws DAOException {
        dao.deleteContactFromGroup(contactId, groupId);
    }

    @Override
    public Set<ContactGroup> searchGroups(String groupName) throws DAOException {
        return dao.searchGroups(groupName);
    }

    @Override
    public ContactGroup searchContactInGroup(final long groupId, final String groupName) throws DAOException {
        return dao.searchContactInGroup(groupId, groupName);
    }

    @Override
    public void addContactToGroup(long contactId, long groupId) throws DAOException {
        dao.addContactToGroup(contactId, groupId);
    }
}
