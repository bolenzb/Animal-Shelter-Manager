package com.techelevator.service;

import com.techelevator.dao.PetImageDao;
import com.techelevator.model.PetImage;
import com.techelevator.model.dto.PetIdDto;
import com.techelevator.model.dto.PetImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PetImageService {

    @Autowired
    private PetImageDao petImageDao;

    public String uploadImage(MultipartFile imageFile) throws IOException, NullPointerException {

        PetImage petImage = new PetImage();
        petImage.setName(imageFile.getOriginalFilename());
        petImage.setType(imageFile.getContentType());
        petImage.setData(imageFile.getBytes());
        petImage.setPetId(Integer.parseInt(imageFile.getOriginalFilename().substring(0, imageFile.getOriginalFilename().length() - 6)));


        petImageDao.save(petImage);
        return imageFile.getOriginalFilename();
    }

    public byte[] downloadImage(String imageName) {
        return this.petImageDao.findByName(imageName).getData();
    }

    public List<String> retrieveAllImages() {
        return this.petImageDao.retrieveImageNames();
    }

    public List<String> retrieveAllImagesOfPet(PetIdDto petId) {
        return this.petImageDao.retrieveAPetsImages(petId);
    }

    public void deletePetImage(String name) {
        petImageDao.deleteImageByName(name);
    }
}
