package actionForm.contactgroup;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class SearchContactInGroupValidationForm extends ActionForm {
    private static final long serialVersionUID = 1L;

    private long groupId = 0;
    private String name = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public ActionErrors validate(
            ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        if (getGroupId() == 0) {
            errors.add("name", new ActionMessage("group.id.required.error"));
        }

        if( getName() == null ) {
            errors.add("name", new ActionMessage("contact.name.required.error"));
        }

        return errors;
    }
}
