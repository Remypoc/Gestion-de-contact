package mvc.bean.group;

import domain.Contact;
import exception.DAOException;
import mvc.bean.BeanManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import service.GroupService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ResourceBundle;


@ManagedBean(name = "addContactToGroup")
@ViewScoped
public class AddContactToGroupBean extends SpringBeanAutowiringSupport implements Serializable {
	private BeanManager beanManager;
	@Autowired
	private GroupService groupService;

	private long contactId;
	private long groupId;

	public AddContactToGroupBean() {
		this.contactId = 0;
		this.groupId = 0;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public void setBeanManager(BeanManager beanManager) {
		this.beanManager = beanManager;
	}

	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public void reset() {
		this.groupId = 0;
		this.contactId = 0;
	}

	public boolean validate() {
		return this.groupId > 0 && this.contactId > 0;
	}

	public void addContactToGroup() {
		System.out.println(String.format("AddContactToGroupBean => addContactToGroup: %d, %d", contactId, groupId));
		if (validate()) {
			try {
				groupService.addContactToGroup(contactId, groupId);
				Contact contact = new Contact(contactId);
				this.beanManager.notifyAddContactToGroup(contact);
				this.reset();
			} catch (DAOException e) {
				FacesContext context = FacesContext.getCurrentInstance();
				ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
//                context.addMessage(null, new FacesMessage(text.getString(e.getMessageBundleName())));
				beanManager.addError(text.getString(e.getMessageBundleName()));
			}
		}
	}
}
