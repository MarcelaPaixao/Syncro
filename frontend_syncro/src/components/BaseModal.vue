<template>
  <div
    class="fixed inset-0 w-screen h-screen bg-[#0a9296]/90 flex justify-center items-center z-50"
    v-if="visivel"
    @click="$emit('close')"
  >
    <div
      class="relative bg-white p-6 rounded-[30px] shadow-xl w-[35%] max-w-[650px] flex flex-col"
      @click.stop
    >
      <button
        @click="$emit('close')"
        class="absolute top-2 right-4 text-4xl text-gray-500 hover:text-gray-800"
      >
        &times;
      </button>

      <header
        class="modal-header border-b border-gray-200 pb-4 mb-4 flex justify-center"
      >
        <slot name="header">
          <h2 class="text-2xl font-bold"></h2>
        </slot>
      </header>

      <main class="modal-body overflow-y-auto max-h-[70vh] p-4">
        <slot></slot>
      </main>

      <footer
        class="modal-footer border-t border-gray-200 pt-4 mt-4 flex justify-center gap-4"
      >
        <slot name="footer"> </slot>
      </footer>
    </div>
  </div>
</template>

<script setup>
defineProps({
  visivel: { type: Boolean, required: true },
});
defineEmits(["close"]);
</script>
