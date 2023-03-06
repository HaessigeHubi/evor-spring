package com.evor.evor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.evor.evor.entity.User;
import com.evor.evor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    //GET Users
    @GetMapping("/users")
    List<User> getAllUsers(@RequestParam(required = false) String title) {
        List<User> users = new ArrayList<User>();

        if (title == null)
            userRepository.findAll().forEach(users::add);
        else
            userRepository.findByTitleContaining(title).forEach(users::add);

        if (users.isEmpty()) {
            return users;
        }

        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable("id") long id) {
        return userRepository.findById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(new User(user.getFirstname(), user.getLastname(), user.getBirthday(), user.getEmail()));
    }

    @DeleteMapping("/users/{id}")
    public HttpStatus deleteUser(@PathVariable("id") long id) {
        userRepository.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }

    @DeleteMapping("/users")
    public HttpStatus deleteAllUsers() {
        userRepository.deleteAll();

        return HttpStatus.NO_CONTENT;
    }
    //Find User by E-Mail. E-Mail should be unique
    @GetMapping("/users/email/{email}")
    User one(@PathVariable String email) throws Exception {
        List<User> checkUser = userRepository.findByEmail(email);

        if (checkUser.isEmpty()){
            return null;
        } else{
            return checkUser.get(0);
        }
    }
}
