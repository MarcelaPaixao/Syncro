<template>
  <BaseModal :visivel="visivel" @close="$emit('close')">
    <template v-slot:header>
      <h2 class="text-2xl font-bold text-gray-800">Descrição</h2>
    </template>

    <form
      id="form-descricao"
      @submit.prevent="salvarDescricao"
      class="space-y-4"
    >
      <TextArea v-model="descricaoLocal" label="Descrição" />
    </form>

    <template v-slot:footer />
  </BaseModal>
</template>

<script>
import TextArea from "./TextArea.vue";
import BaseModal from "./BaseModal.vue";

export default {
  name: "VerDescricaoModal",
  components: {
    TextArea,
    BaseModal,
  },

  props: {
    visivel: { type: Boolean, required: true },
    descricao: { type: String, required: true },
  },

  emits: ["close", "salvar"],
  data() {
    return {
      descricaoLocal: this.descricao,
    };
  },
  methods: {
    salvarDescricao() {},
  },
};
</script>
