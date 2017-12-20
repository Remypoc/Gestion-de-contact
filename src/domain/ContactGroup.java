package domain;

import java.util.HashSet;
import java.util.Set;

public class ContactGroup {

    private long groupId;
    private String groupName;
    private Set<ContactI> contacts = new HashSet<>();

    public ContactGroup() {
    }
	public ContactGroup(long groupId) {
		this.groupId = groupId;
	}

    public ContactGroup(long groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public ContactGroup(String groupName) {
    	this.groupName = groupName;
	}

    public ContactGroup(long groupId, String groupName, Set<ContactI> contacts) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.contacts = contacts;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<ContactI> getContacts() {
    	return this.contacts;
    }

	public void setContacts(Set<ContactI> contacts) {
		this.contacts = contacts;
	}

	public void addContact(ContactI contact) {
		contacts.add(contact);
		if (!contact.isMemberOfGroup(this))
			contact.addBook(this);
	}

	public void removeContact(ContactI contact) {
		contacts.remove(contact);
		if (!contact.isMemberOfGroup(this))
			contact.removeBook(this);
	}

	public void removeContact(long contactId) {
		this.contacts.removeIf(contact -> contact.getId() == contactId);
	}

	public Boolean hasContact(ContactI contact) {
		return contacts.contains(contact);
	}

	@Override
	public int hashCode() {
		return (int) this.groupId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof ContactGroup) {
			if (((ContactGroup) obj).getGroupId() == this.groupId)
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ContactGroup {\n").append("id=").append(groupId);
		sb.append("groupName=").append(groupName).append("\n");
		sb.append("contacts = [\n");
		for (ContactI c : contacts) {
			sb.append("id: ").append(c.getId()).append(", ")
				.append(c.getFullName()).append(",\n");
		}
		sb.append("]}\n");
		return sb.toString();
	}

    public ContactGroup copy(ContactGroup contactGroup) {
    	this.groupName = contactGroup.getGroupName();
    	this.contacts = contactGroup.getContacts();
    	return this;

    }
}
