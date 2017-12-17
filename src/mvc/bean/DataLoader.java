package mvc.bean;

import domain.Contact;
import domain.ContactGroup;
import exception.DAOException;
import org.hibernate.service.spi.InjectService;
import service.ContactService;
import service.GroupService;

import java.util.Set;

public class DataLoader {

    private ContactService contactService;
    private GroupService groupService;

    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public Set<ContactGroup> loadGroups() {
        System.out.println("DataLoader => load Groups");
        Set<ContactGroup> groups;
        try {
            groups = groupService.getAllGroups();
            return groups;
        } catch (DAOException e) {
            return null;
        }
    }

    public Set<Contact> loadContacts() {
        //TODO catch exception if can't load
        System.out.println("DataLoader => load Contacts");
        Set<Contact> contacts = contactService.loadContacts();
        return contacts;
    }

    public ContactGroup loadGroup(long groupId) {
        System.out.println("DataLoader => load Group");
        ContactGroup group = null;
        try {
            group = groupService.getGroupWithContacts(groupId);
            return group;
        } catch (DAOException e) {
            return null;
        }
    }

    public Contact loadContact(long contactId) {
        System.out.println("DataLoader => load Contact");
        Contact contact = (Contact) contactService.loadContact(contactId);
        return contact;
    }
}
