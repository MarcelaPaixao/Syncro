<template>
  <nav
    class="bg-[#73babb] p-1 flex justify-center gap-4 rounded-lg w-screen -mt-4"
  >
    <button
      @click="$emit('abrirModalMembros')"
      :class="baseClass"
      type="button"
    >
      Membros
    </button>

    <router-link to="/dashboard-grupo" :class="baseClass">Quadro</router-link>

    <button
      @click="$emit('abrirModalDescricao')"
      :class="baseClass"
      type="button"
    >
      Descrição
    </button>
  </nav>
</template>

<script setup>
defineEmits(["abrirModalMembros", "abrirModalDescricao"]);
const baseClass =
  "px-6 py-2 rounded-full text-white font-semibold transition-colors duration-200 hover:bg-white/30";
</script>

<style scoped>
.router-link-exact-active {
  @apply bg-white text-[#347677] hover:bg-white/80;
}
</style>
