<template>
    <div>
        <h1>{{ this.updatedPet.name }}</h1>
        <body>
            <div id="gallery">
                <img v-for="image in this.petImages" v-bind:key="image.id" v-bind:src="image.name"/>
            </div>
            <p><strong><em>Gender:</em></strong> {{ this.updatedPet.gender }}</p>
            <p><strong><em>Weight:</em></strong> {{ this.updatedPet.weight }}</p>
            <p><strong><em>Color:</em></strong> {{ this.updatedPet.color }}</p>
            <p><strong><em>Age:</em></strong> {{ Math.floor(daysBetween / 365) }}Yrs. {{ Math.floor((daysBetween / 30.436875) - (Math.floor(daysBetween / 365)*12)) }}Mo.</p>
            <p><strong><em>Species:</em></strong> {{ this.updatedPet.species }}</p>
            <p><strong><em>Breed:</em></strong> {{ this.updatedPet.breed }}</p>
            <p><strong><em>Description:</em></strong> {{ this.updatedPet.description }}</p>
            <div v-show="this.isLoggedIn">
                <h2>Update Pet Info</h2>
                <form v-on:submit.prevent="submit">
                    <div class="form-input-group">
                        <label for="name">Name </label>
                        <input type="text" id="name" v-model="updatedPet.name" required autofocus />
                    </div>
                    <div class="form-input-group">
                        <label for="gender">Gender </label>
                        <select id="pet-genders" v-model="updatedPet.gender" required autofocus>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                            <option value="Unknown">Unknown</option>
                        </select>
                    </div>
                    <div class="form-input-group">
                        <label for="weight">Weight </label>
                        <input type="float" id="weight" v-model="updatedPet.weight" required autofocus />
                    </div>
                    <div class="form-input-group">
                        <label for="species">Species </label>
                        <input type="text" id="species" v-model="updatedPet.species" required autofocus />
                    </div>
                    <div class="form-input-group">
                        <label for="breed">Breed </label>
                        <input type="text" id="breed" v-model="updatedPet.breed" required autofocus />
                    </div>
                    <div class="form-input-group">
                        <label for="color">Color </label>
                        <input type="text" id="color" v-model="updatedPet.color" required autofocus />
                    </div>            
                    <div class="form-input-group">
                        <label for="birthday">Birthday </label>
                        <input type="date" id="birthday" v-model="updatedPet.birthday" required autofocus />
                    </div>
                    <div class="form-input-group">
                        <label for="status">Status </label>
                        <input type="number" id="status" v-model="updatedPet.status" required autofocus />
                    </div>
                    <div class="form-input-group">
                        <label for="description">Description </label>
                        <textarea rows="4" cols="60" type="text" id="description" v-model="updatedPet.description" required autofocus></textarea>
                    </div>

                    <button type="submit">Update Pet</button>
                </form>
                <h2>Delete/Add Pet Photo</h2>
                <div class="form-input-group">
                    <label for="status">Delete Photo </label>
                    <input type="text" id="deletion" v-model="name" required autofocus /> &nbsp;
                    <button type="button" :disabled="name == ''" v-on:click="deleteImage">Delete Image</button>
                </div>
                <div class="form-input-group">
                    <label for="petImage">Upload Image of Pet </label>
                    <input type="file" id="pet_image" ref="file" v-on:change="updateObject" required autofocus />
                    <button type="button" :disabled="imageObject == null" v-on:click="uploadImage">Upload Image</button>
                </div>
            </div>
        </body>
        <footer>
            <div class="donate-button-container"><router-link class="donate-button" v-bind:to="{ name: 'adoptionForm' }">Adopt</router-link></div>
            <div class="donate-button-container"><router-link class="donate-button" v-bind:to="{ name: 'search' }">View All Pets</router-link></div>
        </footer>
    </div>
</template>

<script>
import petService from '../services/PetService';
import petImageService from '../services/PetImageService';

export default {
  data() {
    return {
      updatedPet: {},
      petImages: [],
      obj: {
        petId: '',
      },
      imageObject: null,
      isLoggedIn: false,
      name: '',
      registrationErrorMsg: '',
      date1: new Date(),
      date2: '',
    };
  },
  methods: {
      updateObject() {
        this.imageObject = this.$refs.file.files[0];
      }, 
      submit() {
        petService
          .updatePetStatus(this.updatedPet)
          .then((response) => {
            if (response.status >= 200) {
                window.alert("Pet updated successfully");
                petService.getPet(parseInt(localStorage.getItem('petId'))).then((response) => {
                  this.updatedPet = response.data;
                  this.date2 = new Date(response.data.birthday);
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
      deleteImage() {
        petImageService.deletePetImage(this.name)
            .then((response) => {
                if (response.status >= 200) {
                    window.alert("Image deleted successfully");
                    this.name = '';
                    this.retrievePhotos();
                }
            })
      },
      uploadImage() {
        petImageService.uploadPetImage(this.imageObject)
            .then((response) => {
                if (response.status === 201) {
                    window.alert("Image uploaded successfully");
                    this.$refs.file.value = null;
                    this.imageObject = null;
                    this.retrievePhotos();
                }
            })    
      },
      retrievePhotos() {
        petImageService.getAllImagesOfPet(this.obj).then((resp) => { this.petImages = resp.data; });
      },
    },
  computed: {
    daysBetween() {
      const diffTime = Math.abs(this.date1 - this.date2);
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
      return diffDays;
    },
  },  
  created() {
    petService.getPet(parseInt(localStorage.getItem('petId'))).then((response) => {
      this.updatedPet = response.data;
      this.date2 = new Date(response.data.birthday);
    });
    this.obj.petId = parseInt(localStorage.getItem('petId'));
    this.updatedPet.petId = parseInt(localStorage.getItem('petId'));
    petImageService.getAllImagesOfPet(this.obj).then((resp) => { this.petImages = resp.data; });
    this.isLoggedIn = localStorage.getItem('user') ? true : false;
  }

}
</script>

<style scoped>

@font-face {
    font-family: 'EasterSmile';
    src: url('src\fonts\easter-smile-font\EasterSmileDemo-q27Dd.otf') format('truetype');
}

@font-face {
    font-family: 'drphibes';
    src: url('src\fonts\dr-phibes-font\DrPhibes-nRWL0.ttf') format('truetype');
}

#gallery > img {
  /* height: 200px; */
  width: 200px;
  margin: 20px;
}

div{
    text-align: center;
}

footer p {
    font-size: 30px;
    font-family: dphibes;
}

h2{
    font-family: dphibes;
    font-size: 25px;
}

p {
    font-family: Arial, Helvetica, sans-serif;
    font-size: 20px;
}

img {
  transition: 0.2s;
}

img:hover {
  transition: 1s;
  transform: scale(3);
}

.donate-button-container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}

.donate-button-container:last-of-type {
  margin-top: 0px;
}

.donate-button {
    text-decoration: none;
    font-family: badUnicorn;
     border: solid 3px black;
    border-radius: 30px;
    padding: 10px;
    margin: 20px 295px;
    background-color: rgb(119, 18, 119);
    color: #f8f7fc;
    text-shadow: 3px 3px black;
    font-size: 50px;
    display: block;
    text-align: center;
    width: fit-content;
}

.donate-button-container a:hover {
  color: lightblue;
  background-color: rgb(183, 53, 183);
}

</style>