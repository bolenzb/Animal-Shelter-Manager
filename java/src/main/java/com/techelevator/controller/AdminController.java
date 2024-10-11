package com.techelevator.controller;

import com.techelevator.dao.AdoptionDao;
import com.techelevator.dao.JdbcPendingUserDao;
import com.techelevator.dao.PendingUserDao;
import com.techelevator.dao.PetDao;
import com.techelevator.model.AdoptionApp;
import com.techelevator.model.PendingUser;
import com.techelevator.model.dto.AdoptAppStatusChangeDto;
import com.techelevator.model.dto.PetStatusChangeDto;
import com.techelevator.model.dto.StatusChangeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private PendingUserDao pendingUserDao;

    @Autowired
    private AdoptionDao adoptionDao;

    @Autowired
    private PetDao petDao;

    @GetMapping(path="/applications")
    public List<PendingUser> getAllPendingApplicants() {
        return pendingUserDao.getPendingApplicants();
    }

    @GetMapping(path="/allApplications")
    public List<PendingUser> getAllApplicants() {
        return pendingUserDao.getAllApplicants();
    }

    @GetMapping(path="/adoptionApps")
    public List<AdoptionApp> getAllPendingAdoptionApps() {
        return adoptionDao.getPendingAdoptionApps();
    }

    @PutMapping(path="/updateApp")
    public void updateApplicant(@Valid @RequestBody StatusChangeDto status) {
        pendingUserDao.updateApplication(status.getStatusId(), status.getPendingId());
    }

    @PutMapping(path="/updateAdoptionApp")
    public void updateApplicant(@Valid @RequestBody AdoptAppStatusChangeDto status) {
        adoptionDao.updateAdoptionApp(status);
    }

    @PutMapping(path="/updatePet")
    public void updatePet(@Valid @RequestBody PetStatusChangeDto status) {
        petDao.updatePetStatus(status);
    }
}
