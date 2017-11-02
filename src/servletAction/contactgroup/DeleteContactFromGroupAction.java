package servletAction.contactgroup;

import actionForm.contactgroup.DeleteContactFromGroupValidationForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import service.ContactGroupService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteContactFromGroupAction extends Action {
    @Override
    public ActionForward execute(
            ActionMapping mapping, ActionForm pform,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        DeleteContactFromGroupValidationForm form = (DeleteContactFromGroupValidationForm) pform;
        final ContactGroupService service = ServiceFactory.getContactGroupService();
        service.deleteContact(form.getGroupId(), form.getContactId());

        return mapping.findForward("success");
    }
}
