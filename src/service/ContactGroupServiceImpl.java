package service;

import dao.ContactGroupDAO;
import dao.ContactGroupDAOImpl;
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
    public List<ContactGroup> getAll() {
        return dao.getAll();
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }

    @Override
    public List<ContactGroup> search(String groupName) {
        return dao.search(groupName);
    }
}
