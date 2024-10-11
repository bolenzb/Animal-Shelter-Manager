<template>
  <div>
    <table>
        <thead>
            <tr>
                <th>Pending Applications List</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="app in pendingApplications" v-bind:key="app.pendingId">
                <td>{{app.firstName}} {{ app.lastName }}</td>
                <button @click="approveApp(app)">Approve</button>
                <button @click="denyApp(app.pendingId)">Deny</button>
            </tr>
        </tbody>
    </table>
    <p><router-link v-bind:to="{ name: 'admin' }">Admin Portal</router-link></p>
  </div>
</template>

<script>
import applicationService from '../services/ApplicationService';

export default {
    data() {
        return {
            status: {
                statusId: '',
                pendingId: '',
            }
        }
    },
    methods: {
        approveApp(app) {
            this.status.statusId = 2;
            this.status.pendingId = app.pendingId;
            this.$store.commit('CHANGE_STATUS', this.status);
            this.$store.commit('SET_CURRENT_APP', app);
            this.$router.push('/register');
        },
        denyApp(id) {
            this.status.statusId = 3;
            this.status.pendingId = id;
            applicationService.updateApplication(this.status);
        }
    },
    computed: {
        pendingApplications() {
            this.$store.commit('GET_PENDING_APPS', this.$store.state.applications)
            return this.$store.state.applications;
        },
    },
    // created() {
    //     this.$store.commit('GET_PENDING_APPS', this.$store.state.applications);
    // }
}
</script>

<style scoped>

div {
  margin: 0 auto;
  padding: 0.5rem;
}

</style>