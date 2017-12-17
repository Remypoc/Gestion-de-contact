package dao;

import domain.Address;
import domain.Contact;
import domain.PhoneNumber;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class ContactDAOImpl extends HibernateDaoSupport implements ContactDAO {
//	private SessionFactory sessionFactory;
//
//	public ContactDAOImpl() {
//		try {
//			sessionFactory = HibernateUtil.getSessionFactory();
//		} catch (NoClassDefFoundError e) {
//			System.err.println(e.getMessage());
//			sessionFactory = null;
//		}
//	}

	private HibernateTemplate hibernateTemplate;

	private void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public String addContact(Contact contact) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//
//		session.beginTransaction();
//		session.persist(contact);
//
//		session.getTransaction().commit();
//		session.close();
		getHibernateTemplate().persist(contact);
        return null;
    }

    public Object updateContact(final Contact contact) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//
//        session.beginTransaction();
//        session.saveOrUpdate(contact);
//
//        session.getTransaction().commit();
//        session.close();
		getHibernateTemplate().saveOrUpdate(contact);
        return null;
    }

    public Object deleteContact(final Long id) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//
//        Contact contact = session.get(Contact.class, id);
//
//        session.delete(contact);
//        session.getTransaction().commit();
//        session.close();
        Contact contact  = getHibernateTemplate().get(Contact.class, id);
        getHibernateTemplate().delete(contact);

        return null;
    }

    public Object addAddress(final Address address) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//
//		session.beginTransaction();
//
//		long id = (long) session.save(address);
//		address.setId(id);
//
//		session.getTransaction().commit();
//		session.close();

		getHibernateTemplate().save(address);
        return null;
    }

    public Object addPhoneNumber(final PhoneNumber phoneNumber) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//
//		session.beginTransaction();
//
//		long id = (long) session.save(phoneNumber);
//		phoneNumber.setId(id);
//
//		session.getTransaction().commit();
//		session.close();
		getHibernateTemplate().save(phoneNumber);

		return null;
	}

	public Set<Contact> loadContacts() {
//		if (sessionFactory != null) {
//			Session session = sessionFactory.getCurrentSession();
//			session.beginTransaction();
//			List<Contact> contacts = session.createQuery(
//					"from Contact contact ORDER BY lastName", Contact.class).list();
//			session.close();
//
//			return new HashSet<>(contacts);
//		}
		List contacts = getHibernateTemplate().find("from Contact contact ORDER BY lastName");
		return new HashSet<>(contacts);
	}

    public Object loadContacts(String search) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        Object contacts = session.createQuery(
//                "from Contact contact WHERE lastName like :name or firstName like :name or email like :name " +
//                        "ORDER BY lastName")
//                .setParameter("name", String.format("%s%%", search))
//                .list();
//        session.close();
		List contacts = getHibernateTemplate().find("from Contact contact WHERE lastName like ? or firstName like :name or email like ? ORDER BY lastName",
				String.format("%s%%", search), String.format("%s%%", search));
        return contacts;
    }


    public Object loadContact(Long id) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Contact> criteria = builder.createQuery(Contact.class);
//        Root<Contact> root = criteria.from(Contact.class);
//        criteria.select(root).where(builder.equal(root.get("id"), id));
//        Contact contact = session.createQuery(criteria).getSingleResult();
//
//        // Used to force load object
//        Hibernate.initialize(contact.getAddress());
//        Hibernate.initialize(contact.getPhones());
//        session.close();
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
//				CriteriaBuilder builder = session.getCri();
//        		CriteriaQuery<Contact> criteria = builder.createQuery(Contact.class);
//        		Root<Contact> root = criteria.from(Contact.class);
//        		criteria.select(root).where(builder.equal(root.get("id"), id));
//        		Contact contact = session.createQuery(criteria).getSingleResult();
//
//        		// Used to force load object
//        		Hibernate.initialize(contact.getAddress());
//        		Hibernate.initialize(contact.getPhones());
//				return contact;
				return null;
			}
		});
    }
}
