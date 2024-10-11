<template>
  <div>
    <h1>Admin Portal</h1>
    <p><router-link v-bind:to="{ name: 'adoptionApps' }">Approve Adoptions</router-link></p>
    <p><router-link v-bind:to="{ name: 'applications' }">Approve Volunteers</router-link></p>
    <all-applications-list />
  </div>
</template>

<script>
import AllApplicationsList from '../components/AllApplicationsList.vue';
export default {
  components: { AllApplicationsList },
  data() {
    return {
    };
  },
  computed: {
  }
}
</script>

<style scoped>

div{
  margin: 0 auto;
  padding: 0.5rem;
}

</style>