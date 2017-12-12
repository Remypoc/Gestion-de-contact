package domain;

import java.util.HashSet;
import java.util.Set;

public class Contact {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private Set<PhoneNumber> phones = new HashSet<>();
    private Set<ContactGroup> books = new HashSet<>();

    private int version;

    public Contact() {
    }

    public Contact(long id) {
        this.id = id;
    }

    public Contact(long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Contact(long id, String firstName, String lastName, String email, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public Contact(long id, String firstName, String lastName, String email,
                   Set<PhoneNumber> phones, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        if (phones != null)
        	this.phones = phones;
        this.address = address;
    }

	public String getFullName() {
		return String.format("%s %s", getFirstName(), getLastName());
	}

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String string) {
        email = string;
    }

    public void setFirstName(String string) {
        firstName = string;
    }

    public void setLastName(String string) {
        lastName = string;
    }

    public long getId() {
        return id;
    }

    public void setId(long l) {
        id = l;
    }

    public Set<PhoneNumber> getPhones() {
        return phones;
    }

    public void setPhones(Set<PhoneNumber> phoneNumbers) {
        if (phoneNumbers != null)
			phones = phoneNumbers;
    }

    public void addPhoneNumber(PhoneNumber phone) {
		if (phone != null) {
			this.phones.add(phone);
			phone.setContact(this);
		}
    }

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<ContactGroup> getBooks() {
		return books;
	}

	public void setBooks(Set<ContactGroup> books) {
		this.books = books;
	}

	public void addBook(ContactGroup group) {
		books.add(group);
		if (!group.hasContact(this))
			group.addContact(this);
	}

	public void removeBook(ContactGroup group) {
		books.remove(group);
		if (!group.hasContact(this))
			group.removeContact(this);
	}

	public Boolean isMemberOfGroup(ContactGroup group) {
		return books.contains(group);
	}

	public boolean hasPhoneNumber(PhoneNumber phoneNumber) {
		return phones.contains(phoneNumber);
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		else if (obj instanceof Contact) {
			return ((Contact) obj).getId() == this.id;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Contact {\n");
		sb.append("id: ").append(id).append("\n");
		sb.append("first name: ").append(firstName).append("\n");
		sb.append("last name: ").append(lastName).append("\n");
		sb.append("Address: ").append(address).append("\n");
		sb.append("Phones = [\n");
		for (PhoneNumber p : phones) {
			sb.append("id: ").append(p.getId()).append(", ")
					.append(p.getPhoneNumber()).append(",\n");
		}
		sb.append("]}\n");
		return sb.toString();
	}
}
