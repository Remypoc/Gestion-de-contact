package mvc.bean.group;

import domain.Contact;
import domain.ContactGroup;
import exception.DAOException;
import mvc.bean.BeanManager;
import service.GroupService;
import service.ServiceFactory;
import util.Word;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ResourceBundle;

@ManagedBean(name="updateGroup")
@ViewScoped
public class UpdateGroupBean implements Serializable{
    private BeanManager beanManager;

    private long groupId = 0;
    private String groupName;

    public UpdateGroupBean() {
        groupId = 0;
        groupName = null;
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
        System.out.println(String.format("UpdateGroupBean => updateGroup: %d %s", groupId, groupName));
        GroupService service = ServiceFactory.getGroupService();
        ContactGroup group = new ContactGroup(groupId, Word.capitalize(groupName));
        try {
            service.updateGroup(group);
            this.beanManager.notifyUpdateGroup(group);
            this.reset();
        } catch (DAOException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
            context.addMessage("form-updateGroup", new FacesMessage(text.getString(e.getMessageBundleName())));
        }
    }
}
