<!-- INUTILIZADO -->
<template>
  <div class="input-nome">
    <div class="input-group">
      <label>Nome completo</label>
      <input
        :value="name"
        @input="$emit('update:name', $event.target.value)"
        type="text"
        required
      />
    </div>
  </div>
</template>

<script setup>
defineProps({
  name: { type: String, required: true },
});
defineEmits(["update:name"]);
</script>

<style scoped>
.input-group {
  margin-bottom: 0.8rem;
}
</style>
