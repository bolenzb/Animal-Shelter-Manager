<template>
  <main class="home">
      <div v-show="this.hasNotChangedPassword">
        <section>
          <h1>Welcome, thank you for volunteering!</h1>
          <p>It looks like this is your first time logging into your new account!</p>
          <p>Please fill out the following form to change your password.</p>
          <div role="alert" v-if="registrationErrors">
            {{ registrationErrorMsg }}
          </div>
          <form @submit.prevent="firstLoginComplete">
            <div class="form-input-group">
              <label for="password">Password </label>
              <input type="password" id="password" v-model="updatedUser.password" required />
            </div>
            <div class="form-input-group">
              <label for="confirmPassword">Confirm Password </label>
              <input type="password" id="confirmPassword" v-model="updatedUser.confirmPassword" required />
            </div>
            <button type="submit" id="button">Change Password</button>
          </form>
        </section>
      </div>
    <nav>
      <div v-if="isAdmin">
        <p><router-link v-bind:to="{ name: 'admin' }">Admin Portal</router-link></p>
      </div>
    </nav>
    <user-list />
    </main>
</template>

<script>
import UserList from '../components/UserList.vue';
import volunteerService from '../services/VolunteerService';

export default {
  components: { UserList },
  data() {
    return {
      hasNotChangedPassword: false,
      isAdmin: false,
      updatedUser: {
        username: '',
        password: '',
        confirmPassword: '',
        role: '',
      },
      registrationErrors: false,
      registrationErrorMsg: 'Password & Confirm Password do not match.',
    }
  },
  methods: {
    firstLoginComplete() {
      if (this.updatedUser.password != this.updatedUser.confirmPassword) {
        this.registrationErrors = true;
      } else {
        volunteerService.firstLogin(this.updatedUser)
          .then((response) => {
              if (response.status >= 200) {
                this.$store.commit('GET_USERS');
                this.hasNotChangedPassword = false;
                this.clearErrors();
              }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
    }
  },
  created() {
    this.isAdmin = localStorage.getItem('user').includes('ROLE_ADMIN') ? true : false;
    this.hasNotChangedPassword = JSON.parse(localStorage.getItem('user')).firstTime;
    this.updatedUser.username = JSON.parse(localStorage.getItem('user')).username;
    this.updatedUser.role = JSON.parse(localStorage.getItem('user')).authorities[0].name;
  },

};
</script>
<style scoped>

#confirmPassword {
  margin-right: 78px;
}

section {
  text-align: center;
}

p {
  font-family: 'Ariel';
}

label {
  font-family: 'Ariel';
  font-weight: bold;
}

.form-input-group {
  justify-content: center;
  padding-bottom: 8px;
}

main {
  margin: 0 auto;
  padding: 0.5rem;
}
</style>
