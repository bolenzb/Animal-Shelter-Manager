package com.techelevator.controller;

import com.techelevator.model.PetImage;
import com.techelevator.model.dto.PetIdDto;
import com.techelevator.model.dto.PetImageDto;
import com.techelevator.service.PetImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@CrossOrigin
public class PetImageController {
    @Autowired
    private PetImageService petImageService;

    @Value("${url}")
    private String sourceUrl;

    @PostMapping(path="/upload")
    public ResponseEntity<PetImageDto> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {

        String uploadImage = petImageService.uploadImage(file);
        PetImageDto petImageDto = new PetImageDto( buildUrl(uploadImage) );

        return ResponseEntity.status(HttpStatus.CREATED).body(petImageDto);

    }

    /*
   This endpoint is the means by which a client can access any uploaded image.
   For example, an HTML document can have:
   <img src="{your-domain}/image/{your-image-name}/>
     */
    @GetMapping("/pet_image/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {

        byte[] imageData = petImageService.downloadImage(fileName);

        if (imageData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
                .body(imageData);

    }

    @GetMapping("/all-images")
    public ResponseEntity<List<PetImageDto>> retrieveAllImages() {

        List<PetImageDto> imageNames = new ArrayList<>();

        for(String nameStr : petImageService.retrieveAllImages()) {
            imageNames.add( new PetImageDto( buildUrl(nameStr)) );

        }

        return ResponseEntity.status(HttpStatus.OK).body(imageNames);
    }

    @PostMapping("/all-pet-images")
    public ResponseEntity<List<PetImageDto>> retrieveAllImagesOfPet(@Valid @RequestBody PetIdDto petId) {

        List<PetImageDto> imageNames = new ArrayList<>();

        for(String nameStr : petImageService.retrieveAllImagesOfPet(petId)) {
            imageNames.add( new PetImageDto( buildUrl(nameStr)) );

        }

        return ResponseEntity.status(HttpStatus.OK).body(imageNames);
    }

    @DeleteMapping("/deleteImage/{name}")
    public void deleteImage(@PathVariable String name) {
        petImageService.deletePetImage(name);
    }

    private String buildUrl(String imageName) {
        return sourceUrl + "pet_image/" + imageName;
    }
    
}
