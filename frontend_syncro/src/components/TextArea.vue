<template>
  <div class="w-full">
    <label v-if="label" class="block mb-1 text-base font-bold text-gray-700">
      {{ label }}
    </label>

    <div class="relative">
      <textarea
        :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)"
        :placeholder="placeholder"
        :maxlength="maxlength"
        class="w-full h-40 p-3 pr-14 border border-gray-300 rounded-xl resize-none focus:outline-none focus:ring-2 focus:ring-teal-500 placeholder:text-gray-400"
      ></textarea>

      <div
        v-if="maxlength"
        class="absolute bottom-2 right-3 text-xs text-gray-500 pointer-events-none"
      >
        {{ modelValue.length }} / {{ maxlength }}
      </div>
    </div>
  </div>
</template>

<script setup>
defineProps({
  modelValue: { type: String, required: true },
  label: { type: String },
  placeholder: { type: String, default: "Digite algo..." },
  maxlength: { type: Number, default: 500 },
});
defineEmits(["update:modelValue"]);
</script>
