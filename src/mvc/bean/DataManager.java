package mvc.bean;

import domain.Contact;
import domain.ContactGroup;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class DataManager implements Serializable {
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

    public List<Contact> getDisplayContacts() {
        System.out.println("DataManger => getDisplayContacts");
        if (contacts == null)
            return null;
        if (filterContactsMain == null)
            return getContactsSortByFirstName(contacts);

        return getContactsSortByFirstName(contacts.stream()
                .filter(c -> c.getFirstName().toLowerCase().contains(filterContactsMain) ||
                        c.getLastName().toLowerCase().contains(filterContactsMain))
                .collect(Collectors.toSet()));
    }

    private List<Contact> getContactsSortByFirstName(Set<Contact> contacts) {
        return contacts.stream().sorted((c1, c2) -> c1.getFirstName().compareToIgnoreCase(c2.getFirstName()))
                .collect(Collectors.toList());
    }

    public List<ContactGroup> getDisplayGroups() {
        System.out.println("DataManager => getDisplayGroups");
        if (groups == null) {
            return null;
        }
        if (filterGroups == null) {
            return getGroupsSortedByName(groups);
        }
        return getGroupsSortedByName(groups.stream()
                .filter(g -> g.getGroupName().toLowerCase()
                        .contains(filterGroups.toLowerCase()))
                .collect(Collectors.toSet()));
    }

    private List<ContactGroup> getGroupsSortedByName(Set<ContactGroup> groups) {
        return groups.stream().sorted((g1, g2) -> g1.getGroupName().compareToIgnoreCase(g2.getGroupName()))
                .collect(Collectors.toList());
    }

    public List<Contact> getDisplayContactsOfGroup() {
        System.out.println("DataManger => getDisplayContacts");
        if (group == null || group.getContacts() == null) {
            return null;
        }
        if (filterContacts == null) {
            return getContactsSortByFirstName(group.getContacts());
        }
        return getContactsSortByFirstName(group.getContacts().stream()
                .filter(c -> c.getFirstName().toLowerCase().contains(filterContacts) ||
                    c.getLastName().toLowerCase().contains(filterContacts))
                .collect(Collectors.toSet()));
    }

    public List<Contact> getContactsNotInGroup() {
        System.out.println("DataManager => getContactsNotInGroup");
        if (group == null || contacts == null) {
            return null;
        }
        return getContactsSortByFirstName(contacts.stream()
                .filter(c -> !group.getContacts().contains(c))
                .collect(Collectors.toSet()));

    }

    public void addContactToGroup(Contact contact) {
        Optional<Contact> c1 =contacts.stream()
                .filter(c -> c.getId() == contact.getId()).findFirst();
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
