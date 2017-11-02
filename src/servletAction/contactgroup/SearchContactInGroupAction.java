package servletAction.contactgroup;

import actionForm.contactgroup.SearchContactInGroupValidationForm;
import domain.ContactGroup;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import service.ContactGroupService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchContactInGroupAction extends Action {
    public ActionForward execute(
            final ActionMapping pMapping, ActionForm pForm,
            final HttpServletRequest pRequest, final HttpServletResponse pResponse) {

        final ContactGroupService service = ServiceFactory.getContactGroupService();
        final SearchContactInGroupValidationForm lForm = (SearchContactInGroupValidationForm) pForm;
        final String name = lForm.getName();
        final long groupId = lForm.getGroupId();
        final ContactGroup group = service.searchContactInGroup(groupId, name);

        if (group != null) {
            pRequest.setAttribute("group", group);
            pRequest.setAttribute("contacts", group.getContacts());
            return pMapping.findForward("success");
        }
        else {
            return pMapping.findForward("error");
        }
    }
}
