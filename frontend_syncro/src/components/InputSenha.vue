<template>
  <div class="input-senha">
    <div class="input-group">
      <label>Senha</label>
      <input
        :value="password"
        @input="$emit('update:password', $event.target.value)"
        type="password"
        :placeholder="textoTemp"
        required
      />
    </div>

    <div v-if="showConfirmacao" class="input-group">
      <label>Confirmar senha</label>
      <input
        :value="confirmPassword"
        @input="$emit('update:confirmPassword', $event.target.value)"
        type="password"
        :placeholder="textoTemp"
        required
      />
    </div>

    <p v-if="showConfirmacao && passwordError" class="error-message">
      {{ passwordError }}
    </p>
  </div>
</template>

<script setup>
defineProps({
  password: { type: String, required: true },
  confirmPassword: { type: String },
  passwordError: { type: String },
  showConfirmacao: { type: Boolean, default: false },
  textoTemp: { type: String, default: "" },
});
defineEmits(["update:password", "update:confirmPassword"]);
</script>

<style scoped>
.input-group {
  margin-bottom: 0.8rem;
}

.error-message {
  text-align: left;
  font-size: 0.9rem;
  margin-top: -0.5rem;
  margin-bottom: -0.1rem;
  font-style: italic;
  color: red;
}
</style>
