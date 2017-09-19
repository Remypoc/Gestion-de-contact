package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DAOContact {
	
	private final static String RESOURCE_JDBC = "java:comp/env/jdbc/gestion_contact";

	public String addContact(final long id, final String firstName, final String lastName, final String email) {
		try {
			final Context lContext = new InitialContext();
			final DataSource lDataSource = (DataSource) lContext.lookup(RESOURCE_JDBC);
			final Connection lConnection  = lDataSource.getConnection();
			
			// adding a new contact
			final PreparedStatement lPreparedStatementCreation = 
					
			lConnection.prepareStatement("INSERT INTO CONTACT(ID, FIRSTNAME, LASTNAME, EMAIL) VALUES(?, ?, ?, ?)");
			
			lPreparedStatementCreation.setLong(1, id);
			lPreparedStatementCreation.setString(2, firstName);
			lPreparedStatementCreation.setString(3, lastName);
			lPreparedStatementCreation.setString(4, email);
			lPreparedStatementCreation.executeUpdate();
			
			return null;
		} catch (NamingException e) {
		
			return "NamingException : " + e.getMessage();
		
		} catch (SQLException e) {

			return "SQLException : " + e.getMessage();
			
		}
	}
}
