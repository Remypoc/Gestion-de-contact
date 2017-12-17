package dao;

import domain.Contact;
import domain.ContactGroup;
import exception.DAOException;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupDAOImpl extends HibernateDaoSupport implements GroupDAO {
    private HibernateTemplate hibernateTemplate;

    private void setHibernateTemplate(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

//    private SessionFactory sessionFactory;
//
//    public GroupDAOImpl() {
//        try {
//            sessionFactory = HibernateUtil.getSessionFactory();
//        } catch (NoClassDefFoundError e) {
//            System.err.println(e.getMessage());
//            sessionFactory = null;
//        }
//    }
//
//    private Session getCurrentSession() throws DAOException{
//        if (sessionFactory == null) {
//            System.err.println("getSessionFactory failed");
//            throw new DAOException("Connexion to database failed", "exception.connexion.database.failed");
//        }
//        try {
//            return sessionFactory.getCurrentSession();
//        } catch (HibernateException e) {
//            System.err.println(e.getMessage());
//            throw new DAOException("Connexion to database failed.", "exception.connexion.database.failed");
//        }
//    }

    @Override
    public void addGroup(ContactGroup group) throws DAOException {
//        Session session = getCurrentSession();
        try {
            getHibernateTemplate().save(group);
//            session.beginTransaction();
//            session.save(group);
//            session.getTransaction().commit();
//            session.close();
        } catch(HibernateException e) {
            System.err.println(e.getMessage());
//            session.close();
            throw new DAOException(
                    String.format("Failed to save contact %s", group.getGroupName()), "exception.add.group.failed");
        }
    }

    @Override
    public void updateGroup(ContactGroup group) throws DAOException {
//        Session session = getCurrentSession();
        try {
//            session.beginTransaction();
//            ContactGroup groupToUpdate = session.get(ContactGroup.class, group.getGroupId());
//            groupToUpdate.setGroupName(group.getGroupName());
//            session.update(groupToUpdate);
            getHibernateTemplate().bulkUpdate("update ContactGroup set groupName = ? where groupId = ?",
                    group.getGroupName(), group.getGroupId());
//                .setParameter("groupName", group.getGroupName())
//                .setParameter("groupId", group.getGroupId())
//                .executeUpdate();
//            session.getTransaction().commit();
//            session.close();
        } catch(HibernateException e) {
            System.err.println(e.getMessage());
//            session.close();
            throw new DAOException(
                    String.format("Failed to update group %s", group.getGroupName()), "exception.update.group.failed");
        }
    }

    @Override
    public ContactGroup getGroup(long id) throws DAOException {
//        Session session = getCurrentSession();
        try {
//            session.beginTransaction();
//            ContactGroup group = session.get(ContactGroup.class, id);
            ContactGroup group = getHibernateTemplate().get(ContactGroup.class, id);
//            session.close();
            return group;
        } catch(HibernateException e) {
            System.err.println(e.getMessage());
//            session.close();
            throw new DAOException(String.format("Failed to load group"), "exception.get.group.failed");
        }
    }

    @Override
    public ContactGroup getGroupWithContacts(long id) throws DAOException {
//        Session session = getCurrentSession();
        try {
//            session.beginTransaction();
//            ContactGroup group = session
//                    .createQuery("SELECT contactGroup FROM ContactGroup contactGroup " +
//                            "LEFT JOIN FETCH contactGroup.contacts contact " +
//                            "WHERE contactGroup.groupId=:id", ContactGroup.class)
//                    .setParameter("id", id)
//                    .getSingleResult();
//            session.close();
            ContactGroup group = getHibernateTemplate().get(ContactGroup.class, id);
            getHibernateTemplate().initialize(group.getContacts());
            return group;
        } catch(HibernateException e) {
            System.err.println(e.getMessage());
//            session.close();
            throw new DAOException(String.format("Failed to load group"), "exception.get.group.failed");
        }
    }

    @Override
    public Set<ContactGroup> getAllGroups() throws DAOException {
//        Session session = getCurrentSession();
        try {
//            session.beginTransaction();
//            List<ContactGroup> groups =
//                    session.createQuery(
//                            "from ContactGroup contactGroup ORDER BY groupName",
//                            ContactGroup.class).list();
//            session.close();
            List groups = getHibernateTemplate().find("from ContactGroup contactGroup ORDER BY groupName");
            return new HashSet<>(groups);
        } catch(HibernateException e) {
            System.err.println(e.getMessage());
//            session.close();
            throw new DAOException(String.format("Failed to load groups"), "exception.get.groups.failed");
        }
    }

    @Override
    public void deleteGroup(long id) throws DAOException {
//        Session session = getCurrentSession();
        try {
//            session.beginTransaction();
//            ContactGroup group = session.load(ContactGroup.class, id);
//            session.delete(group);
//            session.getTransaction().commit();
//            session.close();
            getHibernateTemplate().delete(new ContactGroup(id));
        } catch(HibernateException e) {
            System.err.println(e.getMessage());
//            session.close();
            throw new DAOException(String.format("Failed to delete group"), "exception.delete.group.failed");
        }
    }

    @Override
    public void deleteContactFromGroup(long contactId, long groupId) throws DAOException {
//        Session session = getCurrentSession();
        try {
//            session.beginTransaction();
//            ContactGroup group = session.get(ContactGroup.class, groupId);
//
//            group.getContacts().remove(new Contact(contactId));
//            session.update(group);
//
//            session.getTransaction().commit();
//            session.close();
            ContactGroup group = getHibernateTemplate().get(ContactGroup.class, groupId);
            group.getContacts().remove(new Contact(contactId));
            getHibernateTemplate().update(group);

        } catch(HibernateException e) {
            System.err.println(e.getMessage());
//            session.close();
            throw new DAOException(String.format("Failed to delete contact from group"), "exception.delete.contact.from.group.failed");
        }
    }

    @Override
    public Set<ContactGroup> searchGroups(String groupName) throws DAOException{
//        Session session = getCurrentSession();
        try {
//            session.beginTransaction();
//            List<ContactGroup> groups =
//                    session
//                            .createQuery("from ContactGroup contactGroup " +
//                                            "WHERE groupName like lower(:name) ORDER BY groupName",
//                                    ContactGroup.class)
//                            .setParameter("name", String.format("%s%%", groupName))
//                            .list();
//            session.close();
            List groups = getHibernateTemplate()
                            .find("from ContactGroup contactGroup " +
                                            "WHERE groupName like lower(?) ORDER BY groupName", String.format("%s%%", groupName));
            return new HashSet<>(groups);
        } catch(HibernateException e) {
            System.err.println(e.getMessage());
//            session.close();
            throw new DAOException(String.format("Failed to search for group: %s", groupName), "exception.search.groups.failed");
        }
    }

    @Override
    public ContactGroup searchContactInGroup(final long groupId, final String name) throws DAOException{
////        Session session = getCurrentSession();
//        try {
////            session.beginTransaction();
////            ContactGroup group = session.load(ContactGroup.class, groupId);
//            ContactGroup group = getHibernateTemplate().load(ContactGroup.class, groupId);
//            String[] token = name.split(" ");
//            List contacts;
//            if (token.length < 2) {
////                contacts = session
////                        .createFilter(group.getContacts(),
////                                "WHERE firstName LIKE :name OR lastName LIKE :name")
////                        .setParameter("name", String.format("%s%%", name))
////                        .list();
//                contacts = getHibernateTemplate().fin
//            } else {
//                contacts = session
//                        .createFilter(group.getContacts(),
//                                "WHERE firstName LIKE :token1 AND lastName LIKE :token2 " +
//                                        "OR firstName LIKE :token2 AND lastName LIKE :token1")
//                        .setParameter("token1", String.format("%s%%", token[0]))
//                        .setParameter("token2", String.format("%s%%", token[1]))
//                        .list();
//            }
//            session.close();
//            group.setContacts(new HashSet<Contact>(contacts));
//            return group;
//        } catch(HibernateException e) {
//            System.err.println(e.getMessage());
//            session.close();
//            throw new DAOException(String.format("Failed to search for contact: %s in group", name), "exception.search.contact.in.group.failed");
//        }
        return null;
    }

    @Override
    public Set<Contact> getContactsNotInSet(Set<Contact> contacts) throws DAOException{
////        Session session = getCurrentSession();
//        try {
////            session.beginTransaction();
//            List<Long> contactsId = contacts.stream().map(Contact::getId)
//                    .collect(Collectors.toList());
//            contactsId.add(0L); // Add dummy value to not crash hibernate (LOL)
//
//            List<Contact> contactsNotInSet = session
//                    .createQuery("select contact from Contact contact " +
//                            "where contact.id not in :contacts", Contact.class)
//                    .setParameter("contacts", contactsId)
//                    .list();
//            session.close();
//            return new HashSet<>(contactsNotInSet);
//        } catch(HibernateException e) {
//            System.err.println(e.getMessage());
//            session.close();
//            throw new DAOException(String.format("Failed to load contacts not in current group"), "exception.load.contacts.in.group.failed");
//        }
        return null;
    }

    @Override
    public void addContactToGroup(long contactId, long groupId) throws DAOException {
//        Session session = getCurrentSession();
        try {
//            session.beginTransaction();
//            ContactGroup group = session.get(ContactGroup.class, groupId);
            ContactGroup group = getHibernateTemplate().get(ContactGroup.class, groupId);

            if (group != null) {
                group.getContacts().add(new Contact(contactId));
//                session.update(group);
                getHibernateTemplate().update(group);
            } else {
//                session.close();
                throw new DAOException(String.format("Failed to add contact to group, group no longer exists"), "exception.add.contact.to.group.failed");
            }
//            session.getTransaction().commit();
//            session.close();
        } catch(HibernateException e) {
            System.err.println(e.getMessage());
//            session.close();
            throw new DAOException(String.format("Failed to add contact to group"), "exception.add.contact.to.group.failed");
        }
    }
}
