package servletAction.contactgroup;

import actionForm.contactgroup.DeleteContactGroupValidationForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import service.ContactGroupService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteContactGroupAction extends Action {
    public ActionForward execute(
            final ActionMapping pMapping, ActionForm pForm,
            final HttpServletRequest pRequest, final HttpServletResponse pResponse) {

        DeleteContactGroupValidationForm form = (DeleteContactGroupValidationForm) pForm;
        final ContactGroupService service = ServiceFactory.getContactGroupService();
        service.delete(form.getGroupId());

        return pMapping.findForward("success");
    }
}
