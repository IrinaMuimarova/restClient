package ru.muimarova.rest.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.muimarova.rest.model.User;

import java.util.List;

public interface UserService {
    List getAllUser();

    UserDetails getUserByLogin(String username);

    void saveUser(User user);

    void deleteUser(Long id);

    User getUserById(Long userId);
}
