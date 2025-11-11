package org.nmj.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.nmj.config.HibernateUtil;
import org.nmj.model.Category;
import org.nmj.model.User;

import java.util.List;

public class UserDaoImpl implements UserDao{
    @Override
    public void save(User user) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public List<User> findAll() throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session
                    .createQuery("from User", User.class)
                    .list();
        }
    }

    @Override
    public void update(User user) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }

    }

    @Override
    public void delete(Long id) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Category category = session.get(Category.class, id);
            session.delete(category);
            transaction.commit();
        }
    }

    @Override
    public User findById(Long id) throws Exception{
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session
                    .get(User.class, id);
        }
    }
}
