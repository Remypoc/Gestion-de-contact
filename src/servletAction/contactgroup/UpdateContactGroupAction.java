package servletAction.contactgroup;

import actionForm.contactgroup.DeleteContactGroupValidationForm;
import domain.ContactGroup;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import service.ContactGroupService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateContactGroupAction extends Action{
    public ActionForward execute(
            final ActionMapping pMapping, ActionForm pForm,
            final HttpServletRequest pRequest, final HttpServletResponse pResponse) {

        Long groupdId = Long.parseLong(pRequest.getParameter("groupId"));
        final ContactGroupService service = ServiceFactory.getContactGroupService();
        ContactGroup group = service.getWithContacts(groupdId);

        if (group != null) {
            pRequest.setAttribute("group", group);
            pRequest.setAttribute("contacts", group.getContacts());
            return pMapping.findForward("success");
        } else {
            return pMapping.findForward("error");
        }
    }
}
