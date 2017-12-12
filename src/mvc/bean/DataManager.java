package mvc.bean;

import domain.Contact;
import domain.ContactGroup;

import java.util.*;
import java.util.stream.Collectors;

public class DataManager {

    private Set<ContactGroup> groups;
    private Set<Contact> contacts;
    private Contact contact;
    private ContactGroup group;
    private String filterGroups;
    private String filterContacts;
    private String filterContactsMain;

    public DataManager() {
        this.groups = new HashSet<>();
        this.contacts = new HashSet<>();
        this.contact = null;
        this.group = null;
        this.filterContacts = null;
        this.filterGroups = null;
        this.filterContactsMain = null;
    }

    public Set<ContactGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<ContactGroup> groups) {
        this.groups = groups;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getFilterGroups() {
        return filterGroups;
    }

    public void setFilterGroups(String filterGroups) {
        this.filterGroups = filterGroups;
    }

    public String getFilterContacts() {
        return filterContacts;
    }

    public void setFilterContacts(String filterContacts) {
        this.filterContacts = filterContacts;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public ContactGroup getGroup() {
        return group;
    }

    public void setGroup(ContactGroup group) {
        this.group = group;
    }

    public Set<Contact> getDisplayContacts() {
        System.out.println("DataManger => getDisplayContacts");
        if (contacts == null)
            return null;
        if (filterContactsMain == null)
            return contacts;

        Set<Contact> contacts2 = contacts.stream()
                .filter(c -> c.getFirstName().toLowerCase().contains(filterContactsMain) ||
                        c.getLastName().toLowerCase().contains(filterContactsMain))
                .collect(Collectors.toSet());
        System.out.println(contacts2.size());
        return contacts2;
    }

    // TODO function reload for Group and contacts

    public Set<ContactGroup> getDisplayGroups() {
        System.out.println("DataManager => getDisplayGroups");
        // TODO if null try to reload Groups
        if (groups == null) {
            return null;
        }
        if (filterGroups == null) {
            return groups;
        }
        return groups.stream()
                .filter(g -> g.getGroupName().toLowerCase()
                        .contains(filterGroups.toLowerCase()))
                .collect(Collectors.toSet());
    }

    public Set<Contact> getDisplayContactsOfGroup() {
        System.out.println("DataManger => getDisplayContacts");
        // TODO if contacts are not loaded yet because of lazy load, load them
        if (group == null || group.getContacts() == null) {
            return null;
        }
        if (filterContacts == null) {
            return group.getContacts();
        }
        return group.getContacts().stream()
                .filter(c -> c.getFirstName().toLowerCase().contains(filterContacts) ||
                    c.getLastName().toLowerCase().contains(filterContacts))
                .collect(Collectors.toSet());
    }

    public Set<Contact> getContactsNotInGroup() {
        System.out.println("DataManager => getContactsNotInGroup");
        // TODO if contacts of group are not loaded yet because of lazy load, load them
        if (group == null || contacts == null) {
            return null;
        }
        return contacts.stream()
                .filter(c -> !group.getContacts().contains(c))
                .collect(Collectors.toSet());

    }

    public void addContactToGroup(Contact contact) {
        Optional<Contact> c1 =contacts.stream()
                .filter(c -> c.getId() == contact.getId()).findFirst();
        // TODO if contacts of groups are not loaded yet because of lazy load, load them
        if (c1.isPresent() || group != null || group.getContacts() != null) {
            group.getContacts().add(c1.get());
        }

    }

    public void addGroup(ContactGroup group) {
        if (this.groups != null) {
            this.groups.add(group);
        }
    }

    public void setFilterContactsMain(String filterContactsMain) {
        this.filterContactsMain = filterContactsMain;
    }
}
