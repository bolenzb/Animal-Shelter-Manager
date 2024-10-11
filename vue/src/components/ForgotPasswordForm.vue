<template>
  <main>
    <div role="alert" v-if="emailCheckError">
        {{ emailCheckErrorMsg }}
    </div>
    <form @submit.prevent="emailCheck">
        <div class="form-input-group">
            <label for="email">Please Enter Email </label>
            <input type="email" id="email" v-model="emailObject.email" required autofocus />
        </div>
        <button type="submit">Submit</button>
    </form>
    <div v-if="this.isValidEmail.hasEmail">Password Reset Email Sent.</div>
  </main>
</template>

<script>
import authService from '../services/AuthService';

export default {
  data () {
      return {
          emailObject: {
            email: '',
          },
          isValidEmail: {},
          emailCheckError: false,
          emailCheckErrorMsg: 'That email is not in our database.',
      }
    },
    methods: {
        emailCheck() {
            // check if email is in database
            authService.hasEmail(this.emailObject)
                .then(response => {
                    if (response.status == 200) {
                        this.isValidEmail = response.data;
                        if ( this.isValidEmail.hasEmail ) {
                            // send email to user with password reset link
                            this.emailCheckError = false;
                            console.log('email is in database');
                        } else {
                            // display error message
                            this.emailCheckError = true;
                        }
                    }
                })
                .catch(error => {
                const response = error.response;

                if (response.status >= 400) {
                    console.log('error');
                }
                });
            // if email is in database, send email to user with password reset link
            // if email is not in database, display error message
        }
    }
}
</script>

<style scoped>

main {
  margin: 0 auto;
  padding: 0.5rem;
}

</style>