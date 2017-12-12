package mvc.bean;

import domain.Contact;
import domain.ContactGroup;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class DataManager {
    private Set<ContactGroup> groups;
    private Set<Contact> contacts;
    private Contact contact;
    private ContactGroup group;
    private String filterGroups;
    private String filterContacts;

    public DataManager() {
        this.groups = new HashSet<>();
        this.contacts = new HashSet<>();
        this.contact = null;
        this.group = null;
        this.filterContacts = null;
        this.filterGroups = null;
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
        if (groups.stream()
                .filter(g -> g.getGroupName().toLowerCase()
                        .contains(filterGroups.toLowerCase()))
                .collect(Collectors.toSet()).isEmpty())
            System.out.println("EMPTY GROUP");
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
            System.out.println(group.getContacts().stream().map(Contact::getFullName).collect(Collectors.toList()));
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
}
