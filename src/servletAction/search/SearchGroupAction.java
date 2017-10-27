package servletAction.search;

import actionForm.search.SearchGroupValidationForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import service.ContactService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchGroupAction extends Action {
    public ActionForward execute(final ActionMapping pMapping, ActionForm pForm,
                                 final HttpServletRequest pRequest, final HttpServletResponse pResponse) {

        final ContactService cs = new ContactService();
        final SearchGroupValidationForm lForm = (SearchGroupValidationForm) pForm;
        String groupName = lForm.getGroupName();
        final Object groups = cs.loadGroups(groupName);

        if (groups instanceof List) {
            pRequest.setAttribute("groups", groups);
            return pMapping.findForward("success");
        }
        else {
            return pMapping.findForward("error");
        }
    }
}
