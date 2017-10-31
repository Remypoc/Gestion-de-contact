package actionForm.search;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class SearchContactValidationForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    // Can search by lastname, firstname, email
    private String search = null;

    public String getSearch() {
        return search;
    }

    public void setSearch(String research) {
        this.search = research;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.search = null;
    }

    public ActionErrors validate(
            ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        if (getSearch() == null || getSearch().length() < 1) {
            errors.add("search", new ActionMessage("contact.home.research.error"));
        }
        return errors;
    }
}
