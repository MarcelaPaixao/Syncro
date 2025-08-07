<template>
  <div class="text-area">
    <label v-if="label">{{ label }}</label>
    <div class="textarea-container">
      <textarea
        :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)"
        :placeholder="placeholder"
        :maxlength="maxlength"
        required
      ></textarea>
      <div v-if="maxlength" class="char-counter">
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
  maxlength: { type: Number, default: 400 },
});
defineEmits(["update:modelValue"]);
</script>

<style scoped>
label {
  display: block;
  margin-bottom: 0.5rem;
}
.textarea {
  margin-bottom: 0.8rem;
}
.textarea-container {
  position: relative;
}

textarea {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  width: 100%;
  height: 100%;
  background: transparent;
  border: none;
  border-radius: 20px;
  box-sizing: border-box;
  padding: 10px 50px 10px 10px;
  resize: none;
  font-size: 15px;
}

.char-counter {
  position: absolute;
  bottom: 8px;
  right: 12px;
  font-size: 0.8rem;
  color: #555;
  pointer-events: none;
}

::placeholder {
  font-size: 15px;
}
</style>
