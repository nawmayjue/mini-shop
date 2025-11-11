package org.nmj.dao;

import org.nmj.model.User;

import java.util.List;

public interface UserDao {
    void save(User user) throws Exception;
    List<User> findAll()throws Exception;
    void update(User user) throws Exception;
    void delete(Long id) throws Exception;
    User findById(Long id) throws Exception;
}
