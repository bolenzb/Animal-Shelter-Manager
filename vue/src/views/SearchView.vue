<template>
    <div>
      <h1>Available Pets</h1>
      <h2>What type of pet are you looking for?</h2>
      <h3>Search Available Pets</h3>
      <form>
        <input class="search" type="text" v-model="searchQuery" placeholder="Search pets..." />
        <select id="genderFilter" v-model="gender">
          <option value="0">Any</option>
          <option value="Male">Male</option>
          <option value="Female">Female</option>
        </select>
        <select id="speciesFilter" v-model="species">
          <option value="0">Any</option>
          <option value="Cat">Cat</option>
          <option value="Dog">Dog</option>
        </select>
        <select id="breedFilter" v-model="breed">
          <option value="0">Any</option>
          <option value="Domestic Longhair">Domestic Longhair</option>
          <option value="Domestic Shorthair">Domestic Shorthair</option>
          <option value="Domestic Mediumhair">Domestic Mediumhair</option>
          <option value="German Shepherd Mix">German Shepherd Mix</option>
          <option value="Golden Retriever">Golden Retriever</option>
          <option value="Great Pyrenees">Great Pyrenees</option>
          <option value="Husky">Husky</option>
          <option value="Labradoodle">Labradoodle</option>
          <option value="Labrador Retriever Mix">Labrador Retriever Mix</option>
          <option value="Maine Coon Mix">Maine Coon Mix</option>
          <option value="Pitbull">Pitbull</option>
          <option value="Scottish Terrier">Scottish Terrier</option>
        </select>
        <select id="colorFilter" v-model="color">
          <option value="0">Any</option>
          <option value="Black">Black</option>
          <option value="Black and White">Black and White</option>
          <option value="Brown and White">Brown and White</option>
          <option value="Cream">Cream</option>
          <option value="Grey">Grey</option>
          <option value="Grey and white Tabby">Grey and white Tabby</option>
          <option value="Golden">Golden</option>
          <option value="Tabby">Tabby</option>
          <option value="White">White</option>
        </select>
      </form>
      <br>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Gender</th>
            <th>Species</th>
            <th>Breed</th>
            <th>Color</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="pet in filteredPets" :key="pet.petId">
            <td>{{ pet.name }}</td>
            <td>{{ pet.gender }}</td>
            <td>{{ pet.species }}</td>
            <td>{{ pet.breed }}</td>
            <td>{{ pet.color }}</td>
            <button @click.prevent="toInfo(pet)">View</button>
            <button @click.prevent="toAdoptForm(pet)">Adopt</button>
          </tr>
        </tbody>
      </table>
    </div>
</template>
  
<script>
export default {
    data() {
      return {
        searchQuery: '',
        gender: '0',
        species: '0',
        breed: '0',
        color: '0'
      };
    },
    methods: {
      toAdoptForm(pet) {
        this.$store.commit('STORE_PET_ID', pet.petId);  
        this.$router.push('/adoptionForm');
      },
      toInfo(pet) {
        this.$store.commit('STORE_PET_ID', pet.petId);  
        this.$router.push('/pet');
      }
    },
    computed: {
      filteredPets() {
        this.$store.commit('GET_PETS');
        return this.$store.state.petArray.filter(pet => {
          const name = pet.name.toLowerCase();
          const query = this.searchQuery.toLowerCase();
          const genderMatch = this.gender === '0' || pet.gender === this.gender;
          const speciesMatch = this.species === '0' || pet.species === this.species;
          const breedMatch = this.breed === '0' || pet.breed === this.breed;
          const colorMatch = this.color === '0' || pet.color === this.color;
          return name.includes(query) && genderMatch && speciesMatch && breedMatch && colorMatch;
        });
      },
    },
  };
</script>
  
<style scoped>

    h2 {
      color: rgb(119, 18, 119);
    }

    h3 {
      color: rgb(119, 18, 119);
    }

    div {
      text-align: center;
    }


    .compact-table th, .compact-table td {
      padding: 4px 8px; /* Adjust the padding as needed */
      margin: 0; /* Remove any margin */
    }

    /* .search {
      height: 16px;
    } */

    button {
      margin-top: 13px;
      margin-right: 3px;
      background-color: rgb(119, 18, 119);
      color: skyblue;
    }

    th, td {
      padding: 10px;
      border: 1px solid skyblue;
      text-align: center;
      color: skyblue;
      background-color:rgb(119, 18, 119) ;
    }

    tr button:first-of-type {
      margin-left: 5px;
      background-color: rgb(119, 18, 119);
      color: skyblue;
    }

    td:hover {
      background-color: #f1f1f1;
    }

    thead {
      border-bottom: solid 3px skyblue;
    }

    table {
      width: 100%;
    }

    th {
      width: 18%;
    }

    form select {
      border-top: solid 2.5px black;
      border-bottom: solid 2.5px black;
      border-right: none;
      height: 21px;
    }

    form select:last-of-type {
      border-right: solid 2.5px black;
    }

    form input {
      border: solid 2.5px black;
      border-right: none;
    }

</style>