package mvc.bean;

import domain.Contact;
import domain.ContactGroup;
import mvc.bean.contact.DeleteContactBean;
import mvc.bean.contact.SearchContactBean;
import mvc.bean.contact.UpdateContactBean;
import mvc.bean.group.*;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

@ManagedBean(name="beanManager")
@ViewScoped
public class BeanManager implements Serializable {
    private DataManager dataManager;
    private DataLoader dataLoader;
    @ManagedProperty(value="#{viewManager}")
    private ViewManager viewManager;
    
    @ManagedProperty(value="#{searchGroup}")
    private SearchGroupBean searchGroupBean;
    @ManagedProperty(value="#{deleteGroup}")
    private DeleteGroupBean deleteGroupeBean;
    @ManagedProperty(value="#{createGroup}")
    private CreateGroupBean createGroupBean;
    @ManagedProperty(value="#{updateGroup}")
    private UpdateGroupBean updateGroupBean;
    @ManagedProperty(value="#{addContactToGroup}")
    private AddContactToGroupBean addContactToGroupBean;
    @ManagedProperty(value="#{deleteContact}")
    private DeleteContactBean deleteContactBean;
    @ManagedProperty(value="#{searchContact}")
    private SearchContactBean searchContactBean;
    @ManagedProperty(value="#{updateContact}")
    private UpdateContactBean updateContactBean;

    private String error;

