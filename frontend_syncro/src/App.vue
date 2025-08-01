<template>
  <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link> |
    <router-link to="/login">Login</router-link>
  </nav>
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
  background-image: linear-gradient(160deg, #a7f7dc, #33af90, #0d684c);
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
  margin-bottom: 0.2rem;
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
