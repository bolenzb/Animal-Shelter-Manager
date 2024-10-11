<template>
  <div>
    <header>
      <h1>Ruff Times Animal Shelter</h1>
    </header>
    <main class="main-design">
      <aside id="left">
        <!-- <image src="../imgs/Untitled.png"><router-link id="home-icon" class="no-underline" v-bind:to="{ name: 'home' }" ></router-link></image> -->
          <router-link v-show="!this.isLoggedIn" class="no-underline left-panel-button-hover" v-bind:to="{ name: 'login' }"><p>Login</p></router-link>
          <router-link v-show="!this.isLoggedIn" class="no-underline left-panel-button-hover" v-bind:to="{ name: 'apply' }"><p>Volunteer</p></router-link>
          <router-link class="no-underline left-panel-button-hover" v-bind:to="{ name: 'search' }"><p>Available Pets</p></router-link>
          <router-link class="no-underline left-panel-button-hover" v-bind:to="{ name: 'aboutUs' }"><p>About Us</p></router-link>
          <router-link class="no-underline left-panel-button-hover" v-bind:to="{ name: 'contact' }"><p>Contact Us</p></router-link>
          <router-link class="no-underline left-panel-button-hover" v-bind:to="{ name: 'donate' }"><p>Donate</p></router-link>
        <a id="aside-photo"></a>
      </aside>
      <section id="right">
        <h2 id="sub-title" >Most Recent Pets</h2>
        <section id="pets">
          <pet-card 
          v-for="pet in allPets"
          :key="pet.petId"
          :pet="pet" id="pet-card"/>
          <div id="gallery">
              <img v-for="image in images" v-bind:key="image.id" v-bind:src="image.name"/>
          </div>
        </section>  
      </section>
    </main>
  </div>
</template>

<script>
import PetCard from '../components/PetCard.vue';

export default {
  components: { PetCard },
  data() {
    return {
      isLoggedIn: false,
      pets: [],
      images: [],
    };
  },
  computed: {
    allPets() {
      this.$store.commit('GET_RECENT_PETS')
      return this.$store.state.recentPets;
    }
  },
  created() {
    this.isLoggedIn = localStorage.getItem('user') ? true : false;
  },
}
</script>

<style scoped>

main {
  display: flex;
}

#pet-card {
  border-bottom: 2px solid black;
  padding-bottom: 5px;
}

/* #aside-photo {
  background: url(../imgs/pawprinttransparent.gif) center / cover no-repeat;
  width: 100%;
  height: 100%;
} */

</style>