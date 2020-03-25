package ru.muimarova.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.muimarova.rest.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private RestTemplate restTemplate;
    private String url = "http://localhost:8080/api/users/";


    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImp(RestTemplateBuilder builder) {
        this.restTemplate = builder.basicAuthentication("admin", "admin").build();
    }

    @Override
    public List<User> getAllUser() {
        return restTemplate.getForObject(url, List.class);
    }

    @Override
    public UserDetails getUserByLogin(String username) {
        return restTemplate.getForObject(url + "/auth/" + username, User.class);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        restTemplate.postForObject(url, user, User.class);
    }

    @Override
    public void deleteUser(Long id) {
        restTemplate.delete(url + id);
    }

    @Override
    public User getUserById(Long userId) {
        return restTemplate.getForObject(url + userId, User.class);
    }

}