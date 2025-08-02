<template>
  <router-link to="/"></router-link>
  <!-- <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/login">Login</router-link>
  </nav> -->
  <router-view />
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

.bg-padrao {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-image: linear-gradient(160deg, #a7f7ee, #0a9296, #0d5d68);
}

h2 {
  text-align: center;
  margin-bottom: 1.5rem;
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

header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
  background-color: #077a7d;
  border-bottom-left-radius: 20px;
  border-bottom-right-radius: 20px;
  box-shadow: 10px 10px 6px rgba(0, 0, 0, 0.1);
  margin-bottom: 1rem;
}

.logo-container {
  display: flex;
  align-items: center;
}
.logo-container img {
  height: 45px;
}

.icon-container {
  display: flex;
  align-items: center;
  gap: 1.5rem;
}

.icon-container img {
  height: 35px;
}
</style>
