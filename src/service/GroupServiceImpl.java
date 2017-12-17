package service;

import dao.GroupDAO;
import domain.ContactGroup;
import exception.DAOException;

import java.util.Set;

public class GroupServiceImpl implements GroupService {
    private GroupDAO groupDAO;

    public void setGroupDAO(GroupDAO DAO) {
        this.groupDAO = DAO;
    }

    @Override
    public void addGroup(ContactGroup group) throws DAOException {
        groupDAO.addGroup(group);
    }

    @Override
    public void updateGroup(ContactGroup group) throws DAOException {
        groupDAO.updateGroup(group);
    }

    @Override
    public ContactGroup getGroup(long id) throws DAOException {
        return groupDAO.getGroup(id);
    }

    @Override
    public ContactGroup getGroupWithContacts(long id) throws DAOException {
        return groupDAO.getGroupWithContacts(id);
    }

    @Override
    public Set<ContactGroup> getAllGroups() throws DAOException {
        return groupDAO.getAllGroups();
    }

    @Override
    public void deleteGroup(long id) throws DAOException {
        groupDAO.deleteGroup(id);
    }

    @Override
    public void deleteContactFromGroup(long contactId, long groupId) throws DAOException {
        groupDAO.deleteContactFromGroup(contactId, groupId);
    }

    @Override
    public Set<ContactGroup> searchGroups(String groupName) throws DAOException {
        return groupDAO.searchGroups(groupName);
    }

    @Override
    public ContactGroup searchContactInGroup(final long groupId, final String groupName) throws DAOException {
        return groupDAO.searchContactInGroup(groupId, groupName);
    }

    @Override
    public void addContactToGroup(long contactId, long groupId) throws DAOException {
        groupDAO.addContactToGroup(contactId, groupId);
    }
}
