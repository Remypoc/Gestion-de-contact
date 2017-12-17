package mvc.bean.group;

import domain.ContactGroup;
import exception.DAOException;
import mvc.bean.BeanManager;
import service.GroupService;
import util.Word;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ResourceBundle;


//@ManagedBean(name="createGroup")
//@ViewScoped
public class CreateGroupBean implements Serializable {
    private BeanManager beanManager;
//    @ManagedProperty(value = "#{groupService}")
    private GroupService groupService;

    private String groupName;

    public CreateGroupBean() {
        this.groupName = null;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public void setBeanManager(BeanManager beanManager) {
        this.beanManager = beanManager;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void reset() {
        this.groupName = null;
    }

    public boolean validate() {
        return groupName != null;
    }

    public void createGroup() {
        System.out.println(String.format("CreateGroupBean => createGroup: %s", groupName));
        ContactGroup group = new ContactGroup(Word.capitalize(groupName));
        try {
            groupService.addGroup(group);
            this.beanManager.notifyCreateGroup(group);
            this.reset();
        } catch (DAOException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            ResourceBundle text = ResourceBundle.getBundle("resources.Resources", context.getViewRoot().getLocale());
            context.addMessage("form-createGroup", new FacesMessage(text.getString(e.getMessageBundleName())));
        }
    }
}
