<template>
    <main id="addPet" class="text-center">
        <h3>Add Pet Information</h3>
        <form v-on:submit.prevent="submit">
            <div class="form-input-group">
                <label for="name">Name </label>
                <input type="text" id="name" v-model="pet.name" required autofocus />
            </div>
            <div class="form-input-group">
                <label for="gender">Gender </label>
                <select id="pet-genders" v-model="pet.gender" required autofocus>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Unknown">Unknown</option>
                </select>
            </div>
            <div class="form-input-group">
                <label for="weight">Weight </label>
                <input type="float" id="weight" v-model="pet.weight" required autofocus />
            </div>
            <div class="form-input-group">
                <label for="species">Species </label>
                <input type="text" id="species" v-model="pet.species" required autofocus />
            </div>
            <div class="form-input-group">
                <label for="breed">Breed </label>
                <input type="text" id="breed" v-model="pet.breed" required autofocus />
            </div>
            <div class="form-input-group">
                <label for="color">Color </label>
                <input type="text" id="color" v-model="pet.color" required autofocus />
            </div>            
            <div class="form-input-group">
                <label for="birthday">Birthday </label>
                <input type="date" id="birthday" v-model="pet.birthday" required autofocus />
            </div>
            <div class="form-input-group">
                <label for="description">Description </label>
                <br>
                <textarea type="text" id="description" rows="4" cols="60" v-model="pet.description" required autofocus></textarea>
            </div>
            <button type="submit">Add Pet</button>
        </form>
        <div v-show="petId != ''">
            <h4>Newest Pet's Id is: {{ petId }}</h4>
        </div>
        <h3>Add Pet Picture(s)</h3>
        <div class="form-input-group">
            <label for="petImage">Upload Image of Pet </label>
            <input type="file" id="pet_image" ref="file" v-on:change="updateObject" required autofocus />
            <button type="button" :disabled="imageObject == null" v-on:click="uploadImage">Upload Image</button>
        </div>
    </main>  
</template>

<script>
import PetService from '../services/PetService';
import PetImageService from '../services/PetImageService';

export default {
    data() {
        return {
            imageObject: null,
            pet: {
                name: '',
                gender: '',
                weight: '',
                species: '',
                breed: '',
                color: '',
                birthday: '',
                description: ''
            },
            petId: '',
        };
    },
    methods: {
        updateObject() {
            this.imageObject = this.$refs.file.files[0];
        }, 
        submit() {
            PetService
              .addPet(this.pet)
              .then((response) => {
                if (response.status == 201) {
                    window.alert("Pet added successfully");
                    this.pet = {
                        name: '',
                        gender: '',
                        weight: '',
                        species: '',
                        breed: '',
                        color: '',
                        birthday: '',
                        description: ''
                    }
                    PetService.getLastPetId()
                        .then((response) => {
                            if (response.status >= 200) {
                                this.petId = response.data;
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
        },
        uploadImage() {
            PetImageService.uploadPetImage(this.imageObject)
                .then((response) => {
                    if (response.status === 201) {
                        window.alert("Image uploaded successfully");
                        // console.log(response.data);
                        this.$refs.file.value = null;
                        this.imageObject = null;
                    }
                })    
        }
    }

};
</script>

<style scoped>

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
    height: 765px; /* Make height responsive */
    aspect-ratio: 1; /* Ensure it maintains a square aspect ratio */
    display: flex; /* Use flexbox */
    flex-direction: column; /* Stack children vertically */
    justify-content: left; /* Left align */
    align-items: left; /* Left align */
    text-align: left; /* Left align text */
    padding: 20px; 
    width: 530px;
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

h4 {
    margin: 5px 0px 0px 0px;
}

</style>