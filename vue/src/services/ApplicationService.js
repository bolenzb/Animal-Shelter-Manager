import axios from "axios";

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});
  
  export default {
    
    getPendingApps() {
      return http.get('/applications')
    },

    getAllApps() {
      return http.get('/allApplications')
    },

    getPendingAdoptionApps() {
      return http.get('/adoptionApps')
    },

    apply(user) {
      return http.post('/apply', user)
    }, 

    updateApplication(status) {
      return http.put('/updateApp', status)
    },

    updateAdoptionApp(status) {
      return http.put('/updateAdoptionApp', status)
    },

    adopt(app) {
      return http.post('/adoptionApp', app)
    }
  
  }