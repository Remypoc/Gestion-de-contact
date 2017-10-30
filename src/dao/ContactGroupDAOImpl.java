package dao;

import domain.ContactGroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

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
        //TODO Update contactgroup

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
    public List<ContactGroup> getAll() {
        getCurrentSession().beginTransaction();
        List<ContactGroup> groups = getCurrentSession().createQuery(
                "from ContactGroup contactGroup ORDER BY groupName").list();
        getCurrentSession().close();
        return groups;
    }

    @Override
    public void delete(long id) {
        getCurrentSession().beginTransaction();
        ContactGroup group = getCurrentSession().get(ContactGroup.class, id);
        if (group != null)
            getCurrentSession().delete(group);
        getCurrentSession().getTransaction().commit();
        getCurrentSession().close();
    }

    @Override
    public List<ContactGroup> search(String groupName) {
        getCurrentSession().beginTransaction();
        List<ContactGroup> groups = getCurrentSession().createQuery(
                "from ContactGroup contactGroup WHERE groupName like :name ORDER BY groupName")
                .setParameter("name", String.format("%s%%", groupName))
                .list();
        getCurrentSession().close();
        return groups;
    }
}
