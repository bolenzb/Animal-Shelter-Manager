package com.techelevator.dao;

import com.techelevator.model.AdoptionApp;
import com.techelevator.model.PendingUser;
import com.techelevator.model.dto.AdoptAppStatusChangeDto;
import com.techelevator.model.dto.AdoptionAppDto;

import java.util.List;

public interface AdoptionDao {

    void addAdoptionApp(AdoptionAppDto app);
    AdoptionApp getAdoptionAppByEmail(String email);
    List<AdoptionApp> getPendingAdoptionApps();
    void updateAdoptionApp(AdoptAppStatusChangeDto status);
}
