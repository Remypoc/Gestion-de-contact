package servletAction;

import actionForm.AddContactGroupValidationForm;
import actionForm.AddContactValidationForm;
import domain.Address;
import domain.Contact;
import domain.ContactGroup;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import service.ContactService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddContactGroupAction extends Action {
    public ActionForward execute(final ActionMapping pMapping, ActionForm pForm,
                                 final HttpServletRequest pRequest, final HttpServletResponse pResponse) {

        final AddContactGroupValidationForm lForm = (AddContactGroupValidationForm) pForm;

        final long id 			= lForm.getGroupId();
        final String groupName  = lForm.getGroupName();

        ContactGroup group = new ContactGroup(id, groupName);

        // create a new Contact
        final ContactService cs = new ContactService();
        final Object lError = cs.addContactGroup(group);

        if(lError == null) {
            // if no exception is raised,  forward "success"
            return pMapping.findForward("success");
        }
        else {
            // If any exception, return the "error" forward
            return pMapping.findForward("error");
        }
    }
}
