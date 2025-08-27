<template>
  <div
    class="feedback-card flex items-start gap-4 p-4 bg-gray-100 rounded-xl shadow"
  >
    <div
      class="w-10 h-10 rounded-full text-white flex items-center justify-center font-bold flex-shrink-0"
      :style="{ backgroundColor: avatarColor }"
    >
      {{ feedback.alunoNome.charAt(0).toUpperCase() }}
    </div>

    <div class="feedback-content flex-grow">
      <span class="membro-nome font-bold text-gray-800">{{
        feedback.alunoNome
      }}</span>
      <p
        class="feedback-texto mt-1 text-gray-600 text-sm"
        v-if="feedback.comentario"
      >
        {{ feedback.comentario }}
      </p>
    </div>

    <div class="status-icon">
      <span
        v-if="feedback.aprovado === true"
        class="flex items-center justify-center w-8 h-8 rounded-full bg-teal-100 text-teal-600 text-xl font-bold"
        >&#10004;</span
      >
      <span
        v-if="feedback.aprovado === false"
        class="flex items-center justify-center w-8 h-8 rounded-full bg-red-100 text-red-600 text-xl font-bold"
        >&times;</span
      >
    </div>
  </div>
</template>

<script setup>
import { computed, defineProps } from "vue";

const props = defineProps({
  feedback: { type: Object, required: true },
});

const avatarColor = computed(() => {
  const colors = ["#077a7d", "#59b6a8", "#73babb", "#4bb8ab", "#2fbaa3"];
  const index = props.feedback.alunoNome.charCodeAt(0) % colors.length;
  return colors[index];
});
</script>
