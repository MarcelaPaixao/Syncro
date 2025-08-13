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
      <TextArea v-model="tarefaLocal.descricao" label="Descrição" />
    </form>

    <template v-slot:footer>
      <BotaoCustomizado type="submit" form="form-descricao" texto="Salvar" />
    </template>
  </BaseModal>
</template>

<script>
import BotaoCustomizado from "./BotaoCustomizado.vue";
import TextArea from "./TextArea.vue";
import BaseModal from "./BaseModal.vue";

export default {
  name: "VerDescricaoModal",
  components: {
    BotaoCustomizado,
    TextArea,
    BaseModal,
  },
  props: {
    visivel: { type: Boolean, required: true },
  },
  // ver se vai ter a opção de editar
  emits: ["close", "salvar"],
  data() {
    return {
      tarefaLocal: {
        descricao: "",
      },
    };
  },
  methods: {
    salvarDescricao() {},
  },
};
</script>
