<template>
  <div class="input-group">
    <label>Senha</label>
    <input
      :value="password"
      @input="$emit('update:password', $event.target.value)"
      type="password"
      required
    />
  </div>

  <div class="input-group">
    <input
      :value="confirmPassword"
      @input="$emit('update:confirmPassword', $event.target.value)"
      type="password"
      required
    />
  </div>

  <p v-if="passwordError" class="error-message">
    {{ passwordError }}
  </p>
</template>

<script setup>
defineProps({
  password: { type: String, required: true },
  confirmPassword: { type: String, required: true },
  passwordError: { type: String },
});
defineEmits(["update:password", "update:confirmPassword"]);
</script>

<style>
label {
  text-align: left;
  display: block;
  margin-bottom: 0.5rem;
  color: #555;
  font-weight: bold;
}

.input-group {
  margin-bottom: 1rem;
}

input {
  width: 100%;
  padding: 0.6rem;
  border: 1px solid #ccc;
  border-radius: 10px;
  box-sizing: border-box; /* Garante que o padding não aumente a largura total */
}

.error-message {
  text-align: left;
  font-size: 0.9rem;
  margin-top: -0.5rem;
  font-style: italic;
  color: red;
}
</style>
