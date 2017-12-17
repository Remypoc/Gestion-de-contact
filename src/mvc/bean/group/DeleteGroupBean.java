package mvc.bean.group;

import exception.DAOException;
import mvc.bean.BeanManager;
import service.GroupService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ResourceBundle;


//@ManagedBean(name="deleteGroup")
//@ViewScoped
public class DeleteGroupBean implements Serializable {
    private BeanManager beanManager;
//    @ManagedProperty(value = "#{groupService}")
    private GroupService groupService;

    private long groupId;

    public DeleteGroupBean() {
        this.groupId = 0;
    }

    public void setBeanManager(BeanManager beanManager) {
        this.beanManager = beanManager;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public void reset() {
        this.groupId = 0;
    }

    public void deleteGroup(long groupId) {
        System.out.println(String.format("DeleteGroupBean => deleteGroup %d", groupId));
        try {
            groupService.deleteGroup(groupId);
            this.beanManager.notifyDeletedGroup(groupId);
        } catch (DAOException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(e.getMessage()));
        }
    }

    public void deleteContactFromGroup(long contactId, long groupId) {
        System.out.println(String.format("DeleteGroupBean => deleteContactFromGroup %d - %d", contactId, groupId));
        try {
            groupService.deleteContactFromGroup(contactId, groupId);
            this.beanManager.notifyDeleteContactFromGroup(contactId);
        } catch (DAOException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
            context.addMessage(null, new FacesMessage(text.getString(e.getMessageBundleName())));
        }
    }
}
