package servletAction.search;

import actionForm.search.SearchContactValidationForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import service.ContactService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchContactAction extends Action {

    public ActionForward execute(final ActionMapping pMapping, ActionForm pForm,
                                 final HttpServletRequest pRequest, final HttpServletResponse pResponse) {
        final ContactService cs = new ContactService();
        final SearchContactValidationForm lForm = (SearchContactValidationForm) pForm;
        String search = lForm.getSearch();
        final Object contacts = cs.loadContacts(search);

        if (contacts instanceof List) {
            pRequest.setAttribute("contacts", contacts);
            return pMapping.findForward("success");
        } else {
            return pMapping.findForward("error");
        }
    }
}
