package domain;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Contact")
public class Contact {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<PhoneNumber> phones = new HashSet<>();
    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phones=" + phones +
                ", address=" + address +
                '}';
    }
}
