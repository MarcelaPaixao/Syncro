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
.input-group {
  margin-bottom: 0.8rem;
}
</style>
