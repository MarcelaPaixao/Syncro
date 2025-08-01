<template>
  <button :class="variante" @click="$emit('click')">
    {{ texto }}
  </button>
</template>

<script setup>
defineProps({
  texto: { type: String, required: true },
  variante: { type: String, default: "principal" },
});
defineEmits(["click"]);
</script>

<style scoped>
button {
  width: 100%;
  padding: 0.6rem;
  border: none;
  color: white;
  font-size: 1rem;
  font-weight: bold;
  border-radius: 10px;
  cursor: pointer;
  transition: 0.2s;
  margin-top: 1rem;
  margin-bottom: 0.5rem;
}

.principal {
  background-color: #0d8668;
}

.principal:hover {
  background-color: #10a075cb;
}

.secundaria {
  background-color: rgb(101, 165, 146);
}

.secundaria:hover {
  background-color: rgba(124, 202, 174, 0.938);
}
</style>
