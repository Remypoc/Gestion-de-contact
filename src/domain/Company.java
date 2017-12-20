package domain;

import java.util.Set;

public class Company extends Contact {
    private long numSiret = 0;

    public Company() {
    }

    public Company(Contact contact, String lastName, long numSiret) {
        setId(contact.getId());
        setFirstName("Entreprise");
        setLastName(lastName);
        setEmail(contact.getEmail());
        setAddress(contact.getAddress());
        setPhones(contact.getPhones());
        setVersion(contact.getVersion());
        this.numSiret = numSiret;
    }

    public Company(long id, long numSiret) {
        super(id);
        this.numSiret = numSiret;
    }

    public Company(long id, String email, long numSiret) {
        super(id, email);
        this.numSiret = numSiret;
    }

    public Company(long id, String email, Address address, long numSiret) {
        super(id, email, address);
        this.numSiret = numSiret;
    }

    public Company(long id, String email, Set<PhoneNumber> phones, Address address, long numSiret) {
        super(id, email, phones, address);
        this.numSiret = numSiret;
    }

    public long getNumSiret() {
        return numSiret;
    }

    public void setNumSiret(long numSiret) {
        this.numSiret = numSiret;
    }

    @Override
    public String getFullName() {
        return String.format("Entreprise <b>%s</b>", getLastName());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Contact {\n");
        sb.append("id: ").append(getId()).append("\n");
        sb.append("first name: ").append(getFirstName()).append("\n");
        sb.append("last name: ").append(getLastName()).append("\n");
        sb.append("version : ").append(getVersion()).append("\n");
        sb.append("Address: ").append(getAddress()).append("\n");
        sb.append("Phones = [\n");
        for (PhoneNumber p : getPhones()) {
            sb.append("id: ").append(p.getId()).append(", ")
                    .append(p.getPhoneNumber()).append(",\n");
        }
        sb.append("]}\n");
        return sb.toString();
    }

}
