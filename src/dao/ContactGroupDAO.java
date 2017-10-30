package dao;

import domain.ContactGroup;

import java.util.List;

public interface ContactGroupDAO {
        public void add(ContactGroup group);
        public void update(ContactGroup group);
        public ContactGroup get(long id);
        public List<ContactGroup> getAll();
        public void delete(long id);
        public List<ContactGroup> search(String groupName);
}
