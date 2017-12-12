package service;

public class ServiceFactory {

    static public GroupService getGroupService() {
        return new GroupServiceImpl();
    }

    static public ContactService getContactService() {
        return new ContactService();
    }
}
