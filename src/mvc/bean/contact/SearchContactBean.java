package mvc.bean.contact;

import mvc.bean.BeanManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;


@ManagedBean(name = "searchContact")
@ViewScoped
public class SearchContactBean implements Serializable {
	private BeanManager beanManager;

	private String filterContacts;

	public SearchContactBean() {
		this.filterContacts = null;
	}

	public void setBeanManager(BeanManager beanManager) {
		this.beanManager = beanManager;
	}

	public String getFilterContacts() {
		return filterContacts;
	}

	public void setFilterContacts(String filterContact) {
		this.filterContacts = filterContact;
	}

	public void resetContactSearch() {
		this.beanManager.notifyResetFilterContactsMain();
	}

	public void searchContactByName() {
		beanManager.clearErrors();
		if (filterContacts != null)
			this.beanManager.notifySearchContactMainByName(filterContacts.toLowerCase());
		else
			this.beanManager.notifySearchContactMainByName(filterContacts);
	}
}
