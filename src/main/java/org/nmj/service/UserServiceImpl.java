package org.nmj.service;

import org.hibernate.Session;
import org.nmj.config.HibernateUtil;
import org.nmj.dao.UserDao;
import org.nmj.dao.UserDaoImpl;
import org.nmj.dto.UserDto;
import org.nmj.mapper.UserMapper;
import org.nmj.model.Category;
import org.nmj.model.User;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{
    private UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public void create(String username, String password, int userType) throws Exception {
        userDao.save(
                new User().initialize(username, password, userType)
        );
    }

    @Override
    public List<UserDto> retrieveAll() throws Exception {
        List<User> users = userDao.findAll();
        /*
        List<CategoryDto> categoryDtoList = new ArrayList<>()'
        for(Category category : categories){
            categoryDtoList.add(CategoryMapper.toDto(category));
        }

        */
        return users.stream().map(UserMapper::toDto).toList();
    }

    @Override
    public Optional<User> findByUsername(String username) throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            User user = session.
                    createQuery("from User where username = :username", User.class)
                    .setParameter("username", username).uniqueResult();
            return Optional.ofNullable(user);
        }
    }

    @Override
    public void edit(Long id, String username, String password) throws Exception {
        User user = userDao.findById(id);
        user.setUsername(username);
        user.setPassword(password);
        userDao.update(user);

    }

    @Override
    public void delete(Long id) throws Exception {
        userDao.delete(id);
    }

    @Override
    public UserDto retrieveOne(Long id) throws Exception {
        User user = userDao.findById(id);
        if (user == null) {
            throw new Exception("User with ID " + id + " not found.");
        }
        return UserMapper.toDto(user);
    }



}
