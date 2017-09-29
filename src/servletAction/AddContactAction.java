package servletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import actionForm.AddContactValidationForm;
import domain.Contact;
import service.ContactService;

public class AddContactAction extends Action {
	
	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, final HttpServletRequest pRequest, final HttpServletResponse pResponse) {
		
		final AddContactValidationForm lForm = (AddContactValidationForm) pForm;
		
		final long id = lForm.getId();
		final String firstName = lForm.getFirstName();
		final String lastName = lForm.getLastName();
		final String email = lForm.getEmail();
		
		Contact contact = new Contact(id, firstName, lastName, email);

		// create a new Contact
		final ContactService cs = new ContactService();
		final Object lError = cs.addContact(contact);
		
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

