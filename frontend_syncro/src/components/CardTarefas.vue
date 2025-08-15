<template>
  <div class="flex flex-col">
    <label class="text-xl font-bold text-gray-700 mb-2 text-center">
      {{ titulo }}
    </label>
    <div
      class="bg-[#eaf0f1] p-4 rounded-2xl shadow-xl w-full min-h-[60vh] space-y-3"
    >
      <router-link
        v-for="tarefa in tarefas"
        :key="tarefa.id"
        :to="`/tarefa/${tarefa.id}`"
        class="block p-2 bg-white rounded-xl shadow cursor-pointer hover:bg-gray-50 font-medium flex-grow truncate"
      >
        {{ tarefa.titulo }}
      </router-link>

      <div class="flex justify-center w-full">
        <BotaoCustomizado
          v-if="showAddButton"
          texto="Nova Tarefa"
          @click="$emit('abrirModalParaColuna')"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import BotaoCustomizado from "./BotaoCustomizado.vue";

defineProps({
  showAddButton: { type: Boolean, default: false },
  titulo: { type: String, required: true },
  tarefas: { type: Array, default: () => [] },
});
defineEmits(["abrirModalParaColuna"]);
</script>
