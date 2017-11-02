package dao;

import domain.Contact;
import domain.ContactGroup;

import java.util.List;

public interface ContactGroupDAO {
        public void add(ContactGroup group);
        public void update(ContactGroup group);
        public ContactGroup get(long id);
        public ContactGroup getWithContacts(long id);
        public List<ContactGroup> getAll();
        public void delete(long id);
        public void deleteContact(long groupId, long contactId);
        public List<ContactGroup> search(String groupName);
        public ContactGroup searchContactInGroup(final long groupId, final String name);
}
