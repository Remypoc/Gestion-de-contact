package actionForm.contactgroup;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class DeleteContactFromGroupValidationForm extends ActionForm {
    private static final long serialVersionUID = 1L;

    private long groupId = 0;
    private long contactId = 0;

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public ActionErrors validate(
            ActionMapping mapping, HttpServletRequest request ) {
        ActionErrors errors = new ActionErrors();

        if( getGroupId() == 0 ) {
            errors.add("groupId", new ActionMessage("contactgroup.delete.error"));
        }

        if( getContactId() == 0 ) {
            errors.add("contactId", new ActionMessage("contactgroup.delete.error"));
        }

        return errors;
    }
}
