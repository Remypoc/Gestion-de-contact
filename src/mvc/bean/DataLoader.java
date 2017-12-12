package mvc.bean;

import domain.Contact;
import domain.ContactGroup;
import exception.DAOException;
import service.ContactService;
import service.GroupService;
import service.ServiceFactory;

import java.util.Set;

public class DataLoader {

    public Set<ContactGroup> loadGroups() {
        System.out.println("DataLoader => load Groups");
        GroupService service = ServiceFactory.getGroupService();
        Set<ContactGroup> groups;
        try {
            groups = service.getAllGroups();
            return groups;
        } catch (DAOException e) {
            return null;
        }
    }

    public Set<Contact> loadContacts() {
        //TODO catch exception if can't load
        System.out.println("DataLoader => load Contacts");
        ContactService service = ServiceFactory.getContactService();
        Set<Contact> contacts = service.loadContacts();
        return contacts;
    }

    public ContactGroup loadGroup(long groupId) {
        System.out.println("DataLoader => load Group");
        GroupService service = ServiceFactory.getGroupService();
        ContactGroup group = null;
        try {
            group = service.getGroupWithContacts(groupId);
            return group;
        } catch (DAOException e) {
            return null;
        }
    }
}
