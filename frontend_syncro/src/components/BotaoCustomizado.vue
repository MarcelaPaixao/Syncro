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
  width: 250px;
  height: 40px;
  padding: 0.6rem;
  border: none;
  color: white;
  font-size: 1rem;
  font-weight: bold;
  border-radius: 10px;
  cursor: pointer;
  transition: 0.2s;
}

.principal {
  background-color: #077a7d;
}

.principal:hover {
  background-color: #0ca4aad4;
}

.secundaria {
  background-color: #5fbbc1;
}

.secundaria:hover {
  background-color: rgba(119, 204, 204, 0.892);
}
</style>
