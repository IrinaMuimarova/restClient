package ru.muimarova.rest.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.muimarova.rest.model.User;
import ru.muimarova.rest.service.UserService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private UserService userService;

    //add mapping for GET / users

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
        System.out.println(responseEntity);
        return responseEntity;
    }

    // add mapping for GET /users/{userID}

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    // add mapping for POST /users - add new User

    @PostMapping("/users")
    public ResponseEntity<User> addCustomer(@RequestBody User theUser) {
        userService.saveUser(theUser);
        return new ResponseEntity<>(theUser, HttpStatus.OK);
    }

    // add mapping for PUT /users - update existing user
    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User theUser) {
        userService.saveUser(theUser);
        return new ResponseEntity<>(userService.getUserById(theUser.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("Delete user id - " + userId, HttpStatus.OK);
    }
}
