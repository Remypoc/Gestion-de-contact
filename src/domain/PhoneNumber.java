package domain;

public class PhoneNumber {
	private int id;
	private String phoneKind;
	private String phoneNumber;
	private Contact contact;
	
	public PhoneNumber(int id, String phoneKind, String phoneNumber, Contact contact) {
		this.id = id;
		this.phoneKind = phoneKind;
		this.phoneNumber = phoneNumber;
		this.contact = contact;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneKind() {
		return phoneKind;
	}

	public void setPhoneKind(String phoneKind) {
		this.phoneKind = phoneKind;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("PhoneNumber:\n").append("id=").append(id);
		sb.append("phoneKind=").append(phoneKind).append("\n");
		sb.append("phoneNumber=").append(phoneNumber).append("\n");
		if (contact != null) {
			sb.append("contact=").append(contact.getId())
				.append(", ").append(contact.getFullName())
				.append("\n");	
		}
		else
			sb.append("contact=null\n");
		return sb.toString();
	}
}
