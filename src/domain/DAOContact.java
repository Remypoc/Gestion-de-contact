package domain;

import org.hibernate.Session;
import util.HibernateUtil;

public class DAOContact {
	
	private final static String RESOURCE_JDBC = "java:comp/env/jdbc/gestion_contact";

	/**
	 * 
	 * @param contact
	 * @return null if contact was correctly being add or string exception if failure
	 */
	public String addContact(Contact contact) {

        System.out.println("contact : " + contact.toString());

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		session.persist(contact);

		session.getTransaction().commit();
		session.close();

		System.out.println(String.format("Add contact to database : %s", contact.toString()));

		return null;
	}
	
	/**
	 * 
	 * @param contact
	 * @return return null or string exception
	 */
	public Object searchContact(final Contact contact) {
		System.out.println(String.format("Searching contact : %s", contact.toString()));
		return null;
	}
	
	/**
	 * 
	 * @param contact
	 * @return return null or string exception
	 */
	public Object updateContact(final Contact contact) {
		System.out.println(String.format("Updating contact : %s", contact.toString()));
		return null;
	}
	
	/**
	 * 
	 * @param contact
	 * @return return null or string exception
	 */
	public Object deleteContact(final Contact contact) {
		System.out.println(String.format("Deleting contact : %s", contact.toString()));
		return null;
	}
	
	/**
	 * 
	 * @param address
	 * @return null if address was correctly being add or string exception if failure
	 */
	public Object addAddress(final Address address) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();

		long id = (long) session.save(address);
		address.setId(id);

		session.getTransaction().commit();
		session.close();

		System.out.println(String.format("Add address to database : %s", address.toString()));
		return null;
	}
	
	/**
	 * 
	 * @param address
	 * @return address being search or string exception if failure
	 */
	public Object searchAddress(Address address) {
		System.out.println(String.format("Searching address : %s", address.toString()));
		return null;
	}
	
	/**
	 * 
	 * @param address
	 * @return return null or string exception
	 */
	public Object updateAddress(Address address) {
		System.out.println(String.format("Updating address : %s", address.toString()));
		return null;
	}
	
	/**
	 * 
	 * @param address
	 * @return return null or string exception
	 */
	public Object deleteAddress(Address address) {
		System.out.println(String.format("Deleting address : %s", address.toString()));
		return null;
	}
	
	/**
	 * 
	 * @param phoneNumber
	 * @return null if phoneNumber was correctly being add or string exception if failure
	 */
	public Object addPhoneNumber(final PhoneNumber phoneNumber) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();

		long id = (long) session.save(phoneNumber);
		phoneNumber.setId(id);

		session.getTransaction().commit();
		session.close();

		System.out.println(String.format("Add phoneNumber to database : %s", phoneNumber.toString()));
		return null;
	}
	
	/**
	 * 
	 * @param phoneNumber
	 * @return phoneNumber being search or string exception if failure
	 */
	public Object searchPhoneNumber(PhoneNumber phoneNumber) {
		System.out.println(String.format("Searching phoneNumber : %s", phoneNumber.toString()));
		return null;
	}
	
	/**
	 * 
	 * @param phoneNumber
	 * @return return null or string exception
	 */
	public Object updatePhoneNumber(PhoneNumber phoneNumber) {
		System.out.println(String.format("Updating phoneNumber : %s", phoneNumber.toString()));
		return null;
	}
	
	/**
	 * 
	 * @param phoneNumber
	 * @return return null or string exception
	 */
	public Object deletePhoneNumber(PhoneNumber phoneNumber) {
		System.out.println(String.format("Deleting phoneNumber : %s", phoneNumber.toString()));
		return null;
	}

	public Object addContactGroup(ContactGroup contactGroup) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();

		long id = (long) session.save(contactGroup);
		contactGroup.setGroupId(id);

		session.getTransaction().commit();
		session.close();

		System.out.println(String.format("Add contactGroup : %s", contactGroup.toString()));
		return null;
	}

	public Object addContactToGroup(ContactGroup group, Contact contact) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();

		// TODO Comment faire pour mettre à jour l'association sans récupérer le group en entier sur la BDD ?
		ContactGroup groupTmp = session.get(ContactGroup.class, group.getGroupId());
		groupTmp.addContact(contact);

		session.saveOrUpdate(groupTmp);

		session.getTransaction().commit();
		session.close();

		System.out.println(String.format("Add contact %s to group %s",
				contact.toString(), group.toString()));
		return null;
	}
}
