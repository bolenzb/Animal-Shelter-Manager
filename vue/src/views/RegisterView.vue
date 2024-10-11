<template>
  <main id="register" class="text-center">
    <form v-on:submit.prevent="register">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="firstName">First Name </label>
        <input type="text" id="firstName" v-model="user.firstName" required />
      </div>
      <div class="form-input-group">
        <label for="lastName">Last Name </label>
        <input type="text" id="lastName" v-model="user.lastName" required />
      </div>
      <div class="form-input-group">
        <label for="email">Email Address </label>
        <input type="email" id="email" v-model="user.email" required />
      </div>
      <div class="form-input-group">
        <label for="username">Username </label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password </label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password </label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button type="submit">Create Account</button>
      <!-- <p><router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p> -->
    </form>
  </main>
</template>

<script>
import authService from '../services/AuthService';
import applicationService from '../services/ApplicationService';

export default {
  data() {
    return {
      update: {
        statusId: '',
        pendingId: '',
      },
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
        firstName: '',
        lastName: '',
        email: ''
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              applicationService.updateApplication(this.update)
                .then((response) => {
                  if (response.status >= 200) {
                    this.$store.commit('CLEAR_STATUS');
                    this.$store.commit('CLEAR_APP');
                    this.$router.push({
                      path: '/applications',
                      query: { registration: 'success' },
                    });
                  }
                });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
  created() {
    this.user.firstName = JSON.parse(localStorage.getItem('app')).firstName;
    this.user.lastName = JSON.parse(localStorage.getItem('app')).lastName;
    this.user.email = JSON.parse(localStorage.getItem('app')).email;
    this.update = JSON.parse(localStorage.getItem('status'));
  }
};
</script>

<style scoped>
h1 {
  text-shadow: 2px 2px black;
}

main {
    font-family: Georgia, 'Times New Roman', Times, serif;
    font-size: 20px;
    font-weight: lighter;
    line-height: 2;
    background: #fff;
    margin: 2rem auto; /* Center horizontally */
    padding: 2rem; /* Add padding */
    box-shadow:
      0 2px 4px 0 rgb(0 0 0 / 20%),
      0 2.5rem 5rem 0 rgb(0 0 0 / 10%);
    height: 505px; /* Make height responsive */
    aspect-ratio: 1; /* Ensure it maintains a square aspect ratio */
    display: flex; /* Use flexbox */
    flex-direction: column; /* Stack children vertically */
    justify-content: left; /* Left align */
    align-items: left; /* Left align */
    text-align: left; /* Left align text */
    padding: 20px; 
    width: 500px;
}

body {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
    background-color: skyblue;
}

button {
    font-size: 15px;
    padding: 10px 24px;
    border-radius: 8px;
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
    background: rgb(119, 18, 119);
    color: white;
}
</style>
