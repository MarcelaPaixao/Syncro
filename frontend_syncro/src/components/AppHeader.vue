<template>
  <header class="app-header">
    <div class="logo-container">
      <img alt="Syncro logo" src="@/assets/syncro_logo_branco.png" />
    </div>

    <div class="icon-container">
      <router-link to="/perfil-usuario" class="icon-link">
        <img alt="Perfil" src="@/assets/user_icon.png" />
      </router-link>

      <router-link to="/criar-grupo" class="icon-link">
        <img alt="Novo Grupo" src="@/assets/plus_circle_icon.png" />
      </router-link>
    </div>
  </header>
</template>

<script setup></script>

<style scoped>
header {
  display: flex;
  justify-content: space-between;
  padding: 1rem 2rem;
  background-color: #077a7d;
  border-bottom-left-radius: 15px;
  border-bottom-right-radius: 15px;
  margin-bottom: 1rem;
  width: 100vw;
}

.logo-container img {
  height: 45px;
}

.icon-container {
  display: flex;
  gap: 1.5rem;
}

.icon-container img {
  height: 35px;
  opacity: 1;
}

.icon-container img:hover {
  opacity: 0.8;
}
</style>
