<template>
  <router-link to="/"></router-link>
  <!-- <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/login">Login</router-link>
  </nav> -->
  <router-view />
  <!-- <meta name="viewport" content="width=device-width, initial-scale=1" /> -->
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
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

h2 {
  text-align: center;
  color: #333;
  font-size: 1.3rem;
}

label {
  text-align: left;
  display: block;
  margin-bottom: 0.3rem;
  color: #555;
  font-weight: bold;
}

input {
  width: 100%;
  padding: 0.6rem;
  border: 1px solid #ccc;
  border-radius: 10px;
  box-sizing: border-box; /* Garante que o padding não aumente a largura total */
}
</style>
