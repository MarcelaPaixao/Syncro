<template>
  <div
    v-if="visible"
    :class="notificationClasses"
    class="fixed top-5 right-5 p-4 rounded-xl shadow-xl text-white font-bold z-50"
  >
    {{ message }}
    <button @click="visible = false" class="ml-4">&times;</button>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, onUnmounted } from "vue";
import emitter from "@/eventBus.js";

const visible = ref(false);
const message = ref("");
const type = ref(""); // 'success' ou 'error'

const notificationClasses = computed(() => {
  return {
    "bg-teal-500": type.value === "success",
    "bg-red-500": type.value === "error",
  };
});

const notificationListener = (payload) => {
  message.value = payload.message;
  type.value = payload.type;
  visible.value = true;

  // Esconde a notificação automaticamente após 3 segundos.
  setTimeout(() => {
    visible.value = false;
  }, 3000);
};

onMounted(() => {
  emitter.on("show-notification", notificationListener);
});

onUnmounted(() => {
  emitter.off("show-notification", notificationListener);
});
</script>
