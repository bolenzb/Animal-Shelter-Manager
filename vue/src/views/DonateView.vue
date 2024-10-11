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
            <h2 id="sub-title">Donations Wag Tails!</h2>
            <p class="donate-paragraph">Understandably not everyone is able to bring home a pet or have the ability to care for one depending on their unique life circumstances but if you want to donate to a good cause or support these furry friends from afar, donating is the best way to help out.</p>
            <h3>Think of all the lives in need you could help with your simple donation.</h3>
            <section class="photos">
              <section class="hover1">
                <div class="sad-pet">
                  <image height=300px width=300px src="src\imgs\page imgs\thomas-park-On6bRQRn5lY-unsplash.jpg" alt="sad kitty"></image>
                </div>
                <div class="hover-show">
                  <h3>SAVE</h3>
                </div>
              </section>
              <section class="hover2">
                <div class="sad-pet">
                  <image height=300px width=300px src="src\imgs\page imgs\krista-mangulsone-9gz3wfHr65U-unsplash.jpg" alt="sad pets"></image>
                </div>
                <div class="hover-show">
                  <h3>A</h3>
                </div>
              </section>
              <section class="hover3">
                <div class="sad-pet">
                  <image height=300px width=300px src="src\imgs\page imgs\priscilla-du-preez-Iy9wIDKL4YU-unsplash.jpg" alt="sad puppy"></image>
                </div>
                <div class="hover-show">
                  <h3>LIFE</h3>
                </div>
              </section>
            </section>
            <p class="donate-paragraph">“I have found that among its other benefits, giving liberates the soul of the giver." -<strong><em>Maya Angelou</em></strong></p>
            <div class="donate-button-container">
            <a href="https://secure.aspca.org/donate/ps-gn-p2?ms=MP_PMK_Googlenonbrand&initialms=MP_PMK_Googlenonbrand&pcode=WPSN7GO2PK02&lpcode=WPSN7GO1PK02&test&ds_rl=1066464&gad_source=1&gclid=Cj0KCQjw05i4BhDiARIsAB_2wfBZLmuCE2jaZTuom-Ly2yxqTaidp7E-592fC2wKEBgxhFbY8qSjxngaAtSuEALw_wcB&gclsrc=aw.ds" target="_blank" class="donate-button">Donate Here</a>
            </div>
        </aside>
        <div>
    <audio ref="audio" :src="audioSrc" autoplay loop></audio>
        </div>
      </main>
    </div>
  </template>

<script>
export default {

    data() {
      return {
        isLoggedIn: false,
        audioSrc: 'src/sounds/ArmsOfTheAngel.mp3',
      };
    },
    computed: {
    },
    created() {
      this.isLoggedIn = localStorage.getItem('user') ? true : false;
    },
    mounted() {
        const audio = this.$refs.audio;
        audio.volume = 0.5; // Set the volume to 50%
        audio.play().catch(error => {
        console.log('Autoplay was prevented:', error);
    });
    },
}
  </script>

  <style scoped>
  @font-face {
    font-family: 'BadUnicorn';
    src: url('src\fonts\bad-unicorn-font\BadUnicornDemoRegular-BVWx.ttf') format('truetype');
    }

  .donate-paragraph {
    text-indent: 20px;
    text-align: center;
    text-align: justify;
    padding-left: 20px;
    padding-right: 20px;
    font-family: Arial, Helvetica, sans-serif;
  }

  #sub-title {
    margin: 20px 255px;
  }

  h3 {
    text-align: center;
    font-family: Arial, Helvetica, sans-serif;
    font-weight: bolder;
  }

  .photos {
    justify-content: space-around;
    padding: 10px 0px 10px 0px;
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
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}

.donate-button-container a:hover {
  color: lightblue;
  background-color: rgb(183, 53, 183);
}

.sad-pet {
  height: 110px;
}

.hover-show {
  opacity: 0;
}

.hover1, .hover2, .hover3 {
  height: 300px;
  width: 300px;
}

.hover1 .sad-pet, .hover2 .sad-pet, .hover3 .sad-pet {
  transition: 1s;
}

.hover1 .hover-show, .hover2 .hover-show, .hover3 .hover-show {
  transition: 1.2s;
}

.hover1:hover .sad-pet, .hover2:hover .sad-pet, .hover3:hover .sad-pet {
  opacity: 0.2;
  transform: scale(1.02);
}

.hover1:hover .hover-show, .hover2:hover .hover-show, .hover3:hover .hover-show {
  opacity: 1;
  transition: 2.5s;
}

#aside-photo {
  background: url(../imgs/pawprinttransparent.gif) center / cover no-repeat;
  width: 100%;
  height: 100%;
}

  </style>
  