<!-- component for user list display, imported to UserView.vue -->

<template>
  <div class="user-list">
    <h2>Directory</h2>
  <table>
    <thead>
        <tr>
            <th>Username</th>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
        </tr>
    </thead>
    <tbody>
        <tr v-for="user in allUsers" v-bind:key="user.id">
          <td>{{ user.username }}</td>
          <td>{{ user.firstName }} {{ user.lastName }}</td>
          <td>{{ user.email }}</td>
          <td>{{ displayRole(user) }}</td>
        </tr>
    </tbody>
  </table>
</div>
</template>
<script>
export default {
  // props: ['user'],
  data() {
    return {
    }
  },
  methods: {
    displayRole(user) {
      if ( user.authorities[0].name === 'ROLE_ADMIN') {
        return '(Admin)';
      } else {
        return '(Volunteer)';
      }
    }
  },
  computed: {
    allUsers() {
      this.$store.commit('GET_USERS')
      return this.$store.state.userArray.filter(user => user.username !== JSON.parse(localStorage.getItem('user')).username);
    }
  },
  }
</script>
<style scoped>
.user-list {
  max-width: 600px;
  margin: 20px auto;
  padding: 20px;
  border: 1px solid skyblue;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  background-color: rgb(119, 18, 119);
  
}

h2 {
  text-align: center;
  color: skyblue;
  background-color: rgb(119, 18, 119);
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th, td {
  padding: 10px;
  border: 1px solid skyblue;
  text-align: center;
  color: skyblue;
}


tr:hover {
  background-color: #f1f1f1;
}
</style>