package service;

public class ServiceFactory {

    static public ContactGroupService getContactGroupService() {
        return new ContactGroupServiceImpl();
    }

    static public ContactService getContactService() {
        return new ContactService();
    }
}
