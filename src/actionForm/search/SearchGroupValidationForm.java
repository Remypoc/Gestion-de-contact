package actionForm.search;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class SearchGroupValidationForm extends ActionForm{
    private static final long serialVersionUID = 1L;

    private String groupName = null;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        groupName = null;
    }

    public ActionErrors validate(
            ActionMapping mapping, HttpServletRequest request ) {
        ActionErrors errors = new ActionErrors();

        if( getGroupName() == null || getGroupName().length() < 1 ) {
            errors.add("groupName", new ActionMessage("contactgroup.groupName.error"));
        }

        return errors;
    }
}
