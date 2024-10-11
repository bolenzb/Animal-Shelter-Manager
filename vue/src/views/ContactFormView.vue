<template>
  <div>
    <header>
      <h1>Ruff Times Animal Shelter</h1>
    </header>
    <main class="main-design">
      <aside id="left">
        <router-link v-show="!this.isLoggedIn" class="no-underline left-panel-button-hover" v-bind:to="{ name: 'login' }"><p>Login</p></router-link>
        <router-link v-show="!this.isLoggedIn" class="no-underline left-panel-button-hover" v-bind:to="{ name: 'apply' }"><p>Volunteer</p></router-link>
        <router-link class="no-underline left-panel-button-hover" v-bind:to="{ name: 'search' }"><p>Available Pets</p></router-link>
        <router-link class="no-underline left-panel-button-hover" v-bind:to="{ name: 'aboutUs' }"><p>About Us</p></router-link>
        <router-link class="no-underline left-panel-button-hover" v-bind:to="{ name: 'contact' }"><p>Contact Us</p></router-link>
        <router-link class="no-underline left-panel-button-hover" v-bind:to="{ name: 'donate' }"><p>Donate</p></router-link>
        <a id="aside-photo"></a>
      </aside>

      <div id="fix">
        <aside id="right">
          <h2>Contact Us</h2>
          <form @submit.prevent="handleSubmit">
            <div class="form-input-group">
              <label for="firstName">First Name </label>
              <input type="text" id="firstName" v-model="form.firstName" />
            </div>
            <div class="form-input-group">
              <label for="lastName">Last Name </label>
              <input type="text" id="lastName" v-model="form.lastName" />
            </div>
            <div class="form-input-group">
              <label for="phoneNumber">Phone Number </label>
              <input type="text" id="phoneNumber" v-model="form.phoneNumber" />
            </div>
            <div class="form-input-group">
              <label for="email">Email </label>
              <input type="email" id="email" v-model="form.email" />
            </div>
            <div class="form-input-group">
              <label for="contactHow">How would you like us to reach out to you?</label>
              <br>
              <input id="phone" name="contactHow" type="radio" value="phone" v-model="form.contactHow" />
              <label for="phone">Phone </label>
              <input id="email" name="contactHow" type="radio" value="email" v-model="form.contactHow" />
              <label for="email">Email </label>
            </div>
            <div class="form-input-group">
              <label for="description">Please provide a brief description of what you would like to discuss or what you need assistance with below: </label>
              <br>
              <textarea id="description" rows="4" cols="60" v-model="form.description"></textarea>
            </div>
            <button type="submit">Submit Inquiry</button>
          </form>
        </aside>
      </div>
    </main>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isLoggedIn: false,
      form: {
        firstName: '',
        lastName: '',
        phoneNumber: '',
        email: '',
        contactHow: '',
        description: ''
      }
    };
  },
  methods: {
    handleSubmit() {
      console.log('Form submitted:');
      // this clears the form fields upon submission//functions properly.
      this.form = {
        firstName: '',
        lastName: '',
        phoneNumber: '',
        email: '',
        contactHow: '',
        description: ''
      }

      // Navigate to MainView.vue - cannot seem to get this to actually re-route user to the homepage
      this.$router.push({ name: 'home' })
        // .then((resp) => {
        //   if (resp.status == 200) {
        //     window.alert('Submission successful');
        //   }
        // })
        .catch(err => {
        console.error('Navigation error:', err);
      });
      window.alert('Submission successful');
    }
  },
  created() {
    this.isLoggedIn = localStorage.getItem('user') ? true : false;
  }
};
</script>

  <style scoped>
  @font-face {
    font-family: 'BadUnicorn';
    src: url('src\fonts\bad-unicorn-font\BadUnicornDemoRegular-BVWx.ttf') format('truetype');
    }
  @font-face {
    font-family: 'kiottodemo';
    src: url('src\fonts\kiotto-font\KiottoDemo-pgo11.otf') format('truetype');
  }

  .donate-paragraph {
    text-indent: 20px;
    text-align: center;
    text-align: justify;
    padding-left: 20px;
    padding-right: 20px;
    font-family: Arial, Helvetica, sans-serif;
  }

  .photos {
    justify-content: space-around;
    padding: 10px 0px 50px 0px;
    display: flex;
    filter: grayscale(100%);
  }

  .donate-button {
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

.donate-button-container {
  display: flex;
  justify-content: center; /* Center the button container */
  align-items: center;
  margin-top: 20px;
}

#form-input-group {
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
    background-color: lightblue;
}

aside#right {
    background: #fff;
    margin: 2rem auto; /* Center horizontally */
    padding: 2rem; /* Add padding */
    box-shadow:
      0 2px 4px 0 rgb(0 0 0 / 20%),
      0 2.5rem 5rem 0 rgb(0 0 0 / 10%);
    height: 600px; /* Make height responsive */
    aspect-ratio: 1; /* Ensure it maintains a square aspect ratio */
    display: flex; /* Use flexbox */
    flex-direction: column; /* Stack children vertically */
    justify-content: left; /* Left align */
    align-items: left; /* Left align */
    text-align: left; /* Left align text */
    padding: 20px; 
    width: 500px;
    line-height: 1.6;
}

#fix {
  width: 83.2%;
}

button {
    font-size: 15px;
    padding: 10px 24px;
    border-radius: 8px;
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
    background: rgb(119, 18, 119);
    color: white;
}

#aside-photo {
  background: url(../imgs/pawprinttransparent.gif) center / cover no-repeat;
  width: 100%;
  height: 100%;
}

  </style>