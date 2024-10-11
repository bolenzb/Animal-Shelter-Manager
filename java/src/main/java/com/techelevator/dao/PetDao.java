package com.techelevator.dao;

import com.techelevator.model.Owner;
import com.techelevator.model.Pet;
import com.techelevator.model.dto.AddPetDto;
import com.techelevator.model.dto.OwnerInfoDto;
import com.techelevator.model.dto.PetStatusChangeDto;

import java.util.List;

public interface PetDao {
    List<Pet> getPets();
    List<Pet> getMostRecentPets();
    int getLastPetId();
    void updatePets(Pet pet);
    void updatePetStatus(PetStatusChangeDto status);
    Pet addPet(AddPetDto pet);
    Owner addOwner(OwnerInfoDto owner);
    Pet getPetById(int petId);
    Owner getOwnerById(int ownerId);

}
