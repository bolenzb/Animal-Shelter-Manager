<template>
  <div>
  <h3>Volunteer List</h3>   
  <form>
    <input type="text" v-model="searchQuery" placeholder="Search volunteers..." />
    <select id="statusFilter" v-model="status">
      <option value=0>Show All</option>
      <option value=1>Pending</option>
      <option value=2>Approved</option>
      <option value=3>Denied</option>
    </select>
  </form>
  <table class = "volunteer-table">
    <thead>
      <tr>
        <th>First Name</th> &nbsp;
        <th>Last Name</th> &nbsp;
        <th>Status</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="volunteer in filteredVolunteers" :key="volunteer.id">
        <td>{{ volunteer.firstName }}</td> &nbsp;
        <td>{{ volunteer.lastName }}</td> &nbsp;
        <td>{{ displayStatus(volunteer) }}</td>
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
        status: 0,
      }
    },
    methods: {
        displayStatus(volunteer) {
            if ( volunteer.status === 1) {
                return 'Pending';
            } else if ( volunteer.status === 2) {
                return 'Approved';
            } else {
                return 'Denied';
            }
        }
    },
    computed: {
        filteredVolunteers() {
            this.$store.commit('GET_ALL_APPS');
            return this.$store.state.allApplications.filter(volunteer => {
                const fullName = `${volunteer.firstName} ${volunteer.lastName}`.toLowerCase();
                const query = this.searchQuery.toLowerCase();
                return fullName.includes(query) && (volunteer.status == this.status || this.status == 0);
            });
        },
    },
}
</script>

<style scoped>


@font-face {
    font-family: 'BadUnicorn';
    src: url('src\fonts\bad-unicorn-font\BadUnicornDemoRegular-BVWx.ttf') format('truetype');
}

h2 {
  text-align: center;
  color: skyblue;
  background-color: rgb(119, 18, 119);
}

h3 {
  font-family: 'BadUnicorn';
  font-size: 30px;
  color:  rgb(119, 18, 119);
  text-align: center;
  
}

form {
  text-align: center;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

tr, td, th {
  padding: 10px;
  border: 1px solid skyblue;
  background-color: rgb(119, 18, 119);
  text-align: center;
  color: skyblue;
}


td:hover {
  background-color: #f1f1f1;
}


</style>