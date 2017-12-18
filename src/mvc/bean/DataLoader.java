package mvc.bean;

import domain.Contact;
import domain.ContactGroup;
import exception.DAOException;
import service.ContactService;
import service.GroupService;

import java.io.Serializable;
import java.util.Set;

public class DataLoader implements Serializable {

    private ContactService contactService;
    private GroupService groupService;

    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public Set<ContactGroup> loadGroups() {
        // TODO display exception if can't load
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
        // TODO catch exception if can't load
        System.out.println("DataLoader => load Contacts");
        Set<Contact> contacts = contactService.loadContacts();
        return contacts;
    }

    public ContactGroup loadGroup(long groupId) {
        // TODO display exception if can't load
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
        // TODO catch exception if can't load
        System.out.println("DataLoader => load Contact");
        return (Contact) contactService.loadContact(contactId);
    }

    public void refreshContact(Contact contact) {
        contactService.refreshContact(contact);
    }
}
