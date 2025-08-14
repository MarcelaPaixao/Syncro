<template>
  <div class="flex justify-center items-center -mt-16">
    <div
      class="w-[110px] h-[110px] rounded-full bg-[#73babb] flex items-center justify-center text-white text-5xl"
    >
      {{ nome.charAt(0).toUpperCase() }}
    </div>
  </div>
</template>

<script setup>
defineProps({
  nome: { type: String, required: true },
});
</script>
