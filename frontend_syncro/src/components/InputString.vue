<template>
  <div class="input-string">
    <div class="input-group">
      <label v-if="rotulo">{{ rotulo }}</label>
      <input
        :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)"
        :type="tipo"
        :placeholder="textoTemp"
        required
      />
    </div>
  </div>
</template>

<script setup>
defineProps({
  //corrigir placeholder
  modelValue: { type: String, required: true },
  tipo: { type: String, default: "text" },
  rotulo: { type: String },
  textoTemp: { type: String },
});
defineEmits(["update:modelValue"]);
</script>

<style scoped>
.input-group {
  margin-bottom: 0.8rem;
}
</style>
