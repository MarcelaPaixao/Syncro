<template>
  <div class="flex flex-col">
    <label class="text-xl font-bold text-gray-700 mb-2 text-center">
      {{ titulo }}
    </label>
    <div
      class="bg-[#eaf0f1] p-4 rounded-2xl shadow-xl w-full min-h-[60vh] space-y-3"
    >
      <router-link
        v-for="grupo in grupos"
        :key="grupo.id"
        :to="`grupo/${grupo.id}`"
        class="block p-2 bg-white rounded-xl shadow cursor-pointer hover:bg-gray-50 font-medium flex-grow truncate"
      >
        {{ grupo.nome }}
      </router-link>

      <div class="flex justify-center w-full">
        <BotaoCustomizado
          texto="Novo Grupo"
          @click="$emit('informaCriarGrupo')"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import BotaoCustomizado from "./BotaoCustomizado.vue";

defineProps({
  titulo: { type: String, required: true },
  grupos: { type: Array, default: () => [] },
});
defineEmits(["informaCriarGrupo"]);
</script>
