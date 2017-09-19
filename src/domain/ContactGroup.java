package domain;

import java.util.Set;

public class ContactGroup {
	private int groupId;
	private String groupName;
	private Set<Contact> contacts;
	
	public ContactGroup() {
	}
	
	public ContactGroup(int groupId, String groupName, Set<Contact> contacts) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.contacts = contacts;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ContactGroup:\n").append("id=").append(groupId);
		sb.append("groupName=").append(groupName).append("\n");
		sb.append("contacts = [\n");
		for (Contact c : contacts) {
			sb.append("id=").append(c.getId()).append(", ")
				.append(c.getFullName()).append("\n");
		}
		sb.append("]");
		return sb.toString();
	}
}
