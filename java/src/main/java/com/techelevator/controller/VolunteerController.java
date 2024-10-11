package com.techelevator.controller;

import com.techelevator.dao.JdbcUserDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.dto.RegisterUserDto;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class VolunteerController {

    @Autowired
    private UserDao userDao;

    @GetMapping(path="/users")
    public List<User> getUsers() {
        return userDao.getUsers();
    }


    @PutMapping(path="/firstLogin")
    public void firstLogin(@Valid @RequestBody RegisterUserDto user) {
        userDao.firstLoginUpdate(user);
    }
}
