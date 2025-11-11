package org.nmj.service;

import org.nmj.dto.UserDto;
import org.nmj.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void create(String username, String password, int userType) throws Exception;
    List<UserDto> retrieveAll() throws Exception;
    Optional<User> findByUsername(String username) throws Exception;
    void edit(Long id, String username, String password) throws Exception;
    void delete(Long id) throws Exception;
    UserDto retrieveOne(Long id)throws Exception;
}
