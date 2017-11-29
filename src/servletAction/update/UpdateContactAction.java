package servletAction.update;

import actionForm.update.UpdateContactValidationForm;
import domain.Address;
import domain.Contact;
import domain.PhoneNumber;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import service.ContactService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateContactAction extends Action {

    public ActionForward execute(final ActionMapping pMapping, ActionForm pForm,
                                 final HttpServletRequest pRequest, final HttpServletResponse pResponse) {

        final UpdateContactValidationForm lForm = (UpdateContactValidationForm) pForm;

        final long id = lForm.getId();

        System.out.println("UpdateContactAction " + id);

        final String firstName = lForm.getFirstName();
        final String lastName = lForm.getLastName();
        final String email = lForm.getEmail();
        final String hasAddress = lForm.getHasAddress();
        final String hasPhone = lForm.getHasPhone();

        Contact contact = new Contact(id, firstName, lastName, email);

        Address address = null;
        if (hasAddress.equals("true")) {
            address = new Address(
                    lForm.getIdAddress(), lForm.getCountry(),
                    lForm.getCity(), lForm.getStreet(), lForm.getZip());
        }
        contact.setAddress(address);

        PhoneNumber phoneNumber;
        if (hasPhone.equals("true")) {
            phoneNumber = new PhoneNumber(
                    lForm.getIdPhone(), lForm.getPhoneNumber(), lForm.getPhoneKind());
            contact.addPhoneNumber(phoneNumber);
        }

        System.out.println("Contact to be save or update : " + contact);

        // create a new Contact
        final ContactService cs = new ContactService();
        final Object lError = cs.updateContact(contact);

        if (lError == null) {
            // if no exception is raised,  forward "success"
            return pMapping.findForward("success");
        } else {
            // If any exception, return the "error" forward
            return pMapping.findForward("error");
        }
    }
}
