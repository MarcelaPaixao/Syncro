<template>
  <div class="w-full space-y-4">
    <div>
      <label class="block mb-1 text-base font-bold text-gray-700">Senha</label>
      <input
        :value="password"
        @input="$emit('update:password', $event.target.value)"
        type="password"
        :placeholder="textoTemp"
        required
        class="w-full px-3 py-2 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-teal-500"
      />
    </div>

    <div v-if="showConfirmacao">
      <label class="block mb-1 text-base font-bold text-gray-700"
        >Confirmar senha</label
      >
      <input
        :value="confirmPassword"
        @input="$emit('update:confirmPassword', $event.target.value)"
        type="password"
        :placeholder="textoTemp"
        required
        class="w-full px-3 py-2 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-teal-500"
      />
    </div>

    <p
      v-if="showConfirmacao && passwordError"
      class="text-sm text-red-600 italic -mt-2"
    >
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
