package servletAction.delete;

import actionForm.delete.DeleteContactValidationForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import service.ContactService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteContactAction extends Action {

    public ActionForward execute(final ActionMapping pMapping, ActionForm pForm,
                                 final HttpServletRequest pRequest, final HttpServletResponse pResponse) {

        final DeleteContactValidationForm lForm = (DeleteContactValidationForm) pForm;
        final long id = lForm.getId();

        final ContactService cs = new ContactService();
        final Object lError = cs.deleteContact(id);

        final Object contacts = cs.loadContacts();

        if(lError == null) {
            pRequest.setAttribute("contacts", contacts);

            // if no exception is raised,  forward "success"
            return pMapping.findForward("success");
        }
        else {
            // If any exception, return the "error" forward
            return pMapping.findForward("error");
        }
    }
}
