<template>
  <div class="input-string">
    <div class="input-group">
      <label v-if="label">{{ label }}</label>
      <input
        :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)"
        :type="type"
        :placeholder="placeholder"
        required
      />
    </div>
  </div>
</template>

<script setup>
defineProps({
  modelValue: { type: String, required: true },
  type: { type: String, default: "text" },
  label: { type: String },
  placeholder: { type: String, default: "Digite algo..." },
});
defineEmits(["update:modelValue"]);
</script>

<style scoped>
.input-group {
  margin-bottom: 0.8rem;
}

::placeholder {
  font-size: 15px;
}
</style>
