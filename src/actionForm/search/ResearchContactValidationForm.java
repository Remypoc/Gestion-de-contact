package actionForm.search;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class ResearchContactValidationForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    private String research = null;

    public String getResearch() {
        return research;
    }

    public void setResearch(String research) {
        this.research = research;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.research = null;
    }

    public ActionErrors validate(
            ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        if (getResearch() == null || getResearch().length() < 1) {
            errors.add("research", new ActionMessage("contact.home.research.error"));
        }
        return errors;
    }
}
