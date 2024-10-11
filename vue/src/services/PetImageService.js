import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {

  uploadPetImage(imageObj) {

    let formData = new FormData();
    formData.append('image', imageObj);
  
    return http.post("/upload", formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    
    });
  },

  retrieveAll() {
    return http.get("/all-images");
  },

  getAllImagesOfPet(petId) {
    return http.post(`/all-pet-images`, petId)
  },

  deletePetImage(name) {
    return http.delete(`/deleteImage/${name}`)
  }

}