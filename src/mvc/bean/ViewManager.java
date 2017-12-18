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
    private boolean displayContact = false;
    private boolean displayCreateContactForm = false;
    private boolean displayUpdateContactForm = false;

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

    public boolean isDisplayContact() {
        return displayContact;
    }

    public void setDisplayContact(boolean displayContact) {
        this.displayContact = displayContact;
    }

    public boolean isDisplayUpdateContactForm() {
        return displayUpdateContactForm;
    }

    public void setDisplayUpdateContactForm(boolean displayUpdateContactForm) {
        this.displayUpdateContactForm = displayUpdateContactForm;
    }

    public boolean isDisplayCreateContactForm() {
        return displayCreateContactForm;
    }

    public void setDisplayCreateContactForm(boolean displayCreateContactForm) {
        this.displayCreateContactForm = displayCreateContactForm;
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
        this.displayContact = false;
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
        displayCreateContactForm = false;
        displayUpdateGroupForm = false;
        displayUpdateContactForm = false;
        displayContact = false;
        this.beanManager.notifyDisplayCreateGroupForm();
    }

    public void enableDisplayContacts() {
        displayContacts = true;
        displayGroups = false;
        displayCreateGroupForm = false;
        displayGroup = false;
        this.beanManager.refreshContacts();
    }

    public void enableDisplayGroups() {
        displayGroups = true;
        displayContacts = false;
        displayCreateContactForm = false;
        displayUpdateContactForm = false;
        this.beanManager.refreshGroups();
    }

    public void enableDisplayCreateContactForm() {
        displayCreateContactForm = true;
        displayContact = false;
        displayGroup = false;
        displayUpdateContactForm = false;
        displayCreateGroupForm = false;
        displayUpdateGroupForm = false;
        this.beanManager.notifyDisplayCreateContactForm();
    }

    public void displayContact() {
        displayContact = true;
        displayGroup = false;
        displayUpdateGroupForm = false;
        displayUpdateContactForm = false;
        displayCreateGroupForm = false;
        displayCreateContactForm = false;
    }

    public void enableDisplayUpdateContactForm() {
        this.displayUpdateContactForm = true;
        this.displayContact = false;
    }

    public void hideUpdateContactForm() {
        this.displayUpdateContactForm = false;
        this.displayContact = true;
    }

    public void hideCreateContactForm() {
        this.displayCreateContactForm = false;
        this.displayContact = true;
    }

    public void hideRightPane() {
        this.displayCreateContactForm = false;
        this.displayContact = false;
        this.displayUpdateContactForm = false;
        this.displayGroup = false;
        this.displayUpdateGroupForm = false;
        this.displayCreateGroupForm = false;
    }
}
