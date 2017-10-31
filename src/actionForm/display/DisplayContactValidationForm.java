package actionForm.display;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class DisplayContactValidationForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    private long id = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ActionErrors validate(
            ActionMapping mapping, HttpServletRequest request ) {
        ActionErrors errors = new ActionErrors();

        if(getId() < 1) {
            errors.add("id", new ActionMessage("contact.display.error"));
        }

        return errors;
    }
}
