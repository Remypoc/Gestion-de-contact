package servletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import actionForm.SearchPhoneNumberValidationForm;
import domain.DAOContact;
import domain.PhoneNumber;

public class SearchPhoneNumberAction extends Action {
	
	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, 
			final HttpServletRequest pRequest, final HttpServletResponse pResponse) {
		
		final SearchPhoneNumberValidationForm lForm = (SearchPhoneNumberValidationForm) pForm;
		
		final long id 			 = lForm.getId();
		final String phoneNumberStr = lForm.getPhoneNumber();
		final String phoneKind 	 = lForm.getPhoneKind();
		
		final PhoneNumber phoneNumber = new PhoneNumber(id, phoneNumberStr, phoneKind);
		final DAOContact lDAOContact = new DAOContact();
		final Object lError = lDAOContact.searchPhoneNumber(phoneNumber);
		
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
