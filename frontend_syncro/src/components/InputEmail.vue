<template>
  <div class="input-email">
    <div class="input-group">
      <label>Email</label>
      <input
        :value="email"
        @input="$emit('update:email', $event.target.value)"
        type="email"
        required
      />
    </div>
  </div>
</template>

<script setup>
defineProps({
  email: { type: String, required: true },
});
defineEmits(["update:email"]);
</script>

<style scoped>
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
</style>
