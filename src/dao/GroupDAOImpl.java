package dao;

import domain.Contact;
import domain.ContactGroup;
import exception.DAOException;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GroupDAOImpl implements GroupDAO {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addGroup(ContactGroup group) throws DAOException {
        // getHibernateTemplate().save(group);
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(group);
            session.getTransaction().commit();
            session.close();
        } catch(HibernateException e) {
            System.err.println(e.getMessage());
            throw new DAOException(
                    String.format("Failed to save contact %s", group.getGroupName()), "exception.add.group.failed");
        }
    }

    @Override
    public void updateGroup(ContactGroup group) throws DAOException {
        // getHibernateTemplate().bulkUpdate("update ContactGroup set groupName = ? where groupId = ?",
        //         group.getGroupName(), group.getGroupId());
        //     .setParameter("groupName", group.getGroupName())
        //     .setParameter("groupId", group.getGroupId())
        //     .executeUpdate();
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            ContactGroup groupToUpdate = session.get(ContactGroup.class, group.getGroupId());
            groupToUpdate.setGroupName(group.getGroupName());
            session.update(groupToUpdate);
            session.close();
        } catch(HibernateException e) {
            System.err.println(e.getMessage());
            throw new DAOException(
                    String.format("Failed to update group %s", group.getGroupName()), "exception.update.group.failed");
        }
    }

    @Override
    public ContactGroup getGroup(long id) throws DAOException {
        // ContactGroup group = getHibernateTemplate().get(ContactGroup.class, id);
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            ContactGroup group = session.get(ContactGroup.class, id);
            session.close();
            return group;
        } catch(HibernateException e) {
            System.err.println(e.getMessage());
            throw new DAOException(String.format("Failed to load group"), "exception.get.group.failed");
        }
    }

    @Override
    public ContactGroup getGroupWithContacts(long id) throws DAOException {
        // ContactGroup group = getHibernateTemplate().get(ContactGroup.class, id);
        // getHibernateTemplate().initialize(group.getContacts());
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            ContactGroup group = session.get(ContactGroup.class, id);
            Hibernate.initialize(group.getContacts());
            session.close();
            return group;
        } catch(HibernateException e) {
            System.err.println(e.getMessage());
            throw new DAOException(String.format("Failed to load group"), "exception.get.group.failed");
        }
    }

    @Override
    public Set<ContactGroup> getAllGroups() throws DAOException {
        // List groups = getHibernateTemplate().find("from ContactGroup contactGroup ORDER BY groupName");
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List groups =
                    session.createQuery(
                            "from ContactGroup contactGroup ORDER BY groupName").list();
            session.close();
            return new HashSet<>(groups);
        } catch(HibernateException e) {
            System.err.println(e.getMessage());
            throw new DAOException(String.format("Failed to load groups"), "exception.get.groups.failed");
        }
    }

    @Override
    public void deleteGroup(long id) throws DAOException {
        // getHibernateTemplate().delete(new ContactGroup(id));
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            ContactGroup group = session.load(ContactGroup.class, id);
            session.delete(group);
            session.getTransaction().commit();
            session.close();
        } catch(HibernateException e) {
            System.err.println(e.getMessage());
            throw new DAOException(String.format("Failed to delete group"), "exception.delete.group.failed");
        }
    }

    @Override
    public void deleteContactFromGroup(long contactId, long groupId) throws DAOException {
        // ContactGroup group = getHibernateTemplate().get(ContactGroup.class, groupId);
        // group.getContacts().remove(new Contact(contactId));
        // getHibernateTemplate().update(group);
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            ContactGroup group = session.get(ContactGroup.class, groupId);

            group.getContacts().remove(new Contact(contactId));
            session.update(group);

            session.getTransaction().commit();
            session.close();

        } catch(HibernateException e) {
            System.err.println(e.getMessage());
            session.close();
            throw new DAOException(String.format("Failed to delete contact from group"), "exception.delete.contact.from.group.failed");
        }
    }

    @Override
    public Set<ContactGroup> searchGroups(String groupName) throws DAOException{
//        List groups = getHibernateTemplate()
//                .find("from ContactGroup contactGroup " +
//                                "WHERE groupName like lower(?) ORDER BY groupName", String.format("%s%%", groupName));
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List groups =
                    session
                            .createQuery("from ContactGroup contactGroup " +
                                            "WHERE groupName like lower(:name) ORDER BY groupName")
                            .setParameter("name", String.format("%s%%", groupName))
                            .list();
            session.close();
            return new HashSet<>(groups);
        } catch(HibernateException e) {
            System.err.println(e.getMessage());
            session.close();
            throw new DAOException(String.format("Failed to search for group: %s", groupName), "exception.search.groups.failed");
        }
    }

    @Override
    public ContactGroup searchContactInGroup(final long groupId, final String name) throws DAOException{
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String[] token = name.split(" ");
            List contacts;
            ContactGroup group = session.get(ContactGroup.class, groupId);
            if (token.length < 2) {
                contacts = session
                        .createFilter(group.getContacts(),
                                "WHERE firstName LIKE :name OR lastName LIKE :name")
                        .setParameter("name", String.format("%s%%", name))
                        .list();
            } else {
                contacts = session
                        .createFilter(group.getContacts(),
                                "WHERE firstName LIKE :token1 AND lastName LIKE :token2 " +
                                        "OR firstName LIKE :token2 AND lastName LIKE :token1")
                        .setParameter("token1", String.format("%s%%", token[0]))
                        .setParameter("token2", String.format("%s%%", token[1]))
                        .list();
            }
            session.close();
            group.setContacts(new HashSet<Contact>(contacts));
            return group;
        } catch(HibernateException e) {
            System.err.println(e.getMessage());
            session.close();
            throw new DAOException(String.format("Failed to search for contact: %s in group", name), "exception.search.contact.in.group.failed");
        }
    }

    @Override
    public void addContactToGroup(long contactId, long groupId) throws DAOException {
//        ContactGroup group = getHibernateTemplate().get(ContactGroup.class, groupId);
//        getHibernateTemplate().update(group);
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            ContactGroup group = session.get(ContactGroup.class, groupId);

            if (group != null) {
                group.getContacts().add(new Contact(contactId));
                session.update(group);
            } else {
                session.close();
                throw new DAOException(String.format("Failed to add contact to group, group no longer exists"), "exception.add.contact.to.group.failed");
            }
            session.getTransaction().commit();
            session.close();
        } catch(HibernateException e) {
            System.err.println(e.getMessage());
            session.close();
            throw new DAOException(String.format("Failed to add contact to group"), "exception.add.contact.to.group.failed");
        }
    }
}
