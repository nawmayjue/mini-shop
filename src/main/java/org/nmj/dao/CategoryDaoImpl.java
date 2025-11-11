package org.nmj.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.nmj.config.HibernateUtil;
import org.nmj.model.Category;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao{
    @Override
    public void save(Category category) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        }
    }

    @Override
    public List<Category> findAll() throws Exception {
         try(Session session = HibernateUtil.getSessionFactory().openSession()){
             return session
                     .createQuery("from Category", Category.class)
                     .list();
         }
    }

    @Override
    public void update(Category category) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.update(category);
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
    public Category findById(Long id) throws Exception{
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session
                    .get(Category.class, id);
        }
    }
}
