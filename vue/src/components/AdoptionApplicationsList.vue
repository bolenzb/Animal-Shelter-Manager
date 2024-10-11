<template>
    <div>
      <table>
          <thead>
              <tr>
                  <th>Pending Adoption Applications List</th>
              </tr>
          </thead>
          <tbody>
              <tr v-for="app in pendingAdoptionApps" :key="app.petId">
                  <td>{{app.firstName}} {{ app.lastName }}</td>
                  <button @click="approveApp(app)">Approve</button>
                  <button @click="denyApp(app.applicantId)">Deny</button>
              </tr>
          </tbody>
      </table>
      <p><router-link v-bind:to="{ name: 'admin' }">Admin Portal</router-link></p>
    </div>
  </template>
  
  <script>
  import applicationService from '../services/ApplicationService';
  import petService from '../services/PetService';
  
  export default {
      data() {
        return {
          appStatus: {
            statusId: '',
            applicantId: '',
          },  
          petStatus: {
            statusId: 2,
            petId: '',
          },  
          owner: {
              ownerName: '',
              petId: '',
              adoptionDate: new Date(),
          }
        }
      },
      methods: {
          approveApp(app) {
            this.appStatus.statusId = 2;
            this.appStatus.applicantId = app.applicantId;
            this.petStatus.petId = app.petId;
            this.owner.ownerName = app.firstName + ' ' + app.lastName;
            this.owner.petId = app.petId;
            // this.owner.adoptionDate = new Date();
            petService.updatePetStatus(this.petStatus);
            applicationService.updateAdoptionApp(this.appStatus);
            petService.addOwner(this.owner);
            window.alert('Adoption Approved!');
          },
          denyApp(id) {
            this.appStatus.statusId = 3;
            this.appStatus.applicantId = id;
            applicationService.updateAdoptionApp(this.appStatus);
            window.alert('Adoption Denied');
          }
      },
      computed: {
          pendingAdoptionApps() {
              this.$store.commit('GET_PENDING_ADOPTION_APPS')
              return this.$store.state.pendingAdoptionApps;
          },
      },
      // created() {
      //     this.$store.commit('GET_PENDING_APPS', this.$store.state.applications);
      // }
  }
  </script>
  
  <style scoped>

  div {
    margin: 0 auto;
    padding: 0.5rem;
  }
  
  </style>