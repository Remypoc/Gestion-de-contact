package servletAction.display;

import actionForm.display.DisplayContactValidationForm;
import domain.Contact;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import service.ContactService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayContactAction extends Action {

    public ActionForward execute(final ActionMapping pMapping, ActionForm pForm,
                                 final HttpServletRequest pRequest, final HttpServletResponse pResponse) {

        final DisplayContactValidationForm lForm = (DisplayContactValidationForm) pForm;
        final long id = lForm.getId();


        final ContactService cs = new ContactService();
        final Contact contact = (Contact) cs.loadContact(id);

        ((DisplayContactValidationForm) pForm).setLastName(contact.getLastName());

        if(contact instanceof Contact) {

            pRequest.setAttribute("contact", contact);

            // if no exception is raised,  forward "success"
            return pMapping.findForward("success");
        }
        else {
            // If any exception, return the "error" forward
            return pMapping.findForward("error");
        }
    }
}
