package com.techelevator.controller;

import com.techelevator.dao.AdoptionDao;
import com.techelevator.dao.JdbcPendingUserDao;
import com.techelevator.dao.PendingUserDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.dto.*;
import com.techelevator.security.jwt.JWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Email;

// Controller for non-volunteer/Admin based requests

@RestController
@CrossOrigin
public class NonVolunteerController {
    @Autowired
    private PendingUserDao pendingUserDao;

    @Autowired
    private AdoptionDao adoptionDao;

    @Autowired
    private UserDao userDao;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/apply", method = RequestMethod.POST)
    public void apply(@Valid @RequestBody SubmitPendingUserDto newApplicant) {
        try {
            // prevents applicant from submitting multiple apps with same email
            if (pendingUserDao.getApplicantByEmail(newApplicant.getEmail()) != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is already a pending application with this email");
            } else {
                pendingUserDao.createPendingUser(newApplicant);
            }
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Volunteer application failed.");
        }
    }

    @PostMapping(path="/hasEmail")
    public EmailResponseDto hasEmail(@Valid @RequestBody EmailDto email) {
        return userDao.databaseHasUser(email);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path="/adoptionApp")
    public void adoptionApp(@RequestBody AdoptionAppDto app) {
        try {
            // prevents applicant from submitting multiple apps with same email
            if (adoptionDao.getAdoptionAppByEmail(app.getEmail()) != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is already a pending application with this email");
            } else {
                adoptionDao.addAdoptionApp(app);
            }
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Volunteer application failed.");
        }
    }

}
