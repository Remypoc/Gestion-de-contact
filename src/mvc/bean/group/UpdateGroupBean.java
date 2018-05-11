package mvc.bean.group;

import domain.ContactGroup;
import exception.DAOException;
import mvc.bean.BeanManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import service.GroupService;
import util.Word;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ResourceBundle;


@ManagedBean(name = "updateGroup")
@ViewScoped
public class UpdateGroupBean extends SpringBeanAutowiringSupport implements Serializable {
	private BeanManager beanManager;
	@Autowired
	private GroupService groupService;

	private long groupId = 0;
	private String groupName;

	public UpdateGroupBean() {
		groupId = 0;
		groupName = null;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public void setBeanManager(BeanManager beanManager) {
		this.beanManager = beanManager;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void reset() {
		this.groupId = 0;
		this.groupName = null;
	}

	public void updateGroup() {
//		System.out.println(String.format("UpdateGroupBean => updateGroup: %d %s", groupId, groupName));
		ContactGroup group = new ContactGroup(groupId, Word.capitalize(groupName));
		try {
			groupService.updateGroup(group);
			this.beanManager.notifyUpdateGroup(group);
			this.reset();
		} catch (DAOException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
//            context.addMessage(null, new FacesMessage(text.getString(e.getMessageBundleName())));
			beanManager.addError(text.getString(e.getMessageBundleName()));
		}
	}
}
