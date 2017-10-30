package actionForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class UpdatePhoneNumberValidationForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private long id = 0;   
	private String phoneNumber 	= null;
	private String phoneKind 	= null;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneKind() {
		return phoneKind;
	}
	
	public void setPhoneKind(String phoneKind) {
		this.phoneKind = phoneKind;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	  	this.id = 0;
	    this.phoneNumber	= null;
	    this.phoneKind 		= null;
	}

	public ActionErrors validate(
			ActionMapping mapping, HttpServletRequest request ) {
		ActionErrors errors = new ActionErrors();
		      
		if( getPhoneNumber() == null || getPhoneNumber().length() < 2 ) {
			errors.add("phonenumber", new ActionMessage("phonenumber.phonenumber.error"));
		}
		
		if( getPhoneKind()== null || getPhoneKind().length() < 1 ) {
			errors.add("phonekind", new ActionMessage("phonenumber.phonekind.error"));
		}
		
		return errors;
	}
}
