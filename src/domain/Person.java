package domain;

import java.util.Set;

public class Person extends Contact {
    private String firstName;
    private String lastName;

    public Person() {
        // TODO Auto-generated constructor stub
    }

    public Person(long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(long id, String email, String firstName, String lastName) {
        super(id, email);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(long id, String email, Address address, String firstName, String lastName) {
        super(id, email, address);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(long id, String email, Set<PhoneNumber> phones, Address address, String firstName, String lastName) {
        super(id, email, phones, address);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getFullName() {
        return String.format("%s %s", getFirstName(), getLastName());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Contact {\n");
        sb.append("id: ").append(super.getId()).append("\n");
        sb.append("first name: ").append(firstName).append("\n");
        sb.append("last name: ").append(lastName).append("\n");
        sb.append("version : ").append(super.getVersion()).append("\n");
        sb.append("Address: ").append(super.getAddress()).append("\n");
        sb.append("Phones = [\n");
        for (PhoneNumber p : super.getPhones()) {
            sb.append("id: ").append(p.getId()).append(", ")
                    .append(p.getPhoneNumber()).append(",\n");
        }
        sb.append("]}\n");
        return sb.toString();
    }
}
