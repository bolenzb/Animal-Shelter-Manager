import { createStore as _createStore } from 'vuex';
import axios from 'axios';
import applicationService from '../services/ApplicationService';
import volunteerService from '../services/VolunteerService';
import petService from '../services/PetService';

export function createStore(currentToken, currentUser, currentApp, currentPetId) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      app: currentApp || {},
      status: {},
      petId: currentPetId || '',
      applications: [],
      pendingAdoptionApps: [],
      allApplications: [],
      userArray: [],
      petArray: [],
      recentPets: []
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      SET_CURRENT_APP(state, app) {
        state.app = app;
        localStorage.setItem('app', JSON.stringify(app));
      },
      STORE_PET_ID(state, petId) {
        state.petId = petId;
        localStorage.setItem('petId', JSON.stringify(petId));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      CLEAR_APP(state) {
        localStorage.removeItem('app');
        state.app = {};
      },
      CLEAR_PET_ID(state) {
        localStorage.removeItem('petId');
        state.petId = '';
      },
      GET_PENDING_APPS (state) {
        applicationService.getPendingApps().then((response) => {
          state.applications = response.data;
        });
      },
      GET_PENDING_ADOPTION_APPS (state) {
        applicationService.getPendingAdoptionApps().then((response) => {
          state.pendingAdoptionApps = response.data;
        });
      },
      GET_ALL_APPS (state) {
        applicationService.getAllApps().then((response) => {
          state.allApplications = response.data;
        });
      },
      GET_USERS (state) {
        volunteerService.getUsers().then((response) => {
          state.userArray = response.data;
        });
      },
      GET_PETS (state) {
        petService.getPets().then((response) => {
          state.petArray = response.data;
        });
      },
      GET_RECENT_PETS (state) {
        petService.getMostRecentPets().then((response) => {
          state.recentPets = response.data;
        });
      },
      CHANGE_STATUS(state, status) {
        state.status = status;
        localStorage.setItem('status', JSON.stringify(status));
      },
      CLEAR_STATUS(state) {
        localStorage.removeItem('status');
        state.status = {};
      }
    },
  });
  return store;
}
