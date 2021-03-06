package mvc.bean;

import domain.Contact;
import domain.ContactGroup;
import mvc.bean.contact.CreateOrUpdateContactBean;
import mvc.bean.contact.DeleteContactBean;
import mvc.bean.contact.SearchContactBean;
import mvc.bean.group.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

@ManagedBean(name = "beanManager")
@ViewScoped
public class BeanManager extends SpringBeanAutowiringSupport implements Serializable {
	private DataManager dataManager;
	private DataLoader dataLoader;

	@ManagedProperty(value = "#{viewManager}")
	private ViewManager viewManager;
	@ManagedProperty(value = "#{searchGroup}")
	private SearchGroupBean searchGroupBean;
	@ManagedProperty(value = "#{deleteGroup}")
	private DeleteGroupBean deleteGroupBean;
	@ManagedProperty(value = "#{createGroup}")
	private CreateGroupBean createGroupBean;
	@ManagedProperty(value = "#{updateGroup}")
	private UpdateGroupBean updateGroupBean;
	@ManagedProperty(value = "#{addContactToGroup}")
	private AddContactToGroupBean addContactToGroupBean;
	@ManagedProperty(value = "#{deleteContact}")
	private DeleteContactBean deleteContactBean;
	@ManagedProperty(value = "#{searchContact}")
	private SearchContactBean searchContactBean;
	@ManagedProperty(value = "#{createOrUpdateContact}")
	private CreateOrUpdateContactBean createOrUpdateContactBean;

	private Set<String> errors = new HashSet<>();

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).
				getAutowireCapableBeanFactory().
				autowireBean(this);
//		System.out.println("BeanManager.init");
	}

	public Set<String> getErrors() {
		return errors;
	}

	public void setErrors(Set<String> errors) {
		this.errors = errors;
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

	public DeleteGroupBean getDeleteGroupBean() {
		return deleteGroupBean;
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

	public CreateOrUpdateContactBean getCreateOrUpdateContactBean() {
		return createOrUpdateContactBean;
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

	@Autowired
	public void setDataManager(DataManager dataManager) {
		this.dataManager = dataManager;
	}

	@Autowired
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

	public void setDeleteGroupBean(DeleteGroupBean deleteGroupBean) {
		this.deleteGroupBean = deleteGroupBean;
		this.deleteGroupBean.setBeanManager(this);
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

	public void setCreateOrUpdateContactBean(CreateOrUpdateContactBean createOrUpdateContactBean) {
		this.createOrUpdateContactBean = createOrUpdateContactBean;
		this.createOrUpdateContactBean.setBeanManager(this);
	}

	public void notifyDeletedGroup(long groupId) {
		clearErrors();
		this.dataManager.getGroups().removeIf(group -> group.getGroupId() == groupId);
		if (this.dataManager.getGroup() != null && this.dataManager.getGroup().getGroupId() == groupId)
			this.viewManager.hideGroup();
	}

	public void notifyCreateGroup(ContactGroup group) {
		clearErrors();
		this.dataManager.addGroup(group);
		this.viewManager.hideCreateGroupForm();
	}

	public void notifyUpdateGroup(ContactGroup group) {
		clearErrors();
		dataManager.getGroup().setGroupName(group.getGroupName());
		Optional<ContactGroup> c1 = dataManager.getGroups().stream()
				.filter(c -> c.getGroupId() == group.getGroupId()).findFirst();
		c1.ifPresent(contactGroup -> contactGroup.setGroupName(group.getGroupName()));
		this.viewManager.hideUpdateGroupForm();
	}

	public void notifySearchGroupByName(String filterGroups) {
		clearErrors();
		this.dataManager.setFilterGroups(filterGroups);
	}

	public void notifyDeleteContactFromGroup(long contactId) {
		clearErrors();
		this.dataManager.getGroup().removeContact(contactId);
	}

	public void notifyAddContactToGroup(Contact contact) {
		clearErrors();
		this.dataManager.addContactToGroup(contact);
	}

	public void loadGroups() {
		clearErrors();
		Set<ContactGroup> groups = this.dataLoader.loadGroups();

		if (groups == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
//			context.addMessage(null, new FacesMessage(text.getString("exception.load.groups.failed")));
			addError(text.getString("exception.load.groups.failed"));
		} else {
			this.searchGroupBean.resetGroupSearch();
			this.dataManager.setFilterGroups(null);
			this.dataManager.setGroups(groups);
		}
	}

	public void loadContacts() {
		clearErrors();
		Set<Contact> contacts = this.dataLoader.loadContacts();

		if (contacts == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
//			context.addMessage(null, new FacesMessage(text.getString("exception.load.contacts.failed")));
			addError(text.getString("exception.load.contacts.failed"));
		} else {
			this.searchGroupBean.resetContactSearch();
			this.dataManager.setFilterContactsMain(null);
			this.dataManager.setContacts(contacts);
		}
	}

	public void loadGroup(long groupId) {
		clearErrors();
		ContactGroup group = this.dataLoader.loadGroup(groupId);
		if (group == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
//			context.addMessage(null, new FacesMessage(text.getString("exception.load.group.failed")));
			addError(text.getString("exception.load.group.failed"));
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

	void refreshContacts() {
		clearErrors();
		Set<Contact> contacts = this.dataLoader.loadContacts();
		this.dataManager.setContacts(contacts);
	}

	void refreshGroups() {
		clearErrors();
		Set<ContactGroup> groups = this.dataLoader.loadGroups();
		this.dataManager.setGroups(groups);
	}

	void refreshContact() {
		clearErrors();
		this.dataLoader.refreshContact(this.dataManager.getContact());
	}

	void refreshGroup() {
//		this.dataLoader.refreshGroup(this.dataManager.getGroup());
	}

	public void notifySearchContactMainByName(String s) {
		this.dataManager.setFilterContactsMain(s);
	}

	public void notifyResetFilterContactsMain() {

		this.dataManager.setFilterContactsMain(null);
	}

	public void loadContact(long contactId) {
		clearErrors();
		Contact contact = this.dataLoader.loadContact(contactId);
		if (contact == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle text = ResourceBundle.getBundle("resources.Resources",
					context.getViewRoot().getLocale());
			/*context.addMessage(null, new FacesMessage(
					text.getString("exception.load.contact.failed"),
					"BeanManager, loadContact failed: contact is null"));*/
			addError(text.getString("exception.load.contact.failed"));
		} else {
			this.dataManager.setContact(contact);
			this.viewManager.displayContact();
		}
	}

	public void notifyUpdateContact(Contact contact) {
		clearErrors();
		this.refreshContacts();
		this.refreshContact();
		this.viewManager.hideUpdateContactForm();
	}

	public void notifyCreateContact(Contact contact) {
//		System.out.println("BeanManager => notifyCreateContact");
		clearErrors();
		if (contact != null) {
			dataManager.getContacts().add(contact);
			dataManager.setContact(contact);
		}
		this.viewManager.hideRightPane();
	}

	public void notifyDeleteContact(Contact contact) {
//		System.out.println("BeanManager => notifyDeleteContact");
		clearErrors();
		if (contact != null) {
			if (dataManager.getContact() != null && dataManager.getContact().equals(contact)) {
				dataManager.setContact(null);
			}
			dataManager.getContacts().removeIf(contact1 -> contact1.equals(contact));
			this.viewManager.hideRightPane();
		}
	}

	public void notifyDisplayCreateContactForm() {
		clearErrors();
		this.createOrUpdateContactBean.reset();
	}

	public void  addError(String error) {
		errors.add(error);
	}

	public void clearErrors() {
		errors.clear();
	}
}
