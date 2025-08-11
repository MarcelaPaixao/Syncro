<template>
  <header
    class="flex justify-between items-center py-4 px-8 bg-[#077a7d] rounded-b-[15px] mb-4 w-screen"
  >
    <div class="logo-container">
      <img
        alt="Syncro logo"
        src="@/assets/syncro_logo_branco.png"
        class="h-[45px]"
      />
    </div>

    <div class="icon-container flex items-center gap-6">
      <router-link to="/perfil-usuario" class="icon-link">
        <img
          alt="Perfil"
          src="@/assets/user_icon.png"
          class="h-[35px] transition-opacity hover:opacity-80"
        />
      </router-link>

      <router-link to="/criar-grupo" class="icon-link">
        <img
          alt="Novo Grupo"
          src="@/assets/plus_circle_icon.png"
          class="h-[35px] transition-opacity hover:opacity-80"
        />
      </router-link>
    </div>
  </header>
</template>

<script setup></script>
