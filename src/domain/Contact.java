package domain;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Contact")
public class Contact {
	
	private long id;   
	private String firstName;
	private String lastName;
	private String email;
	private Set<PhoneNumber> phones = new HashSet<PhoneNumber>();
	private Address address;
	private Set<ContactGroup> books = new HashSet<ContactGroup>();

	public Contact() {
		// TODO Auto-generated constructor stub
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

    public Contact() {
        // TODO Auto-generated constructor stub
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
        this.phones = phones;
        this.address = address;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
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
        phones = phoneNumbers;
    }

    public void addPhones(PhoneNumber phone) {
        this.phones.add(phone);
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
		if (!group.contains(this))
			group.addContact(this);
	}

	public void removeBook(ContactGroup group) {
		books.remove(group);
		if (!group.contains(this))
			group.removeContact(this);
	}

	public Boolean isMemberOfGroup(ContactGroup group) {
		return books.contains(group);
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof Contact) {
			if (((Contact) obj).getId() == this.id)
				return true;
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
