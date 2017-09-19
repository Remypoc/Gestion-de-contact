package domain;

import java.util.Set;

public class Contact {
	
	private long id;   
	private String firstName;
	private String lastName;
	private String email;
	private Set<PhoneNumber> phones;
	private Address address; 
		
	public Contact() {
		// TODO Auto-generated constructor stub
	}
	
	public Contact(long id, String firstName, String lastName, String email, 
			Set<PhoneNumber> phones, Address address) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phones = phones;
		this.address = address;
	}

	public String getFullName() {
		return String.format("%s %s", firstName, lastName);
	}
	
	/**
	 * @return Email
	 */
	public String getEmail() {
	    return email;
	}

	/**
	 * @return First Name
	 */
	public String getFirstName() {
	  return firstName;
	}

	/** 
     * @return Last name
     */
    public String getLastName() {
    	return lastName;
    }

    /**
     * @param string Sets the Email
     */
    public void setEmail(String string) {
    	email = string;
    }

    /**
     * @param string Sets the First Name
     */
    public void setFirstName(String string) {
    	firstName = string;
    }

    /**
     * @param string sets the Last Name
     */
    public void setLastName(String string) {
    	lastName = string;
    }

    /**
     * @return ID Returns ID
     */
    public long getId() {
    	return id;
    }

    /**
     * @param l Sets the ID
     */
    public void setId(long l) {
    	id = l;
    }
}
