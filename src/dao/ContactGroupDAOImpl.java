package dao;

import domain.Contact;
import domain.ContactGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.util.HashSet;
import java.util.List;

public class ContactGroupDAOImpl implements ContactGroupDAO {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(ContactGroup group) {
        getCurrentSession().beginTransaction();
        getCurrentSession().save(group);
        getCurrentSession().getTransaction().commit();
        getCurrentSession().close();
    }

    @Override
    public void update(ContactGroup group) {
        getCurrentSession().beginTransaction();
        getCurrentSession().update(group);
        getCurrentSession().getTransaction().commit();
        getCurrentSession().close();
    }

    @Override
    public ContactGroup get(long id) {
        getCurrentSession().beginTransaction();
        ContactGroup group = getCurrentSession().get(ContactGroup.class, id);
        getCurrentSession().close();
        return group;
    }

    @Override
    public ContactGroup getWithContacts(long id) {
        getCurrentSession().beginTransaction();
        ContactGroup group = getCurrentSession()
                .createQuery("SELECT contactGroup FROM ContactGroup contactGroup " +
                        "LEFT JOIN FETCH contactGroup.contacts contact " +
                        "WHERE contactGroup.groupId=:id", ContactGroup.class)
                .setParameter("id", id)
                .getSingleResult();
        getCurrentSession().close();
        return group;
    }

    @Override
    public List<ContactGroup> getAll() {
        getCurrentSession().beginTransaction();
        List<ContactGroup> groups = getCurrentSession().createQuery(
                "from ContactGroup contactGroup ORDER BY groupName", ContactGroup.class).list();
        getCurrentSession().close();
        return groups;
    }

    @Override
    public void delete(long id) {
        getCurrentSession().beginTransaction();
        ContactGroup group = getCurrentSession().load(ContactGroup.class, id);
        getCurrentSession().delete(group);
        getCurrentSession().getTransaction().commit();
        getCurrentSession().close();
    }

    @Override
    public List<ContactGroup> search(String groupName) {
        getCurrentSession().beginTransaction();
        List<ContactGroup> groups = getCurrentSession().createQuery(
                "from ContactGroup contactGroup WHERE groupName like :name ORDER BY groupName", ContactGroup.class)
                .setParameter("name", String.format("%s%%", groupName))
                .list();
        getCurrentSession().close();
        return groups;
    }

    @Override
    public ContactGroup searchContactInGroup(final long groupId, final String name) {
        getCurrentSession().beginTransaction();
        ContactGroup group = getCurrentSession().load(ContactGroup.class, groupId);
        String[] token = name.split(" ");
        List contacts;
        if (token.length < 2) {
            contacts = getCurrentSession()
                    .createFilter(group.getContacts(),
                            "WHERE firstName LIKE :name OR lastName LIKE :name")
                    .setParameter("name", String.format("%s%%", name))
                    .list();
        } else {
            contacts = getCurrentSession()
                    .createFilter(group.getContacts(),
                            "WHERE firstName LIKE :token1 AND lastName LIKE :token2 " +
                                    "OR firstName LIKE :token2 AND lastName LIKE :token1")
                    .setParameter("token1", String.format("%s%%", token[0]))
                    .setParameter("token2", String.format("%s%%", token[1]))
                    .list();
        }
        getCurrentSession().close();
        group.setContacts(new HashSet<Contact>(contacts));
        return group;
    }
}
