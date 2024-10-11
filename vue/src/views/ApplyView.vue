<template>
    <div id="apply" class="text-center">
        <h2>Volunteer Application</h2>
        <h3>Please Complete Form Below:</h3>
       <form v-on:submit.prevent="submit">
        <div class="form-input-group">
            <label for="firstName">First Name </label>
            <input type="text" id="firstName" v-model="user.firstName" required autofocus />
        </div>
        <div class="form-input-group">
            <label for="lastName">Last Name </label>
            <input type="text" id="lastName" v-model="user.lastName" required autofocus />
        </div>
        <div class="form-input-group">
            <label for="phoneNumber">Phone Number </label>
            <input type="text" id="phoneNumber" v-model="user.phoneNumber" required autofocus />
        </div>
        <div class="form-input-group">
            <label for="email">Email </label>
            <input type="email" id="email" v-model="user.email" required autofocus />
        </div>
        <div class="form-input-group">
            <label for="birthday">Birthday </label>
            <input type="date" id="birthday" v-model="user.birthday" required autofocus />
        </div>
        <div class="form-input-group">
            <label for="description">Enter previous experience related to animals or working at an animal shelter below: </label>
            <br>
            <textarea type="text" id="description" rows="4" cols="60" v-model="user.description" required autofocus></textarea>
        </div>
        <button type="submit">Submit Application</button>
        </form>
    </div>
</template>

<script>
import ApplicationService from '../services/ApplicationService';

export default {
    data() {
        return {
            user: {
                firstName: '',
                lastName: '',
                phoneNumber: '',
                email: '',
                birthday: '',
                description: ''
            },
        };
    },
    methods: {
        submit() {
            ApplicationService
                .apply(this.user)
                .then((response) => {
                if (response.status == 201) {
                    window.alert("Application submitted successfully");
                    this.$router.push({
                        path: '/',
                        query: { registration: 'success' },
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
    }
    
};
</script>

<style scoped>

h3 {
    font-size: 23px;
}

h2 {
    text-shadow: 1px 1px rgb(119, 18, 119);
}

form {
    font-family: Georgia, 'Times New Roman', Times, serif;
    font-size: 20px;
    font-weight: lighter;
    line-height: 1.6;
}

body {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
    background-color: lightblue;
}

div#apply {
    background: #fff;
    margin: 2rem auto; /* Center horizontally */
    padding: 2rem; /* Add padding */
    box-shadow:
      0 2px 4px 0 rgb(0 0 0 / 20%),
      0 2.5rem 5rem 0 rgb(0 0 0 / 10%);
    height: 600px; /* Make height responsive */
    aspect-ratio: 1; /* Ensure it maintains a square aspect ratio */
    display: flex; /* Use flexbox */
    flex-direction: column; /* Stack children vertically */
    justify-content: left; /* Left align */
    align-items: left; /* Left align */
    text-align: left; /* Left align text */
    padding: 20px; 
    width: 500px;
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