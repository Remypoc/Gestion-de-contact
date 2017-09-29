package servletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import actionForm.DeletePhoneNumberValidationForm;
import domain.PhoneNumber;
import service.ContactService;

public class DeletePhoneNumberAction extends Action {
	
	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, 
			final HttpServletRequest pRequest, final HttpServletResponse pResponse) {
		
		final DeletePhoneNumberValidationForm lForm = (DeletePhoneNumberValidationForm) pForm;
		
		final long id 			    = lForm.getId();
		final String phoneNumberStr = lForm.getPhoneNumber();
		final String phoneKind 	    = lForm.getPhoneKind();
		
		PhoneNumber phoneNumber = new PhoneNumber(id, phoneNumberStr, phoneKind);
		final ContactService cs = new ContactService();
		final Object lError = cs.deletePhoneNumber(phoneNumber);
		
		if (lError instanceof String)
			System.out.print((String) lError);
		
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
