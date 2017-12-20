package domain;


public class PhoneNumber {
    private long id;
    private String phoneKind;
    private String phoneNumber;
    private ContactI contact;
    private boolean isMobile;

    public PhoneNumber() {
    }

    public PhoneNumber(ContactI contact) {
        phoneKind = null;
        phoneNumber = null;
        this.contact = contact;
    }

    public PhoneNumber(long id, String phoneNumber, String phoneKind) {
        this.id = id;
        this.phoneKind = phoneKind;
        this.phoneNumber = phoneNumber;
        this.contact = null;
    }

    public PhoneNumber(long id, String phoneNumber, String phoneKind, ContactI contact) {
        this.id = id;
        this.phoneKind = phoneKind;
        this.phoneNumber = phoneNumber;
        this.contact = contact;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public ContactI getContact() {
        return contact;
    }

    public void setContact(ContactI contact) {
        if (contact != null) {
            this.contact = contact;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PhoneNumber {\n").append("id=").append(id);
        sb.append("phoneKind=").append(phoneKind).append("\n");
        sb.append("phoneNumber=").append(phoneNumber).append("\n");
        if (contact != null) {
            sb.append("contact=").append(contact.getId())
                .append(", ").append(contact.getFullName())
                    .append("\n");
        }
        else
            sb.append("contact=null\n");
        sb.append("}\n");
        return sb.toString();
    }

    public boolean isMobile() {
        if (phoneKind == null)
            return false;
        return phoneKind.equalsIgnoreCase("mobile") || phoneKind.equalsIgnoreCase("portable");
    }

    public PhoneNumber copy(PhoneNumber phone) {
        this.phoneNumber = phone.getPhoneNumber();
        this.phoneKind = phone.getPhoneKind();
        this.contact = phone.getContact();
        return this;
    }
}
