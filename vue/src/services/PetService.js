import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {

  addPet(pet) {
    return http.post('/addPet', pet)
  },

  getLastPetId() {
    return http.get('/lastPetId')
  },

  getPets() {
    return http.get('/pets')
  },

  getMostRecentPets() {
    return http.get('/recentPets')
  },

  getPet(petId) {
    return http.get(`/pet/${petId}`)
  },

  updatePetStatus(status) {
    return http.put(`/updatePet/${status.petId}`, status)
  },

  addOwner(owner) {
    return http.post('/addOwner', owner)
  }
}