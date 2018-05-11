package dao;

import domain.*;
import exception.DAOException;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactDAOImpl implements ContactDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public String addContact(Contact contact) throws DAOException {
		try {
			Session session = getSessionFactory().openSession();
			session.beginTransaction();
			session.persist(contact);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException(
					String.format("Failed to create contact or company %s: ", contact.getFullName()), "exception.add.contact.failed");
		}
		return null;
	}

	@Override
	public Object updateContact(final Contact contact) throws DAOException {
		try {
			Address address = contact.getAddress();
			if (!address.isValid()) {
				contact.setAddress(null);
			}
			System.out.println("updateContact");

			Session session = getSessionFactory().openSession();
			session.beginTransaction();
			session.merge(contact);
			session.getTransaction().commit();
			session.close();

			if (!address.isValid() && address.getId() != 0) {
				deleteAddress(address.getId());
			}
		} catch (StaleObjectStateException e) {
			e.printStackTrace();
			throw new DAOException(
					String.format("Failed to update contact or company %s: ", contact.getFullName()), "exception.edit.contact.lock.failed");
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException(
					String.format("Failed to update contact or company %s: ", contact.getFullName()), "exception.connexion.database.failed");
		}
		return null;
	}

	@Override
	public Object deleteContact(final Long id) throws DAOException {
		try {

			Session session = getSessionFactory().openSession();
			session.beginTransaction();
			Contact contact = session.get(Contact.class, id);
			for (ContactGroup group : contact.getBooks()) {
				group.removeContact(id);
				session.update(group);
			}
			session.delete(contact);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException(
					String.format("Failed to delete contact or company %d : ", id), "exception.delete.contact.failed");
		}
		return null;
	}

	public Object deleteAddress(long id) throws DAOException {
		try {

			Session session = getSessionFactory().openSession();
			session.beginTransaction();
			Address address = session.get(Address.class, id);
			session.delete(address);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException(
					String.format("Failed to delete contact or company %d : ", id), "exception.delete.address.failed");
		}
		return null;
	}

	@Override
	public Object addPhoneNumber(final PhoneNumber phoneNumber) throws DAOException {

		try {
			Session session = getSessionFactory().openSession();
			session.beginTransaction();
			long id = (long) session.save(phoneNumber);
			phoneNumber.setId(id);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException(
					String.format("Failed to delete contact or company %s : ", phoneNumber.getPhoneNumber()), "exception.add.phone.failed");
		}
		return null;
	}

	@Override
	public Set<Contact> loadContacts() throws DAOException {
		if (sessionFactory != null) {
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				List contacts = session.createQuery(
						"from Contact contact ORDER BY lastName").list();
				session.close();
				return new HashSet<>(contacts);
			} catch (HibernateException e) {
				e.printStackTrace();
				throw new DAOException("Failed to load contacts and companies : ", "exception.load.contacts.failed");
			}
		}
		// Exemple with spring hibernateTemplate
		// List contacts = getHibernateTemplate().find("from Contact contact ORDER BY lastName");
		return null;
	}

	@Override
	/**
	 * loadContacts with criteria example !
	 */
	public Object loadContacts(String search) throws DAOException {

		try {
			Session session = getSessionFactory().openSession();
			session.beginTransaction();
			List contacts = session.createCriteria(Contact.class)
					.add(Restrictions.or(Restrictions.like("lastName", String.format("%s%%", search)),
							Restrictions.like("lastName", String.format("%s%%", search))))
					.setCacheable(true).list();
			session.close();
			return contacts;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException("Failed to load contacts and companies : ", "exception.load.contacts.failed");
		}
		// Exemple with HQL
		/*
		Object contacts = session.createQuery(
				"from Contact contact WHERE lastName like :name or firstName like :name or email like :name " +
						"ORDER BY lastName")
				.setParameter("name", String.format("%s%%", search))
				.list();
		*/
		// With hibernateTemplate
		/*
		List contacts = getHibernateTemplate().find("from Contact contact WHERE lastName like ? or firstName like :name or email like ? ORDER BY lastName",
				String.format("%s%%", search), String.format("%s%%", search));
		*/
	}

	@Override
	public Object loadContact(Long id) throws DAOException {
		try {
			Session session = getSessionFactory().openSession();
			Contact contact = session.get(Contact.class, id);
			Hibernate.initialize(contact.getAddress());
			Hibernate.initialize(contact.getPhones());
			session.close();
			return contact;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException("Failed to load contacts : ", "exception.load.contact.failed");
		}

		/* Exemple with Spring getHibernateTemplate
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				CriteriaBuilder builder = session.getCri();
        		CriteriaQuery<Contact> criteria = builder.createQuery(Contact.class);
        		Root<Contact> root = criteria.from(Contact.class);
        		criteria.select(root).where(builder.equal(root.get("id"), id));
        		Contact contact = session.createQuery(criteria).getSingleResult();

        		// Used to force load object
        		Hibernate.initialize(contact.getAddress());
        		Hibernate.initialize(contact.getPhones());
				return contact;
				return null;
			}
		});
		*/
	}

	@Override
	public void refreshContact(Contact contact) {
		if (contact != null) {
			Session session = sessionFactory.openSession();
			try {
				session.refresh(contact);
			} catch (HibernateException e) {
				System.err.println(e.getMessage());
				contact.setAddress(null);
				contact.getPhones().clear();
				session.refresh(contact);
			}
			Hibernate.initialize(contact.getAddress());
			Hibernate.initialize(contact.getPhones());
			session.close();
		}
	}
}
