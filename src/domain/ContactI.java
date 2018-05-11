package domain;

public interface ContactI {
    String getFullName();
    long getId();
    Boolean isMemberOfGroup(ContactGroup group);
    void addBook(ContactGroup group);
    void removeBook(ContactGroup group);
}
