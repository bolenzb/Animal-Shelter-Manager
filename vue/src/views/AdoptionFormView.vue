<template>
    <main id="apply" class="text-center">
        <h2>Adoption Form</h2>
        <h3>Please Complete Form Below:</h3>
        <form v-on:submit.prevent="submit">
        <div class="form-input-group">
            <label for="firstName">First Name </label>
            <input type="text" id="firstName" v-model="app.firstName" required autofocus />
        </div>
        <div class="form-input-group">
            <label for="lastName">Last Name </label>
            <input type="text" id="lastName" v-model="app.lastName" required autofocus />
        </div>
        <div class="form-input-group">
            <label for="phoneNumber">Phone Number </label>
            <input type="tel" id="phoneNumber" v-model="app.phoneNumber" required autofocus />
        </div>
        <div class="form-input-group">
            <label for="email">Email </label>
            <input type="email" id="email" v-model="app.email" required autofocus />
        </div>
        <fieldset class="form-input-group">
            <legend>Have you ever owned any pets? </legend>
            <div>
                <label for="Q1">Yes </label>
                <input type="radio" id="Q1Choice1" :value=true v-model="app.responseOne" required/>
            </div>
            <div>
                <label for="Q1">No </label>
                <input type="radio" id="Q1Choice2" :value=false v-model="app.responseOne" required/>
            </div>
        </fieldset>
        <fieldset class="form-input-group">
            <legend>Do you currently have any pets? </legend>
            <div>
                <label for="Q2">Yes </label>
                <input type="radio" id="Q2Choice1" :value=true v-model="app.responseTwo" required/>
            </div>
            <div>
                <label for="Q2">No </label>
                <input type="radio" id="Q2Choice2" :value=false v-model="app.responseTwo" required/>
            </div>
        </fieldset>
        <fieldset class="form-input-group">
            <legend>Do you have children? </legend>
            <div>
                <label for="Q3">Yes </label>
                <input type="radio" id="Q3Choice1" :value=true v-model="app.responseThree" required/>
            </div>
            <div>
                <label for="Q3">No </label>
                <input type="radio" id="Q3Choice2" :value=false v-model="app.responseThree" required/>
            </div>
        </fieldset>
        <fieldset class="form-input-group">
            <legend>Do you have a yard/space for the pet? </legend>
            <div>
                <label for="Q4">Yes </label>
                <input type="radio" id="Q4Choice1" :value=true v-model="app.responseFour" required/>
            </div>
            <div>
                <label for="Q4">No </label>
                <input type="radio" id="Q4Choice2" :value=false v-model="app.responseFour" required/>
            </div>
        </fieldset>
        <br>
        <button type="submit">Submit Application</button>
        </form>
    </main>
</template>

<script>
import applicationService from '../services/ApplicationService';

export default {
    data() {
        return {
            app: {
                petId: '',
                firstName: '',
                lastName: '',
                phoneNumber: '',
                email: '',
                responseOne: '',
                responseTwo: '',
                responseThree: '',
                responseFour: ''
            },
        };
    },
    methods: {
        submit() {
            applicationService.adopt(this.app)
                .then((response) => {
                    if (response.status == 201) {
                        window.alert("Application submitted successfully");
                        this.$store.commit('CLEAR_PET_ID');
                        this.$router.push("/");
                    }
            });
        }
    },
    created() {
      this.app.petId = parseInt(localStorage.getItem('petId'));  
    }
}
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
    background-color: skyblue;
}

main {
    background: #fff;
    margin: 2rem auto; /* Center horizontally */
    padding: 2rem; /* Add padding */
    box-shadow:
      0 2px 4px 0 rgb(0 0 0 / 20%),
      0 2.5rem 5rem 0 rgb(0 0 0 / 10%);
    height: 1000px; /* Make height responsive */
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