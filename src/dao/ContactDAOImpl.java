package dao;

import domain.Address;
import domain.Contact;
import domain.PhoneNumber;
import exception.DAOException;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ContactDAOImpl implements ContactDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public String addContact(Contact contact) {
		Session session = getSessionFactory().openSession();

		session.beginTransaction();
		session.persist(contact);

		session.getTransaction().commit();
		session.close();
//		getHibernateTemplate().persist(contact);
		return null;
	}

	@Override
	public Object updateContact(final Contact contact) throws DAOException {

		if (contact.getAddress() != null && !contact.getAddress().isValid()) {
			contact.setAddress(null);
		}
		System.out.println("ContactDAOImpl => updateContact, version = " + contact.getVersion());

		try {

			Session session = getSessionFactory().openSession();
			session.beginTransaction();
			session.merge(contact);
			session.getTransaction().commit();
			session.close();

		} catch (OptimisticLockException e) {
			e.printStackTrace();
			throw new DAOException(
					String.format("Failed to update contact %s %s: ", contact.getFirstName(), contact.getLastName()), "exception.edit.contact.lock.failed");
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException(
					String.format("Failed to update contact %s %s: ", contact.getFirstName(), contact.getLastName()), "exception.connexion.database.failed");
		}
		return null;
	}

	@Override
	public Object deleteContact(final Long id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Contact contact = session.get(Contact.class, id);

		session.delete(contact);
		session.getTransaction().commit();
		session.close();
//        Contact contact  = getHibernateTemplate().get(Contact.class, id);
//        getHibernateTemplate().delete(contact);
		return null;
	}

	@Override
	public Object addAddress(final Address address) {
		Session session = getSessionFactory().openSession();

		session.beginTransaction();

		long id = (long) session.save(address);
		address.setId(id);

		session.getTransaction().commit();
		session.close();

//		getHibernateTemplate().save(address);
		return null;
	}

	@Override
	public Object addPhoneNumber(final PhoneNumber phoneNumber) {
		Session session = getSessionFactory().openSession();

		session.beginTransaction();

		long id = (long) session.save(phoneNumber);
		phoneNumber.setId(id);

		session.getTransaction().commit();
		session.close();
//		getHibernateTemplate().save(phoneNumber);

		return null;
	}

	@Override
	public Set<Contact> loadContacts() {
		if (sessionFactory != null) {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			List contacts = session.createQuery(
					"from Contact contact ORDER BY lastName").list();
			session.close();

			return new HashSet<>(contacts);
		}
		// Exemple with spring hibernateTemplate
		// List contacts = getHibernateTemplate().find("from Contact contact ORDER BY lastName");
		return null;
	}

	@Override
	/**
	 * loadContacts with criteria example !
	 */
	public Object loadContacts(String search) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		List contacts = session.createCriteria(Contact.class)
				.add(Restrictions.or(Restrictions.like("lastName", String.format("%s%%", search)),
						Restrictions.like("lastName", String.format("%s%%", search))))
				.setCacheable(true).list();
		session.close();

		// Exemple with HQL
		/*
		Object contacts = session.createQuery(
				"from Contact contact WHERE lastName like :name or firstName like :name or email like :name " +
						"ORDER BY lastName")
				.setParameter("name", String.format("%s%%", search))
				.setCacheable(true)
				.list();
		*/
		// With hibernateTemplate
		/*
		List contacts = getHibernateTemplate().find("from Contact contact WHERE lastName like ? or firstName like :name or email like ? ORDER BY lastName",
				String.format("%s%%", search), String.format("%s%%", search));
		*/
		return contacts;
	}

	@Override
	/**
	 * loadContact with spring getHibernateTemplate example
	 */
	public Object loadContact(Long id) {
		Session session = getSessionFactory().openSession();
		Contact contact = session.get(Contact.class, id);
		Hibernate.initialize(contact.getAddress());
		Hibernate.initialize(contact.getPhones());
		session.close();
		System.out.println(contact);

		return contact;

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
				contact = session.get(Contact.class, contact.getId());
			}
			Hibernate.initialize(contact.getAddress());
			Hibernate.initialize(contact.getPhones());
			session.close();
		}
	}
}
