<template>
  <div class="w-full">
    <label v-if="label" class="block mb-1 text-base font-bold text-gray-700">
      {{ label }}
    </label>

    <input
      :value="modelValue"
      @input="$emit('update:modelValue', $event.target.value)"
      :type="type"
      :placeholder="placeholder"
      required
      class="w-full px-3 py-2 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-teal-500 placeholder:text-gray-400"
    />
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
