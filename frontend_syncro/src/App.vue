<template>
  <router-link to="/"></router-link>
  <AppNotification />
  <router-view />
</template>

<script setup>
import AppNotification from "@/components/AppNotification.vue";
</script>

<style>
html,
body,
#app {
  height: 100%;
  margin: 0;
  padding: 0;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

.bg-padrao {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100vw;
  background-size: cover;
  background-repeat: no-repeat;
  background-image: linear-gradient(160deg, #a7f7ee, #0a9296, #0d5d68);
}
</style>
