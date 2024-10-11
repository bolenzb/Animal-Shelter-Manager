package com.techelevator.dao;

import com.techelevator.model.PetImage;
import com.techelevator.model.dto.PetIdDto;

import java.util.List;

public interface PetImageDao {

        void save(PetImage image);
        PetImage findByName(String name);
        List<String> retrieveImageNames();
        List<String> retrieveAPetsImages(PetIdDto petId);
        void deleteImageByName(String name);
    }
