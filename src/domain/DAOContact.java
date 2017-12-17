package domain;

import exception.DAOException;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.OptimisticLockException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class DAOContact {
    private SessionFactory sessionFactory;

    public DAOContact() {
        try {
            sessionFactory = HibernateUtil.getSessionFactory();
        } catch (NoClassDefFoundError e) {
            System.err.println(e.getMessage());
            sessionFactory = null;
        }
    }

    /**
     * @param contact
     * @return null if contact was correctly being add or string exception if failure
     */
    public String addContact(Contact contact) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        session.persist(contact);

        session.getTransaction().commit();
        session.close();
        return null;
    }

    /**
     * @param contact Contact
     * @return return null or string exception
     */
    public Object updateContact(final Contact contact) throws DAOException {
        try {
            if (!contact.getAddress().isValid()) {

                // If address is registered
                if (contact.getAddress().getId() != 0) {
                    deleteAddress(contact.getAddress().getId());
                }
                contact.setAddress(null);
            }

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();

            session.beginTransaction();
            session.saveOrUpdate(contact);

            session.getTransaction().commit();
            session.close();
        } catch (OptimisticLockException e) {
            throw new DAOException(
                    String.format("Failed to update contact %s %s: ", contact.getFirstName(), contact.getLastName()), "exception.edit.contact.lock.failed");
        } catch (HibernateException e) {
            throw new DAOException(
                    String.format("Failed to update contact %s %s: ", contact.getFirstName(), contact.getLastName()), "exception.connexion.database.failed");
        }

        System.out.println(String.format("Update contact to database : %s", contact.toString()));
        return null;
    }

    /**
     * @param id Delete contact based on his id
     * @return return null or string exception
     */
    public Object deleteContact(final Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Contact contact = session.get(Contact.class, id);

        session.delete(contact);
        session.getTransaction().commit();
        session.close();
        return null;
    }

    public Object deleteAddress(long id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Address address = session.get(Address.class, id);

        session.delete(address);
        session.getTransaction().commit();
        session.close();
        return null;
    }


    /**
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

        return null;
    }

    /**
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

    public Set<Contact> loadContacts() {
        if (sessionFactory != null) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<Contact> contacts = session.createQuery(
                    "from Contact contact ORDER BY lastName")
                    .setCacheable(true)
                    .list();
            session.close();

            System.out.println(contacts.get(0).getFirstName());
            return new HashSet<>(contacts);
        }
        return null;
    }

    public Object loadContacts(String search) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Object contacts = session.createQuery(
                "from Contact contact WHERE lastName like :name or firstName like :name or email like :name " +
                        "ORDER BY lastName")
                .setParameter("name", String.format("%s%%", search))
                .setCacheable(true)
                .list();
        session.close();
        return contacts;
    }


    /*public Object loadContact(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Contact> criteria = builder.createQuery(Contact.class);
        Root<Contact> root = criteria.from(Contact.class);
        criteria.select(root).where(builder.equal(root.get("id"), id));
        Contact contact = session.createQuery(criteria)
                .setCacheable(true)
                .getSingleResult();

        // Used to force load object
        Hibernate.initialize(contact.getAddress());
        Hibernate.initialize(contact.getPhones());
        session.close();
        return contact;
    }*/

    public Object loadGroups() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<ContactGroup> groups = session.createQuery("from ContactGroup contactGroup")
                .setCacheable(true)
                .list();
        session.close();

        return groups;
    }

}
