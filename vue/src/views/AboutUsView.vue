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
        <aside id="right">
            <h2 id="sub-title">About Us</h2>
            <p class="about-paragraph1">Ruff Times, LLC was established in 2024 by a group of friends who wanted to make a difference in their community. Our founders, Dan Beistel, Zoe Bolen, Jobe Abdellah, and Mandii Crain pulled in resources from their community to create a home for unwanted or lost pets so that they could assist in finding their forever homes.</p>
            <h3>Meet the Founders</h3>
            <div id="photo-info">
              <div class="hover1">
                <section class="photos">
                  <image src="src\imgs\founderphotos\Daniel Beistel.jpg" alt="Daniel Beistel"></image>
                </section>
                <section id="founder-names">
                  <h3 class="hover-show">Daniel Beistel</h3>
                </section>
              </div>
              <div class="hover2">
                <section class="photos">
                  <image src="src\imgs\founderphotos\zoeheadshot.jpg" alt="Zoe Bolen"></image>
                </section>
                <section id="founder-names">
                  <h3 class="hover-show">Zoe Bolen</h3>
                </section>
              </div>
              <div class="hover3">
                <section class="photos">
                  <image src="src\imgs\founderphotos\jobeheadshot.jpg" alt="Jobe Abdellah"></image>
                </section>
                <section id="founder-names">
                  <h3 class="hover-show">Jobe Abdellah</h3>
                </section>
              </div>
              <div class="hover4">
                <section class="photos">
                  <image src="src\imgs\founderphotos\mandiiheadshotcrop.jpg" alt="Mandii Crain"></image>
                </section>
                <section id="founder-names">
                  <h3 class="hover-show">Mandii Crain</h3>
                </section>
              </div>
            </div>
            <p class="about-paragraph2">Ruff Times Animal Shelter is a non-profit organization that is dedicated to providing a safe and loving environment for animals in need. We are committed to finding forever homes for all of our animals and ensuring that they receive the care and attention they deserve. Our team of dedicated staff and volunteers work tirelessly to provide the best possible care for our animals and to help them find loving families. We believe that every animal deserves a second chance and we are dedicated to making that happen.</p>
            
            <p class="about-paragraph2">At Ruff Times Animal Shelter, we believe that every animal deserves a loving home and we are committed to finding the perfect match for each of our animals. We work with a network of foster homes and rescue organizations to help us find homes for our animals and we are always looking for new ways to help more animals in need. We rely on the support of our community to help us continue our work and we are grateful for the support we receive from our donors, volunteers, and adopters.</p>

            <p class="about-paragraph2">If you are interested in adopting a pet, volunteering, or making a donation, please contact us today. We would love to hear from you and we are always looking for new ways to help animals in need. Thank you for your support!</p>
        </aside>
      </main>
    </div>
</template>

<script>

export default {

  data() {
    return {
      isLoggedIn: false,
    };
  },
  computed: {
  },
  created() {
    this.isLoggedIn = localStorage.getItem('user') ? true : false;
  },
}
</script>

<style scoped>
@font-face {
    font-family: 'drphibes';
    src: url('src\fonts\dr-phibes-font\DrPhibes-nRWL0.ttf') format('truetype');
}

h3 {
    text-align: center;
}

header {
  border-bottom: solid 3px black;
  padding-bottom: 20px;
}

main, .photos, body, #founder-names, #photo-info {
  display: flex;
}

#photo-info {
  justify-content: space-around;
  padding: 10px 0px 50px 0px;
}

#founder-names {
  align-items: center;
  justify-content: center;
}

#photo-info {
  border-bottom: solid 2px black;
}

.hover-show {
  opacity: 0;
}

.hover1, .hover2, .hover3, .hover4 {
  width: 200px;
  height: 200px;
}

.hover1 .photos, .hover2 .photos, .hover3 .photos, .hover4 .photos {
  transition: 1s;
}

.hover1 .hover-show, .hover2 .hover-show, .hover3 .hover-show, .hover4 .hover-show {
  transition: 1.2s;
}

.hover1:hover .photos, .hover2:hover .photos, .hover3:hover .photos, .hover4:hover .photos {
  opacity: 0.2;
  transform: scale(1.02);
}

.hover1:hover .hover-show, .hover2:hover .hover-show, .hover3:hover .hover-show, .hover4:hover .hover-show {
  opacity: 1;
  transition: 2.5s;
}


.photos {
    height: 70px;
}

.name {
  justify-content: start;
}

body {
    flex-direction: column;
}


a {
  text-decoration: none;
}

.about-paragraph1 {
    font-size: 20px;
    font-family: Arial, Helvetica, sans-serif;
    padding-top: 20px;
    padding-bottom: 20px;
    border-bottom: solid 2px black;
    margin-bottom: 30px;
    text-indent: 20px;
}

.about-paragraph2 {
  font-size: 20px;
  font-family: Arial, Helvetica, sans-serif;
  padding-top: 10px;
  text-indent: 20px;
}

img {
  width: 200px;
  height: 200px;
  border-radius: 50%;
}

section a {
  padding-left: 10px;
}

#aside-photo {
  background: url(../imgs/pawprinttransparent.gif) center / cover no-repeat;
  width: 100%;
  height: 100%;
}

</style>