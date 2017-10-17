package servletAction;

import actionForm.AddContactGroupValidationForm;
import actionForm.AddContactToGroupValidationForm;
import domain.Contact;
import domain.ContactGroup;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import service.ContactService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddContactToGroupAction extends Action {
    public ActionForward execute(final ActionMapping pMapping, ActionForm pForm,
                                 final HttpServletRequest pRequest, final HttpServletResponse pResponse) {

        final AddContactToGroupValidationForm lForm = (AddContactToGroupValidationForm) pForm;

        final long groupId	  = lForm.getGroupId();
        final long contactId  = lForm.getContactId();

        Contact contact = new Contact(contactId);
        ContactGroup group = new ContactGroup(groupId);
        group.addContact(contact);

        // create a new Contact
        final ContactService cs = new ContactService();
        final Object lError = cs.addContactToGroup(group, contact);

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
