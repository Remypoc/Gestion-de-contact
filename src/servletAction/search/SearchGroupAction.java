package servletAction.search;

import actionForm.search.SearchGroupValidationForm;
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

public class SearchGroupAction extends Action {
    public ActionForward execute(final ActionMapping pMapping, ActionForm pForm,
                                 final HttpServletRequest pRequest, final HttpServletResponse pResponse) {

        final ContactGroupService service = ServiceFactory.getContactGroupService();
        final SearchGroupValidationForm lForm = (SearchGroupValidationForm) pForm;
        String groupName = lForm.getGroupName();
        final Object groups = service.search(groupName);

        if (groups != null) {
            pRequest.setAttribute("groups", groups);
            return pMapping.findForward("success");
        }
        else {
            return pMapping.findForward("error");
        }
    }
}
