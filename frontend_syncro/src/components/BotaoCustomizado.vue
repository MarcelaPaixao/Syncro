<template>
  <button
    :class="[
      'w-full max-w-[250px] h-[40px] px-4 py-2 border-none text-white text-base font-bold rounded-xl cursor-pointer transition-colors duration-200 mt-2.5',
      variantClasses,
    ]"
    @click="$emit('click')"
  >
    {{ texto }}
  </button>
</template>

<script setup>
import { computed } from "vue";

const props = defineProps({
  texto: { type: String, required: true },
  variante: { type: String, default: "principal" },
});

defineEmits(["click"]);

const variantClasses = computed(() => {
  switch (props.variante) {
    case "secundaria":
      return "bg-[#5fbbc1] hover:bg-[#77ccccf0]";

    default:
      return "bg-[#077a7d] hover:bg-[#0ca4aad4]";
  }
});
</script>
