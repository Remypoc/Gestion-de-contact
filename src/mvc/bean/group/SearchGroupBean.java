package mvc.bean.group;

import mvc.bean.BeanManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;


@ManagedBean(name = "searchGroup")
@ViewScoped
public class SearchGroupBean implements Serializable {
	private BeanManager beanManager;

	private String filterGroups;
	private String filterContacts;

	public SearchGroupBean() {
		this.filterGroups = null;
		this.filterContacts = null;
	}

	public void setBeanManager(BeanManager beanManager) {
		this.beanManager = beanManager;
	}

	public String getFilterGroups() {
		return filterGroups;
	}

	public void setFilterGroups(String filterGroup) {
		this.filterGroups = filterGroup;
	}

	public String getFilterContacts() {
		return filterContacts;
	}

	public void setFilterContacts(String filterContact) {
		this.filterContacts = filterContact;
	}

	public void resetGroupSearch() {
		this.filterGroups = null;
		this.beanManager.notifyResetFilterGroups();
	}

	public void resetContactSearch() {
		this.filterContacts = null;
		this.beanManager.notifyResetFilterContacts();
	}

	public void searchGroupByName() {
		beanManager.clearErrors();
		if (filterGroups != null)
			this.beanManager.notifySearchGroupByName(filterGroups.toLowerCase());
		else
			this.beanManager.notifySearchGroupByName(filterGroups);
	}

	public void searchContactByName() {
		beanManager.clearErrors();
		if (filterContacts != null)
			this.beanManager.notifySearchContactByName(filterContacts.toLowerCase());
		else
			this.beanManager.notifySearchContactByName(filterContacts);
	}
}
