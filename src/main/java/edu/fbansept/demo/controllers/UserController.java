package edu.fbansept.demo.controllers;

import edu.fbansept.demo.dao.UserDao;
import edu.fbansept.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/sign-in")
    ResponseEntity<User> signIn(@RequestBody User user) {

        user.setPassword(encoder.encode(user.getPassword()));
        userDao.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

}
