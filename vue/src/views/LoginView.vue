<template>
  <div id="login">
    <form v-on:submit.prevent="login">
      <h1 >Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username </label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password </label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <br>
      <button type="submit">Sign in</button>
      <!-- add option to reset password -->
    <router-link v-bind:to="{ name: 'forgotPassword' }">Forgot your password?</router-link>
  </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/user");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>

h1 {
  text-shadow: 2px 2px black;
}

form {
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
    height: 450px; /* Make height responsive */
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