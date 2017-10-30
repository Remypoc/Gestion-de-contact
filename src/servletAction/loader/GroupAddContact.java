package servletAction.loader;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import service.ContactGroupService;
import service.ContactService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GroupAddContact extends Action{

    public ActionForward execute(final ActionMapping pMapping, ActionForm pForm,
                                 final HttpServletRequest pRequest, final HttpServletResponse pResponse) {

        final ContactService contactService = ServiceFactory.getContactService();
        final ContactGroupService groupService = ServiceFactory.getContactGroupService();
        final Object contacts = contactService.loadContacts();
        final Object groups = groupService.getAll();

        if (contacts instanceof List && groups != null) {
            pRequest.setAttribute("contacts", contacts);
            pRequest.setAttribute("groups", groups);
            return pMapping.findForward("success");
        }
        else {
            return pMapping.findForward("error");
        }
    }
}
