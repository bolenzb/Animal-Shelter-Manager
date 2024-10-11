<template>
  <div>
    <th>{{ pet.name }}</th>
    <table>
      <tbody>
        <tr :key="pet.petId">
          <td class="pet-trait">{{ pet.gender }}</td>
          <td class="pet-trait">{{ pet.species }}</td>
          <td class="pet-trait">Breed: {{ pet.breed }}</td>
          <td class="pet-trait">Color: {{ pet.color }}</td>
          <td class="pet-trait">Age: {{ Math.floor(daysBetween / 365) }}Yrs. {{ Math.floor((daysBetween / 30.436875) - (Math.floor(daysBetween / 365)*12)) }}Mo.</td>
          <button @click.prevent="toInfo">View</button>
          <button @click.prevent="toAdoptForm">Adopt</button>
        </tr>
      </tbody>
    </table>
    <!--Images are sized down with CSS-->
    <section id="gallery">
      <img v-for="image in this.petImages" v-bind:key="image.id" v-bind:src="image.name"/>
    </section>
  </div>
</template>
<script>
import petImageService from '../services/PetImageService'

export default {
  props: ['pet'],
  data() {
    return {
      obj: {
        petId: '',
      },
      petImages: [],
      date1: new Date(),
      date2: new Date(this.pet.birthday),
    }
  },
  methods: {
    toAdoptForm() {
      this.$store.commit('STORE_PET_ID', this.pet.petId);  
      this.$router.push('/adoptionForm');
    },
    toInfo() {
      this.$store.commit('STORE_PET_ID', this.pet.petId);  
      this.$router.push('/pet');
    }
  },
  computed: {
    daysBetween() {
      const diffTime = Math.abs(this.date1 - this.date2);
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
      return diffDays;
    },
  },
  created() {
    this.obj.petId = this.pet.petId;
    petImageService.getAllImagesOfPet(this.obj).then((resp) => { this.petImages = resp.data; });
  }
}
</script>

<style scoped>

th, section, table {
  padding-left: 10px;
}

table, section {
  width: 100%;
}

div {
  margin-top: 10px;
}

#gallery > img {
  /* height: 200px; */
  width: 200px;
  margin: 20px;
}

button {
  margin-right: 3px;
  border-radius: 4px;
}

tr button:first-of-type {
  margin-left: 5px;
}

.pet-trait {
    border: solid 1.4px black;
    padding: 4px 8px 4px 8px;
    background-color: white;
}

</style>