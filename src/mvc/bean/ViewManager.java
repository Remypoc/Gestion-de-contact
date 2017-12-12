package mvc.bean;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name="viewManager")
@ViewScoped
public class ViewManager implements Serializable {
    private BeanManager beanManager;

    private boolean displayGroups = false;
    private boolean displayContacts = true;
    private boolean displayGroup = false;
    private boolean displayCreateGroupForm = false;
    private boolean displayUpdateGroupForm = false;

    public BeanManager getBeanManager() {
        return beanManager;
    }

    public void setBeanManager(BeanManager beanManager) {
        this.beanManager = beanManager;
    }

    public boolean isDisplayContacts() {
        return displayContacts;
    }

    public void setDisplayContacts(boolean displayContacts) {
        this.displayContacts = displayContacts;
    }

    public boolean isDisplayUpdateGroupForm() {
        return displayUpdateGroupForm;
    }

    public boolean isDisplayGroups() {
        return displayGroups;
    }

    public void setDisplayGroups(boolean displayGroups) {
        this.displayGroups = displayGroups;
    }

    public boolean isDisplayGroup() {
        return displayGroup;
    }

    public void setDisplayGroup(boolean displayGroup) {
        this.displayGroup = displayGroup;
    }

    public void displayGroup() {
        this.displayGroup = true;
        this.displayCreateGroupForm = false;
        this.displayUpdateGroupForm = false;
    }

    public boolean isDisplayCreateGroupForm() {
        return displayCreateGroupForm;
    }

    public void setDisplayCreateGroupForm(boolean displayCreateGroupForm) {
        this.displayCreateGroupForm = displayCreateGroupForm;
    }

    public void hideCreateGroupForm() {
        this.displayCreateGroupForm = false;
    }

    public Boolean getDisplayUpdateGroupForm() {
        return displayUpdateGroupForm;
    }

    public void setDisplayUpdateGroupForm(boolean displayUpdateGroupForm) {
        this.displayUpdateGroupForm = displayUpdateGroupForm;
    }

    public void enableDisplayUpdateGroupForm() {
        this.displayUpdateGroupForm = true;
        this.displayGroup = false;
    }

    public void hideUpdateGroupForm() {
        this.displayUpdateGroupForm = false;
        this.displayGroup = true;
    }

    public void hideGroup() {
        this.displayGroup = false;
        this.displayUpdateGroupForm = false;
    }

    public void enableDisplayCreateGroupForm() {
        displayGroup = false;
        displayCreateGroupForm = true;
        displayUpdateGroupForm = false;
        this.beanManager.notifyDisplayCreateGroupForm();
    }

    public void enableDisplayContacts() {
        displayContacts = true;
        displayGroups = false;
        this.beanManager.refreshContacts();
    }

    public void enableDisplayGroups() {
        displayGroups = true;
        displayContacts = false;
        this.beanManager.refreshGroups();
    }

}
