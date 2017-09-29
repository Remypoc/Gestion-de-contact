package servletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import actionForm.AddAddressValidationForm;
import domain.Address;
import service.ContactService;

public class AddAddressAction extends Action {
	
	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, 
			final HttpServletRequest pRequest, final HttpServletResponse pResponse) {
		
		final AddAddressValidationForm lForm = (AddAddressValidationForm) pForm;
		
		final long id 			= lForm.getId();
		final String country 	= lForm.getCountry();
		final String city 		= lForm.getCity();
		final String street 	= lForm.getStreet();
		final String zip 		= lForm.getZip();
		
		Address address = new Address(id, country, city, street, zip);
		final ContactService cs = new ContactService();
		final Object lError = cs.addAddress(address);
		
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
