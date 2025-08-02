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

<style scoped></style>
