package com.techelevator.dao;

import com.techelevator.model.dto.EmailDto;
import com.techelevator.model.dto.EmailResponseDto;
import com.techelevator.model.dto.RegisterUserDto;
import com.techelevator.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);
    EmailResponseDto databaseHasUser(EmailDto email);

    User createUser(RegisterUserDto user);

    void firstLoginUpdate(RegisterUserDto user);
}
