<template>
  <BaseModal :visivel="visivel" @close="$emit('close')">
    <template v-slot:header>
      <h2 class="text-2xl font-bold text-gray-800">Nova Tarefa</h2>
    </template>

    <form id="form-tarefa" @submit.prevent="salvarMembros" class="space-y-4">
      <div class="grid grid-cols-2 gap-5"></div>
    </form>

    <template v-slot:footer>
      <BotaoCustomizado type="submit" form="form-membros" texto="Salvar" />
    </template>
  </BaseModal>
</template>

<script>
import BotaoCustomizado from "./BotaoCustomizado.vue";
// import InputString from "./InputString.vue";
import BaseModal from "./BaseModal.vue";

export default {
  name: "CriarTarefaModal",
  components: {
    BotaoCustomizado,
    // InputString,
    BaseModal,
  },
  props: {
    visivel: { type: Boolean, required: true },
    membrosDoGrupo: { type: Array, required: true },
  },
  emits: ["close", "salvar"],
  data() {
    return {
      tarefaLocal: {},
    };
  },
  methods: {
    salvarMembros() {},
  },
};
</script>