    public BeanManager() {
        this.dataLoader = new DataLoader();
        this.dataManager = new DataManager();
        this.error = null;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public SearchGroupBean getSearchGroupBean() {
        return searchGroupBean;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public DataLoader getDataLoader() {
        return dataLoader;
    }

    public DeleteGroupBean getDeleteGroupeBean() {
        return deleteGroupeBean;
    }

    public DeleteContactBean getDeleteContactBean() {
        return deleteContactBean;
    }

    public SearchContactBean getSearchContactBean() {
        return searchContactBean;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

    public CreateGroupBean getCreateGroupBean() {
        return createGroupBean;
    }

    public UpdateContactBean getUpdateContactBean() {
        return updateContactBean;
    }

    public void notifyDisplayCreateGroupForm() {
        this.dataManager.setFilterContacts(null);
    }

    public UpdateGroupBean getUpdateGroupBean() {
        return updateGroupBean;
    }

    public AddContactToGroupBean getAddContactToGroupBean() {
        return addContactToGroupBean;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setDataLoader(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.viewManager.setBeanManager(this);
    }

    public void setSearchGroupBean(SearchGroupBean searchGroupBean) {
        this.searchGroupBean = searchGroupBean;
        this.searchGroupBean.setBeanManager(this);
    }

    public void setDeleteGroupeBean(DeleteGroupBean deleteGroupeBean) {
        this.deleteGroupeBean = deleteGroupeBean;
        this.deleteGroupeBean.setBeanManager(this);
    }

    public void setUpdateGroupBean(UpdateGroupBean updateGroupBean) {
        this.updateGroupBean = updateGroupBean;
        this.updateGroupBean.setBeanManager(this);
    }

    public void setAddContactToGroupBean(AddContactToGroupBean addContactToGroupBean) {
        this.addContactToGroupBean = addContactToGroupBean;
        this.addContactToGroupBean.setBeanManager(this);
    }

    public void setCreateGroupBean(CreateGroupBean createGroupBean) {
        this.createGroupBean = createGroupBean;
        this.createGroupBean.setBeanManager(this);
    }

    public void setDeleteContactBean(DeleteContactBean deleteContactBean) {
        this.deleteContactBean = deleteContactBean;
        this.deleteContactBean.setBeanManager(this);
    }

    public void setSearchContactBean(SearchContactBean searchContactBean) {
        this.searchContactBean = searchContactBean;
        this.searchContactBean.setBeanManager(this);
    }

    public void setUpdateContactBean(UpdateContactBean updateContactBean) {
        this.updateContactBean = updateContactBean;
        this.updateContactBean.setBeanManager(this);
    }

    public void notifyDeletedGroup(long groupId) {
        this.dataManager.getGroups().removeIf(group -> group.getGroupId() == groupId);
        if (this.dataManager.getGroup() != null && this.dataManager.getGroup().getGroupId() == groupId)
            this.viewManager.hideGroup();
    }

    public void notifyCreateGroup(ContactGroup group) {
        this.dataManager.addGroup(group);
        this.viewManager.hideCreateGroupForm();
    }

    public void notifyUpdateGroup(ContactGroup group) {
        dataManager.getGroup().setGroupName(group.getGroupName());
        Optional<ContactGroup> c1 =dataManager.getGroups().stream()
                .filter(c -> c.getGroupId() == group.getGroupId()).findFirst();
        c1.ifPresent(contactGroup -> contactGroup.setGroupName(group.getGroupName()));
        this.viewManager.hideUpdateGroupForm();
    }

    public void notifySearchGroupByName(String filterGroups) {
        this.dataManager.setFilterGroups(filterGroups);
    }

    public void notifyDeleteContactFromGroup(long contactId) {
        this.dataManager.getGroup().removeContact(contactId);
    }

    public void notifyAddContactToGroup(Contact contact) {
        this.dataManager.addContactToGroup(contact);
    }

    public void loadGroups() {
        Set<ContactGroup> groups = this.dataLoader.loadGroups();
        if (groups == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
            context.addMessage("form-createGroup", new FacesMessage(text.getString("exception.load.groups.failed")));
            // TODO form-creategroup ??
        } else {
            this.searchGroupBean.resetGroupSearch();
            this.dataManager.setFilterGroups(null);
            this.dataManager.setGroups(groups);
        }
    }

    public void loadContacts() {
        Set<Contact> contacts = this.dataLoader.loadContacts();
        if (contacts == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
            context.addMessage("form-createGroup", new FacesMessage(text.getString("exception.load.contacts.failed")));
            // TODO form-creategroup ??
        } else {
            this.searchGroupBean.resetContactSearch();
            this.dataManager.setFilterContactsMain(null);
            this.dataManager.setContacts(contacts);
        }
    }

    public void loadGroup(long groupId) {
        ContactGroup group = this.dataLoader.loadGroup(groupId);
        if (group == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
            context.addMessage("form-createGroup", new FacesMessage(text.getString("exception.load.group.failed")));
            // TODO form-creategroup ??
        } else {
            this.dataManager.setFilterContacts(null);
            this.searchGroupBean.resetContactSearch();
            this.dataManager.setGroup(group);
            this.viewManager.displayGroup();
        }
    }

    public void notifySearchContactByName(String filterContacts) {
        this.dataManager.setFilterContacts(filterContacts);
    }

    public void notifyResetFilterGroups() {
        this.dataManager.setFilterGroups(null);
    }

    public void notifyResetFilterContacts() {
        this.dataManager.setFilterContacts(null);
    }

    public void notifyError(String error) {
        this.error = error;
    }

    void refreshContacts() {
        Set<Contact> contacts = this.dataLoader.loadContacts();
        this.dataManager.setContacts(contacts);
    }

    void refreshGroups() {
        Set<ContactGroup> groups = this.dataLoader.loadGroups();
        this.dataManager.setGroups(groups);
    }

    public void notifySearchContactMainByName(String s) {
        this.dataManager.setFilterContactsMain(s);
    }

    public void notifyResetFilterContactsMain() {
        this.dataManager.setFilterContactsMain(null);
    }

    public void loadContact(long contactId) {
        Contact contact = this.dataLoader.loadContact(contactId);
        if (contact == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
            context.addMessage("form-createGroup", new FacesMessage(text.getString("exception.load.contact.failed")));
            // TODO form-creategroup ??
        } else {
            this.dataManager.setContact(contact);
            this.viewManager.displayContact();
        }
    }

    public void notifyUpdateContact(Contact contact) {
        if ( contact != null) {
            dataManager.setContact(contact);
            Optional<Contact> c1 =dataManager.getContacts().stream()
                    .filter(c -> c.getId() == contact.getId()).findFirst();
            c1.ifPresent(c -> c = contact);
        }
        this.viewManager.hideUpdateContactForm();
    }
}
