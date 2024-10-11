<template>
  <div id="capstone-app">
    <nav id="nav">
      <router-link id="home-link" class="no-underline left-panel-button-hover" v-bind:to="{ name: 'home' }" ><p>Home</p></router-link>
      <div id="space"></div>
      <router-link id="user-links" v-if="$store.state.token != ''" class="no-underline left-panel-button-hover" v-bind:to="{ name: 'addPet' }"><p>Add Pet</p></router-link>
      <router-link id="user-links" v-if="$store.state.token != ''" class="no-underline left-panel-button-hover" v-bind:to="{ name: 'user' }"><p>Directory</p></router-link>
      <router-link id="user-links" v-if="$store.state.token != ''" class="no-underline left-panel-button-hover" v-bind:to="{ name: 'logout' }"><p>Logout</p></router-link>
    </nav>
    <router-view />
  </div>
</template>

<style>

#nav {
  background-color: rgb(119, 18, 119);
  border-bottom: solid 3px black;
  display: flex;
  height: 26px;
}

#space {
  flex-grow: 1;
}

#home-link, #user-links {
  height: 26px;
  align-items: center;
  /* transition: 2s; */
}

#home-link {
  border-right: solid 3px black;
}

#user-links {
  border-left: solid 3px black;
}

body {
  margin: 0;
}

h1 {
  margin-top: 0px;
  margin-bottom: 0px;
  text-shadow: 3px 3px black;
}

header {
  border-bottom: solid 3px black;
  padding-bottom: 20px;
  /* Photo by <a href="https://unsplash.com/@theluckyneko?utm_content=creditCopyText&utm_medium=referral&utm_source=unsplash">The Lucky Neko</a> on <a href="https://unsplash.com/photos/three-brown-tabby-kitten-lying-on-board-uePn9YCTCY0?utm_content=creditCopyText&utm_medium=referral&utm_source=unsplash">Unsplash</a>
   */
  background: url(./imgs/banner.jpg) center / cover no-repeat;
  height: 205px;
}

#left {
  display: flex;
  flex-direction: column;
  text-align: center;
  width: 20%;
  border-right: 3px solid black;
  border-bottom: 3px solid black;
  background-color: rgb(119, 18, 119);
}

#left p {
  border-bottom: solid 1px black;
  margin: 0px;
  padding: 13px 0px 7px 0px;
}

#right {
  margin-top: 15px;
  width: 80%;
  /* padding-left: 0; */
  text-align: center;
  font-family: Arial, Helvetica, sans-serif;
  padding-left: 20px;
  padding-right: 20px;
  text-align: justify;
}

#right a {
  padding-left: 10px;
}

#sub-title {
  border: solid 3px black;
  /* rgb(82, 82, 167); */
  border-radius: 30px;
  padding: 10px;
  margin: 20px 295px;
  background-color: rgb(119, 18, 119);
  color: #f8f7fc;
  text-shadow: 3px 3px black;
}

.no-underline {
  text-decoration: none;
}

#left a p {
  text-transform: uppercase;
  color: #f8f7fc;
  transition: .7s;
}

#left a p:hover {
  color: lightblue
}

.main-design, #home-link, #user-links {
  display: flex;
}

.left-panel-button-hover {
  transition: .5s;
}

.left-panel-button-hover:hover {
  background-color: rgb(183, 53, 183);
}

</style>

<style scoped>

a {
  transition: .5s;
}

p {
  height: 24px;
  padding-top: 2px;
  padding: 2px 10px 0px 10px;
  margin: 0px;
  color: #f8f7fc;
  transition: .7s;
}

p:hover {
  color: lightblue;
}

</style>
