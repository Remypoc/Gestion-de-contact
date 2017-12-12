package mvc.bean.group;

import exception.DAOException;
import mvc.bean.BeanManager;
import service.GroupService;
import service.ServiceFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ResourceBundle;


@ManagedBean(name="deleteGroup")
@ViewScoped
public class DeleteGroupBean implements Serializable {
    private BeanManager beanManager;

    private long groupId;

    public DeleteGroupBean() {
        this.groupId = 0;
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

    public void reset() {
        this.groupId = 0;
    }

    public void deleteGroup(long groupId) {
        System.out.println(String.format("DeleteGroupBean => deleteGroup %d", groupId));
        GroupService service = ServiceFactory.getGroupService();
        try {
            service.deleteGroup(groupId);
            this.beanManager.notifyDeletedGroup(groupId);
        } catch (DAOException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(e.getMessage()));
        }
    }

    public void deleteContactFromGroup(long contactId, long groupId) {
        System.out.println(String.format("DeleteGroupBean => deleteContactFromGroup %d - %d", contactId, groupId));
        GroupService service = ServiceFactory.getGroupService();
        try {
            service.deleteContactFromGroup(contactId, groupId);
            this.beanManager.notifyDeleteContactFromGroup(contactId);
        } catch (DAOException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
            context.addMessage(null, new FacesMessage(text.getString(e.getMessageBundleName())));
        }
    }
}
