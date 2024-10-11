package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.model.Pet;
import com.techelevator.model.dto.AddPetDto;
import com.techelevator.model.dto.OwnerInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PetController {

    @Autowired
    private PetDao petDao;

    @GetMapping(path="/pets")
    public List<Pet> getPets() {
        return petDao.getPets();
    }

    @GetMapping(path="/recentPets")
    public List<Pet> getMostRecentPets() {
        return petDao.getMostRecentPets();
    }

    @GetMapping(path="/lastPetId")
    public int getLastPetId() {
        return petDao.getLastPetId();
    }

    @GetMapping(path="/pet/{id}")
    public Pet getPetById(@PathVariable int id) {
        Pet pet = petDao.getPetById(id);
        return pet;
    }

    @PutMapping(path="/updatePet/{id}")
    public void updatePet(@PathVariable int id, @RequestBody Pet pet) {
        pet.setPetId(id);
        petDao.updatePets(pet);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path="/addPet")
    public void addPet(@RequestBody AddPetDto pet) {
        petDao.addPet(pet);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path="/addOwner")
    public void addOwner(@RequestBody OwnerInfoDto owner) {
        petDao.addOwner(owner);
    }
}
