package actionForm;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class AddContactGroupValidationForm extends ActionForm{
    private static final long serialVersionUID = 1L;

    private long groupId = 0;
    private String groupName;

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

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.groupId = 0;
        this.groupName	= null;
    }

    public ActionErrors validate(
            ActionMapping mapping, HttpServletRequest request ) {
        ActionErrors errors = new ActionErrors();

        if( getGroupName() == null || getGroupName().length() < 2 ) {
            errors.add("groupname ", new ActionMessage("contactgroup.groupname.error"));
        }

        return errors;
    }
}
