<template>
  <div class="modal-overlay" v-if="visivel" @click="$emit('close')">
    <div class="modal-content" @click.stop>
      <button @click="$emit('close')" class="close-btn">&times;</button>

      <header class="modal-header">
        <slot name="header">
          <h2>Pop-up</h2>
        </slot>
      </header>

      <main class="modal-body">
        <slot name="body"> </slot>
      </main>

      <footer class="modal-footer">
        <slot name="footer">
          <button @click="$emit('close')">Fechar</button>
        </slot>
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

<style>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(16, 108, 106, 0.6);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background-color: white;
  padding: 1.5rem;
  border-radius: 20px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
  width: 90%;
  max-width: 600px;
  position: relative;
  display: flex;
  flex-direction: column;
}

.close-btn {
  position: absolute;
  top: 10px;
  right: 15px;
  background: none;
  border: none;
  font-size: 2rem;
  cursor: pointer;
  color: #888;
}

.modal-header,
.modal-footer {
  padding: 1rem;
}

.modal-header {
  border-bottom: 1px solid #eee;
}

.modal-footer {
  border-top: 1px solid #eee;
  display: flex;
  justify-content: flex-end; /* Alinha botões à direita */
  gap: 1rem;
}

.modal-body {
  padding: 1rem;
  overflow-y: auto; /* Adiciona scroll se o conteúdo for muito grande */
  max-height: 70vh;
}
</style>
